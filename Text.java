import java.io.File; 
import java.util.Scanner; 
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Text{
    String text = "";
    char[] text_array;
        
    String file_location;
    String file_name;
    boolean left_to_right;
    String language;
    boolean includes_chapter_and_verses;
    boolean includes_annotations_in_parenthesis;

    public Text(String file_location, String file_name, boolean left_to_right, String language, boolean includes_chapter_and_verses, boolean includes_annotations_in_parenthesis)throws Exception{
        this.file_location = file_location;
        this.file_name = file_name;
        this.left_to_right = left_to_right;
        this.language = language;
        this.includes_chapter_and_verses = includes_chapter_and_verses;
        this.includes_annotations_in_parenthesis = includes_annotations_in_parenthesis;
        File file = new File(file_location.concat(file_name)); 
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine() == true){
            text = text.concat(scanner.nextLine());
        }
        if(includes_chapter_and_verses == true){
            
        }else{
            
        }
        if(left_to_right == true){
            text = Text.to_one_string(text);
        }else{
            text = Text.reverse(text);
            text = Text.to_one_string(text);
        }

        
        text_array = text.toCharArray();
    }
    public String getText(){
        return text;
    }
    public char[] getTextArray(){
        return text_array;
    }
    public void copyText(){
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    //-------------------------------
    private static String to_one_string(String string){
        String[] remove = {".", "," , "!" , "?" , ";" , ":" , "-" , "=" , "+" , "(" , ")" , "[" , "]" , "{" , "}"  };
        
        for(int q = 0; q<remove.length; q++){
            string = string.replace(remove[q],"");
        }
        string = string.replace(" ", "");

        return string;
    }
    private static String reverse(String string){
        String Return = "";
        for(int i = string.length()-1; i>=0;i--){
            Return = Return + string.charAt(i);
        }
        
        return Return;
    }
    public static String remove_chapter_and_verses(String string){
        String Return = "";
        /*
        String regularExpression = "\\d+:\\d.";
        
        Pattern pt = Pattern.compile(regularExpression);
        Matcher mt = pt.matcher(string);
        */
        Return = string.replaceAll( "\\d" + ":"+ "\\d."," ");
        return Return;
    }
}
