package com.example.hadeer.cashutask;


public class DataHandler {
    private static DataHandler instance ;

    public DataHandler() {

    }

    public static DataHandler  create_new_instance(){
        return instance = new DataHandler();
    }

    public static DataHandler get_instance(){
        if (instance == null){
            return create_new_instance();
        }
        return instance;
    }

}
