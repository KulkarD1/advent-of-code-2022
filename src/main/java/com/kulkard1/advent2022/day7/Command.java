package com.kulkard1.advent2022.day7;

public record Command(String command) {

    public File execute(File currentDirectory) {
        if (command.startsWith("$ cd /")) {
            return currentDirectory;
        } else if (command.startsWith("$ cd ..")) {
            return currentDirectory.getParent();
        } else if (command.startsWith("$ cd")) {
            final String[] instructionMetadata = command.split(" ");
            return currentDirectory
                    .getChildren()
                    .stream()
                    .filter(File::isDirectory)
                    .filter(directory -> directory.getName().equals(instructionMetadata[2]))
                    .findFirst()
                    .orElseThrow();

        } else if (command.startsWith("$ ls")) {
            return currentDirectory;
        } else if (command.startsWith("dir")) {
            final String[] fileMetadata = command.split(" ");
            File file = new File(true, fileMetadata[1], -1, currentDirectory);
            currentDirectory.getChildren().add(file);
            return currentDirectory;
        } else {
            final String[] fileMetadata = command.split(" ");
            File file = new File(false, fileMetadata[1], Long.parseLong(fileMetadata[0]), currentDirectory);
            currentDirectory.getChildren().add(file);
            return currentDirectory;
        }
    }

}
