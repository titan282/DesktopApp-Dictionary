import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionaryData = new Dictionary();

    public DictionaryManagement() {
    }

    public DictionaryManagement(Dictionary dictionaryData) {
        this.dictionaryData = dictionaryData;
    }

    public void insertFromCommandLine() {
        System.out.println("Số từ muốn nhập từ Commandline:");
        Scanner scan = new Scanner(System.in);
        int numberOfWord = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfWord; i++) {
            String target = scan.nextLine();
            String explain = scan.nextLine();
            Word w = new Word(target, explain);
            dictionaryData.addWord(w);
        }
    }

    public void insertFromFile() {
        try {
            Scanner scan = new Scanner(new File("Input.txt"));
            int numberOfWords = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < numberOfWords; i++) {
                String[] line = scan.nextLine().split("\\s+", 2);
                String target = line[0];
                String explain = line[1];
                dictionaryData.addWord(new Word(target, explain));
            }
        } catch (FileNotFoundException e) {
            System.out.println("hic :(( File Not File !");
            e.printStackTrace();
        }
    }

    /**
     * Tra ve cum E-V khi muon tim nghia cua target
     *
     * @param target target
     * @return
     */
    public boolean haveWord(String target) {
        boolean temp = false;
        for (int i = 0; i < dictionaryData.getSize(); i++) {
            Word word = dictionaryData.getWord(i);
            if (word.getWord_target().compareToIgnoreCase(target) == 0) {
                temp = true;
                break;
            }
        }
        return temp;
    }

    public boolean haveStartWith(String text) {
        int size = dictionaryData.getSize();
        boolean temp = false;
        for (int i = 0, no = 0; i < size; ++i) {
            Word w = this.dictionaryData.getWord(i);
            if (w.getWord_target().toUpperCase().startsWith(text.toUpperCase())) {
                temp = true;
                break;
            }
        }
        return temp;
    }
        public Word dictionaryLookup (String target){
            Word temp = new Word();
            for (int i = 0; i < dictionaryData.getSize(); i++) {
                Word word = dictionaryData.getWord(i);
                if (word.getWord_target().compareToIgnoreCase(target) == 0) {
                    temp = word;
                    break;
                }
            }
            return temp;
        }

        public void addWord () {
            Scanner scan = new Scanner(System.in);
            System.out.print(" -->Nhap New word: ");
            String target = scan.nextLine();
            if (haveWord(target)) {
                System.out.println(" -->Tu nay da co trong tu dien roi, Vui long nhap tu khac!");
            } else {
                System.out.print(" -->Nhap nghia cua tu: ");
                String explain = scan.nextLine();
                dictionaryData.addWord(new Word(target, explain));
                System.out.printf(" -->Nhap tu [%s] thanh cong\n", target);
            }

        }

        public void removeWord (String target){
            if (haveWord(target)) {
                dictionaryData.removeWord(dictionaryLookup(target));
                System.out.printf(" -->Xoa tu [%s] thanh cong\n", target);
            } else {
                System.out.println(" -->Khong co tu nay trong tu dien. Chon Option 1 neu ban muon them!");
            }

        }

        public void editWord () {
            System.out.print(" -->Nhap tu muon edit: ");
            Scanner scan = new Scanner(System.in);
            String target = scan.nextLine();
            if (haveWord(target)) {
                dictionaryData.removeWord(dictionaryLookup(target));
                System.out.print(" -->Word target: ");
                target = scan.nextLine();
                System.out.print(" -->Word explain:");
                String explain = scan.nextLine();
                dictionaryData.addWord(new Word(target, explain));
                System.out.printf(" -->Edit tu [%s] thanh cong!\n",target);
            } else {
                System.out.println(" -->Khong co tu nay trong tu dien, Chon option 1 neu ban muon them no vao tu dien !");
            }
        }

        public void dictionaryExportToFile (String filePath){

            try {
                PrintWriter print = new PrintWriter(new File(filePath));
                int size = this.dictionaryData.getSize();
                print.printf("Tu dien nay co %d tu:\n", size);
                print.println("______________________________________________");
                print.printf("%-6s|%-28s|%s\n", "No", "English", "Vietnamese");
                for (int i = 0; i < size; ++i) {
                    Word w = this.dictionaryData.getWord(i);
                    print.printf("%-6s|%-28s|%s\n", i + 1, w.getWord_target(), w.getWord_explain());
                }
                print.flush();
                System.out.printf(" -->Xuat file %s thanh cong\n", filePath);
            } catch (IOException e) {
                System.err.println("\nError: Khong ghi duoc vao File");
            }
        }
    }




