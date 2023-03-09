package org.onpu;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        Person person = new Person("Daniel", "Yablonskyi", "Olexandrovich",
                "0689782508", "Manezna st. #20");
//        System.out.println(person);

        Employee employee = new Employee(person, "OdesTrans");
//        System.out.println(employee);

        Driver driver = new Driver(employee, LocalDate.of(2009, 10, 18),
                "07:00", "21:00", "North-center", "543562");
        System.out.println(driver);

        Transport transport = new Transport("Bus", "BH 7777 MV", "837463", driver);
//        System.out.println(transport);

        Dispatcher dispatcher = new Dispatcher(employee);
        driver = dispatcher.changeTimeRangeOfRoute(driver, "08:30", "19:30");
        System.out.println(driver);

        /*transport = dispatcher.changeRouteName(transport, "South-center");
        driver = dispatcher.changeRouteName(driver, "North-South");
        System.out.println(transport);
        System.out.println(driver);
        transport.setDriver(driver);
        System.out.println(transport);*/
    }
}