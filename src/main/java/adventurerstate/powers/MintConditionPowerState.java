package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.MintConditionPower;

public class MintConditionPowerState extends AbstractAdventurerPowerState {
    public MintConditionPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        MintConditionPower result = new MintConditionPower(amount);

        preLoadPower(result);

        return result;
    }
}
