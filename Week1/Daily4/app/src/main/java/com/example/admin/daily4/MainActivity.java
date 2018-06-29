package com.example.admin.daily4;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String CALCINPUT_KEY="CALCINPUT";
    private static final String CALCOUTPUT_KEY="CALCOUTPUT";
    private TextView calcInput;
    private TextView calcOutput;
    private String operator;
    private String storedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        calcInput = findViewById(R.id.calcInput);
        calcOutput = findViewById(R.id.calcOutput);
        operator="";
        storedValue="0";
        GridLayout grid=findViewById(R.id.grid);

        if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
            for (int i = 0; i < grid.getChildCount(); i++) {
                Button button=new Button(this);

                    button = (Button) grid.getChildAt(i);
                    if (!(button.getVisibility() == View.VISIBLE)) {
                        button.setVisibility(View.VISIBLE);
                    }

            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CALCINPUT_KEY, calcInput.getText().toString());
        outState.putString(CALCOUTPUT_KEY, calcOutput.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        calcInput = findViewById(R.id.calcInput);
        calcOutput = findViewById(R.id.calcOutput);
        String calcInputData=savedInstanceState.get(CALCINPUT_KEY).toString();
        String calcOutputData=savedInstanceState.get(CALCOUTPUT_KEY).toString();
        calcInput.setText(calcInputData);
        calcOutput.setText(calcOutputData);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void Number_Click(View view) {
        calcInput=findViewById(R.id.calcInput);
        Button button=(Button)view;
        String newInput=button.getText().toString();
        String oldInput=calcInput.getText().toString();
        calcInput.setText(oldInput+newInput);



        if(operator!=""){

          /*  int newInputInt=Integer.getInteger(newInput);
            int storedValueInt=Integer.getInteger(storedValue);*/
            double newInputDouble=Double.valueOf(newInput);
            double storedValueDouble=Double.valueOf(storedValue);
            performOperation(storedValueDouble,newInputDouble);
        }
    }

    public void Operator_Click(View view) {
        if(operator!="")
            return;
        calcInput=findViewById(R.id.calcInput);
        Button button=(Button)view;
        String newInput=button.getText().toString();
        String oldInput=calcInput.getText().toString();
        operator = newInput;
        storedValue = oldInput;

        //Check for operator which only requires one number
        switch(operator){
            case "x^2":
            case"sin":
            case "cos":
            case "tan":
            case "C":
                performOperation(Double.valueOf(storedValue),0.0);
                break;
            default:calcInput.setText(storedValue+operator);
        }
    }

    private void performOperation(Double num1,Double num2){
        Double result=0.0;
        switch(operator){
            case "+":result=num1+num2;
                break;
            case "-":result=num1-num2;
                break;
            case "*":result=num1*num2;
                break;
            case "/":result=num1/num2;
                break;
            case "x^2":result=num1*num1;
                break;
            case "sin":result=Double.valueOf(Math.sin(num1.doubleValue()));
                break;
            case "cos":result=Math.cos(num1.doubleValue());
                break;
            case "tan":result=Math.tan(num1.doubleValue());
                break;
            case "C":
                calcOutput.setText("");
                break;
            default:
                result=num2;
        }
        String resultStr=result.toString();
        if(resultStr.endsWith(".0")){
            resultStr.replaceAll(".0","");
        }
            if(operator!="C") {
                calcOutput.setText(resultStr);
            }
        storedValue=resultStr;
        calcInput.setText("");



        operator="";
    }
}
