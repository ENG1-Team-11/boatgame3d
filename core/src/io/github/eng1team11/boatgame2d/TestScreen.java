package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Screen;
import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.ecs.system.AIControl;
import io.github.eng1team11.boatgame2d.ecs.system.Movement;
import io.github.eng1team11.boatgame2d.ecs.system.PlayerControl;
import io.github.eng1team11.boatgame2d.ecs.system.Render;
import io.github.eng1team11.boatgame2d.ecs.system.Stamina;

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
    void CreatePlayer() {
        int player = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(player, (ControllerComponent) new PlayerInputComponent(player));
        _game._componentManager.AddComponent(player, new CurrencyComponent(player));
        _game._componentManager.AddComponent(player, new VelocityComponent(player));
        _game._componentManager.AddComponent(player, new PositionComponent(player));
        _game._componentManager.AddComponent(player, new SpriteComponent(player, "badlogic.jpg"));
        _game._componentManager.AddComponent(player, new DurabilityComponent(player));
        _game._componentManager.AddComponent(player, new ColliderComponent(player));
        _game._componentManager.AddComponent(player, new StaminaComponent(player));
    }

    /**
     * Create a new AI entity
     */
    void CreateAI() {
        int ai = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(ai, (ControllerComponent) new AIComponent(ai));
        _game._componentManager.AddComponent(ai, new VelocityComponent(ai));
        _game._componentManager.AddComponent(ai, new PositionComponent(ai));
        _game._componentManager.AddComponent(ai, new SpriteComponent(ai, "badlogic.jpg"));
        _game._componentManager.AddComponent(ai, new DurabilityComponent(ai));
        _game._componentManager.AddComponent(ai, new ColliderComponent(ai));
        //_game._componentManager.AddComponent(ai, new StaminaComponent(ai));
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        CreateSystems();
        CreatePlayer();
        CreateAI();
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
