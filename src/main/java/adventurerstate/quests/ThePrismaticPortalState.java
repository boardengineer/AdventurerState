package adventurerstate.quests;

import basemod.ReflectionHacks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;
import theFishing.quest.quests.ThePrismaticPortal;

public class ThePrismaticPortalState extends AbstractQuestState {
    public static final String QUEST_KEY = "THE_PRISMATIC_PORTAL";

    private final boolean[] okayed = new boolean[3];

    public ThePrismaticPortalState(AbstractQuest quest) {
        super(quest);

        boolean[] questOkayed = ReflectionHacks
                .getPrivate(quest, ThePrismaticPortal.class, "okayed");
        okayed[0] = questOkayed[0];
        okayed[1] = questOkayed[1];
        okayed[2] = questOkayed[2];
    }

    public ThePrismaticPortalState(JsonObject questJson) {
        super(questJson);

        JsonArray okayedJsonArray = questJson.get("okayed").getAsJsonArray();

        for (int i = 0; i < okayed.length; i++) {
            okayed[i] = okayedJsonArray.get(i).getAsBoolean();
        }
    }

    @Override
    public AbstractQuest loadQuest() {
        ThePrismaticPortal result = new ThePrismaticPortal();

        populatedSharedFields(result);

        boolean[] questOkayed = ReflectionHacks
                .getPrivate(result, ThePrismaticPortal.class, "okayed");
        questOkayed[0] = okayed[0];
        questOkayed[1] = okayed[1];
        questOkayed[2] = okayed[2];

        return result;
    }

    @Override
    public JsonObject jsonEncode() {
        JsonObject result = super.jsonEncode();

        JsonArray okayedJsonArray = new JsonArray();
        okayedJsonArray.add(okayed[0]);
        okayedJsonArray.add(okayed[1]);
        okayedJsonArray.add(okayed[2]);
        result.add("okayed", okayedJsonArray);

        result.addProperty("quest_key", QUEST_KEY);

        return result;
    }
}
