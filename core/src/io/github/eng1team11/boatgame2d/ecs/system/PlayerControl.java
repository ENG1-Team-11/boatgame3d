package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.PlayerInput;

import java.util.ArrayList;

public class PlayerControl extends System {

    /**
     * Default ctor for PlayerControl
     */
    public PlayerControl() {

    }

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Input(float delta) {

        // Temporary variables to assign left/right values to
        boolean left = false;
        boolean right = false;

        // Check if any key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            left = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            right = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            left = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            right = true;
        }

        // Iterate over all player inputs attached to the system and update them
        for (IComponent comp : _affectedComponents.get(0).values()) {
            PlayerInput pi  = (PlayerInput) comp;
            pi.SetLeft(left);
            pi.SetRight(right);
        }
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Update(float delta) {

    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Render(float delta) {

    }
}
