package com.sebangsa.greendao.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.sebangsa.greendao.db.Order;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORDER".
*/
public class OrderDao extends AbstractDao<Order, Long> {

    public static final String TABLENAME = "ORDER";

    /**
     * Properties of entity Order.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property OrderDate = new Property(1, java.util.Date.class, "orderDate", false, "ORDER_DATE");
        public final static Property Amount = new Property(2, double.class, "amount", false, "AMOUNT");
        public final static Property CustomerId = new Property(3, long.class, "customerId", false, "CUSTOMER_ID");
    };

    private DaoSession daoSession;

    private Query<Order> customer_OrderListQuery;

    public OrderDao(DaoConfig config) {
        super(config);
    }
    
    public OrderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ORDER_DATE\" INTEGER NOT NULL ," + // 1: orderDate
                "\"AMOUNT\" REAL NOT NULL ," + // 2: amount
                "\"CUSTOMER_ID\" INTEGER NOT NULL );"); // 3: customerId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Order entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getOrderDate().getTime());
        stmt.bindDouble(3, entity.getAmount());
        stmt.bindLong(4, entity.getCustomerId());
    }

    @Override
    protected void attachEntity(Order entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Order readEntity(Cursor cursor, int offset) {
        Order entity = new Order( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            new java.util.Date(cursor.getLong(offset + 1)), // orderDate
            cursor.getDouble(offset + 2), // amount
            cursor.getLong(offset + 3) // customerId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Order entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOrderDate(new java.util.Date(cursor.getLong(offset + 1)));
        entity.setAmount(cursor.getDouble(offset + 2));
        entity.setCustomerId(cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Order entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Order entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "orderList" to-many relationship of Customer. */
    public List<Order> _queryCustomer_OrderList(long customerId) {
        synchronized (this) {
            if (customer_OrderListQuery == null) {
                QueryBuilder<Order> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CustomerId.eq(null));
                customer_OrderListQuery = queryBuilder.build();
            }
        }
        Query<Order> query = customer_OrderListQuery.forCurrentThread();
        query.setParameter(0, customerId);
        return query.list();
    }

}
