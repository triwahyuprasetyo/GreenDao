package com.sebangsa.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sebangsa.greendao.db.DaoMaster;
import com.sebangsa.greendao.db.DaoSession;
import com.sebangsa.greendao.db.Item;
import com.sebangsa.greendao.db.ItemDao;
import com.sebangsa.greendao.db.Order;
import com.sebangsa.greendao.db.OrderDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG = "ITEM ACTIVITY";
    private ItemDao itemDao;
    private OrderDao orderDao;
    private Button buttonInsert;
    private Button buttonRetrieve;
    private Button buttonDelete;
    private Button buttonUpdate;
    private String[] itemName = new String[]{"manggis", "sawi", "ciki", "sapu", "aqua", "shampoo"};
    private String[] itemDescription = new String[]{"buah", "sayur", "snack", "alat bersih2", "minuman", "alat mandi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        initDb();

        buttonInsert = (Button) findViewById(R.id.buttonInsertI);
        buttonInsert.setOnClickListener(this);
        buttonRetrieve = (Button) findViewById(R.id.buttonRetrieveI);
        buttonRetrieve.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.buttonDeleteI);
        buttonDelete.setOnClickListener(this);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdateI);
        buttonUpdate.setOnClickListener(this);

        setTitle("GreenDao - Item");
    }

    public void initDb() {
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(this, "greendao.db", null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession daoSession = master.newSession(); //Creates Session session
        itemDao = daoSession.getItemDao();
        orderDao = daoSession.getOrderDao();
    }

    private void insertItem() {
        List<Item> itemList = populateItemData();
        if (itemList.size() > 0) {
            itemDao.insertInTx(itemList);
            Log.d(LOG, "Insert Item success");
        } else {
            Log.d(LOG, "Insert Item sbelum berhasil");
        }
    }

    private List<Item> populateItemData() {
        QueryBuilder qb = orderDao.queryBuilder();
        List<Order> orderList = qb.list();
        if (orderList.size() > 0) {
            List<Item> itemList = new ArrayList<>();
            Item item;
            int itemNameSize = itemName.length;
            for (int i = 0; i < itemNameSize; i++) {
                item = new Item();
                item.setItemName(itemName[i]);
                item.setItemDescription(itemDescription[i]);
                itemList.add(item);
            }
            return itemList;
        } else return new ArrayList<Item>();
    }

    private List<Order> getOrderList() {
        QueryBuilder qb = orderDao.queryBuilder();
        List<Order> orderList = qb.list();
        if (orderList.size() > 0) {
            return orderList;
        } else return new ArrayList<Order>();
    }

    @Override
    public void onClick(View view) {
        int size;
        List<Order> orderList;
        switch (view.getId()) {
            case R.id.buttonInsertI:
                insertItem();
                break;
            case R.id.buttonRetrieveI:
//                orderList = getOrderList();
//                if (orderList.size() > 0) {
//                    for (Order order : orderList) {
//                        Log.i(LOG, order.getId() + " - amount " + order.getAmount() + " - date " + order.getOrderDate().toString() + " - Cust Id " + order.getCustomerId());
//                    }
//                } else {
//                    Log.i(LOG, "order list kosong");
//                }
                break;
            case R.id.buttonDeleteI:
//                orderList = getOrderList();
//                size = orderList.size();
//                if (size > 0) {
//                    orderDao.delete(orderList.get(size - 1));
//                    Log.i(LOG, "delete order berhasil");
//                } else {
//                    Log.i(LOG, "order list kosong");
//                }
                break;
            case R.id.buttonUpdateI:
//                orderList = getOrderList();
//                size = orderList.size();
//                if (size > 0) {
//                    QueryBuilder qb = customerDao.queryBuilder();
//                    List<Customer> customerList = qb.list();
//                    if (customerList.size() > 0) {
//                        Order o = orderList.get(size - 1);
//                        o.setAmount(1000);
//                        o.setCustomerId(customerList.get(customerList.size() - 1).getId());
//                        orderDao.update(o);
//                        Log.i(LOG, "update Order berhasil");
//                    } else {
//                        Log.i(LOG, "Customer list kosong, tidak bisa ganti Customer di order");
//                    }
//                } else {
//                    Log.i(LOG, "Customer list kosong");
//                }
                break;
        }
    }
}
