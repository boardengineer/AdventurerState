package adventurerstate.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.DrawLessNextTurnPower;

public class DrawLessNextTurnPowerState extends AbstractAdventurerPowerState {
    public DrawLessNextTurnPowerState(AbstractPower power) {
        super(power);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        DrawLessNextTurnPower result = new DrawLessNextTurnPower(amount);

        preLoadPower(result);

        return result;
    }
}
