package com.common.utils;

import com.pojo.User;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.UUID;

@Configuration
public class AddLoginTooken {

    public User addLoginTicket(User user){
        Date date = new Date();
        date.setTime(date.getTime()+1000*3600*30);
        user.setUser_tooken(UUID.randomUUID().toString().replaceAll("-",""));
        return user;
    }
}
