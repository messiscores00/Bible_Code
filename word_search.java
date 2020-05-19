
public class word_search
{
    String Input;
    int i = 0;
    public word_search(String input)
    {
        Input = new String(input);
    }
    public int[] search_for_word(String searchWord, int space){
        int spacing = space;
        int[] Return = {0,0};
        while(i<Input.length()){
            if(Input.charAt(i) == searchWord.charAt(0)){
                try{
                    this.search_for_word(searchWord.substring(1), spacing);
                }catch(IndexOutOfBoundsException test){
                    Return[0] = 1;
                    Return[2] = spacing;
                    return Return;
                }
            }
            i++;
        }
        return Return;
    }
}
