package RoyalSpire.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import RoyalSpire.RoyalSpireMod;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static RoyalSpire.RoyalSpireMod.makeCardPath;

public class SenseisArt extends AbstractDynamicCard {

    public static final String ID = RoyalSpireMod.makeID(SenseisArt.class.getSimpleName());
    public static final String IMG = makeCardPath("SenseisArt.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 2;

    private static final int STARTING_ARTIFACT = 1;
    private static final int UPGRADED_ARTIFACT = 1;


    public SenseisArt() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = STARTING_ARTIFACT;
        this.magicNumber = STARTING_ARTIFACT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber), this.magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.initializeDescription();
            this.upgradeMagicNumber(UPGRADED_ARTIFACT);
        }
    }
}
