package Template;

import java.util.ArrayList;
import java.util.List;

// Template Class (Base Abstract Class)
abstract class GameTemplate {
    // Template method defining the skeleton of the game lifecycle
    public final void runGame() {
        initialize();
        start();
        play();
        end();
        close();
    }

    protected abstract void initialize();
    protected abstract void start();
    protected abstract void play();
    protected abstract void end();
    protected void close() {
        System.out.println("Closing game resources...");
    }
}

// Concrete Class 1: RPG Game
class RPGGame extends GameTemplate {
    private List<Character> characters = new ArrayList<>();
    private Level level;

    @Override
    protected void initialize() {
        System.out.println("RPGGame: Initializing game resources...");
        characters.add(new Character("Warrior", 100, new Weapon("Sword")));
        characters.add(new Character("Mage", 80, new Weapon("Staff")));
        level = new Level("Dark Forest");
        System.out.println("RPGGame: Characters and level initialized.");
    }

    @Override
    protected void start() {
        System.out.println("RPGGame: Starting the adventure in " + level.getName() + "!");
    }

    @Override
    protected void play() {
        System.out.println("RPGGame: Playing... Characters are battling monsters!");
        for (Character character : characters) {
            character.attack();
        }
    }

    @Override
    protected void end() {
        System.out.println("RPGGame: The adventure has ended. Calculating XP...");
    }
}

// Concrete Class 2: FPS Game
class FPSGame extends GameTemplate {
    private Weapon playerWeapon;

    @Override
    protected void initialize() {
        System.out.println("FPSGame: Loading 3D assets...");
        playerWeapon = new Weapon("Assault Rifle");
    }

    @Override
    protected void start() {
        System.out.println("FPSGame: Starting the battle!");
    }

    @Override
    protected void play() {
        System.out.println("FPSGame: Playing... Shooting enemies with " + playerWeapon.getName() + "!");
    }

    @Override
    protected void end() {
        System.out.println("FPSGame: Mission completed. Calculating score...");
    }
}

// Concrete Class 3: Puzzle Game
class PuzzleGame extends GameTemplate {
    private int puzzlesSolved = 0;

    @Override
    protected void initialize() {
        System.out.println("PuzzleGame: Loading puzzles...");
    }

    @Override
    protected void start() {
        System.out.println("PuzzleGame: Starting the puzzle game!");
    }

    @Override
    protected void play() {
        System.out.println("PuzzleGame: Solving puzzles...");
        puzzlesSolved += 5; // Example
        System.out.println("PuzzleGame: Solved " + puzzlesSolved + " puzzles!");
    }

    @Override
    protected void end() {
        System.out.println("PuzzleGame: All puzzles solved! Calculating final score...");
    }
}

// Supporting Classes
class Character {
    private String name;
    private int health;
    private Weapon weapon;

    public Character(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public void attack() {
        System.out.println(name + " attacks with " + weapon.getName() + "!");
    }
}

class Weapon {
    private String name;

    public Weapon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Level {
    private String name;

    public Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Main Class
public class GameFramework {
    public static void main(String[] args) {
        System.out.println("Running RPG Game:");
        GameTemplate rpgGame = new RPGGame();
        rpgGame.runGame();

        System.out.println("\nRunning FPS Game:");
        GameTemplate fpsGame = new FPSGame();
        fpsGame.runGame();

        System.out.println("\nRunning Puzzle Game:");
        GameTemplate puzzleGame = new PuzzleGame();
        puzzleGame.runGame();
    }
}
