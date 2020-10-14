package cmd;
public class DictionaryCmdline {
    private Dictionary dictionaryData=new Dictionary();

    public DictionaryCmdline(){

    }
    public DictionaryCmdline(Dictionary dictionaryData) {
        this.dictionaryData = dictionaryData;
    }


    void showAllWords(){
        System.out.printf("%-6s|%-28s|%s\n", "No", "English", "Vietnamese");
        int numberOfWords=this.dictionaryData.getSize();
        for(int i=0;i<numberOfWords;i++){
            Word word=this.dictionaryData.getWord(i); //Lấy ra cụm từ E_V từ vị trí i
            System.out.printf("%-6s|%-28s|%s\n",i+1,word.getWord_target(),word.getWord_explain());
        }
    }
}
