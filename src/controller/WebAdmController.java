package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserInfoService;
import service.WebAdmService;
import service.impl.UserInfoServiceImpl;
import service.impl.WebAdmServiceImpl;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/12 11:09
 */
@Controller
@RequestMapping("webAdm")
public class WebAdmController {
    WebAdmService service = new WebAdmServiceImpl();
    @RequestMapping("/findAllCinema")
    public String findAllCinema(HttpServletRequest request){
        List<CinemaInfo> cinemaInfoList = service.findAllCinema();
        request.setAttribute("cinemaInfoList",cinemaInfoList);
        return "web-cinema-list";
    }
    @RequestMapping("/findNewCinema")
    public String findNewCinema(HttpServletRequest request){
        List<CinemaInfo> cinemaInfoList = service.findNewCinema();
        request.setAttribute("cinemaInfoList",cinemaInfoList);
        return "new_cinema-list";
    }
    @RequestMapping("/changeCinemaState")
    @ResponseBody
    public String changeCinemaState( @RequestParam("cinemaId") int cinemaId){
        boolean flag = service.changeCinemaState(cinemaId);
        String res = "false";
        if(flag){
            res="true";
        }
        return res;
    }
    @RequestMapping("/findAllMovies")
    public String findAllMovies(HttpServletRequest request) throws Exception {
        List<MovieInfo> movieInfos=service.findAllMovies();
        request.setAttribute("movieInfos",movieInfos);
        return "web-movie-list";
    }
    @RequestMapping("/findAllComments")
    public String findAllComments(HttpServletRequest request) throws Exception {
        List<CommentsInfo> commentsInfoList=service.findAllComment();
        request.setAttribute("commentsInfoList",commentsInfoList);
        return "web-comment-list";
    }
    @RequestMapping("/findAllActor")
    public String findAllActor(HttpServletRequest request) throws Exception {
        List<ActorInfo> actorInfoList = service.findAllActor();
        request.setAttribute("actorInfoList",actorInfoList);
        return "web-actor-list";
    }
    @RequestMapping("/findAllRole")
    public String findAllRole(HttpServletRequest request) throws Exception {
        List<RoleInfo> roleInfoList = service.findAllRole();
        request.setAttribute("roleInfoList",roleInfoList);
        return "web-role-list";
    }
    @RequestMapping("/findAllUser")
    public String findAllUser(HttpServletRequest request) throws Exception {
        UserInfoService userInfoService = new UserInfoServiceImpl();
        List<UserInfo> userInfoList = userInfoService.findAllUser();
        request.setAttribute("userInfoList",userInfoList);
        return "web-user-list";
    }
}
