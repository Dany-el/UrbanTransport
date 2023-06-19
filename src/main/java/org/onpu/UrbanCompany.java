package org.onpu;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

// TODO Add file (byte-files) saving support

public class UrbanCompany {
    private Set<Transport> depot = new TreeSet<>();
    private Set<Driver> groupOfDrivers = new TreeSet<>();
    private Dispatcher dispatcher;
    private String nameOfCompany;

    UrbanCompany(String c) {
        nameOfCompany = c;
    }

    UrbanCompany(Set<Transport> depot, Set<Driver> groupOfDrivers, Dispatcher dispatcher) {
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

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.dispatcher.setNameOfOrganization(nameOfCompany);
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    /**
     * Add transport to depot
     *
     * @param t - transport to add to depot
     */
    public void addTransportToDepot(Transport t) {
        depot.add(t);
    }

    /**
     * Removes transport from depot
     *
     * @param t - transport to remove from depot
     */
    public void removeTransportFromDepot(Transport t) {
        try {
            depot.remove(t);
        } catch (Exception e) {
            System.out.println("Depot does not have this transport");
        }
    }

    /**
     * Adds object to the set
     *
     * @param d - driver to employ
     */
    public void employDriver(Driver d) {
        groupOfDrivers.add(d);
        d.setNameOfOrganization(nameOfCompany);
    }

    /**
     * Removes object from the set
     *
     * @param d - driver to fire
     */
    public void fireDriver(Driver d) {
        try {
            groupOfDrivers.remove(d);
            d.setNameOfOrganization("Unemployed");
            d.setRouteName("Undefined");
            d.setStartOfRoute(0, 0);
            d.setEndOfRoute(0, 0);
        }catch (Exception e){
            System.out.println("Group of drivers does not have this driver");
        }
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
        return groupOfDrivers.stream().toList().get(groupOfDrivers.size() - 1);
    }

    /**
     * Returns list of drivers with unique route
     *
     * @param r - route
     * @return list of drivers with unique route
     */
    public List<Driver> getDriversOfSpecificRoute(String r) {
        Set<Driver> specificRoute = new TreeSet<>(groupOfDrivers);
        return specificRoute.stream()
                .filter(d -> d.getRouteName().compareToIgnoreCase(r) == 0)
                .collect(Collectors.toList());
    }

    /**
     * Returns count of transports where the parameter is located at range of transport's begin and end time
     *
     * @param t - time, is used to count transports
     * @return count of transports
     */
    public long getCountOfTransportsAtTime(LocalTime t) {
        return depot.stream()
                .filter(transport ->
                        t.isAfter(transport.getDriver().getStartOfRoute()) &&
                                t.isBefore(transport.getDriver().getEndOfRoute()))
                .count();
    }


    // Temporary -------------------------------------------------------------------------------------------
    private void printDriverInfo(Driver d) {
        System.out.println("Full name: " + d.getSurname() + " " + d.getName() + " " + d.getPatronymic());
        System.out.println("Route: " + d.getRouteName());
    }

    public static void main(String[] args) throws Exception {
        UrbanCompany urbanCompany = new UrbanCompany("TransportCompany");

        Person person = new Person("Lan", "Konta", "Po",
                "8283791236", "Somewhere #1");

        Employee employee = new Employee(person);

        Driver driver1 = new Driver(employee, LocalDate.of(2009, 10, 18),
                LocalTime.of(7, 0), LocalTime.of(17, 30), "North-center", "543562");
        Driver driver2 = new Driver(employee, LocalDate.of(2006, 10, 18),
                LocalTime.of(8, 30), LocalTime.of(19, 30), "West-center", "543562");

        Transport transport1 = new Transport("Bus", "BH 7877 MV", "837463", driver1);
        Transport transport2 = new Transport("Bus", "BH 2757 MV", "287363", driver2);

        Dispatcher dispatcher = new Dispatcher(employee);

        urbanCompany.setDispatcher(dispatcher);

        urbanCompany.employDriver(driver1);
        urbanCompany.employDriver(driver2);

        urbanCompany.addTransportToDepot(transport1);
        urbanCompany.addTransportToDepot(transport2);

        System.out.println(urbanCompany.getAverageDriverWorkingTime());
        System.out.println(urbanCompany.getAverageLengthOfService());
        System.out.println(urbanCompany.getDriverWithGreatLengthOfService());

        for (Driver d :
                urbanCompany.getDriversOfSpecificRoute("north-center")) {
            System.out.println("------------------");
            urbanCompany.printDriverInfo(d);
            System.out.println("------------------");
        }

        System.out.println(urbanCompany.getCountOfTransportsAtTime(LocalTime.of(7, 10)));

        // Unemployment
        urbanCompany.fireDriver(driver1);
        System.out.println(driver1);

        System.out.println(dispatcher);
    }
}
