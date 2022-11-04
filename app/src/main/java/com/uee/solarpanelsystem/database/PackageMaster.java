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
        }

    }

