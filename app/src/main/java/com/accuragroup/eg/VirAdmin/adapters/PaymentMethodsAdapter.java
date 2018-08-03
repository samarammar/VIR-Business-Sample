package com.accuragroup.eg.VirAdmin.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.models.MethodsModel;

import java.util.List;

/**
 * Created by Apex on 12/11/2017.
 */

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.MyViewHolder> {

    private List<MethodsModel> methods;
    private Context context;
    private LayoutInflater inflater;
    private PaymentMethodsAdapter.OnItemClickListener listener;

    public PaymentMethodsAdapter(List<MethodsModel> methods, Context context, PaymentMethodsAdapter.OnItemClickListener onItemClickListener) {
        this.methods = methods;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.listener = onItemClickListener;
    }

    @Override
    public PaymentMethodsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_text, parent, false);
        return new PaymentMethodsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaymentMethodsAdapter.MyViewHolder holder, int position) {
        holder.bind(methods.get(position), listener);
        MethodsModel category = methods.get(position);
        holder.tvName.setText(category.getMethodName());

    }

    @Override
    public int getItemCount() {
        return methods.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_item);
        }

        public void bind(final MethodsModel item, final PaymentMethodsAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(item);

                }

            });

        }
    }

    public interface OnItemClickListener {

        void onItemClick(MethodsModel item);

    }
}
