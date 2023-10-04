import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
    private String dateSupplied;
    public DateHandler(String dateString){
        dateSupplied = dateString;
    }

    public Boolean isValid(){
        Date validDate = this.getValidDate();
        return validDate != null;
    }

    public Date getValidDate(){
        Boolean finishedSuffixMessage = false;
        Date validDate = getValidDateForFormat("dd/MM/yyyy", finishedSuffixMessage);
        if (validDate != null){
            return validDate;
        }
        validDate = getValidDateForFormat("dd-MM-yyyy", finishedSuffixMessage);
        if (validDate != null){
            return validDate;
        }
        validDate = getValidDateForFormat("d'st' MMMM yyyy", finishedSuffixMessage);
        if (validDate != null){
            return validDate;
        }
        validDate = getValidDateForFormat("d'nd' MMMM yyyy", finishedSuffixMessage);
        if (validDate != null){
            return validDate;
        }
        validDate = getValidDateForFormat("d'rd' MMMM yyyy", finishedSuffixMessage);
        if (validDate != null){
            return validDate;
        }
        return validDate = getValidDateForFormat("d'th' MMMM yyyy", finishedSuffixMessage);
    }

    private Date getValidDateForFormat(String dateFormat, Boolean statement){
        if (dateSupplied.contains(" ")){
            String finalSuffix = verifySuffix(dateSupplied);
            Character firstChar = dateSupplied.charAt(1);
            Character secondChar = dateSupplied.charAt(2);
            String finalcheck = String.format("%c%c", firstChar, secondChar);
            if (!finalSuffix.equals(finalcheck)){
                return null;
            }
        }
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        try{
            return dateFormater.parse(dateSupplied);
        }
        catch(ParseException parseE){
            return null;
        }
    }

    private String verifySuffix(String fullDate) {
        Character firstChar = fullDate.charAt(0);
        String finalSuffix = String.format("%c", firstChar);
        int suffix = Integer.parseInt(finalSuffix);
        if (suffix > 3 && suffix < 21) return "th";
        switch (suffix % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}