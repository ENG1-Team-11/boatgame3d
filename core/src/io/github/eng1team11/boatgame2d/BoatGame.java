package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.ecs.ComponentManager;
import io.github.eng1team11.boatgame2d.ecs.EntityManager;
import io.github.eng1team11.boatgame2d.ecs.SystemManager;
import io.github.eng1team11.boatgame2d.ecs.component.*;

public class BoatGame extends Game {

    public EntityManager _entityManager;
    public ComponentManager _componentManager;
    public SystemManager _systemManager;
    private SpriteBatch _spriteBatch;
    private BitmapFont _font;

    /**
     * Register all required components to the ComponentManager
     */
    void registerECSComponents() {
        _componentManager.registerComponentTypeID(AIComponent.class);
        _componentManager.registerComponentTypeID(ColliderComponent.class);
        _componentManager.registerComponentTypeID(CurrencyComponent.class);
        _componentManager.registerComponentTypeID(ControllerComponent.class);
        _componentManager.registerComponentTypeID(DurabilityComponent.class);
        _componentManager.registerComponentTypeID(PlayerInputComponent.class);
        _componentManager.registerComponentTypeID(SpriteComponent.class);
        _componentManager.registerComponentTypeID(StaminaComponent.class);
        _componentManager.registerComponentTypeID(TypeComponent.class);
        _componentManager.registerComponentTypeID(VelocityComponent.class);
        _componentManager.registerComponentTypeID(PositionComponent.class);
        _componentManager.registerComponentTypeID(UpgradeComponent.class);
        _componentManager.registerComponentTypeID(AccelerationComponent.class);
    }

    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {
        // Create a sprite batch
        _spriteBatch = new SpriteBatch();
        // Load the font (defaults to Arial)
        _font = new BitmapFont();

        _entityManager = new EntityManager();
        _componentManager = new ComponentManager();
        _systemManager = new SystemManager();
        registerECSComponents();

        // When the game instance is created, go to the main menu screen
        this.setScreen(new TestScreen(this));

    }

    // Render is essentially treated as the update loop
    @Override
    public void render() {
        // Call render in the superclass (makes sure the screen gets rendered)
        super.render();
    }

    @Override
    public void dispose() {
        // Call dispose in the superclass
        super.dispose();
    }

    public SpriteBatch GetSpriteBatch() {
        return _spriteBatch;
    }

    public BitmapFont GetFont() {
        return _font;
    }
}
