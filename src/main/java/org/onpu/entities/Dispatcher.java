package org.onpu.entities;

import java.time.LocalTime;

public class Dispatcher {
    /**
     * Returns changed Driver object route name
     *
     * @param driver object to change
     * @param route  new route name
     * @return changed Driver object route name
     * @throws NullPointerException if one of the arguments is null
     */
    public Driver changeRoute(Driver driver, String route) throws NullPointerException {
        if (driver != null && route != null) {
            driver.setRouteName(route);
        } else {
            throw new NullPointerException("Argument is null");
        }
        return driver;
    }

    /**
     * Returns changed Driver object start time
     *
     * @param driver object to change
     * @param startTime new start time
     * @return changed Driver object start time
     * @throws NullPointerException if first argument is null
     */
    public Driver changeRouteStartTime(Driver driver, LocalTime startTime) throws NullPointerException {
        if (driver != null) {
            driver.setStartOfRoute(startTime);
        } else {
            throw new NullPointerException("Argument of type Driver is null");
        }
        return driver;
    }

    /**
     * Returns changed Driver object end time
     *
     * @param driver object to change
     * @param endTime new end time
     * @return
     * @throws NullPointerException
     */
    public Driver changeRouteEndTime(Driver driver, LocalTime endTime) throws NullPointerException {
        if (driver != null) {
            driver.setEndOfRoute(endTime);
        } else {
            throw new NullPointerException("Argument of type Driver is null");
        }
        return driver;
    }

    /**
     * @param transport
     * @param driver
     * @return
     */
    public Transport changeDriver(Transport transport, Driver driver) {
        if (driver != null && transport != null) {
            if (transport.getDriver() != driver)
                transport.setDriver(driver);
        } else {
            throw new NullPointerException("Argument is null");
        }
        return transport;
    }
}
