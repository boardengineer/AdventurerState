package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.CatchOfTheDayPower;

public class CatchOfTheDayPowerState extends AbstractAdventurerPowerState {
    public CatchOfTheDayPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        CatchOfTheDayPower result = new CatchOfTheDayPower();

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
