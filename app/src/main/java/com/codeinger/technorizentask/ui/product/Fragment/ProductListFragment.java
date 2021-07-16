package com.codeinger.technorizentask.ui.product.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeinger.technorizentask.databinding.FragmentProductListBinding;
import com.codeinger.technorizentask.model.ProductModel;
import com.codeinger.technorizentask.ui.user.MainActivity;
import com.codeinger.technorizentask.ui.product.ProductViewModel;
import com.codeinger.technorizentask.ui.product.adapter.ProductListAdapter;
import com.codeinger.technorizentask.utils.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private FragmentProductListBinding binding;
    private ProductListAdapter adapter;
    private List<ProductModel> list=new ArrayList<>();
    private ProductViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProductListBinding.inflate(getLayoutInflater(), container, false);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) getContext()).get(ProductViewModel.class);

        ((MainActivity)getActivity()).setAddVisiblity(View.VISIBLE);
        ((MainActivity)getActivity()).SetAppbar(View.VISIBLE);

                binding.rvCatList.setLayoutManager(new LinearLayoutManager(getContext()));

                viewModel.liveData.observe(getViewLifecycleOwner(),models -> {
                    adapter.setdata(models);
                    adapter.notifyDataSetChanged();
                });

        adapter=new ProductListAdapter(list, getContext(), new ProductListAdapter.Callback() {

            @Override
            public void delete(int position, ProductModel item) {
                viewModel.deleteProduct(item);
            }

            @Override
            public void update(int position, ProductModel item) {
                Log.i("axvxcxvd", "update: "+item);
                ((MainActivity)getActivity()).replace(ProductAddFragment.newInstance(Type.UPDATE,item),"ProductAddFragment");
            }
        });
        binding.rvCatList.setAdapter(adapter);



        return binding.getRoot();
    }
}