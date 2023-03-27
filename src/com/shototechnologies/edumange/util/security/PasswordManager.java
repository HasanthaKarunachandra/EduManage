package com.shototechnologies.edumange.util.security;

import org.mindrot.BCrypt;

public class PasswordManager {
    public String encrypt(String rowPassword){
       return BCrypt.hashpw(rowPassword,BCrypt.gensalt(10));
    }
    public boolean checkPassword(String rawPassword,String hashPassword){
        return BCrypt.checkpw(rawPassword,hashPassword);

    }
}
