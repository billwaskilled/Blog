package example.service;

import example.dao.Dao;
import example.service.utils.AppMD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterCheck {
    public static boolean register(String username,String password,String age){
        boolean isSuccess = false;
        boolean isPassed = LoginCheck.check(username, password);

        password = AppMD5Util.getMD5(password);

        if(!isPassed){
            try {
                Connection conn = Dao.getConnection();
                PreparedStatement p = conn.prepareStatement("insert user(username,password,age) VALUES (?,?,?);");
                p.setString(1, username);
                p.setString(2, password);
                p.setString(3, age);
                p.executeUpdate();
                System.out.println("注册成功");
                Dao.close(p, conn);
                isSuccess = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }
}
