import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //List<Strings> containing all words in file (excepting punctuation)
        List<String> inputRead = ReadFiles("resources/Input.txt");
        List<String> stopWordsRead = ReadFiles("resources/stopwords.txt");

        //constructors to return data from other classes
        StopWordsBasic stopwordsbasic = new StopWordsBasic();
        InsertionSort insertionsort = new InsertionSort();
        MergeSort mergesort = new MergeSort();


        assert stopWordsRead != null;
        List<String> allWordsStoppedWordsRemoved = stopwordsbasic.deleteStopWords(inputRead,stopWordsRead);

        System.out.println("\nAll words: " + allWordsStoppedWordsRemoved);

        SortResult hundredinsertionsortresult = insertionsort.insertionSort(allWordsStoppedWordsRemoved.subList(0,Math.min(99,allWordsStoppedWordsRemoved.size()-1)));
        int insertionHundredWords = hundredinsertionsortresult.timeTakenNanoSeconds;

        SortResult twohundredinsertionsortresult = insertionsort.insertionSort(allWordsStoppedWordsRemoved.subList(0,Math.min(199,allWordsStoppedWordsRemoved.size()-1)));
        int insertionTwoHundredWords = twohundredinsertionsortresult.timeTakenNanoSeconds;

        SortResult finalinsertionsortresult = insertionsort.insertionSort(allWordsStoppedWordsRemoved);
        int insertionFinalWords = finalinsertionsortresult.timeTakenNanoSeconds;

        //prints all sortResults found above (including the swap number, time efficiency and sorted alphabetical list of words
        System.out.println("\n\nInsertion sort sorted Input alphabetically: " + finalinsertionsortresult.sortedList);
        System.out.printf("\nThe time it took to sort 100 words in the insertion sort (Nanoseconds): %s\nThe time it took to sort 200 words in the insertion sort (Nanoseconds): %s\nThe time it took to sort %s words in the insertion sort (Nanoseconds): %s\nThe total number of swaps in the insertion sort: %s\n%n",insertionHundredWords, insertionTwoHundredWords, finalinsertionsortresult.sortedList.size(), insertionFinalWords, finalinsertionsortresult.numberOfSwaps);


        allWordsStoppedWordsRemoved = stopwordsbasic.deleteStopWords(inputRead,stopWordsRead);

        SortResult hundredmergesortresult = mergesort.mergeSort(allWordsStoppedWordsRemoved.subList(0,Math.min(99,allWordsStoppedWordsRemoved.size()-1)));
        int mergeHundredWords = hundredmergesortresult.timeTakenNanoSeconds;

        SortResult twohundredmergesortresult = mergesort.mergeSort(allWordsStoppedWordsRemoved.subList(0,Math.min(199,allWordsStoppedWordsRemoved.size()-1)));
        int mergeTwoHundredWords = twohundredmergesortresult.timeTakenNanoSeconds;

        SortResult finalmergesortresult = mergesort.mergeSort(allWordsStoppedWordsRemoved);
        int mergeFinalWords = finalmergesortresult.timeTakenNanoSeconds;

        //prints all sortResults found above (including the swap number, time efficiency and sorted alphabetical list of words
        System.out.println("\nMerge sort sorted Input alphabetically: " + finalmergesortresult.sortedList);
        System.out.printf("\nThe time it took to sort 100 words in the merge sort (Nanoseconds): %s\nThe time it took to sort 200 words in the merge sort (Nanoseconds): %s\nThe time it took to sort %s words in the merge sort (Nanoseconds): %s\nThe total number of swaps in the merge sort: %s\n%n",mergeHundredWords, mergeTwoHundredWords, finalmergesortresult.sortedList.size(), mergeFinalWords, finalmergesortresult.numberOfSwaps);
    }

    //Returns a list<string> of all words in a text file, reading them line by line
    private static List<String> ReadFiles(String filename){
        try{
            List<String> allWords = new ArrayList<String>();
            File file=new File(filename);
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                String[] lineWords = sc.nextLine().split("\\p{Punct}| |^\\w.,;:'");
                allWords.addAll(Arrays.asList(lineWords));
            }
            return allWords;
        }
        catch (Exception ex){
            System.out.print("File not Found");
            return null;
        }
    }
}