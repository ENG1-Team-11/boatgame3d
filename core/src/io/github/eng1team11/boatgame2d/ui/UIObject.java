package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

import java.util.ArrayList;

public class UIObject {
    ArrayList<UIObject> _children = new ArrayList<>();

    UIObject _parent;
    Vector2 _position;
    // Cheaper to just store this than recalculate it every time
    Vector2 _absolutePosition;

    /**
     * Default c'tor for a UI object
     *
     * @param position The position of the UI element (top-left corner)
     * @param parent   The parent object of the UI element
     */
    UIObject(Vector2 position, UIObject parent) {
        _position = position;
        _absolutePosition = new Vector2();
        _absolutePosition = GetPositionAbsolute(_absolutePosition);
        SetParent(parent);
    }

    /**
     * Should be overridden to implement custom behaviours
     *
     * @param mouseX The x position of the mouse
     * @param mouseY The y position of the mouse
     * @param click  Whether or not the mouse has been clicked
     */
    public void Update(float mouseX, float mouseY, boolean click) {
        for (UIObject child : _children) {
            child.Update(mouseX, mouseY, click);
        }
    }

    /**
     * Draw the object.  Make sure to run `super.Draw(spriteBatch)` first on derived objects
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    public void Draw(SpriteBatch spriteBatch) {
        for (UIObject child : _children) {
            child.Draw(spriteBatch);
        }
    }

    public void AttachObject(UIObject object) {
        _children.add(object);
        object._parent = this;
    }

    public void SetParent(UIObject object) {
        if (object != null) {
            _parent = object;
            _absolutePosition.Zero();
            GetPositionAbsolute(_absolutePosition);
        }
        _absolutePosition = _position;
    }

    /**
     * Recursive version
     *
     * @param pos The current position
     * @return The position + the current position, as a Vector2
     */
    private Vector2 GetPositionAbsolute(Vector2 pos) {
        if (_parent != null) _parent.GetPositionAbsolute(pos);
        pos.Add(_position);
        return pos;
    }

    /**
     * Get the absolute position of the UI element
     *
     * @return The absolute position of the UI element, as a Vector2
     */
    public Vector2 GetPositionAbsolute() {
        return _absolutePosition;
    }

    /**
     * Get the relative position of the UI element
     *
     * @return The position of the UI element relative to its parent, as a Vector2
     */
    public Vector2 GetPosition() {
        return _position;
    }

}
