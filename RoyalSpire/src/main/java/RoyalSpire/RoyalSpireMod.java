package RoyalSpire;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import RoyalSpire.cards.*;
import RoyalSpire.util.IDCheckDontTouchPls;
import RoyalSpire.util.TextureLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


@SpireInitializer
public class RoyalSpireMod implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber, PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(RoyalSpireMod.class.getName());
    private static String modID;

    public static Properties defaultSettings = new Properties();

    private static final String MOD_NAME = "Royal Spire";
    private static final String AUTHOR = "Steffo";
    private static final String DESCRIPTION = "A Royal Games mod for Slay the Spire!";

    public static final String BADGE_IMAGE = "RoyalSpireResources/images/Badge.png";

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    
    public RoyalSpireMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);

        setModID("RoyalSpire");

        logger.info("Done subscribing");
        
        logger.info("Adding mod settings");

        try {
            SpireConfig config = new SpireConfig("RoyalSpire", "RoyalSpireConfig", defaultSettings);
            config.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Done adding mod settings");
        
    }

    public static void setModID(String ID) {
        Gson coolG = new Gson();
        InputStream in = RoyalSpireMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    
    public static String getModID() {
        return modID;
    }
    
    private static void pathCheck() {
        Gson coolG = new Gson();
        InputStream in = RoyalSpireMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = RoyalSpireMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Royal Spire =============================");
        RoyalSpireMod defaultmod = new RoyalSpireMod();
        logger.info("========================= Royal Spire Initialized ==============================");
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. ");
        
        receiveEditPotions();
    }
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");

        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        ModPanel settingsPanel = new ModPanel();

        BaseMod.registerModBadge(badgeTexture, MOD_NAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");
    }
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");

        logger.info("Done editing potions");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
        pathCheck();

        logger.info("Add variables");

        logger.info("Adding cards");

        BaseMod.addCard(new SenseisArt());
        
        logger.info("Making sure the cards are unlocked.");

        UnlockTracker.unlockCard(SenseisArt.ID);
        
        logger.info("Done adding cards!");
    }
    
    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + getModID());

        BaseMod.loadCustomStringsFile(CardStrings.class,getModID() + "Resources/localization/eng/RoyalSpireMod-Card-Strings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class,getModID() + "Resources/localization/eng/RoyalSpireMod-Power-Strings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class,getModID() + "Resources/localization/eng/RoyalSpireMod-Relic-Strings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class,getModID() + "Resources/localization/eng/RoyalSpireMod-Event-Strings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class,getModID() + "Resources/localization/eng/RoyalSpireMod-Potion-Strings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class,getModID() + "Resources/localization/eng/RoyalSpireMod-Character-Strings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class,getModID() + "Resources/localization/eng/RoyalSpireMod-Orb-Strings.json");
        
        logger.info("Done edittting strings");
    }
    
    @Override
    public void receiveEditKeywords() {

    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
