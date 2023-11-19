package org.onpu.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Class that represents driver
 *
 * @author Daniel Yablonskyi
 * @version 1.0
 */
public class Driver extends Employee {
    private LocalDate startOfCareer = LocalDate.now();
    private LocalTime startOfRoute = LocalTime.MIN;
    private LocalTime endOfRoute = LocalTime.MAX;
    private String routeName;

    public Driver() {
        super();
        routeName = "Undefined";
    }

    public Driver(String name, String surname, String patronymic,
                  String phoneNumber, String livingAddress,
                  LocalDate startOfCareer,
                  LocalTime startOfRoute, LocalTime endOfRoute,
                  String routeName,
                  String id) throws RuntimeException {
        super(name, surname, patronymic, phoneNumber, livingAddress, id);
        setStartOfCareer(startOfCareer);
        this.routeName = routeName;
        setStartOfRoute(startOfRoute);
        setEndOfRoute(endOfRoute);
        setId(id);
    }

    public Driver(Employee employee,
                  LocalDate startOfCareer, LocalTime startOfRoute,
                  LocalTime endOfRoute, String routeName, String id) throws RuntimeException {
        super(employee);
        setStartOfCareer(startOfCareer);
        this.routeName = routeName;
        setStartOfRoute(startOfRoute);
        setEndOfRoute(endOfRoute);
        setId(id);
    }

    public Driver(Driver driver) {
        super(driver);
        if (driver != null) {
            routeName = driver.routeName;
            startOfCareer = driver.startOfCareer;
            startOfRoute = driver.startOfRoute;
            endOfRoute = driver.endOfRoute;
        }
    }

    /**
     * Sets the time of the start time
     *
     * @param startOfRoute 24-time format
     * @throws RuntimeException if start time is not before end time
     * @throws NullPointerException argument is null
     */
    public void setStartOfRoute(LocalTime startOfRoute) throws RuntimeException{
        if (startOfRoute != null) {
            if (startOfRoute.isBefore(endOfRoute)) {
                this.startOfRoute = startOfRoute;
            } else {
                throw new RuntimeException("Start time is not before end time");
            }
        } else {
            throw new NullPointerException("Argument type of LocalTime is null");
        }
    }

    /**
     * Sets the time of the end time
     *
     * @param endOfRoute 24-time format
     * @throws RuntimeException if end time is not after start time
     * @throws NullPointerException argument is null
     */
    public void setEndOfRoute(LocalTime endOfRoute) throws RuntimeException{
        if (endOfRoute != null) {
            if (endOfRoute.isAfter(startOfRoute)) {
                this.endOfRoute = endOfRoute;
            } else {
                throw new RuntimeException("End time is not after start time");
            }
        } else {
            throw new NullPointerException("Argument type of LocalTime is null");
        }
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     * Sets the career start date
     *
     * @param startOfCareer date that is more than 1 year apart from today's date
     * @throws RuntimeException if the length of service is less than 1 year
     */
    public void setStartOfCareer(LocalDate startOfCareer) throws RuntimeException {
        if (ChronoUnit.YEARS.between(startOfCareer, LocalDate.now()) > 1)
            this.startOfCareer = startOfCareer;
        else
            throw new RuntimeException("The length of service is less than 1 year");
    }

    public LocalDate getStartOfCareer() {
        return startOfCareer;
    }

    public LocalTime getStartOfRoute() {
        return startOfRoute;
    }

    public LocalTime getEndOfRoute() {
        return endOfRoute;
    }

    /**
     * Returns the length of service in years
     *
     * @return the length of service in years
     */
    public long getLengthOfService() {
        return ChronoUnit.YEARS.between(startOfCareer, LocalDate.now());
    }

    public String getRouteName() {
        return routeName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Driver driver = (Driver) object;

        if (!Objects.equals(startOfCareer, driver.startOfCareer))
            return false;
        if (!Objects.equals(startOfRoute, driver.startOfRoute))
            return false;
        if (!Objects.equals(endOfRoute, driver.endOfRoute)) return false;
        return Objects.equals(routeName, driver.routeName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (startOfCareer != null ? startOfCareer.hashCode() : 0);
        result = 31 * result + (startOfRoute != null ? startOfRoute.hashCode() : 0);
        result = 31 * result + (endOfRoute != null ? endOfRoute.hashCode() : 0);
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        return result;
    }
}
