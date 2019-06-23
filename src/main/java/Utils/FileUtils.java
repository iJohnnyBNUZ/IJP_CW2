package Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileUtils {
    static String readFile(String filePath) throws IOException {
    	//if the system in windows, remove the first '/' symbol.
    	if(System.getProperty("file.separator").equals("\\")){
        	filePath=filePath.substring(1,filePath.length());
        	}
        System.out.println(filePath);
        
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded);
    }
}
