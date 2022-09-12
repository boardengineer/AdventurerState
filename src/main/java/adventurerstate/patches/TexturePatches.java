package adventurerstate.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ludicrousspeed.LudicrousSpeedMod;
import theFishing.powers.AbstractAdventurerPower;

public class TexturePatches {
    @SpirePatch(clz = AbstractAdventurerPower.class, method = SpirePatch.CONSTRUCTOR)
    public static class NoLoadTextureInConstructorPatch {
        @SpirePrefixPatch
        public static SpireReturn doQuick(AbstractAdventurerPower power, String ID, String NAME, AbstractPower.PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
            if(LudicrousSpeedMod.plaidMode) {
                power.ID = ID;

                ReflectionHacks.setPrivate(power, AbstractPower.class, "isTurnBased", isTurnBased);
                
                power.name = NAME;

                power.owner = owner;
                power.amount = amount;
                power.type = powerType;

                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }
    }
}
