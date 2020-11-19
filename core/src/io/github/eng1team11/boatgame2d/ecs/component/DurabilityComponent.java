package io.github.eng1team11.boatgame2d.ecs.component;

public class DurabilityComponent extends Component  {
    // Current durability value
    int _value = 0;
    // Max durability value
    int _maxValue = 100;


    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public DurabilityComponent(int id) {
        super(id);
    }

    /**
     * Set the durability to the max value
     */
    public void SetDurabilityToMax() {
        _value = _maxValue;
    }

    /**
     * Set the durability value
     * @param durability The value to set the durability to
     */
    public void SetDurability(int durability) {
        _value = Math.max(0, Math.min(_maxValue, durability));
    }

    public void SetMaxDurability(int durability) {
        _maxValue = durability;
    }

    /**
     * Add to the durability
     * @param durability The value to add to durability
     */
    public void AddDurability(int durability) {
        SetDurability(_value + durability);
    }

    /**
     * Get the remaining durability
     * @return The durability as an integer
     */
    public int GetDurability() {
        return _value;
    }

    /**
     * Get the remaining durability as a decimal
     * @return The durability as a fraction of the max durability as a float
     */
    public float GetRemainingDurability() {
        return (float) _value / (float) _maxValue;
    }
}
