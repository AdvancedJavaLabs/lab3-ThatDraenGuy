package ru.draen.parallel.hadoop;

import lombok.AllArgsConstructor;
import org.apache.hadoop.fs.FileSystem;

@AllArgsConstructor
public class HDFSHelper {
    private final FileSystem fs;
}
