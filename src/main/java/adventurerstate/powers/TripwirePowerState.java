package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.TripwirePower;

public class TripwirePowerState extends AbstractEasyPowerState {
    public TripwirePowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        TripwirePower result = new TripwirePower(amount);

        preLoadPower(result);

        return result;
    }
}
