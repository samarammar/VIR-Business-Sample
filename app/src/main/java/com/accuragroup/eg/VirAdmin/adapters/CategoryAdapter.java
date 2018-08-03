package com.accuragroup.eg.VirAdmin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.models.Responces.Category;

import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {


    private List<Category> categoryList;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public CategoryAdapter(List<Category> categories, Context context, OnItemClickListener listener) {
        this.categoryList = categories;
        this.context = context;
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new CategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {
        holder.bind(categoryList.get(position), listener);
        Category category = categoryList.get(position);
        holder.tvName.setText(category.getCatName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_item);

        }

        public void bind(final Category item, final CategoryAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Category item);
    }
}
