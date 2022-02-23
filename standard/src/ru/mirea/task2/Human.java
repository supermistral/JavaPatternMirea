package ru.mirea.task2;

import java.time.LocalDate;

public class Human {
    int         weight;
    String      firstName,
                lastName;
    LocalDate   birthDate;

    public Human(String firstName, String lastName, LocalDate birthDate, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAge() {
        return this.birthDate.until(LocalDate.now()).getYears();
    }

    public int getWeight() {
        return weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String toString() {
        return "Age - " + getAge() +
                "\nName - " + firstName + " " + lastName +
                "\nBirthDate - " + birthDate.toString() +
                "\nWeight - " + weight;
    }
}
