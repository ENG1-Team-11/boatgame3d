package io.github.eng1team11.boatgame2d.ecs.component;

public class Vector2 extends Component {
    // X component - default to zero
    float _x = 0;
    // Y component - default to zero
    float _y = 0;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public Vector2(int id) {
        super(id);
    }

    /**
     * Set the X and Y values of the vector
     * @param x The X component of the vector
     * @param y The Y component of the vector
     */
    public void Set(float x, float y) {
        _x = x;
        _y = y;
    }

    /**
     * Set the values of the vector to that of another vector
     * @param other The other vector to take the values of
     */
    public void Set(Vector2 other) {
        _x = other._x;
        _y = other._y;
    }

    /**
     * Set the x component
     * @param x The value to set the x component to
     */
    public void SetX(float x) {
        _x = x;
    }

    /**
     * Set the y component
     * @param y The value to set the y component to
     */
    public void SetY(float y) {
        _y = y;
    }

    /**
     * Add a vector to the existing vector
     * @param x The X component of the vector to add
     * @param y The Y component of the vector to add
     */
    public void Add(float x, float y) {
        _x += x;
        _y += y;
    }

    /**
     * Add a vector to the existing vector
     * @param other The other vector to add
     */
    public void Add(Vector2 other) {
        _x += other._x;
        _y += other._y;
    }

    /**
     * Set the velocity vector's X and Y components to zero
     */
    public void Zero() {
        _x = 0;
        _y = 0;
    }

    /**
     * Get the X and Y component of the vector
     * @return The x,y components of the vector as an array [x,y]
     */
    public float[] Get() {
        return new float[] {_x, _y};
    }

    /**
     * Get the X component of the vector
     * @return The x component of the vector as a float
     */
    public float GetX() {
        return _x;
    }

    /**
     * Get the Y component of the vector
     * @return The y component of the vector as a float
     */
    public float GetY() {
        return _y;
    }
}
