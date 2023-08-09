package action;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindepwAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String idcode = "";
		for (int i = 0; i < 6; i++) {
		    char randomChar = (char)((int) (Math.random() * 26) + 97);
		    idcode += randomChar;
		}
		
		sendmail(email, idcode);
		
	}

	private void sendmail(String email, String idcode) {
		String host = "smtp.naver.com";
	  final String user = "apple9568@naver.com";
	  final String password = "Y6L77V2ZS7U9";

	  System.out.println("[시스템]" + email + "님에게 인증 메일 보내는 중...");
	  // Get the session object
	  Properties props = new Properties();
	  props.put("mail.smtp.host", host);
	  props.put("mail.smtp.auth", "true");

	  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	   protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(user, password);
	   }
	  });

	  // Compose the message
	  try {
	   MimeMessage message = new MimeMessage(session);
	   message.setFrom(new InternetAddress(user));
	   message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

	   // Mail Title
	   message.setSubject("[스투비플래너]비밀번호 변경 이메일");
	   
	   // Mail content
	   
	   message.setContent("인증 URL: " + "<a href='http://localhost:9090/Myproject/public/ChangePw.jsp?email="+email+"&idcode="+idcode+"'>비빌번호 변경</a>","text/html; charset=euc-kr");
	   
	   // send system message
	   Transport.send(message);
	   System.out.println("[시스템]메일이 성공적으로 보내졌습니다!" );
	   

	  } catch (MessagingException e) {
	  	
	   e.printStackTrace();
	  }
	}
}
