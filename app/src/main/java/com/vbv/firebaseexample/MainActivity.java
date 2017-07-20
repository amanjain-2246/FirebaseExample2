package com.vbv.firebaseexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText nameET;
    EditText rollET;
    Button btn;
    ListView lv;
    ArrayList<Student> studentArrayList = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference studentRef = database.getReference("students");
    DatabaseReference bookRef = database.getReference("books");
    StudentsAdapter studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = (EditText) findViewById(R.id.nameET);
        rollET = (EditText) findViewById(R.id.rollET);
        btn = (Button) findViewById(R.id.AddBtn);
        lv = (ListView) findViewById(R.id.lv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });

        studentsAdapter = new StudentsAdapter(MainActivity.this, studentArrayList);
        //attaching adapter to the listview
        lv.setAdapter(studentsAdapter);
    }

    private void addStudent() {
        String name = nameET.getText().toString();
        String roll = rollET.getText().toString();
        String key = studentRef.push().getKey();
        Student student = new Student(name, roll, key);
        studentRef.child(key).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Some Issue occured", Toast.LENGTH_SHORT).show();
                }
            }
        });
        String key1 = bookRef.push().getKey();
        Book book = new Book("maths","vbv","3000","12 July 2017",key1);
        bookRef.child(key).setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentArrayList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Student student = postSnapshot.getValue(Student.class);
                    studentArrayList.add(student);
                }
                StudentsAdapter studentsAdapter = new StudentsAdapter(MainActivity.this, studentArrayList);
                lv.setAdapter(studentsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        studentRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.i(TAG, "onChildAdded: " + dataSnapshot.toString());
//                Student student = dataSnapshot.getValue(Student.class);
//                Log.i(TAG, "onChildAdded: " + student);
//                Log.i(TAG, "onChildAdded: " + dataSnapshot.getChildren());
//                studentArrayList.add(student);
//                studentsAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Log.i(TAG, "onChildChanged: " + dataSnapshot.toString());
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Log.i(TAG, "onChildRemoved: " + dataSnapshot.toString());
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                Log.i(TAG, "onChildMoved: " + dataSnapshot.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.i(TAG, "onCancelled: " + databaseError.getMessage());
//            }
//        });
    }
}
