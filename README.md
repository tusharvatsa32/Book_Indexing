# Book_Indexing
While reading a book, one of the things we would do is using the index at the end of the book in which we can find pages where a word or a phrase is used in the book. Our major goal, this time, is to make a program that builds an index of words from a file that we will parse.

## Classes 
### Word.java: Class for the words.
#### Fields : 
1. word (String) : Stores the word that we will parse from the book/article/magazine.
2. index (Set<Integer>) : Stores the line number where the word appeared.
3. frequency (integer) : Stores the frequency of the word.

#### Methods :
1. getWord() : returns the string value of the word.
2. setFrequency() : takes frequency as the input and sets the frequency of that particular word object.
3. getFrequency() : returns the frequency of that particular word object.
4. addToIndex() : takes line number as the input and inserts that in the index set.
5. getIndex() : returns the set of line numbers for the particular object.
6. compareTo() : takes another word as the argument, used to compare the word with other word,\
  return positive integer if word instance is lexicographically bigger than the argument word,\
  return negative integer if word instance is lexicographically smaller than the argument word,\
  return zero if the two words are lexicographically same.
7. toString() : returns the string word, the frequency and the set of line numbers.

### BST.java : Class to store all the words in the Binary Search Tree.
#### Nested Class : Node<T>
##### Fields :
1. data (T) : Accepts any data type, and stores that in the data instance.
2. left (Node<T>) : Left pointer from the current Node.
3. right (Node<T>) : Right pointer from the current Node.
#### Fields :
1. root (Node<T>): root of the binary tree.
2. comparator : stores the comparator object.
#### Methods :
1. getRoot() : return null if the root is null, else return the data of the root.
2. getHeight() : returns the height of the binary search tree.
3. getNumberOfNodes() : returns the total number of nodes in binary search tree.
4. search() : if the word is found, it is returned. Else null is returned. 
5. insert() : the word is inserted at the correct position based on the comparator provided.
6. iterator() : an iterator object which iterates in an in order fashion.
#### Nested Class : BSTIterator
##### Fields :
1. stack : an instance variable for iterating through the binary search tree in an inorder fashion.
##### Methods :
1. hasNext() : returns true if the stack size is greater than 0.
2. next() : returns the next node data in the binary search tree(in order fashion).

  
