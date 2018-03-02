package servlet;

import DAO.OrderDAO;
import DAO.OrderItemDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import bean.Order;
import bean.OrderItem;
import bean.Product;
import bean.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserLoginServlet", urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = request.getParameter("data");
        JSONObject jb = JSONObject.fromObject(jsonString);
        String name = jb.getString("name");
        String password = jb.getString("password");
        User user= new UserDAO().getUser(name,password);
        if(null != user){
            request.getSession().setAttribute("user",user);
            System.out.println(user.getName());
            Order order;
            if(null != (order = new OrderDAO().getOrder(user))){
                List<OrderItem> orderItemList;
                if(null != (orderItemList = new OrderItemDAO().getOrderItem(order))){
                    request.getSession().setAttribute("orderItemList",orderItemList);
                }
            }else{
                request.getSession().removeAttribute("orderItemList");
            }
            response.getWriter().write("right");
        }else{
            response.getWriter().write("wrong");
        }

    }


}
