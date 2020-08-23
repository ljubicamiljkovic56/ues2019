package ues.projekat.y.search.misc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WriteTextFileMessage {

	public static void write() {
		List<String> data = new ArrayList<String>();
        try {
               Connection con = null;
               Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ues", "root", "root");
               Statement st = con.createStatement();
               ResultSet rs = st.executeQuery("Select * from messages");

                while (rs.next()) {
                        String id = rs.getString("message_id");
                        String from = rs.getString("message_from");
                        String to = rs.getString("message_to");
                        String cc = rs.getString("cc");
                        String bcc = rs.getString("bcc");
                        String subject = rs.getString("message_subject");
                        String content = rs.getString("content");
                        data.add("Message id: "  + id + "\n" + "From: " + from + "\n" + "To: " + 
                        to + "\n" + "CC: " + cc + "\n" + "BCC: " + bcc + "\n" + "Subject: " + subject + "\n" +
                        		"Content: " + content + "\n");

                }
                writeToFile(data, "messages.txt");
                rs.close();
                st.close();
        } catch (Exception e) {
                System.out.println(e);
        }
	}
	
	public static void writeToFile(List<String> list, String path) {
		BufferedWriter out = null;
		try {
			File file = new File("C:\\Users\\Ljubica\\Downloads\\dataDirMessages\\messages.txt");
			out = new BufferedWriter(new FileWriter(file, true));
			for (String s : list) {
				out.write(s);
				out.newLine();
			}
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
