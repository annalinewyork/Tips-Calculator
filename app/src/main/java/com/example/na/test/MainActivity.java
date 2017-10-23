package com.example.na.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText mInputTotal;
    TextView tipsShow, totalShow;
    Button percent15, percent18, percent20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputTotal = (EditText) findViewById(R.id.bill_amount);
        tipsShow = (TextView) findViewById(R.id.tips);
        totalShow = (TextView) findViewById(R.id.total);
        percent15 = (Button) findViewById(R.id.button_15);
        percent18 = (Button)findViewById(R.id.button_18);
        percent20 = (Button)findViewById(R.id.button_20);

        //make input center?
        mInputTotal.setGravity(Gravity.CENTER_HORIZONTAL);
        mInputTotal.addTextChangedListener(new TextWatcher() {
            int count_decimal_points_ = 0;
            int selection_start_;
            StringBuffer str_buf_;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().contains(".")) {
                    count_decimal_points_ = 1;
                } else {
                    count_decimal_points_ = 0;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                if(!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
//                {
//                    String userInput= ""+s.toString().replaceAll("[^\\d]", "");
//                    StringBuilder cashAmountBuilder = new StringBuilder(userInput);
//
//                    while (cashAmountBuilder.length() > 3 && cashAmountBuilder.charAt(0) == '0') {
//                        cashAmountBuilder.deleteCharAt(0);
//                    }
//                    while (cashAmountBuilder.length() < 3) {
//                        cashAmountBuilder.insert(0, '0');
//                    }
//                    cashAmountBuilder.insert(cashAmountBuilder.length()-2, '.');
//                    cashAmountBuilder.insert(0, '$');
//
//                    mInputTotal.setText(cashAmountBuilder.toString());
//                    // keeps the cursor always to the right
//                    Selection.setSelection(mInputTotal.getText(), cashAmountBuilder.toString().length());
//
//                }


                str_buf_ = new StringBuffer(s.toString().trim());
                if (before == 0 && s.length() == 1 && s.charAt(start) == '.') {
                    mInputTotal.setText("");
                } else if (before == 0 && count_decimal_points_ == 1) {
                    if (s.charAt(start) == '.' || (start - str_buf_.indexOf(".") > 2)) {
                        str_buf_.deleteCharAt(start);
                        mInputTotal.setText(str_buf_);
                    } else {
                        selection_start_ = str_buf_.length();
                    }
                } else {
                    selection_start_ = str_buf_.length();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                mInputTotal.setSelection(selection_start_);
              //  Log.d("input total",mInputTotal.toString());
              //  Log.d("string s",s.toString());

                if (s != null) {
                    try {

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        percent15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get user input
                String s = mInputTotal.getText().toString();
                //convert input number to double
                double input = Double.parseDouble(s);
                //calculate the tips
                double result15 = input * 0.15;
                //format tip as 2 decimals
                DecimalFormat df = new DecimalFormat("#.00");
                String resullt_15 = df.format(result15);
                //show tips in textview
                tipsShow.setText("$"+" "+resullt_15);
                //calculate bill total in double format
                double total = input + result15;
                //convert double to string
                String result_total = df.format(total);
                //show total in textview
                totalShow.setText("$"+" "+result_total);
            }
        });

        percent18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mInputTotal.getText().toString();
                double input = Double.parseDouble(s);
                double result18 = input * 0.18;
                DecimalFormat df = new DecimalFormat("#.00");
                String resullt_18 = df.format(result18);
                tipsShow.setText("$"+" "+resullt_18);
                double total = input + result18;
                String result_total = df.format(total);
                totalShow.setText("$"+" "+result_total);
            }
        });

        percent20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mInputTotal.getText().toString();
                double input = Double.parseDouble(s);
                double result20 = input * 0.2;
                DecimalFormat df = new DecimalFormat("#.00");
                String resullt_20 = df.format(result20);
                tipsShow.setText("$"+" "+resullt_20);
                double total = input + result20;
                String result_total = df.format(total);
                totalShow.setText("$"+" "+result_total);
            }
        });
    }
}
