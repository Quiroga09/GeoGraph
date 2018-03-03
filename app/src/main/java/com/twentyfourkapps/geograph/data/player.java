package com.twentyfourkapps.geograph.data;

import java.util.UUID;

/**
 * Entidad country
 */
public class player {
    private String id;
    private String player_name;
    private String best_score_0_easy;
    private String best_score_1_easy;
    private String best_score_2_easy;
    private String best_score_3_easy;
    private String best_score_0_medium;
    private String best_score_1_medium;
    private String best_score_2_medium;
    private String best_score_3_medium;
    private String best_score_0_hard;
    private String best_score_1_hard;
    private String best_score_2_hard;
    private String best_score_3_hard;
    private String NO_LOGIN;

    public player(String name, String best_score) {
        this.id = UUID.randomUUID().toString();
        this.player_name = player_name;
        this.best_score_0_easy = best_score_0_easy;
        this.best_score_1_easy = best_score_1_easy;
        this.best_score_2_easy = best_score_2_easy;
        this.best_score_3_easy = best_score_3_easy;
        this.best_score_0_medium = best_score_0_medium;
        this.best_score_1_medium = best_score_1_medium;
        this.best_score_2_medium = best_score_2_medium;
        this.best_score_3_medium = best_score_3_medium;
        this.best_score_0_hard = best_score_0_hard;
        this.best_score_1_hard = best_score_1_hard;
        this.best_score_2_hard = best_score_2_hard;
        this.best_score_3_hard = best_score_3_hard;
        this.NO_LOGIN = NO_LOGIN;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return player_name;
    }

    public String getBest_score_0_easy() {
        return best_score_0_easy;
    }
    public String getBest_score_1_easy() {
        return best_score_1_easy;
    }
    public String getBest_score_2_easy() {
        return best_score_2_easy;
    }
    public String getBest_score_3_easy() {
        return best_score_3_easy;
    }
    public String getBest_score_0_medium() {
        return best_score_0_medium;
    }
    public String getBest_score_1_medium() {
        return best_score_1_medium;
    }
    public String getBest_score_2_medium() {
        return best_score_2_medium;
    }
    public String getBest_score_3_medium() {
        return best_score_3_medium;
    }
    public String getBest_score_0_hard() {
        return best_score_0_hard;
    }
    public String getBest_score_1_hard() {
        return best_score_1_hard;
    }
    public String getBest_score_2_hard() {
        return best_score_2_hard;
    }
    public String getBest_score_3_hard() {
        return best_score_3_hard;
    }
    public String NO_LOGIN(){return NO_LOGIN;}
}