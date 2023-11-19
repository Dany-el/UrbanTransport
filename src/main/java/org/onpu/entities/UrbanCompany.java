package org.onpu.entities;

import org.onpu.controllers.DriverController;
import org.onpu.controllers.TransportController;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UrbanCompany {
    private final DriverController driverController;
    private final TransportController transportController;
    private final Dispatcher dispatcher;
    private String nameOfCompany;

    public UrbanCompany(String c) {
        nameOfCompany = c;
        dispatcher = new Dispatcher();
        transportController = new TransportController();
        driverController = new DriverController(transportController);
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public DriverController getDriverController() {
        return driverController;
    }

    public TransportController getTransportController() {
        return transportController;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    /**
     * Returns an average of drivers' working time
     *
     * @return average of drivers' working time. 0 - if set is empty
     */
    public double getAverageDriverWorkingTime() {
        return driverController.getDriversList().stream()
                .mapToLong(d -> {
                    Duration duration = Duration.between(d.getStartOfRoute(), d.getEndOfRoute());
                    return duration.toHours();
                })
                .average()
                .orElse(0);
    }

    /**
     * Returns an average of drivers' length of service
     *
     * @return average of drivers' length of service, 0 - if set is empty
     */
    public double getAverageLengthOfService() {
        return driverController.getDriversList().stream()
                .mapToDouble(Driver::getLengthOfService)
                .average()
                .orElse(0);
    }

    /**
     * Returns an object of type Driver, which has the longest length of service
     *
     * @return an object of type Driver, which has the longest length of service
     */
    public Driver getDriverWithGreatLengthOfService() {
        return driverController.getDriversList().stream()
                .max(Driver::compareTo)
                .get();
    }

    /**
     * Returns a list of drivers of the given route
     *
     * @param r route
     * @return a list of drivers of the given route
     */
    public List<Driver> getDriversOfSpecificRoute(String r) {
        return driverController.getDriversList().stream()
                .filter(d -> d.getRouteName().compareToIgnoreCase(r) == 0)
                .collect(Collectors.toList());
    }

    /**
     * Returns count of transports where the parameter fits the time range
     *
     * @param time time, is used to count transports
     * @return count of transports where the parameter fits the time range
     */
    public long getCountOfTransportsAtTime(LocalTime time) {
        return transportController.getTransportsList().stream()
                .filter(t ->
                        time.isAfter(t.getDriver().getStartOfRoute()) &&
                                time.isBefore(t.getDriver().getEndOfRoute()))
                .count();
    }
}