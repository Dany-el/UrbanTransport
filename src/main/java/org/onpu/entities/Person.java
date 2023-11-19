package org.onpu.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents person
 *
 * @author Daniel Yablonskyi
 * @version 1.0
 */
public abstract class Person implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String livingAddress;

    public Person() {
        name = "Undefined";
        surname = "Undefined";
        patronymic = "Undefined";
        phoneNumber = "Undefined";
        livingAddress = "Undefined";
    }

    public Person(String name, String surname, String patronymic,
                  String phoneNumber, String livingAddress) throws RuntimeException {
        setFullName(name, surname, patronymic);
        setPhoneNumber(phoneNumber);
        this.livingAddress = livingAddress;
    }

    public Person(Person obj) {
        if (obj != null) {
            setFullName(obj.name, obj.surname, obj.patronymic);
            phoneNumber = obj.phoneNumber;
            livingAddress = obj.livingAddress;
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getFullName() {
        return name + " " + surname + " " + patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setFullName(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    /**
     * Sets the phone number. The phone number must be such as +380... or 0...
     *
     * @param phoneNumber the phone number to set
     * @throws RuntimeException if the phone number has not been matched the pattern
     */
    public void setPhoneNumber(String phoneNumber) throws RuntimeException {
        Pattern pattern = Pattern.compile("^(\\+38)?0\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find())
            this.phoneNumber = phoneNumber;
        else
            throw new RuntimeException("Invalid phone number");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Person person = (Person) object;

        if (!Objects.equals(name, person.name)) return false;
        if (!Objects.equals(surname, person.surname)) return false;
        if (!Objects.equals(patronymic, person.patronymic)) return false;
        if (!Objects.equals(phoneNumber, person.phoneNumber)) return false;
        return Objects.equals(livingAddress, person.livingAddress);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (livingAddress != null ? livingAddress.hashCode() : 0);
        return result;
    }
}
