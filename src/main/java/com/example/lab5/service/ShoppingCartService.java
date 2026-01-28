package com.example.lab5.service;

import com.example.lab5.model.Item;

import java.util.Collection;

public interface ShoppingCartService {
    Item add(Integer id);
    Item update(Integer id, int qty);
    void remove(Integer id);
    void clear();
    Collection<Item> getItems();
    int getCount();
    double getAmount();

}
