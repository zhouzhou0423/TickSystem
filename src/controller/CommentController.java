package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CommentsInfoService;
import service.impl.CommentsInfoServiceImpl;
import vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhou_zhou
 * @date 2019/3/30 21:39
 */
@Controller
@RequestMapping("comment")
public class CommentController {
    CommentsInfoService service =new CommentsInfoServiceImpl();
    /**
     * Ìí¼ÓÆÀÂÛ
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public String addComment(@RequestParam("movieid") String mid, @RequestParam("userid") String uid,@RequestParam("username") String username,@RequestParam("comment") String comment,@RequestParam("grade") String g,HttpServletRequest request, HttpServletResponse response) throws Exception {
        int movieid=Integer.parseInt(mid);
        int userid=Integer.parseInt(uid);
        int grade=Integer.parseInt(g);
        boolean flag=service.addComment(movieid, userid, username, comment, grade);
        String result="error";
        if(flag){
            result="success";
        }
        return result;
    }

    @RequestMapping("/updateZan")
    @ResponseBody
    public String addZan(@RequestParam("commentId") String cId, @RequestParam("isLike") int isLike,@RequestParam("zanCount") String zCount,HttpServletRequest request) throws Exception {
        int commentId=Integer.parseInt(cId);
        int zanCount=Integer.parseInt(zCount);
        UserInfo user= (UserInfo) request.getSession().getAttribute("user");
        if (user==null){
            return "unLogin";
        }
        int userId=user.getUserid();
        service.changeZanState(userId,commentId,isLike);
        service.updateZanCount(commentId,zanCount);
        return "success";
    }
    @RequestMapping("/deleteComment")
    @ResponseBody
    public String deleteComment(@RequestParam("commentId") String cId) throws Exception {
        int commentId=Integer.parseInt(cId);
        boolean flag = service.deleteComment(commentId);
        String res="false";
        if(flag){
            res="success";
        }
        return res;
    }
}
