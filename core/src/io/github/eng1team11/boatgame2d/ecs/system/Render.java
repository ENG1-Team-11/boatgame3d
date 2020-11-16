package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.ecs.component.Sprite;

import java.util.ArrayList;

public class Render implements ISystem {

    private ArrayList<Sprite> _renderables;
    private SpriteBatch _spriteBatch;

    /**
     * Default ctor for the Render system
     * @param spriteBatch The Engine's sprite batch
     */
    public Render(SpriteBatch spriteBatch) {
        _spriteBatch = spriteBatch;
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

    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Render(float delta) {
        // Iterate with a range-based loop
        for (Sprite sprite : _renderables) {
            // Draw the sprite to the batch
           sprite._base.draw(_spriteBatch);
        }
    }
}
