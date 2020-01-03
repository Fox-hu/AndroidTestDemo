package com.example.algorithms.annotations;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fox.hu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventControl(listenerMethod = "setOnClickListener", callBack = "onClick", getClazzType = View.OnClickListener.class)
public @interface OnClick {
    int[] value();
}
