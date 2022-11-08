package com.uee.solarpanelsystem.database;

import android.provider.BaseColumns;

public class PackageMaster {
    public PackageMaster() {
    }

    public static class PackagesT implements BaseColumns {
        public static final String TABLE_NAME = "package";
        public static final String COLUMN_NAME_PACKAGE_ID = "package_id";
        public static final String COLUMN_NAME_PACKAGE_NAME = "package_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_SP_QTY = "sp_qty";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_BATTERIES = "batteries";
        public static final String COLUMN_NAME_BACKUP = "backup";
        public static final String COLUMN_NAME_CONNECTION = "connection";
        public static final String COLUMN_NAME_CH_CURRENT = "ch_current";
        public static final String COLUMN_NAME_CH_TIME = "ch_time";
    }
}