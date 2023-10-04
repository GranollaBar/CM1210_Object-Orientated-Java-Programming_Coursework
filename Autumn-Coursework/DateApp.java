import java.io.File;
import java.util.*;

public class DateApp {
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.print("no valid command has been entered");
        }
        else{
            String[] finalDatesList;
            Boolean readingTextFile;
            if (args.length == 1 && args[0].contains("txt")){
                finalDatesList = datesList(args);
                readingTextFile = true;
            }
            else{
                finalDatesList = args;
                readingTextFile = false;
            }

            for (int i = 0; i < Objects.requireNonNull(finalDatesList).length; i ++){
                Boolean finalFormat = isDateString(finalDatesList[i]);
                String finalDate;
                if (finalFormat){
                    finalDate = String.format("%s %s %s", finalDatesList[i], finalDatesList[i+1], finalDatesList[i+2]);
                    i += 2;
                }
                else{
                    finalDate = finalDatesList[i];
                }

                DateHandler dateHandler = new DateHandler(finalDate);
                if (dateHandler.isValid()){
                    System.out.println(finalDate + " is valid ");
                }
                else{
                    System.out.println(finalDate + " is not valid ");
                }
            }

            if (readingTextFile){
                printDates(finalDatesList);
            }
        }
    }

    private static void printDates(String[] dates){
        List<SimpleDate> simpleDates = new ArrayList<>();
        for (String dateStr: dates){
            SimpleDate simpleDate = new SimpleDate(dateStr);
            System.out.println("oeye" + simpleDate);
            if (simpleDate.isValid()){
                simpleDates.add(simpleDate);
            }
        }
        SimpleDate[] arraySimpleDates = new SimpleDate[simpleDates.size()];
        for (int i = 0; i < simpleDates.size(); i ++){
            arraySimpleDates[i] = simpleDates.get(i);
        }
        System.out.print(Assignment1.mostFrequentDayOfWeek(arraySimpleDates));
    }

    private static String[] datesList(String[] textFile){
        try{
            ArrayList<String> allLines = new ArrayList<String>();
            File file=new File(textFile[0]);
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                allLines.add(sc.nextLine());
            }
            allLines.removeAll(Arrays.asList("", null));
            return allLines.toArray(new String[0]);
        }
        catch (Exception ex){
            System.out.print("File not Found");
            return null;
        }
    }

    private static Boolean isDateString(String date){
        return date.length() <= 4;
    }
}