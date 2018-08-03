package com.accuragroup.eg.VirAdmin.models.Entities;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.accuragroup.eg.VirAdmin.models.Entities.CartProducts;
import com.accuragroup.eg.VirAdmin.models.Entities.CompareProduct;

import com.accuragroup.eg.VirAdmin.models.Entities.CartProductsDao;
import com.accuragroup.eg.VirAdmin.models.Entities.CompareProductDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cartProductsDaoConfig;
    private final DaoConfig compareProductDaoConfig;

    private final CartProductsDao cartProductsDao;
    private final CompareProductDao compareProductDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cartProductsDaoConfig = daoConfigMap.get(CartProductsDao.class).clone();
        cartProductsDaoConfig.initIdentityScope(type);

        compareProductDaoConfig = daoConfigMap.get(CompareProductDao.class).clone();
        compareProductDaoConfig.initIdentityScope(type);

        cartProductsDao = new CartProductsDao(cartProductsDaoConfig, this);
        compareProductDao = new CompareProductDao(compareProductDaoConfig, this);

        registerDao(CartProducts.class, cartProductsDao);
        registerDao(CompareProduct.class, compareProductDao);
    }
    
    public void clear() {
        cartProductsDaoConfig.clearIdentityScope();
        compareProductDaoConfig.clearIdentityScope();
    }

    public CartProductsDao getCartProductsDao() {
        return cartProductsDao;
    }

    public CompareProductDao getCompareProductDao() {
        return compareProductDao;
    }

}
