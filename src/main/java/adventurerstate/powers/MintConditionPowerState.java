package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.MintConditionPower;

public class MintConditionPowerState extends AbstractEasyPowerState {
    public MintConditionPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        MintConditionPower result = new MintConditionPower();

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
