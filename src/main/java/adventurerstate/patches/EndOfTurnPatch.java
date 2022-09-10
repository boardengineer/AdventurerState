package adventurerstate.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import ludicrousspeed.simulator.ActionSimulator;
import theFishing.quest.QuestHelper;

public class EndOfTurnPatch {
    @SpirePatch(
            clz = ActionSimulator.class,
            method = "callEndOfTurnActions"
    )
    public static class QuestEotPatch {
        @SpirePostfixPatch
        public static void updateQuests() {
            QuestHelper.atEndOfTurn();
        }
    }
}
