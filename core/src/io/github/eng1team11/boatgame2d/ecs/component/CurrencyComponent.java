package io.github.eng1team11.boatgame2d.ecs.component;

public class CurrencyComponent extends Component {

    // Amount of currency
    int _amount = 0;

    /**
     * Default ctor for a component
     *
     * @param id The ID of the component
     */
    public CurrencyComponent(int id) {
        super(id);
    }

    /**
     * Getter for the currency
     *
     * @return The amount of currency, as an int
     */
    public int getCurrency() {
        return _amount;
    }

    /**
     * Setter for the currency
     *
     * @param total The amount to set the currency to
     */
    public void setCurrency(int total) {
        _amount = total;
    }

    /**
     * toString for currency
     *
     * @return The amount of currency, as a string
     */
    public String currencyAsString() {
        return "Currency = " + _amount;
    }

    /**
     * Checks if user has enough currency to purchase upgrade
     *
     * @param price The amount of currency to check against
     * @return Whether or not there is more currency than the specified amount, as a boolean
     */
    public boolean enoughCurrency(int price) {
        return (_amount <= price);
    }

    /**
     * Calculates new currency total after gaining currency
     *
     * @param amount The amount of currency to add to the total
     */
    public void addCurrency(int amount) {
        int total = _amount + amount;
        setCurrency(total);
    }
}
