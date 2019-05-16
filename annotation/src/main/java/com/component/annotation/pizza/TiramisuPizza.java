package com.component.annotation.pizza;


import com.component.annotation.Factory;
import com.component.annotation.Meal;

/**
 * Created by fox.hu on 2018/10/18.
 */

@Factory(id = "TiramisuPizza", type = Meal.class)
public class TiramisuPizza implements Meal {
    @Override
    public float getPrice() {
        return 4.5f;
    }
}
