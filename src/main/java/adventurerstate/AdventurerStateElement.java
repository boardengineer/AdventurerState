package adventurerstate;

import adventurerstate.quests.AbstractQuestState;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import savestate.StateElement;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

import java.util.Arrays;

public class AdventurerStateElement implements StateElement {
    public static String ELEMENT_KEY = "ADVENTURE_MOD_STATE";

    AbstractQuestState[] quests;

    public AdventurerStateElement() {
        quests = new AbstractQuestState[QuestHelper.quests.size()];

        for (int i = 0; i < quests.length; i++) {
            AbstractQuest quest = QuestHelper.quests.get(i);
            quests[i] = AdventurerState.questByTypeMap.get(quest.getClass()).questFactory
                    .apply(quest);
        }
    }

    public AdventurerStateElement(JsonObject elementJson) {
        JsonArray questsJson = elementJson.get("quests").getAsJsonArray();

        quests = new AbstractQuestState[questsJson.size()];

        for (int i = 0; i < quests.length; i++) {
            JsonObject singleQuestJson = questsJson.get(i).getAsJsonObject();
            String questKey = singleQuestJson.get("quest_key").getAsString();

            quests[i] = AdventurerState.questByIdMap.get(questKey).jsonQuestFactory
                    .apply(singleQuestJson);
        }
    }

    @Override
    public String encode() {
        return jsonEncode().toString();
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = new JsonObject();

        JsonArray questsArray = new JsonArray();
        for (int i = 0; i < quests.length; i++) {
            questsArray.add(quests[i].jsonEncode());
        }

        result.add("quests", questsArray);

        return result;
    }

    @Override
    public void restore() {
        QuestHelper.quests.clear();
        Arrays.stream(quests).forEach(questState -> QuestHelper.quests.add(questState.loadQuest()));
    }
}
