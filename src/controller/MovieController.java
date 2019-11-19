package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ActorInfoService;
import service.CommentsInfoService;
import service.MovieInfoService;
import service.impl.ActorInfoServiceImpl;
import service.impl.CommentsInfoServiceImpl;
import service.impl.MovieInfoServiceImpl;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/3/29 9:23
 */
@Controller
@RequestMapping("movie")
public class MovieController {
    MovieInfoService service=new MovieInfoServiceImpl();
    /**
     * 加载首页影片
     * @param request
     * @throws Exception
     */
    @RequestMapping("/load")
    public String load(HttpServletRequest request) throws Exception {
        List<MovieInfo> topMovies=service.queryTitleMovie();
        List<MovieInfo> hotMovies=service.queryHotMovie();
        List<MovieInfo> wantMovies=service.queryWantMovie();
        List<MovieInfo> todayTopMovies=service.queryTodayTop();
        List<MovieInfo> todayMostMovies=service.queryTodayMost();

        request.setAttribute("topMovies",topMovies);
        request.setAttribute("hotMovies",hotMovies);
        request.setAttribute("wantMovies",wantMovies);
        request.setAttribute("todayTopMovies",todayTopMovies);
        request.setAttribute("todayMostMovies",todayMostMovies);

        return "head";
    }
    /**
     * 展示影片详情
     * @param request
     * @throws Exception
     */
    @RequestMapping("/showdetail")
    public String showdetail(@RequestParam("orign") String orign, @RequestParam("movieid") String movieid, HttpServletRequest request) throws Exception {
        int mid=Integer.parseInt(movieid);
        List<MovieInfo> list =null;
        MovieInfo moviedetail=null;
        if(orign.equals("top")){
            list=service.queryTitleMovie();
        }else if(orign.equals("hot")){
            list=service.queryHotMovie();

        }else if(orign.equals("want")){
            list=service.queryWantMovie();

        }else if(orign.equals("todayTop")){
            list=service.queryTodayTop();

        }else if(orign.equals("todayMost")){
            list=service.queryTodayMost();
        }else if(orign.equals("other")){
            list=service.findAllMovies();
        }
        for (int i = 0; i < list.size(); i++) {
            if(mid==list.get(i).getMovieid()){
                moviedetail=list.get(i);
                break;
            }

        }

        List<RoleInfo> acList=null;
        ActorInfoService infoService=new ActorInfoServiceImpl();
        acList=infoService.findByMId(mid);
        //导演
        RoleInfo moviedirector = acList.get(0);
        List<RoleInfo> actors=new ArrayList<>();
        for (int i = 0; i < acList.size(); i++) {
            if(acList.get(i).getIsimportant()!=0){
                actors.add(acList.get(i));
            }
        }
        CommentsInfoService commentsInfoService=new CommentsInfoServiceImpl();
        List<CommentsInfo> comments=commentsInfoService.findCommentsByMid(mid);

        UserInfo user= (UserInfo) request.getSession().getAttribute("user");
        if (user!=null){
            List<ZanInfo> zanInfos=commentsInfoService.queryIsZan(user.getUserid());
            //已点赞的评论
            request.setAttribute("zanInfos",zanInfos);
            boolean flag= service.queryIsWantLook(user.getUserid(),mid);
            request.setAttribute("isWantLook",flag);
        }
        //影片详情
        request.setAttribute("moviedetail",moviedetail);
        //导演
        request.setAttribute("moviedirector",moviedirector);
        //演员
        request.setAttribute("actors",actors);
        //评论
        request.setAttribute("comments",comments);
        return "detail";
    }

    /**
     * 查询影院的影片列表
     * @param request
     * @param response
     */
    @RequestMapping("/findMovies")
    public String findMovies(@RequestParam("cinemaId") String cId,HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        int cinemaId=Integer.parseInt(cId);
        List<MovieInfo> cinemaMoviesList=service.findMoviesByCinemaid(cinemaId);

        //影院所有影片
        request.setAttribute("cinemaMoviesList",cinemaMoviesList);

        return "picture-list";
    }
    @RequestMapping("/findMoviesByType")
    public String findMoviesByType(HttpServletRequest request,@RequestParam("typeId") int typeId) throws Exception {
        List<MovieInfo> movieInfoList = null;
        if(typeId==0){
            movieInfoList=service.findAllMovies();
        }else {
            movieInfoList=service.findMoviesBytypeId(typeId);
        }
        List<MovieTypeInfo> typeList = service.findMovieType();
        request.setAttribute("typeId",typeId);
        request.setAttribute("typeList",typeList);
        request.setAttribute("movieInfoList",movieInfoList);
        return "moviePage";
    }

    /**
     * 展示影院未选择的影片
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/showAddMoviePage")
    public String showAddMoviePage(HttpServletRequest request) throws Exception {
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId = cinemaInfo.getCinemaid();
        List<MovieInfo> movieInfos=service.findNoMovies(cinemaId);
        request.setAttribute("movieInfos",movieInfos);
        return "movie-add";
    }
}
