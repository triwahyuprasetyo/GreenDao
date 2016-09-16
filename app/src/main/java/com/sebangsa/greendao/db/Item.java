package com.sebangsa.greendao.db;

import java.util.List;
import com.sebangsa.greendao.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "ITEM".
 */
public class Item {

    private Long id;
    /** Not-null value. */
    private String itemName;
    /** Not-null value. */
    private String itemDescription;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ItemDao myDao;

    private List<OrderItem> orderItemList;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Item() {
    }

    public Item(Long id) {
        this.id = id;
    }

    public Item(Long id, String itemName, String itemDescription) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getItemDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getItemName() {
        return itemName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /** Not-null value. */
    public String getItemDescription() {
        return itemDescription;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<OrderItem> getOrderItemList() {
        if (orderItemList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrderItemDao targetDao = daoSession.getOrderItemDao();
            List<OrderItem> orderItemListNew = targetDao._queryItem_OrderItemList(id);
            synchronized (this) {
                if(orderItemList == null) {
                    orderItemList = orderItemListNew;
                }
            }
        }
        return orderItemList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetOrderItemList() {
        orderItemList = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
