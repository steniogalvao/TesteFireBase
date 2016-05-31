package br.com.vsgdev.firebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import br.com.vsgdev.firebase.R;
import br.com.vsgdev.firebase.models.Circle;
import br.com.vsgdev.firebase.models.User;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";

    private Button btnCreateCircle;
    private Button btnNewUser;
    private DatabaseReference mDatabase;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        System.out.println(FirebaseAuth.getInstance().getCurrentUser().getToken(false).getResult().getToken());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        btnCreateCircle = (Button) findViewById(R.id.btn_create_circle_content_main);
        btnCreateCircle.setOnClickListener(this);
        btnNewUser = (Button) findViewById(R.id.btn_add_new_user_content_main);
        btnNewUser.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Listener dos btns
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_create_circle_content_main:
                createCircle();
                break;
            case R.id.btn_add_new_user_content_main:
                createUser();
                break;
        }
    }

    private void createCircle() {
        Intent intentCreateCircle = new Intent(this, NewCircleActivity.class);
        startActivity(intentCreateCircle);
    }

    private void createUser() {
        Intent intentCreateUser = new Intent(this, NewUserActivity.class);
        startActivity(intentCreateUser);
    }

}
