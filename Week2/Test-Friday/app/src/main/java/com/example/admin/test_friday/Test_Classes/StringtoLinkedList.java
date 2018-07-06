package com.example.admin.test_friday.Test_Classes;

class Node<T>{
    private Node nextNode;
    private T value;

    public Node(){
        value=null;
    }

    public Node(T value){
        this.value=value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

class myLinkedList<T>{

    private Node<T> head=null;
    private Node<T> tail=null;






    public void add(T value){
        Node<T> Node =new Node<T>(value);

        if(head==null){
            head= Node;
        }
        else
        if(tail==null){
            tail= Node;
            head.setNextNode(tail);
        }
        else{
            tail.setNextNode(Node);
            tail= Node;
        }

    }

    public Node<T> getHead(){
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public static myLinkedList<String> convertToString(String str){

        myLinkedList<String> stringmyLinkedList =new myLinkedList<>();

        for (int i = 0; i <str.length() ; i++) {
            stringmyLinkedList.add(str.substring(i,i+1));
        }
        return stringmyLinkedList;
    }

    public int Size(){
        int size=0;

        if(head==null){
            return size;
        }

        size++;
        if(tail==null){
            return size;
        }

        Node Node =head;
        while(!Node.equals(tail)){
            Node = Node.getNextNode();
            size++;
        }

        return size;
    }

    @Override
    public String toString() {
        String output="";
        if(head==null){
            return output;
        }
        if(tail==null){
            return head.getValue().toString();
        }

        Node Node =head;

        while(!Node.equals(tail)){
            output+= Node.getValue().toString();
            Node = Node.getNextNode();

        }
        output+= Node.getValue().toString();
        return output;
    }
}

public class StringtoLinkedList {

    public static void main(String[] args) {
        String str="Hello";
        myLinkedList<String> stringmyLinkedList = myLinkedList.convertToString(str);

        System.out.println(stringmyLinkedList.Size());
        System.out.println(stringmyLinkedList.toString());
        System.out.println(stringmyLinkedList.getHead().getValue());
        System.out.println(stringmyLinkedList.getTail().getValue());

    }
}
