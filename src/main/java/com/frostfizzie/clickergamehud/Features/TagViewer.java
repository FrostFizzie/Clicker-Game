package com.frostfizzie.clickergamehud.Features;

import com.google.common.collect.Iterables;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix3dStack;
import org.joml.Matrix3x2fStack;

import java.awt.*;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import static com.frostfizzie.clickergamehud.client._1ClickerGameHUDClient.component;
import static com.frostfizzie.clickergamehud.client._1ClickerGameHUDClient.miniMessage;
import static com.frostfizzie.clickergamehud.config.Config.scale;

public class TagViewer extends Feature {
    private static Component prefix;
    private static String IP;
    private static final Minecraft client = Minecraft.getInstance();
    @Override
    public void tick(Minecraft client) {
        if (client.level == null || client.player == null || !client.isMultiplayerServer()) {
            prefix = null;
            return;
        }
            InetSocketAddress address = (InetSocketAddress) Objects.requireNonNull(client.getConnection()).getConnection().getRemoteAddress();
            IP = address.getAddress().getHostAddress() + ":" + address.getPort();
        if (IP == null) return;
        if (!Objects.equals(IP, "148.113.223.138:25565")) return;
        assert Minecraft.getInstance().level != null;
        Iterable<Display.BlockDisplay> entities = Minecraft.getInstance().level.getEntitiesOfClass(Display.BlockDisplay.class, AABB.of(BoundingBox.fromCorners(new Vec3i(108007, 51, 2007), new Vec3i(108008, 51, 2008))));
        for (Entity entity : entities) {
                 if (client.gui.hud.getTabList().clickergamehud$getFooter() != null) {
                     String rawFooter = MiniMessage.miniMessage().serialize(component(client.gui.hud.getTabList().clickergamehud$getFooter()));
                        prefix = rawFooter.length() > 1 ? miniMessage(rawFooter.split("<br>")[1]) : null;
                        break;
                            }
                else {
                        prefix = null;
                        }
                    }
    }
    @Override
    public void hudRender(GuiGraphicsExtractor draw, DeltaTracker counter) {
        if (!Minecraft.getInstance().isMultiplayerServer() || Minecraft.getInstance().getConnection() == null || Minecraft.getInstance().getConnection().getServerData() == null || IP == null) return;
        if (!IP.equalsIgnoreCase("148.113.223.138:25565")) return;
        if (prefix == null) return;
        int i = 0;

        Collection<MobEffectInstance> effects = Objects.requireNonNull(client.player)
                .getActiveEffects()
                .stream()
                .filter(MobEffectInstance::showIcon)
                .toList();
        int potionOffset = effects.size() * 25;

        drawRightAlignedTextWithShadow(
                draw,
                getTextRenderer(),
                Component.literal("\247a\247l+1 Clicker"),
                5 + potionOffset,
                i++,
                scale
        );
        drawRightAlignedTextWithShadow(
                draw,
                getTextRenderer(),
                prefix,
                5 + potionOffset,
                i++,
                scale
        );

        String time = new SimpleDateFormat("hh:mm aa").format(new Date());

        drawRightAlignedTextWithShadow(
                draw,
                getTextRenderer(),
                miniMessage("<gray>" + time),
                5 + potionOffset,
                i,
                scale
        );
    }
    public static Font getTextRenderer() {
        return client.font;
    }
    public static void drawRightAlignedTextWithShadow(
            GuiGraphicsExtractor context,
            Font font,
            Component text,
            int rightOffset,
            int row,
            float scale
    ) {
        float rightX = context.guiWidth() - rightOffset;
        float y = 3 + row * font.lineHeight * scale;

        context.pose().pushMatrix();

        // Anchor the transform at the desired right edge.
        context.pose().translate(rightX, y);
        context.pose().scale(scale, scale);

        // Draw leftward from the anchor point.
        context.text(
                font,
                text,
                -font.width(text),
                0,
                0xFFFFFF00,
                true
        );

        context.pose().popMatrix();
    }
}
