package com.twentyfourkapps.geograph.data;

import android.provider.BaseColumns;

/**
 * Created by sergi on 24/01/2017.
 */

public class CountryContract {
        public static abstract class CountryEntry implements BaseColumns {
        public static final String TABLE_NAME ="countries";

        public static final String ID = "id";
        public static final String FILE_NAME = "file_name";
        public static final String ANSWER = "answer";
        public static final String ANSWER_ES = "answer_es";
        public static final String CAPITAL = "capital";
        public static final String CAPITAL_ES = "capital_es";
        public static final String DIFFICULTY = "difficulty";
    }

}

