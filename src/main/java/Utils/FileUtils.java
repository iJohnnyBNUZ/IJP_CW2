package Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileUtils {
    static String readFile(String filePath) throws IOException {
    	//if the system in windows, remove the first '/' symbol.
//    	if(System.getProperty("file.separator").equals("\\")){
//        	filePath=filePath.substring(1,filePath.length());
//        	}
        System.out.println(Paths.get(filePath).toAbsolutePath().toString());
        
        byte[] encoded = Files.readAllBytes(Paths.get(filePath).toAbsolutePath());
        return new String(encoded);
    }

    static void writeFile(String filePath, String fileData) throws IOException {
	    FileWriter fileWriter = new FileWriter(filePath);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(fileData);
		printWriter.close();
    }
}
