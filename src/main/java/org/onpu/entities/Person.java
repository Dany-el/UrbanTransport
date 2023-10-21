package org.onpu.entities;

import org.onpu.Printable;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person implements Printable, Serializable {
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
                  String phoneNumber, String livingAddress) throws Exception {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        setPhoneNumber(phoneNumber);
        this.livingAddress = livingAddress;
    }

    public Person(Person obj) {
        name = obj.name;
        surname = obj.surname;
        patronymic = obj.patronymic;
        phoneNumber = obj.phoneNumber;
        livingAddress = obj.livingAddress;
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

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    /**
     * @param phoneNumber 10-digit phone number
     * @throws Exception phone number length is less or greater than 10
     */
    public void setPhoneNumber(String phoneNumber) throws Exception {
        Pattern pattern = Pattern.compile("^(\\+38)?0\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find())
            this.phoneNumber = phoneNumber;
        else
            throw new Exception("Invalid phone number");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

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

    @Override
    public void printInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "\nName      : " + name +
                "\nSurname   : " + surname +
                "\nPatronymic: " + patronymic +
                "\nAddress   : " + livingAddress +
                "\nPhone number: " + phoneNumber;
    }
}
