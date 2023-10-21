package org.onpu.gui;

import org.onpu.entities.Driver;
import org.onpu.entities.Transport;

public class DataPanelController {
    private static TransportDataPanel transportPanelComponents;
    private static DriverDataPanel driverPanelComponents;

    public DataPanelController() {
        transportPanelComponents = new TransportDataPanel();
        driverPanelComponents = new DriverDataPanel();
        disableAllPanels();
    }

    public DriverDataPanel getDriverPanelComponents() {
        return driverPanelComponents;
    }

    public TransportDataPanel getTransportPanelComponents() {
        return transportPanelComponents;
    }

    public void enableTransportPanel() {
        driverPanelComponents.setVisible(false);
        transportPanelComponents.setVisible(true);
    }

    public void enableDriverPanel() {
        transportPanelComponents.setVisible(false);
        driverPanelComponents.setVisible(true);
    }

    public void disableAllPanels() {
        transportPanelComponents.setVisible(false);
        driverPanelComponents.setVisible(false);
    }

    public void setText(Driver driver) {
        driverPanelComponents.setFullName(driver.getFullName());
        driverPanelComponents.setLivingAddress(driver.getLivingAddress());
        driverPanelComponents.setPhoneNumber(driver.getPhoneNumber());
        driverPanelComponents.setId(driver.getId());
        driverPanelComponents.setStartOfCareer(
                driver.getStartOfCareer().toString() + " (" + driver.getLengthOfService() + " years)"
        );
        driverPanelComponents.setRouteName(driver.getRouteName());
        driverPanelComponents.setStartOfRoute(driver.getStartOfRoute().toString());
        driverPanelComponents.setEndOfRoute(driver.getEndOfRoute().toString());
    }

    public void setText(Transport transport) {
        transportPanelComponents.setDriverFullName(transport.getDriver().getFullName());
        transportPanelComponents.setDriverId(transport.getDriver().getId());
        transportPanelComponents.setRouteName(transport.getRouteName());
        transportPanelComponents.setType(transport.getType());
        transportPanelComponents.setNumber(transport.getNumber());
        transportPanelComponents.setId(transport.getId());
    }

    /*public void setText(String fullName, String livingAddress, String phoneNumber,
                        String id, String serviceLength, String routeName,
                        String startOfRoute, String endOfRoute) {
        driverPanelComponents.setFullName(fullName);
        driverPanelComponents.setLivingAddress(livingAddress);
        driverPanelComponents.setPhoneNumber(phoneNumber);
        driverPanelComponents.setId(id);
        driverPanelComponents.setStartOfCareer(serviceLength);
        driverPanelComponents.setRouteName(routeName);
        driverPanelComponents.setStartOfRoute(startOfRoute);
        driverPanelComponents.setEndOfRoute(endOfRoute);
    }

    public void setText(String driverFullName, String driverId, String route,
                        String type, String number,
                        String id) {
        transportPanelComponents.setDriverFullName(driverFullName);
        transportPanelComponents.setDriverId(driverId);
        transportPanelComponents.setRouteName(route);
        transportPanelComponents.setType(type);
        transportPanelComponents.setNumber(number);
        transportPanelComponents.setId(id);
    }*/
}
