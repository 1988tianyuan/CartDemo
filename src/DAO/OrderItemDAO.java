package DAO;

import DAO.ProductDAO;
import bean.Order;
import bean.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {
    public List<OrderItem> getOrderItem(Order order){
        List<OrderItem> orderItemList= new ArrayList<OrderItem>();
        String sql = "select * from orderitem where oid=?";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ps.setInt(1,order.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setProduct(new ProductDAO().getProduct(rs.getInt("pid")));
                orderItem.setNum(rs.getInt("num"));
                orderItem.setOrder(order);
                orderItemList.add(orderItem);
            }
            c.close();
            ps.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
        return orderItemList;
    }

    public void addOrderItem(OrderItem orderItem){
        String sql = "insert into orderitem values(null,?,?,?)";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ps.setInt(1,orderItem.getProduct().getId());
            ps.setInt(2,orderItem.getOrder().getId());
            ps.setInt(3,orderItem.getNum());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                orderItem.setId(rs.getInt(1));
            }
            c.close();
            ps.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }

    public void updateOrderItem(OrderItem orderItem){
        String sql = "update orderitem set num=? where id=?";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ps.setInt(1,orderItem.getNum());
            ps.setInt(2,orderItem.getId());
            ps.executeUpdate();
            c.close();
            ps.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }


    }

    public void deleteOrderItem(OrderItem orderItem){
        String sql = "delete from orderitem where id=?";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ps.setInt(1,orderItem.getId());
            ps.executeUpdate();
            c.close();
            ps.close();
        }catch (SQLException se){
            se.printStackTrace();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }


}
