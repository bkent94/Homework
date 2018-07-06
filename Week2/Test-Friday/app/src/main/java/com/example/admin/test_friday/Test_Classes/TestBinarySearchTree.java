package com.example.admin.test_friday.Test_Classes;

public class TestBinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree<String> stringBinarySearchTree = new BinarySearchTree<>();

        stringBinarySearchTree.add("A");
        stringBinarySearchTree.add("B");

        System.out.println(stringBinarySearchTree);

    }
}
