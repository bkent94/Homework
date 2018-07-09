package com.example.admin.test_friday.Test_Classes;

class TreeNode<T>{
    private TreeNode<T> rightTreeNode;
    private TreeNode<T> leftTreeNode;
    private T value;

    public TreeNode(){
        value=null;
    }

    public TreeNode(T value){
        this.value=value;
    }

    public TreeNode getRightTreeNode() {
        return rightTreeNode;
    }

    public void setRightTreeNode(TreeNode rightTreeNode) {
        this.rightTreeNode = rightTreeNode;
    }
    public TreeNode getLeftTreeNode() {
        return leftTreeNode;
    }

    public void setLeftTreeNode(TreeNode leftTreeNode) {
        this.leftTreeNode = leftTreeNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

public class BinarySearchTree<T> {
    TreeNode<T> head = null;

    public void add(T t) {
        if (head == null) {
            head = new TreeNode<>(t);
            return;
        }

        findPlacement(head, t);

    }

    private void findPlacement(TreeNode<T> currentNode, T t) {


        if (currentNode.getValue().toString().compareTo(t.toString()) <= 0) {

            TreeNode<T> right = currentNode.getRightTreeNode();

            if (right == null) {

                currentNode.setRightTreeNode(new TreeNode<T>(t));
                return;
            }
            findPlacement(right, t);
        } else {
            TreeNode<T> left = currentNode.getLeftTreeNode();

            if (left == null) {
                currentNode.setLeftTreeNode(new TreeNode<T>(t));
                return;
            }
            findPlacement(left, t);
        }
    }

    public String getSearchTreeMap() {
        String str = "";

        if(head==null)
            return str;

        TreeNode current = head;
        str+=current.getValue().toString();
        str=Search(current.getRightTreeNode(),str)+Search(current.getLeftTreeNode(),str);


        return str;
    }


    private String Search(TreeNode<T> current,String str) {



        if (current != null) {
            str += ","+current.getValue().toString();
            str+=Search(current.getRightTreeNode(),str)+Search(current.getLeftTreeNode(),str);
            return str;
        }

        return "";
    }



    @Override
    public String toString() {
        return getSearchTreeMap();
    }

}
