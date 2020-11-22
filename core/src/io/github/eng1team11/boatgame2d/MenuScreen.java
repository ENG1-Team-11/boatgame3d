package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MenuScreen implements Screen {

    private final BoatGame _game;
    private OrthographicCamera _guiCamera;

    /**
     * Default ctor for the menu screen
     *
     * @param boatGame The boat game this is attached to
     */
    public MenuScreen(final BoatGame boatGame) {
        _game = boatGame;

        _guiCamera = new OrthographicCamera();
        _guiCamera.setToOrtho(false, 1280, 720);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Set the screen clear colour to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        // Clear the colour buffer and the depth buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Update the camera to account for input
        _guiCamera.update();
        // Set the projection matrix based on the ortho camera
        _game.GetSpriteBatch().setProjectionMatrix(_guiCamera.projection);

        // Start adding things to the game sprite batch
        _game.GetSpriteBatch().begin();
        // Draw the text, "BoatGame3D" (note that 0,0 is the centre of the screen for some reason..?)
        _game.GetFont().draw(_game.GetSpriteBatch(), "BoatGame3D", 0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            _game.setScreen(new GameScreen(_game));
        }

        // Finish adding things to the sprite batch
        _game.GetSpriteBatch().end();
    }

    /**
     * @param width The width of the screen
     * @param height The height of the screen
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        // Scale the camera with the window size
        _guiCamera.setToOrtho(false, width, height);
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
