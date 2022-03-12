package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    TCPClient client = new TCPClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.input);
        String matnr = editText.getText().toString();

        client.setMatnr(matnr);
        Thread t = new Thread(client);
        t.start();
        try {
            t.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView response = findViewById(R.id.textResponse);
        response.setText(client.getResponse());
    }
}