package com.example.admin.test_friday.Test_Classes;

public class TestMyQueue {

    public static void main(String[] args) {
        MyQueue<String> queue= new MyQueue<>();

        queue.Enqueue("Hello");
        queue.Enqueue("I am a person");
        queue.Enqueue("It is nice to meet you");

        System.out.println(queue.Dequeue());
        System.out.println(queue.Dequeue());
        System.out.println(queue.Dequeue());
    }


}
