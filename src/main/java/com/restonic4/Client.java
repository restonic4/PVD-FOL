package com.restonic4;

import com.restonic4.citadel.core.GameLogic;
import com.restonic4.citadel.core.Window;
import com.restonic4.citadel.world.SceneManager;

public class Client implements GameLogic {
    @Override
    public void start() {
        SceneManager.loadScene(new MainScene());
        //ImGuiScreens.SCENE_CONTROLLER.show();
        Window.getInstance().setCursorLocked(true);
    }

    @Override
    public void update() {

    }
}
