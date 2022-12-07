package com.kulkard1.advent2022.day7;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class File {
    private final boolean directory;

    private final String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final long size;

    private final File parent;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Set<File> children = new HashSet<>();

    public long getSize() {
        return directory ? children.stream().map(File::getSize).mapToLong(i -> i).sum() : size;
    }

    public Set<File> getDirectories() {
        Set<File> collect = children
                .stream()
                .filter(File::isDirectory)
                .flatMap(directories -> directories.getDirectories().stream())
                .collect(Collectors.toSet());
        collect.add(this);
        return collect;
    }

    public File getRoot() {
        return parent == null ? this : this.getParent().getRoot();
    }

}
