package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Screen;
import io.github.eng1team11.boatgame2d.ecs.component.*;

public class TestScreen implements Screen {

    final BoatGame _game;


    public TestScreen(BoatGame game) {
        _game = game;
    }

    /**
     * Create a new player entity
     */
    void CreatePlayer() {
        int player = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(new PlayerInput(player));
        _game._componentManager.AddComponent(new Currency(player));
        _game._componentManager.AddComponent(new Velocity(player));
        _game._componentManager.AddComponent(new Position(player));
        _game._componentManager.AddComponent(new Sprite(player, "badlogic.jpg"));
        _game._componentManager.AddComponent(new Durability(player));
        _game._componentManager.AddComponent(new Collider(player));
    }

    /**
     * Create a new AI entity
     */
    void CreateAI() {
        int ai = _game._entityManager.CreateEntity();
        _game._componentManager.AddComponent(new AI(ai));
        _game._componentManager.AddComponent(new Velocity(ai));
        _game._componentManager.AddComponent(new Position(ai));
        _game._componentManager.AddComponent(new Sprite(ai, "badlogic.jpg"));
        _game._componentManager.AddComponent(new Durability(ai));
        _game._componentManager.AddComponent(new Collider(ai));
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
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
