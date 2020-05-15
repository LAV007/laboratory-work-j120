package ru.avalon.j120.order_accounting_system.person.passport;

import java.io.Serializable;

public class Passport implements Serializable  {
    private String name, surName, patronymic;

    public Passport(String name, String patronymic, String surName) {
        this.name = name;
        this.patronymic = patronymic;
        this.surName = surName;
    }

    public void setName (String setname) {
        this.name = setname;
    }
    public String getName () {
        return name;
    }
    public void setSurName (String setsurname) {
        this.surName = setsurname;
    }
    public String getSurName () {
        return surName;
    }
    public void setPatronymic (String setpatronymic) {
        this.patronymic = setpatronymic;
    }
    public String getPatronumic () {
        return patronymic;
    }

    @Override
    public String toString() {
        return name + " " + patronymic+ " " + surName  + " ";
    }
}



