import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


public class SendMail {

	private String mailContent;
	
		
	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public void sendMail(){
		
		Properties prop = System.getProperties();
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.setProperty("mail.smtp.auth","true" );
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.transport.protocol", "smtp");

		Authenticator auth = new SMTPAuthenticator();
		
		Session session = Session.getDefaultInstance(prop,auth);
		session.setDebug(true);		
		
		Message msg = new MimeMessage(session);
		
		try {
			Transport t = session.getTransport();
			
			msg.setFrom(new InternetAddress("hareesh.lakshminarayanan@gmail.com"));
			
			msg.setRecipient(RecipientType.TO, new InternetAddress("hareesh_l@yahoo.com"));
			
			msg.setSubject("test");
									
			msg.setContent(mailContent,"text/html");
						
			t.connect();
			
			t.send(msg);
			
			t.close();
			
			System.out.println("mail sent");
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
		
	private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "hareesh.lakshminarayanan@gmail.com";
           String password = "****";
           return new PasswordAuthentication(username, password);
        }
    }

	public static void main(String[] args){
		
		new SendMail().sendMail();
		
	}
	
}
