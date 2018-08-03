package com.accuragroup.eg.VirAdmin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.models.Responces.Category;
import com.accuragroup.eg.VirAdmin.models.Responces.OwnerProducts;

import java.util.List;

/**
 * Created by Apex on 12/10/2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {


    private List<OwnerProducts> productList;
    private Context context;
    private LayoutInflater inflater;
    private ProductsAdapter.OnItemClickListener listener;

    public ProductsAdapter(List<OwnerProducts> products, Context context, ProductsAdapter.OnItemClickListener listener) {
        this.productList = products;
        this.context = context;
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new ProductsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.MyViewHolder holder, int position) {

        holder.bind(productList.get(position), listener);
        OwnerProducts product = productList.get(position);
        holder.tvName.setText(product.getProductName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_item);

        }

        public void bind(final OwnerProducts item, final ProductsAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(OwnerProducts item);
    }
}
