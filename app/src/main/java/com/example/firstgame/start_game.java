package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class start_game extends AppCompatActivity {

    int op1, op2, sum, points, nombreOfQuestion , sumOthers;
    TextView tvOperat, tvTimer, tvPoints, tvResult;
    CountDownTimer countDownTimer;
    long millisUntilFinished;
    Button btn0, btn1, btn2, btn3;
    Random random;
    int[] btnids ;
    int correctAnsewer;
    ArrayList<Integer> incorrectAnswer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        op1 = 0;
        op2 = 0;
        sum = 0;
        tvOperat = findViewById(R.id.tvOperat);
        tvTimer = findViewById(R.id.tvTimer);
        tvResult = findViewById(R.id.tvResult);
        tvPoints = findViewById(R.id.tvPoints);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        millisUntilFinished = 60000;
        points = 0;
        nombreOfQuestion = 0;
        correctAnsewer = 0;
        incorrectAnswer = new ArrayList<>();
        random = new Random();
        btnids = new int[]{R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3};
        startGame();


    }

    private void startGame() {

        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
        tvPoints.setText("" + points + "/" + nombreOfQuestion);
        generateQueshion();
        
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");


            }

            @Override
            public void onFinish() {
                btn0.setClickable(false);
                btn1.setClickable(false);
                btn2.setClickable(false);
                btn3.setClickable(false);

            }
        }.start();

    }

    private void generateQueshion() {
        nombreOfQuestion++;
        op1 = random.nextInt(20);
        op2 = random.nextInt(20);
        sum = op1 + op2 ;
        tvOperat.setText(op1 + "+ " + op2 + " = ");
        correctAnsewer = random.nextInt(4);
        ((Button)findViewById(btnids[correctAnsewer])).setText(""+sum);
        while (true){
            if(incorrectAnswer.size() > 3)
                break;
            op1 = random.nextInt(20);
            op2 = random.nextInt(20);
            sumOthers = op1 + op2 ;
            if(sumOthers == sum )
                continue;
            incorrectAnswer.add(sumOthers);
        }
        for (int i= 0; i < 3 ; i++){
            if(i == correctAnsewer)
                continue;
            ((Button)findViewById(btnids[i])).setText(""+incorrectAnswer.get(i));
        }
        incorrectAnswer.clear();
    }

    public void chooseAnsewer(View view) {
        int answer =Integer.parseInt(((Button) view).getText().toString());
        if(answer == sum){
            points++;
            tvPoints.setText(points + " / " + nombreOfQuestion);
            tvResult.setText("Correct !! nice ");
        }else
        {
            tvResult.setText("incCorrect !! tfou ");

        }
        tvPoints.setText(points + " / " + nombreOfQuestion);

        generateQueshion();
    }
}
