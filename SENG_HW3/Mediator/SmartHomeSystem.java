package Mediator;

import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface SmartHomeHub {
    void notify(Device device, String event);
}

// Abstract Device Class
abstract class Device {
    protected SmartHomeHub mediator;

    public Device(SmartHomeHub mediator) {
        this.mediator = mediator;
    }

    public abstract void receiveEvent(String event);
}

// Concrete Devices
class Light extends Device {
    private boolean isOn = false;

    public Light(SmartHomeHub mediator) {
        super(mediator);
    }

    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Light: Turned ON");
            mediator.notify(this, "LightOn");
        }
    }

    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Light: Turned OFF");
        }
    }

    @Override
    public void receiveEvent(String event) {
        if (event.equals("AlarmTriggered")) {
            turnOn();
        }
    }
}

class SmartSpeaker extends Device {
    public SmartSpeaker(SmartHomeHub mediator) {
        super(mediator);
    }

    public void playMusic(String song) {
        System.out.println("SmartSpeaker: Playing " + song);
    }

    public void voiceCommand(String command) {
        System.out.println("SmartSpeaker: Received voice command: " + command);
        mediator.notify(this, "VoiceCommand:" + command);
    }

    @Override
    public void receiveEvent(String event) {
        if (event.equals("AlarmTriggered")) {
            System.out.println("SmartSpeaker: Announcing alarm has been triggered.");
        }
    }
}

class SmartDoorLock extends Device {
    private boolean isLocked = true;

    public SmartDoorLock(SmartHomeHub mediator) {
        super(mediator);
    }

    public void lock() {
        if (!isLocked) {
            isLocked = true;
            System.out.println("SmartDoorLock: Door locked.");
        }
    }

    public void unlock() {
        if (isLocked) {
            isLocked = false;
            System.out.println("SmartDoorLock: Door unlocked.");
            mediator.notify(this, "DoorUnlocked");
        }
    }

    @Override
    public void receiveEvent(String event) {
        if (event.equals("AlarmTriggered")) {
            lock();
        }
    }
}

class SprinklerSystem extends Device {
    private boolean isOn = false;

    public SprinklerSystem(SmartHomeHub mediator) {
        super(mediator);
    }

    public void startSprinklers() {
        if (!isOn) {
            isOn = true;
            System.out.println("SprinklerSystem: Sprinklers activated.");
            mediator.notify(this, "SprinklersOn");
        }
    }

    public void stopSprinklers() {
        if (isOn) {
            isOn = false;
            System.out.println("SprinklerSystem: Sprinklers deactivated.");
        }
    }

    @Override
    public void receiveEvent(String event) {
        if (event.equals("SevereWeather")) {
            stopSprinklers();
        }
    }
}

class SmartRefrigerator extends Device {
    public SmartRefrigerator(SmartHomeHub mediator) {
        super(mediator);
    }

    public void checkInventory() {
        System.out.println("SmartRefrigerator: Checking inventory... Low on milk!");
        mediator.notify(this, "LowInventory:Milk");
    }

    @Override
    public void receiveEvent(String event) {
        if (event.startsWith("VoiceCommand:Restock")) {
            System.out.println("SmartRefrigerator: Restocking initiated.");
        }
    }
}

class SmartWindowShades extends Device {
    private boolean areShadesDown = false;

    public SmartWindowShades(SmartHomeHub mediator) {
        super(mediator);
    }

    public void lowerShades() {
        if (!areShadesDown) {
            areShadesDown = true;
            System.out.println("SmartWindowShades: Shades lowered.");
        }
    }

    public void raiseShades() {
        if (areShadesDown) {
            areShadesDown = false;
            System.out.println("SmartWindowShades: Shades raised.");
        }
    }

    @Override
    public void receiveEvent(String event) {
        if (event.equals("SevereWeather")) {
            lowerShades();
        }
    }
}

// Concrete Mediator
class SmartHomeController implements SmartHomeHub {
    private List<Device> devices = new ArrayList<>();

    public void registerDevice(Device device) {
        devices.add(device);
    }

    @Override
    public void notify(Device device, String event) {
        if (device != null) {
            System.out.println("SmartHomeController: Received event '" + event + "' from " + device.getClass().getSimpleName());
        } else {
            System.out.println("SmartHomeController: Received event '" + event + "' from an unknown source.");
        }
        for (Device d : devices) {
            if (d != device) { // Ensure the originating device does not process its own event
                d.receiveEvent(event);
            }
        }
    }
    
}

// Main Class
public class SmartHomeSystem {
    public static void main(String[] args) {
        SmartHomeController mediator = new SmartHomeController();

        Light light = new Light(mediator);
        SmartSpeaker smartSpeaker = new SmartSpeaker(mediator);
        SmartDoorLock smartDoorLock = new SmartDoorLock(mediator);
        SprinklerSystem sprinklerSystem = new SprinklerSystem(mediator);
        SmartRefrigerator smartRefrigerator = new SmartRefrigerator(mediator);
        SmartWindowShades smartWindowShades = new SmartWindowShades(mediator);

        mediator.registerDevice(light);
        mediator.registerDevice(smartSpeaker);
        mediator.registerDevice(smartDoorLock);
        mediator.registerDevice(sprinklerSystem);
        mediator.registerDevice(smartRefrigerator);
        mediator.registerDevice(smartWindowShades);

        System.out.println("Scenario 1: Alarm is triggered.");
        smartDoorLock.lock();
        smartSpeaker.voiceCommand("Restock fridge");
        System.out.println();

        System.out.println("Scenario 2: Severe weather alert.");
        mediator.notify(null, "SevereWeather");
        System.out.println();

        System.out.println("Scenario 3: Low inventory in the fridge.");
        smartRefrigerator.checkInventory();
    }
}

