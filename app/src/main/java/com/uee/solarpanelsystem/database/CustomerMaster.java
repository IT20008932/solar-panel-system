package com.uee.solarpanelsystem.database;

import android.provider.BaseColumns;

public class CustomerMaster {
    public CustomerMaster() {
    }

    public static class CustomersT implements BaseColumns {
        public static final String TABLE_NAME = "customer";
        public static final String COLUMN_NAME_CUSTOMER_ID = "cus_id";
        public static final String COLUMN_NAME_PACKAGE_ID = "package_id";
        public static final String COLUMN_NAME_CUSTOMER_NAME = "cus_name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_CONTACT_NO = "contact_mp";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}