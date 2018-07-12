package com.example.admin.daily3.Data;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {
public static final String ARNOLD_SCHWARZENEGGER="arnold";
public static final String JOHN_STEWART="john";
    public static List<Celebrity> CreateCelebrityList(){

        List<Celebrity> celebrityList=new ArrayList<>();

        celebrityList.add(new Celebrity("Arnold Schwarzenegger","Arnold Alois Schwarzenegger is an Austrian-American actor, filmmaker, " +
                "businessman, investor, author, philanthropist, activist, politician, and former professional bodybuilder and powerlifter." +
                " He served two terms as the 38th Governor of California from 2003 to 2011.",ARNOLD_SCHWARZENEGGER));
        celebrityList.add(new Celebrity("John Stewart","John Stewart is an American comedian, writer, producer, director, political commentator, actor, and television host. " +
                "He hosted The Daily Show, a satirical news program on Comedy Central, from 1999 to 2015.",JOHN_STEWART));
        return celebrityList;
    }
}
