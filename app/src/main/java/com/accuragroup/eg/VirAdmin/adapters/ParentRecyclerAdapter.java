package com.accuragroup.eg.VirAdmin.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.interfaces.OnItemRemovedListener;
import com.accuragroup.eg.VirAdmin.interfaces.OnItemsClickListener;
import com.accuragroup.eg.VirAdmin.utils.DialogUtils;
import com.accuragroup.eg.VirAdmin.utils.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sayed on 5/11/16.
 */
public abstract class ParentRecyclerAdapter<Item> extends RecyclerView.Adapter<ParentRecyclerViewHolder> {
    protected ParentRecyclerAdapter adapter;
    protected Context context;
    protected List<Item> data;
    protected int layoutId;
    protected OnItemsClickListener itemClickListener;
    protected OnItemRemovedListener itemRemovedListener;
    protected ProgressDialog progressDialog;
    // used to hold connection handlers that should be cancelled when destroyed

    public ParentRecyclerAdapter(Context context, List<Item> data) {
        this(context, data, -1);
    }

    public ParentRecyclerAdapter(Context context, List<Item> data, int layoutId) {
        inti(context, data, layoutId);
    }

    public ParentRecyclerAdapter(Context context, Item[] data) {
        this(context, data, -1);
    }

    public ParentRecyclerAdapter(Context context, Item[] data, int layoutId) {
        inti(context, Arrays.asList(data), layoutId);
    }

    private void inti(Context context, List<Item> data, int layoutId) {
        this.context = context;
        this.data = data;
        this.layoutId = layoutId;

        adapter = this;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemsClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public void setOnItemRemovedListener(OnItemRemovedListener itemRemovedListener) {
        this.itemRemovedListener = itemRemovedListener;
    }

    public int getResColor(int id) {
        return context.getResources().getColor(id);
    }

    protected void logE(String msg) {
        Utils.logE(msg);
    }

    protected String getString(int resId) {
        return context.getString(resId);
    }

    public void showProgressDialog() {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        } else {
            progressDialog = DialogUtils.showProgressDialog(context, R.string.please_wait_dotted);
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        // cancel requests if found

        super.onDetachedFromRecyclerView(recyclerView);
    }

    public void removeItem(int position) {

        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        notifyDataSetChanged();

        if (itemRemovedListener != null) {
            itemRemovedListener.onItemRemoved(position);
        }
    }

    public Context getContext() {
        return context;
    }
}
