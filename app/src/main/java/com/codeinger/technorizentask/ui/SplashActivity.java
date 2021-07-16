package com.codeinger.technorizentask.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.codeinger.technorizentask.databinding.ActivitySplashBinding;
import com.codeinger.technorizentask.model.UserModel;
import com.codeinger.technorizentask.ui.user.MainActivity;
import com.codeinger.technorizentask.ui.user.SignInActivity;
import com.codeinger.technorizentask.utils.SharedPrefsManager;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding  binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                UserModel user =SharedPrefsManager.getInstance().getObject("Users_info", UserModel.class);
                if (user!=null){
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    mainIntent.putExtra("U_id",user.getU_id());
                    startActivity(mainIntent);
                    SplashActivity.this.finish();
                }else {
                    Intent mainIntent = new Intent(SplashActivity.this, SignInActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }

            }
        }, 1000);

    }
}