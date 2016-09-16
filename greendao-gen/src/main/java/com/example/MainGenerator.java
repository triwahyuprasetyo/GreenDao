package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MainGenerator {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.sebangsa.greendao.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        Entity customer = addCustomer(schema);
        Entity address = addAddress(schema);
        Entity order = addOrder(schema);
        Entity item = addItem(schema);
        Entity orderItem = addOrderItem(schema);

        Property addressIdProperty = customer.addLongProperty("addressId").getProperty();
        customer.addToOne(address, addressIdProperty, "address");

        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        customer.addToMany(order, customerId);

        Property orderId  = orderItem.addLongProperty("orderId").getProperty();
        Property itemId  = orderItem.addLongProperty("itemId").getProperty();
        orderItem.addToOne(order, orderId);
        orderItem.addToOne(item, itemId);
        order.addToMany(orderItem, orderId);
        item.addToMany(orderItem, itemId);
    }

    private static Entity addCustomer(final Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty().primaryKey().autoincrement();
        customer.addStringProperty("customerName").notNull();
        return customer;
    }

    private static Entity addAddress(final Schema schema) {
        Entity address = schema.addEntity("Address");
        address.addIdProperty().primaryKey().autoincrement();
        address.addStringProperty("address").notNull();
        return address;
    }

    private static Entity addOrder(final Schema schema) {
        Entity order = schema.addEntity("Order");
        order.addIdProperty().primaryKey().autoincrement();
        order.addDateProperty("orderDate").notNull();
        order.addDoubleProperty("amount").notNull();
        return order;
    }

    private static Entity addItem(final Schema schema) {
        Entity item = schema.addEntity("Item");
        item.addIdProperty().primaryKey().autoincrement();
        item.addStringProperty("itemName").notNull();
        item.addStringProperty("itemDescription").notNull();
        return item;
    }

    private static Entity addOrderItem(final Schema schema) {
        Entity orderItem = schema.addEntity("OrderItem");
        orderItem.addIdProperty().primaryKey().autoincrement();
        return orderItem;
    }
}
