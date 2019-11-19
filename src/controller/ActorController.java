package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.ActorInfoService;
import service.impl.ActorInfoServiceImpl;
import vo.ActorInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhou_zhou
 * @date 2019/4/14 15:36
 */
@Controller
@RequestMapping("actor")
public class ActorController {
    ActorInfoService service = new ActorInfoServiceImpl();

    /**
     * ��ѯ�Ƿ���ͬ����Ա
     * @param actorName
     * @return
     * @throws Exception
     */
    @RequestMapping("/findActorByName")
    @ResponseBody
    public String findActorByName(@RequestParam("actorName") String actorName) throws Exception {
        String res="false";
        if(service.findActorByName(actorName)==null){
            res="success";
        }
        return res;
    }

    /**
     * ������Ա
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/addActor")
    @ResponseBody
    public String addActor(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        String actorName=multipartRequest.getParameter("actorName");

        /*ָ���ļ��ϴ�·��*/
        String path=request.getServletContext().getRealPath("/upload");
        String fileName=file.getOriginalFilename();
        // ��ȡ�ϴ��ļ��ĺ�׺��
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        // ���ļ�����һ���µ�����,�ž��ļ�������������
        String newFileName= UUID.randomUUID().toString()+suffix;

        File f=new File(path+"\\" + newFileName);
        //���ϴ��ļ�д���ļ�
        file.transferTo(f);

        ActorInfo actorInfo = new ActorInfo();
        actorInfo.setActorname(actorName);
        actorInfo.setPhoto("upload/"+newFileName);
        boolean flag=service.addActor(actorInfo);
        String res="false";
        if(flag){
            res="success";
        }
        return res;
    }
    @RequestMapping("/deleteActor")
    @ResponseBody
    public String deleteActor(@RequestParam("actorId") String aId) throws Exception {
        int actorId=Integer.parseInt(aId);
        boolean flag = service.deleteActor(actorId);
        String res="false";
        if(flag){
            res="success";
        }
        return res;
    }

    /**
     * ��ת����Ա��Ϣ�޸�ҳ
     * @param aId
     * @param actorName
     * @param request
     * @return
     */
    @RequestMapping("/toEditActor")
    public String toEditActor(@RequestParam("actorId") String aId,@RequestParam("actorName") String actorName,@RequestParam("photo") String photo,HttpServletRequest request){
        int actorId=Integer.parseInt(aId);
        ActorInfo actorInfo = new ActorInfo();
        actorInfo.setActorid(actorId);
        actorInfo.setActorname(actorName);
        actorInfo.setPhoto(photo);
        request.setAttribute("actorInfo",actorInfo);
        return "web-actor-edit";
    }

    /**
     * �޸���Ա��Ϣ
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/editActor")
    @ResponseBody
    public String editActor(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file=multipartRequest.getFile("file");
        int actorId = Integer.parseInt(multipartRequest.getParameter("actorId"));
        String img = multipartRequest.getParameter("uploadfile-2");
        String photo="";
        if (file.getSize()==0){
            photo=img;
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
            photo = "upload/" + newFileName;
        }
        boolean flag=service.editActor(photo,actorId);
        String res="false";
        if(flag){
            res="success";
        }
        return res;
    }
}
