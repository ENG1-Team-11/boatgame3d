package io.github.eng1team11.boatgame2d.ecs.component;

import io.github.eng1team11.boatgame2d.util.Vector2;

public class VelocityComponent extends Vector2Component {

    float _maxVelocity = 50.0f;
    float _baseVelocity = 10.0f;
    float _velocityModifier = 1.0f;
    float _durModifier = 1.0f;
    float _staModifier = 1.0f;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public VelocityComponent(int id) {
        super(id);
    }

    /**
     * Add a vector to the existing vector
     *
     * @param x The X component of the vector to add
     * @param y The Y component of the vector to add
     */
    @Override
    public void Add(float x, float y) {
        _x = Math.max(-_maxVelocity, Math.min(x + _x, _maxVelocity));
        _y = Math.max(-_maxVelocity, Math.min(y + _y, _maxVelocity));
    }

    /**
     * Add a vector to the existing vector
     *
     * @param other The other vector to add
     */
    @Override
    public void Add(Vector2 other) {
        _x = Math.max(-_maxVelocity, Math.min(other._x + _x, _maxVelocity));
        _y = Math.max(-_maxVelocity, Math.min(other._y + _y, _maxVelocity));
    }

    /**
     * Get the X and Y component of the vector, multiplied by the velocity modifier
     *
     * @return The x,y components of the vector as an array [x,y]
     */
    public float[] GetModified() {
        return new float[]{GetXModified(), GetXModified()};
    }

    /**
     * Get the X component of the vector, multiplied by the velocity modifier
     *
     * @return The modified x component as a float
     */
    public float GetXModified() {
        return GetX() * (_baseVelocity * _velocityModifier * ((_durModifier + 1.0f) * 0.5f) * _staModifier);
    }

    /**
     * Get the Y component of the vector, multiplied by the velocity modifier
     *
     * @return The modified y component as a float
     */
    public float GetYModified() {
        return GetY() * (_baseVelocity * _velocityModifier * ((_durModifier + 1.0f) * 0.5f) * _staModifier);
    }

    /**
     * Set the base velocity
     *
     * @param v The value to set the base velocity to
     */
    public void SetBaseVelocity(float v) {
        // Make sure velocity is at least 0 (don't want to go backwards)
        _baseVelocity = Math.max(0.0f, v);
    }

    /**
     * Add to the velocity modifier
     *
     * @param v The value to add to the velocity modifier (should probably be <1.0f)
     */
    public void AddToModifier(float v) {
        _velocityModifier += v;
    }

    /**
     * Set the durability modifier
     *
     * @param v The value to set the modifier to
     */
    public void SetDurModifier(float v) {
        _durModifier = v;
    }

    /**
     * Set the stamina modifier
     *
     * @param v The value to set the modifier to
     */
    public void SetStaModifier(float v) {
        _durModifier = v;
    }

    /**
     * Set the modifier
     *
     * @param v The value to set the modifier to
     */
    public void SetModifier(float v) {
        _velocityModifier = v;
    }
}
