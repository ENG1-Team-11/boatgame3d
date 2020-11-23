package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.Texture;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class ButtonSprite extends SpriteObject {

    Runnable _releaseCB;
    Runnable _hoverCB;
    Runnable _pressCB;

    State _state;

    /**
     * Default c'tor for a sprite-based button
     *
     * @param position The position of the UI element (top-left corner)
     * @param size     The size of the UI element
     * @param texture  The texture the button should use
     * @param callback The callback that should be invoked when the button is clicked on
     */
    public ButtonSprite(Vector2 position, Vector2 size, Texture texture, Runnable callback) {
        super(position, size, texture);
        _releaseCB = callback;
        _state = State.None;
    }

    /**
     * Should be overridden to implement custom behaviours
     *
     * @param mouseX The x position of the mouse
     * @param mouseY The y position of the mouse
     * @param click  Whether or not the mouse has been clicked
     */
    @Override
    public void update(int mouseX, int mouseY, boolean click) {
        super.update(mouseX, mouseY, click);
//        System.out.printf("X: %d;\tY: %d\n", (int) _position._x, (int) _position._y);
        if (mouseX > _position._x && mouseX < (_position._x + _size._x)) {
            if (mouseY > _position._y && mouseY < (_position._y + _size._y)) {
                if (click) {
                    onPress();
                    return;
                } else if (_state == State.Press) {
                    onRelease();
                    return;
                } else {
                    onHover();
                    return;
                }
            }
        }
        _state = State.None;
    }

    /**
     * Set the callback to be run when the button is released
     *
     * @param fn A runnable object to call
     */
    public void setReleaseCallback(Runnable fn) {
        _releaseCB = fn;
    }

    /**
     * Set the callback to be run when the button is pressed
     *
     * @param fn A runnable object to call
     */
    public void setPressCallback(Runnable fn) {
        _pressCB = fn;
    }

    /**
     * Set the callback to be run when the button is hovered over
     *
     * @param fn A runnable object to call
     */
    public void setHoverCallback(Runnable fn) {
        _hoverCB = fn;
    }

    /**
     * Hover callback
     */
    public void onHover() {
        _state = State.Hover;
        if (_hoverCB != null)
            _hoverCB.run();
    }

    /**
     * Press callback
     */
    public void onPress() {
        _state = State.Press;
        if (_pressCB != null)
            _pressCB.run();
    }

    /**
     * Release callback
     */
    public void onRelease() {
        _state = State.None;
        if (_releaseCB != null)
            _releaseCB.run();
    }

    enum State {None, Hover, Press}
}
