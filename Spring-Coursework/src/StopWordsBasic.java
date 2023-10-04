import java.util.*;

public class StopWordsBasic {

    //Returns a list<string> containg all words in the input that is not present in the stopwords list
    public List<String> deleteStopWords(List<String> input, List<String> stopWords){
        for (String stopvalue:stopWords) {
            String newStopValue = stopvalue.toLowerCase();
            List<String> newList = new ArrayList<String>();
            for (String value : input) {
                String newValue = value.toLowerCase();
                if ((!newValue.equals("")) && (!newValue.equalsIgnoreCase(newStopValue))) {
                    newList.add(newValue);
                }
            }
            input = newList;
        }
        return input;
    }
}
