package com.example.loginpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity {

    private String mNewUsername;
    private String mNewPassword;
    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mUsername = findViewById(R.id.text_change_name);
        mPassword = findViewById(R.id.text_change_password);
        Button createNew = findViewById(R.id.button_change_password);


        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewUsername = mUsername.getText().toString();
                mNewPassword = mPassword.getText().toString();

                if (mNewUsername.equals("") || mNewPassword.equals("")) {
                    showMessage("     ERROR!! ", "EMPTY FIELDS");
                } else {

                    SharedPreferences myLocalDB
                            = PreferenceManager.getDefaultSharedPreferences(CreateAccountActivity.this);
                    SharedPreferences.Editor myDatabaseEditor = myLocalDB.edit();

                    myDatabaseEditor.putString("Name", mNewUsername);
                    myDatabaseEditor.putString("Password", mNewPassword);
                    myDatabaseEditor.apply();

                    String defaultUserName = myLocalDB.getString("Name", mNewUsername);
                    String defaultPassword = myLocalDB.getString("Password", mNewPassword);

                    Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    intent.putExtra(LoginActivity.LOGIN_INFO, defaultUserName);
                    intent.putExtra(LoginActivity.LOGIN_INFO2, defaultPassword);
                    startActivity(intent);
                }
            }
        });

    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}