package com.example.admin.daily2.model.data;

import android.provider.BaseColumns;

public class LocalDataContract {

    public static final String NAME="Animals.db";
    public static final int VERSION=1;
    public static final String ANIMALS_TABLE_NAME="Animals";
    public static final String CATEGORY_TABLE_NAME="Categories";
    public static final String TEXT=" TEXT ";
    public static final String PRIMARY_KEY=" PRIMARY KEY ";
    public static final String ANIMALS_FOREIGN_KEY="FOREIGN KEY("+Animals.CATEGORY+") REFERENCES "+CATEGORY_TABLE_NAME+"("+Category.NAME+")";
    /**
     * Creates table
     */
    public static class DDL{

        public static final String DROP_ANIMALS_TABLE="DROP TABLE IF EXISTS "+ANIMALS_TABLE_NAME;
        public static final String DROP_CATEGORY_TABLE="DROP TABLE IF EXISTS "+CATEGORY_TABLE_NAME;
        public static final String CREATE_ANIMALS_TABLE="CREATE TABLE "+ANIMALS_TABLE_NAME+"("+Animals.NAME+TEXT+PRIMARY_KEY+","+Animals.CATEGORY+TEXT+","+Animals.WEIGHT+TEXT+","+Animals.SOUND+TEXT+","+Animals.DETAILS+TEXT+","+ANIMALS_FOREIGN_KEY+")";
        public static final String CREATE_CATEGORY_TABLE="CREATE TABLE "+CATEGORY_TABLE_NAME+"("+Category.NAME+TEXT+PRIMARY_KEY+")";

    }

    /**
     * Queries from table
     */
    public static class DML{
public static final String GET_ALL_ANIMALS="SELECT * FROM "+ANIMALS_TABLE_NAME;
        public static final String GET_ALL_CATEGORIES="SELECT * FROM "+CATEGORY_TABLE_NAME;
        public static final String GET_ANIMALS_BY_CATEGORY=GET_ALL_ANIMALS+" WHERE "+Animals.CATEGORY+"=";
    }

    /**
     * SQL for inserting Animals
     */
    public static  class Animals implements BaseColumns{
        public static final String NAME="name";
        public static final String CATEGORY="category";
        public static final String SOUND="sound";
        public static final String DETAILS="details";
        public static final String WEIGHT="weight";
    }

    public static class Category implements BaseColumns{
        public static final String NAME="name";
    }
}
