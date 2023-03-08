package org.onpu;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Driver driver = new Driver();
        try{
            person.setPhoneNumber("1234567890");
            driver.setId("123456");
            driver.setStartOfRoute("08:30");
            driver.setEndOfRoute("20:30");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}