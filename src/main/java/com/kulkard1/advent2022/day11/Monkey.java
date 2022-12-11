package com.kulkard1.advent2022.day11;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Data
public class Monkey {

    private String id;

    private List<Integer> worryLevelOfItems = new ArrayList<>();

    private Function<Integer, Integer> operation;

    private Function<Integer, Integer> test;

    private int itemsInspected;

    public Monkey(List<String> input) {
        final Pattern digits = Pattern.compile("\\d+");

        final Matcher idMatcher = digits.matcher(input.get(0));
        assertTrue(idMatcher.find());
        this.id = idMatcher.group();

        final Matcher worryLevelMatcher = digits.matcher(input.get(1));
        while(worryLevelMatcher.find()) {
            this.worryLevelOfItems.add(Integer.parseInt(worryLevelMatcher.group()));
        }

        this.operation = (worryLevel) -> {
            String operationInput = input.get(2);
            int operationDigit = worryLevel;
            final Matcher operationDigitMatcher = digits.matcher(operationInput);
            if(operationDigitMatcher.find()) {
                operationDigit = Integer.parseInt(operationDigitMatcher.group());
            }
            int newWorryLevel;
            if(operationInput.contains("*")) {
                newWorryLevel = worryLevel * operationDigit;
            } else {
                newWorryLevel = worryLevel + operationDigit;
            }
            return newWorryLevel;
        };

        this.test = (worryLevel) -> {
            final Matcher testDivisibleByMatcher = digits.matcher(input.get(3));
            assertTrue(testDivisibleByMatcher.find());
            int divisibleByValue = Integer.parseInt(testDivisibleByMatcher.group());
            final Matcher testTrueMonkeyMatcher = digits.matcher(input.get(4));
            final Matcher testFalseMonkeyMatcher = digits.matcher(input.get(5));
            assertTrue(testTrueMonkeyMatcher.find());
            assertTrue(testFalseMonkeyMatcher.find());
            return worryLevel % divisibleByValue == 0 ? Integer.parseInt(testTrueMonkeyMatcher.group()) : Integer.parseInt(testFalseMonkeyMatcher.group());
        };
    }

    public void executeRound(List<Monkey> monkeys) {
        worryLevelOfItems.forEach(worryLevel -> {
            int newWorryLevel = this.operation.apply(worryLevel) / 3;
            int throwToMonkeyIndex = this.test.apply(newWorryLevel);
            monkeys.get(throwToMonkeyIndex).getWorryLevelOfItems().add(newWorryLevel);
            itemsInspected++;
        });
        this.worryLevelOfItems.clear();
    }

}
