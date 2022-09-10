package adventurerstate.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import savestate.CardState;

public class FoilPatches {
    @SpirePatch(clz = CardState.class, method = SpirePatch.CLASS)
    public static class FoilStateField {
        public static SpireField<Boolean> foil = new SpireField<>(() -> false);
    }
}
