package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Screen;
import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.ecs.system.*;

import javax.swing.text.Position;

public class TestScreen implements Screen {

    final BoatGame _game;


    public TestScreen(BoatGame game) {
        _game = game;
    }

    /**
     * Create all systems required for this game screen
     */
    void CreateSystems() {
        int aiControl = _game._systemManager.AddSystem(new AIControl());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(AIComponent.class), aiControl);

        int playerControl = _game._systemManager.AddSystem(new PlayerControl());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PlayerInputComponent.class), playerControl);

        int stamina = _game._systemManager.AddSystem(new Stamina());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(StaminaComponent.class), stamina);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), stamina);

        int collision = _game._systemManager.AddSystem(new Collision());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(ColliderComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(DurabilityComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(SpriteComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PositionComponent.class), collision);

        int movement = _game._systemManager.AddSystem(new Movement());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PositionComponent.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PlayerInputComponent.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(AIComponent.class), movement);

        int render = _game._systemManager.AddSystem(new Render(_game.GetSpriteBatch()));
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PositionComponent.class), render);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(SpriteComponent.class), render);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(TypeComponent.class), render);
    }

    /**
     * Create a new player entity
     */
    void CreatePlayer(float x, float y) {
        int player = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(player, (ControllerComponent) new PlayerInputComponent(player));
        _game._componentManager.AddComponent(player, new CurrencyComponent(player));
        _game._componentManager.AddComponent(player, new VelocityComponent(player));
        _game._componentManager.AddComponent(player,
                new PositionComponent(player, x, y)
        );
        _game._componentManager.AddComponent(player,
                new SpriteComponent(player, "badlogic.jpg", 100, 100)
        );
        _game._componentManager.AddComponent(player, new DurabilityComponent(player));
        _game._componentManager.AddComponent(player,
                new ColliderComponent(player, 100, 100)
        );
        _game._componentManager.AddComponent(player, new StaminaComponent(player));
    }

    /**
     * Create a new AI entity
     */
    void CreateAI(float x, float y) {
        int ai = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(ai, (ControllerComponent) new AIComponent(ai));
        _game._componentManager.AddComponent(ai, new VelocityComponent(ai));
        _game._componentManager.AddComponent(ai,
                new PositionComponent(ai, x, 0)
        );
        _game._componentManager.AddComponent(ai,
                new SpriteComponent(ai, "badlogic.jpg", 100, 100)
        );
        _game._componentManager.AddComponent(ai, new DurabilityComponent(ai));
        _game._componentManager.AddComponent(ai,
                new ColliderComponent(ai, 100, 100)
        );
        //_game._componentManager.AddComponent(ai, new StaminaComponent(ai));
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        CreateSystems();
        CreatePlayer(150, 0);
        CreateAI(0, 0);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        _game._systemManager.Update(delta);
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
