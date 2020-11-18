package io.github.eng1team11.boatgame2d.ecs.component;

public class VelocityComponent extends Vector2 {

    float _baseVelocity = 10.0f;
    float _velocityModifier = 1.0f;
    float _durModifier = 1.0f;

    /**
     * Default ctor for a component
     * @param id the ID of the a component
     */
    public VelocityComponent(int id) {
        super(id);
    }

    /**
     * Get the X and Y component of the vector
     * @return The x,y components of the vector as an array [x,y]
     */
    @Override
    public float[] Get() {
        return new float[] {GetX(), GetY()};
    }

    /**
     * Get the X and Y component of the vector, multiplied by the velocity modifier
     * @return The x,y components of the vector as an array [x,y]
     */
    public float[] GetModified() {
        return new float[] {GetXModified(), GetXModified()};
    }

    /**
     * Get the X component of the vector
     * @return The x component of the vector as a float
     */
    @Override
    public float GetX() {
        return super.GetX();
    }

    /**
     * Get the Y component of the vector
     * @return The y component of the vector as a float
     */
    @Override
    public float GetY() {
        return super.GetY() ;
    }

    /**
     * Get the X component of the vector, multiplied by the velocity modifier
     * @return The modified x component as a float
     */
    public float GetXModified() {
        return GetX() * (_baseVelocity * _velocityModifier * _durModifier);
    }

    /**
     * Get the Y component of the vector, multiplied by the velocity modifier
     * @return The modified y component as a float
     */
    public float GetYModified() {
        return GetY() * (_baseVelocity * _velocityModifier * _durModifier);
    }

    /**
     * Set the base velocity
     * @param v The value to set the base velocity to
     */
    public void SetBaseVelocity(float v) {
        // Make sure velocity is at least 0 (don't want to go backwards)
        _baseVelocity = Math.max(0.0f, v);
    }

    /**
     * Add to the velocity modifier
     * @param v The value to add to the velocity modifier (should probably be <1.0f)
     */
    public void AddToModifier(float v) {
        _velocityModifier += v;
    }

    /**
     * Set the durability modifier
     * @param v The value to set the modifier to
     */
    public void SetDurModifier(float v) {
        _durModifier = v;
    }
}
