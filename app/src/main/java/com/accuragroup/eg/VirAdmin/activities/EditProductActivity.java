package com.accuragroup.eg.VirAdmin.activities;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.accuragroup.eg.VirAdmin.dialogs.CategoryDialog;
import com.accuragroup.eg.VirAdmin.dialogs.SubCategoryDialog;
import com.accuragroup.eg.VirAdmin.utils.BitmapUtils;
import com.accuragroup.eg.VirAdmin.utils.Utils;
import com.accuragroup.eg.VirAdmin.Const;
import com.accuragroup.eg.VirAdmin.R;
import com.accuragroup.eg.VirAdmin.connections.ServiceApi;
import com.accuragroup.eg.VirAdmin.models.CategoryModel;
import com.accuragroup.eg.VirAdmin.utils.DialogUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apex on 8/10/2017.
 */


public class EditProductActivity extends PicPickerActivity implements CompoundButton.OnCheckedChangeListener {

    private Button btnSave, btnCancel;
    private EditText etProductName, etProductDesc;
    private MaterialEditText  etProductPrice, etProductSale;
    private CheckBox chMen, chWomen, chKids,  chHide, chNotExist;
    private ImageView ivPro1, ivPro2, ivPro3;

    private int productId;


    private List<Integer> integers;
    private List<Integer> images = new ArrayList<>();

    private int catID, subCatId;
    private File imageFile;


    int flag;
    int exist = 0;
    int hide = 0;
    int image1 = 0, image2 = 0, image3 = 0;
    private String ownerid,userType,empId;

    int productIsExists,productIsHidden,productIsFeatured;
    String productName,productPrice,productSalePrice
            ,productDesc,productCategory,productSubCategory;
    CategoryDialog categoryDialog;
    SubCategoryDialog subCategoryDialog;

    EditText etmainCat,etsubCat;
    String [] photosIds;
    ArrayList<String>photos;
    ArrayList<Integer>classifications;
    android.app.FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        setTitle(getString(R.string.edit_product));
        getDataFromIntent();
        initiViews();
        initListeners();
        enableBackButton();
        hideKeyboard();
        setPeoductData();
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        productId = intent.getIntExtra("id", 0);
        productName = intent.getStringExtra("product_name");
        productPrice = intent.getStringExtra("price");
        productSalePrice = intent.getStringExtra("sale_price");
        productIsExists = intent.getIntExtra("is_exists",0);
        productIsHidden = intent.getIntExtra("is_hidden",0);
        productDesc = intent.getStringExtra("description");
        productCategory = intent.getStringExtra("product_category");
        productSubCategory = intent.getStringExtra("product_subcategory");
        catID = intent.getIntExtra("main_category",0);
        subCatId = intent.getIntExtra("sub_category",0);
        photosIds=intent.getStringArrayExtra("product_gallery_ids");
        photos=intent.getStringArrayListExtra("product_gallery");
        classifications=intent.getIntegerArrayListExtra("product_classifications");

