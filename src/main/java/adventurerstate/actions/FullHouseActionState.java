package adventurerstate.actions;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import savestate.CardState;
import savestate.actions.CurrentActionState;
import theFishing.actions.FullHouseAction2;

import java.util.Arrays;

public class FullHouseActionState implements CurrentActionState {
    private final int[] selectGroupIndeces;
    private final int dupes;

    public FullHouseActionState(AbstractGameAction action) {
        CardGroup selectGroup = ReflectionHacks
                .getPrivate(action, FullHouseAction2.class, "targetGroup");

        selectGroupIndeces = new int[selectGroup.size()];
        for (int i = 0; i < selectGroup.size(); i++) {
            selectGroupIndeces[i] = CardState.indexForCard(selectGroup.group.get(i));
        }

        dupes = ReflectionHacks.getPrivate(action, FullHouseAction2.class, "dupes");
    }

    @Override
    public AbstractGameAction loadCurrentAction() {
        CardGroup possCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Arrays.stream(selectGroupIndeces).boxed()
              .map(CardState::cardForIndex).forEach(card -> possCards.addToBottom(card));

        FullHouseAction2 result = new FullHouseAction2(possCards, dupes);

        // This should make the action only trigger the second half of the update
        ReflectionHacks
                .setPrivate(result, AbstractGameAction.class, "duration", 0);

        return result;
    }

    @SpirePatch(
            clz = FullHouseAction2.class,
            paramtypez = {},
            method = "update"
    )
    public static class HalfDoneActionPatch {
        public static void Postfix(FullHouseAction2 _instance) {
            // Force the action to stay in the the manager until cards are selected
            if (AbstractDungeon.isScreenUp) {
                _instance.isDone = false;
            }
        }
    }
}
