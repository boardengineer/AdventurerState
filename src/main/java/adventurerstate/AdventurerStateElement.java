package adventurerstate;

import adventurerstate.quests.AbstractQuestState;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import savestate.CardState;
import savestate.StateElement;
import theFishing.FishingMod;
import theFishing.patch.PreDrawPatch;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

import java.util.Arrays;

public class AdventurerStateElement implements StateElement {
    public static String ELEMENT_KEY = "ADVENTURE_MOD_STATE";

    private final AbstractQuestState[] quests;
    private final int[] voyagedCardsIndeces;
    private final boolean drawnCard;

    public AdventurerStateElement() {
        this.quests = new AbstractQuestState[QuestHelper.quests.size()];

        for (int i = 0; i < quests.length; i++) {
            AbstractQuest quest = QuestHelper.quests.get(i);
            if (!AdventurerState.questByTypeMap.containsKey(quest.getClass())) {
                throw new IllegalArgumentException("No State Factory for quest " + quest
                        .getClass());
            }
            quests[i] = AdventurerState.questByTypeMap.get(quest.getClass()).questFactory
                    .apply(quest);
        }

        this.drawnCard = PreDrawPatch.DRAWN_DURING_TURN;

        voyagedCardsIndeces = new int[FishingMod.voyagedCards.size()];
        for (int i = 0; i < FishingMod.voyagedCards.size(); i++) {
            voyagedCardsIndeces[i] = CardState.indexForCard(FishingMod.voyagedCards.get(i));
        }
    }

    public AdventurerStateElement(String elementJson) {
        this.quests = null;
        this.drawnCard = false;
        this.voyagedCardsIndeces = null;
    }

    public AdventurerStateElement(JsonObject elementJson) {
        JsonArray questsJson = elementJson.get("quests").getAsJsonArray();
        this.quests = new AbstractQuestState[questsJson.size()];
        for (int i = 0; i < quests.length; i++) {
            JsonObject singleQuestJson = questsJson.get(i).getAsJsonObject();
            String questKey = singleQuestJson.get("quest_key").getAsString();

            quests[i] = AdventurerState.questByIdMap.get(questKey).jsonQuestFactory
                    .apply(singleQuestJson);
        }

        this.drawnCard = elementJson.get("drawnCard").getAsBoolean();

        JsonArray voyagedCardsJson = elementJson.get("voyaged_card_indeces").getAsJsonArray();
        this.voyagedCardsIndeces = new int[voyagedCardsJson.size()];
        for (int i = 0; i < voyagedCardsIndeces.length; i++) {
            voyagedCardsIndeces[i] = voyagedCardsJson.get(i).getAsInt();
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
        result.addProperty("drawnCard", drawnCard);

        JsonArray voyagedCardsJson = new JsonArray();
        Arrays.stream(voyagedCardsIndeces)
              .forEach(index -> voyagedCardsJson.add(index));
        result.add("voyaged_card_indeces", voyagedCardsJson);

        return result;
    }

    @Override
    public void restore() {
        QuestHelper.quests.clear();
        Arrays.stream(quests).forEach(questState -> QuestHelper.quests.add(questState.loadQuest()));

        PreDrawPatch.DRAWN_DURING_TURN = drawnCard;

        FishingMod.voyagedCards.clear();
        Arrays.stream(voyagedCardsIndeces).filter(index -> index >= 0)
              .forEach(index -> FishingMod.voyagedCards.add(CardState.cardForIndex(index)));
    }
}
