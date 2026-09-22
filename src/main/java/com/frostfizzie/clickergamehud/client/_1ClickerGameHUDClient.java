package com.frostfizzie.clickergamehud.client;

import com.frostfizzie.clickergamehud.Features.EventRegistry;
import com.frostfizzie.clickergamehud.Features.Features;
import com.frostfizzie.clickergamehud.Features.TagViewer;
import com.frostfizzie.clickergamehud.config.Config;
import net.fabricmc.api.ClientModInitializer;

import net.kyori.adventure.platform.modcommon.MinecraftClientAudiences;

import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.Component;


public class _1ClickerGameHUDClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */

    @Override
    public void onInitializeClient() {
        EventRegistry.init();
        Features.init();
        Features.hudRender();
        Config.init();
    }
    public static net.minecraft.network.chat.Component miniMessage(String message) {
        return MinecraftClientAudiences.of().asNative(MiniMessage.miniMessage().deserialize(message).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE));

    }
    public static Component component(net.minecraft.network.chat.Component message) {
        return MinecraftClientAudiences.of().asAdventure(message);
    }
}
