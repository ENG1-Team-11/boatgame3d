package io.github.eng1team11.boatgame2d.ecs.component;

public class DurabilityComponent extends Component {
    // Current durability value
    float _value = 0;
    // Max durability value
    float _maxValue = 100;
    // Grace period for reducing durability
    float _gracePeriod = 0.1f;
    // Durability modifier (upgrades)
    float _durabilityModifier = 1.0f;
    // Whether or not durability needs to be reduced
    boolean _shouldReduce;


    /**
     * Default ctor for a component
     *
     * @param id The ID of the component
     */
    public DurabilityComponent(int id) {
        super(id);
        setDurabilityToMax();
    }

    /**
     * Set the durability to the max value
     */
    public void setDurabilityToMax() {
        _value = _maxValue;
    }

    /**
     * Set the maximum durability
     *
     * @param durability The value to set the maximum durability to
     */
    public void setMaxDurability(float durability) {
        _maxValue = durability;
    }

    /**
     * Add to the durability
     *
     * @param durability The value to add to durability
     */
    public void addDurability(float durability) {
        setDurability(_value + (durability / _durabilityModifier));
    }

    /**
     * Get the remaining durability
     *
     * @return The durability as an integer
     */
    public float getDurability() {
        return _value;
    }

    /**
     * Set the durability value
     *
     * @param durability The value to set the durability to
     */
    public void setDurability(float durability) {
        _value = Math.max(0, Math.min(_maxValue, durability));
    }

    /**
     * Get the remaining durability as a decimal
     *
     * @return The durability as a fraction of the max durability as a float
     */
    public float getRemainingDurability() {
        return _value / _maxValue;
    }

    /**
     * Get the durability grace period (the time before the boat can be next hit)
     *
     * @return The time before the boat can next be hit in seconds as a float
     */
    public float getGracePeriod() {
        return _gracePeriod;
    }

    /**
     * Set the grace period
     *
     * @param t The value to set the grace period to
     */
    public void setGracePeriod(float t) {
        _gracePeriod = t;
    }

    /**
     * Reduce the grace period by delta
     *
     * @param delta The time since the last frame
     */
    public void decayGracePeriod(float delta) {
        _gracePeriod -= delta;
    }

    /**
     * Set the durability to be reduced
     *
     * @param r Whether or not durability needs to be reduced
     */
    public void setShouldReduce(boolean r) {
        _shouldReduce = r;
    }

    /**
     * Get whether the durability needs to be reduced
     *
     * @return Whether or not the durability should be reduced as a boolean
     */
    public boolean shouldReduce() {
        return _shouldReduce;
    }

    /**
     * Get the durability modifier
     *
     * @return The durability modifier as a float
     */
    public float getDurModifier() {
        return _durabilityModifier;
    }

    /**
     * Set the durability modifier
     *
     * @param m The value to set the durability modifier to
     */
    public void setDurModifier(float m) {
        _durabilityModifier = m;
    }
}
