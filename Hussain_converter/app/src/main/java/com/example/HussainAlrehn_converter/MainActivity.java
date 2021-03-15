package com.example.HussainAlrehn_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private TextView textviewMutli;
    private TextView resultValue;
    private TextView convert_text;
    private RadioButton milesToKiloRadio;
    private RadioButton KiloToMilesRadio;
    private Button convert;
    private EditText inputfield;
    private TextView result;
    boolean Radiobutton = true;

    private DecimalFormat format = new DecimalFormat("#.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewMutli = findViewById(R.id.multiLine);
        textviewMutli.setMovementMethod(new ScrollingMovementMethod());
        resultValue = findViewById(R.id.resultValue);
        convert_text = findViewById(R.id.convert_text);

        milesToKiloRadio = findViewById(R.id.MilesToKilRadio);
        KiloToMilesRadio = findViewById(R.id.KilotomileRadio);
        convert = findViewById(R.id.convert);
        inputfield = findViewById(R.id.inputfield);
        result = findViewById(R.id.result);

    }

    public void radioClicked(View v) {
        if (v.getId() == R.id.KilotomileRadio) {

            Radiobutton = true;
            convert_text.setText("Kilomteres value:");
            resultValue.setText("Miles Value:");

        }
        if (v.getId() == R.id.MilesToKilRadio) {
            Radiobutton = false;
            convert_text.setText("Miles value:");
            resultValue.setText("Kilometers Value:");
        }


    }

    public void convert(View v) {
        String input = inputfield.getText().toString();

        String output = converts(input);
        result.setText(output);
        addHistory(input, output);

    }

    public void addHistory(String input, String output) {
        StringBuilder sb = new StringBuilder();
        format.format(Double.parseDouble(input));
        format.format(Double.parseDouble(output));
        if (Radiobutton) {
            sb.append(input);
            sb.append(" Km ");
            sb.append("==> ");
            sb.append(output);
            sb.append(" Mi ");
            sb.append("\n");

        } else {

            sb.append(input);
            sb.append(" Mi ");
            sb.append("==> ");
            sb.append(output);
            sb.append(" Km ");
            sb.append("\n");
        }
        textviewMutli.append(sb);

    }

    public void clear (View v){
        textviewMutli.setText("");

    }
    private String converts (String input){

        if (Radiobutton) {
            return convertKiloToMile(input);
        } else {
            return convertMilesToKilo(input);
        }

    }
    private String convertMilesToKilo (String input){
        return format.format(Double.parseDouble(input) * 1.60934);
    }
    private String convertKiloToMile (String input){
        return format.format(Double.parseDouble(input) * 0.621371);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("History",textviewMutli.getText().toString());
        outState.putString("Output",resultValue.getText().toString());
        outState.putString("input",inputfield.getText().toString());
        outState.putString("result",result.getText().toString());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        textviewMutli.setText(savedInstanceState.getString("History"));
        result.setText(savedInstanceState.getString("Result"));
        resultValue.setText(savedInstanceState.getString("resultValue"));
    }


}

