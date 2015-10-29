package Panthera.Models;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import java.util.Arrays;
import java.io.FileInputStream;

/**
 * Created by Brandon on 28-Oct-15.
 */
public class ExportFromExcel {



        public static void main(String[] args)
        {
            Workbook workbook = null;
            try {
            //Creating a file stream containing the Excel file to be opened
            FileInputStream fstream = new FileInputStream("C://Users/Brandon/Desktop//Excel//klantenlijst.xls");

            //Instantiating a Workbook object

                workbook = new Workbook(fstream);


            //Accessing the first worksheet in the Excel file
            Worksheet worksheet = workbook.getWorksheets().get(0);

            //Exporting the contents of 7 rows and 2 columns starting from 1st cell to Array.
            Object dataTable [][] = worksheet.getCells().exportArray(4,0,7,8);

            for (int i = 0 ; i < dataTable.length ; i++)
            {
                System.out.println("["+ i +"]: "+ Arrays.toString(dataTable[i]));
            }
            //Closing the file stream to free all resources
            fstream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

