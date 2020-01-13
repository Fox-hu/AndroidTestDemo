package com.example.algorithms.annotations.pizza;


import com.component.annotation.Factory;
import com.component.annotation.Meal;

/**
 * Created by fox.hu on 2018/10/18.
 */

@Factory(id = "MargheritPizza", type = Meal.class)
public class MargheritPizza implements Meal {
    @Override
    public float getPrice() {
        return 6f;
    }
}
