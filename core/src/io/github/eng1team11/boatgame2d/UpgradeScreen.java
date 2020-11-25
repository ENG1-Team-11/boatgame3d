package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.eng1team11.boatgame2d.ui.ButtonSprite;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.ui.Text;
import io.github.eng1team11.boatgame2d.util.FontManager;
import io.github.eng1team11.boatgame2d.util.TextureManager;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class UpgradeScreen implements Screen {

    final BoatGame _game;

    Scene _menuScene;
    GameScreen.RaceNumber _nextRace;

    /**
     * Default c'tor for an upgrade screen
     *
     * @param game         A reference to the game
     * @param previousRace Which race just took place (leg 1, 2, 3, final)
     */
    public UpgradeScreen(BoatGame game, GameScreen.RaceNumber previousRace) {
        _game = game;

        switch (previousRace) {
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
                        ()-> _game.setScreen(new GameScreen(_game, _nextRace))),
                "button_continue");
        _menuScene.addObject(
                new Text(
                        new Vector2(640.0f, 660.0f), 
                        "Upgrades", 
                        FontManager.get().getFont(72)),
                        "text_upgrades");
        _menuScene.addObject(
                new Text(
                        new Vector2(1000.0f, 670.0f), 
                        "Upgrades Points: 0", 
                        FontManager.get().getFont(26)),
                        "text_upgrades");
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(540, 500),
                        new Vector2(200, 75),
                        TextureManager.getTexture("button_stamina"),
                        TextureManager.getTexture("button_stamina_hover"),
                        TextureManager.getTexture("button_stamina"),
                        ()-> _game.setScreen(new GameScreen(_game, _nextRace))),
                "button_stamina");
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(540, 400),
                        new Vector2(200, 75),
                        TextureManager.getTexture("button_acceleration"),
                        TextureManager.getTexture("button_acceleration_hover"),
                        TextureManager.getTexture("button_acceleration"),
                        ()-> _game.setScreen(new GameScreen(_game, _nextRace))),
                "button_acceleration");
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(540, 300),
                        new Vector2(200, 75),
                        TextureManager.getTexture("button_speed"),
                        TextureManager.getTexture("button_speed_hover"),
                        TextureManager.getTexture("button_speed"),
                        ()-> _game.setScreen(new GameScreen(_game, _nextRace))),
                "button_speed");
        _menuScene.addObject(
                new ButtonSprite(
                        new Vector2(540, 200),
                        new Vector2(200, 75),
                        TextureManager.getTexture("button_health"),
                        TextureManager.getTexture("button_health_hover"),
                        TextureManager.getTexture("button_health"),
                        ()-> _game.setScreen(new GameScreen(_game, _nextRace))),
                "button_health");
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
