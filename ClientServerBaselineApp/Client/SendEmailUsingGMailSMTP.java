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
	static private String username = "<<your gmail username>>";
	static private String password = "<<your gmail password>>";

	public static void main(String[] args) throws InterruptedException {

		Scanner kb = new Scanner(System.in);
		System.out.print("email username: ");
		username = kb.next();
		System.out.print("email password: ");
		password = kb.next();
		
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
		String from = "bjsinventech@gmail.com";

		// -- Set up the recipient's email address
		String to = "bsottile@callutheran.edu";
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
			message.setSubject("CSC335 Project" + Character.toString((char) randomchar));

			// Now set the actual message
			message.setText("REQUEST FOR ASSISTANCE-STRICTLY CONFIDENTIAL\n" +
					"\n" +
					"I am Dr. Bakare Tunde, the cousin of Nigerian Astronaut, " + "\n" +
							"Air Force Major Abacha Tunde. " + "\n" + "He was the first African in space when" + "\n" + "he made a secret flight to the Salyut 6 space station in 1979. He was on a later Soviet spaceflight, Soyuz T-16Z to the secret Soviet military space station Salyut 8T in 1989. He was stranded there in 1990 when the Soviet Union was dissolved. His other Soviet crew members returned to earth on the Soyuz T-16Z, but his place was taken up by return cargo. There have been occasional Progrez supply flights to keep him going since that time. He is in good humor, but wants to come home." +
					"\n" + "In the 14-years since he has been on the station, he has accumulated flight pay and interest amounting to almost $ 15,000,000 American Dollars. This is held in a trust at the Lagos National Savings and Trust Association. If we can obtain access to this money, we can place a down payment with the Russian Space Authorities for a Soyuz return flight to bring him back to Earth. I am told this will cost $ 3,000,000 American Dollars. In order to access the his trust fund we need your assistance." +

			"Consequently, my colleagues and I are willing to transfer the total amount to your account or subsequent disbursement, since we as civil servants are prohibited by the Code of Conduct Bureau (Civil Service Laws) from opening and/ or operating foreign accounts in our names." +
					"Needless to say, the trust reposed on you at this juncture is enormous. In return, we have agreed to offer you 20 percent of the transferred sum, while 10 percent shall be set aside for incidental expenses (internal and external) between the parties in the course of the transaction. You will be mandated to remit the balance 70 percent to other accounts in due course.");

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
	}}}
