package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016100100637793";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCu2EkBYBunI1RQUcvQT/FaOEwbzyNgpXbTiH8tF3g2djU1sXkTVNXIbqVwpNY9n7AOY3sJWxsDdr9AhjkPDN/pwceU5KCAvL5vAZ7TJEA2FYFkgk0rydmsZI8yIPbjRVtuA1kBDUcJylMlByO+4UDFxR8WRg9OCz6m+uSs3CPxrG4nQ5LHLf94mSnZM6yCITbZt4BHVT4OEa6fu0VQHQGJKW7uafQEitGmpgpQdL+yKQYXfxynLxG4AlEo7dt2oRKkWzGSxcfCDVpZpyCGIRLQtv/y8/lxkTthshUnj0EuTtujpywuAGmTiSIpylGPSpDIRjbz5J+afCq+BoS6dK21AgMBAAECggEBAKSRdN2zjTOa4EAdTT4MELp/ZLuEEuNjul1OC43wmjiAuIKKQdC9N99IrbVBoVktVbOlVOgwdVOkUE02SLxuvM6zktwE+UFS5ddr6gcIBtwhD8H/lw1RFu3ZVKK1PkwaOv1obzy7N5LyeaHPf4ipUaMUb7cySDOIB/d5DQXayoYr2x/k3gdTpameV6jvqEmlUVVEmc/dY8xKOljcV17fwzO/uVM2RmBvwTfPpIfLLrtq4/Ub05e6h+kJ1KX1L1ae8oMY1yWYYaS3xjXA7NRrJQiOh6zIIT/vexXOorAS7f/Nn6CLQcWV/3HSjytLy+WkhN85w6RlTRJfywWq0GEN7AECgYEA6DKpDLeePL+MjOGVXuentmf8rtOo9ljWrJ5tQ6qoa0Bhvs4MaBryxj983DjUKk6Ml4z68fZ5/baXSfid8x3ycE9K+X63AndxBHkWbhxuwoJiNtro4CbE/RejjRlPCbF0cfDBwGPj8+zq/m8pI3Xwn0020JzwHtjj20/OuMJviwECgYEAwMSQ524XE0RpAp1sLEXGyQoyceoJol5bIZl9phkUz3EWFmol2omjdXPl7QzCYlLVbY64o0+l5PUbEr5XoTbWNpY0z0PsRy5xLb5OMWnRJr5pPIDUmn09TgzcO/CHf7WLm9GvtovU0ONFjRryGBfhZqbrjCWF9V8pvK9DcAk1ZrUCgYAtk8Qa+u/UfMTZ4m2vc0k4RLEUn/dKY6W/vFk2LwlgBAMrsMY+qQt+XnfjwkPjCggOt12KoRbQ9kY7/tECJ56u7OGKl5TUpx1IA3yjoHEdPPqKSe3sEbAY/crcp1scS/jPZLKjUPgtqRN3Xdp1W3Ef08guqp93bptLMR8ipQfAAQKBgQC9tjYtrYkEK0V1j1xQTfOPE3wF5CDLLTVe4eJovzJd3ZuxP5I1e1PlWcSlAPOlNIRbqCRKhS5tIFQepDk1rfQwEK+0c9JrU2eLb0khFFUEXkzPgLK7wBm/YKS2UMfphc/57+mQLBmr4qAmeKhd0dLA5fHXIW6ux5MZ4RrrfgSSKQKBgQDXJ1aej2joNt/mhv+qXMCGfSLZ+O6tgb5Pn+6l1SSrGUfmiVEd7lRbR92oTQ+dZSZFOD+aOC2GoXdx0Ie2JBn5QYmaUK4JdaKFrOWLrdx4C6bDF7dkRViuGH1FMaCckvyifaR9fao4aoG/XeH5t3wsyxmUHQzy5DDuz1Amx0oLQw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx7eQP88/DX89vVd9z+MJB7ZIyUSFy4MZPiNchGboarRhoi5DSkMgIwJI33YUEpCmbdQAPw8g1AgpS5Wis+FEyPd5GFDBEp1Dc7DSZP7QceKtoty/35FAEgAJKz/2eC4y9Wgs7fX6Qu0EWf6Vrw3H6N5CqdCFv2Rs8gCMGJbDh7hP3LEtsfpTTsizGTow7alietZAZqUqigN54iuyjxu1P4BhfjwQMIqcLYuz3EWjClLAy9pH02hcG2tZpxAKTMig2Z221fz6TaPjio9gQ0tbwi8l5Q69M61ZLlvI9thlzOit4YMtT3NFdsYeyywXyBLcrW+rRaLfaywHavc++/oucwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1:8080/TicketSystem/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://127.0.0.1:8080/TicketSystem/AliPay/returnUrl";
//http://127.0.0.1:8080/TicketSystem/head.jsp
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "F:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

