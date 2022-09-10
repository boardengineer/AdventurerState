package adventurerstate.quests;

import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.TheStorm;

public class TheStormState extends AbstractQuestState {
    public static final String QUEST_KEY = "THE_STORM";

    public TheStormState(AbstractQuest quest) {
        super(quest);
    }

    public TheStormState(JsonObject questJson) {
        super(questJson);
    }

    @Override
    public AbstractQuest loadQuest() {
        TheStorm result = new TheStorm();

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
