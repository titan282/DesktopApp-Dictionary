import java.util.InputMismatchException;
import java.util.Scanner;

public class DictionaryCommandLine {
    private Dictionary dictionaryData;
    public DictionaryCommandLine(){}
    public DictionaryCommandLine(Dictionary dictionaryData) {
        this.dictionaryData = dictionaryData;
    }

    public void dictionaryBasic(){
        dictionaryData=new Dictionary();
        (new DictionaryManagement(dictionaryData)).insertFromCommandLine();
        (new DictionaryCmdline(dictionaryData)).showAllWords();
    }

    public void dictionaryAdvanced(){
        dictionaryData=new Dictionary();
        DictionaryManagement management=new DictionaryManagement(dictionaryData);
        management.insertFromFile();
        (new DictionaryCmdline(dictionaryData)).showAllWords();
        Scanner scan = new Scanner(System.in);
        System.out.println("---------Welcome to TNDictionary--------");
        while(true){
            int option =0;
            System.out.println("\nOption: 1. Them tu | 2. Edit tu | 3. Xoa tu | 4. Search | 5. Export to file | 6.Show All Word | 7.Ket thuc");
            System.out.print("Nhap option: ");
            try{
                option = scan.nextInt();
            }
            catch (InputMismatchException e){
            }
            scan.nextLine();
            switch (option){
                case 1: {
                    (new DictionaryManagement(this.dictionaryData)).addWord();
                    break;
                }
                case 2:{
                    (new DictionaryManagement(this.dictionaryData)).editWord();
                    break;
                }
                case 3: {
                    System.out.print(" -->Nhap tu can xoa: ");
                    String t = scan.nextLine();
                    // remove word
                    (new DictionaryManagement(this.dictionaryData)).removeWord(t);
                    break;
                }
                case 4: {
                    dictionarySearcher();

                    break;
                }

                case 5: {
                    System.out.print(" -->Nhap ten file muon xuat: ");
                    String f = scan.nextLine();
                    (new DictionaryManagement(this.dictionaryData)).dictionaryExportToFile(f);

                    break;
                }
                case 6:{
                    (new DictionaryCmdline(this.dictionaryData)).showAllWords();
                    break;
                }
                case 7:{
                    System.out.println("------Cam on ban da su dung dich vu <3-------");
                    System.exit(0);
                }
                default:
                    System.out.println("    Vui long nhap option trong khoang tu 1 den 7!");
            }
        }
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
