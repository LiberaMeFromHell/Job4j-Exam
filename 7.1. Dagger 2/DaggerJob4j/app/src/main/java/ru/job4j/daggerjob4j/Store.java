package ru.job4j.daggerjob4j;

import java.util.List;

public interface Store {
    void add(String value);
    List<String> getAll();
}
