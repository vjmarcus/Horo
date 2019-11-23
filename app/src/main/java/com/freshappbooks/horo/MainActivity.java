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
import java.util.Formatter;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";
    private static String mailRu = "https://horo.mail.ru/prediction/sagittarius/today/";
    Button button;
    DatePicker picker;
    int month;
    int day;
    private String sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_ok);
        picker = findViewById(R.id.datePicker);
//        mailRu = String.format(mailRu, sign);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month = picker.getMonth();
                day = picker.getDayOfMonth();
                sign = getZodiacSign(day, month).toLowerCase();
                mailRu = "https://horo.mail.ru/prediction/" + sign + "/today/";
                Log.d(TAG, "onClick: " + sign);
                DownloadTask task = new DownloadTask();
                try {
                    String result = task.execute(mailRu).get();
                    Log.d(TAG, "onClick: + result " + result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

