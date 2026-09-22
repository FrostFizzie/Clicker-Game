package com.frostfizzie.clickergamehud.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.io.IOException;

import static com.frostfizzie.clickergamehud._1ClickerGameHUD.MOD_ID;
import static com.frostfizzie.clickergamehud.config.Config.*;

public class YACL {
    public static Screen createConfigMenu(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable(MOD_ID + ".screen.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable(MOD_ID + ".screen.config.main.title"))
                        .option(Option.<Float>createBuilder()
                                .name(Component.translatable(MOD_ID + ".screen.config.main.scale"))
                                .binding(1f, () -> scale, (newVal) -> scale = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .range(0.5f, 1.25f)
                                        .step(0.01f)
                                        .formatValue(val -> Component.literal(val + "x"))
                                )
                                .build())
                        .build())
                .save(() -> {
                    instance.set("display-scale", scale);
                    try {
                        instance.save();
                        updateValues();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                })
                .build()
                .generateScreen(parent);
    }
}
