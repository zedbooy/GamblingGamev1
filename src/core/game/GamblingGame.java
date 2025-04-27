package core.game;

import core.engine.GameEngine;
import core.model.Player;
import java.util.Scanner;

public class GamblingGame extends Game implements GameEngine {
    protected final Player player;
    private final Scanner scanner = new Scanner(System.in);

    public GamblingGame(Player player) {
        super("Lucky Numbers 🎰");
        this.player = player;
    }

    @Override
    public void startGame() {
        printBanner();
        while (player.getMoney() > 0) {
            printLine();
            System.out.println(player); // uses Player's toString()

            int guess = askNumber("🎯 Guess a number (1 - 10) or 0 to Cash Out: ", 0, 10);

            if (guess == 0) {
                System.out.println("💵 You chose to Cash Out!");
                break;
            }

            int bet = askBet("💵 Place your bet: ");

            int winningNumber = (int) (Math.random() * 10) + 1;
            System.out.println("🎲 Rolling the number...");
            delay(1000);
            System.out.println("🎉 The winning number is: " + winningNumber);

            if (guess == winningNumber) {
                int winnings = bet * 10;
                player.addMoney(winnings);
                System.out.println("✅ You WIN! You earned $" + winnings);
            } else {
                player.subtractMoney(bet);
                System.out.println("❌ You LOST $" + bet);
            }

            if (player.getMoney() <= 0) {
                System.out.println("💀 You're out of money!");
                break;
            }

            System.out.print("\n🔁 Do you want to play again? (yes/no): ");
            String answer = scanner.next().trim().toLowerCase();
            if (!answer.equals("yes") && !answer.equals("y")) {
                break;
            }
        }
        endGame();
    }

    @Override
    public void endGame() {
        printLine();
        System.out.println(String.format("🏁 Game Over, %s!", player.getName()));
        System.out.println(String.format("🧾 Final Balance: $%d", player.getMoney()));
        printLine();
    }

    private int askNumber(String prompt, int min, int max) {
        int number;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("🚫 Invalid input! Please enter a number.");
                scanner.next();
                System.out.print(prompt);
            }
            number = scanner.nextInt();
        } while (number < min || number > max);
        return number;
    }

    private int askBet(String prompt) {
        int bet;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("🚫 Invalid input! Please enter a number.");
                scanner.next();
                System.out.print(prompt);
            }
            bet = scanner.nextInt();
            if (!player.canBet(bet)) {
                System.out.println("🚫 You don't have enough money!");
            }
        } while (!player.canBet(bet));
        return bet;
    }

    private void printLine() {
        System.out.println("════════════════════════════════════");
    }

    private void printBanner() {
        StringBuilder banner = new StringBuilder();
        banner.append("════════════════════════════════════\n");
        banner.append(String.format("🎰🎰 WELCOME TO %s 🎰🎰\n", super.getGameName()));
        banner.append("════════════════════════════════════\n");
        System.out.println(banner);
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
