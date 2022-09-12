package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.AnglerFormPower;

public class AnglerFormPowerState extends AbstractAdventurerPowerState {
    public AnglerFormPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        AnglerFormPower result = new AnglerFormPower(amount);

        preLoadPower(result);

        return result;
    }
}
