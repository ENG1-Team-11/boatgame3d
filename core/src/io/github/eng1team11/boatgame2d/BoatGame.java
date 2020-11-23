package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.ecs.ComponentManager;
import io.github.eng1team11.boatgame2d.ecs.EntityFactory;
import io.github.eng1team11.boatgame2d.ecs.EntityManager;
import io.github.eng1team11.boatgame2d.ecs.SystemManager;
import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.ecs.system.*;
import io.github.eng1team11.boatgame2d.util.TextureManager;

public class BoatGame extends Game {

    // ECS
    public EntityManager _entityManager;
    public ComponentManager _componentManager;
    public SystemManager _systemManager;

    public SpriteBatch _spriteBatch;

    public OrthographicCamera _camera;

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
     * Create all systems required
     */
    void createSystems() {
        int aiControl = _systemManager.addSystem(new AIControl());
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(AIComponent.class), aiControl);

        int playerControl = _systemManager.addSystem(new PlayerControl());
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(PlayerInputComponent.class), playerControl);

        int stamina = _systemManager.addSystem(new Stamina());
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(StaminaComponent.class), stamina);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(VelocityComponent.class), stamina);

        int upgrade = _systemManager.addSystem(new Upgrade());
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(UpgradeComponent.class), upgrade);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(StaminaComponent.class), upgrade);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(DurabilityComponent.class), upgrade);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(VelocityComponent.class), upgrade);

        int collision = _systemManager.addSystem(new Collision());
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(ColliderComponent.class), collision);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(DurabilityComponent.class), collision);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(VelocityComponent.class), collision);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(SpriteComponent.class), collision);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(PositionComponent.class), collision);

        int durability = _systemManager.addSystem(new Durability(_entityManager));
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(DurabilityComponent.class), durability);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(VelocityComponent.class), durability);

        int movement = _systemManager.addSystem(new Movement());
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(PositionComponent.class), movement);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(VelocityComponent.class), movement);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(AccelerationComponent.class), movement);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(PlayerInputComponent.class), movement);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(AIComponent.class), movement);

        int render = _systemManager.addSystem(new Render(_spriteBatch));
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(SpriteComponent.class), render);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(PositionComponent.class), render);
        _systemManager.registerComponentsToSystem(_componentManager.getComponentsOfType(TypeComponent.class), render);
    }

    /**
     * Called when the {@link Application} is first created.
     */
    @Override
    public void create() {
        // Create a sprite batch
        _spriteBatch = new SpriteBatch();

        _componentManager = new ComponentManager();
        _entityManager = new EntityManager(_componentManager);
        _systemManager = new SystemManager();

        _camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        _camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Set the camera position
        _camera.position.set(_camera.viewportWidth / 2f, _camera.viewportHeight / 2f, 0);

        EntityFactory.get().setComponentManager(_componentManager);
        EntityFactory.get().setEntityManager(_entityManager);

        registerECSComponents();
        createSystems();

        TextureManager.loadTexture("ui/button_play.png", "button_play");
        TextureManager.loadTexture("ui/button_exit.png", "button_exit");
        TextureManager.loadTexture("badlogic.jpg", "badlogic");
        TextureManager.loadTexture("placeholder.png", "placeholder");
        TextureManager.loadTexture("boat_assets/boat_separate.png", "boat");

        // When the game instance is created, go to the main menu screen
        this.setScreen(new MenuScreen(this));

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

}
