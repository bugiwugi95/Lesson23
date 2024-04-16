import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Task8 {
    //8.Реализуйте метод, который принимает два LocalDate, и сохраняет все снимки дня NASA в указанный промежуток


    public static void main(String[] args) throws IOException {
        LocalDate dateStart = LocalDate.of(2000, 1, 1);
        LocalDate dateEnd = LocalDate.of(2000, 1, 5);
        getDate(dateStart, dateEnd);


    }

    static void getDate(LocalDate start, LocalDate end) throws IOException {
        int cur = 0;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            StringBuilder builder = createStringUrlApiNasa(date);
            String strPage = downloadWebPage(builder.toString());
            cur = getImage(cur, date, strPage);
        }


    }

    private static int getImage(int cur, LocalDate date, String strPage) throws IOException {
        int urlStart = strPage.lastIndexOf("url");
        int urlEnd = strPage.lastIndexOf("}");
        String urlImage = strPage.substring(urlStart + 6, urlEnd - 1);
        cur++;
        try (InputStream in = new URL(urlImage).openStream()) {
            Files.copy(in, Paths.get("nasa.jpg" + cur));
        }

        System.out.println("Файлы сохранены!" + cur);
        System.out.println(date);
        return cur;
    }

    private static StringBuilder createStringUrlApiNasa(LocalDate date) {
        StringBuilder builder = new StringBuilder();
        String apiNasa = "https://api.nasa.gov/planetary/apod?";
        String apiKey = "api_key=7OaqIpDHdb3PHOsbhFIYVQdcgrC9UppAvenyrRuC";
        String startDate = ("&start_date=" + date);
        String endDate = ("&end_date=" + date);
        builder.append(apiNasa);
        builder.append(apiKey);
        builder.append(startDate);
        builder.append(endDate);
        return builder;
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