package org.onpu;

import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.entities.UrbanCompany;

import java.util.List;

public final class Connector {
    private static UrbanCompany urbanCompany = new UrbanCompany("Urban Corp.");

    public static UrbanCompany getUrbanCompany() {
        return urbanCompany;
    }

    public static List<Driver> getDriverList() {
        return urbanCompany.getDriverController().getDriversList();
    }

    public static List<Transport> getTransportList() {
        return urbanCompany.getTransportController().getTransportsList();
    }
}
