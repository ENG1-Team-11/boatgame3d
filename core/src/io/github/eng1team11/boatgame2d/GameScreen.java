package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.ecs.system.*;

public class GameScreen implements Screen {

    private final BoatGame _game;

    /**
     * Create all systems required for this game screen
     */
    void createSystems() {
        int aiControl = _game._systemManager.addSystem(new AIControl());
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(AIComponent.class), aiControl);

        int playerControl = _game._systemManager.addSystem(new PlayerControl());
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(PlayerInputComponent.class), playerControl);

        int stamina = _game._systemManager.addSystem(new Stamina());
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(StaminaComponent.class), stamina);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(VelocityComponent.class), stamina);

        int upgrade = _game._systemManager.addSystem(new Upgrade());
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(UpgradeComponent.class), upgrade);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(StaminaComponent.class), upgrade);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(DurabilityComponent.class), upgrade);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(VelocityComponent.class), upgrade);

        int collision = _game._systemManager.addSystem(new Collision());
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(ColliderComponent.class), collision);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(DurabilityComponent.class), collision);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(VelocityComponent.class), collision);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(SpriteComponent.class), collision);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(PositionComponent.class), collision);

        int durability = _game._systemManager.addSystem(new Durability());
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(DurabilityComponent.class), durability);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(VelocityComponent.class), durability);

        int movement = _game._systemManager.addSystem(new Movement());
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(PositionComponent.class), movement);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(VelocityComponent.class), movement);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(AccelerationComponent.class), movement);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(PlayerInputComponent.class), movement);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(AIComponent.class), movement);

        int render = _game._systemManager.addSystem(new Render(_game._spriteBatch));
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(PositionComponent.class), render);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(SpriteComponent.class), render);
        _game._systemManager.registerComponentsToSystem(_game._componentManager.getComponentsOfType(TypeComponent.class), render);
    }

    /**
     * Default ctor for the game screen
     *
     * @param boatGame The BoatGame this screen is attached to
     */
    public GameScreen(final BoatGame boatGame) {
        _game = boatGame;
        createSystems();
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

        _game._systemManager.update(delta);


        // Set the screen clear colour to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        // Clear the colour buffer and the depth buffer
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
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
        _game._systemManager.clear();
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
