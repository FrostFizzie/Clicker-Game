package com.frostfizzie.clickergamehud.config;

import com.frostfizzie.clickergamehud._1ClickerGameHUD;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class Config {
    public static YamlDocument instance;
    public static float scale;
    public static void init() {
        try {
            instance = YamlDocument.create(new File(FabricLoader.getInstance().getConfigDir().resolve("clickergamehud").toFile(), "/config.yml"),
                    Objects.requireNonNull(_1ClickerGameHUD.class.getResourceAsStream("/assets/clickergamehud/config.yml")),
                    GeneralSettings.DEFAULT,
                    LoaderSettings.builder().setAutoUpdate(true).setAllowDuplicateKeys(false).setCreateFileIfAbsent(true).build(),
                    DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).setOptionSorting(UpdaterSettings.OptionSorting.SORT_BY_DEFAULTS)
                            .build());
            instance.save();
            updateValues();
        } catch (IOException ignored) {
        }
    }

    public static void updateValues() throws IOException {
        instance.update();
        scale = Math.clamp(instance.getOptionalFloat("display-scale").orElse(1f), 0.5f, 1.25f);
    }
}
