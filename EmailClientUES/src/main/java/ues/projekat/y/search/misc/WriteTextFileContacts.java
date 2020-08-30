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

public class WriteTextFileContacts {
	
	public static void write() {
		List<String> data = new ArrayList<String>();
        try {
               Connection con = null;
               Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ues", "root", "root");
               Statement st = con.createStatement();
               ResultSet rs = st.executeQuery("Select * from contacts");

                while (rs.next()) {
                        String id = rs.getString("contact_id");
                        String displayname = rs.getString("displayname");
                        String email = rs.getString("email");
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        String note = rs.getString("note");
                      //  String photo_id = rs.getString("photo_id");
                      //  String user_id = rs.getString("user_id");
                        data.add(id + "\n" + displayname + "\n"  + 
                        email + "\n" + firstname + "\n" + lastname + "\n" + note + "\n");

                }
                writeToFile(data, "contacts.txt");
                rs.close();
                st.close();
        } catch (Exception e) {
                System.out.println(e);
        }
	}
	
	public static void writeToFile(List<String> list, String path) {
		BufferedWriter out = null;
		try {
			File file = new File("C:\\Users\\Ljubica\\Downloads\\dataDir\\contacts.txt");
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