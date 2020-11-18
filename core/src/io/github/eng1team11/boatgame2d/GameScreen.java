package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.eng1team11.boatgame2d.ecs.system.*;

public class GameScreen implements Screen {

    private final BoatGame _game;

    public GameScreen(final BoatGame boatGame) {
        _game = boatGame;
    }

    void CreatePlayer() {
        int boat = _game._entityManager.CreateEntity();
//        boat._playerInput = _game._componentManager.AddComponent(PlayerInput.class);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        // Create all systems required for this game state
        _game._systemManager.AddSystem(new AIControl());
        _game._systemManager.AddSystem(new Collision());
        _game._systemManager.AddSystem(new InLane());
        _game._systemManager.AddSystem(new Movement());
        _game._systemManager.AddSystem(new PlayerControl());
        _game._systemManager.AddSystem(new Render(_game.GetSpriteBatch()));
        _game._systemManager.AddSystem(new Stamina());
        _game._systemManager.AddSystem(new Upgrade());

        // Create the player
        CreatePlayer();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        _game._systemManager.Update(delta);



        // Set the screen clear colour to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        // Clear the colour buffer and the depth buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
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
