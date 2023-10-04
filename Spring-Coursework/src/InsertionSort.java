import java.time.Duration;
import java.time.Instant;
import java.util.List;

//sorts all words alphabetically in listofwords using an insertion sort algorithm
public class InsertionSort {
    public SortResult insertionSort(List<String> listOfWords) {
        Instant timenow = Instant.now();
        int swapCount = 0;
        for (int i = 1; i < listOfWords.size(); i++) {
            String s = listOfWords.get(i);
            int j = i - 1;

            while (j >= 0 && s.compareTo(listOfWords.get(j)) < 0) {
                String y = listOfWords.get(j + 1);
                listOfWords.set(j + 1, listOfWords.get(j));
                listOfWords.set(j, y);
                j--;
                swapCount++;
            }
        }
        SortResult sortresult = new SortResult();
        sortresult.numberOfSwaps = swapCount;
        sortresult.timeTakenNanoSeconds = Duration.between(timenow,Instant.now()).getNano();
        sortresult.sortedList = listOfWords;
        return sortresult;
    }
}