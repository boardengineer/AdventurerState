package adventurerstate;

import adventurerstate.actions.MopUpActionState;
import adventurerstate.actions.PrecludeActionState;
import adventurerstate.cards.AbstractFishingCardState;
import adventurerstate.powers.*;
import adventurerstate.quests.*;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import savestate.CardState;
import savestate.StateElement;
import savestate.StateFactories;
import savestate.actions.ActionState;
import savestate.actions.CurrentActionState;
import savestate.powers.PowerState;
import theFishing.TheFishing;
import theFishing.actions.MopUpAction;
import theFishing.actions.PrecludeAction;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.CatchOfTheDay;
import theFishing.cards.EndsOfTheEarth;
import theFishing.powers.*;
import theFishing.quest.quests.*;

import java.util.HashMap;

@SpireInitializer
public class AdventurerState implements PostInitializeSubscriber, EditCardsSubscriber {
    public static HashMap<String, AbstractQuestState.QuestFactories> questByIdMap = new HashMap<>();
    public static HashMap<Class<? extends AbstractQuest>, AbstractQuestState.QuestFactories> questByTypeMap = new HashMap<>();

    public static void initialize() {
        BaseMod.subscribe(new AdventurerState());
    }

    @Override
    public void receivePostInitialize() {
        populateCurrentActionsFactory();
        populateActionsFactory();
        populatePowerFactory();
        populateCardFactories();
        populateQuestFactories();

        StateElement.ElementFactories stateFactories = new StateElement.ElementFactories(() -> new AdventurerStateElement(), json -> new AdventurerStateElement(json), jsonObject -> new AdventurerStateElement(jsonObject));
        StateFactories.elementFactories.put(AdventurerStateElement.ELEMENT_KEY, stateFactories);
    }

    private void populateQuestFactories() {
        AbstractQuestState.QuestFactories theLuckyPackFactories = new AbstractQuestState.QuestFactories(quest -> new TheLuckyPackState(quest), jsonObject -> new TheLuckyPackState(jsonObject));

        questByIdMap.put(TheLuckyPackState.QUEST_KEY, theLuckyPackFactories);
        questByTypeMap.put(TheLuckyPack.class, theLuckyPackFactories);

        AbstractQuestState.QuestFactories theFishoPediaFactories = new AbstractQuestState.QuestFactories(quest -> new TheFishOPediaState(quest), jsonObject -> new TheFishOPediaState(jsonObject));

        questByIdMap.put(TheFishOPediaState.QUEST_KEY, theFishoPediaFactories);
        questByTypeMap.put(TheFishOPedia.class, theFishoPediaFactories);

        AbstractQuestState.QuestFactories theStormFactories = new AbstractQuestState.QuestFactories(quest -> new TheStormState(quest), jsonObject -> new TheStormState(jsonObject));

        questByIdMap.put(TheStormState.QUEST_KEY, theStormFactories);
        questByTypeMap.put(TheStorm.class, theStormFactories);

        AbstractQuestState.QuestFactories thePrismaticPortalFactories = new AbstractQuestState.QuestFactories(quest -> new ThePrismaticPortalState(quest), jsonObject -> new ThePrismaticPortalState(jsonObject));

        questByIdMap.put(ThePrismaticPortalState.QUEST_KEY, thePrismaticPortalFactories);
        questByTypeMap.put(ThePrismaticPortal.class, thePrismaticPortalFactories);

        AbstractQuestState.QuestFactories theHarpoonFactories = new AbstractQuestState.QuestFactories(quest -> new TheHarpoonState(quest), jsonObject -> new TheHarpoonState(jsonObject));

        questByIdMap.put(TheHarpoonState.QUEST_KEY, theHarpoonFactories);
        questByTypeMap.put(TheHarpoon.class, theHarpoonFactories);
    }

    private void populateCurrentActionsFactory() {
        StateFactories.currentActionByClassMap
                .put(MopUpAction.class, new CurrentActionState.CurrentActionFactories(action -> new MopUpActionState(action)));
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
                .put(FinishingTouchesPower.ID, new PowerState.PowerFactories(power -> new FinishingTouchesPowerState(power)));
        StateFactories.powerByIdMap
                .put(FullySleevedPower.ID, new PowerState.PowerFactories(power -> new FullySleevedPowerState(power)));
        StateFactories.powerByIdMap
                .put(MintConditionPower.ID, new PowerState.PowerFactories(power -> new MintConditionPowerState(power)));
        StateFactories.powerByIdMap
                .put(RidiculousFishingPower.ID, new PowerState.PowerFactories(power -> new RidiculousFishingPowerState(power)));
        StateFactories.powerByIdMap
                .put(TakeItEasyPower.ID, new PowerState.PowerFactories(power -> new TakeItEasyPowerState(power)));
        StateFactories.powerByIdMap
                .put(TripwirePower.ID, new PowerState.PowerFactories(power -> new TripwirePowerState(power)));
        StateFactories.powerByIdMap
                .put(VexingDealPower.ID, new PowerState.PowerFactories(power -> new VexingDealPowerState(power)));

    }

    private void populateCardFactories() {
        CardState.CardFactories fishCardFactories = new CardState.CardFactories(card -> new AbstractFishingCardState(card), json -> new AbstractFishingCardState(json), jsonObject -> new AbstractFishingCardState(jsonObject));

        StateFactories.cardFactoriesByType.put(AbstractFishingCard.class, fishCardFactories);
        StateFactories.cardFactoriesByTypeName
                .put(AbstractFishingCardState.TYPE_KEY, fishCardFactories);
    }

    @Override
    public void receiveEditCards() {
        // TODO: oh, come on, playing from the discard? what gives?
        BaseMod.removeCard(EndsOfTheEarth.ID, TheFishing.Enums.FISHING_COLOR);

        // TODO: Uses an in-place power.  Will have to be patched if not fixed.
        BaseMod.removeCard(CatchOfTheDay.ID, TheFishing.Enums.FISHING_COLOR);
    }
}