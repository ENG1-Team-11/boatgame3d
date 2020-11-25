package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.GL20;
import io.github.eng1team11.boatgame2d.ecs.EntityFactory;
import io.github.eng1team11.boatgame2d.ecs.component.CurrencyComponent;
import io.github.eng1team11.boatgame2d.ecs.component.SpriteComponent;
import io.github.eng1team11.boatgame2d.ui.ProgressBar;
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
    float _aiStrength;

    CurrencyComponent _coins;
    SpriteComponent _playerSprite;

    float _raceProgress;
    ProgressBar _progressBar;

    Scene _ui;

    ArrayList<Integer> _obstacles;
    RaceState _state;

    RaceNumber _raceNumber;
    
    Music game_music;

    /**
     * Default ctor for the game screen
     *
     * @param boatGame   The BoatGame this screen is attached to
     * @param raceNumber Which leg of the race we're currently on
     */
    public GameScreen(final BoatGame boatGame, RaceNumber raceNumber) {
        _game = boatGame;
        _obstacles = new ArrayList<>();
        _state = RaceState.CountIn;
        _raceNumber = raceNumber;

        // Race number specific parameters
        switch (raceNumber) {
            case R1:
                _obstacleFrequency = 0.03f;
                _aiStrength = 0.1f;
                break;
            case R2:
                _obstacleFrequency = 0.06f;
                _aiStrength = 0.2f;
                break;
            case R3:
                _obstacleFrequency = 0.1f;
                _aiStrength = 0.4f;
                break;
            case Final:
                _obstacleFrequency = 0.25f;
                _aiStrength = 0.6f;
        }
    }

    void SpawnObstacle() {
        _obstacles.add(
                EntityFactory.get().createObstacleEntity(
                        1000 + _playerSprite._position._x,
                        (int) ((Math.random() * Gdx.graphics.getHeight()) - (Gdx.graphics.getHeight() / 2)),
                        25,
                        25,
                        TextureManager.getTexture("obstacle")
                )
        );
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
    	game_music = Gdx.audio.newMusic(Gdx.files.internal("game_music.mp3"));
    	game_music.play();
    	game_music.setLooping(true);
        EntityFactory.get().createDrawableEntity(-800, -600, 9630, 1450, TextureManager.getTexture("background"));
        int player = EntityFactory.get().createPlayerEntity(
                0,
                Gdx.graphics.getHeight() / 3.0f,
                334,
                75,
                TextureManager.getTexture("boat")
        );
        EntityFactory.get().createAIEntity(
                0,
                0,
                334,
                75,
                TextureManager.getTexture("boat"),
                (float) Math.random() * _aiStrength + 0.8f,
                (float) Math.random() * _aiStrength + 0.8f,
                (float) Math.random() * _aiStrength + 0.8f
        );
        EntityFactory.get().createAIEntity(
                0,
                Gdx.graphics.getHeight() / -3.0f,
                334,
                75,
                TextureManager.getTexture("boat"),
                (float) Math.random() * _aiStrength + 0.8f,
                (float) Math.random() * _aiStrength + 0.8f,
                (float) Math.random() * _aiStrength + 0.8f
        );
        EntityFactory.get().createLaneEntity(
                Gdx.graphics.getWidth() * -1.0f,
                Gdx.graphics.getHeight() / -5.25f,
                Gdx.graphics.getWidth() * 100,
                5,
                TextureManager.getTexture("placeholder")
        );
        EntityFactory.get().createLaneEntity(
                Gdx.graphics.getWidth() * -1.0f,
                Gdx.graphics.getHeight() / 5.25f,
                Gdx.graphics.getWidth() * 100,
                5,
                TextureManager.getTexture("placeholder")
        );

        _ui = new Scene();
        _ui.addObject(
                new Text(new Vector2(640.0f, 540.0f), "5", FontManager.get().getFont(72)),
                "text_countdown"
        );
        _ui.addObject(
                new ProgressBar(new Vector2(440.0f, 640.0f), new Vector2(400.0f, 20.0f), TextureManager.getTexture("progress_outer"), TextureManager.getTexture("progress_inner")),
                "progress_raceProgress"
        );
        //supposed to display currency on screen
        //_ui.addObject(new Text(new Vector2(Gdx.graphics.getWidth() / 4.0f, -Gdx.graphics.getHeight() / 3.0f), _coins.currencyAsString(), FontManager.get().getFont(22)), "currency");
        _countdownText = (Text) _ui.getObject("text_countdown");
        _progressBar = (ProgressBar) _ui.getObject("progress_raceProgress");

        _startCountdown = 3.0f;

        _playerSprite = (SpriteComponent) _game._componentManager.getComponent(player, SpriteComponent.class);

        // Update the systems once so it looks good during the countdown
        _game._systemManager.update(0.0f);

        // Update the camera to centre on the player
        _game._gameCamera.position.set(_playerSprite._position.getX() + _playerSprite._size._x / 2, _playerSprite._position.getY() + _playerSprite._size._y / 2, 0.0f);
        _game._gameCamera.update();
        _game._spriteBatch.setProjectionMatrix(_game._gameCamera.combined);
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


            _game._gameCamera.update();

            // Behind the sprite batch
            _game._spriteBatch.begin();
            // Render the in-game objects
            _game._spriteBatch.setProjectionMatrix(_game._gameCamera.combined);
            _game._systemManager.render(delta);
            // Render the UI
            _game._spriteBatch.setProjectionMatrix(_game._guiCamera.combined);
            _ui.draw(_game._spriteBatch);
            // End the sprite batch
            _game._spriteBatch.end();
            return true;
        }
        _state = RaceState.Racing;
        // Hide the countdown text
        _countdownText.setText("");
        return false;
    }

    void doRace(float delta) {


        // Spawn some obstacles (maybe)
        if (Math.random() < _obstacleFrequency) {
            if (Math.random() < _obstacleFrequency) {
                SpawnObstacle();
            }
        }

        // Run input and update functions on all systems
        _game._systemManager.input(delta);
        _game._systemManager.update(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            _playerSprite._position._x = 6701.0f;
        }

        // Update the camera and use it to set the view projection
        _game._gameCamera.position.set(_playerSprite._position.getX() + _playerSprite._size._x / 2, _playerSprite._position.getY() + _playerSprite._size._y / 2, 0.0f);
        _game._gameCamera.update();

        // Update the UI
        _progressBar.setProgress(_raceProgress);

        _raceProgress = _playerSprite._position._x / 6700.0f;

        if (_raceProgress >= 1.0f) {
            _state = RaceState.Finished;
            _ui.addObject(
                    new Text(new Vector2(640.0f, 540.0f), "Race Finished", FontManager.get().getFont(72)),
                    "text_finished"
            );
            _ui.addObject(
                    new Text(new Vector2(640.0f, 440.0f), "Press Space to Continue", FontManager.get().getFont(36)),
                    "text_pressSpace"
            );
        }
    }

    void doFinished(float delta) {
    	
    	game_music.stop();
    	game_music.dispose();

        _game._systemManager.update(delta);

        // Update the camera and use it to set the view projection
        _game._gameCamera.position.set(_playerSprite._position.getX() + _playerSprite._size._x / 2, _playerSprite._position.getY() + _playerSprite._size._y / 2, 0.0f);
        _game._gameCamera.update();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            if (_raceNumber == RaceNumber.Final)
                _game.setScreen(new EndScreen(_game, 1, 0.0f));
            else
                _game.setScreen(new UpgradeScreen(_game, _raceNumber));
        }
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


        switch (_state) {
            case CountIn:
                if (doCountdown(delta)) return;
                break;
            case Racing:
                doRace(delta);
                break;
            case Finished:
                doFinished(delta);
                break;
        }

        _ui.update(delta);

        //Begin the sprite batch
        _game._spriteBatch.begin();
        // Render all systems' entities
        _game._spriteBatch.setProjectionMatrix(_game._gameCamera.combined);
        _game._systemManager.render(delta);
        // Render the UI
        _game._spriteBatch.setProjectionMatrix(_game._guiCamera.combined);
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
        _game._entityManager.clear();
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }

    enum RaceState {CountIn, Racing, Finished}

    public enum RaceNumber {R1, R2, R3, Final}
}
