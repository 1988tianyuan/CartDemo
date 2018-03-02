package DAO;

import bean.Order;
import bean.User;

import java.sql.*;

public class OrderDAO {
    public Order getOrder(User user){
        Order order = null;
        String sql = "select * from order_ where uid=?";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setUser(user);
            }
            c.close();
            ps.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
        return order;
    }

    public void addOrder(Order order){
        String sql = "insert into order_ values(null,?)";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ps.setInt(1,order.getUser().getId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                order.setId(rs.getInt(1));
            }
            c.close();
            ps.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }

    }


}
