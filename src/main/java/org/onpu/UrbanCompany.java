package org.onpu;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// TODO Add possibility to fire driver and remove transport by id

public class UrbanCompany {
    private Set<Transport> depot = new TreeSet<>();
    private Set<Driver> groupOfDrivers = new TreeSet<>();
    private Dispatcher dispatcher;
    private String nameOfCompany;

    UrbanCompany(String c) {
        nameOfCompany = c;
        readDepot();
        readGroupOfDrivers();
        readDispatcher();
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
     * @param id id to find the transport in set
     * @throws Exception if id is not right written
     */
    public void removeTransportFromDepot(String id) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{6}$");
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
        } catch (Exception e) {
            System.out.println("Group of drivers does not have this driver");
        }
        saveGroupOfDrivers();
    }

    /**
     * Removes driver from the set
     * @param id id to find the driver in set
     * @throws Exception if id is not right written
     */
    public void fireDriver(String id) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            groupOfDrivers = groupOfDrivers.stream()
                    .filter(d -> !d.getId().equals(id))
                    .collect(Collectors.toSet());
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
        return groupOfDrivers.stream().toList().get(groupOfDrivers.size() - 1);
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
     * Saves Set<Transport> in depot.ser using serialization
     */
    private void saveDepot() {
        try {
            FileOutputStream fos = new FileOutputStream("depot.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(depot);
            oos.close();
            fos.close();
            System.out.println("Serialized data is saved in depot.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves Set<Driver> in drivers.ser using serialization
     */
    private void saveGroupOfDrivers() {
        try {
            FileOutputStream fos = new FileOutputStream("drivers.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(groupOfDrivers);
            oos.close();
            fos.close();
            System.out.println("Serialized data is saved in drivers.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves Dispatcher in dispatcher.ser using serialization
     */
    private void saveDispatcher() {
        try {
            FileOutputStream fos = new FileOutputStream("dispatcher.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dispatcher);
            oos.close();
            fos.close();
            System.out.println("Serialized data is saved in dispatcher.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads depot.ser and initializes Set<Transport> with new object
     */
    private void readDepot() {
        try {
            FileInputStream fis = new FileInputStream("depot.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            if (object != null)
                depot = (Set<Transport>) object;
            ois.close();
            fis.close();
            System.out.println("Depot.ser was successfully read");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads drivers.ser and initializes Set<Driver> with new object
     */
    private void readGroupOfDrivers() {
        try {
            FileInputStream fis = new FileInputStream("drivers.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            if (object != null)
                groupOfDrivers = (Set<Driver>) object;
            ois.close();
            fis.close();
            System.out.println("Drivers.ser was successfully read");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads dispatcher.ser and initializes Dispatcher with new object
     */
    private void readDispatcher() {
        try {
            FileInputStream fis = new FileInputStream("dispatcher.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            if (object != null)
                dispatcher = (Dispatcher) object;
            ois.close();
            fis.close();
            System.out.println("Drivers.ser was successfully read");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*// Temporary -------------------------------------------------------------------------------------------
    private void printDriverInfo(Driver d) {
        System.out.println("Full name: " + d.getSurname() + " " + d.getName() + " " + d.getPatronymic());
        System.out.println("Route: " + d.getRouteName());
    }
    // -----------------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws Exception {
        UrbanCompany urbanCompany = new UrbanCompany("TransportCompany");
//        urbanCompany.removeTransportFromDepot("837463");
        System.out.println(urbanCompany.getDepot());
//        urbanCompany.fireDriver("543662");
        System.out.println(urbanCompany.getGroupOfDrivers());

        /*Person person = new Person("Lan", "Konta", "Po",
                "8283791236", "Somewhere #1");

        Employee employee = new Employee(person);

        Driver driver1 = new Driver(employee, LocalDate.of(2012, 3, 9),
                LocalTime.of(6, 30), LocalTime.of(20, 30), "West-North", "392884");
        Driver driver2 = new Driver(employee, LocalDate.of(2006, 10, 18),
                LocalTime.of(8, 30), LocalTime.of(19, 30), "West-center", "543562");

        Transport transport1 = new Transport("Bus", "BH 7877 MV", "837463", driver1);
        Transport transport2 = new Transport("Bus", "BH 2757 MV", "287363", driver2);

        Dispatcher dispatcher = new Dispatcher(employee);

        urbanCompany.employDispatcher(dispatcher);

        urbanCompany.employDriver(driver1);
        urbanCompany.employDriver(driver2);

        urbanCompany.addTransportToDepot(transport1);
        urbanCompany.addTransportToDepot(transport2);*/

//        urbanCompany.employDriver(driver1);

        /*System.out.println("Average working time: " + urbanCompany.getAverageDriverWorkingTime());
        System.out.println("Average length of service: " + urbanCompany.getAverageLengthOfService());
        System.out.println("Driver with greatest length of service: " + urbanCompany.getDriverWithGreatLengthOfService());

        System.out.println(urbanCompany.getDriversOfSpecificRoute("north-center"));

        System.out.println("\nTransports at given time: " + urbanCompany.getCountOfTransportsAtTime(LocalTime.of(7, 10)));
*/
        // Unemployment
        /*urbanCompany.fireDriver(driver1);
        System.out.println(driver1);*/

        /*System.out.println(urbanCompany.getDepot());
        System.out.println(urbanCompany.getGroupOfDrivers());
        System.out.println(urbanCompany.getDispatcher());*/
    }
}
