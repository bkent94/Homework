package com.example.admin.test_friday.Test;

import java.util.ArrayList;
import java.util.List;

public class SkipLetters {


    private static List<Character> getAllLetters(String str){
        List<Character> listOfLetters= new ArrayList<>();

        for (int i = 0; i <str.length() ; i++) {
            if(listOfLetters.size()==0){
                listOfLetters.add(str.charAt(i));
                continue;
            }

            if(!listOfLetters.contains(str.charAt(i))){
                listOfLetters.add(str.charAt(i));
            }
        }
        return listOfLetters;
    }

    public static List<String> Skip(String str){
        List<Character> listOfLetters=getAllLetters(str);
        List<String> listOfSubStrings=new ArrayList<>();
        listOfSubStrings.add(str);

        for(Character c:listOfLetters){


           String newSubString=str.substring(0,str.indexOf(c.toString()));
           if(str.indexOf(c.toString())<str.length()-1){
               newSubString+=str.substring(str.indexOf(c.toString())+1,str.length());
           }

           if(newSubString.length()>1){
              for(String temp: Skip(newSubString)){
                  if(!listOfSubStrings.contains(temp)){
                      listOfSubStrings.add(temp);
                  }
              }
           }

           listOfSubStrings.add(newSubString);
        }

        return listOfSubStrings;
    }

    public static void main(String[] args) {
        System.out.println(Skip("frog"));
        System.out.println(Skip("abcd"));
    }
}
