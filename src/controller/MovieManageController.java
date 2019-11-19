package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.MovieManageService;
import service.impl.MovieManageServiceImpl;
import vo.CinemaInfo;
import vo.MovieInfo;
import vo.MovieTypeInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author zhou_zhou
 * @date 2019/4/2 16:28
 */
@Controller
@RequestMapping("movieManage")
public class MovieManageController {
    MovieManageService service = new MovieManageServiceImpl();
    @RequestMapping("/changeState")
    @ResponseBody
    public String changeState(@RequestParam("movieid") String mid,@RequestParam("cmstate") String s,HttpServletRequest request) throws Exception {
        int movieId = Integer.parseInt(mid);
        int cmstate = Integer.parseInt(s);
        CinemaInfo cinemaInfo= (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        boolean flag=service.changeState(movieId,cmstate,cinemaId);
        if(flag){
            return "true";
        }
        return "false";
    }
    @RequestMapping("/showMoviePage")
    public String showMoviePage(HttpServletRequest request) throws Exception {
        List<MovieTypeInfo> typeList=service.queryMovieType();
        request.setAttribute("typeList",typeList);
        return "web-movie-add";
    }
    /**
     * 新增影片
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploads")
    @ResponseBody
    public String uploads(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        String movieName=multipartRequest.getParameter("movieName");
        int timeLimit = Integer.parseInt(multipartRequest.getParameter("timeLimit"));
        String movieIntro = multipartRequest.getParameter("movieIntro");
        String releaseTime = multipartRequest.getParameter("releaseTime");
        String typeId = multipartRequest.getParameter("typeId");
        String nation = multipartRequest.getParameter("nation");
        /*指定文件上传路径*/
        String path=request.getServletContext().getRealPath("/upload");
        String fileName=file.getOriginalFilename();
        // 获取上传文件的后缀名
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        // 给文件定义一个新的名称,杜绝文件重名覆盖现象
        String newFileName= UUID.randomUUID().toString()+suffix;
        File f=new File(path+"\\" + newFileName);
        //将上传文件写入文件
        file.transferTo(f);
        String movieImg = "upload/"+newFileName;
        boolean flag = service.webAddMovie(movieName,timeLimit,movieIntro,releaseTime,typeId,movieImg,nation);
        String res="false";
        if (flag){
            res="success";
        }
        return res;
    }
    @RequestMapping("/toEditMovieInfo")
    public String editMovieInfo(@RequestParam("movieId") String mid,HttpServletRequest request) throws Exception {
        int movieId = Integer.parseInt(mid);
        MovieInfo movie = service.queryMovieByMId(movieId);
        request.setAttribute("movie",movie);
        return "web-movie-edit";
    }
    @RequestMapping("/updateMovieInfo")
    @ResponseBody
    public String updateMovieInfo(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        String movieName=multipartRequest.getParameter("movieName");
        String movieIntro = multipartRequest.getParameter("movieIntro");
        String releaseTime = multipartRequest.getParameter("releaseTime");
        String nation = multipartRequest.getParameter("nation");
        int movieId = Integer.parseInt(multipartRequest.getParameter("movieId"));
        String img = multipartRequest.getParameter("uploadfile-2");
        String movieImg="";
        if (file.getSize()==0){
            movieImg=img;
        }else {
            /*指定文件上传路径*/
            String path = request.getServletContext().getRealPath("/upload");
            String fileName = file.getOriginalFilename();
            // 获取上传文件的后缀名
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            // 给文件定义一个新的名称,杜绝文件重名覆盖现象
            String newFileName = UUID.randomUUID().toString() + suffix;
            File f = new File(path + "\\" + newFileName);
            //将上传文件写入文件
            file.transferTo(f);
            movieImg = "upload/" + newFileName;
        }
        boolean flag = service.updateMovieInfo(movieName,movieIntro,releaseTime,movieImg,nation,movieId);
        String res="false";
        if (flag){
            res="success";
        }
        return res;
    }

    /**
     * 删除影片
     * @param mId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteMovie")
    @ResponseBody
    public String deleteActor(@RequestParam("movieId") String mId) throws Exception {
        int movieId=Integer.parseInt(mId);
        boolean flag = service.deleteMovie(movieId);
        String res="false";
        if(flag){
            res="success";
        }
        return res;
    }
}
