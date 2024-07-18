package io.github.zekerzhayard.compatibilitylayerforcustomskinloader.mixin.plugin;

import cpw.mods.fml.relauncher.FMLLaunchHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public enum Mixin {

    //
    // IMPORTANT: Do not make any references to any mod from this file. This file is loaded quite early on and if
    // you refer to other mods you load them as well. The consequence is: You can't inject any previously loaded classes!
    // Exception: Tags.java, as long as it is used for Strings only!
    //

    // Replace with your own mixins:
    FileManager("mmmlibx.lib.MixinFileManager",Side.CLIENT, TargetedMod.MMMLib),

    ModelBendsPlayer("net.gobbob.mobends.client.model.entity.MixinModelBendsPlayer",Side.CLIENT, TargetedMod.MOBENDS),
    RenderBendsPlayer("net.gobbob.mobends.client.render.entity.MixinRenderBendsPlayer",Side.CLIENT, TargetedMod.MOBENDS),

    AbstractClientPlayer("net.minecraft.client.entity.MixinAbstractClientPlayer",Side.CLIENT, TargetedMod.VANILLA),
    RenderManager("net.minecraft.client.renderer.entity.MixinRenderManager",Side.CLIENT, TargetedMod.VANILLA),
    RenderPlayer("net.minecraft.client.renderer.entity.MixinRenderPlayer",Side.CLIENT, TargetedMod.VANILLA),
    SkinManager("net.minecraft.client.resources.MixinSkinManager$SkinAvailableCallback",Side.CLIENT, TargetedMod.VANILLA),

    SkinTexture("riskyken.armourersWorkshop.common.skin.data.MixinSkinTexture",Side.CLIENT, TargetedMod.AW),
    RenderTFGiant("twilightforest.client.renderer.entity.MixinRenderTFGiant",Side.CLIENT, TargetedMod.TF),

    ;



    public final String mixinClass;
    public final HashSet<TargetedMod> targetedMods;
    private final Side side;

    Mixin(String mixinClass, Side side, TargetedMod... targetedMods) {
        this.mixinClass = mixinClass;
        this.targetedMods = new HashSet<>(Arrays.asList(targetedMods));
        this.side = side;
    }


    public boolean shouldLoad(List<TargetedMod> loadedMods) {

        return (side == Side.BOTH
            || side == Side.SERVER && FMLLaunchHandler.side().isServer()
            || side == Side.CLIENT && FMLLaunchHandler.side().isClient())
            && new HashSet<>(loadedMods).containsAll(targetedMods);
    }
}

enum Side {
    BOTH,
    CLIENT,
    SERVER
}
