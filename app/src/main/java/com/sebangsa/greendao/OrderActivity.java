package com.sebangsa.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sebangsa.greendao.db.Customer;
import com.sebangsa.greendao.db.CustomerDao;
import com.sebangsa.greendao.db.DaoMaster;
import com.sebangsa.greendao.db.DaoSession;
import com.sebangsa.greendao.db.Order;
import com.sebangsa.greendao.db.OrderDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG = "ORDER ACTIVITY";
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private Button buttonInsert;
    private Button buttonRetrieve;
    private Button buttonDelete;
    private Button buttonUpdate;
    private double[] amount = new double[]{5, 3, 7, 2, 6, 9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initDb();

        buttonInsert = (Button) findViewById(R.id.buttonInsertO);
        buttonInsert.setOnClickListener(this);
        buttonRetrieve = (Button) findViewById(R.id.buttonRetrieveO);
        buttonRetrieve.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.buttonDeleteO);
        buttonDelete.setOnClickListener(this);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdateO);
        buttonUpdate.setOnClickListener(this);
    }

    public void initDb() {
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(this, "greendao.db", null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession daoSession = master.newSession(); //Creates Session session
        customerDao = daoSession.getCustomerDao();
        orderDao = daoSession.getOrderDao();
    }

    private void insertOrder() {
        List<Order> orderList = populateOrderData();
        if (orderList.size() > 0) {
            orderDao.insertInTx(orderList);
            Log.d(LOG, "Insert Order success");
        } else {
            Log.d(LOG, "Insert Order sbelum berhasil");
        }
    }

    private List<Order> populateOrderData() {
        QueryBuilder qb = customerDao.queryBuilder();
        List<Customer> customerList = qb.list();
        if (customerList.size() > 0) {
            List<Order> orderList = new ArrayList<>();
            Order order;
            int amountSize = amount.length;
            for (int i = 0; i < amountSize; i++) {
                order = new Order();
                order.setAmount(amount[i]);
                order.setOrderDate(Calendar.getInstance().getTime());
                order.setCustomerId(customerList.get(0).getId());
                orderList.add(order);
            }
            return orderList;
        } else return new ArrayList<Order>();
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
            case R.id.buttonInsertO:
                insertOrder();
                break;
            case R.id.buttonRetrieveO:
                orderList = getOrderList();
                if (orderList.size() > 0) {
                    for (Order order : orderList) {
                        Log.i(LOG, order.getId() + " - amount " + order.getAmount() + " - date " + order.getOrderDate().toString() + " - Cust Id " + order.getCustomerId());
                    }
                } else {
                    Log.i(LOG, "order list kosong");
                }
                break;
            case R.id.buttonDeleteO:
                orderList = getOrderList();
                size = orderList.size();
                if (size > 0) {
                    orderDao.delete(orderList.get(size - 1));
                    Log.i(LOG, "delete order berhasil");
                } else {
                    Log.i(LOG, "order list kosong");
                }
                break;
            case R.id.buttonUpdateO:
                orderList = getOrderList();
                size = orderList.size();
                if (size > 0) {
                    QueryBuilder qb = customerDao.queryBuilder();
                    List<Customer> customerList = qb.list();
                    if (customerList.size() > 0) {
                        Order o = orderList.get(size - 1);
                        o.setAmount(1000);
                        o.setCustomerId(customerList.get(customerList.size() - 1).getId());
                        orderDao.update(o);
                        Log.i(LOG, "update Order berhasil");
                    } else {
                        Log.i(LOG, "Customer list kosong, tidak bisa ganti Customer di order");
                    }
                } else {
                    Log.i(LOG, "Customer list kosong");
                }
                break;
        }
    }
}
