import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class Task7 {
    //7.Реализуйте метод, который возвращает explanation снимка дня NASA,
    // в качестве параметра принимайте LocalDate -дату, на которую нужно брать снимок
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        LocalDate date = LocalDate.of(2003,2,2);
        String apiNasa = "https://api.nasa.gov/planetary/apod?";
        String apiKey = "api_key=DEMO_KEY";
        String dateUrl = "&date=" + getDate(date);
        builder.append(apiNasa);
        builder.append(apiKey);
        builder.append(dateUrl);
        String resultUrl = builder.toString();
        String page = downloadWebPage(resultUrl);
        String str = infoExplanation(page);
        String strDate = getDate(date);
        System.out.println(strDate);
        System.out.println(str);



    }


    private static String getDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        return (year + "-" + month + "-" + dayOfMonth);
    }

    private static String infoExplanation(String explanation) {
        int start = explanation.indexOf("explanation");
        int end = explanation.indexOf("hdurl");
        return explanation.substring(start + 14, end - 3);
    }


    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();


        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

        }

        return result.toString();

    }


}