package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.PowerPelletPower;

public class PowerPelletPowerState extends AbstractEasyPowerState {
    public PowerPelletPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        PowerPelletPower result = new PowerPelletPower(amount);

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
