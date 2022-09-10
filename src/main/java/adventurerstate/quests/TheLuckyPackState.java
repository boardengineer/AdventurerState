package adventurerstate.quests;

import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.TheLuckyPack;

public class TheLuckyPackState extends AbstractQuestState {
    public static final String QUEST_KEY = "THE_LUCKY_PACK";

    public TheLuckyPackState(AbstractQuest quest) {
        super(quest);
    }

    public TheLuckyPackState(JsonObject questJson) {
        super(questJson);
    }

    @Override
    public AbstractQuest loadQuest() {
        TheLuckyPack result = new TheLuckyPack();

        populatedSharedFields(result);

        return result;
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        result.addProperty("quest_key", QUEST_KEY);

        return result;
    }
}
