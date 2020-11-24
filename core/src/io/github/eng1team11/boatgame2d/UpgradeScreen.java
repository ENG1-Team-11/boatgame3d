package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.eng1team11.boatgame2d.ui.ButtonSprite;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.util.TextureManager;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class UpgradeScreen implements Screen {

    final BoatGame _game;

    Scene _menuScene;
    GameScreen.RaceNumber _nextRace;

    public UpgradeScreen(BoatGame game, GameScreen.RaceNumber previousRace) {
        _game = game;

        switch (previousRace){
            case R1:
                _nextRace = GameScreen.RaceNumber.R2;
                break;
            case R2:
                _nextRace = GameScreen.RaceNumber.R3;
                break;
            case R3:
                _nextRace = GameScreen.RaceNumber.Final;
                break;
        }
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        _menuScene = new Scene();
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(1000, 80),
                        new Vector2(200, 75),
                        TextureManager.getTexture("button_play"),
                        TextureManager.getTexture("button_play_hover"),
                        TextureManager.getTexture("button_play"),
                        ()-> _game.setScreen(new GameScreen(_game, _nextRace))
                ),
                "button_continue"
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
