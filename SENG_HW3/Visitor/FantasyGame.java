package Visitor;

import java.util.ArrayList;
import java.util.List;

// Visitable Interface
interface GameLocation {
    void accept(GameCharacter visitor);
}

// Visitor Interface
interface GameCharacter {
    void visit(Forest forest);
    void visit(Castle castle);
    void visit(Dungeon dungeon);
    void visit(MagicSpring magicSpring);
}

// Concrete Location: Forest
class Forest implements GameLocation {
    @Override
    public void accept(GameCharacter visitor) {
        visitor.visit(this);
    }
}

// Concrete Location: Castle
class Castle implements GameLocation {
    @Override
    public void accept(GameCharacter visitor) {
        visitor.visit(this);
    }
}

// Concrete Location: Dungeon
class Dungeon implements GameLocation {
    @Override
    public void accept(GameCharacter visitor) {
        visitor.visit(this);
    }
}

// Concrete Location: MagicSpring
class MagicSpring implements GameLocation {
    @Override
    public void accept(GameCharacter visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitor: Knight
class Knight implements GameCharacter {
    @Override
    public void visit(Forest forest) {
        System.out.println("Knight fights monsters in the forest!");
    }

    @Override
    public void visit(Castle castle) {
        System.out.println("Knight protects the castle's inhabitants.");
    }

    @Override
    public void visit(Dungeon dungeon) {
        System.out.println("Knight charges into the dungeon with bravery!");
    }

    @Override
    public void visit(MagicSpring magicSpring) {
        System.out.println("Knight drinks from the Magic Spring to restore health.");
    }
}

// Concrete Visitor: Mage
class Mage implements GameCharacter {
    @Override
    public void visit(Forest forest) {
        System.out.println("Mage collects rare herbs in the forest.");
    }

    @Override
    public void visit(Castle castle) {
        System.out.println("Mage enchants the castle's defenses.");
    }

    @Override
    public void visit(Dungeon dungeon) {
        System.out.println("Mage casts powerful spells to clear the dungeon.");
    }

    @Override
    public void visit(MagicSpring magicSpring) {
        System.out.println("Mage harnesses the Magic Spring's power to enhance magic.");
    }
}

// Concrete Visitor: Thief
class Thief implements GameCharacter {
    @Override
    public void visit(Forest forest) {
        System.out.println("Thief stealthily avoids monsters in the forest.");
    }

    @Override
    public void visit(Castle castle) {
        System.out.println("Thief sneaks into the castle to steal treasures.");
    }

    @Override
    public void visit(Dungeon dungeon) {
        System.out.println("Thief uses agility to evade traps in the dungeon.");
    }

    @Override
    public void visit(MagicSpring magicSpring) {
        System.out.println("Thief secretly fills a vial with the Magic Spring's water.");
    }
}

// Main Class
public class FantasyGame {
    public static void main(String[] args) {
        // Create locations
        List<GameLocation> locations = new ArrayList<>();
        locations.add(new Forest());
        locations.add(new Castle());
        locations.add(new Dungeon());
        locations.add(new MagicSpring());

        // Create characters
        GameCharacter knight = new Knight();
        GameCharacter mage = new Mage();
        GameCharacter thief = new Thief();

        // Visitors interact with locations
        System.out.println("Knight's Adventure:");
        for (GameLocation location : locations) {
            location.accept(knight);
        }

        System.out.println("\nMage's Adventure:");
        for (GameLocation location : locations) {
            location.accept(mage);
        }

        System.out.println("\nThief's Adventure:");
        for (GameLocation location : locations) {
            location.accept(thief);
        }
    }
}
