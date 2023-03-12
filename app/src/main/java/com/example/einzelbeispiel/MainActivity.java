package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

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

    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        TextView textViewResponse = findViewById(R.id.textResponse);
        EditText editTextMatnr = (EditText) findViewById(R.id.input);
        String matnr = editTextMatnr.getText().toString();

        client.setMatnr(matnr);
        Thread t = new Thread(client);
        t.start();
        try {
            t.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textViewResponse.setText(client.getResponse());
    }

    public void calculate(View view) {
        EditText editTextMatnr = findViewById(R.id.input);
        String matnr = editTextMatnr.getText().toString();
        TextView textViewCalc = findViewById(R.id.textViewCalc);

        String s = "";

        for (int i = 0; i < matnr.length(); i++) {
            int z = Integer.parseInt(String.valueOf(matnr.charAt(i)));
            if (isPrime(z)) s += z + " ";
        }
        textViewCalc.setText(s);
    }

    static boolean isPrime(int n) {
        int teilerAnz = 0;
        for (int teiler = 1; teiler <= n; teiler++) {
            if (n % teiler == 0) teilerAnz++;
        }

        if (teilerAnz == 2) return true;
        return false;
    }
}