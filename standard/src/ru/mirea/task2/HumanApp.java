package ru.mirea.task2;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HumanApp {
    List<Human> humans = new ArrayList<>();

    private static final String[] firstNames = {
            "Алексей", "Василий", "Владимир", "Матвей", "Михаил", "Никита", "Олег", "Павел", "Андрей"
    };
    private static final String[] lastNames = {
            "Баранюк", "Петров", "Матвеев"
    };

    private static int generateValue(int maxNum, int minNum) {
        return (int)(Math.random() * (maxNum - minNum) + minNum);
    }

    private static int generateWeight() {
        return generateValue(120, 50);
    }

    private static String generateFirstName() {
        return firstNames[generateValue(firstNames.length - 1, 0)];
    }

    private static String generateLastName() {
        return lastNames[generateValue(lastNames.length - 1, 0)];
    }

    private static LocalDate generateBirthDate() {
        LocalDate date;
        int year, month, day;

        while (true) {
            year = generateValue(2016, 1952);
            month = generateValue(12, 1);
            day = generateValue(31, 1);

            try {
                date = LocalDate.of(year, month, day);
                return date;
            } catch (DateTimeException ex) {}
        }

    }

    public void generateHumans(int amount) {

        for (int i = 0; i < amount; ++i) {
            humans.add(new Human(
                    generateFirstName(),
                    generateLastName(),
                    generateBirthDate(),
                    generateWeight()
            ));
        }
    }

    public void solveTask() {
        List<Human> humansResult;
        humans.forEach(human -> System.out.println(human + "\n"));

        humansResult = humans
                .stream()
                .sorted((human1, human2) -> human1.getAge() - human2.getAge())
                .collect(Collectors.toList());
        humansResult.forEach(human -> System.out.println(human.getAge()));
        System.out.println();

        humansResult = humansResult
                .stream()
                .filter(human -> human.getAge() < 20)
                .collect(Collectors.toList());
        humansResult.forEach(human -> System.out.println(human.getBirthDate()));
        System.out.println();

        humansResult = humansResult
                .stream()
                .filter(human -> human.getFirstName().contains("е"))
                .collect(Collectors.toList());
        humansResult.forEach(human -> System.out.println(human.getFirstName()));
        System.out.println();

        String result = humansResult
                .stream()
                .map(human -> human.getFirstName())
                .reduce("", (acc, human) -> acc + human.substring(0, 1));
        System.out.println(result);
    }
}
