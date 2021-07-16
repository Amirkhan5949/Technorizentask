package com.codeinger.technorizentask.ui.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.codeinger.technorizentask.R;
import com.codeinger.technorizentask.databinding.ActivitySignUpBinding;
import com.codeinger.technorizentask.model.UserModel;
import com.codeinger.technorizentask.utils.SharedPrefsManager;
import com.codeinger.technorizentask.utils.Util;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    boolean isImageAdded = false;
    private ActivitySignUpBinding binding;
    private UserViewModel viewModel;
    private UserModel user;
    private Bitmap bitmap;
    private List<UserModel> data;
    private int U_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        init();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    isImageAdded = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                binding.ivProfile.setImageBitmap(bitmap);
            }
        }

    }

    private void init() {
        binding.tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        binding.tvSignIn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));

        });

        binding.ivProfile.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });


    }


    private void insertData() {
        if (validate()) {
            Log.d("xvvz", "insertData: " + "fsgd");
            data = viewModel.getEmail(binding.etEmail.getText().toString());
            Log.i("fzvxvx", "insertData: " + data);
            if (data.size() < 1) {
                user = new UserModel(0, binding.etName.getText().toString(), binding.etLastName.getText().toString(),
                        binding.etNo.getText().toString(), binding.etEmail.getText().toString(), binding.etPassword.getText().toString(), bitmap);

                long id = (int) viewModel.addUser(user);
                user.setU_id((int)id);

                SharedPrefsManager.getInstance().setObject("Users_info", user);

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                intent.putExtra("U_id", id);
                startActivity(intent);

            } else {
                Snackbar.make(findViewById(android.R.id.content),
                        "user allready exist",
                        Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validate() {
        if (isImageAdded == false) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Please Select Image",
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), "Enter Name", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etLastName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.lastname,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etNo.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), "Enter your no", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.etNo.getText().toString().replace(" ", "").length() < 10) {
            Snackbar.make(findViewById(android.R.id.content),
                    "enter right no",
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!Util.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText().toString().replace(" ", "")).matches()
        ) {
            Snackbar.make(findViewById(android.R.id.content), "Enter correct email", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.etPassword.getText().toString().replace(" ", "").length() < 6) {
            Snackbar.make(findViewById(android.R.id.content), "Enter long password", Snackbar.LENGTH_SHORT).show();
            return false;

        } else {
            return true;
        }
    }

}