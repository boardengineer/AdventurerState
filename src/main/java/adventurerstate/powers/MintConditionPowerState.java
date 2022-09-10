package adventurerstate.powers;

import basemod.ReflectionHacks;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.CollectorPower;
import theFishing.powers.MintConditionPower;

public class MintConditionPowerState extends AbstractEasyPowerState {
    private final int damage;

    public MintConditionPowerState(AbstractPower power) {
        super(power);

        this.damage = ReflectionHacks.getPrivate(power, MintConditionPower.class, "damage");
    }

    public MintConditionPowerState(String jsonString) {
        super(jsonString);

        JsonObject parsed = new JsonParser().parse(jsonString).getAsJsonObject();

        this.damage = parsed.get("damage").getAsInt();
    }

    public MintConditionPowerState(JsonObject powerJson) {
        super(powerJson);

        this.damage = powerJson.get("damage").getAsInt();
    }

    @Override
    public AbstractPower loadPower(AbstractCreature targetAndSource) {
        CollectorPower result = new CollectorPower(amount);

        preLoadPower(result);

        ReflectionHacks.setPrivate(result, MintConditionPower.class, "damage", damage);

        return result;
    }

    @Override
    public String encode() {
        JsonObject parsed = new JsonParser().parse(super.encode()).getAsJsonObject();

        parsed.addProperty("damage", damage);

        return parsed.toString();
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        result.addProperty("damage", damage);

        return result;
    }
}
