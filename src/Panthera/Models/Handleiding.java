package Panthera.Models;

import java.io.*;
import java.nio.file.*;

import static java.nio.file.Paths.*;

/**
 * Created by Thijs on 9/24/2015.
 */
public class Handleiding {

    String inhoud;

    Path dir = Paths.get("src/Panthera/resources");

    public Handleiding(String handleidingNaam) throws IOException {
        // Set inhoud van handleindg file in inhoud var (LEES BESTAND UIT)

        BufferedReader br = new BufferedReader(new FileReader("src/resources"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            handleiding.setInhoud(inhoud);
        }
    }

    public void leesHandleidingTest() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {

                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {

            System.err.println(x);
        }
    }

    public String getInhoud(String handleiding) throws IOException() {
        BufferedReader br = new BufferedReader(new FileR/eader("src/resources/email_handleiding"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            handleiding.setInhoud(inhoud);
    }
    }



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










