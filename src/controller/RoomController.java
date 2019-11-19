package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoomInfoService;
import service.SitService;
import service.impl.RoomInfoServiceImpl;
import service.impl.SitServiceImpl;
import vo.CinemaInfo;
import vo.RoomInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/18 22:01
 */
@Controller
@RequestMapping("room")
public class RoomController {
    RoomInfoService service = new RoomInfoServiceImpl();

    /**
     * 查询影厅列表
     * @param request
     * @return
     */
    @RequestMapping("/showRoomList")
    public String showRoomList(HttpServletRequest request){
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        List<RoomInfo> roomInfoList = service.queryRoomListByCId(cinemaId);
        request.setAttribute("roomInfoList",roomInfoList);
        return "cinema-room-list";
    }
    /**
     * 查看影厅座位分布
     * @param request
     * @param roomId
     * @return
     */
    @RequestMapping("/queryRoomSit")
    public String queryRoomSit(HttpServletRequest request, @RequestParam("roomId") int roomId){
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        SitService sitService=new SitServiceImpl();
        //影厅信息
        List<RoomInfo> roomInfos = sitService.queryRoomInfo(cinemaId,roomId);
        request.setAttribute("roomInfos",roomInfos);
        return "cinema-room-show";
    }

    /**
     * 新增影厅
     * @param request
     * @param rId
     * @param rowSit
     * @param rowNum
     * @return
     */
    @RequestMapping("/addRoom")
    @ResponseBody
    public String addRoom(HttpServletRequest request, @RequestParam("roomId") String rId,@RequestParam("rowSit") String rowSit,@RequestParam("rowNum") String rowNum){
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        int roomId = Integer.parseInt(rId);
        String[] rowSits = rowSit.split(",");
        String[] rowNums = rowNum.split(",");
        for (int i = 0; i < rowSits.length; i++) {
            int rNum=Integer.parseInt(rowNums[i]);
            String rSit=rowSits[i];
            boolean flag = service.addRoom(roomId,rNum,rSit,cinemaId);
            if (!flag){
                return "false";
            }
        }
        return "success";
    }

    /**
     * 查询影厅编号是否已存在
     * @param rId
     * @param request
     * @return
     */
    @RequestMapping("/queryRoomIdIsExist")
    @ResponseBody
    public String queryRoomIdIsExist(@RequestParam("roomId") String rId,HttpServletRequest request){
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        int roomId = Integer.parseInt(rId);
        RoomInfo roomInfo = service.queryRIdIsExist(roomId,cinemaId);
        if (roomInfo!=null){
            return "false";
        }
        return "success";
    }
    @RequestMapping("deleteRoom")
    @ResponseBody
    public String deleteRoom(@RequestParam("roomId") String rId,HttpServletRequest request){
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        int roomId = Integer.parseInt(rId);
        boolean flag = service.deleteRoom(roomId,cinemaId);
        if (flag) {
            return "success";
        }
        return "false";
    }
}
