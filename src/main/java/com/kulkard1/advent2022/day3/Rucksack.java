package com.kulkard1.advent2022.day3;

import java.util.List;

public class Rucksack {

    private final String firstCompartment;

    private final String secondCompartment;

    public Rucksack(String contents) {
        firstCompartment = contents.substring(0, contents.length() / 2);
        secondCompartment = contents.substring(contents.length() / 2);
    }

    public int getCommonItemPriority() {
        final List<Integer> firstCompartmentItemPrioritiesList = firstCompartment.subSequence(0, firstCompartment.length())
                .chars()
                .map(Rucksack::getPriorityFromHtmlCode)
                .boxed()
                .toList();
        return secondCompartment.subSequence(0, secondCompartment.length())
                .chars()
                .map(Rucksack::getPriorityFromHtmlCode)
                .filter(firstCompartmentItemPrioritiesList::contains)
                .findFirst()
                .orElseThrow();
    }

    public List<Integer> getAllItemsPriority() {
        return firstCompartment
                .concat(secondCompartment)
                .chars()
                .map(Rucksack::getPriorityFromHtmlCode)
                .boxed()
                .toList();
    }

    private static int getPriorityFromHtmlCode(int charHtmlCode) {
        if (charHtmlCode >= 97) {
            return charHtmlCode - 96;
        } else {
            return charHtmlCode - 38;
        }
    }
}
