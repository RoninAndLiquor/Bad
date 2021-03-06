package high.util;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtils {

	public static final String DEFAULT_ENCODING = "utf-8";
	
	public static void main(String[] args) throws Exception {  
        JavaMailSenderImpl sender = initJavaMailSender();  
        String[] ss = { "1037322988@qq.com" };  
  
        //sendTextWithHtml(sender, ss, "测试简单文本邮件发送！ ", " <a href='http://work.dev.gomeplus.com/'>test</a>测试我的简单邮件发送机制！！ ");
        sendTextWithImg(sender, ss, "测试邮件带附件", "测试啊", "zxing.png", "D:/zxing.png");
        //sendWithAll(sender, ss, null, "测试邮件带附件啊", "测试啊啊", "Image.png", "D:/zxing.png", "FU.txt", "F:/queryEmpAll.txt");
        //sendTextWithImg(sender, ss, "测试邮件中嵌套图片!！", 
        //"<html><head></head><body><h1>hello 欢迎你!!spring image html mail</h1><img src='cid:image'/></body> , "image", "d:/compare2.png");  
  
        //sendWithAttament(sender,"yy22@163.com","测试邮件中上传附件!！",
        //"<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>","c.png","d:/compare2.png");  
  
        //sendWithAll(sender, "yy22@163.com", "测试邮件中嵌套图片!！", 
        //"<html><head></head><body><h1>hello 欢迎你!!spring image html ma ", "image", "d:/compare2.png","工作日志.docx","d:/工作日志.docx");  
    } 
	
	public static String sendTextWithHtml(JavaMailSenderImpl sender, String[] tos, String subject, String text)  
            throws Exception {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text);  
        // 发送邮件  
        sender.send(mailMessage);  
        System.out.println("发送成功");
        return "发送成功";  
    }  
	
	public static String sendTextWithImg(JavaMailSenderImpl sender, String[] tos, String subject, String text,  
            String imgId, String imgPath) throws MessagingException {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        MimeMessageHelper messageHelper = initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text,  
                true, true, "GBK");  
        // 发送图片  
        FileSystemResource img = new FileSystemResource(new File(imgPath));  
        messageHelper.addInline(imgId, img);  
        // 发送邮件  
        sender.send(mailMessage);  
  
        System.out.println("邮件发送成功..");  
        return "发送成功"; 
    }  
  
    public static String sendWithAttament(JavaMailSenderImpl sender, String[] tos, String subject, String text,  
            String AttachName, String filePath) throws MessagingException {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        MimeMessageHelper messageHelper = initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text,  
                true, true, DEFAULT_ENCODING);  
  
        FileSystemResource file = new FileSystemResource(new File(filePath));  
        // 发送邮件  
        messageHelper.addAttachment(AttachName, file);  
        sender.send(mailMessage);  
  
        System.out.println("邮件发送成功..");  
        return "发送成功"; 
    }  
  
    public static String sendWithAll(JavaMailSenderImpl sender, String[] tos, String from, String subject, String text,  
            String imgId, String imgPath, String AttachName, String filePath) throws MessagingException {  
        MimeMessage mailMessage = sender.createMimeMessage();  
        MimeMessageHelper messageHelper = initMimeMessageHelper(mailMessage, tos, sender.getUsername(), subject, text,  
                true, true, DEFAULT_ENCODING);  
  
        // 插入图片  
        FileSystemResource img = new FileSystemResource(new File(imgPath));  
        messageHelper.addInline(imgId, img);  
        // 插入附件  
        FileSystemResource file = new FileSystemResource(new File(filePath));  
        messageHelper.addAttachment(AttachName, file);  
  
        // 发送邮件  
        sender.send(mailMessage);  
  
        System.out.println("邮件发送成功.."); 
        return "发送成功"; 
  
    }  
  
    private static MimeMessageHelper initMimeMessageHelper(MimeMessage mailMessage, String[] tos, String from,  
            String subject, String text) throws MessagingException {  
        return initMimeMessageHelper(mailMessage, tos, from, subject, text, true, false, DEFAULT_ENCODING);  
    }  
  
      
    private static MimeMessageHelper initMimeMessageHelper(MimeMessage mailMessage, String[] tos, String from,  
            String subject, String text, boolean isHTML, boolean multipart, String encoding) throws MessagingException {  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, multipart, encoding);  
        messageHelper.setTo(tos);  
        messageHelper.setFrom(from);  
        messageHelper.setSubject(subject);  
        // true 表示启动HTML格式的邮件  
        messageHelper.setText(text, isHTML);  
  
        return messageHelper;  
    }  
  
  
    /** 
     * 这个方法在实际应用中，springboot会通过在配置文件application.xml 
     * 中加配置自动实例化JavaMailSenderImpl，本方法只是为了测试使用 
     */  
    public static JavaMailSenderImpl initJavaMailSender() {  
  
        Properties properties = new Properties();  
        properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)  
        properties.setProperty("mail.smtp.starttls.enable", "false");  
        properties.setProperty("mail.smtp.socketFactory.class",  
                "javax.net.ssl.SSLSocketFactory");  
        properties.setProperty("mail.smtp.auth", "true");  
        properties.put(" mail.smtp.timeout ", " 25000 ");  
  
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();  
        javaMailSender.setJavaMailProperties(properties);  
        javaMailSender.setHost("smtp.163.com");  
        javaMailSender.setUsername("ronin_404@163.com"); // s根据自己的情况,设置username  
        javaMailSender.setPassword("forbid404"); // 根据自己的情况, 设置password  
        javaMailSender.setPort(465);  
        javaMailSender.setDefaultEncoding("UTF-8"); 
  
        return javaMailSender;  
    }  
}
