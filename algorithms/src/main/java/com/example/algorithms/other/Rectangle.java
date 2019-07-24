package com.example.algorithms.other;

public class Rectangle {
    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isSquare() {
        return height == width;
    }

    class Person {
        private String name;
        private int age;
        private int num;

        Person(String name, int age, int num) {
            this.name = name;
            this.age = age;
            this.num = num;
        }

        public Person(String name) {
            this(name, 20, 100);
        }

        public Person(int age) {
            this("lili", age, 100);
        }

        public Person(int num, int age) {
            this("lili", age, num);
        }
    }
}
