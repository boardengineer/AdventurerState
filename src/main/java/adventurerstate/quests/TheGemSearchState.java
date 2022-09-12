package adventurerstate.quests;

import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.TheGemSearch;

public class TheGemSearchState extends AbstractQuestState {
    public static final String QUEST_KEY = "GEM_SEARCH";

    public TheGemSearchState(AbstractQuest quest) {
        super(quest);
    }

    public TheGemSearchState(JsonObject questJson) {
        super(questJson);
    }

    @Override
    public AbstractQuest loadQuest() {
        TheGemSearch result = new TheGemSearch();

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
