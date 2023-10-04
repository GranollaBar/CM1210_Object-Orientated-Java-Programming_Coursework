import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

//sorts all words alphabetically in listofwords using a recursive merge sort algorithm
public class MergeSort {
    public SortResult mergeSort(List<String> listOfWords) {
        Instant timenow = Instant.now();
        int swapCount = 0;
        if (listOfWords.size() >= 2) {
            String[] left = new String[listOfWords.size() / 2];
            String[] right = new String[listOfWords.size() - (listOfWords.size() / 2)];

            List<String> leftArrayList = new ArrayList<>();
            List<String> RightArrayList = new ArrayList<>();
            Collections.addAll(leftArrayList, left);
            Collections.addAll(RightArrayList, right);

            for (int i = 0; i < leftArrayList.size(); i++) {
                leftArrayList.set(i,listOfWords.get(i));
            }
            for (int j = 0; j < RightArrayList.size(); j++) {
                RightArrayList.set(j,listOfWords.get(j + listOfWords.size()/2));
            }

            mergeSort(leftArrayList);
            mergeSort(RightArrayList);
            swapCount = merge(listOfWords, leftArrayList, RightArrayList);
        }
        SortResult sortresult = new SortResult();
        sortresult.numberOfSwaps = swapCount;
        sortresult.timeTakenNanoSeconds = Duration.between(timenow,Instant.now()).getNano();
        sortresult.sortedList = listOfWords;
        return sortresult;
    }

    //performs the physical merge of all the elements in the result list<String>
    public static int merge(List<String> result, List<String> left, List<String> right) {
        int swapCount = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < result.size(); i++) {
            if (y >= right.size() || (x < left.size() && left.get(x).compareToIgnoreCase(right.get(y))<0)) {
                result.set(i,left.get(x));
                x++;
                swapCount++;
            } else {
                result.set(i,right.get(y));
                y++;
                swapCount++;
            }
        }
        return swapCount;
    }
}
