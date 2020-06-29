package ru.avalon.j120.order_accounting_system.person;

import ru.avalon.j120.order_accounting_system.person.passport.Passport;

import java.io.Serializable;

public class Person implements Serializable {

    private final Passport pass;

    public Person(Passport pass) {
        this.pass = pass;
    }

    public Passport getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return pass.toString();
    }
}