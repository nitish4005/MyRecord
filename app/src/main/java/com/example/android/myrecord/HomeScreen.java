package com.example.android.myrecord;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
   private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;
    public static  String token=null;
    public static  String name = "";
    public static  String id = "";
    public static  String  department= "";
    SharedPreferences sharedpreferences;
    private String semester="";
    private TextView userName,nameHome;
    private TextView enrollment;
    private TextView sem;
    private Button upload_edit_button,assign,prep;
    private ImageView profilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerlayout);
        sharedpreferences=getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        name=sharedpreferences.getString("Name",null);
        token=sharedpreferences.getString("access_token",null);
        id=sharedpreferences.getString("id",null);
        nameHome=(TextView) findViewById(R.id.namehome);
        assign=(Button) findViewById(R.id.assignmentHome);
        prep=(Button) findViewById(R.id.practice);
        semester=sharedpreferences.getString("semester",null);
        department=sharedpreferences.getString("department",null);
        mtoggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        userName=(TextView) headerView.findViewById(R.id.userNaame);
        enrollment=(TextView) headerView.findViewById(R.id.userid);
        upload_edit_button=(Button) headerView.findViewById(R.id.uploadImageButton);
        profilepic = (ImageView) headerView.findViewById(R.id.userimage);
        nameHome.setText(name);
        userName.setText(name);
        enrollment.setText(id);

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Assignments_List.class);
                intent.putExtra("access_token",token);
                intent.putExtra("id",id);
                intent.putExtra("semester",semester);
                intent.putExtra("department",department);
                startActivity(intent);
            }
        });
        prep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), SubjectSelection.class);
                i.putExtra("Name", name);
                startActivity(i);
                finish();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.assignment){
                    Intent intent = new Intent(getApplicationContext(), Assignments_List.class);
                    intent.putExtra("access_token",token);
                    intent.putExtra("id",id);
                    intent.putExtra("semester",semester);
                    intent.putExtra("department",department);
                    startActivity(intent);

                }
                if(item.getItemId()==R.id.feedback){
                    Intent intent = new Intent(getApplicationContext(), FeedBack.class);
                    intent.putExtra("access_token",token);
                    intent.putExtra("id",id);
                    startActivity(intent);

                }

                if(item.getItemId()==R.id.form){
                    Intent intent = new Intent(getApplicationContext(), Form.class);
                    intent.putExtra("access_token",token);
                    intent.putExtra("id",id);
                    startActivity(intent);

                }
                if(item.getItemId()==R.id.Logout){
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }

                if(item.getItemId()==R.id.ChangePasswordmenu){
                    Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                    startActivity(intent);
                }

                return false;
            }
        });
       drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
