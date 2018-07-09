package com.example.admin.daily4;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EMICalculator extends MainActivity implements SeekBar.OnSeekBarChangeListener {


    private TextView myPrincipalLoanAmount;
    private TextView myRateOfInterest;
    private TextView myLoanDuration;
    private TextView emiOutput;
    private SeekBar myPrincipalLoanAmountSeekBar;
    private SeekBar myRateOfInterestSeekBar;
    private SeekBar myLoanDurationSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emicalculator);

        myPrincipalLoanAmount = (TextView)findViewById(R.id.myPrincipalLoanAmount);
        myRateOfInterest = (TextView)findViewById(R.id.myRateOfInterest);
        myLoanDuration = (TextView)findViewById(R.id.myLoanDuration);
        emiOutput = (TextView)findViewById(R.id.emi_output);

        myPrincipalLoanAmountSeekBar = (SeekBar)findViewById(R.id.principalLoanSeekbar);
        myRateOfInterestSeekBar = (SeekBar)findViewById(R.id.rateOfInterestSeekbar);
        myLoanDurationSeekBar = (SeekBar)findViewById(R.id.loanDurationSeekbar);


        myPrincipalLoanAmountSeekBar.setOnSeekBarChangeListener(this);
        myRateOfInterestSeekBar.setOnSeekBarChangeListener(this);
        myLoanDurationSeekBar.setOnSeekBarChangeListener(this);
       super.NavigationSetup(this);
    }


    public void CalculateEmi(View view){



        Double EMI;
        double P=Double.valueOf(myPrincipalLoanAmount.getText().toString());
        double R=Double.valueOf(myRateOfInterest.getText().toString());
        int N=Integer.valueOf(myLoanDuration.getText().toString());

        EMI=(P*R*Math.pow(1+R,N))/Math.pow(1+R,N-1);

        emiOutput.setText(EMI.toString());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        TextView textView=null;

        switch(seekBar.getId()){
            case R.id.principalLoanSeekbar:
                textView=myPrincipalLoanAmount;
                break;

            case R.id.loanDurationSeekbar:
                textView=myLoanDuration;
                break;
            case R.id.rateOfInterestSeekbar:
                textView=myRateOfInterest;
                break;
        }

        Integer progress=i;

        textView.setText(progress.toString());

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