        images.add(0,0);
        images.add(1,0);
        images.add(2,0);
    }

    private void setPeoductData(){
        etProductName.setText(productName);
        etmainCat.setText(productCategory);
        etsubCat.setText(productSubCategory);
        etProductPrice.setText(productPrice);
        etProductSale.setText(productSalePrice);
        etProductDesc.setText(productDesc);

        //notexist
        if (productIsExists == 0) {
            chNotExist.setChecked(false);
        } else {
            chNotExist.setChecked(true);
        }

        //hide
        if (productIsHidden == 0) {
            chHide.setChecked(false);
        } else {
            chHide.setChecked(true);
        }

        if(photosIds.length!=0) {
            for (int i = 0; i < photosIds.length; i++) {
                String id = photosIds[i].toString();
                if (id != null && i == 0) {
                    try {
                    image1 = Integer.parseInt(photosIds[0]);
                    }catch (NumberFormatException e){
                        System.out.println("not a number");
                    }
                    Log.wtf("Image 1 ", String.valueOf(image1));
                    images.set(0, image1);
                } else {

                }

                if (id != null && i == 1) {
                    try {
                    image2 = Integer.parseInt(photosIds[1]);
                }catch (NumberFormatException e){
                    System.out.println("not a number");
                }
                Log.wtf("Image 2 ", String.valueOf(image2));
                    images.set(1, image2);
                } else {

                }

                if (id != null && i == 2) {
                    try {
                    image3 = Integer.parseInt(photosIds[2]);
                }catch (NumberFormatException e){
                    System.out.println("not a number");
                }
                    Log.wtf("Image 2 ", String.valueOf(image3));
                    images.set(2, image3);
                } else {

                }
            }
        }


        if (photos.size()!=0){
        for (int i = 0; i < photos.size(); i++) {
            String image = photos.get(i);
            if (image != null && i == 0) {
                Picasso.with(EditProductActivity.this).load(photos.get(0)).into(ivPro1);

            } else {

            }
            if (image != null && i == 1) {
                Picasso.with(EditProductActivity.this).load(photos.get(1)).into(ivPro2);


            } else {

            }
            if (image != null && i == 2) {
                Picasso.with(EditProductActivity.this).load(photos.get(2)).into(ivPro3);


            } else {

            }
        }
        }else {
            Log.wtf("Wrong", "turn");
        }

        for (int i = 0; i < classifications.size(); i++) {
            int proClass = classifications.get(i);
            if (proClass != 0 && proClass==52) {
                chMen.setChecked(true);
            } else {

            }

            if (proClass != 0 && proClass==53) {
                chWomen.setChecked(true);
            } else {

            }
            if (proClass != 0 && proClass==54) {

                chKids.setChecked(true);
            } else {

            }
        }

    }


    private void initiViews() {

        fragmentManager = getFragmentManager();

        Log.wtf("Category Id", String.valueOf(catID));
        userType = Utils.getCachedString(this, Const.USER_TYPE, "");
        if (userType.equals(Const.USER_Emp)) {
            ownerid = String.valueOf(Utils.getCachedInt(this, Const.OWNER_ID, 0));
            empId=Utils.getCachedString(this, "USERID", "");
        } else {
            ownerid = Utils.getCachedString(this, "USERID", "");
        }
        integers = new ArrayList<>();


        ///EditText
        etProductDesc = (EditText) findViewById(R.id.et_add_product_desc);
        etProductPrice = (MaterialEditText) findViewById(R.id.et_add_product_price);
        etProductSale = (MaterialEditText) findViewById(R.id.et_add_product_sale);
        etProductName = (EditText) findViewById(R.id.et_add_product_name);
        etmainCat=(EditText)findViewById(R.id.et_category_product);
        etsubCat=(EditText)findViewById(R.id.et_subcategory_product);

        //Buttons
        btnSave = (Button) findViewById(R.id.btn_save_product);
        btnCancel = (Button) findViewById(R.id.btn_cancel_product);


        //ImageView
        ivPro1 = (ImageView) findViewById(R.id.iv_image_product1);
        ivPro2 = (ImageView) findViewById(R.id.iv_image_product2);
        ivPro3 = (ImageView) findViewById(R.id.iv_image_product3);


        //CheckBox
        chWomen = (CheckBox) findViewById(R.id.ch_women);
        chKids = (CheckBox) findViewById(R.id.ch_kids);
        chMen = (CheckBox) findViewById(R.id.ch_men);
        //chFeature = (CheckBox) findViewById(R.id.ch_menfeature);
        chNotExist = (CheckBox) findViewById(R.id.ch_not_exist);
        chHide = (CheckBox) findViewById(R.id.ch_hide);


        //Dialogs
        categoryDialog=new CategoryDialog();
        subCategoryDialog=new SubCategoryDialog();

    }

    private void initListeners(){
        etmainCat.setOnClickListener(this);
        etsubCat.setOnClickListener(this);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        ivPro1.setOnClickListener(this);
        ivPro2.setOnClickListener(this);
        ivPro3.setOnClickListener(this);

        chWomen.setOnCheckedChangeListener(this);
        chKids.setOnCheckedChangeListener(this);
        chMen.setOnCheckedChangeListener(this);
        //chFeature.setOnCheckedChangeListener(this);
        chNotExist.setOnCheckedChangeListener(this);
        chHide.setOnCheckedChangeListener(this);

        categoryDialog.setOnDialogClickedListener(new CategoryDialog.OnDialogClickedListener() {
            @Override
            public void onDialogClicked(int position, String name) {
                catID = position;
                etmainCat.setText(name);
                Utils.cacheInt(EditProductActivity.this,"main_category",catID);
            }
        });

        subCategoryDialog.setOnDialogClickedListener(new SubCategoryDialog.OnDialogClickedListener() {
            @Override
            public void onDialogClicked(int position, String name) {
                if(position==407){
                    subCatId=0;
                    etsubCat.setText(getString(R.string.chose_sub_cat));
                }else {
                    etsubCat.setText(name);
                    subCatId = position;
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_image_product1:
                flag = 0;
                Log.wtf("Flag", String.valueOf(flag));
                choseImage();
                break;
            case R.id.iv_image_product2:
                flag = 1;
                Log.wtf("Flag", String.valueOf(flag));
                choseImage();
                break;
            case R.id.iv_image_product3:
                flag = 2;
                Log.wtf("Flag", String.valueOf(flag));
                choseImage();
                break;
            case R.id.btn_save_product:
                if (Valid(etProductName, etProductPrice, integers, catID, subCatId)) {
                    String productName = etProductName.getText().toString();
                    String productDesc = etProductDesc.getText().toString();
                    String productPrice = etProductPrice.getText().toString();
                    String productSale;
                    if (etProductSale.getText().toString().trim().isEmpty()) {
                        productSale = "";
                    } else {
                        productSale = etProductSale.getText().toString().trim();
                    }
                    upDateProduct(productId, productName, productDesc,  Utils.replaceArabicNumbers(productPrice), Utils.replaceArabicNumbers(productSale)
                            , catID, subCatId, hide,  exist, integers, images);
                }
                break;
            case R.id.btn_cancel_product:
                finish();
                break;
            case R.id.et_category_product:
                categoryDialog.show(fragmentManager,"");
                break;
            case R.id.et_subcategory_product:
                subCategoryDialog.show(fragmentManager,"");
                break;
        }
    }


    private void upDateProduct(int proId, String productName, String productDesc, String productPrice, String productSale
            , int catID, int subCat, int hide, int exist, List<Integer> integers, List<Integer> images) {

        JsonObject data = new JsonObject();
        data.addProperty("productName", productName);
        data.addProperty("ownerId", Integer.valueOf(ownerid.replaceAll("^\"|\"$", "")));
        if (userType.equals(Const.USER_Emp)) {
             data.addProperty("employeeId",  Integer.valueOf(empId.replaceAll("^\"|\"$", "")));
        }else{}
        data.addProperty("productId", proId);
        data.addProperty("cat", catID);
        data.addProperty("sub_cat", subCat);
        data.addProperty("hide", hide);
        data.addProperty("images", android.text.TextUtils.join(",", images));
        data.addProperty("section", android.text.TextUtils.join(",", integers));
        data.addProperty("exist", exist);
        data.addProperty("price", productPrice);
        data.addProperty("salePrice", productSale);
        data.addProperty("description", productDesc);
        data.addProperty("access_key", Const.ACCESS_KEY);
        data.addProperty("access_password", Const.ACCESS_PASSWORD);

        Log.i("catId", String.valueOf(catID));
        Log.i("sub_cat", String.valueOf(subCat));

        showProgressDialog();

        Ion.with(EditProductActivity.this)
                .load(ServiceApi.Service.updateProduct.getBaseService())
                .setJsonObjectBody(data)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e != null) {
                    showProgressDialog();
                    Utils.showShortToast(EditProductActivity.this, String.valueOf(e));
                    Log.wtf("Error", String.valueOf(e));
                    e.printStackTrace();
                    hideProgressDialog();
                } else {

                    String s = result.get("status").toString();
                    String d = result.get("message").toString();
                    if (s.equals("200")) {
                        Utils.showShortToast(EditProductActivity.this, getString(R.string.product_updated));
                        hideProgressDialog();
                        finish();
                    }else if(s.equals("411")){
                        hideProgressDialog();
                        Utils.clearAll(EditProductActivity.this);
                        finishAffinity();
                    } else {
                        Utils.showShortToast(EditProductActivity.this, String.valueOf(d));
                        hideProgressDialog();
                    }

                }

            }
        });


    }

    boolean isValid = false;

    private boolean Valid(EditText name, MaterialEditText price, List<Integer> sectionList, int category, int subcat) {

        if (Utils.isEmpty(name)) {
            name.setError(getString(R.string.must_product));
            name.requestFocus();
        } else if (Utils.isEmpty(price)) {
            price.setError(getString(R.string.mustprice));
            price.requestFocus();
        } else if (sectionList.size() == 0) {
            Utils.showShortToast(EditProductActivity.this, getString(R.string.select_product_section));
        } else if (category == 0) {
            Utils.showShortToast(EditProductActivity.this, getString(R.string.select_product_category));
        } else if (subcat == 0) {
            Utils.showShortToast(EditProductActivity.this, getString(R.string.select_sub_cat_first));
        } else if (images.size() == 0||image1==0&&image2==0&&image3==0) {

            Utils.showShortToast(EditProductActivity.this, getString(R.string.select_product_image));
        } else {
            isValid = true;
        }

        return isValid;
    }

    private void choseImage() {
        String[] options;
        if (flag == 0 ) {
            options = new String[]{
                    getString(R.string.from_gallery),
                    getString(R.string.from_camera),
            };
        } else if (flag == 1 ) {
            options = new String[]{
                    getString(R.string.from_gallery),
                    getString(R.string.from_camera),
            };
        } else if (flag == 2 ) {
            options = new String[]{
                    getString(R.string.from_gallery),
                    getString(R.string.from_camera),
            };
        } else {
            options = new String[]{
                    getString(R.string.from_gallery),
                    getString(R.string.from_camera),
                    getString(R.string.remove_image)
            };
        }

        // show list dialog
        DialogUtils.showListDialog(this, options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // switch the selected item
                switch (which) {
                    case 0:
                        setPickerAspects(Const.IMG_ASPECT_X_PROFILE, Const.IMG_ASPECT_Y_PROFILE);
                        setPickerMaxDimen(Const.MAX_IMG_DIMEN_PROFILE);
                        pickFromGallery(0, true);
                        break;
                    case 1:
                        setPickerAspects(Const.IMG_ASPECT_X_PROFILE, Const.IMG_ASPECT_Y_PROFILE);
                        setPickerMaxDimen(Const.MAX_IMG_DIMEN_PROFILE);
                        captureFromCamera(0, true);
                        break;
                    case 2:
                        removeImage();
                        break;
                }
            }
        });

    }

    private void removeImage() {
        if (flag == 0) {
            ivPro1.setImageResource(R.drawable.ic_addimage);
            images.set(0,0);
            Log.wtf("image1", String.valueOf(images));
            image1 = 0;
        } else if (flag == 1) {
            ivPro2.setImageResource(R.drawable.ic_addimage);
            images.set(1,0);
            Log.wtf("image1", String.valueOf(images));
            image2 = 0;
        } else if (flag == 2) {
            ivPro3.setImageResource(R.drawable.ic_addimage);
            images.set(2,0);
            Log.wtf("image1", String.valueOf(images));
            image3 = 0;
        }
    }


    @Override
    public void onImageReady(int requestCode, File image) {
        this.imageFile = image;
        // put new photo in RoundImage variable
        if (image.length() > 100) {
            if (flag == 0) {
                Picasso.with(this).load(image).placeholder(R.drawable.ic_addimage).resize(100, 100)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(ivPro1);
                uploadImageMultipart();


            } else if (flag == 1) {
                Picasso.with(this).load(image).placeholder(R.drawable.ic_addimage).resize(100, 100)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(ivPro2);
                uploadImageMultipart();

            } else if (flag == 2) {
                Picasso.with(this).load(image).placeholder(R.drawable.ic_addimage).resize(100, 100)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(ivPro3);
                uploadImageMultipart();

            } else {

            }
        } else {
            Utils.showShortToast(EditProductActivity.this, getString(R.string.image_small));
        }

    }

    private void uploadImageMultipart() {
        showProgressDialog();

        Ion.with(EditProductActivity.this)
                .load(ServiceApi.Service.uploadImageMultipart.getBaseService())
                .setMultipartFile("imageData",imageFile)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e != null) {
                    Utils.showShortToast(EditProductActivity.this, getString(R.string.connectionFieldTryAgain));
                    Log.wtf(Const.LOG_TAG, String.valueOf(e));
                    e.printStackTrace();
                    hideProgressDialog();
                } else {
                    String s = result.get("status").getAsString();
                    int id = result.get("return").getAsInt();
                    Log.wtf("productId", String.valueOf(id));
                    showProgressDialog();

                    if (s.equals("200") && flag == 0) {
                        image1 = id;
                        showProgressDialog();
                        images.set(0,id);
                        hideProgressDialog();

                    } else if (s.equals("200") && flag == 1) {
                        showProgressDialog();
                        image2 = id;
                        images.set(1,id);
                        hideProgressDialog();


                    } else if (s.equals("200") && flag == 2) {
                        showProgressDialog();
                        image3 = id;
                        images.set(2,id);
                        hideProgressDialog();

                    } else {

                    }

                    hideProgressDialog();
                }

            }
        });
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {

            if (buttonView.getId() == R.id.ch_kids) {
                integers.add(54);
                Log.wtf("Sections", String.valueOf(integers));
            } else if (buttonView.getId() == R.id.ch_women) {
                integers.add(53);
                Log.wtf("Sections", String.valueOf(integers));

            } else if (buttonView.getId() == R.id.ch_men) {
                integers.add(52);
                Log.wtf("Sections", String.valueOf(integers));
            } else if (buttonView.getId() == R.id.ch_hide) {
                hide = 1;
                Log.wtf("hide", String.valueOf(hide));
            } else if (buttonView.getId() == R.id.ch_not_exist) {
                exist = 1;
                Log.wtf("exist", String.valueOf(exist));
            }
        } else {
            if (buttonView.getId() == R.id.ch_kids) {
                buttonView.setChecked(false);
                integers.remove(Integer.valueOf(54));
                Log.wtf("Sections", String.valueOf(integers));

            } else if (buttonView.getId() == R.id.ch_men) {
                buttonView.setChecked(false);
                integers.remove(Integer.valueOf(52));
                Log.wtf("Sections", String.valueOf(integers));


            } else if (buttonView.getId() == R.id.ch_women) {
                buttonView.setChecked(false);
                integers.remove(Integer.valueOf(53));
                Log.wtf("Sections", String.valueOf(integers));


            } else if (buttonView.getId() == R.id.ch_hide) {
                hide = 0;
                Log.wtf("hide", String.valueOf(hide));

            } else if (buttonView.getId() == R.id.ch_not_exist) {
                exist = 0;
                Log.wtf("exist", String.valueOf(exist));
            }

        }
    }

}
