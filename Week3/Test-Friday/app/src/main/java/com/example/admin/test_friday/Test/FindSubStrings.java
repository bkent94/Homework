package com.example.admin.test_friday.Test;

public class FindSubStrings {

    public static boolean Copies(String str,String sub,int n){
        int subCount=0;
        for (int i = 0; i <str.length()-1 ; i++) {
            for (int j = i+1; j <=str.length() ; j++) {

                if(str.substring(i,j).equals(sub)){
                    subCount++;
                }
            }
        }
        System.out.println(subCount);
        if(subCount==n){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(  Copies("catcowcats","cat",2));
        System.out.println(Copies("catcowcats","cow",2));
        System.out.println(Copies("catcowcats","cow",1));
    }
}
