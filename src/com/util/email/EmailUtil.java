package com.util.email;

import com.util.encryption.Base64Util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 邮箱工具类
 * @author 唐小甫
 * @createTime 2020-11-22 17:40:44
 */
public class EmailUtil {

    /**
     * 邮件发送
     * @param user
     * @return void
     * @author 唐小甫
     * @datetime 2020/11/21 23:29
     */
    public static void sendEmail(Map<String, String> user){
        //发送方的账号
        String myAccount = "cqjava1701@163.com";
        //发送方的授权码/密码
        String myPass = "cq1701";

        //封装一系列消息
        Properties pros = new Properties();
        //邮件相关的协议：发送方协议: smtp(简单邮件传输协议); 接收协议: pop3
        //设置协议类型
        pros.setProperty("mail.transport.protocal", "smtp");
        //设置发件人的邮箱服务器地址
        pros.setProperty("mail.smtp.host", "smtp.163.com");
        //设置请求验证
        pros.setProperty("mail.smtp.auth", "ture");

        //邮箱里的会话对象
        Session session = Session.getDefaultInstance(pros);

        //测试阶段
        //session.setDebug(true);

        try {
            //创建一个邮件传输的通道
            Transport transport = session.getTransport();

            //获取要发送的信息
            MimeMessage message = new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress(myAccount, "小米商城", "UTF-8"));

            //设置收件人及发送方式
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(user.get("email"), user.get("username"), "UTF-8")});

            //邮件主题
            message.setSubject("小米商城激活邮件", "UTF-8");

            //设置邮件内容
            String ip = Inet4Address.getLocalHost().getHostAddress();
            String url = "http://" + ip + ":8080/mishop/user?method=active&c=" + Base64Util.encode(user.get("username"))
                    + "&e=" + user.get("code");

            message.setContent(user.get("username") + ", 你好<br>欢迎注册小米商城！请点击链接进行激活：<a href='"
                    + url + "'>" + url + "</a>", "text/html;charset=utf-8");

            //设置邮件的发送时间
            message.setSentDate(new Date());
            //保存设置
            message.saveChanges();


            //连接邮件发送器
            transport.connect(myAccount, myPass);

            //getAllRecipients: 用来获取收件人地址等信息(收件人, 抄送, 密送等)
            transport.sendMessage(message, message.getAllRecipients());

            //关闭与服务器之间的连接
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}