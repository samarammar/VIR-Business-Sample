package com.accuragroup.eg.VirAdmin.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.adapters.CategoryAdapter;
import com.accuragroup.eg.VirAdmin.adapters.PaymentMethodsAdapter;
import com.accuragroup.eg.VirAdmin.connections.ServiceApi;
import com.accuragroup.eg.VirAdmin.models.MethodsModel;
import com.accuragroup.eg.VirAdmin.models.Request.DefaultRequest;
import com.accuragroup.eg.VirAdmin.models.Responces.Category;
import com.accuragroup.eg.VirAdmin.models.Responces.CategoryResponse;
import com.accuragroup.eg.VirAdmin.models.Responces.City;
import com.accuragroup.eg.VirAdmin.models.Responces.CityResponse;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apex on 12/11/2017.
 */

public class PaymentMethodsDialog extends android.app.DialogFragment {

    List<MethodsModel> methodsModels = new ArrayList<>();
    private RecyclerView recyclerView;
    PaymentMethodsAdapter paymentMethodsAdapter;
    private ProgressBar progressBar;
    protected PaymentMethodsDialog.OnDialogClickedListener callback = null;

    private RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_payments, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_payments);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        this.getDialog().setTitle(R.string.choode_payment_method);

        loadData();
        return view;
    }

    private void loadData() {

        JsonObject data = new JsonObject();
        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);
        data.addProperty("langu", Utils.getCachedString(getActivity(), Const.Language, ""));
        data.addProperty("ownerId", Integer.parseInt(Utils.getCachedString(getActivity(), "USERID", "")));

        progressBar.setVisibility(View.VISIBLE);

        Ion.with(getActivity())
                .load(ServiceApi.Service.getOwnerPaymentMethods.getBaseService())
                .setJsonObjectBody(data)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String s = result.get("status").toString();
                        if (e != null) {
                            Utils.showShortToast(getActivity(), getString(R.string.connectionFieldTryAgain));
                            progressBar.setVisibility(View.GONE);
                            e.printStackTrace();
                        } else if (s.equals("200")) {
                            progressBar.setVisibility(View.GONE);
                            JsonArray methods = result.getAsJsonArray("return");
                            methodsModels.clear();
                            for (int i = 0; i < methods.size(); i++) {
                                JsonObject object = methods.get(i).getAsJsonObject();
                                MethodsModel methodsModel = new MethodsModel(object.get("ID").getAsInt(),object.get("method_name").toString(),object.get("setting").toString());
                                methodsModels.add(methodsModel);

                                if (getActivity()!=null)
                                {
                                paymentMethodsAdapter = new PaymentMethodsAdapter(methodsModels, getActivity(), new PaymentMethodsAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(final MethodsModel item) {
                                        callback.onDialogClicked(item.getMethodtId(), item.getMethodName(),item.getMethodSetting());
                                        dismiss();
                                    }
                                });

                            }

                            layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(paymentMethodsAdapter);}
                            progressBar.setVisibility(View.GONE);

                        } else {

                        }

                    }
                });
    }


    public void setOnDialogClickedListener(PaymentMethodsDialog.OnDialogClickedListener l) {
        callback = l;
    }

    public interface OnDialogClickedListener {
        public abstract void onDialogClicked(int position, String name,String setting);
    }
}
