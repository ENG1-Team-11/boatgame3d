package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

public class Scene {

    HashMap<String, UIObject> _uiObjects;
    HashMap<Integer, ArrayList<UIObject>> _uiObjectsByLayer;
    List<Integer> _sortedKeys;

    /**
     * Default c'tor for a scene
     */
    public Scene() {
        _sortedKeys = new ArrayList<>();
        _uiObjects = new HashMap<>();
        _uiObjectsByLayer = new HashMap<>();
    }

    /**
     * Add a UI object to the scene provided an object with the same identifier does not already exist on layer zero
     *
     * @param object     The object to add to the scene
     * @param identifier The identifier of the UI object
     */
    public void addObject(UIObject object, String identifier) {
        addObject(object, identifier, 0);
    }

    /**
     * Add a UI object to the scene provided an object with the same identifier does not already exist
     *
     * @param object     The object to add to the scene
     * @param identifier The identifier of the UI object
     * @param zIndex The layer to draw the object on
     */
    public void addObject(UIObject object, String identifier, int zIndex) {
        if (_uiObjects.containsKey(identifier)) return;
        _uiObjects.put(identifier, object);
        addObjectToLayer(object, zIndex);
    }

    /**
     * Add an object to the specified layer
     * @param object The object to add
     * @param zIndex The layer to add it to
     */
    void addObjectToLayer(UIObject object, int zIndex) {
        if (!_uiObjectsByLayer.containsKey(zIndex)) {
            _uiObjectsByLayer.put(zIndex, new ArrayList<>());
            Set<Integer> keys = _uiObjectsByLayer.keySet();
            _sortedKeys = new ArrayList<>(keys);
        }
        _uiObjectsByLayer.get(zIndex).add(object);
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
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
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
        for (int i : _sortedKeys) {
            for (UIObject obj : _uiObjectsByLayer.get(i)) {
                obj.draw(batch);
            }
        }
    }

    public UIObject getObject(String identifier) {
        return _uiObjects.get(identifier);
    }

}
