package org.onpu.entities;

import java.io.Serializable;

public enum TransportType implements Serializable {
    BUS("Bus"),
    SHUTTLE_BUS("Shuttle bus"),
    TRAM("Tram"),
    FUNICULAR("Funicular"),
    TROLLEYBUS("Trolleybus"),
    SUBWAY("Subway"),
    UNDEFINED("Undefined");

    private final String value;

    TransportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
