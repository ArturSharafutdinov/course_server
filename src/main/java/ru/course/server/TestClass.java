package ru.course.server;

import java.util.ArrayList;

public class TestClass {

    public static int i=0;

    public static void add(){
        i++;
    }


    public static void main(String[] args) {
        System.out.println(i);
      add();
        System.out.println(i);
    }
}
