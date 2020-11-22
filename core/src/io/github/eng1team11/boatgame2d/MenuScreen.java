package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import io.github.eng1team11.boatgame2d.ui.ButtonSprite;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.ui.Text;
import io.github.eng1team11.boatgame2d.util.FontManager;
import io.github.eng1team11.boatgame2d.util.TextureManager;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class MenuScreen implements Screen {

    final BoatGame _game;
    OrthographicCamera _guiCamera;
    Scene _menuScene;

    /**
     * Default ctor for the menu screen
     *
     * @param boatGame The boat game this is attached to
     */
    public MenuScreen(final BoatGame boatGame) {
        _game = boatGame;

        _guiCamera = new OrthographicCamera();
        _guiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

        TextureManager.loadTexture("ui/button_play.png", "button_play");
        TextureManager.loadTexture("ui/button_exit.png", "button_exit");

        _menuScene = new Scene();
        _menuScene.addObject(
                new Text(
                        new Vector2(-220.0f, 200.0f),
                        "Boat Game 2D",
                        FontManager.get().getFont(72)
                ),
                "text_Title"
        );
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(-160.0f, -60.0f),
                        new Vector2(320.0f, 120.0f),
                        TextureManager.getTexture("button_play"),
                        new Runnable() {
                            @Override
                            public void run() {
                                _game.setScreen(new GameScreen(_game));
                            }
                        }
                ),
                "button_play"
        );
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(-160.0f, -220.0f),
                        new Vector2(320.0f, 120.0f),
                        TextureManager.getTexture("button_exit"),
                        new Runnable() {
                            @Override
                            public void run() {
                                _game.dispose();
                            }
                        }
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
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        // Update the camera to account for input
        _guiCamera.update();
        // Set the projection matrix based on the ortho camera
        _game._spriteBatch.setProjectionMatrix(_guiCamera.projection);

        // Update the menu
        _menuScene.update(delta);

        // Start adding things to the game sprite batch
        _game._spriteBatch.begin();
        // Draw the UI scene
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
        // Scale the camera with the window size
        _guiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
