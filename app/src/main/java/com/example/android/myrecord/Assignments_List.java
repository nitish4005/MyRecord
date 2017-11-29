package com.example.android.myrecord;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myrecord.Network.ApiClient;
import com.example.android.myrecord.Network.ApiInterface;
import com.example.android.myrecord.NetworkModels.AssignmentsRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Assignments_List extends AppCompatActivity {

    private TextView mEmptyStateTextView;
    private ProgressBar progressBar;
    private static String token;
    private static String name = "";
    private static String stdid = "";
    private static String department = "";
    private static String semester = "";
    private ArrayList<AssignmentsRecord> assimentsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments__list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView listView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        Bundle data = getIntent().getExtras();
        token = data.getString("access_token");
        stdid=data.getString("id");
        department=data.getString("department");
        semester=data.getString("semester");
        listView.setEmptyView(mEmptyStateTextView);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        final CustomAdapter customAdapter = new CustomAdapter(assimentsList, this);
        listView.setAdapter(customAdapter);

        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<ArrayList<AssignmentsRecord>> assignmentListUserCall = apiInterface.assignmentList("Bearer "+ token,semester,department,stdid);
        assignmentListUserCall.enqueue(new Callback<ArrayList<AssignmentsRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<AssignmentsRecord>> call, Response<ArrayList<AssignmentsRecord>> response) {
                if (response.isSuccessful()) {
                    Log.i("TestResponse", "Sucsessful");
                    progressBar.setVisibility(View.GONE);
                    assimentsList.addAll(response.body());
                    customAdapter.notifyDataSetChanged();

                } else {
                    Log.i("TestResponse", "fail");
                    Toast.makeText(getApplicationContext(), "ErrorResponse", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AssignmentsRecord>> call, Throwable t) {
                Log.i("TestResponse", "Sucsessful" + t.getMessage());
                Toast.makeText(getApplicationContext(), "No assignment found", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(assimentsList.get(position).getStatus().equals("Expired")){
                    return;
                }else {
                    Intent intent = new Intent(getApplicationContext(), Assignment_StartPro.class);
                    intent.putExtra("assignRecord", assimentsList.get(position));
                    startActivity(intent);
                }


            }
        });


    }

    class CustomAdapter extends BaseAdapter {
        private ArrayList<AssignmentsRecord> mAssognMentList;
        private Context mContext;
        private int count=1;
        public CustomAdapter(ArrayList<AssignmentsRecord> mAssognMentList, Context mContext) {
            this.mAssognMentList = mAssognMentList;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mAssognMentList.size();
        }

        @Override
        public Object getItem(int position) {
            return mAssognMentList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            TextView tvMarksOrStatus = (TextView) convertView.findViewById(R.id.marksOrStaus);
            TextView tvDetailsOfAssignment = (TextView) convertView.findViewById(R.id.detailsOfAssignment);
            TextView tvDate = (TextView) convertView.findViewById(R.id.date);
            TextView tvTime = (TextView) convertView.findViewById(R.id.time);
            tvMarksOrStatus.setText(" " + count++);
            GradientDrawable marksCircle = (GradientDrawable) tvMarksOrStatus.getBackground();
            marksCircle.setColor(ContextCompat.getColor(getApplicationContext(), R.color.magnitude1));
            tvDetailsOfAssignment.setText(mAssognMentList.get(position).getSubject() + " Assignment \n Teacher :" + mAssognMentList.get(position).getTeacherName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date dateObject = null;
            try {
                dateObject = sdf.parse(mAssognMentList.get(position).getDateOfTest());
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            String formattedDate = formatDate(dateObject);
            tvDate.setText(formattedDate);
            sdf = new SimpleDateFormat("h:mm:ss");
            try {
                dateObject = sdf.parse(mAssognMentList.get(position).getStartTime());
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            String formattedTime = formatTime(dateObject);
            tvTime.setText(formattedTime);
            if(assimentsList.get(position).getStatus().equals("Expired")){
                convertView.setBackgroundColor(Color.parseColor("#FF00ACC1"));
            }else {
                convertView.setBackgroundColor(Color.parseColor("#FFC62828"));
            }

            return convertView;
        }

        private String formatDate(Date dateObject) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd,MM,yyyy");
            return dateFormat.format(dateObject);
        }

        private String formatTime(Date dateObject) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            return timeFormat.format(dateObject);
        }
    }
}
