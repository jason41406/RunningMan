package com.example.yu_chan.runningman.data;

import android.provider.BaseColumns;

/**
 * Created by Yu_Chan on 2016/11/3.
 */
public class MemberContract {

    private MemberContract(){}

    public static final class MemberEntry implements BaseColumns{

        public final static String TABLE_NAME = "members";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NAME = "name";

        public final static String COLUMN_GENDER ="gender";

        public final static String COLUMN_AGE = "age";

        public final static String COLUMN_HEIGHT = "height";

        public final static String COLUMN_WEIGHT = "weight";

        public final static String COLUMN_ACCOUNT = "account";

        public final static String COLUMN_PASSWORD = "password";


        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }
}
