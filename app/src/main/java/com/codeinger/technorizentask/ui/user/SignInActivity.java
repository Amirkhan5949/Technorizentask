package com.codeinger.technorizentask.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.codeinger.technorizentask.R;
import com.codeinger.technorizentask.databinding.ActivitySignInBinding;
import com.codeinger.technorizentask.model.UserModel;
import com.codeinger.technorizentask.utils.SharedPrefsManager;
import com.codeinger.technorizentask.utils.Util;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private UserViewModel viewModel;
    private List<UserModel> user;
    private LiveData<List<UserModel>> alluser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        init();
        alluser= viewModel.getallUsers();

    }

    private void init() {


        binding.tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));

        });

        binding.tvSignin.setOnClickListener(view -> {
            if (!Util.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText()).matches()
            ) {
                Snackbar.make(findViewById(android.R.id.content),
                        R.string.corr,
                        Snackbar.LENGTH_SHORT).show();
            } else if (binding.etPassword.getText().toString().replace(" ", "").length() < 6) {
                Snackbar.make(findViewById(android.R.id.content),
                        R.string.pass,
                        Snackbar.LENGTH_SHORT).show();
            } else {
                user = viewModel.getEmailAndPass(binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString());


                Log.i("sdckjs", "init: " + user);

                if (user!=null&&user.size() != 0) {
                    long id =user.get(0).getU_id();
                    SharedPrefsManager.getInstance().setObject("Users_info",user.get(0));
                    Intent intent=new Intent(this, MainActivity.class);
                    intent.putExtra("U_id",id);
                    startActivity(intent);
                } else {
                    Snackbar.make(findViewById(android.R.id.content),
                            "You don't exist",
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });


    }
}