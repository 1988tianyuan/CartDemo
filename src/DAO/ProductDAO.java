package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bean.Product;

public class ProductDAO {
    public List<Product> ListProduct(){
        List<Product> productList = new ArrayList();
        String sql = "select * from product order by id asc";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Product product = new Product(rs.getInt(1),rs.getString(2),rs.getFloat(3));
                productList.add(product);
            }
            c.close();
            ps.close();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }

    public Product getProduct(int id){
        Product product = null;
        String sql = "select * from product where id=?";
        Connection c;
        PreparedStatement ps;
        try{Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
            ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                product = new Product(rs.getInt(1),rs.getString(2),rs.getFloat(3));
            }
            c.close();
            ps.close();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return product;
    }

}
