package adventurerstate.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import savestate.actions.ActionState;
import theFishing.actions.PrecludeAction;

public class PrecludeActionState implements ActionState {
    @Override
    public AbstractGameAction loadAction() {
        return new PrecludeAction();
    }
}
