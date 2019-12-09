package ru.job4j.a312;

import java.util.List;

public interface Store {
    void addPhone(String phone);
    List<String> getPhones();
    void clear();
    String getItem(int position);
}
