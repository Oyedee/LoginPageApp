package com.example.loginpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class LoginActivity extends AppCompatActivity {

    String username = "";
    String password = "";
    private EditText mUserName;
    private EditText mPassWord;
    private Button mLoginButton;
    private TextView mCreateAccount;
    private TextView mChangePassword;
    public static final String LOGIN_INFO = "com.example.loginpage.LOGIN_INFO";
    public static final String LOGIN_INFO2 = "com.example.loginpage.LOGIN_INFO2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView image = findViewById(R.id.image_view);

        mUserName = findViewById(R.id.edit_text_username);
        mPassWord = findViewById(R.id.edit_text_password);

        mLoginButton = findViewById(R.id.login_click_button);

        mCreateAccount = findViewById(R.id.text_click_create_account);
        mChangePassword = findViewById(R.id.text_click_change_password);



        submitUserLoginDetails();
        createAccount();
        changePassword();

    }

    private void saveNewAccountDetails() {

        Intent intent = getIntent();
        String defaultUsername = intent.getStringExtra(LOGIN_INFO);
        String defaultPassword = intent.getStringExtra(LOGIN_INFO2);
        if(defaultUsername == null || defaultPassword == null) {
            Toast.makeText(getApplicationContext(), "Login Denied!! Create new account first!!", Toast.LENGTH_LONG).show();
        } else if(defaultUsername.equals(username)  && defaultPassword.equals(password)) {
            Toast.makeText(getApplicationContext(), "Login Successful!!!", Toast.LENGTH_LONG).show();
            Intent intentPage = new Intent(LoginActivity.this, AppActivity.class);
            startActivity(intentPage);
        } else {
            Toast.makeText(getApplicationContext(), "Login Denied!! Input the correct details", Toast.LENGTH_LONG).show();
        }
    }

    private void changePassword() {
        mChangePassword.setText("Change Password");
        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createAccount() {
        mCreateAccount.setText("Create Account");
        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void submitUserLoginDetails() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mUserName.getText().toString();
                password = mPassWord.getText().toString();
                if(username.equals("") || password.equals(""))
                {
                    showMessage("     ERROR!! ", "EMPTY FIELDS");
                } else {
                    saveNewAccountDetails();
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