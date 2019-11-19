package controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.MovieInfoService;
import service.OrderService;
import service.SitService;
import service.UserInfoService;
import service.impl.MovieInfoServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.SitServiceImpl;
import service.impl.UserInfoServiceImpl;
import vo.OrderInfo;
import vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("AliPay")
public class AliPayController {
    protected final Logger logger = Logger.getLogger(AliPayController.class);
    OrderService service = new OrderServiceImpl();
    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response, @RequestParam("orderId") String orderId)  throws Exception {
        //��ѯ����
        OrderInfo orderInfo = service.queryOrder(orderId);
        //��ó�ʼ����AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //�����������
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //�̻������ţ��̻���վ����ϵͳ��Ψһ�����ţ�����
        String out_trade_no =orderId;
        //���۲�Ʒ�룬��֧����ǩԼ�Ĳ�Ʒ�����ơ� ע��Ŀǰ��֧��FAST_INSTANT_TRADE_PAY
        String product_code="FAST_INSTANT_TRADE_PAY";
        //���������
        String total_amount = String.valueOf(orderInfo.getTotalprice());
        //�������ƣ�����
        String subject = orderInfo.getMoviename();
        //��Ʒ�������ɿ�
        String body = orderInfo.getMoviename()+"��λ��"+orderInfo.getSitposition();

        alipayRequest.setBizContent(""
                + "{"
                + "\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"product_code\":\""+ product_code +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\""
                + "}"
        );
        //��������ɲ��ġ�������վ֧����API�ĵ�-alipay.trade.page.pay-����������½�

        //����SDK���ɱ�
        String form = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(form);
    }

    @RequestMapping(value = "/returnUrl")
    public String returnUrl(HttpServletRequest request) throws Exception {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //����������δ����ڳ�������ʱʹ��
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //����SDK��֤ǩ��

        //�������������д���ĳ������´�������ο�������
        if(signVerified) {
            //�̻�������
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //֧�������׺�
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //������
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            String payTime=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
            String ticketCode=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            service.updateOrder(out_trade_no,ticketCode,payTime);
            OrderInfo orderInfo = service.queryOrder(out_trade_no);
            SitService sitService = new SitServiceImpl();
            int arrangeId=orderInfo.getArrangeid();
            String sitPosition = orderInfo.getSitposition();
            String[] sits = sitPosition.split(",");
            for (int i = 0; i < sits.length; i++) {
                sitService.updateState(arrangeId,sits[i]);
            }
            //����Ʊ��
            int num = orderInfo.getNum();
            MovieInfoService movieInfoService = new MovieInfoServiceImpl();
            movieInfoService.updateCount(orderInfo.getMovieid(),"add",num);
            request.getSession().setAttribute("order",orderInfo);
            UserInfoService userInfoService = new UserInfoServiceImpl();
            UserInfo userInfo = userInfoService.findUser(orderInfo.getUserid());
            request.getSession().setAttribute("user",userInfo);
        }else {
            //out.println("��ǩʧ��");
        }
        return "paySuccess";
    }
    /**
     * ֧�����������첽֪ͨurl
     * @throws Exception
     */
    @RequestMapping(value="/notifyUrl")
    public String notifyUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
        logger.info("����֧�����������첽֪ͨurl...");
        //��ȡ֧����POST��������Ϣ
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //����������δ����ڳ�������ʱʹ��
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //����SDK��֤ǩ��
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        //�������������д���ĳ������´�������ο�������

        /* ʵ����֤���̽����̻�����������У�飺
        1����Ҫ��֤��֪ͨ�����е�out_trade_no�Ƿ�Ϊ�̻�ϵͳ�д����Ķ����ţ�
        2���ж�total_amount�Ƿ�ȷʵΪ�ö�����ʵ�ʽ����̻���������ʱ�Ľ���
        3��У��֪ͨ�е�seller_id������seller_email) �Ƿ�Ϊout_trade_no��ʵ��ݵĶ�Ӧ�Ĳ��������е�ʱ��һ���̻������ж��seller_id/seller_email��
        4����֤app_id�Ƿ�Ϊ���̻�����
        */
        if(signVerified) {//��֤�ɹ�
            //�̻�������
            String out_trade_no =request.getParameter("out_trade_no");
            //֧�������׺�
            String trade_no =request.getParameter("trade_no");
            //����״̬
            String trade_status =request.getParameter("trade_status");
            String payTime=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
            String ticketCode=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            service.updateOrder(out_trade_no,ticketCode,payTime);
            OrderInfo orderInfo = service.queryOrder(out_trade_no);
            SitService sitService = new SitServiceImpl();
            int arrangeId=orderInfo.getArrangeid();
            String sitPosition = orderInfo.getSitposition();
            String[] sits = sitPosition.split(",");
            for (int i = 0; i < sits.length; i++) {
                sitService.updateState(arrangeId,sits[i]);
            }
            //����Ʊ��
            int num = orderInfo.getNum();
            MovieInfoService movieInfoService = new MovieInfoServiceImpl();
            movieInfoService.updateCount(orderInfo.getMovieid(),"add",num);
            request.getSession().setAttribute("order",orderInfo);
        }else {//��֤ʧ��
            //�����ã���¼��־��Ϣ
            //Logger.error(AlipaySignature.getSignCheckContentV1(params));
            System.out.println("false");
        }
        return "admIndex";
    }
}