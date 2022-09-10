package adventurerstate;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class AdventurerState implements PostInitializeSubscriber {
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

    private void populateCurrentActionsFactory() {}

    private void populateActionsFactory() {}

    private void populatePowerFactory() {}

    private void populateCardFactories() {}
}