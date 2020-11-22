package io.github.eng1team11.boatgame2d.ecs.component;

public class StaminaComponent extends Component {

    float _maxStamina = 100.0f;     // The maximum stamina
    float _stamina = 0.0f;          // The current stamina
    float _decayRate = 1.0f;        // The base rate at which stamina decays
    float _decayModifier = 1.0f;    // The decay rate modifier (`final = base * modifier`)

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public StaminaComponent(int id) {
        super(id);
        setStaminaToMax();
    }

    /**
     * Get the max stamina value
     *
     * @return The max stamina value as a float
     */
    public float getMaxStamina() {
        return _maxStamina;
    }

    /**
     * Get the current stamina value
     *
     * @return The current stamina value as a float
     */
    public float getStamina() {
        return _stamina;
    }

    /**
     * Set the stamina to a specific value (this is limited to a range of 0.0-`_maxStamina`)
     *
     * @param s The value to set stamina to
     */
    public void setStamina(float s) {
        // Set stamina to a max of `_maxStamina` or a min of 0.0
        _stamina = Math.max(0.0f, Math.min(_maxStamina, s));
    }

    /**
     * Get the remaining stamina
     *
     * @return The remaining stamina as a decimal / 1.0f
     */
    public float getRemainingStamina() {
        return _stamina / _maxStamina;
    }

    /**
     * Set the value of stamina to the max
     */
    public void setStaminaToMax() {
        _stamina = _maxStamina;
    }

    /**
     * Decay stamina according to the decay rate and modifier
     *
     * @param delta The time since the last frame
     */
    public void decayStamina(float delta) {
        _stamina -= _decayRate * _decayModifier * delta;
    }

    /**
     * Add an amount of stamina
     *
     * @param s The amount of stamina to add
     */
    public void addStamina(float s) {
        setStamina(_stamina + s);
    }

    /**
     * Add to the decay modifier
     *
     * @param m The value to add to the modifier
     */
    public void addDecayModifier(float m) {
        _decayModifier += m;
    }

    /**
     * Set the decay modifier
     *
     * @param m The value to set the decay modifier to
     */
    public void setDecayModifier(float m) {
        _decayModifier = m;
    }

}
