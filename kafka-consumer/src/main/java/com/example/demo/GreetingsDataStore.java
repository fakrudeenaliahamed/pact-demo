package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class GreetingsDataStore {

    private static List<GreetingsPojoConsumer> data = new ArrayList<GreetingsPojoConsumer>();

    public synchronized static List<GreetingsPojoConsumer> getDataStore(){
        return data;
    }

}
