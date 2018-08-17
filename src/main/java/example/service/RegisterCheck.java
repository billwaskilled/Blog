package example.service;

import example.dao.Dao;
import example.service.utils.AppMD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterCheck {
    public static boolean register(String username,String password,String age){
        boolean isSuccess = false;
        if (username.length() <= 3 || username.length() >= 15) return isSuccess;     // 如果username长度不符合 4 到 14个字符，报错返回
        if (password.length() <= 3 || password.length() >= 15) return isSuccess;     // 如果password长度不符合 4 到 14个字符，报错返回

        boolean isPassed = checkUsernameIsExisting(username);

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

    private static boolean checkUsernameIsExisting(String username) {
        boolean isExisting = false;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from user where username=?");
            p.setString(1, username);
            ResultSet rs = p.executeQuery();
            if (rs.next()){
                isExisting = true;
            } else {
                isExisting = false;
            }
            Dao.close(rs, p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isExisting;
    }
}
