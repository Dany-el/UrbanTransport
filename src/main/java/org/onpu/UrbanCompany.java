package org.onpu;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UrbanCompany {
    private List<Transport> depot = new ArrayList<>();
    private List<Driver> groupOfDrivers = new ArrayList<>();
    private Dispatcher dispatcher;
    private String nameOfCompany;

    UrbanCompany(String c) {
        nameOfCompany = c;
    }

    UrbanCompany(List<Transport> depot, List<Driver> groupOfDrivers, Dispatcher dispatcher) {
        this.depot = depot;
        this.groupOfDrivers = groupOfDrivers;
        this.dispatcher = dispatcher;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public List<Driver> getGroupOfDrivers() {
        return groupOfDrivers;
    }

    public List<Transport> getDepot() {
        return depot;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.dispatcher.setNameOfOrganization(nameOfCompany);
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void addTransportToDepot(Transport t) {
        depot.add(t);
    }

    public void removeTransportFromDepot(Transport t) {
        depot.remove(t);
    }

    public void employDriver(Driver d) {
        groupOfDrivers.add(d);
        d.setNameOfOrganization(nameOfCompany);
    }

    public void fireDriver(Driver d) {
        groupOfDrivers.remove(d);
        d.setNameOfOrganization("Unemployed");
    }

    public double getAverageDriverWorkingTime() {
        // In hours
        double sumOfTimeSubtraction = 0;

        for (Driver driver :
                groupOfDrivers) {
            Duration duration = Duration.between(driver.getStartOfRoute(), driver.getEndOfRoute());
            sumOfTimeSubtraction += duration.toHours();
        }

        return sumOfTimeSubtraction / groupOfDrivers.size();
    }

    public double getAverageLengthOfService() {
        double sumOfLengthOfService = 0;

        for (Driver driver :
                groupOfDrivers) {
            sumOfLengthOfService += driver.getLengthOfService();
        }

        return sumOfLengthOfService / groupOfDrivers.size();
    }

    public Driver getDriverWithGreatLengthOfService() {
        // Sorting by comparing the length of service of driver
        groupOfDrivers.sort(Driver::compareTo);
        return groupOfDrivers.get(groupOfDrivers.size() - 1);
    }

    /**
     * Returns list of drivers with unique route
     * @param r - route
     * @return list of drivers with unique route
     */
    public List<Driver> getDriversOfSpecificRoute(String r) {
        List<Driver> specificRoute = new ArrayList<>();

        for (Driver driver :
                groupOfDrivers) {
            if (driver.getRouteName().compareToIgnoreCase(r) == 0) specificRoute.add(driver);
        }

        return specificRoute;
    }

    /**
     * Returns count of transports where the parameter is located at range of transport's begin and end time
     * @param t - time, is used to count transports
     * @return count of transports
     */
    public int getCountOfTransportsAtTime(LocalTime t) {
        int counter = 0;
        for (Transport transport :
                depot) {
            // check if time in range
            if (t.isAfter(transport.getDriver().getStartOfRoute()) && t.isBefore(transport.getDriver().getEndOfRoute()))
                ++counter;
        }

        return counter;
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

        System.out.println(urbanCompany.getCountOfTransportsAtTime(LocalTime.of(14, 10)));

        // Unemployment
        urbanCompany.fireDriver(driver1);
        System.out.println(driver1);

        System.out.println(dispatcher);
    }
}
