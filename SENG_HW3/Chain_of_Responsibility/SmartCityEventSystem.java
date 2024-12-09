package Chain_of_Responsibility;

import java.util.ArrayList;
import java.util.List;

// Abstract Handler
abstract class EventHandler {
    protected EventHandler nextHandler;

    public void setNextHandler(EventHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleEvent(String event, String location);
}

// Concrete Handlers
class FireDepartment extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Fire")) {
            System.out.println("FireDepartment: Responding to fire at " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("FireDepartment: Cannot handle the event.");
        }
    }
}

class TrafficDepartment extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Traffic Jam")) {
            System.out.println("TrafficDepartment: Managing traffic jam at " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("TrafficDepartment: Cannot handle the event.");
        }
    }
}

class WeatherMonitoring extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Severe Weather")) {
            System.out.println("WeatherMonitoring: Issuing alerts for severe weather in " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("WeatherMonitoring: Cannot handle the event.");
        }
    }
}

class EnergyGrid extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Power Outage")) {
            System.out.println("EnergyGrid: Restoring power in " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("EnergyGrid: Cannot handle the event.");
        }
    }
}

class PoliceDepartment extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Burglary")) {
            System.out.println("PoliceDepartment: Investigating burglary at " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("PoliceDepartment: Cannot handle the event.");
        }
    }
}

class WasteManagement extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Overflowing Garbage")) {
            System.out.println("WasteManagement: Clearing overflowing garbage at " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("WasteManagement: Cannot handle the event.");
        }
    }
}

class EmergencyServices extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Medical Emergency")) {
            System.out.println("EmergencyServices: Responding to medical emergency at " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("EmergencyServices: Cannot handle the event.");
        }
    }
}

class CyberSecurity extends EventHandler {
    @Override
    public void handleEvent(String event, String location) {
        if (event.equalsIgnoreCase("Cyber Attack")) {
            System.out.println("CyberSecurity: Handling cyber attack in " + location + ".");
        } else if (nextHandler != null) {
            nextHandler.handleEvent(event, location);
        } else {
            System.out.println("CyberSecurity: Cannot handle the event.");
        }
    }
}

// Main Class
public class SmartCityEventSystem {
    public static void main(String[] args) {
        // Create handlers
        FireDepartment fireDepartment = new FireDepartment();
        TrafficDepartment trafficDepartment = new TrafficDepartment();
        WeatherMonitoring weatherMonitoring = new WeatherMonitoring();
        EnergyGrid energyGrid = new EnergyGrid();
        PoliceDepartment policeDepartment = new PoliceDepartment();
        WasteManagement wasteManagement = new WasteManagement();
        EmergencyServices emergencyServices = new EmergencyServices();
        CyberSecurity cyberSecurity = new CyberSecurity();

        // Chain the handlers
        fireDepartment.setNextHandler(trafficDepartment);
        trafficDepartment.setNextHandler(weatherMonitoring);
        weatherMonitoring.setNextHandler(energyGrid);
        energyGrid.setNextHandler(policeDepartment);
        policeDepartment.setNextHandler(wasteManagement);
        wasteManagement.setNextHandler(emergencyServices);
        emergencyServices.setNextHandler(cyberSecurity);

        // Simulate events
        System.out.println("Scenario 1: Fire in Downtown.");
        fireDepartment.handleEvent("Fire", "Downtown");

        System.out.println("\nScenario 2: Traffic Jam on Main Street.");
        fireDepartment.handleEvent("Traffic Jam", "Main Street");

        System.out.println("\nScenario 3: Severe Weather in North Area.");
        fireDepartment.handleEvent("Severe Weather", "North Area");

        System.out.println("\nScenario 4: Power Outage in South Sector.");
        fireDepartment.handleEvent("Power Outage", "South Sector");

        System.out.println("\nScenario 5: Cyber Attack on City Servers.");
        fireDepartment.handleEvent("Cyber Attack", "City Hall");

        System.out.println("\nScenario 6: Overflowing Garbage in West Park.");
        fireDepartment.handleEvent("Overflowing Garbage", "West Park");
    }
}
