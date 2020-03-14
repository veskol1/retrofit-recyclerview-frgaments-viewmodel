package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private TextView editText;
    private SharedViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = findViewById(R.id.edit_text_main);

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new FragmentA())
                .add(R.id.container_b, new FragmentB())
                .commit();
    }


}
