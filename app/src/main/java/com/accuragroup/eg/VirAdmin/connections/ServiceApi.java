package com.accuragroup.eg.VirAdmin.connections;

/**
 * Created by Apex on 7/9/2017.
 */

public interface ServiceApi {

    String BASE_API = "http://vir-eg.net/app_services/";

    enum Service {

        getPaymentInfo("getPaymentInfo"),
        deleteProduct("deleteProduct"),
        getCategories("getCategories"),

        getOwnerProducts("getOwnerProducts"),
        addProduct("addProduct"),
        updateProduct("updateProduct"),
        addPayment("addPayment"),
        uploadImageMultipart("uploadImageMultipart"),
        getLastPayment("getLastPayment"),
        getOwnerPaymentMethods("getOwnerPaymentMethods");


        private final String service;

        private Service(String service) {
            this.service = service;
        }

        public String getBaseService() {
            return BASE_API + this.service;
        }


    }
}
