package adventurerstate.heuristics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;

import java.util.Comparator;

public class PlayHeuristic implements Comparator<AbstractCard> {
    @Override
    public int compare(AbstractCard o1, AbstractCard o2) {
        if (!(AbstractDungeon.player instanceof TheFishing)) {
            return 0;
        }

        if (o1.type == AbstractCard.CardType.POWER) {
            if (o2.type != AbstractCard.CardType.POWER) {
                return -1;
            }

            // try the more expensive power first
            return o2.costForTurn - o1.costForTurn;
        } else if (o2.type == AbstractCard.CardType.POWER) {
            return 1;
        }

        if (o1.costForTurn == 0) {
            return -1;
        } else if (o2.costForTurn == 0) {
            return 1;
        }

        if (isBasic(o1)) {
            return 1;
        } else if (isBasic(o2)) {
            return -1;
        }

        // Block last
        if (o1.baseBlock > 0) {
            return 1;
        } else if (o2.baseBlock > 0) {
            return -1;
        }


        // try the more expensive power first
        return o2.costForTurn - o1.costForTurn;
    }

    private static boolean isBasic(AbstractCard card) {
        return card.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || card
                .hasTag(AbstractCard.CardTags.STARTER_STRIKE);
    }
}
