package adventurerstate.powers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.CollectorPower;

public class CollectorPowerState extends AbstractEasyPowerState {
    private final boolean activated;

    public CollectorPowerState(AbstractPower power) {
        super(power);

        CollectorPower actualPower = (CollectorPower) power;

        this.activated = actualPower.activated;
    }

    public CollectorPowerState(String jsonString) {
        super(jsonString);

        JsonObject parsed = new JsonParser().parse(jsonString).getAsJsonObject();

        this.activated = parsed.get("activated").getAsBoolean();
    }

    public CollectorPowerState(JsonObject powerJson) {
        super(powerJson);

        this.activated = powerJson.get("activated").getAsBoolean();
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        CollectorPower result = new CollectorPower(amount);

        preLoadPower(result);

        result.activated = activated;

        return result;
    }

    @Override
    public String encode() {
        JsonObject parsed = new JsonParser().parse(super.encode()).getAsJsonObject();

        parsed.addProperty("activated", activated);

        return parsed.toString();
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        result.addProperty("activated", activated);

        return result;
    }
}
