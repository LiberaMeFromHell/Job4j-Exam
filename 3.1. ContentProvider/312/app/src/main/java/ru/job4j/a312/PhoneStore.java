package ru.job4j.a312;

import java.util.ArrayList;
import java.util.List;

public class PhoneStore implements Store {
    private List<String> phones;

    public PhoneStore() {
        this.phones = new ArrayList<>();
    }

    public List<String> getPhones() {
        return phones;
    }

    @Override
    public void clear() {
        phones.clear();
    }

    @Override
    public String getItem(int position) {
        return phones.get(position);
    }

    public void addPhone(String phone) {
        phones.add(phone);
    }
}
