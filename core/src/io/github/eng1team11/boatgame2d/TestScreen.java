package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.eng1team11.boatgame2d.ecs.EntityFactory;
import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.ecs.system.*;
import io.github.eng1team11.boatgame2d.ui.ButtonSprite;
import io.github.eng1team11.boatgame2d.ui.Scene;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class TestScreen implements Screen {

    final BoatGame _game;

    Scene _scene;


    public TestScreen(BoatGame game) {
        _game = game;
    }

    /**
     * Create all systems required for this game screen
     */
    void CreateSystems() {
        int aiControl = _game._systemManager.AddSystem(new AIControl());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(AIComponent.class), aiControl);

        int playerControl = _game._systemManager.AddSystem(new PlayerControl());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PlayerInputComponent.class), playerControl);

        int stamina = _game._systemManager.AddSystem(new Stamina());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(StaminaComponent.class), stamina);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), stamina);

        int upgrade = _game._systemManager.AddSystem(new Upgrade());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(UpgradeComponent.class), upgrade);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(StaminaComponent.class), upgrade);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(DurabilityComponent.class), upgrade);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), upgrade);

        int collision = _game._systemManager.AddSystem(new Collision());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(ColliderComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(DurabilityComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(SpriteComponent.class), collision);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PositionComponent.class), collision);

        int durability = _game._systemManager.AddSystem(new Durability());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(DurabilityComponent.class), durability);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), durability);

        int movement = _game._systemManager.AddSystem(new Movement());
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PositionComponent.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(VelocityComponent.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(AccelerationComponent.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PlayerInputComponent.class), movement);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(AIComponent.class), movement);

        int render = _game._systemManager.AddSystem(new Render(_game.GetSpriteBatch()));
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(PositionComponent.class), render);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(SpriteComponent.class), render);
        _game._systemManager.RegisterComponentsToSystem(_game._componentManager.GetComponentsOfType(TypeComponent.class), render);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {
        _scene = new Scene();
        _scene.GetCanvas().AttachObject(
                new ButtonSprite(
                        new Vector2(),
                        _scene.GetCanvas(),
                        new Vector2(400, 150),
                        new Sprite(
                                new Texture(Gdx.files.internal("placeholder.png")),
                                0,
                                0
                        ),
                        null
                )
        );

        CreateSystems();
        EntityFactory.Get().CreatePlayer(
                _game._componentManager,
                _game._entityManager,
                75,
                75,
                100,
                100,
                new Texture(Gdx.files.internal("badlogic.jpg"))
        );
        EntityFactory.Get().CreateAI(
                _game._componentManager,
                _game._entityManager,
                -75,
                -75,
                100,
                100,
                new Texture(Gdx.files.internal("badlogic.jpg"))
        );
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        _game.GetSpriteBatch().begin();
        _game._systemManager.Update(delta);
        _scene.Update(delta);
        _scene.Draw(delta, _game.GetSpriteBatch());
        _game.GetSpriteBatch().end();
    }

    /**
     * @param width
     * @param height
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
