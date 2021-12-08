import java.util.Comparator;

/**
 * @author Tushar Vatsa
 * andrewId : tvatsa
 */
public class Frequency implements Comparator<Word> {
    /**
     * Method to compare the two words.
     * @param o1
     * @param o2
     * @return positive, 0, negative depending on the comparison
     */
    @Override
    public int compare(Word o1, Word o2) {
        return -1 * (o1.getFrequency() - o2.getFrequency());
    }
}
