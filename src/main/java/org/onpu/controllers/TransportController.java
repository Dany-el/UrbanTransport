package org.onpu.controllers;

import org.onpu.entities.Driver;
import org.onpu.entities.Transport;
import org.onpu.serialization.FileSerialization;

import java.util.*;
import java.util.stream.Collectors;

public final class TransportController implements FileSerialization {
    private Set<Transport> transports;

    public TransportController() {
        transports = Objects.requireNonNullElseGet(
                (HashSet<Transport>) FileSerialization.readFromFile("transports"),
                HashSet::new);
    }

    /**
     * Returns sorted List
     *
     * @return sorted List by id
     */
    public List<Transport> getTransportsList() {
        return new ArrayList<>(new TreeSet<>(transports));
    }

    public void updateSerializedFile() {
        FileSerialization.saveToFile(transports, "transports");
    }

    /**
     * Returns an object of type Transport or null if an object does not exist
     *
     * @param id id(8-digits) to find an object
     * @return an object of type Transport or null if an object does not exist
     */
    public Transport getById(String id) {
        return transports.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds object Transport to the list if it's not null
     *
     * @param t object to add
     * @throws NullPointerException if an object is null
     */
    public void add(Transport t) throws NullPointerException {
        if (t == null) {
            throw new NullPointerException("Argument is null");
        }
        transports.add(t);
        updateSerializedFile();
    }

    /**
     * Removes object Transport from the list
     *
     * @param id id to find the object
     * @throws NullPointerException if object Transport was not found
     * @throws RuntimeException     if id has not been matched the pattern
     */
    public void remove(String id) throws RuntimeException, NullPointerException {
        Transport t = getById(id);
        if (t == null) {
            throw new NullPointerException("Transport was not found");
        }
        t.setDriver(null);

        Map<String, Transport> transportMap = transports.stream()
                .collect(Collectors.toMap(Transport::getId, transport -> transport));
        transportMap.remove(t.getId());
        transports = new HashSet<>(transportMap.values());

        transports.remove(t);
        updateSerializedFile();
    }
}
