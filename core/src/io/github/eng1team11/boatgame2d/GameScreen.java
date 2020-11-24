package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.eng1team11.boatgame2d.ecs.EntityFactory;
import io.github.eng1team11.boatgame2d.ecs.component.SpriteComponent;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.ui.Text;
import io.github.eng1team11.boatgame2d.util.FontManager;
import io.github.eng1team11.boatgame2d.util.TextureManager;
import io.github.eng1team11.boatgame2d.util.Vector2;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private final BoatGame _game;

    float _startCountdown;
    Text _countdownText;

    float _obstacleFrequency;

    float _raceProgress;
    SpriteComponent _playerSprite;

    Scene _ui;

    ArrayList<Integer> _obstacles;

    /**
     * Default ctor for the game screen
     *
     * @param boatGame The BoatGame this screen is attached to
     */
    public GameScreen(final BoatGame boatGame) {
        _game = boatGame;
        _obstacles = new ArrayList<>();
    }

    void SpawnObstacle() {
        _obstacles.add(
                EntityFactory.get().createObstacleEntity(
                        1000 + _playerSprite._position._x,
                        (int) ((Math.random() * Gdx.graphics.getHeight()) - (Gdx.graphics.getHeight() / 2)),
                        25,
                        25,
                        TextureManager.getTexture("badlogic")
                )
        );
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        int player = EntityFactory.get().createPlayerEntity(
                -375,
                Gdx.graphics.getHeight() / 4.0f,
                334,
                96,
                TextureManager.getTexture("boat")
        );
        EntityFactory.get().createAIEntity(
                -375,
                -75,
                334,
                96,
                TextureManager.getTexture("boat")
        );
        EntityFactory.get().createAIEntity(
                -375,
                -225,
                334,
                96,
                TextureManager.getTexture("boat")
        );

        _ui = new Scene();
        _ui.addObject(new Text(new Vector2(-20.0f, 72.0f), "5", FontManager.get().getFont(72)), "text_countdown");
        _countdownText = (Text) _ui.getObject("text_countdown");

        _obstacleFrequency = 0.1f;
        _startCountdown = 5.0f;

        _playerSprite = (SpriteComponent) _game._componentManager.getComponent(player, SpriteComponent.class);

        // Update the camera to centre on the player
        _game._camera.position.set(_playerSprite._position.getX() + _playerSprite._size._x / 2, _playerSprite._position.getY() + _playerSprite._size._y / 2, 0.0f);
        _game._camera.update();
        _game._spriteBatch.setProjectionMatrix(_game._camera.combined);

        // Update the systems once so it looks good during the countdown
        _game._systemManager.update(0.0f);
    }

    /**
     * Do the countdown sequence if required
     *
     * @param delta The time since the last frame
     * @return A boolean representing whether or not hte countdown is still in progress
     */
    boolean doCountdown(float delta) {
        if (_startCountdown >= 0.0f) {
            // Convert remaining time to an String, display it
            _startCountdown -= delta;
            String time = Integer.toString((int) Math.ceil(_startCountdown));
            _countdownText.setText(time);

            _game._camera.update();
            _game._spriteBatch.setProjectionMatrix(_game._camera.combined);

            // Behind the sprite batch
            _game._spriteBatch.begin();
            // Render the in-game objects
            _game._systemManager.render(delta);
            // Render the UI
            _ui.draw(_game._spriteBatch);
            // End the sprite batch
            _game._spriteBatch.end();
            return true;
        }
        return false;
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        // Set the screen clear colour to black
        Gdx.gl.glClearColor(0.212f, 0.702f, 0.753f, 1.0f);
        // Clear the colour buffer and the depth buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Check if we need to do the countdown timer
        if (doCountdown(delta)) return;

        // Hide the countdown text
        _countdownText.setText("");

        // Spawn some obstacles (maybe)
        if (Math.random() < _obstacleFrequency) {
            if (Math.random() < _obstacleFrequency) {
                SpawnObstacle();
            }
        }

        // Update the camera and use it to set the view projection
        _game._camera.position.set(_playerSprite._position.getX() + _playerSprite._size._x / 2, _playerSprite._position.getY() + _playerSprite._size._y / 2, 0.0f);
        _game._camera.update();
        _game._spriteBatch.setProjectionMatrix(_game._camera.combined);

        // Run input and update functions on all systems
        _game._systemManager.input(delta);
        _game._systemManager.update(delta);

        // Update the UI
        _ui.update(delta);

        //Begin the sprite batch
        _game._spriteBatch.begin();
        // Render all systems' entities
        _game._systemManager.render(delta);
        // Render the UI
        _ui.draw(_game._spriteBatch);
        // End the sprite batch
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
        _game._systemManager.clear();
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
