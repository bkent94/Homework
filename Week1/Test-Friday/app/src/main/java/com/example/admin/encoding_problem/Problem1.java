package com.example.admin.encoding_problem;

public class Problem1 {

    public static String f(String inputStr){


        String encodedStr="";
        for(int i=0;i<inputStr.length();i++){
            char c=inputStr.charAt(i);

            if(c>'z' ||c<'A' || c==' '){}
            else
            if(c=='m' || c=='M'){}
                else
                    //Is our character a capital letter?
            if(c<='Z'){
                if(c<'M'){
                    c=(char)('Z'-(c-'A'));

                }
                else{
                    c=(char)('A'+('Z'-c));
                }
            }
            else
            if(c<'m'){
                c=(char)('z'-(c-'a'));

            }
            else{
                c=(char)('a'+('z'-c));
            }
            encodedStr+=c;
        }


        return encodedStr;
    }

    public static void main(String[] args) {
        System.out.println(f("Error in strategy cannot be correct through tactical maneuvers?"));
    }
}
