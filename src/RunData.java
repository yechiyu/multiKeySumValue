import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RunData {
    public static void main(String[] args) {

        List<String[]> lis = new ArrayList<>();
        MultiMap lis1 = new MultiValueMap();

        String fileName = args[0];  // firstly, put the file to data folder. then change the name here
        int firstKeyCol = Integer.parseInt(args[1]);  // this is the first key column from 0 start
        int secondKeyCol = Integer.parseInt(args[2]); // this is the second key column also from 0 count

        int sumValueCol = Integer.parseInt(args[3]); // this is the column that you want to sum with first key and second key same.

        int resultCol = Integer.parseInt(args[4]);  // this is the test result column

        //The time complexity is approximately n + log n.


        try {



            FileInputStream fs=new FileInputStream(fileName);

            POIFSFileSystem ps=new POIFSFileSystem(fs);

            HSSFWorkbook wb=new HSSFWorkbook(ps);
            HSSFSheet sheet=wb.getSheetAt(0);



            Iterator<Row> rows1 = sheet.iterator();
            while(rows1.hasNext()) {

                Row row2 = rows1.next();
                Iterator<Cell> cells =  row2.cellIterator();
                String firstValue = "";
                String secondValue = "";
                String value = "";
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    if (cell.getColumnIndex() == firstKeyCol){
                        firstValue = cell.toString();
                    }else if (cell.getColumnIndex() == secondKeyCol){
                        secondValue = cell.toString();
                    } else if (cell.getColumnIndex() == sumValueCol){
                        value = cell.toString();
                    }
                }
                String [] colRecord = {firstValue, secondValue, value};
                lis.add(colRecord);
                lis1.put(firstValue+secondValue, value);


            }

        }catch (Exception o){

        }


        try {



            FileInputStream fs=new FileInputStream(fileName);

            POIFSFileSystem ps=new POIFSFileSystem(fs);

            HSSFWorkbook wb=new HSSFWorkbook(ps);
            HSSFSheet sheet=wb.getSheetAt(0);

            DecimalFormat df = new DecimalFormat("0");

            FileOutputStream out=new FileOutputStream(fileName);

            Iterator<Row> rows1 = sheet.iterator();

            while(rows1.hasNext()) {

                Row row2 = rows1.next();
                Iterator<Cell> cells =  row2.cellIterator();
                String firstValue = "";
                String secondValue = "";
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    if (cell.getColumnIndex() == firstKeyCol){
                        firstValue = cell.toString();
                    }else if (cell.getColumnIndex() == secondKeyCol){
                        secondValue = cell.toString();
                    }
                }
                Set keySet = lis1.keySet();
                double sumResult = 0;

                Iterator keyIterator = keySet.iterator();
                while (keyIterator.hasNext()){
                    Object key = keyIterator.next();
                    if (key.toString().equals(firstValue+secondValue)){
                        ArrayList values = (ArrayList) lis1.get(key);
                        Iterator valuesIterator = values.iterator();
                        while (valuesIterator.hasNext()){
                            sumResult += Double.valueOf(valuesIterator.next().toString());
                        }
                    } else if (key.toString().equals(secondValue+firstValue)){
                        ArrayList values = (ArrayList) lis1.get(key);
                        Iterator valuesIterator = values.iterator();
                        while (valuesIterator.hasNext()){
                            sumResult += Double.valueOf(valuesIterator.next().toString());
                        }
                    }
                }
                row2.createCell(resultCol).setCellValue(df.format(sumResult));

            }

            out.flush();
            wb.write(out);
            out.close();



        }catch (Exception o){

        }



    }
}
