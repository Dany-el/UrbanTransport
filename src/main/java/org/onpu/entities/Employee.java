package org.onpu.entities;

import java.util.Objects;

public class Employee extends Person {
    private String nameOfOrganization;

    Employee() {
        super();
        nameOfOrganization = "Unemployed";
    }

    public Employee(Person person, String nameOfOrganization) {
        super(person);
        this.nameOfOrganization = nameOfOrganization;
    }

    public Employee(Person obj) {
        super(obj);
    }

    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }


    @Override
    public String toString() {
        return super.toString() + "\nOrganization: " + nameOfOrganization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        return Objects.equals(nameOfOrganization, employee.nameOfOrganization);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nameOfOrganization != null ? nameOfOrganization.hashCode() : 0);
        return result;
    }

}
