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

    private List<Item> getItemList() {
        QueryBuilder qb = itemDao.queryBuilder();
        List<Item> itemList = qb.list();
        if (itemList.size() > 0) {
            return itemList;
        } else return new ArrayList<Item>();
    }

    @Override
    public void onClick(View view) {
        int size;
        List<Item> itemList;
        switch (view.getId()) {
            case R.id.buttonInsertI:
                insertItem();
                break;
            case R.id.buttonRetrieveI:
                itemList = getItemList();
                if (itemList.size() > 0) {
                    for (Item item : itemList) {
                        Log.i(LOG, item.getId() + " - ItemName " + item.getItemName() + " - ItemDescription " + item.getItemDescription());
                    }
                } else {
                    Log.i(LOG, "item list kosong");
                }
                break;
            case R.id.buttonDeleteI:
                itemList = getItemList();
                size = itemList.size();
                if (size > 0) {
                    itemDao.delete(itemList.get(size - 1));
                    Log.i(LOG, "delete item berhasil");
                } else {
                    Log.i(LOG, "item list kosong");
                }
                break;
            case R.id.buttonUpdateI:
                itemList = getItemList();
                size = itemList.size();
                if (size > 0) {
                    Item i = itemList.get(size - 1);
                    i.setItemName("sepatu");
                    i.setItemDescription("alas kaki");
                    itemDao.update(i);
                    Log.i(LOG, "update item berhasil");
                } else {
                    Log.i(LOG, "Customer item kosong");
                }
                break;
        }
    }
}
