package com.codeinger.technorizentask.ui.product.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.codeinger.technorizentask.databinding.FragmentProductAddBinding;
import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.model.UserModel;
import com.codeinger.technorizentask.ui.user.MainActivity;
import com.codeinger.technorizentask.ui.product.ProductViewModel;
import com.codeinger.technorizentask.utils.SharedPrefsManager;
import com.codeinger.technorizentask.utils.Type;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;


public class ProductAddFragment extends Fragment {

    private FragmentProductAddBinding binding;
    private ProductModel updateModel;
    private ProductViewModel viewModel;
    private Type type;

    public static ProductAddFragment newInstance(Type type, ProductModel model) {
        ProductAddFragment f = new ProductAddFragment();
        Bundle args = new Bundle();
        args.putSerializable("type", type);
        args.putString("product", new Gson().toJson(model));
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductAddBinding.inflate(getLayoutInflater(), container, false);


        ((MainActivity) getActivity()).setAddVisiblity(View.GONE);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(ProductViewModel.class);
        ((MainActivity) getActivity()).SetAppbar(View.GONE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).back();
            }
        });
        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAndUpdateData();
            }
        });

        init();


        return binding.getRoot();


    }

    public void init() {

        Bundle args = getArguments();
        if (args != null) {
            type = (Type) args.getSerializable("type");

            if (type == Type.UPDATE) {
                updateModel = new Gson().fromJson(args.getString("product"), ProductModel.class);
                Log.i("zcxvxv", "init: " + updateModel);
                binding.etRate.setText(updateModel.getPrice());
                binding.etDescription.setText(updateModel.getDescription());
                binding.etName.setText(updateModel.getName());
                binding.btnDone.setText("Update");
                binding.textchange.setText("Update Product");


            }
        }
    }

    private void insertAndUpdateData() {

        String name = binding.etName.getText().toString();
        String des = binding.etDescription.getText().toString();
        String rate = binding.etRate.getText().toString();
        if (validate()) {
            UserModel model = SharedPrefsManager.getInstance().getObject("Users_info", UserModel.class);
            Log.i("sfsfsfs", "insertAndUpdateData: " + "addds" + model);
            if (type == Type.ADD) {
                ProductModel models = new ProductModel(0, name, rate, des, model.getU_id());
                Log.i("xfvxvxv", "insertAndUpdateData: " + models.toString());
                viewModel.addProduct(models);
                Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_LONG).show();
                requireActivity().onBackPressed();
            } else {
                ProductModel updateModel = new ProductModel(this.updateModel.getP_id(), name, rate, des, model.getU_id());
                viewModel.updateProduct(updateModel);
                Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_LONG);
                Log.i("dszfs", "insertAndUpdateData: " + updateModel);
                requireActivity().onBackPressed();
            }

        }
    }

    private boolean validate() {

        if (TextUtils.isEmpty(binding.etName.getText().toString().replace(" ", ""))) {
            Snackbar.make(binding.getRoot(), "Enter Name", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etRate.getText().toString().replace(" ", ""))) {
            Snackbar.make(binding.getRoot(),
                    "enter rate",
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etDescription.getText().toString().replace(" ", ""))) {
            Snackbar.make(binding.getRoot(), "Enter description", Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}