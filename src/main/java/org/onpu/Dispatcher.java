package org.onpu;

import java.time.LocalTime;

public class Dispatcher extends Employee {

    Dispatcher(Employee employee) {
        super(employee);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Changes route name of transport, driver is connected to transport, so driver's route is changed too
     *
     * @param transport transport which has driver
     * @param routeName route name
     * @return object with changed route name. 'null' if 'transport' is null
     */
    public Transport changeRouteName(Transport transport, String routeName) {
        if (transport.getDriver() != null) {
            Transport copy = new Transport(transport);
            copy.setRouteName(routeName);
            return copy;
        }
        return null;
    }

    /**
     * Returns an object with changed name of the route
     *
     * @param driver    - driver which will have changed name of the route
     * @param routeName - new route name
     * @return an object with changed name of the route
     */
    public Driver changeRouteName(Driver driver, String routeName) {
        if (driver == null) {
            return null;
        }
        Driver copy = new Driver(driver);
        copy.setRouteName(routeName);
        return copy;
    }

    /**
     * Returns an object with changed start time of the route
     *
     * @param driver       - driver which will have new start time of the route
     * @param startOfRoute - new time of starting of the route
     * @return an object with changed start time of the route
     * @throws Exception if start time is greater than start time
     */
    public Driver changeStartOfRoute(Driver driver, LocalTime startOfRoute) throws Exception {
        if (driver == null) {
            return null;
        }
        if (startOfRoute.isAfter(driver.getEndOfRoute()))
            throw new Exception("Start time should be lower than end time");
        Driver copy = new Driver(driver);
        copy.setStartOfRoute(startOfRoute);
        return copy;
    }

    /**
     * Returns an object with changed end time of the route
     *
     * @param driver     - driver which will have new end time of the route
     * @param endOfRoute - new end time of the route
     * @return an object with changed end time of the route
     * @throws Exception if end time is lower than start time
     */
    public Driver changeEndOfRoute(Driver driver, LocalTime endOfRoute) throws Exception {
        if (driver == null) {
            return null;
        }
        if (endOfRoute.isBefore(driver.getStartOfRoute()))
            throw new Exception("End time should be greater than start time");
        Driver copy = new Driver(driver);
        copy.setEndOfRoute(endOfRoute);
        return copy;
    }

    /**
     * Returns an object with changed time range of the route
     *
     * @param driver       - driver which will have new range of the time
     * @param startOfRoute - new start time of the route
     * @param endOfRoute   - new end time of the route
     * @return an object with changed time range of the route
     * @throws Exception if start time is greater than end time
     */
    public Driver changeTimeRangeOfRoute(Driver driver, LocalTime startOfRoute, LocalTime endOfRoute) throws Exception {
        if (driver == null) {
            return null;
        }
        if (startOfRoute.isAfter(endOfRoute))
            throw new Exception("Start time should be lower than end time");
        Driver copy = new Driver(driver);
        copy.setStartOfRoute(startOfRoute);
        copy.setEndOfRoute(endOfRoute);
        return copy;
    }
}
