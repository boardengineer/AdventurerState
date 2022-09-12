package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.RidiculousFishingPower;

public class RidiculousFishingPowerState extends AbstractAdventurerPowerState {
    public RidiculousFishingPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        RidiculousFishingPower result = new RidiculousFishingPower(amount);

        preLoadPower(result);

        return result;
    }
}
