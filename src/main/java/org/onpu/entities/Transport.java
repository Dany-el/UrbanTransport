package org.onpu.entities;

import org.onpu.Printable;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transport implements Printable, Comparable<Transport>, Serializable {
    private Driver driver;
    private String type;
    private String routeName;
    private String number;
    private String id;

    public Transport() {
        type = "Undefined";
        routeName = "Undefined";
        number = "Undefined";
        id = "Undefined";
    }

    public Transport(String type, String number, String id, Driver driver) throws Exception {
        this.type = type;
        this.routeName = driver.getRouteName();
        this.driver = driver;
        setNumber(number);
        setId(id);
    }

    // really useful?
    // ---------------------------------------

    public Transport(String type, String number, String id) throws Exception {
        this.type = type;
        setNumber(number);
        setId(id);
    }

    public Transport(Driver driver) {
        this.driver = driver;
        this.routeName = driver.getRouteName();
    }

    // ------------------------------------------

    public Transport(Transport transport) {
        type = transport.type;
        number = transport.number;
        id = transport.id;
        setDriver(transport.getDriver());
    }

    /**
     * @param number requires this type of format - 2 letters 4 digits 2 letters (e.g. AA 1234 AA)
     * @throws Exception not required this type of format
     */
    public void setNumber(String number) throws Exception {
        Pattern pattern = Pattern.compile("^([a-zA-Z]{2})\\s(\\d{4})\\s([a-zA-Z]{2})$");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find())
            this.number = number;
        else
            throw new Exception("Invalid number");
    }

    /**
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

    public void setRouteName(String routeName) {
        this.routeName = routeName;
        driver.setRouteName(routeName);
    }

    public void setDriver(Driver driver) {
        if (this.driver != driver) {
            this.driver = driver;
            this.routeName = driver.getRouteName();
        }
    }

    public Driver getDriver() {
        return driver;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nType  : " + type +
                "\nNumber: " + number +
                "\nId    : " + id +
                "\nDriver initials: " +
                driver.getName() + " " + driver.getSurname() + " " + driver.getPatronymic() + " " +
                "(" + driver.getId() + ")" +
                "\nRoute \"" + routeName + "\"";
    }

    @Override
    public int compareTo(Transport o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        if (!Objects.equals(driver, transport.driver)) return false;
        if (!Objects.equals(type, transport.type)) return false;
        if (!Objects.equals(routeName, transport.routeName)) return false;
        if (!Objects.equals(number, transport.number)) return false;
        return Objects.equals(id, transport.id);
    }

    @Override
    public int hashCode() {
        int result = driver != null ? driver.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
