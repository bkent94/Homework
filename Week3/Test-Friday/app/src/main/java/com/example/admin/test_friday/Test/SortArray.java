package com.example.admin.test_friday.Test;

public class SortArray {

    public static void sort(int[] a){

        for (int i = 0; i < a.length-1; i++) {
            for (int i1 = i+1; i1 < a.length; i1++) {
                if(a[i]>a[i1]){
                    int temp=a[i];
                    a[i]=a[i1];
                    a[i1]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] a={2,8,9,3,4,3,2,6,6,2,4,9,8};
      sort(a);
        for (int i : a) {
            System.out.print(i+",");
        }
    }
}
