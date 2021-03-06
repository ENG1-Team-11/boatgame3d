package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

    public OrthographicCamera _gameCamera;
    public OrthographicCamera _guiCamera;

    public Animation<TextureRegion> _animation;

    public Screen _menuScreen;

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

        _gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        _gameCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Set the camera position
        _gameCamera.position.set(_gameCamera.viewportWidth / 2f, _gameCamera.viewportHeight / 2f, 0);

        _guiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        _guiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Set the camera position
        _guiCamera.position.set(_gameCamera.viewportWidth / 2f, _gameCamera.viewportHeight / 2f, 0);

        EntityFactory.get().setComponentManager(_componentManager);
        EntityFactory.get().setEntityManager(_entityManager);

        registerECSComponents();
        createSystems();

        TextureManager.loadTexture("ui/button_play.png", "button_play");
        TextureManager.loadTexture("ui/button_exit.png", "button_exit");
        TextureManager.loadTexture("ui/button_play_hover.png", "button_play_hover");
        TextureManager.loadTexture("ui/button_exit_hover.png", "button_exit_hover");
        TextureManager.loadTexture("ui/progress_outer.png", "progress_outer");
        TextureManager.loadTexture("ui/progress_inner.png", "progress_inner");

        TextureManager.loadTexture("ui/button_acceleration_hover.png", "button_acceleration_hover");
        TextureManager.loadTexture("ui/button_acceleration.png", "button_acceleration");
        TextureManager.loadTexture("ui/button_health_hover.png", "button_health_hover");
        TextureManager.loadTexture("ui/button_health.png", "button_health");
        TextureManager.loadTexture("ui/button_speed_hover.png", "button_speed_hover");
        TextureManager.loadTexture("ui/button_speed.png", "button_speed");
        TextureManager.loadTexture("ui/button_stamina_hover.png", "button_stamina_hover");
        TextureManager.loadTexture("ui/button_stamina.png", "button_stamina");

        TextureManager.loadTexture("background.png", "background");
        TextureManager.loadTexture("menu_background.gif", "menu_background");
        TextureManager.loadTexture("obstacle.png", "obstacle");
        TextureManager.loadTexture("placeholder.png", "placeholder");
        TextureManager.loadTexture("boat_assets/boat_separate.png", "boat");

        _animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("menu_background.gif").read());

        _menuScreen = new MenuScreen(this);

        // When the game instance is created, go to the main menu screen
        this.setScreen(_menuScreen);

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
