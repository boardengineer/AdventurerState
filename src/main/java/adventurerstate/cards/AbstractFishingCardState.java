package adventurerstate.cards;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megacrit.cardcrawl.cards.AbstractCard;
import savestate.CardState;
import theFishing.cards.AbstractFishingCard;

public class AbstractFishingCardState extends CardState {
    public static final String TYPE_KEY = "AbstractFishingCard";

    public final int secondMagic;
    public final int baseSecondMagic;
    public final boolean upgradedSecondMagic;
    public final boolean isSecondMagicModified;

    public final int thirdMagic;
    public final int baseThirdMagic;
    public final boolean upgradedThirdMagic;
    public final boolean isThirdMagicModified;

    public final int secondDamage;
    public final int baseSecondDamage;
    public final boolean upgradedSecondDamage;
    public final boolean isSecondDamageModified;

    public AbstractFishingCardState(AbstractCard card) {
        super(card);

        AbstractFishingCard fishCard = (AbstractFishingCard) card;

        this.secondMagic = fishCard.secondMagic;
        this.baseSecondMagic = fishCard.baseSecondMagic;
        this.upgradedSecondMagic = fishCard.upgradedSecondMagic;
        this.isSecondMagicModified = fishCard.isSecondMagicModified;

        this.thirdMagic = fishCard.thirdMagic;
        this.baseThirdMagic = fishCard.baseThirdMagic;
        this.upgradedThirdMagic = fishCard.upgradedThirdMagic;
        this.isThirdMagicModified = fishCard.isThirdMagicModified;

        this.secondDamage = fishCard.secondDamage;
        this.baseSecondDamage = fishCard.baseSecondDamage;
        this.upgradedSecondDamage = fishCard.upgradedSecondDamage;
        this.isSecondDamageModified = fishCard.isSecondDamageModified;
    }

    public AbstractFishingCardState(String json) {
        super(json);

        JsonObject parsed = new JsonParser().parse(json).getAsJsonObject();

        this.secondMagic = parsed.get("secondMagic").getAsInt();
        this.baseSecondMagic = parsed.get("baseSecondMagic").getAsInt();
        this.upgradedSecondMagic = parsed.get("upgradedSecondMagic").getAsBoolean();
        this.isSecondMagicModified = parsed.get("isSecondMagicModified").getAsBoolean();

        this.thirdMagic = parsed.get("thirdMagic").getAsInt();
        this.baseThirdMagic = parsed.get("baseThirdMagic").getAsInt();
        this.upgradedThirdMagic = parsed.get("upgradedThirdMagic").getAsBoolean();
        this.isThirdMagicModified = parsed.get("isThirdMagicModified").getAsBoolean();

        this.secondDamage = parsed.get("secondDamage").getAsInt();
        this.baseSecondDamage = parsed.get("baseSecondDamage").getAsInt();
        this.upgradedSecondDamage = parsed.get("upgradedSecondDamage").getAsBoolean();
        this.isSecondDamageModified = parsed.get("isSecondDamageModified").getAsBoolean();
    }

    public AbstractFishingCardState(JsonObject cardJson) {
        super(cardJson);

        this.secondMagic = cardJson.get("secondMagic").getAsInt();
        this.baseSecondMagic = cardJson.get("baseSecondMagic").getAsInt();
        this.upgradedSecondMagic = cardJson.get("upgradedSecondMagic").getAsBoolean();
        this.isSecondMagicModified = cardJson.get("isSecondMagicModified").getAsBoolean();

        this.thirdMagic = cardJson.get("thirdMagic").getAsInt();
        this.baseThirdMagic = cardJson.get("baseThirdMagic").getAsInt();
        this.upgradedThirdMagic = cardJson.get("upgradedThirdMagic").getAsBoolean();
        this.isThirdMagicModified = cardJson.get("isThirdMagicModified").getAsBoolean();

        this.secondDamage = cardJson.get("secondDamage").getAsInt();
        this.baseSecondDamage = cardJson.get("baseSecondDamage").getAsInt();
        this.upgradedSecondDamage = cardJson.get("upgradedSecondDamage").getAsBoolean();
        this.isSecondDamageModified = cardJson.get("isSecondDamageModified").getAsBoolean();
    }

    @Override
    public AbstractCard loadCard() {
        AbstractFishingCard result = (AbstractFishingCard) super.loadCard();

        result.secondMagic = this.secondMagic;
        result.baseSecondMagic = this.baseSecondMagic;
        result.upgradedSecondMagic = this.upgradedSecondMagic;
        result.isSecondMagicModified = this.isSecondMagicModified;

        result.thirdMagic = this.thirdMagic;
        result.baseThirdMagic = this.baseThirdMagic;
        result.upgradedThirdMagic = this.upgradedThirdMagic;
        result.isThirdMagicModified = this.isThirdMagicModified;

        result.secondDamage = this.secondDamage;
        result.baseSecondDamage = this.baseSecondDamage;
        result.upgradedSecondDamage = this.upgradedSecondDamage;
        result.isSecondDamageModified = this.isSecondDamageModified;

        return result;
    }

    @Override
    public String encode() {
        return jsonEncode().toString();
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        result.addProperty("secondMagic", secondMagic);
        result.addProperty("baseSecondMagic", baseSecondMagic);
        result.addProperty("upgradedSecondMagic", upgradedSecondMagic);
        result.addProperty("isSecondMagicModified", isSecondMagicModified);

        result.addProperty("thirdMagic", thirdMagic);
        result.addProperty("baseThirdMagic", baseThirdMagic);
        result.addProperty("upgradedThirdMagic", upgradedThirdMagic);
        result.addProperty("isThirdMagicModified", isThirdMagicModified);

        result.addProperty("secondDamage", secondDamage);
        result.addProperty("baseSecondDamage", baseSecondDamage);
        result.addProperty("upgradedSecondDamage", upgradedSecondDamage);
        result.addProperty("isSecondDamageModified", isSecondDamageModified);

        result.addProperty("type", TYPE_KEY);

        return result;
    }
}
