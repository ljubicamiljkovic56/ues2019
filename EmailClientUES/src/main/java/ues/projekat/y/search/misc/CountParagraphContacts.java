package ues.projekat.y.search.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CountParagraphContacts {

	public static void main(String[] args) throws IOException {
		System.out.println("No. of paragraphs in file: " + CountParagraphContacts.getParagraphCount());
	}
	
	private static final String FILE_PATH = "C:\\Users\\Ljubica\\Downloads\\dataDir\\contacts.txt";
		   
	@SuppressWarnings("resource")
	public static int getParagraphCount() throws IOException {
		File file = new File(FILE_PATH);
		FileInputStream fileStream = new FileInputStream(file);
		byte[] byteArray = new byte[(int)file.length()];
		fileStream.read(byteArray);
		String data = new String(byteArray);

		String[] paragraphs = data.toString().split(",");

		return paragraphs.length;
	}
}
