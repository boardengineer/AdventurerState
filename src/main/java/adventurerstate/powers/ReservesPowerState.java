package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.ReservesPower;

public class ReservesPowerState extends AbstractAdventurerPowerState {
    public ReservesPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        ReservesPower result = new ReservesPower();

        preLoadPower(result);

        result.amount = amount;

        return result;
    }
}
