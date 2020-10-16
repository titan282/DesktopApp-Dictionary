package cmd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DictionaryManagement {
    private Dictionary dictionaryData = new Dictionary();

    public Dictionary getDictionaryData() {
        return dictionaryData;
    }

    public void setDictionaryData(Dictionary dictionaryData) {
        this.dictionaryData = dictionaryData;
    }

    public DictionaryManagement() {
    }

    public DictionaryManagement(Dictionary dictionaryData) {
        this.dictionaryData = dictionaryData;
    }
    public void insertFromFile() {
        try {
            File fileDir = new File("dictionaries.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
            while (br.ready()) {
                String lineWord = br.readLine();
                String[] parts = lineWord.split("\\t");
                if (parts.length == 2) {
                    addWord(parts[0], parts[1]);
                }
            }
            br.close();
        }catch (IOException e) {
            System.out.println("hic :(( File Not Found !");
            e.printStackTrace();
        }
    }

    public void dictionaryToText(){
        try {
            FileWriter myWriter = new FileWriter("dictionaries.txt");
            for(int i=0;i<dictionaryData.getSize();i++) {
                myWriter.write(dictionaryData.getWord(i).getWord_target()+"\t"+dictionaryData.getWord(i).getWord_explain()+"\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
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

    public Word dictionaryLookup(String target) {
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

    public void addWord(String target, String explain) {
        dictionaryData.addWord(new Word(target, explain));
    }

    public void removeWord(String target) {
            dictionaryData.removeWord(dictionaryLookup(target));
    }


    public void dictionaryExportToFile(String filePath) {

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

    public String[] dictionarySearcher(String text) {
        List<String> a = new ArrayList<String>();
        int size = dictionaryData.getSize();
        for (int i = 0, no = 0; i < size; ++i) {
            Word w = this.dictionaryData.getWord(i);
            if (w.getWord_target().toUpperCase().startsWith(text.toUpperCase())) {
                a.add(w.getWord_target());
            }
        }
        return (String[]) a.toArray();
    }
}




