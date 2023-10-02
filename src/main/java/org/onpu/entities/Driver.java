package org.onpu.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver extends Employee implements Comparable<Driver> {
    private LocalDate startOfCareer;
    private LocalTime startOfRoute;
    private LocalTime endOfRoute;
    private String routeName;
    private String id;

    public Driver() {
        startOfCareer = null;
        startOfRoute = null;
        endOfRoute = null;
        routeName = "Undefined";
        id = "Undefined";
    }

    public Driver(Employee employee, LocalDate startOfCareer, LocalTime startOfRoute, LocalTime endOfRoute, String routeName, String id) throws Exception {
        super(employee);
        this.startOfCareer = startOfCareer;
        this.routeName = routeName;
        this.startOfRoute = startOfRoute;
        this.endOfRoute = endOfRoute;
        setId(id);
    }

    public Driver(Driver driver) {
        super(driver);
        startOfCareer = driver.startOfCareer;
        routeName = driver.routeName;
        startOfRoute = driver.startOfRoute;
        endOfRoute = driver.endOfRoute;
        id = driver.id;
    }

    /**
     * @param id 6-digit id
     * @throws Exception id length is less or greater than 6
     */
    public void setId(String id) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) this.id = id;
        else throw new Exception("Invalid id");
    }

    /**
     * Sets the time of starting the route
     *
     * @param startOfRoute 24-time format
     */
    public void setStartOfRoute(LocalTime startOfRoute) {
        this.startOfRoute = startOfRoute;
    }

    /**
     * Sets the time of starting the route
     */
    public void setStartOfRoute(int hour, int minute) {
        startOfRoute = LocalTime.of(hour, minute);
    }

    /**
     * Sets the time of ending the route
     *
     * @param endOfRoute 24-time format
     */
    public void setEndOfRoute(LocalTime endOfRoute) {
        this.endOfRoute = endOfRoute;
    }

    /**
     * Sets the time of ending the route
     */
    public void setEndOfRoute(int hour, int minute) {
        endOfRoute = LocalTime.of(hour, minute);
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setStartOfCareer(LocalDate startOfCareer) {
        this.startOfCareer = startOfCareer;
    }

    public LocalTime getStartOfRoute() {
        return startOfRoute;
    }

    public LocalTime getEndOfRoute() {
        return endOfRoute;
    }

    /**
     * @return The length of service in years
     */
    public long getLengthOfService() {
        return ChronoUnit.YEARS.between(startOfCareer, LocalDate.now());
    }

    public String getRouteName() {
        return routeName;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString() + "\nId: " + id + "\nService length: " + getLengthOfService() + "\nRoute \"" + routeName + "\"" + "\nFrom " + startOfRoute + " to " + endOfRoute;
    }

    @Override
    public int compareTo(Driver o) {
        return Long.compare(getLengthOfService(), o.getLengthOfService());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Driver driver = (Driver) o;

        if (!Objects.equals(startOfCareer, driver.startOfCareer))
            return false;
        if (!Objects.equals(startOfRoute, driver.startOfRoute))
            return false;
        if (!Objects.equals(endOfRoute, driver.endOfRoute)) return false;
        if (!Objects.equals(routeName, driver.routeName)) return false;
        return Objects.equals(id, driver.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (startOfCareer != null ? startOfCareer.hashCode() : 0);
        result = 31 * result + (startOfRoute != null ? startOfRoute.hashCode() : 0);
        result = 31 * result + (endOfRoute != null ? endOfRoute.hashCode() : 0);
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
