import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.*;
import java.util.*;

public class In_to_one_string
{
    String[] remove = {".", "," , "!" , "?" , ";" , ":" , "-" , "=" , "+" , "(" , ")" , "[" , "]" , "{" , "}"  };
    String Str1;
    char[] Str2;
    
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
        //copy to clipboard
        /*
        StringSelection stringSelection = new StringSelection(Str1);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        */
        return Str1;
    }
    public void setStr2(){
        Str2 = Str1.toCharArray();
    }
}
