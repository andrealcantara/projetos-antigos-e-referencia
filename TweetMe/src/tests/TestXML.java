package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.tweetme.entities.User;
import br.tweetme.factories.FactoryDAOJdbc;
import br.tweetme.persistenceDAO.FactoryDAO;
import br.tweetme.persistenceDAO.UserDAO;

public class TestXML {

	public static void main(String[] args) {
		File file = new File("teste.xml");
		
		DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFac.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc = null;
		try {
			doc = dBuilder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		 
		NodeList nList = doc.getElementsByTagName("staff");
		
	}

	public static void writeXML() {
		File file = new File("teste.xml");

		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PrintWriter out = new PrintWriter(fileOut);

		try {
			fileOut = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		FactoryDAO f = FactoryDAOJdbc.getInstance();
		UserDAO ud = f.createUserPersistence();

		List<User> l = null;

		try {
			l = ud.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<users>\n";

		for (User user : l) {
			xml += "  <user id=\"" + user.getId() + "\">\n";
			xml += "    <name>" + user.getName() + "</name>\n";
			xml += "    <login>" + user.getLogin() + "</login>\n";
			xml += "    <password>" + user.getPassword() + "</password>\n";
			xml += "    <followers>" + user.getFollowers().size()
					+ "</followers>\n";
			xml += "    <following>" + user.getFollowing().size()
					+ "</following>\n";
			xml += "  </user>\n";
		}

		xml += "</users>";

		out.println(xml);

		out.close();
	}
}
