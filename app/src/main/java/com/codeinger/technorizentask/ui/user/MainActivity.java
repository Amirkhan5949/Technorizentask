package com.codeinger.technorizentask.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codeinger.technorizentask.R;
import com.codeinger.technorizentask.databinding.ActivityMainBinding;
import com.codeinger.technorizentask.model.UserModel;
import com.codeinger.technorizentask.ui.product.Fragment.ProductAddFragment;

import com.codeinger.technorizentask.ui.product.Fragment.ProductListFragment;
import com.codeinger.technorizentask.utils.SharedPrefsManager;
import com.codeinger.technorizentask.utils.Type;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import de.hdodenhof.circleimageview.CircleImageView;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserViewModel  viewModel;
    private CircleImageView circleImageView;
    private TextView tv_name,tv_emial_id,tv_no;
    private LinearLayout logout;
    private long U_id;
    private List<UserModel> user;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replace(new ProductListFragment(),"ProductListFragment");

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        init();

    }

    public void init(){
        U_id=getIntent().getLongExtra("U_id",0);
        Log.i("fsfdsc", "init: "+U_id);
        user= viewModel.getUserById(U_id);

        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawer.openDrawer(GravityCompat.START);

            }
        });

        LinearLayout ll_Main=(LinearLayout) binding.navigation.getHeaderView(0);
        circleImageView=ll_Main.findViewById(R.id.iv_profile_image);
        tv_name=ll_Main.findViewById(R.id.tv_name);
        tv_emial_id=ll_Main.findViewById(R.id.tv_emial_id);
        tv_no=ll_Main.findViewById(R.id.tv_no);
        logout=ll_Main.findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsManager.getInstance().clearPrefs();
                Intent intent=new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });




        userModel= SharedPrefsManager.getInstance().getObject("Users_info",UserModel.class);
        if(user!=null&&user.size()>0){
            circleImageView.setImageBitmap(user.get(0).getProfile());
        }


        tv_emial_id.setText(userModel.getEmail());
        tv_name.setText(userModel.getName()+""+userModel.getLast_name());
        tv_no.setText(userModel.getMobile_no());


        binding.llAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(  ProductAddFragment.newInstance(Type.ADD,null),"ProductAddFragment");
            }
        });

    }


    public void setAddVisiblity(int visiblity){
        binding.llAddProduct.setVisibility(visiblity);
    }

    public void SetAppbar(int visiblity){
        binding.appBarLayout.setVisibility(visiblity);

    }

    public void back(){
        onBackPressed();
    }

    public void replace(Fragment fragment,String tag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment, tag).addToBackStack("").commit();
    }
}