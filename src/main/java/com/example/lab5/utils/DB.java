package com.example.lab5.utils;

import com.example.lab5.model.Item;

import java.util.HashMap;
import java.util.Map;

public class DB {
    public static Map<Integer, Item> items = new HashMap<>();
    static {
        items.put(1, new Item(1, "Samsung Galaxy S25 Plus 256GB", 22700000, 0));
        items.put(2, new Item(2, "Oppo Find N5", 44180000, 0));
        items.put(3, new Item(3, "iPhone 15 Pro 128GB", 23990000, 0));
        items.put(4, new Item(4, "Sony Xperia 10V", 7490000, 0));
        items.put(5, new Item(5, "Xiaomi Note 15 5G 128GB", 7190000, 0));
    }
}
