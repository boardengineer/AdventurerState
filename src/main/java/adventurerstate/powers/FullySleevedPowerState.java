package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.FullySleevedPower;

public class FullySleevedPowerState extends AbstractEasyPowerState {
    public FullySleevedPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        FullySleevedPower result = new FullySleevedPower(amount);

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
