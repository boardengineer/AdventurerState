package adventurerstate.actions;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import savestate.CardState;
import savestate.actions.CurrentActionState;
import theFishing.actions.FullHouseAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FullHouseActionState implements CurrentActionState {
    private final int[] selectGroupIndeces;
    private final int numCopies;

    public FullHouseActionState(AbstractGameAction action) {
        CardGroup selectGroup = ReflectionHacks
                .getPrivate(action, SelectCardsAction.class, "selectGroup");

        selectGroupIndeces = new int[selectGroup.size()];
        for (int i = 0; i < selectGroup.size(); i++) {
            selectGroupIndeces[i] = CardState.indexForCard(selectGroup.group.get(i));
        }

        numCopies = ReflectionHacks.getPrivate(action, FullHouseAction.class, "numCopies");
    }

    @Override
    public AbstractGameAction loadCurrentAction() {
        ArrayList<AbstractCard> selectGroup = Arrays.stream(selectGroupIndeces).boxed()
                                                    .map(CardState::cardForIndex).collect(Collectors
                        .toCollection(ArrayList::new));

        FullHouseAction result = new FullHouseAction(selectGroup, numCopies);

        // This should make the action only trigger the second half of the update
        ReflectionHacks
                .setPrivate(result, AbstractGameAction.class, "duration", 0);

        return result;
    }

    @SpirePatch(
            clz = SelectCardsAction.class,
            paramtypez = {},
            method = "update"
    )
    public static class HalfDoneActionPatch {
        public static void Postfix(SelectCardsAction _instance) {
            // Force the action to stay in the the manager until cards are selected
            if (AbstractDungeon.isScreenUp) {
                _instance.isDone = false;
            }
        }
    }
}
