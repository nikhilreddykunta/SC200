
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


public class WriteFile {

	public static void main(String[] args) {
		String filePath = args[0];
		String text = "\n" +
				"  " + args[1] + "-" + args[2] + ":\n" +
				"    image: " + args[1] + "-" + args[2] + "\n" +
				"    build: ./" + args[1] +"/" + args[2] +"/\n" +
				"    network_mode: host\n" +
				"    container_name: " + args[1] + "-" + args[2] + "\n" +
				"    ports:\n" +
				"      - " + args[3] + ":" + args[3] + "\n" +
				"    expose:\n" +
				"      - " + args[3] + "\n" +
				"\n";
		File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		PrintWriter pr = null;
		try {
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			pr.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pr.close();
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
}