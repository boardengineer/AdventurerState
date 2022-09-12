package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.TakeItEasyPower;

public class TakeItEasyPowerState extends AbstractAdventurerPowerState {
    public TakeItEasyPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        TakeItEasyPower result = new TakeItEasyPower();

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
