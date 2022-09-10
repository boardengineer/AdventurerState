package adventurerstate.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.google.gson.JsonObject;
import com.megacrit.cardcrawl.cards.AbstractCard;
import savestate.CardState;
import theFishing.patch.foil.FoilPatches;

public class FoilStatePatches {
    @SpirePatch(clz = CardState.class, method = SpirePatch.CLASS)
    public static class FoilStateField {
        public static SpireField<Boolean> foil = new SpireField<>(() -> false);
    }

    @SpirePatch(clz = CardState.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class})
    public static class MainConstructorFoilPatch {
        @SpirePostfixPatch
        public static void addFoilParam(CardState cardState, AbstractCard cardParam) {
            FoilStateField.foil.set(cardState, FoilPatches.isFoil(cardParam));
        }
    }

    @SpirePatch(clz = CardState.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {JsonObject.class})
    public static class JsonObjectConstructorFoilPatch {
        @SpirePostfixPatch
        public static void addFoilParam(CardState cardState, JsonObject cardJson) {
            FoilStateField.foil.set(cardState, cardJson.get("foil").getAsBoolean());
        }
    }

    @SpirePatch(clz = CardState.class, method = "loadCard")
    public static class LoadCardFoilPatch {
        private static boolean disable = true;

        @SpirePrefixPatch
        public static SpireReturn maybeDoNothing(CardState cardState) {
            if (disable) {
                disable = false;
                AbstractCard result = cardState.loadCard();
                FoilPatches.FoilField.foil.set(result, FoilStateField.foil.get(cardState));
                disable = true;
                return SpireReturn.Return(result);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = CardState.class, method = "jsonEncode")
    public static class EncodeFoilPatch {
        private static boolean disable = true;

        @SpirePrefixPatch
        public static SpireReturn maybeDoNothing(CardState cardState) {
            if (disable) {
                disable = false;
                JsonObject result = cardState.jsonEncode();
                result.addProperty("foil", FoilStateField.foil.get(cardState));
                disable = true;
                return SpireReturn.Return(result);
            }
            return SpireReturn.Continue();
        }
    }
}
