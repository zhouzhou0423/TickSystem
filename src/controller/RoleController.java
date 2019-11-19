package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleInfoService;
import service.WebAdmService;
import service.impl.RoleInfoServiceImpl;
import service.impl.WebAdmServiceImpl;
import vo.ActorInfo;
import vo.MovieInfo;
import vo.RoleInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/14 17:49
 */
@Controller
@RequestMapping("role")
public class RoleController {
    RoleInfoService service = new RoleInfoServiceImpl();
    @RequestMapping("/toRoleAddPage")
    public String toRoleAddPage(HttpServletRequest request) throws Exception {
        WebAdmService webAdmService = new WebAdmServiceImpl();
        List<ActorInfo> actorInfos = webAdmService.findAllActor();
        List<MovieInfo> movieInfos = webAdmService.findAllMovies();
        request.setAttribute("actorInfos",actorInfos);
        request.setAttribute("movieInfos",movieInfos);
        return "web-role-add";
    }
    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(HttpServletRequest request, @RequestParam("roleName") String roleName,@RequestParam("actorId") int actorId,@RequestParam("movieId") int movieId,@RequestParam("isImportant") int isImportant) throws Exception {
        boolean flag = service.addRole(roleName,actorId,movieId,isImportant);
        String res="false";
        if (flag) {
            res="success";
        }
        return res;
    }
    @RequestMapping("/toRoleEdit")
    public String toRoleEdit(HttpServletRequest request, @RequestParam("roleId") int roleId)throws Exception {
        RoleInfo roleInfo = service.queryRoleById(roleId);
        WebAdmService webAdmService = new WebAdmServiceImpl();
        List<ActorInfo> actorInfos = webAdmService.findAllActor();
        List<MovieInfo> movieInfos = webAdmService.findAllMovies();
        request.setAttribute("actorInfos",actorInfos);
        request.setAttribute("movieInfos",movieInfos);
        request.setAttribute("roleInfo",roleInfo);
        return "web-role-edit";
    }
    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(@RequestParam("roleId") String rid, @RequestParam("roleName") String roleName,@RequestParam("actorId") int actorId,@RequestParam("movieId") int movieId,@RequestParam("isImportant") int isImportant) throws Exception {
        int roleId = Integer.parseInt(rid);
        boolean flag = service.updateRole(roleId,roleName,actorId,movieId,isImportant);
        String res="false";
        if (flag) {
            res="success";
        }
        return res;
    }
    @RequestMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(@RequestParam("roleId") String rId) throws Exception {
        int roleId=Integer.parseInt(rId);
        boolean flag = service.deleteRole(roleId);
        String res="false";
        if(flag){
            res="success";
        }
        return res;
    }
}
