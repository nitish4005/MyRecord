package com.example.android.myrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    TextView tv3,tv1,tv2;
    Button btn;
    int s;
    String n;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        tv3=(TextView)findViewById(R.id.textView3);
        tv1=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        btn=(Button)findViewById(R.id.button1);
        Bundle data=getIntent().getExtras();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s=data.getInt("score");
        n=data.getString("Name");

           if(s<5){
               tv1.setText("Sorry! " + n);
               tv2.setText("You need more prepration.");
           }else {
               tv1.setText(tv1.getText().toString() + " " + n);
           }
        tv3.setText("Score: "+s);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(), HomeScreen.class);
                i.putExtra("Name", n);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),SubjectSelection.class);
        intent.putExtra("Name",n);
        startActivity(intent);
        finish();
    }
}
