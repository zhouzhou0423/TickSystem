package util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhou_zhou
 * @date 2019/4/16 14:29
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        String data=request.getQueryString();

        if ("/cinemaInfo/findByArea".equals(url)) {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
            if(userInfo==null){
                request.getSession().setAttribute("back",request.getRequestURL()+"?"+data);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return false;
            }
            return true;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
