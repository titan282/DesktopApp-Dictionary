package cmd;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DictionaryCommandLine {
    private Dictionary dictionaryData;
    public DictionaryCommandLine(){}
    public DictionaryCommandLine(Dictionary dictionaryData) {
        this.dictionaryData = dictionaryData;
    }

    public void dictionarySearcher(){
        DictionaryManagement management =new DictionaryManagement(dictionaryData);
        System.out.print(" -->Nhap tu muon search: ");
        Scanner scan=new Scanner(System.in);
        String text=scan.nextLine();
        if(management.haveStartWith(text)) {
            System.out.printf("%-6s|%-28s|%s\n", "No", "English", "Vietnamese");
            int size = dictionaryData.getSize();
            for (int i = 0, no = 0; i < size; ++i) {
                Word w = this.dictionaryData.getWord(i);
                if (w.getWord_target().toUpperCase().startsWith(text.toUpperCase()))
                    System.out.printf("%-6s|%-28s|%s\n", String.valueOf(++no), w.getWord_target(), w.getWord_explain());
            }
        }else{
            System.out.printf("Hien khong co tu [%s] trong tu dien, Chon Option 1 neu ban muon them\n",text);
        }
    }
}
