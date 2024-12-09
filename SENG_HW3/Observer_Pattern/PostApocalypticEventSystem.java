import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Subject (Central Event System)
class CentralEventSystem {
    private List<EventObserver> observers = new ArrayList<>();
    private String currentEvent;
    private Map<String, String> eventDetails = new HashMap<>();

    // Attach an observer
    public void registerObserver(EventObserver observer) {
        observers.add(observer);
    }

    // Detach an observer
    public void removeObserver(EventObserver observer) {
        observers.remove(observer);
    }

    // Notify all observers
    public void notifyObservers() {
        for (EventObserver observer : observers) {
            observer.update(currentEvent, eventDetails);
        }
    }

    // Trigger a new event
    public void triggerEvent(String event, Map<String, String> details) {
        this.currentEvent = event;
        this.eventDetails = details;
        System.out.println("Global Event Triggered: " + event);
        notifyObservers();
    }
}

// Observer Interface
interface EventObserver {
    void update(String event, Map<String, String> details);
}

// Concrete Observer: Survivor
class Survivor implements EventObserver {
    private String name;
    private int health;

    public Survivor(String name, int health) {
        this.name = name;
        this.health = health;
    }

    @Override
    public void update(String event, Map<String, String> details) {
        System.out.println(name + " (Survivor) reacts to: " + event);

        switch (event) {
            case "Zombie Outbreak":
                System.out.println(name + ": Grabs a weapon! Zombies detected near " + details.get("location") + ".");
                health -= 10;  // Health decreases due to fighting zombies
                System.out.println(name + " loses 10 health. Current health: " + health);
                break;

            case "Resource Drop":
                System.out.println(name + ": Heads to " + details.get("location") + " to collect supplies.");
                health += 5;  // Health increases due to finding food/medicine
                System.out.println(name + " gains 5 health. Current health: " + health);
                break;

            case "Radioactive Storm":
                System.out.println(name + ": Finds shelter! Storm intensity: " + details.get("intensity"));
                health -= Integer.parseInt(details.get("intensity"));  // Health decreases based on storm intensity
                System.out.println(name + " loses " + details.get("intensity") + " health. Current health: " + health);
                break;

            default:
                System.out.println(name + ": Observes the event but takes no immediate action.");
        }
    }
}

// Concrete Observer: Faction
class Faction implements EventObserver {
    private String factionName;
    private int resources;

    public Faction(String factionName, int resources) {
        this.factionName = factionName;
        this.resources = resources;
    }

    @Override
    public void update(String event, Map<String, String> details) {
        System.out.println(factionName + " (Faction) reacts to: " + event);

        switch (event) {
            case "Zombie Outbreak":
                System.out.println(factionName + ": Dispatches troops to defend the area.");
                resources -= 20;  // Resources decrease due to combat
                System.out.println(factionName + " loses 20 resources. Current resources: " + resources);
                break;

            case "Resource Drop":
                System.out.println(factionName + ": Sends scavengers to " + details.get("location") + ".");
                resources += 30;  // Resources increase due to scavenging
                System.out.println(factionName + " gains 30 resources. Current resources: " + resources);
                break;

            case "Radioactive Storm":
                System.out.println(factionName + ": Fortifies base defenses.");
                resources -= 10;  // Resources decrease for fortifications
                System.out.println(factionName + " loses 10 resources. Current resources: " + resources);
                break;

            default:
                System.out.println(factionName + ": Waits for further developments.");
        }
    }
}

// Concrete Observer: Drone
class Drone implements EventObserver {
    private String droneID;

    public Drone(String droneID) {
        this.droneID = droneID;
    }

    @Override
    public void update(String event, Map<String, String> details) {
        System.out.println("Drone " + droneID + " responds to: " + event);

        switch (event) {
            case "Zombie Outbreak":
                System.out.println("Drone " + droneID + ": Deploying aerial surveillance over " + details.get("location") + ".");
                break;

            case "Resource Drop":
                System.out.println("Drone " + droneID + ": Scanning for resources at " + details.get("location") + ".");
                break;

            case "Radioactive Storm":
                System.out.println("Drone " + droneID + ": Returning to base. Storm intensity: " + details.get("intensity"));
                break;

            default:
                System.out.println("Drone " + droneID + ": Standing by.");
        }
    }
}

// Main Class
public class PostApocalypticEventSystem {
    public static void main(String[] args) {
        // Create the event system
        CentralEventSystem eventSystem = new CentralEventSystem();

        // Create observers
        Survivor alice = new Survivor("Alice", 100);
        Survivor bob = new Survivor("Bob", 90);
        Faction raiders = new Faction("Raiders", 200);
        Drone scoutDrone = new Drone("Scout-01");

        // Register observers
        eventSystem.registerObserver(alice);
        eventSystem.registerObserver(bob);
        eventSystem.registerObserver(raiders);
        eventSystem.registerObserver(scoutDrone);

        // Trigger a zombie outbreak
        Map<String, String> zombieDetails = new HashMap<>();
        zombieDetails.put("location", "Sector 7");
        eventSystem.triggerEvent("Zombie Outbreak", zombieDetails);
        System.out.println();

        // Trigger a resource drop
        Map<String, String> resourceDetails = new HashMap<>();
        resourceDetails.put("location", "Abandoned Factory");
        eventSystem.triggerEvent("Resource Drop", resourceDetails);
        System.out.println();

        // Trigger a radioactive storm
        Map<String, String> stormDetails = new HashMap<>();
        stormDetails.put("intensity", "15");
        eventSystem.triggerEvent("Radioactive Storm", stormDetails);
    }
}
