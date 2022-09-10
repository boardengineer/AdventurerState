package adventurerstate.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import javassist.CtBehavior;
import ludicrousspeed.simulator.patches.MonsterPatch;
import theFishing.quest.QuestHelper;

public class OnKillEnemyPatch {
    @SpirePatch(
            clz = MonsterPatch.MonsterDeathWithRelicsPatch.class,
            method = "Prefix"
    )
    public static class OnKillEnemy {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void triggerOnDeath(AbstractMonster __instance, boolean triggerRelics) {
            QuestHelper.onKillEnemy();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(MonsterGroup.class, "areMonstersBasicallyDead");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
