package br.com.vsgdev.firebase.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import br.com.vsgdev.firebase.R;
import br.com.vsgdev.firebase.models.Circle;

public class NewCircleActivity extends BaseActivity implements View.OnClickListener {

    EditText edtNameCircle;
    EditText edtescription;
    DatabaseReference db;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_circle);
        db = FirebaseDatabase.getInstance().getReference();
        edtNameCircle = (EditText) findViewById(R.id.et_name_new_circle);
        edtescription = (EditText) findViewById(R.id.et_description_new_circle);
        btnSave = (Button) findViewById(R.id.btn_save_new_circle);
        btnSave.setOnClickListener(this);
    }


    private boolean checkFields() {
        if (edtNameCircle.getText() != null && edtescription.getText() != null)
            return true;
        return false;
    }

    private void saveCircle(final String name) {
        if (checkFields()) {
            createNew(name);
           /* Listener para cicles para verificar dados do "circles"*/
            db.child("circles").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                 /*   verifica se tem algum filho com o nome */
                    if (dataSnapshot.hasChild(name.toLowerCase()))
                        Toast.makeText(getApplicationContext(), "this circle already exist", Toast.LENGTH_LONG).show();
                    else {
                        createNew(name);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_new_circle:
                saveCircle(edtNameCircle.getText().toString());
        }

    }

    private void createNew(String name) {

        Circle circle = new Circle(edtNameCircle.getText().toString(), edtescription.getText().toString());
        /*Cria a nova entidade no banco*/
        db.child("circles").child(name).push();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/circles/" + name, circle.toMap());
        //Listener para saber se o dado foi salvo ou não
        DatabaseReference.CompletionListener completionListener = new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(), "Não foi possivel gravar", Toast.LENGTH_LONG).show();
                    Log.e("NEW_CIRCLE", databaseError.getMessage() + " " + databaseError.getDetails());
                } else {
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                    finish();
                }
            }
        };
        /*chamado para alterar a linha criada com os dados e o listener para saber se a transação foi salva*/
        db.updateChildren(childUpdates, completionListener);
    }
}
