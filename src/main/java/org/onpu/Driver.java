package org.onpu;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver extends Employee {
    private LocalDate startOfCareer;
    private String startOfRoute;
    private String endOfRoute;
    private String routeName;
    private String id;

    Driver() {
        startOfCareer = LocalDate.of(2000, 1, 1);
        startOfRoute = "00:00";
        endOfRoute = "12:00";
        routeName = "Undefined";
        id = "Undefined";
    }

    Driver(Employee employee,
           LocalDate startOfCareer, String startOfRoute, String endOfRoute, String routeName, String id) throws Exception {
        super(employee);
        this.startOfCareer = startOfCareer;
        this.routeName = routeName;
        setStartOfRoute(startOfRoute);
        setEndOfRoute(endOfRoute);
        setId(id);
    }

    /**
     *
     * @param id 6-digit id
     * @throws Exception id length is less or greater than 6
     */
    public void setId(String id) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find())
            this.id = id;
        else
            throw new Exception("Invalid id");
    }

    /**
     * Sets time of starting the route
     * @param startOfRoute 24-time format (e.g. 00:00 - 23:59)
     * @throws Exception wrong typed time (e.g. 24:00, 7:30)
     */
    public void setStartOfRoute(String startOfRoute) throws Exception {
        Pattern pattern = Pattern.compile("^((2[0-3])|([0-1]\\d)):[0-5]\\d$");
        Matcher matcher = pattern.matcher(startOfRoute);
        if (matcher.find())
            this.startOfRoute = startOfRoute;
        else
            throw new Exception("Invalid time of start of the route");
    }

    /**
     * Sets time of ending the route
     * @param endOfRoute 24-time format (e.g. 00:00 - 23:59)
     * @throws Exception wrong typed time (e.g. 24:00, 7:30)
     */
    public void setEndOfRoute(String endOfRoute) throws Exception {
        Pattern pattern = Pattern.compile("^((2[0-3])|([0-1]\\d)):[0-5]\\d$");
        Matcher matcher = pattern.matcher(endOfRoute);
        if (matcher.find())
            this.endOfRoute = endOfRoute;
        else
            throw new Exception("Invalid time of end of the route");
    }

    /**
     * @return The length of service in years
     */
    public long getLengthOfService() {
        return ChronoUnit.YEARS.between(startOfCareer, LocalDate.now());
    }
}
