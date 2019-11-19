package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.MovieInfoService;
import service.UserInfoService;
import service.impl.MovieInfoServiceImpl;
import service.impl.UserInfoServiceImpl;
import vo.CommentsInfo;
import vo.MovieInfo;
import vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author zhou_zhou
 * @date 2019/3/28 21:53
 */
@Controller
@RequestMapping("user")
public class UserController {
    UserInfoService userInfoService=new UserInfoServiceImpl();
    /**
     * ��¼
     * @param usertel
     * @param userpwd
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(String usertel, String userpwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        System.out.println("��¼�˻�"+usertel);
        UserInfo user=userInfoService.login(usertel,userpwd);
        String result="error";
        if(user!=null){
            request.getSession().setAttribute("user", user);
        result="success";
        }
        return result;
    }
    /**
     * ע��
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/reg")
    @ResponseBody
    public String reg(@RequestParam("regusername") String username,@RequestParam("reguserpwd") String userpwd,@RequestParam("regusertel") String usertel,HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        boolean flag=userInfoService.reg(username, userpwd,usertel);
        String result="error";
        if(flag){
            UserInfo user=userInfoService.login(usertel,userpwd);
            request.getSession().setAttribute("user", user);
            result="success";
        }
        return result;
    }
    /**
     * ��ѯ�û�����
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/findPwd",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findPwd(@RequestParam("username") String username,@RequestParam("usertel") String usertel,@RequestParam("code") String code,HttpServletRequest request,HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        String yzm=(String) request.getSession().getAttribute("rand");
        String res=null;
        if(!code.equals(yzm)){
            res="��֤�����";
            return res;
        }
        UserInfo user=null;
        user=userInfoService.findpwd(username, usertel);
        if(user!=null){
            res=user.getUsername()+"������Ϊ:"+user.getUserpwd();
        }else{
            res="�û������ֻ����벻��ȷ";
        }
        return res;
    }
    /**
     * ����û��ֻ����Ƿ����
     * @throws Exception
     */
    @RequestMapping("/checkUserTel")
    @ResponseBody
    public String checkUserTel(@RequestParam("usertel") String usertel,@RequestParam("userId") String uid) throws Exception {
        int userId=Integer.parseInt(uid);
        boolean flag=userInfoService.checkUserTel(usertel,userId);
        String res="success";
        if(flag){
            res="error";
        }
        return res;
    }

    /**
     * ����û����Ƿ����
     * @throws Exception
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(@RequestParam("username") String username,@RequestParam("userId") String uid) throws Exception {
        int userId=Integer.parseInt(uid);
        boolean flag=userInfoService.checkUserName(username,userId);
        String res="success";
        if(flag){
            res="error";
        }
        return res;
    }
    /**
     * �˳���¼
     * @param request
     */
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        return "login";
    }
    /**
     * ��ת���û���Ϣ�޸�ҳ
     * @param uId
     * @param userName
     * @param request
     * @return
     */
    @RequestMapping("/toEditUser")
    public String toEditActor(@RequestParam("userId") String uId,@RequestParam("userName") String userName,@RequestParam("userTel") String userTel,@RequestParam("userPwd") String userPwd,@RequestParam("userPhoto") String userPhoto,HttpServletRequest request){
        int userId=Integer.parseInt(uId);
        UserInfo userInfo = new UserInfo(userId,userName,userPwd,userTel,userPhoto);
        request.setAttribute("userInfo",userInfo);
        return "web-user-edit";
    }

    /**
     * �޸��û���Ϣ
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        int userId = Integer.parseInt(multipartRequest.getParameter("userId"));
        String userName=multipartRequest.getParameter("userName");
        String userPwd=multipartRequest.getParameter("userPwd");
        String userTel = multipartRequest.getParameter("userTel");
        String img = multipartRequest.getParameter("uploadfile-2");
        String userPhoto="";
        if (file.getSize()==0){
            userPhoto=img;
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
            userPhoto = "upload/" + newFileName;
        }
        UserInfo userInfo = new UserInfo(userId,userName,userPwd,userTel,userPhoto);
        boolean flag=userInfoService.editUserInfo(userInfo);
        String res="false";
        if(flag){
            UserInfo user= (UserInfo) request.getSession().getAttribute("user");
            if (user!=null){
                request.getSession().setAttribute("user",userInfo);
            }
            res="success";
        }
        return res;
    }

    /**
     * ɾ���û�
     * @param uId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam("userId") String uId) throws Exception {
        int userId = Integer.parseInt(uId);
        String res = "false";
        boolean flag= userInfoService.deleteUser(userId);
        if (flag){
            res="success";
        }
        return res;
    }

    /**
     * ������뿴
     * @param mId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/wantLook")
    @ResponseBody
    public String wantLook(@RequestParam("movieId") String mId,HttpServletRequest request) throws Exception {
        int movieId = Integer.parseInt(mId);
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        int userId = userInfo.getUserid();
        String res = "false";
        boolean flag= userInfoService.wantLook(userId,movieId);
        MovieInfoService movieInfoService = new MovieInfoServiceImpl();
        if (flag){
            boolean f=movieInfoService.updateWantSeeCount(movieId,"add");
            if (f){
                res="success";
            }
            else {
                return res;
            }
        }
        return res;
    }

    /**
     * ɾ���뿴ӰƬ
     * @param mId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteWantLook")
    @ResponseBody
    public String deleteWantLook(@RequestParam("movieId") String mId,HttpServletRequest request) throws Exception {
        int movieId = Integer.parseInt(mId);
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        int userId = userInfo.getUserid();
        String res = "false";
        boolean flag= userInfoService.deleteWantLook(userId,movieId);
        if (flag){
            res="success";
        }
        return res;
    }

    /**
     * ��ѯ�뿴�б�
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryWantList")
    public String queryWantList(HttpServletRequest request) throws Exception {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        int userId = user.getUserid();
        List<MovieInfo> movieInfos = userInfoService.queryUserWantLook(userId);
        request.setAttribute("movieInfos",movieInfos);
        return "user-wantLook-list";
    }

    /**
     * ��ѯ�û������б�
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserComments")
    public String findUserComments(HttpServletRequest request) throws Exception {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        int userId = user.getUserid();
        List<CommentsInfo> commentsInfoList = userInfoService.findUserComment(userId);
        request.setAttribute("commentsInfoList",commentsInfoList);
        return "user-comment-list";
    }
}
