package com.javarush.test;

/*
И снова StackTrace
*/

public class Solution {
    public static void main(String[] args) {
        for (int i = 10; i > 0; i--)
        {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Поехали");
    }
}