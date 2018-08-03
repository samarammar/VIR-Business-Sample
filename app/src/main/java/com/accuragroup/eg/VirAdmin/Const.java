package com.accuragroup.eg.VirAdmin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apex on 7/9/2017.
 */

public class Const {


    public static final String Charts_URL = "http://vir-eg.net/mobile-charts/";

    // App level constants:--------------
    public static final String LOG_TAG = "Vmall";
    public static final String SHARED_PREFERENCES_FILE_NAME = "Vmall";
    public static final String APP_FILES_DIR = "/.Vmall";


    // Server Constants:--------------------
    public static final int SER_CODE_200 = 200;
    public static final String SER_DATE_FORMAT = "yyyy/MM/dd";
    public static final String SER_TIME_FORMAT = "hh:mm:ss";
    public static final String Authorize = "Authorization";
    public static final String TokenKey = "Bearer" + " ";
    public static final String ACCESS_KEY = "KHJHs40255SWA02adeeed22W";
    public static final String ACCESS_PASSWORD = "Ijv112dQsaspdnb52WSdf32fd21sd";


    // login
    public static final String UserInfo = "userInfo";
    public static final String UserName = "name";
    public static final String UserPassword = "password";
    public static final String UserEmail = "email";
    public static final String UserPhone = "phone";
    public static final String UserImage = "image";
    public static final String UserStatus = "status";
    public static final String UserRole = "role";
    public static final String UserId = "userId";
    public static final String UserToken = "authToken";
    public static final String UserAddress = "userAddress";
    public static final String UserCoordinates = "userCoordinates";
    public static final String UserWaitingProduct = "waitingProduct";
    public static final String UserWaitingServices = "waitingServices";
    public static final String Visitor = "00000000000000000000";
    public static final String RegionId = "regionId";
    public static final String CityId = "cityId";

    public static final String NewAddress = "newAddress";
    public static final String NewAddressOrder = "newAddressOrder";
    public static final String AddressType = "addressType";
    public static final String TotalPrice = "totalPrice";


    //-------
    public static final String CategryId = "categoryId";
    public static final String Title = "title";
    public static final String SubCategoryId = "SubcategoryId";
    public static final String ProductInfo = "productInfo";
    public static final String ProductType = "productType";

    public static final String ProductNormal = "productNormal";
    public static final String ProductFavourite = "productFavourite";
    public static final String OrderReq = "orderReq";

    public static final String Home = "home";
    public static final String Cart = "cart";
    public static final String Favourite = "Favourite";
    public static final String Offer = "Offer";
    public static final String OrderStatus = "orderStatus";
    public static final String Flag = "flag";
    public static final String ImageLink = "http://miwah.me";
    public static final String Language = "language";
    public static final String Float = "float";
    public static final String Integer = "integer";

    // Address
    public static final String Latitude = "latitude";
    public static final String Longitude = "longitude";
    public static final String Address = "Address";
    public static final String AddressId = "AddressId";
    public static final String Phone = "Phone";

    public static final String NormalOrder = "normal";
    public static final String QuickOrder = "quick";

    // Images uploading:-------------------
    public static final int IMG_ASPECT_X_PROFILE = 1;
    public static final int IMG_ASPECT_Y_PROFILE = 1;
    public static final int MAX_IMG_DIMEN_PROFILE = 500;
    public static final int IMG_ASPECT_Question = 1;
    public static final int IMG_ASPECT_Y_TEAM = 1;
    public static final int MAX_IMG_DIMEN_TEAM = 500;

    public static final String KEY_CATEGORY = "category";
    public static final String USER_TYPE = "userType";


    //user types
    public static final String USER_Default = "user";
    public static final String USER_IMAGE = "user_image";
    public static final String USER_OWNER = "owner";
    public static final String USER_Emp = "employee";
    public static final String OWNER_ID = "ownerId";
    public static final String GovernmentID = "governmentId";
    public static final String CityID = "cityId";
    public static final String ZoneId = "zoneId";
    public static final String CatId = "catID";
    public static final String SubCat = "subCat";
    public static final String Rate = "rate";
    public static final String Section = "section";
    public static final String Max = "max";
    public static final String Min = "min";
    public static final String KeyWord = "key";

    //Product Details
    public static final String ProductName = "ProductName";
    public static final String productId = "productId";
    public static final String ProductOwner = "ProductOwner";
    public static final String ProductCategory = "ProductCategory";
    public static final String ProductSubCategory = "ProductSubCategory";
    public static final String ProductSalePrice = "ProductSalePrice";
    public static final String ProductPrice = "ProductPrice";
    public static final String ProductRate = "ProductRate";
    public static final String ProductReviews = "ProductReviews";
    public static final String ProductDescription = "ProductDescription";
    public static final String ProductReserved = "ProductReserved";
    public static final String ProductFavourit = "ProductFavourit";
    public static final String ProductCity = "Productcity";
    public static final String ProductGov = "Productgov";
    public static final String ProductImage = "Productimage";


    public static final String OrderDetails = "OrderDetails";
    public static final String PaymentPlan="PaymentPlan";
    public static final String PaymentExpiration="PaymentExpiration";
    public static final String ExpirationDate="payment_expiration_date";


    ///services
    public static final String REG_TOKEN="REG_TOKEN";

    // permijava.lang.Stringssions

    public static final int PERM_REQ_LOCATION = 2;
    public static final List<Integer> catList = new ArrayList<Integer>();
}
