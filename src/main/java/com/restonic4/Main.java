package com.restonic4;

import com.restonic4.citadel.core.CitadelLauncher;
import com.restonic4.citadel.core.CitadelSettings;

public class Main {
    public static void main(String[] args) {
        CitadelSettings citadelSettings = new CitadelSettings(new Client(), new Server(), new Shared(), "PVD", args);

        CitadelLauncher.create(citadelSettings).launch();
    }
}