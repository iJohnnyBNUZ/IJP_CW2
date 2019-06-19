package Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileUtils {
    static String readFile(String filePath) throws IOException {
        System.out.println(filePath);
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded);
    }
}
