package org.onpu.controllers;

import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.serialization.FileSerialization;

import java.time.LocalTime;
import java.util.*;

public final class DriverController implements FileSerialization {
    private final Set<Driver> drivers;
    private final TransportController transportController;

    public DriverController(TransportController tc) {
        drivers = Objects.requireNonNullElseGet(
                (HashSet<Driver>) FileSerialization.readFromFile("drivers"),
                HashSet::new);
        transportController = tc;
    }

    /**
     * Returns sorted List
     *
     * @return sorted List by id
     */
    public List<Driver> getDriversList() {
        return new ArrayList<>(new TreeSet<>(drivers));
    }

    /**
     *
     */
    public void updateSerializedFile() {
        FileSerialization.saveToFile(drivers, "drivers");
    }

    /**
     * Returns an object of type Driver or null if an object does not exist
     *
     * @param id id(6-digits) to find an object
     * @return an object of type Driver or null if an object does not exist
     */
    public Driver getById(String id) {
        return drivers.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns object Transport or null if an object does not exist with this Driver id
     *
     * @param id Driver id
     * @return object Transport or null if an object does not exist with this Driver id
     */
    public Transport getTransportByDriverId(String id) {
        return transportController.getTransportsList().stream()
                .filter(t -> t.getDriver().getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds object Driver to the list if it's not null
     *
     * @param d object to add
     * @throws NullPointerException if an object is null
     */
    public void add(Driver d) throws NullPointerException {
        if (d == null) {
            throw new NullPointerException("Argument is null");
        }
        drivers.add(d);
        updateSerializedFile();
    }

    /**
     * Removes object Driver from the list
     *
     * @param id id to find the object
     * @throws RuntimeException     if object Driver was not found
     * @throws NullPointerException if id has not been matched the pattern
     */
    public void remove(String id) throws NullPointerException, RuntimeException {
        Driver d = getById(id);
        if (d == null) {
            throw new NullPointerException("Driver was not found");
        }
        drivers.remove(d);

        d.setNameOfOrganization("Unemployed");
        d.setRouteName("Undefined");
        d.setStartOfRoute(LocalTime.MIN);
        d.setEndOfRoute(LocalTime.MAX);

        Transport transport = getTransportByDriverId(id);
        if (transport != null) {
            transport.setDriver(null);
        }

        updateSerializedFile();
    }
}
