package org.onpu;

import org.onpu.entities.*;

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UrbanCompany {
    private Set<Transport> depot;
    private Set<Driver> groupOfDrivers;
    private Dispatcher dispatcher;
    private String nameOfCompany;

    public UrbanCompany(String c) {
        nameOfCompany = c;
        readDepot();
        readGroupOfDrivers();
        readDispatcher();
    }

    public UrbanCompany(Set<Transport> depot, Set<Driver> groupOfDrivers, Dispatcher dispatcher) {
        this.depot = depot;
        this.groupOfDrivers = groupOfDrivers;
        this.dispatcher = dispatcher;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public List<Driver> getGroupOfDrivers() {
        return new ArrayList<>(groupOfDrivers);
    }

    public List<Transport> getDepot() {
        return new ArrayList<>(depot);
    }

    /**
     * Works as setter
     *
     * @param dispatcher object to be set as field dispatcher
     */
    public void employDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.dispatcher.setNameOfOrganization(nameOfCompany);
        saveDispatcher();
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    /**
     * Adds transport to depot
     *
     * @param t transport to add to depot
     */
    public void addTransportToDepot(Transport t) {
        depot.add(t);
        saveDepot();
    }

    /**
     * Removes transport from the depot
     *
     * @param t transport to remove from depot
     */
    public void removeTransportFromDepot(Transport t) {
        try {
            depot.remove(t);
        } catch (Exception e) {
            System.out.println("Depot does not have this transport");
        }
        saveDepot();
    }

    /**
     * Removes transport from the depot
     *
     * @param id id to find the transport in set
     * @throws Exception if id is not right written
     */
    public void removeTransportFromDepot(String id) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{8}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            depot = depot.stream()
                    .filter(t -> !t.getId().equals(id))
                    .collect(Collectors.toSet());
            saveDepot();
        } else throw new Exception("Invalid id");
    }

    /**
     * Adds driver to the set
     *
     * @param d driver to employ
     */
    public void employDriver(Driver d) {
        groupOfDrivers.add(d);
        d.setNameOfOrganization(nameOfCompany);
        saveGroupOfDrivers();
    }

    /**
     * Removes current driver from transport changing the object
     *
     * @param id id to find the object
     */
    private void removeDriverFromTransport(String id) {
        depot = depot.stream()
                .map(t -> {
                    if (Objects.equals(t.getDriver().getId(), id))
                        t.setDriver(new Driver());
                    return t;
                })
                .collect(Collectors.toSet());
        saveDepot();
    }

    /**
     * Removes driver from the set
     *
     * @param d driver to fire
     */
    public void fireDriver(Driver d) {
        try {
            groupOfDrivers.remove(d);
            d.setNameOfOrganization("Unemployed");
            d.setRouteName("Undefined");
            d.setStartOfRoute(0, 0);
            d.setEndOfRoute(0, 0);
            removeDriverFromTransport(d.getId());
        } catch (Exception e) {
            System.out.println("Group of drivers does not have this driver");
        }
        saveGroupOfDrivers();
    }

    /**
     * Removes driver from the set
     *
     * @param id id to find the driver in set
     * @throws Exception if id is not right written
     */
    public void fireDriver(String id) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            // There are always two collections, which equal and not equal to id
            // So we get which has true(is equal) and 'fire' it
            Map<Boolean, Set<Driver>> partitionedDrivers = groupOfDrivers.stream()
                    .collect(Collectors.partitioningBy(d -> d.getId().equals(id), Collectors.toSet()));

            partitionedDrivers.get(true).forEach(this::fireDriver);
            groupOfDrivers = partitionedDrivers.get(false);

            saveGroupOfDrivers();
        } else throw new Exception("Invalid id");
    }

    /**
     * Returns an average of drivers' working time
     *
     * @return average of drivers' working time. 0 - if set is empty
     */
    public double getAverageDriverWorkingTime() {
        return groupOfDrivers.stream()
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
        return groupOfDrivers.stream()
                .mapToDouble(Driver::getLengthOfService)
                .average()
                .orElse(0);
    }

    /**
     * Returns an object with a type Driver, which has the longest length of service
     *
     * @return object with a type Driver, which has the longest length of service
     */
    public Driver getDriverWithGreatLengthOfService() {
        // Sorting by comparing the length of service of driver
        return groupOfDrivers.stream()
                .toList()
                .get(groupOfDrivers.size() - 1);
    }

    /**
     * Returns a list of drivers with a given route
     *
     * @param r route
     * @return a list of drivers with a given route
     */
    public List<Driver> getDriversOfSpecificRoute(String r) {
        return groupOfDrivers.stream()
                .filter(d -> d.getRouteName().compareToIgnoreCase(r) == 0)
                .collect(Collectors.toList());
    }

    /**
     * Returns count of transports where the parameter is located at range of transport's begin and end time
     *
     * @param t time, is used to count transports
     * @return count of transports
     */
    public long getCountOfTransportsAtTime(LocalTime t) {
        return depot.stream()
                .filter(transport ->
                        t.isAfter(transport.getDriver().getStartOfRoute()) &&
                                t.isBefore(transport.getDriver().getEndOfRoute()))
                .count();
    }

    /**
     * Gets Driver object by ID
     *
     * @param id id to find the object
     * @return Driver object or null if object was not found
     */
    public Driver getDriverBy(String id) {
        return groupOfDrivers.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets Transport object by ID
     *
     * @param id id to find the object
     * @return Transport object or null if object was not found
     */
    public Transport getTransportBy(String id) {
        return depot.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Saves Set<Transport> in depot.ser using serialization
     */
    public void saveDepot() {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/resources/saves/depot.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(depot);
            oos.close();
            fos.close();
            System.out.println("Serialized data is saved in depot.ser");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Saves Set<Driver> in drivers.ser using serialization
     */
    public void saveGroupOfDrivers() {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/resources/saves/drivers.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(groupOfDrivers);
            oos.close();
            fos.close();
            System.out.println("Serialized data is saved in drivers.ser");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Saves Dispatcher in dispatcher.ser using serialization
     */
    public void saveDispatcher() {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/resources/saves/dispatcher.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dispatcher);
            oos.close();
            fos.close();
            System.out.println("Serialized data is saved in dispatcher.ser");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Reads depot.ser and initializes Set<Transport> with new object
     */
    private void readDepot() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/saves/depot.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            if (object != null)
                depot = (Set<Transport>) object;
            ois.close();
            fis.close();
            System.out.println("Depot.ser was successfully read");
        } catch (Exception e) {
            depot = new TreeSet<>();
        }
    }

    /**
     * Reads drivers.ser and initializes Set<Driver> with new object
     */
    private void readGroupOfDrivers() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/saves/drivers.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            if (object != null)
                groupOfDrivers = (Set<Driver>) object;
            ois.close();
            fis.close();
            System.out.println("Drivers.ser was successfully read");
        } catch (Exception e) {
            groupOfDrivers = new TreeSet<>();
        }
    }

    /**
     * Reads dispatcher.ser and initializes Dispatcher with new object
     */
    private void readDispatcher() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/saves/dispatcher.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            if (object != null)
                dispatcher = (Dispatcher) object;
            ois.close();
            fis.close();
            System.out.println("Drivers.ser was successfully read");
        } catch (Exception ignored) {

        }
    }
}
