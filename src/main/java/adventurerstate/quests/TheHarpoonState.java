package adventurerstate.quests;

import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.TheHarpoon;

public class TheHarpoonState extends AbstractQuestState {
    public static final String QUEST_KEY = "THE_HARPOON";

    public TheHarpoonState(AbstractQuest quest) {
        super(quest);
    }

    public TheHarpoonState(JsonObject questJson) {
        super(questJson);
    }

    @Override
    public AbstractQuest loadQuest() {
        TheHarpoon result = new TheHarpoon();

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
