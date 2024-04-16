import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Task5 {
    //5.Реализуйте метод, который возвращает случайную цитату Уолтера Уайта.
    // https://api.breakingbadquotes.xyz/v1/quotes


    public static void main(String[] args) throws IOException {
        String httpsApi = "https://api.breakingbadquotes.xyz/v1/quotes";
        String result = getQuotes(httpsApi);
        System.out.println(result);

    }

    private static String getQuotes(String httpsApi) throws IOException {
        String page = downloadWebPage(httpsApi);
        int starIndex = page.indexOf("quote");
        int starEnd = page.indexOf(",");
        return page.substring(starIndex + 8, starEnd - 1);

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