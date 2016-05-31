package br.com.vsgdev.firebase.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import br.com.vsgdev.firebase.R;
import br.com.vsgdev.firebase.models.Circle;
import br.com.vsgdev.firebase.models.User;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "NewUserActivity";
    private Spinner spnCircles;
    private EditText edtUserEmail;
    private Button btnSalvar;
    private DatabaseReference db;
    private List<Circle> circlesObj;
    private List<String> circlesName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        //Itens da view
        btnSalvar = (Button) findViewById(R.id.btn_save_new_user);
        btnSalvar.setOnClickListener(this);
        spnCircles = (Spinner) findViewById(R.id.s_circles_new_user);
        edtUserEmail = (EditText) findViewById(R.id.et_email_new_user);

        //pegando referencia do firebase
        db = FirebaseDatabase.getInstance().getReference();

        //lista de circles
        circlesName = new ArrayList<>();
        circlesObj = new ArrayList<>();

        loadCircles();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(NewUserActivity.this, R.layout.support_simple_spinner_dropdown_item, R.id.list_item, circlesName);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnCircles.setAdapter(dataAdapter);

//        loadOne("job");

        // Creating adapter for spinner


    }

    public void retrivingOneData() {

    }

    private void loadOne(final String circleName) {
        Query circleReference = FirebaseDatabase.getInstance().getReference().child("circles");
        circleReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Circle c = (Circle) map.get(circleName);
//                circlesName.add(c);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void loadCircles() {
        db.child("circles").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            circlesObj.add(d.getValue(Circle.class));
                            circlesName.add(d.getValue(Circle.class).getName());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("NEWUSER", "getUser:onCancelled", databaseError.toException());
                    }
                });

    }

    public boolean checkFields() {
        if (!edtUserEmail.getText().equals(""))
            return true;
        return false;
    }

    public User createUser() {
        if (!checkFields()) {
            Toast.makeText(getApplicationContext(), "Email deve ser preenchido", Toast.LENGTH_LONG);
        }
        User user = new User(edtUserEmail.getText().toString(), circlesObj);
        return user;
    }

    private void saveUser(User user) {

        //cria chave para usuário
        String userKey = db.child("users").push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        ///separa onde adicionar o nome usuário
        childUpdates.put("/users/" + userKey, user.toMap());
        for (Circle circle : user.getCircles()) {
            childUpdates.put("/users-circle/" + userKey + "/" + circle.getName(), circle.toMap());
        }


        //Listener para saber se o dado foi salvo ou não
        DatabaseReference.CompletionListener completionListener = new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(), "Não foi possivel gravar", Toast.LENGTH_LONG).show();
                    Log.e(TAG, databaseError.getDetails());
                } else {
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                    finish();
                }
            }
        };
        db.updateChildren(childUpdates, completionListener);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_new_user:
                saveUser(createUser());
                break;
        }

    }
}
