import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Example {
    public static void main(String[] args) {

            for(int i=0; i< 100; i++){
                if(i%7==0){
                    System.out.println(i);
                }
            }

        Map<String, String> map = new HashMap<>();
        map.put("name", "Moogii");
        map.put("age", "29");
        map.put("firstName", "Moogii");
        System.out.println(map);

        for (String each : map.keySet()) {
            System.out.println(map.get(each));
        }
        writeExcel();
    }

         public static void writeExcel(){
            String filePath = "Names.xlsx";
            try {
                FileInputStream in = new FileInputStream(filePath);
                Workbook workbook = WorkbookFactory.create(in);
                Sheet worksheet = workbook.getSheetAt(0);
                Cell column = worksheet.getRow(0).createCell(2);
                if(column == null){
                    column = worksheet.getRow(0).createCell(2);
                }
                column.setCellValue("State");
                Cell cont1 = worksheet.getRow(1).createCell(2);
                cont1.setCellValue("Mongolia");

                FileOutputStream out = new FileOutputStream(filePath);
                workbook.write(out);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
