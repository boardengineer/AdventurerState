package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.ShinyShivPower;

public class ShinyShivePowerState extends AbstractEasyPowerState {
    public ShinyShivePowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        ShinyShivPower result = new ShinyShivPower();

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
