package com.mon.bubu.yourtreat.db;

import com.orm.SugarRecord;

/**
 * Created by Chuck on 2015. 5. 12..
 */
public class User extends SugarRecord<City> {

    String name;

    public User(){super();};

    public User(String name){
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
