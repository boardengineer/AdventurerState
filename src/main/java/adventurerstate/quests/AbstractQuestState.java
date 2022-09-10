package adventurerstate.quests;

import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;

import java.util.function.Function;

public abstract class AbstractQuestState {
    public final int progress;
    public final int goal;

    protected AbstractQuestState(AbstractQuest quest) {
        this.progress = quest.progress;
        this.goal = quest.goal;
    }

    protected AbstractQuestState(JsonObject questJson) {
        this.progress = questJson.get("progress").getAsInt();
        this.goal = questJson.get("goal").getAsInt();
    }

    public abstract AbstractQuest loadQuest();

    protected void populatedSharedFields(AbstractQuest quest) {
        quest.progress = progress;
        quest.goal = goal;
    }

    public JsonObject jsonEncode() {
        JsonObject result = new JsonObject();

        result.addProperty("progress", progress);
        result.addProperty("goal", goal);

        return result;
    }

    public static class QuestFactories {
        public Function<AbstractQuest, AbstractQuestState> questFactory;
        public Function<JsonObject, AbstractQuestState> jsonQuestFactory;

        public QuestFactories(Function<AbstractQuest, AbstractQuestState> questFactory, Function<JsonObject, AbstractQuestState> jsonQuestFactory) {
            this.questFactory = questFactory;
            this.jsonQuestFactory = jsonQuestFactory;
        }
    }
}
