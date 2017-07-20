package com.vbv.firebaseexample;


import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class StudentsAdapter extends ArrayAdapter<Student> {
    List<Student> studentArrayList;
    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference studentRef = database.getReference("students");

    public StudentsAdapter(Context context, List<Student> studentList) {
        super(context, R.layout.item_student, studentList);
        this.context = context;
        this.studentArrayList = studentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.item_student, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        Button updateBtn = (Button) listViewItem.findViewById(R.id.updateBtn);
        Button deletebtn = (Button) listViewItem.findViewById(R.id.deleteBtn);

        final Student student = studentArrayList.get(position);
        textViewName.setText(student.getName());
        textViewGenre.setText(student.getRoll());

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = student.getId();
                studentRef.child(id).removeValue();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = student.getId();
                showUpdateDeleteDialog(id);

            }
        });

        return listViewItem;
    }

    private void showUpdateDeleteDialog(final String studentId) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextRoll = (EditText) dialogView.findViewById(R.id.editTextroll);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateStudent);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String roll = editTextRoll.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    Student student = new Student(name, roll,studentId);
                    studentRef.child(studentId).setValue(student);
                    b.dismiss();
                }
            }
        });

    }
}
