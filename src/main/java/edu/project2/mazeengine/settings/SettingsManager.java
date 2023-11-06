package edu.project2.mazeengine.settings;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SettingsManager {
    private static SettingsManager instance;
    private static final String SETTINGS_FILE_PATH = "src/main/java/edu/project2/mazeengine/settings/settings.json";
    private JsonObject settings;

    private SettingsManager() {
        loadSettings();
    }

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }

        return instance;
    }

    public char getChar(String key) {
        return getString(key).charAt(0);
    }

    public String getString(String key) {
        if (settings.has(key)) {
            return settings.get(key).getAsString();
        }

        return "";
    }

    private void loadSettings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE_PATH))) {
            Gson gson = new Gson();
            settings = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            settings = new JsonObject();
        }
    }
}
