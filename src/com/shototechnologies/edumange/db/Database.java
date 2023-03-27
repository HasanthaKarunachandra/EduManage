package com.shototechnologies.edumange.db;

import com.shototechnologies.edumange.model.User;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<>();

    static {
        userTable.add(new User("Hasantha","karunachandra","hasa@gmail.com","1234"));
    }
}
