package adventurerstate.quests;

import com.google.gson.JsonObject;
import theFishing.quest.quests.AbstractQuest;

import java.util.function.Function;

public abstract class AbstractQuestState {
    public abstract AbstractQuest loadQuest();

    public abstract String getKey();

    public abstract JsonObject jsonEncode();

    public static class QuestFactories {
        public Function<AbstractQuest, AbstractQuestState> questFactory;
        public Function<JsonObject, AbstractQuestState> jsonQuestFactory;

        public QuestFactories(Function<AbstractQuest, AbstractQuestState> questFactory, Function<JsonObject, AbstractQuestState> jsonQuestFactory) {
            this.questFactory = questFactory;
            this.jsonQuestFactory = jsonQuestFactory;
        }
    }
}
