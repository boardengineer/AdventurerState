package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.FinishingTouchesPower;

public class FinishingTouchesPowerState extends AbstractEasyPowerState {
    public FinishingTouchesPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        FinishingTouchesPower result = new FinishingTouchesPower();

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
