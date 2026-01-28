package com.example.lab5.service.impl;

import com.example.lab5.model.Item;
import com.example.lab5.service.ShoppingCartService;
import com.example.lab5.utils.DB;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScope //tồn tại trong phạm vi 1 phiên làm việc
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, Item> map = new HashMap<>();
    @Override
    public Item add(Integer id) {
        Item item = map.get(id);
        if (item == null){
            item = DB.items.get(id);
            item.setQty(1);
            map.put(id, item);
        }else item.setQty(item.getQty()+1);
        return item;
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item != null) item.setQty(qty);
        return item;
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    @Override
    public int getCount() { //đếm tổng số cái item có trong cart
        return map.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount() { //tính sum số tiền trong cart
        return map.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()) //giá * sl
                .sum();
    }
}
