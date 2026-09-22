package com.frostfizzie.clickergamehud.Features;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.impl.client.rendering.hud.HudElementRegistryImpl;

public class EventRegistry {
    public static void init() {
        ClientTickEvents.START_CLIENT_TICK.register(Features::tick);
        ClientLifecycleEvents.CLIENT_STOPPING.register(Features::clientStop);
    }
}
