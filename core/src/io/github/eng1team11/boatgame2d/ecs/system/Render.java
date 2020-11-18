package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.Position;
import io.github.eng1team11.boatgame2d.ecs.component.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Render extends System {

    OrthographicCamera _camera;
    private SpriteBatch _spriteBatch;

    /**
     * Default ctor for the Render system
     *
     * @param spriteBatch The Engine's sprite batch
     */
    public Render(SpriteBatch spriteBatch) {
        _spriteBatch = spriteBatch;
        _camera = new OrthographicCamera(1280, 720);
        // Set the camera such that the origin is the top-left, and the Y-axis is down
        // (Because that makes sense because this is GL)
        _camera.setToOrtho(true, 1280, 720);
    }

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Input(float delta) {

    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Update(float delta) {
        int componentCount = _affectedComponents.get(0).size();
        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(1).entrySet()) {
            int id = kv.getKey();
            Position position = (Position) _affectedComponents.get(0).get(id);
            Sprite sprite = (Sprite) kv.getValue();
            if (position == null) continue;
            sprite._base.setPosition(position.GetX(), position.GetY());
        }
    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Render(float delta) {
        // Update the camera
        _camera.update();
        // Use the camera's projection matrix to update the batch
        _spriteBatch.setProjectionMatrix(_camera.projection);

        // Set the clear colour then clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);

        // Start the sprite batch so we can draw
        _spriteBatch.begin();
        // Iterate with a range-based loop
        for (IComponent comp : _affectedComponents.get(1).values()) {
            // Cast comp to a sprite
            Sprite sprite = (Sprite) comp;
            // Draw the sprite to the batch
            sprite._base.draw(_spriteBatch);
        }
        // End the sprite batch (stop drawing)
        _spriteBatch.end();
    }
}
