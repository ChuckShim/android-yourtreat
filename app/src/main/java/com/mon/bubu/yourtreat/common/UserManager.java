package com.mon.bubu.yourtreat.common;

import android.content.Context;

import com.mon.bubu.yourtreat.db.User;

import java.util.List;

/**
 * Created by Chuck on 2015. 5. 12..
 */
public class UserManager {

    private static UserManager mInstance = null;
    private Context mCtx;

    private UserManager(Context ctx){
        this.mCtx = ctx;
    }

    public static UserManager getInstance(Context ctx){

        if(mInstance == null){
            mInstance = new UserManager(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public void save(String name){
        User user = new User(name);
        user.save();
    }

    public List<User> selectAll(){
        List<User> userList = User.listAll(User.class);
        return  userList;
    }
}
