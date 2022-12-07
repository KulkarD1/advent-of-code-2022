package com.kulkard1.advent2022.day7;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class File {
    private boolean directory;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private long size;

    private File parent;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<File> children = new HashSet<>();

    public long getSize() {
        return directory ? children.stream().map(File::getSize).mapToLong(i -> i).sum() : size;
    }

    public Set<File> getDirectories() {
        if(children.stream().noneMatch(File::isDirectory)) {
            return Set.of(this);
        } else {
            Set<File> collect = children
                    .stream()
                    .filter(File::isDirectory)
                    .flatMap(directories -> directories.getDirectories().stream())
                    .collect(Collectors.toSet());
            collect.add(this);
            return collect;
        }
    }
}
