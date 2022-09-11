package adventurerstate.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import savestate.actions.ActionState;
import theFishing.actions.AbandonQuestAction;

public class AbandonQuestActionState implements ActionState {
    private final String questId;

    public AbandonQuestActionState(AbstractGameAction action) {
        questId = ReflectionHacks
                .getPrivate(action, AbandonQuestAction.class, "ID");
    }

    @Override
    public AbstractGameAction loadAction() {
        return new AbandonQuestAction(questId);
    }
}
