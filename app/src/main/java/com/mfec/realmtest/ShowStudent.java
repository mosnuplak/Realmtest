package com.mfec.realmtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by E5-473G on 7/21/2017.
 */

public class ShowStudent extends AppCompatActivity {
    private Realm realm;
    TextView btn_show;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent = getIntent();
        str = intent.getStringExtra("text");
        realm = Realm.getDefaultInstance();
        btn_show = (TextView) findViewById(R.id.btn_show);
        showStudent();
    }

    private void showStudent() {
        RealmResults<Student> listStudent = getAllStudent();
        Log.d("STUDENT", "SIZE = " + listStudent.size());
        String str = "";
        for (Student student : listStudent) {
            str += "\nID = " + student.getStudentId() +
                    " ,Name " + student.getStudentName() +
                    " ,Time " + student.getStudentScore() + "";
            btn_show.setText(this.str + str);
        }
    }

    private RealmResults<Student> getAllStudent() {
        RealmResults<Student> result = realm.where(Student.class).findAll();
        return result;
    }
}
