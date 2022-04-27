package aot;

public class Coin {
    
    private int coinPerHour;
    private int balance;
    private static int totalCost = 0;
    
    public Coin() {
        balance = 50;
        coinPerHour = 5;  // default coin generation per hour
    }
   
    public int getCoinPerHour() {
        return coinPerHour;
    }
    
    // get the current coin balance
    public int getBalance() {
        return balance;
    }
    
    // get total cost for upgrading as we need to upgrade as much walls/weapons as possible when the remaining coin is not enough
    public int getTotalCost() {
        int actualCost = this.totalCost;
        return actualCost;
    }
    
    // if not in normal mode, set the coin generated per hour
    public void setCoinPerHour(int coinPerHour) {
        this.coinPerHour = coinPerHour;
    }
    
    // set new coin balance
    public void setBalance(int balance) {
        this.balance = balance;
    }

    // helper method to sum up actual cost to upgrade
    public void sumUpCost(int cost) {
        totalCost += cost;
    }
    
    // pay for the cost to upgrade wall or weapon and reset total cost to 0
    public void pay() {
        balance -= totalCost;
        totalCost = 0;
    } 
}
