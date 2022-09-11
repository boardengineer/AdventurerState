package adventurerstate.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import savestate.actions.ActionState;
import theFishing.actions.StormCompletionAction;

public class StormCompletionActionState implements ActionState {
    @Override
    public AbstractGameAction loadAction() {
        return new StormCompletionAction();
    }
}
