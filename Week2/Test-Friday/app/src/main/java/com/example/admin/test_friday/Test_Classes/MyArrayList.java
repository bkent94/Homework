package com.example.admin.test_friday.Test_Classes;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E>  {

    public Object[] array;
    private int size;

    public MyArrayList(){
        array=new Object[0];
        size=0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {


        if(array.length>size()){
            array[size()]=e;
            size++;
            return true;
        }

        Object[] newArray=new Object[array.length*2];
        for (int i = 0; i <array.length ; i++) {
            newArray[i]=array[i];
        }

        newArray[size()]=e;
        size++;
        array=newArray;

        return true;
    }

    @Override
    public boolean remove(Object o) {

        Object[] newArray=new Object[array.length-1];
        boolean objectRemoved=false;
        for (int i = 0; i <array.length ; i++) {
            if(array[i].equals(o) && !objectRemoved){
                objectRemoved=true;
            }
            else
                newArray[i]=array[i];
        }


        array=newArray;
        size--;

        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        return null;
    }



    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        array=new Object[0];
    }

    @Override
    public E get(int i) {
        return (E)array[i];
    }

    @Override
    public E set(int i, E e) {
        return null;
    }

    @Override
    public void add(int i, E e) {

    }

    @Override
    public E remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int i) {
        return null;
    }

    @NonNull
    @Override
    public List<E> subList(int i, int i1) {
        return null;
    }


}
