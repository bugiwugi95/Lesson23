import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Task4 {
    //4.Отрефакторите(улучшите читабельность) кода вашей реализации анализа курса валют.
    private static final StringBuilder builder = new StringBuilder();
    public static void main(String[] args) throws IOException {
        System.out.println("Введите число месяца:");
        String qr1 = inputUser();
        System.out.println("Введите месяц:");
        String qr2 = inputUser();
        System.out.println("Введите год:");
        String qr3 = inputUser();
        String str = getString(qr1, qr2, qr3);
         getCurrency(str);

    }

    private static String inputUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void getCurrency(String str) throws IOException {
        String page2 = downloadWebPage(str);
        int starIndex = page2.lastIndexOf("<Value>");
        int endIndex = page2.lastIndexOf("</Value>");
        if (starIndex != -1) {
            String strPage = page2.substring(starIndex + 7, endIndex);
            int starName = page2.lastIndexOf("<CharCode>");
            int endName = page2.lastIndexOf("</CharCode>");
            String strName = page2.substring(starName + 10, endName);
            System.out.println(strPage);
            System.out.println(strName);
        }



    }

    private static String getString(String qr1, String qr2, String qr3) {
        String http = "http://www.cbr.ru/scripts/XML_daily.asp?";
        String req = "date_req=";
        builder.append(http);
        builder.append(req);
        builder.append(qr1);
        builder.append("/");
        builder.append(qr2);
        builder.append("/");
        builder.append(qr3);
        return builder.toString();
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