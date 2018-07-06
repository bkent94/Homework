package com.example.admin.test_friday.Test_Classes;

import java.util.Stack;

public class MyQueue<E> {

    Stack<E> stack;

    MyQueue(){
        stack=new Stack<>();
    }


    public void Enqueue(E e){

       stack.push(e);
    }

    public E Dequeue(){
      return stack.remove(0);

    }



}
