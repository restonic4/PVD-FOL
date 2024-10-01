package com.restonic4;

import com.restonic4.citadel.core.LevelEditor;
import com.restonic4.citadel.render.gui.guis.ToggleableImGuiScreen;
import com.restonic4.citadel.util.CitadelConstants;
import com.restonic4.citadel.util.StringBuilderHelper;
import com.restonic4.citadel.world.object.GameObject;
import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;

public class SceneControllerImGui extends ToggleableImGuiScreen {
    @Override
    public void render() {
        if (!isVisible()) {
            return;
        }

        ImGui.begin("Scene controller");

        ImGui.text("Hello!");

        ImGui.end();
    }
}
