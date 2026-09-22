package com.frostfizzie.clickergamehud.Features;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketListener;
import net.minecraft.network.chat.Component;

import net.minecraft.network.protocol.Packet;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.stream.Stream;


public class Features {
    public static final HashMap<Class<?>, Feature> features = new HashMap<>();

    public static void init() {
        addFeature(new TagViewer());
        features.values().forEach(Feature::activate);
    }
    public static void addFeature(Feature feature) {
        features.put(feature.getClass(), feature);
    }
    public static void getFeature(Class<?> clazz) {
        Feature feature = features.get(clazz);
    }
    public static Stream<Feature> features() {
        return features.values().stream().filter(Feature::isEnabled);
    }
    public static void tick(Minecraft client) {
        features().forEach(feature -> feature.tick(client));
    }
    public static void hudRender() {
        features().forEach((feature) -> {
            HudElementRegistry.addLast(feature.getHudIdentifier(),feature::hudRender);
        });
    }
    public static void onPacket(Packet<?> packet, PacketListener listener, CallbackInfo ci) {
        features().forEach(feature -> feature.onPacket(packet, listener, ci));
    }
    public static void sendPacket(Packet<?> packet, CallbackInfo ci) {
        features().forEach(feature -> feature.sendPacket(packet, ci));
    }
    public static void clientStop(Minecraft client) {
        features().forEach(feature -> feature.clientStop(client));
    }
    public static Component receiveChatMessage(Component message, CallbackInfo ci) {
        for (Feature feature : features().toList()) {
            message = feature.receiveChatMessage(message, ci);
        }
        return message;
    }
}
