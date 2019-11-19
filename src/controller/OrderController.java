package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.OrderService;
import service.SitService;
import service.impl.OrderServiceImpl;
import service.impl.SitServiceImpl;
import vo.OrderInfo;
import vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/20 22:36
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @RequestMapping("/queryOrderList")
    public String queryOrderList(HttpServletRequest request){
        SitService sitService = new SitServiceImpl();
        int orderState=1;
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        int userId = userInfo.getUserid();
        List<OrderInfo> orderInfoList = sitService.queryOrderList(userId,orderState);
        request.setAttribute("orderInfoList",orderInfoList);
        return "user-order-list";
    }
    @RequestMapping("/queryAllOrder")
    public String queryAllOrder(HttpServletRequest request){
        OrderService service = new OrderServiceImpl();
        int orderState=1;
        List<OrderInfo> orderInfoList = service.queryOrderList(orderState);
        request.setAttribute("orderInfoList",orderInfoList);
        return "cinema-order-list";
    }
}
