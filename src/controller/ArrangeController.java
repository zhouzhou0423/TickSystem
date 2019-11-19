package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ArrangeService;
import service.MovieInfoService;
import service.impl.ArrangeServiceImpl;
import service.impl.MovieInfoServiceImpl;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/3/31 15:59
 */
@Controller
@RequestMapping("arrange")
public class ArrangeController {
    ArrangeService service  = new ArrangeServiceImpl();
    @RequestMapping("/queryArrange")
    public String arrange(@RequestParam("arrangeId") String aId, HttpServletRequest request) throws Exception {
        int arrangeId=Integer.parseInt(aId);
        CinemaInfo cinemaInfo= (CinemaInfo) request.getSession().getAttribute("cinema");
        ArrangedInfo arrangedInfo= service.findArrangeBy(cinemaInfo.getCinemaid(),arrangeId);
        request.setAttribute("arrangedInfo",arrangedInfo);
        return "arrange-edit";
    }
    @RequestMapping("/arrangeEdit")
    @ResponseBody
    public String arrangeEdit(@RequestParam("arrangeId") String aId,@RequestParam("roomId") String rId,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("moviePrice") String mPrice) throws Exception {
        int arrangeId=Integer.parseInt(aId);
        int roomId=Integer.parseInt(rId);
        double moviePrice=Double.valueOf(mPrice);
        boolean flag=service.arrangeEdit(arrangeId,roomId,startTime,endTime,moviePrice);
        String res="error";
        if (flag){
            res="success";
        }
        return res;
    }
    @RequestMapping("/showAddPage")
    public String arrangeEdit(HttpServletRequest request) throws Exception {
        CinemaInfo cinemaInfo= (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        List<RoomBaseInfo> rooms=service.findRoomByCinemaId(cinemaId);
        MovieInfoService movieInfoService=new MovieInfoServiceImpl();
        List<MovieInfo> movieNames=movieInfoService.findMoviesByCinemaid(cinemaId);
        request.setAttribute("rooms",rooms);
        request.setAttribute("movieNames",movieNames);
        return "arrange-add";
    }
    @RequestMapping("/arrangeAdd")
    @ResponseBody
    public String arrangeEdit(@RequestParam("ondate") String ondate,@RequestParam("roomId") String rId,@RequestParam("movieId") String mId,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("moviePrice") String mPrice,HttpServletRequest request) throws Exception {
        CinemaInfo cinemaInfo= (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        int roomId=Integer.parseInt(rId);
        int movieId=Integer.parseInt(mId);
        double moviePrice=Double.valueOf(mPrice);
        ArrangeBaseInfo arrangeBaseInfo=new ArrangeBaseInfo();
        arrangeBaseInfo.setCinemaid(cinemaId);
        arrangeBaseInfo.setMovieid(movieId);
        arrangeBaseInfo.setStarttime(startTime);
        arrangeBaseInfo.setEndtime(endTime);
        arrangeBaseInfo.setRoomid(roomId);
        arrangeBaseInfo.setOndate(ondate);
        arrangeBaseInfo.setMovieprice(moviePrice);
        boolean flag=service.addArrange(arrangeBaseInfo);
        String res="error";
        if (flag){
            res="success";
        }
        return res;
    }
    @RequestMapping("/queryTimeLimit")
    @ResponseBody
    public String queryTimeLimit(@RequestParam("movieId") String mId,HttpServletRequest request) throws Exception{
        int movieId=Integer.parseInt(mId);
        MovieInfoService movieInfoService=new MovieInfoServiceImpl();
        CinemaInfo cinemaInfo= (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        List<MovieInfo> movieNames=movieInfoService.findMoviesByCinemaid(cinemaId);
        String res="";
        for (int i = 0; i < movieNames.size(); i++) {
            if(movieNames.get(i).getMovieid()==movieId){
                res=movieNames.get(i).getTimelimit()+","+movieNames.get(i).getReleasetime();
                break;
            }
        }
        return  res;
    }
    @RequestMapping("/queryArrangeExist")
    @ResponseBody
    public String queryRoomArrangeExist(@RequestParam("ondate") String onDate,@RequestParam("roomId") String rId,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,HttpServletRequest request){
        CinemaInfo cinemaInfo= (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        int roomId=Integer.parseInt(rId);
        boolean flag=service.queryRoomArrangeExist(cinemaId,roomId,onDate,startTime,endTime);
        if(flag){
            return "success";
        }
        return "error";
    }
    @RequestMapping("/deleteArrange")
    @ResponseBody
    public String queryRoomArrangeExist(@RequestParam("arrangeId") String aId){
        int arrangeId = Integer.parseInt(aId);
        boolean flag=service.deleteArrange(arrangeId);
        if(flag){
            return "success";
        }
        return "error";
    }
}
