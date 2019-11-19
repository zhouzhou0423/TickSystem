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
        //查询订单
        OrderInfo orderInfo = service.queryOrder(orderId);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no =orderId;
        //销售产品码，与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
        String product_code="FAST_INSTANT_TRADE_PAY";
        //付款金额，必填
        String total_amount = String.valueOf(orderInfo.getTotalprice());
        //订单名称，必填
        String subject = orderInfo.getMoviename();
        //商品描述，可空
        String body = orderInfo.getMoviename()+"座位："+orderInfo.getSitposition();

        alipayRequest.setBizContent(""
                + "{"
                + "\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"product_code\":\""+ product_code +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\""
                + "}"
        );
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //调用SDK生成表单
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
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
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
            //更新票房
            int num = orderInfo.getNum();
            MovieInfoService movieInfoService = new MovieInfoServiceImpl();
            movieInfoService.updateCount(orderInfo.getMovieid(),"add",num);
            request.getSession().setAttribute("order",orderInfo);
            UserInfoService userInfoService = new UserInfoServiceImpl();
            UserInfo userInfo = userInfoService.findUser(orderInfo.getUserid());
            request.getSession().setAttribute("user",userInfo);
        }else {
            //out.println("验签失败");
        }
        return "paySuccess";
    }
    /**
     * 支付宝服务器异步通知url
     * @throws Exception
     */
    @RequestMapping(value="/notifyUrl")
    public String notifyUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
        logger.info("进入支付宝服务器异步通知url...");
        //获取支付宝POST过来的信息
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
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        //——请在这里编写您的程序（以下代码仅作参考）——

        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no =request.getParameter("out_trade_no");
            //支付宝交易号
            String trade_no =request.getParameter("trade_no");
            //交易状态
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
            //更新票房
            int num = orderInfo.getNum();
            MovieInfoService movieInfoService = new MovieInfoServiceImpl();
            movieInfoService.updateCount(orderInfo.getMovieid(),"add",num);
            request.getSession().setAttribute("order",orderInfo);
        }else {//验证失败
            //调试用，记录日志信息
            //Logger.error(AlipaySignature.getSignCheckContentV1(params));
            System.out.println("false");
        }
        return "admIndex";
    }
}