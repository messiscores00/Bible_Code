public class word_search
{
    String Input;
    int i = 0;
    int first = 0;
    int[] Return = new int[2];
    int[] spacing;
    int[] Result;
    public word_search(String input, String search)
    {
        Input = new String(input);
        Result = this.search_for_word(search,0);
    }
    private int[] search_for_word(String searchWord, int a){
        if(a==0){
            spacing = new int[searchWord.length()-1];
            while(i<Input.length()){
                if(Input.charAt(i) == searchWord.charAt(0)){
                    first = i;
                    i++;
                    this.search_for_word(searchWord,1);
                }
                i++;
            }
        }
        //--------------------------------
        while(i<Input.length()){
            if(Input.charAt(i) == searchWord.charAt(a)){
                i++;
                if(a+1<searchWord.length()){
                    this.search_for_word(searchWord,a+1);
                }else{
                    if(this.equals(spacing)){
                        Return[0] = first;
                        Return[1] = spacing[0];
                        return Return;
                    }else{
                        
                    }
                }
            }
            i++;
            spacing[a-1]++;
        }
        return Return;
    }
    
    private boolean equals(int[] array){
        for(int i = 1; i<array.length ; i++){
            if(array[i-1] != array[i]){
                return false;
            }
        }
        return true;
    }
    
    public int[] getResult(){
        return Result;
    }
}
