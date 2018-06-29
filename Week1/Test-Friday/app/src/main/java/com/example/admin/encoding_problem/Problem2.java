package com.example.admin.encoding_problem;

import java.util.ArrayList;
import java.util.List;

public class Problem2 {

    public static List<String> g(String inputStr){


        ArrayList<String> frequencyList=new ArrayList<String>();
        inputStr=inputStr.toLowerCase();

        for(Character i='a';i<='z';i++) {
                frequencyList.add(i.toString()+":");
                Integer frequency=0;
            for (int j = 0; j < inputStr.length(); j++) {
                char c = inputStr.charAt(j);

                if(c==i){
                    frequency++;
                }


            }
            String str= frequencyList.get(i-'a');
            frequencyList.set(i-'a',str+frequency.toString());
        }
        return frequencyList;
    }

    public static void main(String[] args) {
        System.out.println(g("aab"));
    }
}
