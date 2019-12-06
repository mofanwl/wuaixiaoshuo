package com.wn.utils;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2019/12/2.
 */
public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101600696251";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJ+HEw/Ih4CK8jiWp0VFA9UOq+Z+MGzcOV5hv4nS/AKGiwZ8eeJJUvd0/IUOxYkCHlilTPFUMAZRa9/gW7HTTFl3G2yDvIPPHPxnd95k6FmEgqllE+4Ynurhksk3USYyQfwkib/XkYhv1ba1cIfIxCa5LPcSc3lo79w7TG3mRL+LWzYKVIXoKHpwHdUpo5Qym1rX05kWhI9zvwzU/UfhnJkYQyYAgBKsU1gexU7A+VhSCEmzl+y74bl0/3Iz5FcjTalfnng7aUfJovFXEhHjvKZ42gREfI1AXHfzlzlHquo+kUaOK4NcMkvZI/xoVjCi3YoVPk8l0NMsI6RzRCiKKBAgMBAAECggEASCEgW/HKDqbLmRKAGuzxPf0NnBmHsMjl1XoFaYYTrqvxfzfiPiT18Wi4xwq3ZN2FOxGEoPaGnrA6oX0p1dbA/gPcqSgpSNfDu1IQqjs7FdC0+b64Ht2eVK+j7Y/xhnlaK1hQyWxMYKxBzAdj6sXiNd8zU0veqssFWll0KvudB7Hjv+o+u+oRTbErlxkVookqdovozqYeuTxlr8q5r36/CW0P6HYcMcU6CNb6c8EjuNsfBamVVi967zSWRxoIa9QUUQTrZXR9CTGslBGHxHBqY4KPKLrBMN0Pmf77wjs7G/1k1ldtsV1rxn9hOPkcQ5ue5AGDqzr0N8ePz+OFALamgQKBgQD7Tyo30RpKXRnzYkbCe0s6WvPW0GGF7DonF0O369HFNzdR1gdkJS1PiB+EseMmjC4He69s4+8GhqqWEGlnZwuJ+v2x2L/2UQq1BEywHX39ByO9DPU9XZ1Wm2q1n/E279xQ0htzJi1lAwawEgwXXxFLrjNPWDRrRAff8781iNeq+QKBgQCMi7Vxph+z8kjYFWycysjAFvfCwT4/8b+dbviZT9shRM8TfZxYThbdiQioj8aE/RhV1bRhchMmBnhkwSuQcLX1HsweMpm1+DplZpKwYdCFoE34sIY2nC6gS5teUu97dge2POu4CpMvlTDAUEpboSK41Zdt2iznxKBUSO1dwBrNyQKBgBmXnEMG5liAVq40aeRQTf7pWD+eBPBweBOXCOxn9XrR/q40z5fhOVYKFLucqfc4+08Dx3+dTrApcqHJlhJTqhcpGkDMQ/A/nImGoJCm0De+TGNqOGdBa05wkVNhsFzZ3GPWobj+QE0zG7E+hUC0C7zbRppj7yKlUO/7kJNRFjVxAoGAX+PqNdIsCzAhNxzW79sDQNvqyvvhpusf2yAdiSyMy5QQ3LF0/GK7Awc92xPNVGsCMX4PBfU4z1bWyZdqS7A61TTf+zJ24UqtPQXQy3rRNHp8+FIoIBZLUZ6EMMTRFCEhfdru0LCWX36gtnWRZhb4g7dnC9IxlSf4Y8kl+jLZltECgYBCuN/80zQ4w7YkJ3crZyPhFpCWFq2kQ9rYPXINQsi/vpPmSgKq25QIRFrigXKUclIWgeBNJmiGRy7jlnObthk/h6aet39IFodXxl+tCZFagaIQ2CTxipGKmEzVtgeYYuREfi4NLP60hg0ad78JrNjRSZzB608tKqNZYdOMcU3H0g==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String notify_url = "http://localhost:8080/pay/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //public static String return_url = "http://localhost:8080/pay/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";


    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

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
