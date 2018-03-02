package servlet;

import DAO.ProductDAO;
import bean.OrderItem;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderItemAddServlet", urlPatterns = "/addOrderItem")
public class OrderItemAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = request.getParameter("data");
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        int num = Integer.parseInt(jsonObject.getString("num"));
        int pid = Integer.parseInt(jsonObject.getString("pid"));
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(new ProductDAO().getProduct(pid));
        orderItem.setNum(num);
        List<OrderItem> orderItemList = (List<OrderItem>) request.getSession().getAttribute("orderItemList");
        boolean found = false;
        if(null != orderItemList){
            for(OrderItem orderItem1 : orderItemList){
                if(orderItem1.getProduct().getId() == orderItem.getProduct().getId() ){
                    orderItem1.setNum(orderItem1.getNum()+orderItem.getNum());
                    found = true;
                    break;
                }
            }
        }else {
            orderItemList = new ArrayList<OrderItem>();
            request.getSession().setAttribute("orderItemList", orderItemList);
        }

        if(!found) orderItemList.add(orderItem);
//        response.sendRedirect("/listOrderItem");
    }
}
