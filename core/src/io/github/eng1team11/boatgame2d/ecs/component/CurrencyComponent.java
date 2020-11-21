package io.github.eng1team11.boatgame2d.ecs.component;

public class CurrencyComponent extends Component  {
    // Amount of currency
    int amount = 0;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public CurrencyComponent(int id) {
        super(id);
    }
    
    /**
     * Getter for the currency
     * 
     * @return
     */
    public int getCurrency() {
    	return amount;
    }
    
    /**
     * Setter for the currency
     * 
     * @param total
     */
    public void setCurrency(int total) {
    	amount = total;
	}
    
    /**
     * Checks if user has enough currency to purchase upgrade
     * 
     * @param price
     * @return
     */
    public boolean enoughCurrency(int price) {
    	if(amount < price) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    /**
     * Calculates new currency total after purchasing upgrade
     * 
     * @param price
     */
    public void spendCurrency(int price) {
    	int total = amount - price;
    	setCurrency(total);
    }
    
    /**
     * Calculates new currency total after gaining currency
     * 
     * @param winnings
     */
    public void gainCurrency(int winnings) {
    	int total = amount + winnings;
    	setCurrency(total);
    }
}
