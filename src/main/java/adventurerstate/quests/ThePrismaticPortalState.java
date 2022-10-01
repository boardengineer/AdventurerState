package adventurerstate.quests;

import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.ThePrismaticPortal;

public class ThePrismaticPortalState extends AbstractQuestState {
    public static final String QUEST_KEY = "THE_PRISMATIC_PORTAL";

    public ThePrismaticPortalState(AbstractQuest quest) {
        super(quest);
    }

    public ThePrismaticPortalState(JsonObject questJson) {
        super(questJson);
    }

    @Override
    public AbstractQuest loadQuest() {
        ThePrismaticPortal result = new ThePrismaticPortal();

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
