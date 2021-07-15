package com.codeinger.technorizentask.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.codeinger.technorizentask.R;
import com.codeinger.technorizentask.databinding.ActivitySignInBinding;
import com.codeinger.technorizentask.ui.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

    }

    private void init() {

        binding.tvForget.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, ForgotPasswordActivity.class));

        });

        binding.tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this,SignUpActivity.class));

        });

        binding.tvSignin.setOnClickListener(view -> {
            if (!EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText()).matches()
            ) {
                Snackbar.make(findViewById(android.R.id.content),
                        R.string.corr,
                        Snackbar.LENGTH_SHORT).show()   ;
            }
            else if(binding.etPassword.getText().toString().replace(" ", "").length() <=6){
                Snackbar.make(findViewById(android.R.id.content),
                        R.string.pass,
                        Snackbar.LENGTH_SHORT).show()   ;
            } else {

                startActivity(new Intent(this, MainActivity.class));
            }
        });


    }
}