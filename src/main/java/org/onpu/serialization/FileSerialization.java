package org.onpu.serialization;

import java.io.*;

/**
 * Class that provides methods to save objects and read files.
 * Uses serialization to save objects.
 *
 * @author Daniel Yablonskyi
 * @version 1.0
 */
public interface FileSerialization {
    /**
     * Reads a serialized file with a .ser extension at "src/main/resources/saves/"
     * and returns read serialized Object
     *
     * @param fileName name of the file to read
     * @return read serialized Object
     */
    static Object readFromFile(String fileName) {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/saves/" + fileName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            ois.close();
            fis.close();
            System.out.println(fileName + ".ser was successfully read");
            if (object != null) {
                return object;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * Serializes object at "src/main/resources/saves/" with .ser extension.
     *
     * @param t        object to save
     * @param fileName name of the file
     */
    static <T> void saveToFile(T t, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream("src/main/resources/saves/" + fileName + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.close();
            fos.close();
            System.out.println("Serialized data was saved in " + fileName + ".ser");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
