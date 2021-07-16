package com.codeinger.technorizentask.ui.product.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codeinger.technorizentask.R;
import com.codeinger.technorizentask.databinding.ProductlistBinding;
import com.codeinger.technorizentask.model.ProductModel;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListAdapter_VIew> {
    private List<ProductModel> list;
   private Context  context;
    private Callback callback;

    public ProductListAdapter(List<ProductModel> list, Context  context,Callback callback) {
        this.list=list;
        this.context=context;
        this.callback=callback;
    }

    @Override
    public ProductListAdapter_VIew onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.productlist, parent, false);
        return new ProductListAdapter_VIew(view);
    }

    @Override
    public void onBindViewHolder( ProductListAdapter.ProductListAdapter_VIew holder, int position) {

        ProductModel currentItem = list.get(position);

        holder.name.setText(currentItem.getName());
        holder.description.setText(currentItem.getDescription());
        holder.rate.setText(currentItem.getPrice());

         holder.options.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 showPopUpMenu(v,position);
             }
         });



    }

    private void showPopUpMenu(View view, int position) {
        final ProductModel currentitem = list.get(position);
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuDelete:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    AlertDialog deleteDialog =  alertDialogBuilder.setTitle("Are you sure you want to delete this Product").setMessage("sure to delete").
                            setPositiveButton("yes", (dialog, which) -> {
                                callback.delete(currentitem.getP_id(),currentitem);
                            })
                            .setNegativeButton("no", (dialog, which) -> dialog.cancel()).create();
                    deleteDialog.show();
                    break;
                case R.id.menuUpdate:
                    callback.update(currentitem.getP_id(),currentitem);
                    break;

            }
            return false;
        });
        popupMenu.show();
    }


    private void delete(ProductModel currentitem) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductListAdapter_VIew extends RecyclerView.ViewHolder {

        private TextView name,rate,description;
        private ImageView options;

        public ProductListAdapter_VIew(View itemView) {
            super(itemView);
            options=itemView.findViewById(R.id.options);
            name=itemView.findViewById(R.id.name);
            rate=itemView.findViewById(R.id.rate);
            description=itemView.findViewById(R.id.description);

        }



    }

    public void setdata(List<ProductModel> models){
        this.list=models;
        notifyDataSetChanged();

    }

    public interface Callback{
        public void delete(int position,ProductModel item);
        public void update(int position,ProductModel item);
    }
}
