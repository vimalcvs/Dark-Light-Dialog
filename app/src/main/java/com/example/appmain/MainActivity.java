package com.example.appmain;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private int checkedItem;
    private String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MaterialToolbar toolbarTop = findViewById(R.id.toolbar);
        toolbarTop.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_delete) {
                showDialog();

            } else if (item.getItemId() == R.id.action_show){
                Intent intent = new Intent (this, TargetActivity.class);
                startActivity(intent);
            }
            return true;
        });


        Button buttonPanel =findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(view -> {
            Intent intent = new Intent (this, TargetActivity.class);
             startActivity(intent);

        });
    }

    private void showDialog() {
        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theme, MyApplication.getCheckedItem(), (dialogInterface, i) -> {
            selected = themes[i];
            checkedItem = i;
        });

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            if (selected == null){
                selected = themes[i];
                checkedItem = i;
            }
            switch (selected){
                case "System Default":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    break;
                case "Dark":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case "Light":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
            }
            MyApplication.setCheckedItem(checkedItem);
        });

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}