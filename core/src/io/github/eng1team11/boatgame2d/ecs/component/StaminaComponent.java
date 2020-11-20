package io.github.eng1team11.boatgame2d.ecs.component;

public class StaminaComponent extends Component {

    float _maxStamina = 100.0f;
    float _stamina = 0.0f;
    float _decayRate = 1.0f;
    float _decayModifier = 1.0f;

    /**
     * Default ctor for a component
     *
     * @param id   the ID of the a component
     * @param type
     */
    public StaminaComponent(int id) {
        super(id);
        SetStaminaToMax();
    }

    /**
     * Get the max stamina value
     * @return The max stamina value as a float
     */
    public float GetMaxStamina() {
        return _maxStamina;
    }

    /**
     * Get the current stamina value
     * @return The current stamina value as a float
     */
    public float GetStamina() {
        return _stamina;
    }

    /**
     * Get the remaining stamina
     * @return The remaining stamina as a decimal / 1.0f
     */
    public float GetRemainingStamina() {
        return _stamina / _maxStamina;
    }

    /**
     * Set the stamina to a specific value (this is limited to a range of 0.0-`_maxStamina`)
     * @param s The value to set stamina to
     */
    public void SetStamina (float s) {
        // Set stamina to a max of `_maxStamina` or a min of 0.0
        _stamina = Math.max(0.0f, Math.min(_maxStamina, s));
    }

    /**
     * Set the value of stamina to the max
     */
    public void SetStaminaToMax() {
        _stamina = _maxStamina;
    }

    /**
     * Decay stamina according to the decay rate and modifier
     * @param delta The time since the last frame
     */
    public void DecayStamina(float delta) {
        _stamina -= _decayRate * _decayModifier * delta;
    }

    /**
     * Add an amount of stamina
     * @param s The amount of stamina to add
     */
    public void AddStamina(float s) {
        SetStamina(_stamina + s);
    }

    /**
     * Add to the decay modified
     * @param m The value to add to the modifier
     */
    public void AddDecayModifier(float m) {
        _decayModifier += m;
    }

    public void SetDecayModifier(float m) { _decayModifier = m; }

}
