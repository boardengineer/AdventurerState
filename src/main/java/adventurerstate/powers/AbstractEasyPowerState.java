package adventurerstate.powers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacrit.cardcrawl.powers.AbstractPower;
import savestate.powers.PowerState;
import theFishing.powers.AbstractAdventurerPower;

public abstract class AbstractEasyPowerState extends PowerState {
    private final int amount2;
    private final boolean isTwoAmount;
    private final boolean canGoNegative2;

    public AbstractEasyPowerState(AbstractPower power) {
        super(power);

        AbstractAdventurerPower actualPower = (AbstractAdventurerPower) power;
        this.amount2 = actualPower.amount2;
        this.isTwoAmount = actualPower.isTwoAmount;
        this.canGoNegative2 = actualPower.canGoNegative2;
    }

    public AbstractEasyPowerState(String jsonString) {
        super(jsonString);

        JsonObject parsed = new JsonParser().parse(jsonString).getAsJsonObject();

        this.amount2 = parsed.get("amount2").getAsInt();
        this.isTwoAmount = parsed.get("isTwoAmount").getAsBoolean();
        this.canGoNegative2 = parsed.get("canGoNegative2").getAsBoolean();
    }

    public AbstractEasyPowerState(JsonObject powerJson) {
        super(powerJson);

        this.amount2 = powerJson.get("amount2").getAsInt();
        this.isTwoAmount = powerJson.get("isTwoAmount").getAsBoolean();
        this.canGoNegative2 = powerJson.get("canGoNegative2").getAsBoolean();
    }

    protected void preLoadPower(AbstractAdventurerPower power) {
        power.amount2 = this.amount2;
        power.isTwoAmount = this.isTwoAmount;
        power.canGoNegative2 = this.canGoNegative2;
    }

    @Override
    public String encode() {
        JsonObject parsed = new JsonParser().parse(super.encode()).getAsJsonObject();

        parsed.addProperty("amount2", amount2);
        parsed.addProperty("isTwoAmount", isTwoAmount);
        parsed.addProperty("canGoNegative2", canGoNegative2);

        return parsed.toString();
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        result.addProperty("amount2", amount2);
        result.addProperty("isTwoAmount", isTwoAmount);
        result.addProperty("canGoNegative2", canGoNegative2);

        return result;
    }
}
