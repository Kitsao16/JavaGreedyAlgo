import java.util.ArrayList;
import java.util.List;

class Hospital {
    String name;
    int locationX;
    int locationY;
    int urgencyLevel;
    int suppliesDemand;

    // Constructor
    public Hospital(String name, int locationX, int locationY, int urgencyLevel, int suppliesDemand) {
        this.name = name;
        this.locationX = locationX;
        this.locationY = locationY;
        this.urgencyLevel = urgencyLevel;
        this.suppliesDemand = suppliesDemand;
    }
}

class DeliveryVehicle {
    int capacity;
    int currentCapacity;
    int locationX;
    int locationY;

    // Constructor
    public DeliveryVehicle(int capacity, int locationX, int locationY) {
        this.capacity = capacity;
        this.currentCapacity = 0;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    // Method to simulate delivering supplies to a hospital
    public void deliverSupplies(Hospital hospital) {
        // Simulate delivery process
        System.out.println("Delivering supplies to " + hospital.name);
        // Update vehicle location
        this.locationX = hospital.locationX;
        this.locationY = hospital.locationY;
        // Update current capacity of the vehicle
        this.currentCapacity += hospital.suppliesDemand;
        System.out.println("Supplies delivered successfully!");
    }
}

public class EmergencySupplyDeliverySimulation {

    // Method to calculate average
    public static double calculateAverage(List<Integer> data) {
        if (data.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return (double) sum / data.size();
    }

    // Method to calculate standard deviation
    public static double calculateStandardDeviation(List<Integer> data) {
        if (data.isEmpty()) {
            return 0;
        }
        double mean = calculateAverage(data);
        double sum = 0;
        for (int value : data) {
            sum += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sum / data.size());
    }

    // Method to calculate Z-score
    public static double calculateZScore(int value, double mean, double standardDeviation) {
        return (value - mean) / standardDeviation;
    }

    // Method to simulate delivery process and collect data
    public static void main(String[] args) {
        // Create list of hospitals
        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital("Hospital A", 10, 20, 3, 50));
        hospitals.add(new Hospital("Hospital B", 30, 40, 2, 80));
        hospitals.add(new Hospital("Hospital C", 50, 60, 5, 100));

        // Create delivery vehicle
        DeliveryVehicle vehicle = new DeliveryVehicle(200, 0, 0);

        // Simulate delivery process and collect data
        List<Integer> deliveryTimes = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            long startTime = System.nanoTime();
            vehicle.deliverSupplies(hospital);
            long endTime = System.nanoTime();
            int deliveryTime = (int) ((endTime - startTime) / 1_000_000); // Convert to milliseconds
            deliveryTimes.add(deliveryTime);
        }

        // Print vehicle's final location and current capacity
        System.out.println("Final vehicle location: (" + vehicle.locationX + ", " + vehicle.locationY + ")");
        System.out.println("Final vehicle capacity: " + vehicle.currentCapacity);

        // Calculate average delivery time
        double averageDeliveryTime = calculateAverage(deliveryTimes);
        System.out.println("Average delivery time: " + averageDeliveryTime + " milliseconds");

        // Calculate standard deviation of delivery times
        double standardDeviationDeliveryTime = calculateStandardDeviation(deliveryTimes);
        System.out.println("Standard deviation of delivery times: " + standardDeviationDeliveryTime + " milliseconds");

        // Calculate Z-score for each delivery time
        System.out.println("Z-scores for delivery times:");
        for (int deliveryTime : deliveryTimes) {
            double zScore = calculateZScore(deliveryTime, averageDeliveryTime, standardDeviationDeliveryTime);
            System.out.println("Delivery time: " + deliveryTime + " milliseconds, Z-score: " + zScore);
        }
    }
}

