import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Task6 {
    //6.Реализуйте метод, который выводит explanation сегодняшнего снимка дня NASA
   private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        downloadApi();

    }
    private static void downloadApi() throws IOException {
        String apiNasa = "https://api.nasa.gov/planetary/apod?";
        String apiKey = "api_key=DEMO_KEY";
        builder.append(apiNasa);
        builder.append(apiKey);
        String resultUrl = builder.toString();
        String page = downloadWebPage(resultUrl);
        String str = infoExplanation(page);
        System.out.println(str);
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