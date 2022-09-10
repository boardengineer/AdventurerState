package adventurerstate.quests;

import basemod.ReflectionHacks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.util.Pair;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.TheFishOPedia;

import java.util.ArrayList;
import java.util.Arrays;

public class TheFishOPediaState extends AbstractQuestState {
    public static final String QUEST_KEY = "THE_FISH_O_PEDIA";

    // Only bother storing ids
    private final String[] alreadyPlayed;

    public TheFishOPediaState(AbstractQuest quest) {
        super(quest);

        ArrayList<Pair<String, String>> alreadyPlayedPairs = ReflectionHacks
                .getPrivate(quest, TheFishOPedia.class, "alreadyPlayed");
        this.alreadyPlayed = new String[alreadyPlayedPairs.size()];

        for (int i = 0; i < alreadyPlayedPairs.size(); i++) {
            this.alreadyPlayed[i] = alreadyPlayedPairs.get(i).getKey();
        }
    }

    public TheFishOPediaState(JsonObject questJson) {
        super(questJson);

        JsonArray alreadyPlayedArray = questJson.get("alreadyPlayed").getAsJsonArray();

        this.alreadyPlayed = new String[alreadyPlayedArray.size()];
        for (int i = 0; i < alreadyPlayed.length; i++) {
            alreadyPlayed[i] = alreadyPlayedArray.get(i).getAsString();
        }
    }

    @Override
    public AbstractQuest loadQuest() {
        TheFishOPedia result = new TheFishOPedia();

        populatedSharedFields(result);

        ArrayList<Pair<String, String>> alreadyPlayedPairs = ReflectionHacks
                .getPrivate(result, TheFishOPedia.class, "alreadyPlayed");
        alreadyPlayedPairs.clear();
        Arrays.stream(alreadyPlayed)
              .forEach(cardId -> alreadyPlayedPairs.add(new Pair<>(cardId, "")));

        return result;
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        JsonArray alreadyPlayedArray = new JsonArray();

        Arrays.stream(alreadyPlayed).forEach(s -> alreadyPlayedArray.add(s));
        result.add("alreadyPlayed", alreadyPlayedArray);

        result.addProperty("quest_key", QUEST_KEY);

        return result;
    }
}
