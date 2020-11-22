package io.github.eng1team11.boatgame2d.util;

public class Vector2 {
    public static Vector2 Zero = new Vector2();
    // Static zeroed vector2
    public static Vector2 zero = new Vector2(0.0f, 0.0f);
    // X component - default to zero
    public float _x = 0;
    // Y component - default to zero
    public float _y = 0;

    public Vector2() {
        _x = 0.0f;
        _y = 0.0f;
    }

    public Vector2(float x, float y) {
        _x = x;
        _y = y;
    }

    /**
     * Set the X and Y values of the vector
     *
     * @param x The X component of the vector
     * @param y The Y component of the vector
     */
    public void set(float x, float y) {
        _x = x;
        _y = y;
    }

    /**
     * Set the values of the vector to that of another vector
     *
     * @param other The other vector to take the values of
     */
    public void set(Vector2 other) {
        _x = other._x;
        _y = other._y;
    }

    /**
     * Add a vector to the existing vector
     *
     * @param x The X component of the vector to add
     * @param y The Y component of the vector to add
     */
    public void add(float x, float y) {
        _x += x;
        _y += y;
    }

    /**
     * Add a vector to the existing vector
     *
     * @param other The other vector to add
     */
    public void add(Vector2 other) {
        _x += other._x;
        _y += other._y;
    }

    /**
     * Set the velocity vector's X and Y components to zero
     */
    public void zero() {
        _x = 0;
        _y = 0;
    }

    /**
     * Get the X and Y component of the vector
     *
     * @return The x,y components of the vector as an array [x,y]
     */
    public float[] get() {
        return new float[]{_x, _y};
    }

    /**
     * Get the X component of the vector
     *
     * @return The x component of the vector as a float
     */
    public float getX() {
        return _x;
    }

    /**
     * Set the x component
     *
     * @param x The value to set the x component to
     */
    public void setX(float x) {
        _x = x;
    }

    /**
     * Get the Y component of the vector
     *
     * @return The y component of the vector as a float
     */
    public float getY() {
        return _y;
    }

    /**
     * Set the y component
     *
     * @param y The value to set the y component to
     */
    public void setY(float y) {
        _y = y;
    }

}
