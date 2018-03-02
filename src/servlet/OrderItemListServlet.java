package servlet;

import DAO.OrderDAO;
import DAO.OrderItemDAO;
import bean.Order;
import bean.OrderItem;
import bean.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderItemListServlet", urlPatterns = "/listOrderItem")
public class OrderItemListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        List<OrderItem> orderItemList = (List<OrderItem>) request.getSession().getAttribute("orderItemList");
        OrderDAO orderDAO = new OrderDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        Order order = orderDAO.getOrder(user);

        if(null == order){
            order = new Order();
            order.setUser(user);
            orderDAO.addOrder(order);
            if(null != orderItemList){
                for(OrderItem orderItem:orderItemList){
                    orderItem.setOrder(orderDAO.getOrder(user));
                    orderItemDAO.addOrderItem(orderItem);
                }
            }
        }else if(null != orderItemList){
            for(OrderItem orderItem:orderItemList){
                if(0!=orderItem.getId()){
                    orderItemDAO.updateOrderItem(orderItem);
                }else{
                    orderItem.setOrder(orderDAO.getOrder(user));
                    orderItemDAO.addOrderItem(orderItem);
                }
            }
        }

        request.getRequestDispatcher("listOrderItem.jsp").forward(request,response);
    }
}
