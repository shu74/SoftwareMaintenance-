package javaapplication25;
import java.io.*;

public class writeFile {
    
    String x = null;
    String y = null;
    FileWriter fileWriter;
    String fileName = null;
    
    
    public writeFile(String fileName, String x, String y){
        this.fileName = fileName;
        this.x = x;
        this.y = y;
    }
    
    public void write() {
        
        try {

            fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
                           
            bufferedWriter.write(x);
            bufferedWriter.write(" ");
            bufferedWriter.write(y);
            // Always close files.
            bufferedWriter.close();
        }catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
 
}