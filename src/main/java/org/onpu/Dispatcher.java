package org.onpu;

import java.time.LocalTime;

public class Dispatcher extends Employee {

    Dispatcher() {
        super();
    }

    Dispatcher(Employee employee) throws Exception {
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
     * @return new object with copied data from parameter 'transport' of type Transport. 'null' if 'transport' is null
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
     * Creates new object of type 'Driver' and changes its route name
     *
     * @param driver    NotNull driver
     * @param routeName route name
     * @return new object of type 'Driver' with changed route name
     */
    public Driver changeRouteName(Driver driver, String routeName) {
        if (driver == null) {
            return null;    
        }
        Driver copy = new Driver(driver);
        copy.setRouteName(routeName);
        return copy;
    }

    public Driver changeStartOfRoute(Driver driver, LocalTime startOfRoute) {
        if (driver == null) {
            return null;
        }
        Driver copy = new Driver(driver);
        copy.setStartOfRoute(startOfRoute);
        return copy;
    }

    public Driver changeEndOfRoute(Driver driver, LocalTime endOfRoute) {
        if (driver == null) {
            return null;
        }
        Driver copy = new Driver(driver);
        copy.setEndOfRoute(endOfRoute);
        return copy;
    }

    public Driver changeTimeRangeOfRoute(Driver driver, LocalTime startOfRoute, LocalTime endOfRoute) {
        if (driver == null) {
            return null;
        }
        Driver copy = new Driver(driver);
        copy.setStartOfRoute(startOfRoute);
        copy.setEndOfRoute(endOfRoute);
        return copy;
    }

}
