package org.onpu;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws Exception {

        Person person = new Person("Danieru", "Oles", "Loes",
                "3290138213", "somewhere st.");
//        System.out.println(person);

        Employee employee = new Employee(person, "OdesTrans");
//        System.out.println(employee);

        Driver driver = new Driver(employee, LocalDate.of(2009, 10, 18),
                LocalTime.of(7,0), LocalTime.of(19,30), "North-center", "543562");
        System.out.println(driver);

        Transport transport = new Transport("Bus", "BH 7777 MV", "837463", driver);
//        System.out.println(transport);

        Dispatcher dispatcher = new Dispatcher(employee);
        driver = dispatcher.changeTimeRangeOfRoute(driver, LocalTime.of(8,30), LocalTime.of(20, 30));
        System.out.println(driver);

        /*transport = dispatcher.changeRouteName(transport, "South-center");
        driver = dispatcher.changeRouteName(driver, "North-South");
        System.out.println(transport);
        System.out.println(driver);
        transport.setDriver(driver);
        System.out.println(transport);*/
    }
}
