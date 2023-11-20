package org.onpu;

import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.entities.TransportType;
import org.onpu.ui.graphics.main.MainFrame;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        Connector.initUrbanCompany();

        /*String driverIdPattern = "000000";
        String transportIdPattern = "00000000";

        addDrivers(50, driverIdPattern);
        addTransports(50, transportIdPattern);*/

        MainFrame.drawFrame();
    }

    public static void addDrivers(int size, String id) {
        for (int i = 1; i <= size; i++) {
            Driver d = new Driver();
            d.setName("name-" + i);
            d.setSurname("surname-" + i);
            d.setPatronymic("patronymic-" + i);
            d.setLivingAddress("liv-address-" + i);
            d.setPhoneNumber("0123456789");
            d.setStartOfCareer(LocalDate.of(1910 + i, 10, 10));
            d.setId(getId(id, i));
            d.setRouteName("route");
            d.setStartOfRoute(LocalTime.of(10, 0));
            d.setEndOfRoute(LocalTime.of(20, 30));
            Connector.getUrbanCompany().getDriverController().add(d);
        }
    }

    public static void addTransports(int size, String id) {
        for (int i = 1; i <= size; i++) {
            Transport t = new Transport();
            t.setType(TransportType.BUS);
            t.setLicence("XX 0000 XX");
            t.setId(getId(id, i));
            Connector.getUrbanCompany().getTransportController().add(t);
        }
    }

    static String getId(String pattern, int i){
        return usingSubstringMethod(pattern, pattern.length() - getIntLength(i)) + i;
    }

    static String usingSubstringMethod(String text, int length) {
        if (text.length() <= length) {
            return text;
        } else {
            return text.substring(0, length);
        }
    }

    static int getIntLength(int n) {
        int i = 0;
        while (n > 0) {
            n /= 10;
            i++;
        }
        return i;
    }
}