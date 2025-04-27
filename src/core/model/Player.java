package core.model;

public class Player {
    private String name;
    private int money;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void subtractMoney(int amount) {
        money -= amount;
    }

    public boolean canBet(int amount) {
        return amount > 0 && amount <= money;
    }

    @Override
    public String toString() {
        return String.format("👤 Player: %s | 💰 Balance: $%d", name, money);
    }
}
