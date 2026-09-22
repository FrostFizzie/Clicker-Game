package com.frostfizzie.clickergamehud.mixins;

import com.frostfizzie.clickergamehud.Accessors.PlayerListMethods;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerTabOverlay.class)
public class MixinGatherHeaders implements PlayerListMethods {

    @Shadow @Nullable private Component header;

    @Shadow @Nullable private Component footer;

    @Override
    public Component clickergamehud$getHeader() {
        return header;
    }
    @Override
    public Component clickergamehud$getFooter() {
        return footer;
    }
}
