package com.accuragroup.eg.VirAdmin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.models.Responces.SubCat;

import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class SubCatAdapter extends RecyclerView.Adapter<SubCatAdapter.MyViewHolder> {


    private List<SubCat> subCatList;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public SubCatAdapter(List<SubCat> subCats, Context context, OnItemClickListener listener) {
        this.subCatList = subCats;
        this.context = context;
        this.listener = listener;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.bind(subCatList.get(position), listener);
        SubCat subCat = subCatList.get(position);
        holder.tvName.setText(subCat.getCatName());
    }

    @Override
    public int getItemCount() {
        return subCatList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_item);

        }

        public void bind(final SubCat item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(SubCat item);
    }
}
