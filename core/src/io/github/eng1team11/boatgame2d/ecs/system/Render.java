package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.PositionComponent;
import io.github.eng1team11.boatgame2d.ecs.component.SpriteComponent;

import java.util.Map;

public class Render extends System {

    OrthographicCamera _camera;
    private SpriteBatch _spriteBatch;

    /**
     * Default ctor for the Render system
     *
     * @param spriteBatch The engine's sprite batch
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
    public void input(float delta) {

    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void update(float delta) {
        int componentCount = _affectedComponents.get(0).size();
        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(1).entrySet()) {
            int id = kv.getKey();
            PositionComponent position = (PositionComponent) _affectedComponents.get(0).get(id);
            SpriteComponent sprite = (SpriteComponent) kv.getValue();
            if (position == null) continue;
            sprite._base.setPosition(position.getX(), position.getY());
        }
    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void render(float delta) {
        // Update the camera
        _camera.update();
        // Use the camera's projection matrix to update the batch
        _spriteBatch.setProjectionMatrix(_camera.projection);

        // Set the clear colour then clear the screen
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Iterate with a range-based loop
        for (IComponent comp : _affectedComponents.get(1).values()) {
            // Cast comp to a sprite
            SpriteComponent sprite = (SpriteComponent) comp;
            // Draw the sprite to the batch
            sprite._base.draw(_spriteBatch);
        }
    }
}
