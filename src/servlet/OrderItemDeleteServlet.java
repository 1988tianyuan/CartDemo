package servlet;

import DAO.OrderItemDAO;
import bean.OrderItem;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderItemDeleteServlet",urlPatterns = "/deleteOrderItem")
public class OrderItemDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<OrderItem> orderItemList = (List<OrderItem>)request.getSession().getAttribute("orderItemList");
        if(null != orderItemList){
            for(OrderItem orderItem1 : orderItemList){
                if(orderItem1.getProduct().getId() == id ){
                    orderItemList.remove(orderItem1);
                    new OrderItemDAO().deleteOrderItem(orderItem1);
                    break;
                }
            }
        }
        request.getSession().setAttribute("orderItemList",orderItemList);
        response.sendRedirect("listOrderItem");
    }
}
