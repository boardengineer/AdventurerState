package adventurerstate.actions;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import savestate.actions.CurrentActionState;
import theFishing.actions.DiscardPileSetupAction;

public class DiscardPileSetupActionState implements CurrentActionState {
    public DiscardPileSetupActionState(AbstractGameAction action) {}

    @Override
    public AbstractGameAction loadCurrentAction() {
        DiscardPileSetupAction result = new DiscardPileSetupAction();

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
