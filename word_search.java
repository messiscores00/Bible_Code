public class word_search
{
    String Input;
    String searchWord;
    int first = 0;
    int spacing = 0;
    int[] Return = new int[2];
    int[] Result;
    public word_search(String input, String search)
    {
        Input = new String(input);
        searchWord = new String(search);
        Result = this.search_for_word(0,0);
    }
    
    private int[] search_for_word(int a, int i){
        if(a==0){
            while(i<Input.length()){
                if(Input.charAt(i) == searchWord.charAt(0)){
                    first = i;
                    i++;
                    Return = this.search_for_word(1,i);
                    if(Return[0] != 0 || Return[1] != 0){
                        return Return;
                    }
                    i--;
                }
                i++;
            }
        }
        //-----------------------------
        if(a==1){
            while(i<Input.length()){
                if(Input.charAt(i) == searchWord.charAt(1)){
                    i++;
                    Return = this.search_for_word(2,i + spacing);
                    if(Return[0] != 0 || Return[1] != 0){
                        return Return;
                    }
                    i--;
                    //spacing--;
                }
                spacing++;
                i++;
            }
            spacing = 0;
            Return[0] = 0;
            Return[1] = 0;
            return Return;
        }
        //-----------------------------
        if(i<Input.length()){
            if(Input.charAt(i) == searchWord.charAt(a)){
                if(a<searchWord.length()){
                    Return[0] = first;
                    Return[1] = spacing;
                    return Return;
                }
                i++;
                this.search_for_word(a+1,i + spacing);
            }
            Return[0] = 0;
            Return[1] = 0;
            return Return;
        }
        return Return;
    }
    /*
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
    */
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
