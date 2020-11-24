package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.eng1team11.boatgame2d.ui.ButtonSprite;
import io.github.eng1team11.boatgame2d.ui.Image;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.ui.Text;
import io.github.eng1team11.boatgame2d.util.FontManager;
import io.github.eng1team11.boatgame2d.util.TextureManager;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class EndScreen implements Screen {

    final BoatGame _game;
    final float _time;

    String _victoryText;
    Scene _menuScene;

    public EndScreen(BoatGame game, int place, float time) {
        _game = game;
        _time = time;

        switch (place) {
            case 1:
                _victoryText = "Well Done!  You've won the race!";
                break;
            case 2:
                _victoryText = "You've placed second!";
                break;
            case 3:
                _victoryText = "You've placed third!";
                break;
            default:
                _victoryText = "You didn't place, but at least you tried";
        }

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
                        new Vector2(640.0f, 600.0f),
                        _victoryText,
                        FontManager.get().getFont(72)
                ),
                "text_victoryMessage"
        );

        _menuScene.addObject(
                new Text(
                        new Vector2(640.0f, 420.0f),
                        "Your combined time was:",
                        FontManager.get().getFont(48)
                ),
                "text_yourTime"
        );
        _menuScene.addObject(
                new Text(
                        new Vector2(640.0f, 360.0f),
                        Float.toString(_time),
                        FontManager.get().getFont(36)
                ),
                "text_time"
        );
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(480.0f, 40.0f),
                        new Vector2(320.0f, 120.0f),
                        TextureManager.getTexture("button_exit"),
                        TextureManager.getTexture("button_exit_hover"),
                        TextureManager.getTexture("button_exit"),
                        () -> _game.setScreen(new MenuScreen(_game))
                ),
                "button_menu"
        );
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
     * @param width The width of the screen
     * @param height The height of the screen
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
