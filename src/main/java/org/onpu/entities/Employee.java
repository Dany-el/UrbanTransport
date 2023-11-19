package org.onpu.entities;

import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents employee
 *
 * @author Daniel Yablonskyi
 * @version 1.0
 */
public abstract class Employee extends Person implements Comparator<Employee>, Comparable<Employee> {
    private String nameOfOrganization;
    private String id;

    public Employee() {
        super();
        nameOfOrganization = "Unemployed";
        id = "Undefined";
    }

    public Employee(String name, String surname, String patronymic,
                    String phoneNumber, String livingAddress,
                    String id) throws RuntimeException {
        super(name, surname, patronymic, phoneNumber, livingAddress);
        setNameOfOrganization("Unemployed");
        setId(id);
    }

    public Employee(Employee obj) {
        super(obj);
        if (obj != null) {
            this.id = obj.id;
            this.nameOfOrganization = obj.nameOfOrganization;
        }
    }

    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }

    /**
     * Sets the id. The id must have 6 digits innit
     *
     * @param id the id to set
     * @throws RuntimeException if the id has not been matched the pattern
     */
    public void setId(String id) throws RuntimeException {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            this.id = id;
        } else {
            throw new RuntimeException("Id has not been matched the pattern");
        }
    }

    public String getId() {
        return id;
    }

    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        Employee employee = (Employee) object;

        if (!Objects.equals(nameOfOrganization, employee.nameOfOrganization))
            return false;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nameOfOrganization != null ? nameOfOrganization.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.id.compareTo(o2.id);
    }

    @Override
    public int compareTo(Employee o) {
        return id.compareTo(o.id);
    }
}
