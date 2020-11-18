package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.ecs.system.AIControl;
import io.github.eng1team11.boatgame2d.ecs.system.Movement;
import io.github.eng1team11.boatgame2d.ecs.system.PlayerControl;
import io.github.eng1team11.boatgame2d.ecs.system.Render;

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
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(AI.class), aiControl);

        int playerControl = _game._systemManager.AddSystem(new PlayerControl());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PlayerInput.class), playerControl);

        int movement = _game._systemManager.AddSystem(new Movement());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(Position.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(Velocity.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PlayerInput.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(AI.class), movement);

        int render = _game._systemManager.AddSystem(new Render(_game.GetSpriteBatch()));
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(Position.class), render);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(Sprite.class), render);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(Type.class), render);
    }

    /**
     * Create a new player entity
     */
    void CreatePlayer() {
        int player = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(player, (Controller) new PlayerInput(player));
        _game._componentManager.AddComponent(player, new Currency(player));
        _game._componentManager.AddComponent(player, new Velocity(player));
        _game._componentManager.AddComponent(player, new Position(player));
        _game._componentManager.AddComponent(player, new Sprite(player, "badlogic.jpg"));
        _game._componentManager.AddComponent(player, new Durability(player));
        _game._componentManager.AddComponent(player, new Collider(player));
    }

    /**
     * Create a new AI entity
     */
    void CreateAI() {
        int ai = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(ai, (Controller) new AI(ai));
        _game._componentManager.AddComponent(ai, new Velocity(ai));
        _game._componentManager.AddComponent(ai, new Position(ai));
        _game._componentManager.AddComponent(ai, new Sprite(ai, "badlogic.jpg"));
        _game._componentManager.AddComponent(ai, new Durability(ai));
        _game._componentManager.AddComponent(ai, new Collider(ai));
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        CreateSystems();
        CreatePlayer();
        //CreateAI();
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
