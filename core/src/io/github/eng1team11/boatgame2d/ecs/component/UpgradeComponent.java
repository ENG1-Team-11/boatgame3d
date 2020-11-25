package io.github.eng1team11.boatgame2d.ecs.component;

public class UpgradeComponent extends Component {
    float _staMod = 1.0f;   // The stamina modifier
    float _durMod = 1.0f;   // The durability modifier
    float _velMod = 1.0f;   // The velocity modifier

    /**
     * Default ctor for a component
     *
     * @param id The ID of the component
     */
    public UpgradeComponent(int id) {
        super(id);
    }

    /**
     * Ctor that allows specification of modifiers
     *
     * @param id  The ID of the component
     * @param sta The stamina modifier
     * @param dur The durability modifier
     * @param vel The velocity modifier
     */
    public UpgradeComponent(int id, float sta, float dur, float vel) {
        super(id);
        _staMod = sta;
        _durMod = dur;
        _velMod = vel;
    }

    /**
     * Get the stamina modifier
     *
     * @return The value of the stamina modifier as a float
     */
    public float getStaMod() {
        return _staMod;
    }

    /**
     * Set the stamina modifier
     *
     * @param sta The value to set the stamina modifier to
     */
    void setStaMod(float sta) {
        _staMod = sta;
    }

    /**
     * Get the durability modifier
     *
     * @return The value of the durability modifier as a float
     */
    public float getDurMod() {
        return _durMod;
    }

    /**
     * Set the durability modifier
     *
     * @param dur The value to set the durability modifier to
     */
    void setDurMod(float dur) {
        _durMod = dur;
    }

    /**
     * Get the velocity modifier
     *
     * @return The value of the velocity modifier as a float
     */
    public float getVelMod() {
        return _velMod;
    }

    /**
     * Set the velocity modifier
     *
     * @param vel The value to set the velocity modifier to
     */
    void setVelMod(float vel) {
        _velMod = vel;
    }
}
