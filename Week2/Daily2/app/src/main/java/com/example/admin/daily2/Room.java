package com.example.admin.daily2;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class Room {
    public final boolean isInfected;
    public boolean visited = false;
    Room(boolean infected){
        isInfected = infected;
    }

    public static boolean isOutbreak(Room[][] floor){

        boolean foundOutbreak=false;
        for(int i=0;i<floor.length;i++){
            for(int j=0;j<floor[i].length;j++){

                if(floor[i][j].visited==true){
                    continue;
                }
                String str="";
                if(floor[i][j].isInfected)
                    str=":INFECTION";
                System.out.println(  "Now traversing room "+i+","+j+str);
                    if(findOutbreak(floor,i,j,4)){
                        foundOutbreak=true;
                        break;
                    }
            }
            if(foundOutbreak){break;}
        }

            return  foundOutbreak;
    }

    private static boolean findOutbreak(Room[][] floor,int roomX,int roomY,int roomsToCheck){

        floor[roomX][roomY].visited=true;

        if(roomsToCheck==0){
            return true;
        }



        if(floor[roomX][roomY].isInfected){

            boolean infectedX=false;
            boolean infectedY=false;

            if(roomX<floor.length-1){
                infectedX=findOutbreak(floor,roomX+1,roomY,roomsToCheck-1);
            }
            if(roomY<floor.length-1){
                infectedY=findOutbreak(floor, roomX, roomY+1, roomsToCheck-1);
            }


            return (infectedX||infectedY);
        }

        return false;
    }
}
