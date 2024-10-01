package com.restonic4;

import com.restonic4.citadel.registries.AbstractRegistryInitializer;
import com.restonic4.citadel.registries.AssetLocation;
import com.restonic4.citadel.registries.Registries;
import com.restonic4.citadel.registries.Registry;
import com.restonic4.citadel.render.gui.guis.ToggleableImGuiScreen;

public class ImGuiScreens extends AbstractRegistryInitializer {
    public static ToggleableImGuiScreen SCENE_CONTROLLER;

    @Override
    public void register() {
        SCENE_CONTROLLER = (ToggleableImGuiScreen) Registry.register(Registries.IM_GUI_SCREEN, new AssetLocation("pvd", "scene_controller"), new SceneControllerImGui());
    }
}
