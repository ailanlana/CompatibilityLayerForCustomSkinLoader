package io.github.zekerzhayard.compatibilitylayerforcustomskinloader;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.HTML;

@Mod(
    modid = CompatibilityLayerForCustomSkinLoader.MODID,
    name = "CompatibilityLayerForCustomSkinLoader",
    version = CompatibilityLayerForCustomSkinLoader.VERSION,
    acceptedMinecraftVersions = "(,1.7.10]",
    acceptableRemoteVersions = "*"
)
public class CompatibilityLayerForCustomSkinLoader {
    public final static String MODID = "CompatibilityLayerForCustomSkinLoader";
    public final static String VERSION = Tags.VERSION;

    private final static Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            Class.forName("customskinloader.Logger").getMethod("info", String.class).invoke(Class.forName("customskinloader.CustomSkinLoader").getField("logger").get(null), MODID + " Version: " + VERSION);
        } catch (Throwable t) {
            LOGGER.info("No CustomSkinLoader detected!", t);
        }
    }
}
