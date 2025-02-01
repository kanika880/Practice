import java.time.*;
import java.util.*;

public class DeliveryDateCalculator {

    public static void main(String[] args) {
        // Example Input
        LocalDate orderDate = LocalDate.of(2025, 1, 23); // Order placed on Jan 23, 2025
        int processingTime = 2; // 2 days to process the order
        int transitTime = 3; // 3 days to deliver based on distance
        Set<LocalDate> holidays = Set.of(
                LocalDate.of(2025, 1, 26), // Public holiday
                LocalDate.of(2025, 2, 1)  // Another holiday
        );
        Map<LocalDate, Integer> driverCapacity = new HashMap<>();
        driverCapacity.put(LocalDate.of(2025, 1, 25), 20); // 20 kg capacity on Jan 25
        driverCapacity.put(LocalDate.of(2025, 1, 27), 15); // 15 kg capacity on Jan 27

        int orderWeight = 10; // Example order weight

        // Calculate Delivery Date
        LocalDate deliveryDate = calculateDeliveryDate(
                orderDate, processingTime, transitTime, holidays, driverCapacity, orderWeight
        );

        // Output
        if (deliveryDate != null) {
            System.out.println("Estimated Delivery Date: " + deliveryDate);
        } else {
            System.out.println("The order cannot be delivered due to insufficient capacity or conflicts.");
        }
    }

    /**
     * Calculate the delivery date based on input parameters.
     *
     * @param orderDate       Date when the order is placed
     * @param processingTime  Days required to process the order
     * @param transitTime     Days required for delivery transit
     * @param holidays        Set of non-working holidays
     * @param driverCapacity  Map of date to available driver capacity
     * @param orderWeight     Weight of the order
     * @return Estimated delivery date or null if not possible
     */
    public static LocalDate calculateDeliveryDate(
            LocalDate orderDate,
            int processingTime,
            int transitTime,
            Set<LocalDate> holidays,
            Map<LocalDate, Integer> driverCapacity,
            int orderWeight
    ) {
        // Step 1: Calculate Dispatch Date
        LocalDate dispatchDate = orderDate.plusDays(processingTime);

        // Step 2: Estimate Initial Delivery Date
        LocalDate estimatedDeliveryDate = dispatchDate.plusDays(transitTime);

        // Step 3: Adjust Delivery Date for Holidays and Capacity
        while (true) {
            // Check for holidays
            if (holidays.contains(estimatedDeliveryDate)) {
                estimatedDeliveryDate = estimatedDeliveryDate.plusDays(1);
                continue;
            }

            // Check for driver availability and sufficient capacity
            int availableCapacity = driverCapacity.getOrDefault(estimatedDeliveryDate, 0);
            if (availableCapacity >= orderWeight) {
                // Assign order and reduce the capacity for the driver
                driverCapacity.put(estimatedDeliveryDate, availableCapacity - orderWeight);
                break; // Valid delivery date found
            }

            // Move to the next day if capacity is insufficient
            estimatedDeliveryDate = estimatedDeliveryDate.plusDays(1);

            // Safety check: If estimatedDeliveryDate exceeds a certain limit, return null
            if (estimatedDeliveryDate.isAfter(orderDate.plusDays(30))) {
                // Avoid infinite loops by setting a max delivery range (30 days here)
                return null;
            }
        }

        return estimatedDeliveryDate;
    }
}
