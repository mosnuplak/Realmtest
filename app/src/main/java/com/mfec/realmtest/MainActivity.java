package com.mfec.realmtest;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Realm realm;
    Button btnShow;
    Button btnInsert;
    public EditText editQ;
    EditText editQ1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRealm();



        realm = Realm.getDefaultInstance();

        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(this);
        btnShow = (Button) findViewById(R.id.btn_show);
        editQ = (EditText) findViewById(R.id.edit_1);
        editQ1 = (EditText) findViewById(R.id.edit_2);
        //clearStudent();


        btnShow.setOnClickListener(this);
        btnInsert.setOnClickListener(this);



    }
    private  void addStudent(){


        realm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Number currentIdNum = realm.where(Student.class).max("studentId");
                        int nextId;
                        if(currentIdNum == null) {
                            nextId = 1;
                        } else {
                            nextId = currentIdNum.intValue() + 1;
                        }

                            Student student = realm.createObject(Student.class);
                            student.setStudentId(nextId);
                            student.setStudentName(editQ.getText().toString());
                            student.setStudentScore(editQ1.getText().toString());


                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Generate student complete.", Toast.LENGTH_SHORT).show();

                    }

                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    }
                });
    }



    private void clearStudent() {
        realm.beginTransaction();
        realm.delete(Student.class);
        realm.commitTransaction();
    }


    @Override
    public void onClick(View v) {
        if (v == btnShow) {
            Intent intent = new Intent(this, ShowStudent.class);
            intent.putExtra("text","Test Text View");
            startActivity(intent);
//            finish();
        }
        if (v == btnInsert) {
            addStudent();
        }
    }

    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("android.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}