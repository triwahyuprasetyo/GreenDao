package com.sebangsa.greendao.db;

import com.sebangsa.greendao.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "ORDER_ITEM".
 */
public class OrderItem {

    private Long id;
    private Long orderId;
    private Long itemId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient OrderItemDao myDao;

    private Order order;
    private Long order__resolvedKey;

    private Item item;
    private Long item__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public OrderItem() {
    }

    public OrderItem(Long id) {
        this.id = id;
    }

    public OrderItem(Long id, Long orderId, Long itemId) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getOrderItemDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /** To-one relationship, resolved on first access. */
    public Order getOrder() {
        Long __key = this.orderId;
        if (order__resolvedKey == null || !order__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrderDao targetDao = daoSession.getOrderDao();
            Order orderNew = targetDao.load(__key);
            synchronized (this) {
                order = orderNew;
            	order__resolvedKey = __key;
            }
        }
        return order;
    }

    public void setOrder(Order order) {
        synchronized (this) {
            this.order = order;
            orderId = order == null ? null : order.getId();
            order__resolvedKey = orderId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Item getItem() {
        Long __key = this.itemId;
        if (item__resolvedKey == null || !item__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ItemDao targetDao = daoSession.getItemDao();
            Item itemNew = targetDao.load(__key);
            synchronized (this) {
                item = itemNew;
            	item__resolvedKey = __key;
            }
        }
        return item;
    }

    public void setItem(Item item) {
        synchronized (this) {
            this.item = item;
            itemId = item == null ? null : item.getId();
            item__resolvedKey = itemId;
        }
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