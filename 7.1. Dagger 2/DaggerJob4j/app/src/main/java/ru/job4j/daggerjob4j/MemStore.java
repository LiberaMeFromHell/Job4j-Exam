package ru.job4j.daggerjob4j;

import java.util.Arrays;
import java.util.List;

public class MemStore implements Store{
    private List<String> store = Arrays.asList(
            "String 1",
            "String 2",
            "String 3",
            "String 4",
            "String 5",
            "String 6");

    public List<String> getAll() {
        return store;
    }

    @Override
    public void add(String value) {
        store.add(value);
    }
}
