package com.frostfizzie.clickergamehud.Features;


import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.PacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.frostfizzie.clickergamehud._1ClickerGameHUD.MOD_ID;

public abstract class Feature {

    public boolean isEnabled() {return true;}
    public void activate(){};
    public void tick(Minecraft client){}
    public Identifier getHudIdentifier() {return Identifier.fromNamespaceAndPath(MOD_ID, "clickerhud");}
    public void hudRender(GuiGraphicsExtractor draw, DeltaTracker counter){}
    public void onPacket(Packet<?> packet, PacketListener listener, CallbackInfo ci){}
    public void sendPacket(Packet<?> packet, CallbackInfo ci) {}
    public Component receiveChatMessage(Component message, CallbackInfo ci) {return message;}
    public  void clientStop(Minecraft client){}
}
