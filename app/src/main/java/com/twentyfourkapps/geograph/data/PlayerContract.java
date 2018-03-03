package com.twentyfourkapps.geograph.data;

import android.provider.BaseColumns;

/**
 * Created by sergi on 24/01/2017.
 */

public class PlayerContract {
        public static abstract class PlayerEntry implements BaseColumns {
                public static final String TABLE_NAME ="players";

                public static final String ID = "id";
                public static final String PLAYER_NAME = "player_name";
                public static final String BEST_SCORE_0_easy = "best_score_0_easy";
                public static final String BEST_SCORE_1_easy = "best_score_1_easy";
                public static final String BEST_SCORE_2_easy = "best_score_2_easy";
                public static final String BEST_SCORE_3_easy = "best_score_3_easy";
                public static final String BEST_SCORE_0_medium = "best_score_0_medium";
                public static final String BEST_SCORE_1_medium = "best_score_1_medium";
                public static final String BEST_SCORE_2_medium = "best_score_2_medium";
                public static final String BEST_SCORE_3_medium = "best_score_3_medium";
                public static final String BEST_SCORE_0_hard = "best_score_0_hard";
                public static final String BEST_SCORE_1_hard = "best_score_1_hard";
                public static final String BEST_SCORE_2_hard = "best_score_2_hard";
                public static final String BEST_SCORE_3_hard = "best_score_3_hard";
                public static final String NO_LOGIN = "NO_LOGIN";
        }

}

