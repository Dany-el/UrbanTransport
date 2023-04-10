package org.onpu;

public class Employee extends Person {
    private String nameOfOrganization;

    Employee() {
        super();
        nameOfOrganization = "Unemployed";
    }

    Employee(Person person, String nameOfOrganization) throws Exception {
        super(person);
        this.nameOfOrganization = nameOfOrganization;
    }

    Employee(Person obj) {
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
    public void printInfo() {
        System.out.println(this);
    }
}
