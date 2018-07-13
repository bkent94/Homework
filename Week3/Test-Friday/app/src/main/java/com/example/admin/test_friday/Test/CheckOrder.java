package com.example.admin.test_friday.Test;

import java.util.ArrayList;
import java.util.List;

public class CheckOrder {

    public static boolean checkBrackets(String str){

        int length=str.length()-1;

        boolean allMatch=true;

       List<String> listOfForwardBrackets= new ArrayList<>();
        List<String> listOfReverseBrackets= new ArrayList<>();
       List<String> listOfBracketsInString =new ArrayList<>();
       listOfForwardBrackets.add("{");
        listOfForwardBrackets.add("(");
        listOfForwardBrackets.add("[");
        listOfReverseBrackets.add("]");
        listOfReverseBrackets.add(")");
        listOfReverseBrackets.add("}");

        for (int i = 0; i <str.length() ; i++) {
            Character c=str.charAt(i);




            if(listOfReverseBrackets.contains(c.toString())){
                //Resolve code for an end bracket
                int currentBracketIndex=listOfReverseBrackets.indexOf(c.toString());
                String selected_bracket=listOfForwardBrackets.get(currentBracketIndex);
                System.out.println(selected_bracket);
                if(listOfBracketsInString.contains(selected_bracket)){
                    listOfBracketsInString.remove(selected_bracket);

                }
                else{

                    allMatch=false;
                }
            }

            else{
                //resolve code for a start bracket

                if(i==str.length()-1){
                    allMatch=false;

                }

                listOfBracketsInString.add(c.toString());
            }
        }

        return allMatch;
    }

    public static void main(String[] args) {
        System.out.println(checkBrackets("({})[]"));
        System.out.println(checkBrackets("[]{[}["));
    }
}
