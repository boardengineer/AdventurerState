package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.VexingDealPower;

public class VexingDealPowerState extends AbstractEasyPowerState {
    public VexingDealPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        VexingDealPower result = new VexingDealPower();

        preLoadPower(result);

        return result;
    }
}
