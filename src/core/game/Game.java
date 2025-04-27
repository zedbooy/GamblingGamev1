package core.game;

public abstract class Game {
    protected String gameName;

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public abstract void startGame();
    public abstract void endGame();

    public String getGameName() {
        return gameName;
    }
}
