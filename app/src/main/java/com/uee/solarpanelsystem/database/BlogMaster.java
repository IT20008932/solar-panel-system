package com.uee.solarpanelsystem.database;

import android.provider.BaseColumns;

public class BlogMaster {

    public BlogMaster() {
    }

    public static class BlogT implements BaseColumns {
        public static final String TABLE_NAME = "Blog";
        public static final String COLUMN_NAME_BLOG_ID = "blog_id";
        public static final String COLUMN_NAME_BLOG_NAME = "blog_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }


}
