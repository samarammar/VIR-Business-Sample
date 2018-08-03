package com.accuragroup.eg.VirAdmin.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.adapters.SubCatAdapter;
import com.accuragroup.eg.VirAdmin.connections.ServiceApi;
import com.accuragroup.eg.VirAdmin.models.Request.SubCatRequest;
import com.accuragroup.eg.VirAdmin.models.Responces.SubCat;
import com.accuragroup.eg.VirAdmin.models.Responces.SubCategoryResponse;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sayed} on 11/5/2017.
 */

public class SubCategoryDialog extends DialogFragment {

    List<SubCat> subCatList = new ArrayList<>();
    private RecyclerView recyclerView;
    SubCatAdapter subCategoryAdapter;
    private ProgressBar progressBar;
    protected OnDialogClickedListener callback = null;

    private RecyclerView.LayoutManager layoutManager;
    int catId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_government, container, false);
//        Bundle bundle = getArguments();
//        catId = bundle.getInt("catId");
        catId= Utils.getCachedInt(getActivity(),"main_category",0);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_government);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        this.getDialog().setTitle(getString(R.string.sub_category));
        progressBar.setVisibility(View.VISIBLE);
        if (Utils.hasConnection(getActivity())) {
            loadData();
        }else {
            Utils.showShortToast(getActivity(), getActivity().getString(R.string.connectionFieldTryAgain));
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }
        return view;
    }

    private void loadData() {
        SubCatRequest params = new SubCatRequest();
        params.setName(Const.ACCESS_KEY);
        params.setAccessPassWord(Const.ACCESS_PASSWORD);
        params.setLangu(Utils.getCachedString(getActivity(), Const.Language, ""));
        params.setCatId(catId);

        progressBar.setVisibility(View.VISIBLE);
        Ion.with(getActivity())
                .load(ServiceApi.Service.getCategories.getBaseService())
                .setJsonPojoBody(params)
                .as(new TypeToken<SubCategoryResponse>() {
                })
                .setCallback(new FutureCallback<SubCategoryResponse>() {
                    @Override
                    public void onCompleted(Exception e, SubCategoryResponse result) {

                        if (e != null) {
                            Utils.showShortToast(getActivity(), getActivity().getString(R.string.connectionFieldTryAgain));
                            Utils.showShortToast(getActivity(), String.valueOf(e));
                            progressBar.setVisibility(View.GONE);
                        } else {
                            if (result.getStatus() == 200) {
                                if (getActivity()!=null) {
                                    subCategoryAdapter = new SubCatAdapter(result.getReturn(), getActivity(), new SubCatAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(SubCat item) {
                                            callback.onDialogClicked(Integer.valueOf(item.getCatId()), item.getCatName());
                                            dismiss();
                                        }

                                    });
                                    layoutManager = new LinearLayoutManager(getActivity());
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(subCategoryAdapter);
                                }else {
                                    recyclerView.setVisibility(View.GONE);
                                    //Utils.showShortToast(getActivity(), getActivity().getString(R.string.connectionFieldTryAgain));
                                }
                                progressBar.setVisibility(View.GONE);

                            } else if(result.getStatus() == 407) {
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.GONE);
                                callback.onDialogClicked(407, "407");
                            }else {
                                Utils.showShortToast(getActivity(), getActivity().getString(R.string.connectionFieldTryAgain));
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        }
                    }
                });

    }

    public void setOnDialogClickedListener(OnDialogClickedListener l) {
        callback = l;
    }

    public interface OnDialogClickedListener {
        public abstract void onDialogClicked(int position, String name);
    }

}
