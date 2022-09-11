package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.FirstClassPower;

public class FirstClassPowerState extends AbstractEasyPowerState {
    public FirstClassPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        FirstClassPower result = new FirstClassPower();

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
