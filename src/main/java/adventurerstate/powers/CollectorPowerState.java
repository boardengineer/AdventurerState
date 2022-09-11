package adventurerstate.powers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.CollectorPower;

public class CollectorPowerState extends AbstractEasyPowerState {
    public CollectorPowerState(AbstractPower power) {
        super(power);

        CollectorPower actualPower = (CollectorPower) power;
    }

    public CollectorPowerState(String jsonString) {
        super(jsonString);

        JsonObject parsed = new JsonParser().parse(jsonString).getAsJsonObject();
    }

    public CollectorPowerState(JsonObject powerJson) {
        super(powerJson);
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        CollectorPower result = new CollectorPower(amount);

        preLoadPower(result);

        return result;
    }

    @Override
    public String encode() {
        JsonObject parsed = new JsonParser().parse(super.encode()).getAsJsonObject();

        return parsed.toString();
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        return result;
    }
}
