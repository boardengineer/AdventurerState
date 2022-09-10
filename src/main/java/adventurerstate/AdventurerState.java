package adventurerstate;

import adventurerstate.actions.PrecludeActionState;
import adventurerstate.powers.*;
import adventurerstate.quests.AbstractQuestState;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import savestate.StateFactories;
import savestate.actions.ActionState;
import savestate.powers.PowerState;
import theFishing.TheFishing;
import theFishing.actions.PrecludeAction;
import theFishing.cards.CatchOfTheDay;
import theFishing.cards.EndsOfTheEarth;
import theFishing.powers.*;
import theFishing.quest.quests.AbstractQuest;

import java.util.HashMap;

@SpireInitializer
public class AdventurerState implements PostInitializeSubscriber, EditCardsSubscriber {
    public static HashMap<String, AbstractQuestState.QuestFactories> questByIdMap;
    public static HashMap<Class<? extends AbstractQuest>, AbstractQuestState.QuestFactories> questByTypeMap;

    public static void initialize() {
        BaseMod.subscribe(new AdventurerState());
    }

    @Override
    public void receivePostInitialize() {
        populateCurrentActionsFactory();
        populateActionsFactory();
        populatePowerFactory();
        populateCardFactories();
    }

    private void populateCurrentActionsFactory() {
    }

    private void populateActionsFactory() {
        StateFactories.actionByClassMap
                .put(PrecludeAction.class, new ActionState.ActionFactories(action -> new PrecludeActionState()));
    }

    private void populatePowerFactory() {
        StateFactories.powerByIdMap
                .put(AnglerFormPower.ID, new PowerState.PowerFactories(power -> new AnglerFormPowerState(power)));
        StateFactories.powerByIdMap
                .put(CollectorPower.ID, new PowerState.PowerFactories(power -> new CollectorPowerState(power), jsonString -> new CollectorPowerState(jsonString), jsonObject -> new CollectorPowerState(jsonObject)));
        StateFactories.powerByIdMap
                .put(DrawLessNextTurnPower.ID, new PowerState.PowerFactories(power -> new DrawLessNextTurnPowerState(power)));
        StateFactories.powerByIdMap
                .put(MintConditionPower.ID, new PowerState.PowerFactories(power -> new MintConditionPowerState(power), jsonString -> new MintConditionPowerState(jsonString), jsonObject -> new MintConditionPowerState(jsonObject)));
        StateFactories.powerByIdMap
                .put(RidiculousFishingPower.ID, new PowerState.PowerFactories(power -> new RidiculousFishingPowerState(power)));
        StateFactories.powerByIdMap
                .put(TakeItEasyPower.ID, new PowerState.PowerFactories(power -> new TakeItEasyPowerState(power)));
        StateFactories.powerByIdMap
                .put(TripwirePower.ID, new PowerState.PowerFactories(power -> new TripwirePowerState(power)));
    }

    private void populateCardFactories() {
    }

    @Override
    public void receiveEditCards() {
        // TODO: oh, come on, playing from the discard? what gives?
        BaseMod.removeCard(EndsOfTheEarth.ID, TheFishing.Enums.FISHING_COLOR);

        // TODO: Uses an in-place power.  Will have to be patched if not fixed.
        BaseMod.removeCard(CatchOfTheDay.ID, TheFishing.Enums.FISHING_COLOR);
    }
}