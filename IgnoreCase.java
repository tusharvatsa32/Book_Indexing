import java.util.Comparator;

/**
 * @author Tushar Vatsa
 * andrewId : tvatsa
 */
public class IgnoreCase implements Comparator<Word> {
    /**
     * Method to compare the two words.
     * @param o1
     * @param o2
     * @return positive, 0, negative depending on the comparison
     */
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord().toLowerCase().compareTo(o2.getWord().toLowerCase());
    }
}
