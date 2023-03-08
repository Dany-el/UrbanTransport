package org.onpu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transport {
    private String type;
    private String routeName;
    private String number;
    private String id;

    Transport() {
        type = "Undefined";
        routeName = "Undefined";
        number = "Undefined";
        id = "Undefined";
    }

    Transport(String type, String routeName, String number, String id) throws Exception {
        this.type = type;
        this.routeName = routeName;
        setNumber(number);
        setId(id);
    }

    public void setNumber(String number) throws Exception{
        Pattern pattern = Pattern.compile("^([a-zA-Z]{2})\\s(\\d{4})\\s([a-zA-Z]{2})$");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find())
            this.number = number;
        else
            throw new Exception("Invalid number");
    }

    public void setId(String id) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find())
            this.id = id;
        else
            throw new Exception("Invalid id");
    }
}
