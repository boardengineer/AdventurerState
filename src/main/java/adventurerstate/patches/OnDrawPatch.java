package adventurerstate.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import ludicrousspeed.simulator.patches.PlayerPatches;
import theFishing.FishingMod;
import theFishing.patch.PreDrawPatch;

public class OnDrawPatch {
    @SpirePatch(
            clz = PlayerPatches.NoSoundDrawPatch2.class,
            method = "fastDraw"
    )
    public static class OnDrawCardPatch {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"card"}
        )
        public static void onDraw(AbstractPlayer __instance, AbstractCard card) {
            if (PreDrawPatch.DRAWN_DURING_TURN) {
                FishingMod.voyagedCards.add(card);
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "powers");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
