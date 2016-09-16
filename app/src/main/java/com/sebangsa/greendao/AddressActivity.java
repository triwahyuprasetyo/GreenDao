package com.sebangsa.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sebangsa.greendao.db.Address;
import com.sebangsa.greendao.db.AddressDao;
import com.sebangsa.greendao.db.DaoMaster;
import com.sebangsa.greendao.db.DaoSession;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {
    private AddressDao addressDao;
    private Button buttonInsert;
    private Button buttonRetrieve;
    private Button buttonDelete;
    private Button buttonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initDb();

        buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(this);
        buttonRetrieve = (Button) findViewById(R.id.buttonRetrieve);
        buttonRetrieve.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);
        setTitle("GreenDao - Address");
    }
    public void initDb() {
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(this, "greendao.db", null); //create database db file if not exist
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession daoSession = master.newSession(); //Creates Session session
        addressDao = daoSession.getAddressDao();
    }

    private void insertAddress() {
        List<String> addressList = new ArrayList<String>();
        addressList.add("Dagen");
        addressList.add("Suruh");
        addressList.add("Tasikmadu");
        addressList.add("Karanganyar");
        addressList.add("Jawa Tengah");
        addressList.add("Indonesia");

        Address address;
        for (String a : addressList) {
            address = new Address();
            address.setAddress(a);
            addressDao.insert(address);
            Log.d("DaoExample", "Inserted new note, ID: " + address.getId());
        }
    }

    private List<Address> getAddressList() {
        QueryBuilder qb = addressDao.queryBuilder();
        List<Address> addressList = qb.list();
        if (addressList.size() > 0) {
            return addressList;
        } else return new ArrayList<Address>();
    }

    @Override
    public void onClick(View view) {
        int size;
        List<Address> addressList;
        switch (view.getId()) {
            case R.id.buttonInsert:
                insertAddress();
                break;
            case R.id.buttonRetrieve:
                addressList = getAddressList();
                if (addressList.size() > 0) {
                    for (Address address : addressList) {
                        Log.i("ADDRESS", address.getId() + " - " + address.getAddress());
                    }
                } else {
                    Log.i("ADDRESS", "address list kosong");
                }
                break;
            case R.id.buttonDelete:
                addressList = getAddressList();
                size = addressList.size();
                if (size > 0) {
                    addressDao.delete(addressList.get(size - 1));
                    Log.i("ADDRESS", "delete address berhasil");
                } else {
                    Log.i("ADDRESS", "address list kosong");
                }
                break;
            case R.id.buttonUpdate:
                addressList = getAddressList();
                size = addressList.size();
                if (size > 0) {
                    Address a = addressList.get(size - 1);
                    a.setAddress("Tokyo");
                    addressDao.update(a);
                    Log.i("ADDRESS", "update address berhasil");
                } else {
                    Log.i("ADDRESS", "address list kosong");
                }
                break;
        }
    }
}
