package com.frostfizzie.clickergamehud.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.fabricmc.loader.api.FabricLoader;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3") ? YACL::createConfigMenu : ModMenuApi.super.getModConfigScreenFactory();
    }
}
