package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.AllOutPower;

public class AllOutPowerState extends AbstractEasyPowerState {
    public AllOutPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        AllOutPower result = new AllOutPower(amount);

        preLoadPower(result);

        return result;
    }
}
