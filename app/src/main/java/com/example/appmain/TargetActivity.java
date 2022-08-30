package com.example.appmain;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TargetActivity extends AppCompatActivity {

    private static final String[] COUNTRIES = new String[]{"Portrait", "Landscape"};
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        TextView textview_second = findViewById(R.id.textview_second);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setText(autoCompleteTextView.getAdapter().getItem(0).toString(), false);

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> textview_second.setText(autoCompleteTextView.getText().toString()));

        Button buttonPanel = findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(view -> showBottomSheetDialogComplainToBase(this));
    }

    private void showBottomSheetDialogComplainToBase(AppCompatActivity activity) {
        final View view = getLayoutInflater().inflate(R.layout.bottom_seet, findViewById(R.id.bottom_sheets));

        TextView tv_more_menu_file = view.findViewById(R.id.tv_more_menu_file);
        tv_more_menu_file.setOnClickListener(v -> {

            View view1 = getLayoutInflater().inflate(R.layout.item_password, findViewById(R.id.constrain_layout));

            TextInputLayout til = view1.findViewById(R.id.text_input_layout);
            TextInputEditText edittext = view1.findViewById(R.id.edit_text);


            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
            dialog.setTitle("Enter Password");
            dialog.setView(edittext);
            dialog.setView(til);
            dialog.setView(view1);
            dialog.setNegativeButton("Cancel",null);

            String task = String.valueOf(edittext.getText());

            dialog.setPositiveButton("OK", (dialogInterface, which) -> {

                til.setError("You need to enter a name");

            });

            AlertDialog builder = dialog.create();
            builder.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            builder.show();
            bottomSheetDialog.dismiss();

        });


        bottomSheetDialog = new BottomSheetDialog(activity);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

}