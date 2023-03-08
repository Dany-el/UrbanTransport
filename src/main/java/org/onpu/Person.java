package org.onpu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String livingAddress;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find())
            this.phoneNumber = phoneNumber;
        else
            throw new Exception("Invalid phone number");
    }

    Person() {
        name = "Undefined";
        surname = "Undefined";
        patronymic = "Undefined";
        phoneNumber = "Undefined";
        livingAddress = "Undefined";
    }

    Person(String name, String surname, String patronymic,
           String phoneNumber, String livingAddress) throws Exception {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        setPhoneNumber(phoneNumber);
        this.livingAddress = livingAddress;
    }

    Person(Person obj) {
        name = obj.name;
        surname = obj.surname;
        patronymic = obj.patronymic;
        phoneNumber = obj.phoneNumber;
        livingAddress = obj.livingAddress;
    }
}
