package br.com.vsgdev.firebase.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import br.com.vsgdev.firebase.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btnLogout = (Button) findViewById(R.id.btn_logout_activity_main);
        btnLogout.setOnClickListener(this);
    }

    //Listener dos butões
    @Override
    public void onClick(View v) {
        if (fab.isPressed()) {
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        if (btnLogout.isPressed()) {
             signOut();
        }
    }
}
