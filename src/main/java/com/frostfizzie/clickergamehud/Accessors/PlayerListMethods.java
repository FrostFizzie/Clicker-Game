package com.frostfizzie.clickergamehud.Accessors;

import net.minecraft.network.chat.Component;

public interface PlayerListMethods {

    default Component clickergamehud$getFooter() {
        return null;
    }
    default Component clickergamehud$getHeader() {
        return null;
    }
}
