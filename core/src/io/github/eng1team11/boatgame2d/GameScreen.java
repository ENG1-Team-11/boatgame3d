package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.eng1team11.boatgame2d.ecs.EntityFactory;
import io.github.eng1team11.boatgame2d.util.TextureManager;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private final BoatGame _game;

    float _startCountdown;
    float _obstacleFrequency;
    float _raceProgress;

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
                        400,
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
        EntityFactory.get().createPlayerEntity(
                -375,
                Gdx.graphics.getHeight() / 4,
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

        _obstacleFrequency = 0.1f;
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        if (Math.random() < _obstacleFrequency) {
            if (Math.random() < _obstacleFrequency) {
                SpawnObstacle();
            }
        }


        // Set the screen clear colour to black
        Gdx.gl.glClearColor(0.212f, 0.702f, 0.753f, 1.0f);
        // Clear the colour buffer and the depth buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        _game._camera.update();
        _game._spriteBatch.setProjectionMatrix(_game._camera.projection);

        //Begin the sprite batch
        _game._spriteBatch.begin();
        _game._systemManager.update(delta);
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
