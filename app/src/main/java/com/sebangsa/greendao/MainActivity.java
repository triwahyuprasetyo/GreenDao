package com.sebangsa.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAddress;
    private Button buttonCustomer;
    private Button buttonOrder;
    private Button buttonItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAddress = (Button) findViewById(R.id.buttonAddress);
        buttonAddress.setOnClickListener(this);
        buttonCustomer = (Button) findViewById(R.id.buttonCustomer);
        buttonCustomer.setOnClickListener(this);
        buttonOrder = (Button) findViewById(R.id.buttonOrder);
        buttonOrder.setOnClickListener(this);
        buttonItem = (Button) findViewById(R.id.buttonItem);
        buttonItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.buttonAddress:
                i = new Intent(MainActivity.this, AddressActivity.class);
                startActivity(i);
                break;
            case R.id.buttonCustomer:
                i = new Intent(MainActivity.this, CustomerActivity.class);
                startActivity(i);
                break;
            case R.id.buttonOrder:
                i = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(i);
                break;
            case R.id.buttonItem:

                break;
        }
    }
}
