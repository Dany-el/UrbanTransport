package org.onpu.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents transport
 *
 * @author Daniel Yablonskyi
 * @version 1.0
 */
public class Transport implements Comparable<Transport>, Comparator<Transport>, Serializable {
    private Driver driver;
    private TransportType type;
    private String routeName;
    private String licence;
    private String id;

    public Transport() {
        type = TransportType.UNDEFINED;
        routeName = "Undefined";
        licence = "Undefined";
        id = "Undefined";
    }

    public Transport(TransportType type, String licence, String id) throws RuntimeException {
        this.type = type;
        this.routeName = "Undefined";
        setLicence(licence);
        setId(id);
    }

    public Transport(Transport transport) {
        if (transport != null) {
            type = transport.type;
            licence = transport.licence;
            id = transport.id;
            setDriver(transport.getDriver());
        }
    }

    /**
     * @param licence requires this type of format - 2 letters 4 digits 2 letters (e.g. AA 1234 AA)
     * @throws RuntimeException if licence has not been matched the pattern
     */
    public void setLicence(String licence) throws RuntimeException {
        Pattern pattern = Pattern.compile("^([a-zA-Z]{2})\\s(\\d{4})\\s([a-zA-Z]{2})$");
        Matcher matcher = pattern.matcher(licence);
        if (matcher.find())
            this.licence = licence;
        else
            throw new RuntimeException("Invalid licence");
    }

    /**
     * @param id 8-digit id (12345678)
     * @throws RuntimeException if id has not been matched the pattern
     */
    public void setId(String id) throws RuntimeException {
        Pattern pattern = Pattern.compile("^\\d{8}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find())
            this.id = id;
        else
            throw new RuntimeException("Invalid id");
    }

    public void setDriver(Driver driver) {
        if (this.driver != driver && driver != null) {
            this.driver = driver;
            this.routeName = driver.getRouteName();
        } else if (driver == null) {
            this.driver = null;
            this.routeName = "Undefined";
        }
    }

    public void setRouteName(String routeName) {
        if (routeName.equals(driver.getRouteName()))
            this.routeName = routeName;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getId() {
        return id;
    }

    public String getLicence() {
        return licence;
    }

    public TransportType getType() {
        return type;
    }

    public String getRouteName() {
        return routeName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Transport transport = (Transport) object;

        if (!Objects.equals(driver, transport.driver)) return false;
        if (!Objects.equals(type, transport.type)) return false;
        if (!Objects.equals(routeName, transport.routeName)) return false;
        if (!Objects.equals(licence, transport.licence)) return false;
        return Objects.equals(id, transport.id);
    }

    @Override
    public int hashCode() {
        int result = driver != null ? driver.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        result = 31 * result + (licence != null ? licence.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public int compare(Transport o1, Transport o2) {
        return o1.id.compareTo(o2.id);
    }

    @Override
    public int compareTo(Transport o) {
        return this.id.compareTo(o.id);
    }
}
