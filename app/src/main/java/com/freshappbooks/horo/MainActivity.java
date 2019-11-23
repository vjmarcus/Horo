package com.freshappbooks.horo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";
    private static String mailRu = "https://horo.mail.ru/prediction/sagittarius/today/";
    Button btn_ok, btn_match;
    DatePicker picker;
    int month;
    int day;
    private String sign;
    public static String horo;
    private String resultTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ok = findViewById(R.id.btn_ok);
        picker = findViewById(R.id.datePicker);
//        mailRu = String.format(mailRu, sign);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month = picker.getMonth();
                day = picker.getDayOfMonth();
                sign = getZodiacSign(day, month).toLowerCase();
                mailRu = "https://horo.mail.ru/prediction/" + sign + "/today/";
                Log.d(TAG, "onClick: " + sign);
                DownloadTask task = new DownloadTask();
                try {
                    resultTask = task.execute(mailRu).get();
                    Log.d(TAG, "onClick: result = " + resultTask);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                String river = "<div class=\"article__item article__item_alignment_left article__item_html\"><p>Спокойный день. Его нельзя назвать совершенно лишенным трудностей, но в целом ситуация складывается неплохо, и вы понимаете, как нужно действовать, чтобы достичь успеха. Могут появиться новые планы, в осуществлении которых помогут проверенные союзники, старые знакомые.</p>\n" +
                        "\n" +
                        "<p>Вероятны незапланированные поездки. Собираться в дорогу придется быстро, но вы ничего не забудете и не упустите. Вдали от дома вероятны удачные покупки. Вы обращаете внимание на необычные вещи, которые долго будут радовать. Также не исключены интересные знакомства.</p>";
                String parse = "<!DOCTYPE html><html><head><link rel=\"stylesheet\" href=\"/-/01d7ee44/bem/horo/touch/touch.bundles/common/_common.css\"/><meta name=\"viewport\" content=\"width=device-width, user-scalable=no, maximum-scale=1.0, initial-scale=1.0, minimum-scale=1.0\"/><meta charset=\"UTF-8\"/><meta name=\"theme-color\" content=\"#005FF9\"/><link rel=\"amphtml\" href=\"https://horo.mail.ru/amp/prediction/sagittarius/today/\"/><link rel=\"apple-touch-startup-image\" href=\"/img/mobile/touch/apple-touch-icon.png\"/><link rel=\"apple-touch-icon-precomposed\" href=\"/img/mobile/touch/apple-touch-icon.png\"/><link rel=\"apple-touch-icon\" href=\"/img/mobile/touch/apple-touch-icon.png\"/><title>Гороскоп на сегодня - для знака зодиака Стрелец - Гороскопы Mail.ru</title><meta name=\"description\" content=\"Бесплатный гороскоп на сегодня - для знака Зодиака Стрелец - для планирования дел, любовных, дружеских и семейных отношений\"/><meta name=\"keywords\" content=\"стрелец, гороскоп, на сегодня, все знаки\"/><meta property=\"og:site_name\" content=\"Гороскопы Mail.ru\"/><meta property=\"og:title\" content=\"Гороскоп для Стрельца на сегодня\"/><meta name=\"mrc__share_title\" content=\"Гороскоп для Стрельца на сегодня\"/><meta property=\"og:description\" content=\"Спокойный день. Его нельзя назвать совершенно лишенным трудностей, но в целом ситуация складывается неплохо, и вы понимаете, как нужно действовать...\"/><meta name=\"mrc__share_description\" content=\"Спокойный день. Его нельзя назвать совершенно лишенным трудностей, но в целом ситуация складывается неплохо, и вы понимаете, как нужно действовать...\"/><link rel=\"image_src\" href=\"https://horo.mail.ru/img/horo/shares/sign/sagittarius.png\"/><meta property=\"og:image\" content=\"https://horo.mail.ru/img/horo/shares/sign/sagittarius.png\"/><link rel=\"manifest\" href=\"/manifest.json\"/><link rel=\"canonical\" href=\"https://horo.mail.ru/prediction/sagittarius/today/\"/><meta property=\"browsing_history_id\" content=\"h9\"/><meta name=\"csrf-token\" content=\"AG_TCPNxVQgAXh5rnRdk2F3S\" /><script src=\"/-/b9450a22/js/cmpld/horo/touch/head.js\"></script><!-- /rb/38218?_SITEZONE=10 --><script type=\"text/javascript\">window._logJsErrors = true;</script><!-- //rb/38218?_SITEZONE=10 --></head><body class=\"js-module\" data-module=\"MainMenu,PushNotificationsModel\" data-view=\"PushNotificationsView\" data-mp=\"LazyItem as LazyBody\"><div class=\"popup-shown__hide\"><div class=\"wrapper wrapper_inner\">\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\" js-module\" data-module=\"SlotModel\" data-view=\"SlotView.36359\" data-id=\"36359\" data-siteid=\"104\"></div>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div></div><div class=\"overlay js-popup\"><div class=\"overlay__wrapper js-popup_cont\"></div></div><div id=\"layout\"><div class=\"portal-headline popup-shown__hide\"><div class=\"portal-headline__wrap\"><div class=\"portal-headline__toolbar portal-headline__toolbar_left\"><div class=\"portal-headline__button js-menu_toggle\" name=\"clb36323428\"><div class=\"portal-headline__button-icon portal-headline__button-icon_menu\"></div></div></div><div class=\"portal-headline__toolbar portal-headline__toolbar_logotype\"><a class=\"portal-headline__logotype\" href=\"//lady.mail.ru\"><img class=\"portal-headline__logotype-image\" src=\"/img/logo/lady/lady_touch.svg\"/></a></div></div></div><div class=\"wrapper wrapper_content wrapper_page js-layout popup-shown__hide\"><script>\t\t\t\tvar aProjectList =     [      {\"name\": \"Mail.Ru\", \"url\": \"https://r.mail.ru/n157643803?sz=10&rnd=194586325\", \"alias\": \"Mail.Ru\"}         ,{\"name\": \"Почта\", \"url\": \"https://r.mail.ru/n156861861?sz=10&rnd=194586325\"}         ,{\"name\": \"Мой Мир\", \"url\": \"https://";


                Pattern pattern = Pattern.compile("<div class=\"article__item article__item_alignment_left article__item_html\"><p>(.*?)</p>");
                Matcher matcher = pattern.matcher(river);
                if (matcher.find()){
                    horo = matcher.group(1);
                    Log.d(TAG, "onCreate: Mather = " + horo);
                } else {
                    Log.d(TAG, "onClick: Mather" + "Нету");
                }
            }
        });


    }

    private static class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = reader.readLine();
                while (line != null) {
                    result.append(line);
                    line = reader.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result.toString();
        }
    }


    private String getZodiacSign(int day, int month) {
        String sign = null;
        if (month == 0) {
            if (day < 20)
                sign = "Capricorn";
            else
                sign = "Aquarius";
        } else if (month == 1) {
            if (day < 19)
                sign = "Aquarius";
            else
                sign = "Pisces";
        } else if(month == 2) {
            if (day < 21)
                sign = "Pisces";
            else
                sign = "Aries";
        } else if (month == 3) {
            if (day < 20)
                sign = "Aries";
            else
                sign = "Taurus";
        } else if (month == 4) {
            if (day < 21)
                sign = "Taurus";
            else
                sign = "Gemini";
        } else if( month == 5) {
            if (day < 21)
                sign = "Gemini";
            else
                sign = "Cancer";
        } else if (month == 6) {
            if (day < 23)
                sign = "Cancer";
            else
                sign = "Leo";
        } else if( month == 7) {
            if (day < 23)
                sign = "Leo";
            else
                sign = "Virgo";
        } else if (month == 8) {
            if (day < 23)
                sign = "Virgo";
            else
                sign = "Libra";
        } else if (month == 9) {
            if (day < 23)
                sign = "Libra";
            else
                sign = "Scorpio";
        } else if (month == 10) {
            if (day < 22)
                sign = "scorpio";
            else
                sign = "Sagittarius";
        } else if (month == 11) {
            if (day < 22)
                sign = "Sagittarius";
            else
                sign ="Capricorn";
        }
        return sign;
    }
}

