package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.eng1team11.boatgame2d.ui.ButtonSprite;
import io.github.eng1team11.boatgame2d.ui.Image;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.ui.Text;
import io.github.eng1team11.boatgame2d.util.FontManager;
import io.github.eng1team11.boatgame2d.util.TextureManager;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class MenuScreen implements Screen {

    final BoatGame _game;
    Scene _menuScene;

    /**
     * Default ctor for the menu screen
     *
     * @param boatGame The boat game this is attached to
     */
    public MenuScreen(final BoatGame boatGame) {
        _game = boatGame;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

        _menuScene = new Scene();
        _menuScene.addObject(
                new Image(
                        new Vector2(-400.0f, 0.0f),
                        new Vector2(7223.0f, 1088.0f),
                        TextureManager.getTexture("background")
                ),
                "background",
                -1
        );
        _menuScene.addObject(
                new Text(
                        new Vector2(420.0f, 600.0f),
                        "Boat Game 2D",
                        FontManager.get().getFont(72)
                ),
                "text_Title"
        );
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(480.0f, 340.0f),
                        new Vector2(320.0f, 120.0f),
                        TextureManager.getTexture("button_play"),
                        TextureManager.getTexture("button_play_hover"),
                        TextureManager.getTexture("button_play"),
                        () -> _game.setScreen(new GameScreen(_game))
                ),
                "button_play"
        );
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(480.0f, 180.0f),
                        new Vector2(320.0f, 120.0f),
                        TextureManager.getTexture("button_exit"),
                        TextureManager.getTexture("button_exit_hover"),
                        TextureManager.getTexture("button_exit"),
                        () -> Gdx.app.exit()
                ),
                "button_exit"
        );
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

        // Update the menu
        _menuScene.update(delta);

        // Update the camera
        _game._gameCamera.update();

        // Start adding things to the game sprite batch
        _game._spriteBatch.begin();
        // Draw the UI scene
        _game._spriteBatch.setProjectionMatrix(_game._guiCamera.combined);
        _menuScene.draw(_game._spriteBatch);
        // Finish adding things to the sprite batch
        _game._spriteBatch.end();
    }

    /**
     * @param width  The width of the screen
     * @param height The height of the screen
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        // Scale the camera with the window size - Don't do this, it messes up the UI
//        _game._camera.viewportWidth = width;
//        _game._camera.viewportHeight = height;
        // Set the camera position
        _game._gameCamera.position.set(_game._gameCamera.viewportWidth / 2f, _game._gameCamera.viewportHeight / 2f, 0);
        // Update the camera
        _game._gameCamera.update();
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
        _game._componentManager.clear();
        _game._entityManager.clear();
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
