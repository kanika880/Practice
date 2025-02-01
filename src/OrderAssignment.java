import java.util.*;

public class OrderAssignment {

        // Class to represent a driver
        static class Driver {
            int id; // Driver ID
            int capacity; // Total capacity
            int remainingCapacity; // Remaining capacity after assignments
            List<Integer> assignedOrders; // List of assigned orders

            // Constructor
            public Driver(int id, int capacity) {
                this.id = id;
                this.capacity = capacity;
                this.remainingCapacity = capacity;
                this.assignedOrders = new ArrayList<>();
            }

            // Display driver information
            @Override
            public String toString() {
                return "Driver " + id + " (Remaining Capacity: " + remainingCapacity + ") -> Orders: " + assignedOrders;
            }
        }

        public static void main(String[] args) {
            // Input: Orders and Driver Capacities
            int[] orders = {5, 10, 10, 10, 15};
            int[] capacities = {10, 20};

            // Perform order assignment
            Map<Integer, Driver> drivers = assignOrdersToDrivers(orders, capacities);

            // Display results
            System.out.println("Order Assignments:");
            drivers.values().forEach(System.out::println);

            System.out.println("Unassigned Orders:");
            System.out.println(drivers.get(-1).assignedOrders); // Unassigned orders stored under Driver ID -1
        }

        /**
         * Function to assign orders to drivers based on their capacities.
         * @param orders Array of order weights
         * @param driverCapacities Array of driver capacities
         * @return Map of Driver IDs to Driver objects, including unassigned orders under Driver ID -1
         */
        public static Map<Integer, Driver> assignOrdersToDrivers(int[] orders, int[] driverCapacities) {
            // Sort orders in descending order
            Arrays.sort(orders);
            for (int i = 0; i < orders.length / 2; i++) {
                int temp = orders[i];
                orders[i] = orders[orders.length - i - 1];
                orders[orders.length - i - 1] = temp;
            }

            // Create driver objects
            Map<Integer, Driver> drivers = new HashMap<>();
            for (int i = 0; i < driverCapacities.length; i++) {
                drivers.put(i, new Driver(i, driverCapacities[i]));
            }

            // Create an entry for unassigned orders
            drivers.put(-1, new Driver(-1, 0)); // Driver ID -1 for unassigned orders

            // Assign orders to drivers
            for (int order : orders) {
                boolean assigned = false;
                for (Driver driver : drivers.values()) {
                    // Skip the placeholder for unassigned orders
                    if (driver.id == -1) continue;

                    // Check if the driver has enough remaining capacity
                    if (driver.remainingCapacity >= order) {
                        driver.assignedOrders.add(order);
                        driver.remainingCapacity -= order;
                        assigned = true;
                        break; // Stop once the order is assigned
                    }
                }

                // If no driver can accommodate the order, mark it as unassigned
                if (!assigned) {
                    drivers.get(-1).assignedOrders.add(order);
                }
            }

            return drivers;
        }
    }


