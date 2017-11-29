package com.example.android.myrecord;

import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.myrecord.NetworkModels.AssignmentsRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment_StartPro extends AppCompatActivity {

    private TextView semester,teacher,subject,duration,heading,instructions;
    private  long difference;
    int days,hours,min,sec;
    Button start;
    AssignmentsRecord assignmentsRecord;

   private Date parseDate(String date) {
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       Date dateObject = null;
       try {
           dateObject = sdf.parse(date);
       } catch (ParseException e) {
           e.printStackTrace();
       } catch (java.text.ParseException e) {
           e.printStackTrace();
       }
       return dateObject;
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_assignment__startpro);
        semester =(TextView) findViewById(R.id.semesterASP);
        teacher = (TextView) findViewById(R.id.teacherASP);
        subject = (TextView) findViewById(R.id.subjectASP);
        duration =(TextView) findViewById(R.id.durationASP);
        heading = (TextView) findViewById(R.id.headingASP);
        instructions = (TextView) findViewById(R.id.instructionsASP);
        start = (Button) findViewById(R.id.startASP);
        assignmentsRecord = (AssignmentsRecord)getIntent().getSerializableExtra("assignRecord");
        subject.append(assignmentsRecord.getSubject());
        teacher.append(assignmentsRecord.getTeacherName());
        semester.append(assignmentsRecord.getSemester());
//        Log.i("value of start time",assignmentsRecord.getStartTime());
        Date date1 = parseDate(assignmentsRecord.getStartTime());
        Date date2 = parseDate(assignmentsRecord.getEndTime());
//        Log.i("value of object",date1.getTime()+"");
        difference = date2.getTime() - date1.getTime();
        days = (int) (difference / (1000*60*60*24));
        hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
        min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
        sec = (int) ((difference - (1000*60*60*24*days) - (1000*60*60*hours) -(1000*60*min))/(1000));
        duration.append(hours+" hours "+min+" min");
        heading.setText(assignmentsRecord.getHeading());
        instructions.setText(assignmentsRecord.getInstructions());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Assignment_Started.class);
                intent.putExtra("AssignmentID",assignmentsRecord.getAssid());
                intent.putExtra("durationSeconds",difference);
                startActivity(intent);
                finish();
            }
        });

    }
}
