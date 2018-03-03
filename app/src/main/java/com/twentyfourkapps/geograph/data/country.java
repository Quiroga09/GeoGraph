package com.twentyfourkapps.geograph.data;

import java.util.UUID;

/**
 * Entidad country
 */
public class country {
    private String id;
    private String file_name;
    private String answer;
    private String capital;
    private String capital_es;
    private String answer_es;
    private String difficulty;

    public country(String file_name, String answer, String capital, String answer_es, String capital_es, String difficulty) {
        this.id = UUID.randomUUID().toString();
        this.file_name = file_name;
        this.answer = answer;
        this.capital = capital;
        this.capital_es = capital_es;
        this.answer_es = answer_es;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return file_name;
    }
    public String getCapital() {
        return capital;
    }
    public String getCapitales() {
        return capital_es;
    }


    public String getAnswer() {
        return answer;
    }
    public String getAnsweres() {
        return answer_es;
    }
    public String getDifficulty(){return difficulty;}

}