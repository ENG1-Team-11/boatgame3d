package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public class Scene {

    HashMap<String, UIObject> _uiObjects;

    /**
     * Default c'tor for a scene
     */
    public Scene() {
        _uiObjects = new HashMap<>();
    }

    /**
     * Add a UI object to the scene provided an object with the same identifier does not already exist
     *
     * @param object     The object to add to the scene
     * @param identifier The identifier of the UI object
     */
    public void addObject(UIObject object, String identifier) {
        if (_uiObjects.containsKey(identifier)) return;
        _uiObjects.put(identifier, object);
    }

    /**
     * Remove a UI object from the scene
     *
     * @param identifier The identifier of the object to remove
     */
    public void removeObject(String identifier) {
        _uiObjects.remove(identifier);
    }

    /**
     * Update all objects in the current scene
     *
     * @param delta The time since the last frame in seconds
     */
    public void update(float delta) {
        int mouseX = Gdx.graphics.getWidth() / 2 - Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() / 2 - Gdx.input.getY();
//        System.out.printf("X: %d;\tY: %d\n", mouseX, mouseY);
        boolean click = Gdx.input.isButtonJustPressed(0);
        for (Map.Entry<String, UIObject> object : _uiObjects.entrySet()) {
            object.getValue().update(mouseX, mouseY, click);
        }
    }

    /**
     * Draw all objects in the current scene
     *
     * @param batch The sprite batch to draw to
     */
    public void draw(SpriteBatch batch) {
        for (Map.Entry<String, UIObject> object : _uiObjects.entrySet()) {
            object.getValue().draw(batch);
        }
    }

}
