package com.example.android.myrecord;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PracticeStart extends AppCompatActivity {
    TextView tv, tv2, tv3, tv4;
    RadioGroup rg;
    Button btn;
    Toast toast;
    MediaPlayer mp;
    ColorStateList colorStateList;
    ProgressBar pb;
    int scr, nscr, questions = 0;
    private SQLiteDatabase db;
    private static final String x = "SELECT * FROM questions";
    private Cursor c;
    CountDownTimer timer;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_start);
        tv = (TextView) findViewById(R.id.textView1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        btn = (Button) findViewById(R.id.button1);
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setProgress(100);
        Bundle data = getIntent().getExtras();
        n = data.getString("Name");

        openDatabase();
        c = db.rawQuery(x, null);
        c.moveToFirst();
        colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{

                        Color.WHITE
                        , Color.rgb(242, 81, 112),
                }
        );
        getQuestion();
        tv2.setText("Score: 0");
        startCount();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = rg.getCheckedRadioButtonId();
                if (nscr == 1) {
                    switch (res) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            break;
                        default:
                            toast = Toast.makeText(getApplicationContext(), "Select atleast one option", Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                    }
                    if (res == Integer.parseInt(c.getString(6))) {
                        toast = Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                        scr += nscr;
                        tv2.setText("Score: " + scr);
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        mp.start();
                    } else {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                        mp.start();
                        toast = Toast.makeText(getApplicationContext(), "Incorrect Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                if (questions < 11) {
                    //c.moveToNext();
                    rg.removeAllViews();
                    getQuestion();
                    timer.cancel();
                    pb.setProgress(100);
                    startCount();
                } else {
                    timer.cancel();
                    Intent i = new Intent(getApplicationContext(), FinalActivity.class);
                    i.putExtra("score", scr);
                    i.putExtra("Name", n);
                    startActivity(i);
                    finish();
                }

            }
        });

    }

    protected void openDatabase() {
        db = openOrCreateDatabase("InterviewDB.db", Context.MODE_PRIVATE, null);
    }

    protected void getQuestion() {
        c.moveToNext();
        Integer.parseInt(c.getString(0));
        tv.setText(c.getString(1));
        rg.removeAllViews();
        for (int i = 0; i < 4; i++) {
            AppCompatRadioButton radioButton = new AppCompatRadioButton(this);
            radioButton.setId(i);
            radioButton.setText(c.getString(i + 2));
            radioButton.setSupportButtonTintList(colorStateList);
            rg.addView(radioButton);
        }
        rg.clearCheck();
        questions++;
    }

    protected void startCount() {
        tv4.setText("Seconds Remaining");
        btn.setText("SUBMIT");
        timer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv3.setText("" + millisUntilFinished / 1000);
                nscr = 1;
                pb.setProgress(pb.getProgress() - 5);//100/20 reason for 5
            }

            @Override
            public void onFinish() {
                nscr = 0;
                btn.setText("NEXT");
                pb.setProgress(0);
                tv3.setText("");
                tv4.setText("Sorry...Time Up!");

            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure, you want to leave Practice Test")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),SubjectSelection.class);
                        intent.putExtra("Name",n);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();

    }


}
