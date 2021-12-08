import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;

/**
 * @author Tushar Vatsa
 * andrewId : tvatsa
 */
public class Index {
    /**
     * Helper method to get all the words in a line.
     * @param binaryTree : the binary tree where we add the words
     * @param line : the line from which we get the words
     * @param numLines : the line number
     * @param flag : flag to determine if we need to ignore the case
     */
    private void traverse(BST<Word> binaryTree, String line, int numLines, boolean flag) {
        if (line == null) {
            return;
        }
        if (flag) {
            line = line.toLowerCase();
        }
        String[] words = line.split("\\W");
        for (String word : words) {
            if (word.equals("") || !word.matches("^[a-zA-Z]*$")) {
                continue;
            }
            Word newWord = new Word(word);
            if (binaryTree.search(newWord) == null) {
                newWord.setFrequency(newWord.getFrequency() + 1);
                newWord.addToIndex(numLines + 1);
                binaryTree.insert(newWord);
            } else {
                Word alreadyExisting = binaryTree.search(newWord);
                alreadyExisting.setFrequency(alreadyExisting.getFrequency() + 1);
                alreadyExisting.addToIndex(numLines + 1);
            }
        }
    }

    /**
     * Helper method to scan the file.
     * @param binaryTree : the binary tree to add all the words
     * @param fileName : the name of the file
     * @param flag : flag which determines if we need to ignore the case
     */
    private void scanning(BST<Word> binaryTree, String fileName, boolean flag) {
        Scanner scanner = null;
        try {
            int numLines = 0;
            scanner = new Scanner(new File(fileName), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line == null) {
                    continue;
                }
                traverse(binaryTree, line, numLines, flag);
                numLines += 1;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Method to build the Index using the string filename.
     * @param fileName
     * @return binaryTree
     */
    public BST<Word> buildIndex(String fileName) {
        BST<Word> binaryTree = new BST<Word>();
        scanning(binaryTree, fileName, false);
        return binaryTree;
    }

    /**
     * Method to build the Index using the filename and the comparator.
     * @param fileName
     * @param comparator
     * @return binaryTree
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        boolean flag = false;
        if (comparator instanceof IgnoreCase) {
            flag = true;
        }
        BST<Word> binaryTree = new BST<Word>(comparator);
        scanning(binaryTree, fileName, flag);
        return binaryTree;
    }

    /**
     * Method to build the Index using the list of words and the comparator.
     * @param list
     * @param comparator
     * @return binaryTree
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        BST<Word> binaryTree = new BST<Word>(comparator);
        for (Word word : list) {
            if (binaryTree.search(word) == null) {
                binaryTree.insert(word);
            }
        }
        return binaryTree;
    }

    /**
     * Helper method to make the list.
     * @param tree
     * @return array
     */
    private ArrayList<Word> makingList(BST<Word> tree) {
        ArrayList<Word> array = new ArrayList<Word>();
        Iterator<Word> itr = tree.iterator();
        while (itr.hasNext()) {
            array.add(itr.next());
        }
        return array;
    }

    /**
     * Helper method to sort the list alphabetically.
     * @param tree
     * @return array
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        /*
         * Even though there should be no ties with regard to words in BST,
         * in the spirit of using what you wrote,
         * use AlphaFreq comparator in this method.
         */
        AlphaFreq comp = new AlphaFreq();
        ArrayList<Word> array = makingList(tree);
        Collections.sort(array, comp);
        return array;
    }

    /**
     * Helper method to sort the list by frequency(reverse).
     * @param tree
     * @return array
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        Frequency comp = new Frequency();
        ArrayList<Word> array = makingList(tree);
        Collections.sort(array, comp);
        return array;

    }

    /**
     * Helper method to get the top frequencies in the list.
     * @param tree
     * @return list of words with highest frequency
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> array = sortByFrequency(tree);
        int maxFrequency = array.get(0).getFrequency();
        ArrayList<Word> output = new ArrayList<Word>();
        for (Word word : array) {
            if (word.getFrequency() == maxFrequency) {
                output.add(word);
            }
        }
        return output;
    }

}
