package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import io.github.eng1team11.boatgame2d.ecs.EntityFactory;
import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.ecs.system.*;
import io.github.eng1team11.boatgame2d.ui.ButtonSprite;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.util.TextureManager;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class TestScreen implements Screen {

    final BoatGame _game;

    OrthographicCamera _camera;
    Scene _scene;

    float _obstacleFrequency;

    /**
     * Default ctor for the test screen
     *
     * @param game BoatGame instance
     */
    public TestScreen(BoatGame game) {
        _game = game;
        //This would be the point at which the obstacle frequency would be decided
        _obstacleFrequency = 0.001f;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        TextureManager.loadTexture("placeholder.png", "placeholder");
        TextureManager.loadTexture("badlogic.jpg", "badlogic");
        _scene = new Scene();
        _camera = new OrthographicCamera(1280, 720);
        _camera.setToOrtho(true, 1280, 720);

        EntityFactory.get().setComponentManager(_game._componentManager);
        EntityFactory.get().setEntityManager(_game._entityManager);


        _scene.addObject(
                new ButtonSprite(
                        Vector2.zero,
                        new Vector2(160, 60),
                        TextureManager.getTexture("placeholder"),
                        null
                ),
                "btn_placeholder"
        );

        EntityFactory.get().createPlayerEntity(
                75,
                75,
                100,
                100,
                TextureManager.getTexture("badlogic")
        );
        EntityFactory.get().createAIEntity(
                -75,
                -75,
                100,
                100,
                TextureManager.getTexture("badlogic")
        );
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        if (Math.random() < _obstacleFrequency) {
            EntityFactory.get().createObstacleEntity(
                    400,
                    (int)((Math.random()*Gdx.graphics.getHeight())-(Gdx.graphics.getHeight()/2)),
                    100,
                    100,
                    TextureManager.getTexture("badlogic")
            );
        }

        // Set the clear colour then clear the screen
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the camera
        _camera.update();
        // Use the camera's projection matrix to update the batch
        _game._spriteBatch.setProjectionMatrix(_camera.projection);

        _game._spriteBatch.begin();
        _game._systemManager.update(delta);
        _scene.update(delta);
        _scene.draw(_game._spriteBatch);
        _game._spriteBatch.end();
    }

    /**
     * @param width  The width of the screen
     * @param height The height of the screen
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        _camera.setToOrtho(true, width, height);
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
