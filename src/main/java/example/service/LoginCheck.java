package example.service;

import example.dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginCheck {
    public static boolean check(String username, String password) {
        boolean isPassed = false;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from user where username=? and password=?");
            p.setString(1, username);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            if (rs.next()){
                isPassed = true;
            } else {
                isPassed = false;
            }
            Dao.close(rs, p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isPassed;
    }
}
