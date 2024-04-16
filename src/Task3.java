import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Task3 {
    //3.Возьмите любую программу, которую вы писали до этого.. какая сердцу ближе.
    // Отрефакторите ее (улучшите читабельность кода) -путем разбиения крупных частей на методы поменьше.
    // Стало лучше? (надеюсь, да)
    public static void main(String[] args) throws IOException {
        getDate();

    }

    private static void getDate() throws IOException {
        for (int month = 1; month <= 12; month++) {
            for (int day = 0; day <= 1; day++) {
                String strMonth = month < 10 ? "0" + month : String.valueOf(month);
                String strDay = day == 1 ? "0" + day : String.valueOf(day);
                getCurrency(strMonth, strDay);

            }


        }
    }

    private static void getCurrency(String strMonth, String strDay) throws IOException {
        String page = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=01/" + strMonth + "/2014&date_req2=" + strDay + "/" + strMonth + "/2014&VAL_NM_RQ=R01235");
        int starIndex = page.lastIndexOf("<Value>");
        if (starIndex != -1) {
            int endIndex = page.lastIndexOf("</Value>");
            String str = page.substring(starIndex + 7, endIndex);
            System.out.println(strDay + "/" + strMonth + "/" + "2014" + ": " + str);
        }
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