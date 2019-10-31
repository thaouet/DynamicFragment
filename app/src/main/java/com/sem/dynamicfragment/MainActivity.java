package com.sem.dynamicfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DynamicFragment.DynamicFragmentCallback {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an object of your Fragment

                DynamicFragment b = DynamicFragment.newInstance("Hello Fragment");
                // Create an object of FragmentManager
                FragmentManager fm = getSupportFragmentManager();
                // Begin the transaction
                FragmentTransaction ft = fm.beginTransaction();
                // Replace the container with the new fragment
                ft.replace(R.id.fragmentframe,b);
                ft.commit();
            }
        });

    }

    @Override
    public void onBtnClicked() {
        Toast.makeText(this,"Tu as clické !",Toast.LENGTH_SHORT).show();
    }
}
