package com.restonic4;

import com.restonic4.citadel.core.GameLogic;
import com.restonic4.citadel.registries.RegistryManager;

public class Shared implements GameLogic {
    @Override
    public void start() {
        RegistryManager.registerRegistrySet(new ImGuiScreens());
        RegistryManager.registerCustom();
    }

    @Override
    public void update() {

    }
}
