package Panthera.Models;

import java.io.*;
import java.nio.file.*;

import static java.nio.file.Paths.*;

/**
 * Created by Thijs on 9/24/2015.
 */
public class Handleiding {

    Path dir = Paths.get("src/Panthera/resources");

    public void leesHandleidingTest() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {

                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {

            System.err.println(x);
        }
    }
   // public void getFactuurHandleiding(){

   // }
    public void setFactuurHandleiding(File inhoud) {this.inhoud = inhoud;}




    /*public void leesHandleiding( ) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/resources/Panthera/Views/test.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        //for (int i=0; i < handleidingen.length; i++){
           // System.out.print(handleidingen[i]);
        }*/
}










