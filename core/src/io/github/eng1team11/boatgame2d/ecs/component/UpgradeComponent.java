package io.github.eng1team11.boatgame2d.ecs.component;

public class UpgradeComponent extends Component {
    float _staMod = 1.0f;
    float _durMod = 1.0f;
    float _velMod = 1.0f;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public UpgradeComponent(int id) {
        super(id);
    }

    /**
     * Set the stamina modifier
     * @param sta The value to set the stamina modifier to
     */
    void SetStaMod(float sta) {
        _staMod = sta;
    }

    /**
     * Set the durability modifier
     * @param dur The value to set the durability modifier to
     */
    void SetDurMod(float dur) {
        _durMod = dur;
    }

    /**
     * Set the velocity modifier
     * @param vel The value to set the velocity modifier to
     */
    void SetVelMod(float vel) {
        _velMod = vel;
    }

    /**
     * Get the stamina modifier
     * @return The value of the stamina modifier as a float
     */
    public float GetStaMod() {
        return _staMod;
    }

    /**
     * Get the durability modifier
     * @return The value of the durability modifier as a float
     */
    public float GetDurMod() {
        return _durMod;
    }

    /**
     * Get the velocity modifier
     * @return The value of the velocity modifier as a float
     */
    public float GetVelMod() {
        return _velMod;
    }
}
