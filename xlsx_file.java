import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.*;
import jxl.*;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.format.Colour;
import java.util.*;
public class xlsx_file{
    int width = 1;
    int start = 1;
    String oneString= "";
    char[] charList;
    private String inputFile = "/home/ryan/Documents/Project/Bible Code/Bible_Code.xlsx";
    private WritableCellFormat times;
    File file;
    WorkbookSettings wbSettings;
    WritableWorkbook workbook;
    WritableSheet excelSheet;
    public xlsx_file(int w, int s, String sheetString){
        this.width = w;
        this.start = s;
        oneString = sheetString;
        charList = oneString.toCharArray();
    }
    public void writeSheet() throws IOException, WriteException{
        file = new File(inputFile);
        wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Sheet1", 0);
        excelSheet = workbook.getSheet(0);
        setSheet(width, excelSheet);
        workbook.write();
        workbook.close();
    }
    private void setSheet(int width, WritableSheet sheet ) throws WriteException{
        int c = 0;
        int r = 0;
        for(int i = start-width; i< charList.length; i++){
            if(c<width){
                addLabel(sheet, c, r, String.valueOf(charList[i]));
                c++;
            }else{
                c=0;
                r++;
                addLabel(sheet, c, r, String.valueOf(charList[i]));
                c++;
            }
        }   
    }
    private void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException {
        Number number =  new Number(column, row, integer, times);
        sheet.addCell(number);
    }
    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException {
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        times = new WritableCellFormat(times10pt);
        times.setWrap(true);
        
        CellView cv = new CellView();
        cv.setFormat(times);
    
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }
    private void highlight(int start, int spacing, String searchWord, Colour color)throws WriteException{
        int length = searchWord.length();
        WritableCellFormat background = new WritableCellFormat(times);
        background.setBackground(color);
        Label label;
        int[] cell_col_row = new int[2];
        for(int i = start; i<=(start + (length-1)*(spacing+1)); i =  i+spacing+1){
            cell_col_row = this.find_col_row(i);
            label = new Label(cell_col_row[0] , cell_col_row[1] ,excelSheet.getCell(cell_col_row[0],cell_col_row[1]).getContents(), background);
            this.excelSheet.addCell(label);
        }
        
    }
    private int[] find_col_row(int i){
        int col = i%width;
        int row = i/width;
        int[] result = {col, row};
        return result;
    }
}
