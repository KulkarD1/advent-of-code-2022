package com.kulkard1.advent2022.day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4Solution1 {

    public static void main(String... args) throws IOException, URISyntaxException {
        try (final Stream<String> linesStream = Files.lines(Paths.get(ClassLoader.getSystemResource("day-4-input").toURI()))) {
            final long count = linesStream
                    .filter(line -> {
                        final String[] pairedElvesAssignmentArray = line.split(",");
                        final String[] elf1AssignmentsRange = pairedElvesAssignmentArray[0].split("-");
                        final String[] elf2AssignmentsRange = pairedElvesAssignmentArray[1].split("-");
                        final List<Integer> elf1AssignmentsList = IntStream.rangeClosed(Integer.parseInt(elf1AssignmentsRange[0]), Integer.parseInt(elf1AssignmentsRange[1])).boxed().toList();
                        final List<Integer> elf2AssignmentsList = IntStream.rangeClosed(Integer.parseInt(elf2AssignmentsRange[0]), Integer.parseInt(elf2AssignmentsRange[1])).boxed().toList();
                        final long elf1NonCommonAssignmentCount = elf1AssignmentsList
                                .stream()
                                .filter(assignmentId -> !elf2AssignmentsList.contains(assignmentId))
                                .count();
                        final long elf2NonCommonAssignmentCount = elf2AssignmentsList
                                .stream()
                                .filter(assignmentId -> !elf1AssignmentsList.contains(assignmentId))
                                .count();
                        return elf1NonCommonAssignmentCount == 0 || elf2NonCommonAssignmentCount == 0;
                    })
                    .count();
            System.out.println("count = " + count);
        }

    }
}
