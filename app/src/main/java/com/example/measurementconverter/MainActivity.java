package com.example.measurementconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener {

    private TextView conversionOneTextView;
    private TextView conversionTwoTextView;
    private EditText enterNumberEditText;
    private TextView displayTextView;
    private Spinner conversionSpinner;
    public int spinnerPosition = 0;


    private SharedPreferences savedValues;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversionSpinner = (Spinner) findViewById(R.id.conversionSpinner);
        conversionOneTextView = (TextView) findViewById(R.id.conversionOneTextView);
        conversionTwoTextView = (TextView) findViewById(R.id.conversionTwoTextView);
        enterNumberEditText = (EditText) findViewById(R.id.enterNumberEditText);
        displayTextView = (TextView) findViewById(R.id.diplayNumberTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversionSpinner, R.layout.support_simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);

        conversionSpinner.setOnItemSelectedListener(this);
        enterNumberEditText.setOnEditorActionListener(this);

        //savedValues = getPreferences("savedValues", MODE_PRIVATE);

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(position == 0){
            conversionOneTextView.setText("Miles");
            conversionTwoTextView.setText("Kilometer");
            spinnerPosition = 0;
            displayTextView.setText("0.00");
            enterNumberEditText.setText("");

        }else if(position == 1){
            conversionOneTextView.setText("Kilometer");
            conversionTwoTextView.setText("Miles");
            spinnerPosition = 1;
            displayTextView.setText("0.00");
            enterNumberEditText.setText("");
        }else if(position == 2){
            conversionOneTextView.setText("Inches");
            conversionTwoTextView.setText("Centimeter");
            spinnerPosition = 2;
            displayTextView.setText("0.00");
            enterNumberEditText.setText("");
        }else if(position == 3){
            conversionOneTextView.setText("Centimeter");
            conversionTwoTextView.setText("Inches");
            spinnerPosition = 3;
            displayTextView.setText("0.00");
            enterNumberEditText.setText("5.0");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private double milesToKilometer (double miles){
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(miles*1.6093));
    }

    private double kilometerToMiles (double kilometer){
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(kilometer*0.6214));
    }

    private double inchesToCentimeter (double inches){
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(inches*2.54));
    }

    private double centimeterToInches (double centimeter){
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.parseDouble(f.format(centimeter*0.3937));
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
            if (spinnerPosition == 0){
                double miles = Double.parseDouble(enterNumberEditText.getText().toString());
                String kilometer = String.valueOf(milesToKilometer(miles));
                displayTextView.setText(kilometer);
            } else if(spinnerPosition ==1) {
                double kilometer = Double.parseDouble(enterNumberEditText.getText().toString());
                String miles = String.valueOf(kilometerToMiles(kilometer));
                displayTextView.setText(miles);
            } else if(spinnerPosition == 2){
                double inches = Double.parseDouble(enterNumberEditText.getText().toString());
                String centimeter = String.valueOf(inchesToCentimeter(inches));
                displayTextView.setText(centimeter);
            }else if(spinnerPosition == 3){
                double centimeter = Double.parseDouble(enterNumberEditText.getText().toString());
                String inches = String.valueOf(centimeterToInches(centimeter));
                displayTextView.setText(inches);
            }
        }
        return false;
    }

}