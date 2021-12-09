package Client;// -- Download JavaMail API from here: http://www.oracle.com/technetwork/java/javamail/index.html
// -- Download JavaBeans Activation Framework from here: http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-java-plat-419418.html#jaf-1.1.1-fcs-oth-JPR
import java.util.Properties;
import java.util.Scanner;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailUsingGMailSMTP {

	// -- set the gmail host URL
	final static private String host = "smtp.gmail.com";

	// -- You must have a valid gmail username/password pair to use
	// gmail as a SMTP service
	static private String username = "csc335booked@gmail.com";
	static private String password = "SEbooked!";

	public static void main(String[] args) throws InterruptedException {

	}

	public static void sendPasswordEmail(String Semail, String Spassword) throws InterruptedException {
		// -- set up host properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// -- Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		// -- Set up the sender's email account information
		String from = username+"@gmail.com";

		// -- Set up the recipient's email address
		String to = Semail;
		int c = 0;
		while(c < 5){
			Thread.sleep(10000);
			c++;
			try {
				// -- Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// -- Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// -- Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				int randomchar = ((int)(Math.random() * 25) + 52);

				// -- Set Subject: header field
				message.setSubject("BooKeD Blocks - password recovery" + Character.toString((char) randomchar));

				// Now set the actual message
				message.setText("PASSWORD: "+Spassword);

				// -- Send message
				// -- use either these three lines or...
				// Transport t = session.getTransport("smtp");
				// t.connect();
				// t.sendMessage(message, message.getAllRecipients());

				// -- .. this one (which ultimately calls sendMessage(...)
				Transport.send(message);

				System.out.println("Sent message successfully....");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
