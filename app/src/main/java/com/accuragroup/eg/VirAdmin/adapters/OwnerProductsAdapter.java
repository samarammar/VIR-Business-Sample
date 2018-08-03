package com.accuragroup.eg.VirAdmin.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.activities.EditProductActivity;
import com.accuragroup.eg.VirAdmin.connections.ServiceApi;
import com.accuragroup.eg.VirAdmin.models.OwnerProductsModel;
import com.accuragroup.eg.VirAdmin.models.Responces.StoreProduct;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.accuragroup.eg.VirAdmin.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apex on 8/6/2017.
 */

public class OwnerProductsAdapter extends RecyclerView.Adapter<OwnerProductsAdapter.MyViewHolder> {
    View view;
    private List<StoreProduct> productsList;
    private LayoutInflater inflater;
    Context context;

    public OwnerProductsAdapter(List<StoreProduct> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.item_my_shop_products, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final StoreProduct productsModel = productsList.get(position);
        holder.tvProudctName.setText(productsModel.getProductName());
        Picasso.with(context).load(productsModel.getProductImage()).error(R.drawable.default_avatar).into(holder.ivProduct);
        holder.tv_shop_product_code.setText(context.getString(R.string.code)+productsModel.getID());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.are_you_shur_you_want_to_delete_this_product);
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = Integer.parseInt(productsList.get(position).getID());
                       // Utils.showShortToast(context, String.valueOf(id));
                        deleteProduct(id);
                        remove(position);

                    }
                });

                builder.show();
            }
        });

        holder.ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] photosIds = new String[0];
                int id = Integer.parseInt(productsList.get(position).getID());
                Intent settingActivity = new Intent(v.getContext(), EditProductActivity.class);
                settingActivity.putExtra("id", id);
                settingActivity.putExtra("product_name", productsList.get(position).getProductName());
                settingActivity.putExtra("price", productsList.get(position).getPrice());
                settingActivity.putExtra("sale_price", productsList.get(position).getSalePrice());
                settingActivity.putExtra("description", productsList.get(position).getDescription());
               // settingActivity.putExtra("is_featured", productsList.get(position).getIsFeatured());
                settingActivity.putExtra("is_exists", productsList.get(position).getIsExists());
                settingActivity.putExtra("is_hidden", productsList.get(position).getIsHidden());
                settingActivity.putExtra("product_category", productsList.get(position).getProductCategory());
                settingActivity.putExtra("product_subcategory", productsList.get(position).getProductSubcategory());
                settingActivity.putExtra("main_category", productsList.get(position).getMainCategory());
                settingActivity.putExtra("sub_category", productsList.get(position).getSubCategory());

                String imagesIds=productsList.get(position).getProductGalleryIds();
                if (imagesIds.equals("")){
                    settingActivity.putExtra("product_gallery_ids", photosIds);
                }else {
                    photosIds = imagesIds.split(",");
                    settingActivity.putExtra("product_gallery_ids", photosIds);
                    Log.i("photo", String.valueOf(photosIds));
                }
                List<String> images=productsList.get(position).getProductGallery();
                settingActivity.putStringArrayListExtra("product_gallery", (ArrayList<String>) images);

                List<Integer>Classifications=productsList.get(position).getProductClassifications();
                settingActivity.putIntegerArrayListExtra("product_classifications", (ArrayList<Integer>) Classifications);

                v.getContext().startActivity(settingActivity);
            }
        });

    }

    private void deleteProduct(int id) {
        JsonObject data = new JsonObject();

        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("productId", id);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);
        Ion.with(context)
                .load(ServiceApi.Service.deleteProduct.getBaseService())
                .setJsonObjectBody(data)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                String s = result.get("status").toString();
                if (s.equals("200")) {
                    Toast.makeText(context, R.string.deleted, Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void remove(int position) {
        productsList.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProduct, ivSetting, ivDelete;
        private TextView tvProudctName,tv_shop_product_code;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete_product);
            ivProduct = (ImageView) itemView.findViewById(R.id.iv_shop_product);
            ivSetting = (ImageView) itemView.findViewById(R.id.iv_product_settings);
            tvProudctName = (TextView) itemView.findViewById(R.id.tv_shop_product);
            tv_shop_product_code=(TextView)itemView.findViewById(R.id.tv_shop_product_code);
        }
    }
}
