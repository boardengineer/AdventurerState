package adventurerstate.actions;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import savestate.CardState;
import savestate.actions.CurrentActionState;
import theFishing.actions.DiscardPileSetupAction;
import theFishing.actions.XMarksTheSpotAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class XMarksTheSpotActionState implements CurrentActionState {
    private final CardState[] cardOptions;

    public XMarksTheSpotActionState(AbstractGameAction action) {
        CardGroup selectGroup = ReflectionHacks
                .getPrivate(action, XMarksTheSpotAction.class, "selectGroup");

        cardOptions = selectGroup.group.stream().map(CardState::forCard).toArray(CardState[]::new);
    }

    @Override
    public AbstractGameAction loadCurrentAction() {
        ArrayList<AbstractCard> selectGroup = Arrays.stream(cardOptions).map(CardState::loadCard)
                                                    .collect(Collectors
                                                            .toCollection(ArrayList::new));

        XMarksTheSpotAction result = new XMarksTheSpotAction(selectGroup);

        // This should make the action only trigger the second half of the update
        ReflectionHacks
                .setPrivate(result, AbstractGameAction.class, "duration", 0);

        return result;
    }

    @SpirePatch(
            clz = DiscardPileSetupAction.class,
            paramtypez = {},
            method = "update"
    )
    public static class HalfDoneActionPatch {
        public static void Postfix(DiscardPileSetupAction _instance) {
            // Force the action to stay in the the manager until cards are selected
            if (AbstractDungeon.isScreenUp) {
                _instance.isDone = false;
            }
        }
    }
}
