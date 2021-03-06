package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.PositionComponent;
import io.github.eng1team11.boatgame2d.ecs.component.SpriteComponent;

import java.util.Map;

public class Render extends System {

    private SpriteBatch _spriteBatch;

    /**
     * Default ctor for the Render system
     *
     * @param spriteBatch The engine's sprite batch
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
    public void input(float delta) {

    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void update(float delta) {
        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(0).entrySet()) {
            int id = kv.getKey();
            SpriteComponent sprite = (SpriteComponent) kv.getValue();
            PositionComponent position = (PositionComponent) _affectedComponents.get(1).get(id);
            sprite.setPosition(position.getX(), position.getY());
        }
    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void render(float delta) {
        // Iterate with a range-based loop
        for (IComponent comp : _affectedComponents.get(0).values()) {
            ((SpriteComponent) comp).draw(_spriteBatch);
        }
    }
}
