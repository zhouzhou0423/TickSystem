package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.ArrangeService;
import service.CinemaInfoService;
import service.MovieInfoService;
import service.impl.ArrangeServiceImpl;
import service.impl.CinemaInfoServiceImpl;
import service.impl.MovieInfoServiceImpl;
import vo.ArrangedInfo;
import vo.CinemaInfo;
import vo.MovieInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhou_zhou
 * @date 2019/3/29 14:25
 */
@Controller
@RequestMapping("cinemaInfo")
public class CinemaInfoController {
    CinemaInfoService cinemaInfoService=new CinemaInfoServiceImpl();
    /**
     * 按照地区查找影院
     * @param request
     * @throws Exception
     */
    @RequestMapping("/findByArea")
    public String findByArea(@RequestParam("keyword") String keyword,@RequestParam("seq") String seq,@RequestParam("movieId") String mId,HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        List<CinemaInfo> cinemaInfos=null;
        String movieId="";
        if (!mId.equals("all")){
            movieId=mId;
        }
        if("all".equals(keyword)){
            cinemaInfos=cinemaInfoService.findAll(movieId);
        }else{
            cinemaInfos =cinemaInfoService.findByArea(keyword,movieId);
        }
        request.setAttribute("seq",seq);
        request.setAttribute("cinemaInfos",cinemaInfos);
        request.setAttribute("mId",mId);
        return "filmPage";
    }
    /**
     * 跳转至影院详情页
     * @param request
     * @throws Exception
     */
    @RequestMapping("/showCinemaDetail")
    public String showCinemaDetail(@RequestParam("cinemaId") String cId,HttpServletRequest request) throws Exception {
        int cinemaId = Integer.parseInt(cId);
        CinemaInfo cinemaInfo=cinemaInfoService.findByCinemaID(cinemaId);
        MovieInfoService movieInfoService = new MovieInfoServiceImpl();
        List<MovieInfo> movieInfoList = movieInfoService.findMoviesByCinemaid(cinemaId);
        ArrangeService arrangeService = new ArrangeServiceImpl();

        Date date =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(date);

        List<ArrangedInfo> arrangedInfoList=null;
        if (movieInfoList.size()!=0){
            arrangedInfoList=arrangeService.queryArrangeByMovieId(cinemaId,movieInfoList.get(0).getMovieid(),dateTime);
        }
        request.setAttribute("cinemaInfo",cinemaInfo);
        request.setAttribute("movieInfoList",movieInfoList);
        request.setAttribute("arrangedInfoList",arrangedInfoList);
        return "cinemaDetail";
    }

    @RequestMapping("/showArrange")
    @ResponseBody
    public List<ArrangedInfo> showArrange(@RequestParam("cinemaId") String cId,@RequestParam("movieId") String mId,@RequestParam("dateStr") String dateStr,HttpServletRequest request) throws Exception {
        int movieId = Integer.parseInt(mId);
        int cinemaId = Integer.parseInt(cId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        if (dateStr.equals("today")) {
            calendar.add(Calendar.DATE, 0);
        }
        else if(dateStr.equals("tomorrow")){
            calendar.add(Calendar.DATE,1);
        }
        else if (dateStr.equals("thirdDay")){
            calendar.add(Calendar.DATE,2);
        }
        String dateTime=sdf.format(calendar.getTime());

        ArrangeService arrangeService = new ArrangeServiceImpl();
        List<ArrangedInfo> arrangedInfoList=arrangeService.queryArrangeByMovieId(cinemaId,movieId,dateTime);
        request.setAttribute("arrangedInfoList",arrangedInfoList);
        return arrangedInfoList;
    }

    /**
     * 跳转至修改影院信息页面
     * @param cId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/toEditCinema")
    public String toEditPage(@RequestParam("cinemaId") String cId,HttpServletRequest request) throws Exception {
        int cinemaId = Integer.parseInt(cId);
        CinemaInfo cinemaInfo=cinemaInfoService.findByCinemaID(cinemaId);
        request.setAttribute("cinemaInfo",cinemaInfo);
        return "web-cinema-edit";
    }

    /**
     * 修改影院信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/editCinema")
    @ResponseBody
    public String editCinema(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        int cinemaId = Integer.parseInt(multipartRequest.getParameter("cinemaId"));
        String cinemaName = multipartRequest.getParameter("cinemaName");
        String cinemaTel = multipartRequest.getParameter("cinemaTel");
        String cinemaAddr = multipartRequest.getParameter("cinemaAddr");
        String cinemaIntro = multipartRequest.getParameter("cinemaIntro");
        String cinemaPwd = multipartRequest.getParameter("cinemaPwd");
        String img = multipartRequest.getParameter("uploadfile-2");
        String cinemaImg="";
        if (file.getSize()==0){
            cinemaImg=img;
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
            cinemaImg = "upload/" + newFileName;
        }
        CinemaInfo cinemaInfo = new CinemaInfo();
        cinemaInfo.setCinemaid(cinemaId);
        cinemaInfo.setCinemaname(cinemaName);
        cinemaInfo.setCinematel(cinemaTel);
        cinemaInfo.setCinemaintro(cinemaIntro);
        cinemaInfo.setCinemaaddr(cinemaAddr);
        cinemaInfo.setCinemaimg(cinemaImg);
        cinemaInfo.setCinemapwd(cinemaPwd);
        boolean flag = cinemaInfoService.editCinemaInfo(cinemaInfo);
        String res = "false";
        if (flag){
            res="success";
        }
        return res;
    }

    /**
     * 删除影院
     * @param cId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteCinema")
    @ResponseBody
    public String deleteCinema(@RequestParam("cinemaId") String cId) throws Exception {
        int cinemaId = Integer.parseInt(cId);
        boolean flag=cinemaInfoService.deleteCinema(cinemaId);
        String res = "false";
        if (flag){
            res="success";
        }
        return res;
    }
    @RequestMapping("/addMovie")
    @ResponseBody
    public String addMovie(@RequestParam("movieId") String mId,HttpServletRequest request) throws Exception {
        int movieId = Integer.parseInt(mId);
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        boolean flag = cinemaInfoService.addMovie(cinemaId,movieId);
        String res = "false";
        if (flag){
            res="success";
        }
        return res;
    }
    @RequestMapping("/deleteMovie")
    @ResponseBody
    public String deleteMovie(@RequestParam("movieId") String mId,HttpServletRequest request) throws Exception {
        int movieId = Integer.parseInt(mId);
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        boolean flag=cinemaInfoService.deleteMovie(cinemaId,movieId);
        String res = "false";
        if (flag){
            res="success";
        }
        return res;
    }
    @RequestMapping("/batchAdd")
    @ResponseBody
    public String batchAdd(@RequestParam("ids") int[] ids,HttpServletRequest request) throws Exception {
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        int cinemaId=cinemaInfo.getCinemaid();
        boolean flag;
        for (int i = 0; i < ids.length; i++) {
            flag=cinemaInfoService.addMovie(cinemaId,ids[i]);
            if (!flag){
               return "false";
            }
        }
        return "success";
    }
}
