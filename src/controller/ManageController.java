package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.ManageService;
import service.impl.ManageServiceImpl;
import vo.AdmInfo;
import vo.ArrangedInfo;
import vo.CinemaInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhou_zhou
 * @date 2019/3/31 12:35
 */
@Controller
@RequestMapping("manage")
public class ManageController {
    ManageService service=new ManageServiceImpl();
    /**
     * ����Ա��¼
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("cinematel") String cinematel, @RequestParam("cinemapwd") String cinemapwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        CinemaInfo cinema=service.login(cinematel, cinemapwd);
        String result="error";
        if(cinema!=null){
            request.getSession().setAttribute("cinema", cinema);
            result="success";
        }return result;
    }
    @RequestMapping("/webLogin")
    @ResponseBody
    public String webLogin(@RequestParam("admtel") String admtel, @RequestParam("admpwd") String admpwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        AdmInfo webAdm=service.webLogin(admtel, admpwd);
        String result="error";
        if(webAdm!=null){
            request.getSession().setAttribute("webAdm", webAdm);
            result="success";
        }return result;
    }

    /**
     * ����û��ֻ����Ƿ����
     * @param cinematel
     * @param cId
     * @return
     * @throws Exception
     */
    @RequestMapping("/checkCinemaTel")
    @ResponseBody
    public String checkCinemaTel(@RequestParam("cinematel") String cinematel,@RequestParam("cinemaId") String cId) throws Exception {
        int cinemaId = Integer.parseInt(cId);
        boolean flag=service.checkCinemaTel(cinematel,cinemaId);
        String result="success";
        if(flag){
            result="error";
        }
        return result;
    }
    /**
     * ע��
     * @param response
     * @throws Exception
     */
    @RequestMapping("/reg")
    @ResponseBody
    public String reg(HttpServletRequest request,@RequestParam("regcinemaname") String cinemaname,@RequestParam("regcinematel") String cinematel, @RequestParam("regcinemapwd") String cinemapwd,HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        boolean flag=service.reg(cinemaname,cinematel, cinemapwd);
        String result="error";
        if(flag){
            result="success";
            CinemaInfo cinema=service.login(cinematel, cinemapwd);
            request.getSession().setAttribute("cinema",cinema);
        }
        return result;
    }
    /**
     * ��ѯӰԺ�����б�
     * @param request
     */
    @RequestMapping("/findArrange")
    public String findArrange(HttpServletRequest request) throws Exception{
        CinemaInfo cinemaInfo= (CinemaInfo) request.getSession().getAttribute("cinema");
        List<ArrangedInfo> list=service.findArrangeInfo(cinemaInfo.getCinemaid());
        List<ArrangedInfo> arrangedList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            arrangedList.add(list.get(i));
        }
        //�����б�
        request.setAttribute("arrangedList",arrangedList);
        return "arrange-list";
    }
    @RequestMapping("/updateCinemaInfo")
    @ResponseBody
    public String updateCinemaInfo(HttpServletRequest request, HttpSession session) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CinemaInfo cinemaInfo = (CinemaInfo) request.getSession().getAttribute("cinema");
        MultipartFile file=multipartRequest.getFile("file");
        String moreAddr=multipartRequest.getParameter("moreAddr");
        String cinemaAddr = multipartRequest.getParameter("cinemaAddr");
        String cinemaIntro=multipartRequest.getParameter("cinemaIntro");
        String addr=cinemaAddr+moreAddr;
        String img = multipartRequest.getParameter("uploadfile-2");
        String cineamImg="";
        if (file.getSize()==0){
            cineamImg=img;
        }else {
            /*ָ���ļ��ϴ�·��*/
            String path = request.getServletContext().getRealPath("/upload");
            String fileName = file.getOriginalFilename();
            // ��ȡ�ϴ��ļ��ĺ�׺��
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            // ���ļ�����һ���µ�����,�ž��ļ�������������
            String newFileName = UUID.randomUUID().toString() + suffix;

            File f = new File(path + "\\" + newFileName);
            //���ϴ��ļ�д���ļ�
            file.transferTo(f);
            cineamImg = "upload/" + newFileName;
        }
        cinemaInfo.setCinemaaddr(addr);
        cinemaInfo.setCinemaintro(cinemaIntro);
        cinemaInfo.setCinemaimg(cineamImg);
        boolean flag=service.updateCinemaInfo(cinemaInfo);
        String res="false";
        if (flag){
            res="success";
        }
        return res;
    }

    /**
     * �˳���¼
     * @param request
     */
    @RequestMapping("/cinemaLoginOut")
    public String cinemaLoginOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("cinema");
        return "admlogin";
    }
    /**
     * �˳���¼
     * @param request
     */
    @RequestMapping("/webLoginOut")
    public String webLoginOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("webAdm");
        return "webLogin";
    }

}
