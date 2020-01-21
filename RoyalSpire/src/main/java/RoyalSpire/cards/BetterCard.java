package RoyalSpire.cards;

import RoyalSpire.RoyalSpireMod;
import basemod.abstracts.CustomCard;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class BetterCard extends CustomCard {

    public BetterCard(final String id,
                      final String img,
                      final int cost,
                      final CardType type,
                      final CardColor color,
                      final CardRarity rarity,
                      final CardTarget target) {
        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
    }

    public String makeID() {
        return RoyalSpireMod.makeID(getClass().getSimpleName());
    }
}