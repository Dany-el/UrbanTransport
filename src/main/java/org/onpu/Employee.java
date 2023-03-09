package org.onpu;

public class Employee extends Person {
    private String nameOfOrganization;

    Employee() {
        super();
        nameOfOrganization = "Undefined";
    }

    Employee(Person person, String nameOfOrganization) throws Exception {
        super(person);
        this.nameOfOrganization = nameOfOrganization;
    }

    Employee(Employee obj) {
        super(obj);
        nameOfOrganization = obj.nameOfOrganization;
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
