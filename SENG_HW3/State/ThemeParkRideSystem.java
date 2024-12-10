package State;

// Context Class
class ThemeParkRide {
    private RideState currentState;

    public ThemeParkRide() {
        this.currentState = new IdleState(this); // Initial state is Idle
    }

    public void setState(RideState state) {
        this.currentState = state;
    }

    public void startRide() {
        currentState.startRide();
    }

    public void stopRide() {
        currentState.stopRide();
    }

    public void performMaintenance() {
        currentState.performMaintenance();
    }

    public void fixRide() {
        currentState.fixRide();
    }
}

// Abstract State
abstract class RideState {
    protected ThemeParkRide ride;

    public RideState(ThemeParkRide ride) {
        this.ride = ride;
    }

    public abstract void startRide();
    public abstract void stopRide();
    public abstract void performMaintenance();
    public abstract void fixRide();
}

// Concrete States

// 1. Idle State
class IdleState extends RideState {
    public IdleState(ThemeParkRide ride) {
        super(ride);
    }

    @Override
    public void startRide() {
        System.out.println("IdleState: Passengers are boarding. Moving to BoardingState.");
        ride.setState(new BoardingState(ride));
    }

    @Override
    public void stopRide() {
        System.out.println("IdleState: Ride is already stopped.");
    }

    @Override
    public void performMaintenance() {
        System.out.println("IdleState: Performing maintenance. Moving to MaintenanceState.");
        ride.setState(new MaintenanceState(ride));
    }

    @Override
    public void fixRide() {
        System.out.println("IdleState: No issues to fix. Ride is idle.");
    }
}

// 2. Boarding State
class BoardingState extends RideState {
    public BoardingState(ThemeParkRide ride) {
        super(ride);
    }

    @Override
    public void startRide() {
        System.out.println("BoardingState: Passengers have boarded. Starting the ride. Moving to OperatingState.");
        ride.setState(new OperatingState(ride));
    }

    @Override
    public void stopRide() {
        System.out.println("BoardingState: Ride cannot be stopped during boarding.");
    }

    @Override
    public void performMaintenance() {
        System.out.println("BoardingState: Cannot perform maintenance during boarding.");
    }

    @Override
    public void fixRide() {
        System.out.println("BoardingState: No issues to fix. Ride is boarding passengers.");
    }
}

// 3. Operating State
class OperatingState extends RideState {
    public OperatingState(ThemeParkRide ride) {
        super(ride);
    }

    @Override
    public void startRide() {
        System.out.println("OperatingState: Ride is already running.");
    }

    @Override
    public void stopRide() {
        System.out.println("OperatingState: Ride has stopped. Moving to IdleState.");
        ride.setState(new IdleState(ride));
    }

    @Override
    public void performMaintenance() {
        System.out.println("OperatingState: Cannot perform maintenance during operation.");
    }

    @Override
    public void fixRide() {
        System.out.println("OperatingState: No issues to fix. Ride is running.");
    }
}

// 4. Maintenance State
class MaintenanceState extends RideState {
    public MaintenanceState(ThemeParkRide ride) {
        super(ride);
    }

    @Override
    public void startRide() {
        System.out.println("MaintenanceState: Cannot start ride during maintenance.");
    }

    @Override
    public void stopRide() {
        System.out.println("MaintenanceState: Maintenance completed. Moving to IdleState.");
        ride.setState(new IdleState(ride));
    }

    @Override
    public void performMaintenance() {
        System.out.println("MaintenanceState: Ride is already under maintenance.");
    }

    @Override
    public void fixRide() {
        System.out.println("MaintenanceState: Maintenance completed. Moving to IdleState.");
        ride.setState(new IdleState(ride));
    }
}

// 5. OutOfService State
class OutOfServiceState extends RideState {
    public OutOfServiceState(ThemeParkRide ride) {
        super(ride);
    }

    @Override
    public void startRide() {
        System.out.println("OutOfServiceState: Cannot start ride. Ride is out of service.");
    }

    @Override
    public void stopRide() {
        System.out.println("OutOfServiceState: Ride is already stopped.");
    }

    @Override
    public void performMaintenance() {
        System.out.println("OutOfServiceState: Performing maintenance. Moving to MaintenanceState.");
        ride.setState(new MaintenanceState(ride));
    }

    @Override
    public void fixRide() {
        System.out.println("OutOfServiceState: Fixing ride. Moving to IdleState.");
        ride.setState(new IdleState(ride));
    }
}

// Main Class
public class ThemeParkRideSystem {
    public static void main(String[] args) {
        ThemeParkRide ride = new ThemeParkRide();

        System.out.println("Scenario 1: Starting the ride.");
        ride.startRide();

        System.out.println("\nScenario 2: Ride in operation. Stopping the ride.");
        ride.stopRide();

        System.out.println("\nScenario 3: Performing maintenance.");
        ride.performMaintenance();

        System.out.println("\nScenario 4: Fixing ride after maintenance.");
        ride.fixRide();

        System.out.println("\nScenario 5: Moving to OutOfService state and fixing.");
        ride.setState(new OutOfServiceState(ride));
        ride.fixRide();

        System.out.println("\nScenario 6: Starting ride again after fixing.");
        ride.startRide();
    }
}

