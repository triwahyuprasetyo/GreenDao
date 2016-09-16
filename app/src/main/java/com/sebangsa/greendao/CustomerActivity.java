package com.sebangsa.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sebangsa.greendao.db.Address;
import com.sebangsa.greendao.db.AddressDao;
import com.sebangsa.greendao.db.Customer;
import com.sebangsa.greendao.db.CustomerDao;
import com.sebangsa.greendao.db.DaoMaster;
import com.sebangsa.greendao.db.DaoSession;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG = "CUSTOMER ACTIVITY";
    private CustomerDao customerDao;
    private AddressDao addressDao;
    private Button buttonInsert;
    private Button buttonRetrieve;
    private Button buttonDelete;
    private Button buttonUpdate;
    private String[] namaCustomer = new String[]{"tri", "wahyu", "prasetyo", "why", "prast", "tri wahyu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initDb();

        buttonInsert = (Button) findViewById(R.id.buttonInsertC);
        buttonInsert.setOnClickListener(this);
        buttonRetrieve = (Button) findViewById(R.id.buttonRetrieveC);
        buttonRetrieve.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.buttonDeleteC);
        buttonDelete.setOnClickListener(this);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdateC);
        buttonUpdate.setOnClickListener(this);
        setTitle("GreenDao - Customer");
    }

    public void initDb() {
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(this, "greendao.db", null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession daoSession = master.newSession(); //Creates Session session
        customerDao = daoSession.getCustomerDao();
        addressDao = daoSession.getAddressDao();
    }

    private void insertCustomer() {
        List<Customer> customerList = populateCustomerData();
        if (customerList.size() > 0) {
            customerDao.insertInTx(customerList);
            Log.d(LOG, "Insert customer success");
        } else {
            Log.d(LOG, "Insert belum berhasil");
        }
    }

    private List<Customer> populateCustomerData() {
        QueryBuilder qb = addressDao.queryBuilder();
        List<Address> addressList = qb.list();
        if (addressList.size() > 0) {
            List<Customer> customerList = new ArrayList<>();
            Customer customer;
            int nameCustomersize = namaCustomer.length;
            for (int i = 0; i < nameCustomersize; i++) {
                customer = new Customer();
                customer.setCustomerName(namaCustomer[i]);
                customer.setAddress(addressList.get(i));
                customerList.add(customer);
            }
            return customerList;
        } else return new ArrayList<Customer>();
    }

    private List<Customer> getCustomerList() {
        QueryBuilder qb = customerDao.queryBuilder();
        List<Customer> customerList = qb.list();
        if (customerList.size() > 0) {
            return customerList;
        } else return new ArrayList<Customer>();
    }

    @Override
    public void onClick(View view) {
        int size;
        List<Customer> customerList;
        switch (view.getId()) {
            case R.id.buttonInsertC:
                insertCustomer();
                break;
            case R.id.buttonRetrieveC:
                customerList = getCustomerList();
                if (customerList.size() > 0) {
                    for (Customer customer : customerList) {
                        Log.i(LOG, customer.getId() + " - " + customer.getCustomerName() + " - " + customer.getAddress().getAddress());
                    }
                } else {
                    Log.i(LOG, "Customer list kosong");
                }
                break;
            case R.id.buttonDeleteC:
//                addressList = getAddressList();
//                size = addressList.size();
//                if (size > 0) {
//                    addressDao.delete(addressList.get(size - 1));
//                    Log.i("ADDRESS", "delete address berhasil");
//                } else {
//                    Log.i("ADDRESS", "address list kosong");
//                }
                break;
            case R.id.buttonUpdateC:
//                addressList = getAddressList();
//                size = addressList.size();
//                if (size > 0) {
//                    Address a = addressList.get(size - 1);
//                    a.setAddress("Tokyo");
//                    addressDao.update(a);
//                    Log.i("ADDRESS", "update address berhasil");
//                } else {
//                    Log.i("ADDRESS", "address list kosong");
//                }
                break;
        }
    }
}
