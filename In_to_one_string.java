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
import java.util.*;

public class In_to_one_string
{
    String[] remove = {".", "," , "!" , "?" , ";" , ":" , "-" , "=" , "+" , "(" , ")" , "[" , "]" , "{" , "}"  };
    String Str1;
    char[] Str2;
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile = "/home/ryan/Documents/Project/Bible Code/Bible_Code.xlsx";
    int width = 1;
    int start = 1282;
    
    public In_to_one_string(String s )
    {
        Str1 = new String(s);
    }
    public void set_String(String s){
        Str1 = s;
    }
    public String to_one_string (){
        //remove everything in the remove array
        for(int q = 0; q<remove.length; q++){
            Str1=Str1.replace(remove[q],"");
        }
        //replace the spaces with /
        Str1 = Str1.replace(" ", "");
        //output that string
        StringSelection stringSelection = new StringSelection(Str1);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        return Str1;
    }
    public void setStr2(){
        Str2 = Str1.toCharArray();
    }
    public void copy(){
        StringSelection stringSelection = new StringSelection(Str1.substring(1282 - 114,1282));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    /*
    public void setOutputFile(String inputFile){
        this.inputFile = inputFile;
    }
    */
    public void setWidth(int w){
        this.width = w;
    }
    public void setStart(int s){
        this.start = s;
    }
    public void writeSheet() throws IOException, WriteException{
        this.to_one_string();
        this.setStr2();
        this.setWidth(114);
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Sheet1", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        setSheet(width, excelSheet);
        workbook.write();
        workbook.close();
    }
    private void setSheet(int width, WritableSheet sheet ) throws WriteException{
        int c = 0;
        int r = 0;
        for(int i = start-width; i< Str2.length; i++){
            if(c<width){
                addLabel(sheet, c, r, String.valueOf(Str2[i]));
                c++;
            }else{
                c=0;
                r++;
                addLabel(sheet, c, r, String.valueOf(Str2[i]));
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
    
}
