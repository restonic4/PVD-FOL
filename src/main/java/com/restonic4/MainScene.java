package com.restonic4;

import com.restonic4.citadel.core.CitadelLauncher;
import com.restonic4.citadel.core.Window;
import com.restonic4.citadel.files.parsers.mesh.MeshLoader;
import com.restonic4.citadel.input.KeyListener;
import com.restonic4.citadel.input.MouseListener;
import com.restonic4.citadel.render.Texture;
import com.restonic4.citadel.render.cameras.PerspectiveCamera;
import com.restonic4.citadel.util.Time;
import com.restonic4.citadel.world.Scene;
import com.restonic4.citadel.world.object.GameObject;
import com.restonic4.citadel.world.object.Mesh;
import com.restonic4.citadel.world.object.Transform;
import com.restonic4.citadel.world.object.components.LightComponent;
import com.restonic4.citadel.world.object.components.ModelRendererComponent;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class MainScene extends Scene {
    public float velocity = 0.75f;
    public Vector3f exaggeration = new Vector3f(0.25f, 0.6f, 0.25f);

    int slideID = 0;

    private List<GameObject> screens = new ArrayList<>();
    private GameObject light;

    @Override
    public void init() {
        Transform camTransform = new Transform();
        camTransform.setPosition(0, 0, 0);

        camera = new PerspectiveCamera(camTransform);
        camera.load();

        light = new GameObject(false);
        LightComponent lightComponent = new LightComponent(LightComponent.LightType.POINT);
        lightComponent.getLightType().adjustAttenuationByRange(20);
        light.addComponent(lightComponent);
        light.transform.setPosition(0, 0, 0);
        this.addGameObject(light);

        createScreen(0, 0, -2.5f, "computer_res1");
        createScreen(1000, 0, -2.5f, "computer_res2");
        createScreen(2000, 0, -20, "computer_res1");
        createScreen(3000, 0, -10, "computer_res1");
        createScreen(4000, 0, -2.5f, "computer_res1");
        createScreen(5000, 0, -2.5f, "computer_res1");
        createScreen(6000, 0, -2.5f, "computer_res1");
        createScreen(7000, 0, -2.5f, "computer_res1");
        createScreen(8000, 0, -2.5f, "computer_res1");
        createScreen(9000, 0, -2.5f, "computer_res1");
        createScreen(10000, 0, -2.5f, "computer_res1");

        for (int y = -3; y < 3; y++) {
            for (int x = -3; x < 3; x++) {
                createScreen(11000 + x * 4, y * 3, -5f, "computer_res1");
            }
        }

        super.init();
    }

    private void createScreen(float x, float y, float z, String texture) {
        Mesh mesh = MeshLoader.loadMesh("assets/models/computer.obj");
        mesh.setTexture(new Texture("assets/textures/" + texture + ".png"));

        GameObject pc = new GameObject(false);
        pc.addComponent(new ModelRendererComponent(mesh));
        pc.transform.setPosition(x, y, z);
        screens.add(pc);
        this.addGameObject(pc);
    }

    @Override
    public void update() {
        camera.transform.setPosition(1000 * slideID, 0, 0);
        light.transform.setPosition((1000 * slideID + 5), 0, 2);

        if (slideID == 5) {
            light.getComponent(LightComponent.class).getLightType().adjustAttenuationByRange(1);
            animateScreens();
        }
        else if (slideID == 7) {
            CitadelLauncher.getInstance().getSettings().setVsync(false);
            CitadelLauncher.getInstance().getSettings().setFPSCap(2);

            animateScreens();
        }
        else if (slideID == 9) {
            for (int i = 0; i < screens.size(); i++) {
                GameObject screen = screens.get(i);
                screen.transform.setRotation(new Quaternionf().rotateY((float) Math.toRadians(-80)).rotateX((float) Math.toRadians(-10)));
            }
        }
        else if (slideID == 10) {
            for (int i = 0; i < screens.size(); i++) {
                GameObject screen = screens.get(i);
                screen.transform.setRotation(new Quaternionf().rotateY((float) Math.toRadians(-90)));
            }
        }
        else {
            CitadelLauncher.getInstance().getSettings().setVsync(true);
            CitadelLauncher.getInstance().getSettings().setFPSCap(144);
            light.getComponent(LightComponent.class).getLightType().adjustAttenuationByRange(20);

            animateScreens();
        }

        if (KeyListener.isKeyPressedOnce(GLFW.GLFW_KEY_RIGHT)) {
            slideID++;
        }
        if (KeyListener.isKeyPressedOnce(GLFW.GLFW_KEY_LEFT)) {
            slideID--;
        }

        /*if (Window.getInstance().isCursorLocked()) {
            float sensitivity = 0.005f;

            float xMouseDelta = MouseListener.getDy() * sensitivity;
            float yMouseDelta = MouseListener.getDx() * sensitivity;

            Quaternionf pitchRotation = new Quaternionf().rotateX(xMouseDelta);
            Quaternionf yawRotation = new Quaternionf().rotateY(yMouseDelta);

            camera.transform.addRotationQuaternion(yawRotation);
            camera.transform.addRotationQuaternion(pitchRotation);
        }*/

        super.update();
    }

    private void animateScreens() {
        float x = (float) Math.sin(Time.getRunningTime() * velocity) * exaggeration.x;
        float y = (float) Math.cos(Time.getRunningTime() * velocity) * exaggeration.y;
        float z = x * exaggeration.z;

        Vector3f rotationVector = new Vector3f(x, y, z);
        float angle = rotationVector.length();

        for (int i = 0; i < screens.size(); i++) {
            GameObject screen = screens.get(i);

            Quaternionf offsetRotation = new Quaternionf().rotateY((float) Math.toRadians(-90));

            screen.transform.setRotation(offsetRotation.mul(new Quaternionf().rotateAxis(angle, rotationVector.normalize())));
        }
    }
}
