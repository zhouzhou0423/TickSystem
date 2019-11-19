package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.SitService;
import service.impl.SitServiceImpl;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author zhou_zhou
 * @date 2019/4/4 11:12
 */
@Controller
@RequestMapping("sit")
public class SitController {
    SitService sitService=new SitServiceImpl();
    @RequestMapping("/showSitPage")
    public String showSitPage(@RequestParam("arrangeId") String aId,@RequestParam("cinemaId") String cId,@RequestParam("roomId") String rId, HttpServletRequest request){
        int cinemaId=Integer.parseInt(cId);
        int roomId=Integer.parseInt(rId);
        //选中的影片信息
        int arrangeId = Integer.parseInt(aId);
        ArrangedInfo arrangedInfo = sitService.queryArrangeByAId(arrangeId);
        //影厅信息
        List<RoomInfo> roomInfos = sitService.queryRoomInfo(cinemaId,roomId);
        //已被购买或者已被选择的座位
        List<SitInfo> sitInfos = sitService.querySitInfo(arrangeId,roomId);
        request.setAttribute("arrangedInfo",arrangedInfo);
        request.setAttribute("roomInfos",roomInfos);
        request.setAttribute("sitInfos",sitInfos);
        return "sit";
    }
    @RequestMapping("/buyTickets")
    @ResponseBody
    public String buyTickets(@RequestParam("sits") String sits,@RequestParam("arrangeId") String aId,@RequestParam("roomId") String rId,@RequestParam("totalPrice") double totalPrice, HttpServletRequest request){
        String[] sit=sits.split(",");
        int roomId = Integer.parseInt(rId);
        int arrangeId = Integer.parseInt(aId);
        for (int i = 0; i < sit.length; i++) {
            SitInfo sitInfo = new SitInfo();
            sitInfo.setRoomid(roomId);
            sitInfo.setArrangeid(arrangeId);
            sitInfo.setSitposition(sit[i]);
            sitInfo.setState(2);
            boolean flag=sitService.updateSitState(sitInfo);
            if (!flag){
                return "false";
            }
        }
        //自动生成订单编号
        String orderTime=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        String orderId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+new Random().nextInt(100);
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        int userId = userInfo.getUserid();
        int num = sit.length;
        OrderInfo orderInfo = new OrderInfo(orderId,userId,num,totalPrice,arrangeId,sits,0,orderTime);
        boolean flag=sitService.addOrder(orderInfo);
        if (!flag){
            return "false";
        }
        return "success";
    }
    @RequestMapping("/queryOrderList")
    public String queryOrderList(HttpServletRequest request,@RequestParam("orderState") int orderState,@RequestParam("origin") int origin){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        int userId = userInfo.getUserid();
        List<OrderInfo> orderInfoList = sitService.queryOrderList(userId,orderState);
        request.setAttribute("orderInfoList",orderInfoList);
        if (origin==0){
            return "shoppingCart-list";
        }else {
            return "user-shoppingCart-list";
        }
    }

    /**
     * 删除订单
     * @param orderId
     * @param aId
     * @param sits
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(@RequestParam("orderId") String orderId,@RequestParam("arrangeId") String aId,@RequestParam("sitposition") String sits) throws Exception {
        int arrangeId=Integer.parseInt(aId);
        //删除订单
        boolean flag = sitService.deleteOrder(orderId);
        if (!flag){
            return "false";
        }
        //释放座位
        String[] sit = sits.split(",");
        for (int i = 0; i < sit.length; i++) {
            boolean flag2 = sitService.deleteSit(arrangeId,sit[i]);
            if (!flag2){
                return "false";
            }
        }

        return "success";
    }
}
