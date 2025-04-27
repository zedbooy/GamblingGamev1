package core.GamblingGame;

import core.engine.GameEngine;
import core.game.GamblingGame;
import core.model.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎰 Welcome to Lucky Numbers!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("How much money are you starting with? ");
        int money = scanner.nextInt();

        Player player = new Player(name, money);
        GameEngine game = new GamblingGame(player); // Use interface here
        game.startGame();
    }
}
