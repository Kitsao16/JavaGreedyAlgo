import java.util.ArrayList;
import java.util.List;

class Hospital {
    String name;
    int x, y; // Coordinates
    int urgency; // Urgency level
    List<String> suppliesNeeded;

    public Hospital(String name, int x, int y, int urgency, List<String> suppliesNeeded) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.urgency = urgency;
        this.suppliesNeeded = suppliesNeeded;
    }
}

class DeliveryVehicle {
    int capacity; // Maximum capacity of the vehicle
    int currentCapacity; // Current load of the vehicle
    int x, y; // Current location
    List<String> suppliesLoaded;

    public DeliveryVehicle(int capacity, int x, int y) {
        this.capacity = capacity;
        this.currentCapacity = 0;
        this.x = x;
        this.y = y;
        this.suppliesLoaded = new ArrayList<>();
    }

    // Method to load supplies onto the vehicle
    public void loadSupplies(List<String> supplies) {
        for (String supply : supplies) {
            this.suppliesLoaded.add(supply);
            this.currentCapacity++;
        }
    }

    // Method to deliver supplies to a hospital
    public void deliverSupplies(Hospital hospital) {
        // Simulate delivery process
        System.out.println("Delivering supplies to " + hospital.name);
        // Update vehicle's location
        this.x = hospital.x;
        this.y = hospital.y;
        // Update vehicle's capacity
        this.currentCapacity -= hospital.suppliesNeeded.size();
        // Simulate unloading supplies
        System.out.println("Unloading supplies at " + hospital.name);
        // Update hospital's supply status
        hospital.suppliesNeeded.clear();
    }
}

public class EmergencySupplyDelivery {
    public static void main(String[] args) {
        // Create hospitals
        Hospital hospital1 = new Hospital("Hospital A", 10, 20, 3, List.of("Medication", "PPE"));
        Hospital hospital2 = new Hospital("Hospital B", 30, 40, 2, List.of("PPE", "Medical Equipment"));
        // Add more hospitals as needed
        
        // Create delivery vehicles
        DeliveryVehicle vehicle1 = new DeliveryVehicle(10, 0, 0);
        DeliveryVehicle vehicle2 = new DeliveryVehicle(15, 0, 0);
        // Add more vehicles as needed
        
        // Greedy delivery algorithm
        while (true) {
            Hospital nearestHospital = findNearestHospital(vehicle1.x, vehicle1.y, hospital1, hospital2);
            if (nearestHospital != null && vehicle1.currentCapacity >= nearestHospital.suppliesNeeded.size()) {
                vehicle1.loadSupplies(nearestHospital.suppliesNeeded);
                vehicle1.deliverSupplies(nearestHospital);
            } else {
                break; // No more hospitals to deliver to
            }
        }
    }

    // Method to find the nearest hospital from a given location
    public static Hospital findNearestHospital(int x, int y, Hospital... hospitals) {
        Hospital nearestHospital = null;
        double minDistance = Double.MAX_VALUE;
        for (Hospital hospital : hospitals) {
            double distance = Math.sqrt(Math.pow(x - hospital.x, 2) + Math.pow(y - hospital.y, 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestHospital = hospital;
            }
        }
        return nearestHospital;
    }
}

