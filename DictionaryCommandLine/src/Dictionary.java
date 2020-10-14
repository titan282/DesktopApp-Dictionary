import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> dictionary=new ArrayList<Word>();

    public void addWord(Word w){
       dictionary.add(w);
    }
    public void removeWord(Word w){
        dictionary.remove(w);
    }
    public int getSize(){
        return dictionary.size();
    }

    /**
     * Get Word from index.
     * @param index index
     * @return word
     */
    public Word getWord(int index){
        return this.dictionary.get(index);
    }

}
