import java.util.HashSet;
import java.util.Set;

/**
 * @author Tushar Vatsa
 * andrew Id : tvatsa
 */
public class Word implements Comparable<Word> {
    /**
     * Instance variable for the string word.
     */
    private String word;

    /**
     * Instance variable for the set of indices.
     */
    private Set<Integer> index;

    /**
     * Instance variable for the frequency.
     */
    private int frequency;

    /**
     * Constructor for the class.
     * @param w
     */
    public Word(String w) {
        if (w.matches("^[a-zA-Z]*$")) {
            word = w;
        }
        index = new HashSet<Integer>();
    }

    /**
     * sets the word as the new word.
     * @param newWord
     */
    public void setWord(String newWord) {
        if (newWord.matches("^[a-zA-Z]*$")) {
            word = newWord;
        }
    }

    /**
     * get the word.
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * sets the frequency of the word.
     * @param freq
     */
    public void setFrequency(int freq) {
        frequency = freq;
    }

    /**
     * get the frequency of the word.
     * @return frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * add the new line to the index.
     * @param line
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }

    /**
     * returns the set of all indexes.
     * @return index
     */
    public Set<Integer> getIndex() {
        return index;
    }

    /**
     * compares the two different words.
     * @param o
     * @return int : positive, 0 , negative depending on the comparison
     */
    @Override
    public int compareTo(Word o) {
       return getWord().compareTo(o.getWord());
    }

    /**
     * the way in which the words will be displayed.
     * @return the string to be displayed
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getWord()).append(" ")
                .append(String.valueOf(getFrequency()))
                .append(" ")
                .append(index.toString());
        return str.toString();
    }
}
