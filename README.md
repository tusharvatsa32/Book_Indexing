# Book_Indexing
While reading a book, one of the things we would do is using the index at the end of the book in which we can find pages where a word or a phrase is used in the book. Our major goal, this time, is to make a program that builds an index of words from a file that we will parse.

## Classes 
### Word.java:
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
  
