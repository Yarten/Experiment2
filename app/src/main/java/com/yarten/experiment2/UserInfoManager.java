package com.yarten.experiment2;

import android.util.Log;

/**
 * Created by yarten on 2017/10/11.
 */

public class UserInfoManager
{
    public static boolean checkLogin(String id, String password)
    {
        UserInfo info = query(id);

        if(info == null) return false;

        if(Encipher.decode(info.getPassword()).equals(password))
            return true;
        else return false;
    }

    protected static class UserInfo
    {
        private String id, password;

        UserInfo()
        {
            this("", "");
        }

        UserInfo(String id, String password)
        {
            setId(id);
            setPassword(password);
        }

        public void setId(final String id){this.id = id;}

        public String getId(){return id;}

        public void setPassword(final String password)
        {
            this.password = password;
        }

        public String getPassword()
        {
            return password;
        }
    }

    protected static UserInfo query(String id)
    {
        UserInfo info = null;

        if(id.equals("123456"))
        {
            info = new UserInfo("123456", "6666");
        }

        return info;
    }
}

class Encipher
{
    public static String encode(String original)
    {
        return original;
    }

    public static String decode(String secret)
    {
        return secret;
    }
}
