package DAO;

import bean.User;

import java.sql.*;

public class UserDAO {
    public User getUser(String name, String password){
        User user = null;
        String sql = "select * from user where name=? and password=?";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8",
                    "root", "admin");
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(name);
                user.setPassword(password);
            }
            c.close();
            ps.close();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }
}
