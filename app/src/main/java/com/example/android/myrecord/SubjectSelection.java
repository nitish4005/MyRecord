package com.example.android.myrecord;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SubjectSelection extends AppCompatActivity {

    Button btn, btntopic1, btntopic2, btntopic3, btntopic4, btntopic5;
    String n;
    RelativeLayout rl;
    int option = 0,l = 12;
    LinearLayout ll;
    private SQLiteDatabase db;
    String q[] = {
            "","What is the range of short data type in Java?",
            "What is the range of byte data type in Java?",
            "An expression involving byte, int, and literal numbers is promoted to which of these?",
            "Which data type value is returned by all transcendental math functions?",
            "Which of these operators is used to allocate memory to array variable in Java?",
            "What will this code print?\n" +
                    "int arr[] = new int [5];\n" +
                    "System.out.print(arr);",
            "Which of these is necessary to specify at time of array initialization?",
            "Which of these access specifiers must be used for main() method?",
            "Which of these is used to access member of class before object of that class is created?",
            "What is the process by which we can control what parts of a program can access the members of a class?",
            "Which of these class is superclass of all other classes?"
    };
    String a[] = {"","-128 to 127", "-128 to 127", "int", " int", "malloc", "0", "row", "private", "public", "Polymorphism", "Math"};
    String b[] = {"","-32768 to 32767", "-32768 to 32767", "long", "float", "alloc", "value stored in arr[0]", "column", "public", "private", "Abstraction", "Process"};
    String c[] = {"","-2147483648 to 2147483647", "-2147483648 to 2147483647", "byte", "double", "new", "00000", "Both Row and Column", "protected", " static", "Encapsulation", "System"};
    String d[] = {"","None of the mentioned", "None of the mentioned", "float", "long", "new Malloc", " Class name@ hashcode in hexadecimal form", "None of the above", "None of the mentioned", " protected", " Recursion", "Object"};
    int ans[] = {0,1, 0, 0, 2, 2, 3, 0, 1, 2, 2, 3};

    //q2
    String q2[] = {
            "","How many Bytes are stored by ‘Long’ Datatype in C# .net?",
            "Choose “.NET class” name from which datatype “UInt” is derived ?",
            "Correct Declaration of Values to variables ‘a’ and ‘b’?",
            "Arrange the following datatype in order of increasing magnitude sbyte, short, long, int.",
            "Which datatype should be more preferred for storing a simple number like 35 to improve execution speed of a program?",
            "Number of constructors a class can define is ?",
            "Correct statement about constructors in C#.NET is ?",
            "Which among the following is the correct statement :",
            "Can the method add() be overloaded in the following ways in C#?\n" +
                    "public int add() { }\n" +
                    "public float add(){ }",
            "Which of the following statements is correct about constructors in C#.NET?",
            "What is the return type of constructors?"
    };
    String a2[] = {"","8", "System.Int16", "int a = 32, b = 40.6;", "long < short < int < sbyte", "sbyte", "1", "Constructors cannot be overloaded", " initialize the objects", "True", "A constructor cannot be declared as private", "int"};
    String b2[] = {"","4", "System.UInt32", " int a = 42; b = 40;", "sbyte < short < int < long", "short", "2", "Constructors allocate space for object in memory", "construct the data members", "false", "A constructor cannot be overloaded", "float"};
    String c2[] = {"","2", "System.UInt64", "int a = 32; int b = 40;", "short < sbyte < int < long", "int", "Any number", "Constructors are never called explicitly", "both a & b", "None of the mentioned.", "A constructor can be a static constructor", " void"};
    String d2[] = {"","1", "System.UInt16", " int a = b = 42;", " short < int < sbyte < long", "long", "None of the mentioned", "Constructors have same name as name of the class", "None of the mentioned", "None of the above", "None of the mentioned", "None of the mentioned"};
    int ans2[] = {0,0, 1, 2, 1, 0, 2, 2, 1, 1, 2, 3};


    //q3
    String q3[] = {
            "","What is Pending Intent in android?",
            "What is android view group?",
            "What is the difference between services and thread in android?",
            "How many broadcast receivers are available in android?",
            "What is an anonymous class in android?",
            "What is a base adapter in android?",
            "Data can be read from local source XML in android through",
            "What is the HTTP response error code status in android?",
            "Is it mandatory to call onCreate() and onStart() in android?",
            "What is bean class in android?",
            "What is ANR in android?"
    };
    String a3[] = {"","It is a kind of an intent", "Collection of views and other child views", "Services performs functionalities in the background. By default services run on main thread only",
            "sendIntent()", "Interface class", " Base Adapter is a common class for any adapter, which can we use for both ListView and spinner",
            "XML resource parser", "status code < 100", "No, we can write the program without writing onCreate() and onStart()", "A class used to hold states and objects", "When the application is not responding ANR will occur."};
    String b3[] = {"","It is used to pass the data between activities", "Base class of building blocks", "Thread and services are having same functionalities.",
            " onRecieve()", "A class that does not have a name but have functionalities in it", "A kind of adapter"
            , "XML pull parsing", " status code > 100", "Yes, we should call onCreate() and onStart() to write the program", " A bean class can be passed from one activity to another.", "Dialog box is called as ANR."};
    String c3[] = {"","It will fire at a future point of time.", "Layouts", "Thread works on services", " implicitBroadcast()", "Java class",
            "Data storage space", "DOM parsing", " status >= 400", "At least we need to call onCreate() once", "A mandatory class in android", " When Android forcefully kills an application, it is called ANR"};
    String d3[] = {"","None of the above", "None of the Above", "None of the above", "sendBroadcast(),sendOrderBroadcast(),and sendStickyBroadcast().",
            "Manifest file", "None of the above.", "None of the above", "None of the above", "None of the above", "None of the above", "None of the above"};
    int ans3[] = {0,2, 0, 0, 3, 1, 0, 0, 2, 0, 0, 0};
    //q4
    String q4[] = {
            "","Which of the following is correct about PHP?",
            "Which of the following type of variables are special type that only has one value: NULL?",
            "Which of the following is correct about constants vs variables in PHP?",
            "Which of the following is used to get information sent via get method in PHP?",
            "Doubly quoted strings are treated almost literally, whereas singly quoted strings replace variables with their values as well as specially interpreting certain character sequences.",
            "Which of the following is used to delete a cookie?",
            "Which of the following is used to destroy the session?",
            "Which of the following gives a string containing PHP script file name in which it is called?",
            "Which of the following method can be used to close a MySql database using PHP?",
            "Which of the following type of variables are sequences of characters, like PHP supports string operations.?",
            "Which of the following is correct about constants vs variables in PHP?"
    };
    String a4[] = {"","PHP is a recursive acronym for PHP: Hypertext Preprocessor.",
            "Strings",
            "Constants may be defined and accessed anywhere without regard to variable scoping rules.",
            " $_GET",
            "true",
            "setcookie() function",
            "session_start() function",
            "$_PHP_SELF",
            "mysql_connect()",
            "Strings",
            "Constants may be defined and accessed anywhere without regard to variable scoping rules."


    };
    String b4[] = {"","PHP is a server side scripting language that is embedded in HTML.",
            "Doubles",
            " Once the Constants have been set, may not be redefined or undefined.",
            "$GET",
            "false",
            " $_COOKIE variable",
            "$_SESSION[]",
            "$php_errormsg",
            " mysql_query()",
            "Arrays",
            "Once the Constants have been set, may not be redefined or undefined."


    };
    String c4[] = {"","It is used to manage dynamic content, databases, session tracking, even build entire e-commerce sites.",
            "Booleans",
            "Both of the above.",
            "$GETREQUEST",
            "both",
            " isset() function",
            "isset() function",
            "$_COOKIE",
            "mysql_close()",
            "Objects",
            "Both of the above."


    };
    String d4[] = {""," All of the above.",
            "NULL",
            " None of the above.",
            "None of the above.",
            "None",
            "None of the above.",
            "session_destroy() function",
            "$_SESSION",
            "None of the above",
            "Resources",
            " None of the above."

    };
    int ans4[] = {0,3, 3, 2, 0, 1, 0, 3, 0, 2, 0,2};

    //q5
    String q5[] = {"","Relational calculus is a ?",
                   "‘AS’ clause is used in SQL for?",
                   "An entity set that does not have sufficient attributes to form a primary key is a?",
                   "The language used in application programs to request data from the DBMS is referred to as the?",
                   "A logical schema",
                   "The property / properties of a database is / are :",
                   "Data independence meAns",
                   "Which of the following operation is used if we are interested in only certain columns of a table?",
                   "Which of the following is a legal expression in SQL?",
                   "The result of the UNION operation between R1 and R2 is a relation that includes",
                   "An instance of relational schema R (A, B, C) has distinct values of A including NULL values. Which one of the following is true?"};
    String a5[] = {"","Procedural language.","Selection operation.","strong entity set.","DML","is the entire database.","It is an integrated collection of logically related records.","data is defined separately and not included in programs.","PROJECTION","SELECT NULL FROM EMPLOYEE;","all the tuples of R1","A is a candidate key"};
    String b5[] = {"","Non- Procedural language.","Rename operation.","weak entity set.","DDL","is a standard way of organizing information into accessible parts.","It consolidates separate files into a common pool of data records.","programs are not dependent on the physical attributes of data.","SELECTION","SELECT NAME FROM EMPLOYEE;","tall the tuples of R2","A is not a candidate key"};
    String c5[] = {"","Data definition language.","Join operation.","simple entity set.","VDL","describes how data is actually stored on disk.","Data stored in a database is independent of the application programs using it.","programs are not dependent on the logical attributes of data.","UNION","SELECT NAME FROM EMPLOYEE WHERE SALARY = NULL;","all the tuples of R1 and R2","A is a primary key"};
    String d5[] = {"","High level language.","Projection operation.","primary entity set.","SDL","both 1 and 2","All of the above","both (B) and (C).","JOIN","None of the above","all the tuples of R1 and R2 which have common columns","Both 1 and 3"};
    int ans5[] = {0,1,1,1,0,0,3,3,0,1,3,1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selection);
        Bundle data = getIntent().getExtras();
        n=data.getString("Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createDatabase();
        btn = (Button) findViewById(R.id.button1);
        btntopic1 = (Button) findViewById(R.id.btntopic1);
        btntopic2 = (Button) findViewById(R.id.btntopic2);
        btntopic3 = (Button) findViewById(R.id.btntopic3);
        btntopic4 = (Button) findViewById(R.id.btntopic4);
        btntopic5 = (Button) findViewById(R.id.btntopic5);
        rl = (RelativeLayout) findViewById(R.id.rlcontinue);
        ll = (LinearLayout) findViewById(R.id.rltopics);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),PracticeStart.class);
                intent.putExtra("Name",n);
                startActivity(intent);
                finish();
            }
        });

        btntopic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                option = 1;
                copyquestions();
            }
        });

        btntopic2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                option = 2;
                copyquestions();
            }
        });

        btntopic3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                option = 3;
                copyquestions();
            }
        });

        btntopic4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                option = 4;
                copyquestions();
            }
        });

        btntopic5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                option = 5;
                copyquestions();
            }
        });
    }

    void copyquestions() {
        db.execSQL("delete  from questions");

        if (option == 1)
            insertDB1();
        else if (option == 2)
            insertDB2();
        else if (option == 3)
            insertDB3();
        else if (option == 4)
            insertDB4();
        else if (option == 5)
            insertDB5();

        rl.setVisibility(View.VISIBLE);
        ll.setVisibility(View.GONE);


    }

    protected void insertDB1() {

        for (int i = 0; i < l; i++) {
            String query="INSERT INTO questions(question,opA,opB,opC,opD,answer) values('" + q[i] + "','" + a[i] + "','" + b[i] +
                    "','" + c[i] + "','" + d[i] + "','" + ans[i] + "');";
            db.execSQL(query);
        }
    }

    protected void insertDB2() {


        for (int i = 0; i < l; i++) {
            String query = "INSERT INTO questions(question,opA,opB,opC,opD,answer) values('" + q2[i] + "','" + a2[i] + "','" + b2[i] +
                    "','" + c2[i] + "','" + d2[i] + "','" + ans2[i] + "');";
            db.execSQL(query);
        }

    }

    protected void insertDB3() {


        for (int i = 0; i < l; i++) {
            String query = "INSERT INTO questions(question,opA,opB,opC,opD,answer) values('" + q3[i] + "','" + a3[i] + "','" + b3[i] +
                    "','" + c3[i] + "','" + d3[i] + "','" + ans3[i] + "');";
            db.execSQL(query);
        }

    }

    protected void insertDB4() {


        for (int i = 0; i < l; i++) {
            String query = "INSERT INTO questions(question,opA,opB,opC,opD,answer) values('" + q4[i] + "','" + a4[i] + "','" + b4[i] +
                    "','" + c4[i] + "','" + d4[i] + "','" + ans4[i] + "');";
            db.execSQL(query);
        }

    }

    protected void insertDB5() {


        for (int i = 0; i < l; i++) {
            String query = "INSERT INTO questions(question,opA,opB,opC,opD,answer) values('" + q5[i] + "','" + a5[i] + "','" + b5[i] +
                    "','" + c5[i] + "','" + d5[i] + "','" + ans5[i] + "');";
            db.execSQL(query);
        }

    }

    protected void createDatabase(){
        try {
            db = openOrCreateDatabase("InterviewDB.db", Context.MODE_PRIVATE, null);
            db.execSQL("DROP TABLE IF EXISTS 'questions'");
            db.execSQL("CREATE TABLE  questions(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, question VARCHAR, opA VARCHAR," +
                    "opB VARCHAR, opC VARCHAR,opD VARCHAR,answer NUMBER)");
        } catch (Exception ex) {
            System.out.println("error=" + ex.getMessage());
        }

    }

    @Override
    public void onBackPressed() {
        if(rl.getVisibility()==rl.VISIBLE){
            rl.setVisibility(View.GONE);
            ll.setVisibility(View.VISIBLE);
        }else {
            Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
            intent.putExtra("Name",n);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onResume() {
        rl.setVisibility(View.GONE);
        ll.setVisibility(View.VISIBLE);
        super.onResume();
    }
}
