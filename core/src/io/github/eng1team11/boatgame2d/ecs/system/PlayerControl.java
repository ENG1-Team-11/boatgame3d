package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.PlayerInputComponent;

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
        boolean forwards = false;
        boolean backwards = false;

        // Check if any key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            left = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            right = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            forwards = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            backwards = true;
        }

        // Iterate over all player inputs attached to the system and update them
        for (IComponent comp : _affectedComponents.get(0).values()) {
            PlayerInputComponent pi  = (PlayerInputComponent) comp;
            pi.SetLeft(left);
            pi.SetRight(right);
            pi.SetForwards(forwards);
            pi.SetBackwards(backwards);
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
