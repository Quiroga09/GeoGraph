package com.twentyfourkapps.geograph;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.twentyfourkapps.geograph.data.CountryContract;
import com.twentyfourkapps.geograph.data.PlayerContract;

/**
 * Created by sergi on 24/01/2017.
 */

public class CountriesDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Geograph.db";

    public CountriesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Comandos SQL

        sqLiteDatabase.execSQL("CREATE TABLE " + CountryContract.CountryEntry.TABLE_NAME + " ("
                + CountryContract.CountryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CountryContract.CountryEntry.ID + " TEXT NOT NULL,"
                + CountryContract.CountryEntry.FILE_NAME + " TEXT NOT NULL,"
                + CountryContract.CountryEntry.ANSWER + " TEXT NOT NULL,"
                + CountryContract.CountryEntry.ANSWER_ES + " TEXT NOT NULL,"
                + CountryContract.CountryEntry.CAPITAL + " TEXT NOT NULL,"
                + CountryContract.CountryEntry.CAPITAL_ES + " TEXT NOT NULL,"
                + CountryContract.CountryEntry.DIFFICULTY + " TEXT NOT NULL,"
                + "UNIQUE (" + CountryContract.CountryEntry.ID + "))");

        sqLiteDatabase.execSQL("CREATE TABLE " + PlayerContract.PlayerEntry.TABLE_NAME + " ("
                + PlayerContract.PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PlayerContract.PlayerEntry.ID + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.PLAYER_NAME + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_0_easy + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_1_easy + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_2_easy + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_3_easy + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_0_medium + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_1_medium + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_2_medium + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_3_medium + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_0_hard + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_1_hard + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_2_hard + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.BEST_SCORE_3_hard + " TEXT NOT NULL,"
                + PlayerContract.PlayerEntry.NO_LOGIN + " TEXT NOT NULL,"
                + "UNIQUE (" + PlayerContract.PlayerEntry.ID + "))");

        fillCountries(sqLiteDatabase);
        fillPlayers(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
        db.execSQL("DROP TABLE IF EXISTS "+ CountryContract.CountryEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ PlayerContract.PlayerEntry.TABLE_NAME);
        onCreate(db);
    }

    public void fillCountries(SQLiteDatabase sqLiteDatabase){
        // Contenedor de valores
        ContentValues values = new ContentValues();
        sqLiteDatabase.beginTransaction();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kabul");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kabul");
        values.put(CountryContract.CountryEntry.ID, "1");
        values.put(CountryContract.CountryEntry.FILE_NAME, "afghanistan");
        values.put(CountryContract.CountryEntry.ANSWER, "Afghanistan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Afganistán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tirana");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Tirana");
        values.put(CountryContract.CountryEntry.ID, "2");
        values.put(CountryContract.CountryEntry.FILE_NAME, "albania");
        values.put(CountryContract.CountryEntry.ANSWER, "Albania");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Albania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Algiers");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Argel");
        values.put(CountryContract.CountryEntry.ID, "3");
        values.put(CountryContract.CountryEntry.FILE_NAME, "algeria");
        values.put(CountryContract.CountryEntry.ANSWER, "Algeria");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Algeria");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Luanda");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Luanda");
        values.put(CountryContract.CountryEntry.ID, "4");
        values.put(CountryContract.CountryEntry.FILE_NAME, "angola");
        values.put(CountryContract.CountryEntry.ANSWER, "Angola");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Angola");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Buenos Aires");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Buenos Aires");
        values.put(CountryContract.CountryEntry.ID, "5");
        values.put(CountryContract.CountryEntry.FILE_NAME, "argentina");
        values.put(CountryContract.CountryEntry.ANSWER, "Argentina");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Argentina");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Yerevan");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ereván");
        values.put(CountryContract.CountryEntry.ID, "6");
        values.put(CountryContract.CountryEntry.FILE_NAME, "armenia");
        values.put(CountryContract.CountryEntry.ANSWER, "Armenia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Armenia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Canberra");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Canberra");
        values.put(CountryContract.CountryEntry.ID, "7");
        values.put(CountryContract.CountryEntry.FILE_NAME, "australia");
        values.put(CountryContract.CountryEntry.ANSWER, "Australia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Australia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Viena");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Viena");
        values.put(CountryContract.CountryEntry.ID, "8");
        values.put(CountryContract.CountryEntry.FILE_NAME, "austria");
        values.put(CountryContract.CountryEntry.ANSWER, "Austria");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Austria");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Baku");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Baku");
        values.put(CountryContract.CountryEntry.ID, "9");
        values.put(CountryContract.CountryEntry.FILE_NAME, "azerbaijan");
        values.put(CountryContract.CountryEntry.ANSWER, "Azerbaijan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Azerbaiyán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Daca");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Daca");
        values.put(CountryContract.CountryEntry.ID, "10");
        values.put(CountryContract.CountryEntry.FILE_NAME, "bangladesh");
        values.put(CountryContract.CountryEntry.ANSWER, "Bangladesh");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Bangladesh");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bridgetown");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bridgetown");
        values.put(CountryContract.CountryEntry.ID, "11");
        values.put(CountryContract.CountryEntry.FILE_NAME, "barbados");
        values.put(CountryContract.CountryEntry.ANSWER, "Barbados");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Barbados");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Minsk");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Minsk");
        values.put(CountryContract.CountryEntry.ID, "12");
        values.put(CountryContract.CountryEntry.FILE_NAME, "belarus");
        values.put(CountryContract.CountryEntry.ANSWER, "Belarus");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Bielorrusia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Brussels");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bruselas");
        values.put(CountryContract.CountryEntry.ID, "13");
        values.put(CountryContract.CountryEntry.FILE_NAME, "belgium");
        values.put(CountryContract.CountryEntry.ANSWER, "Belgium");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Bélgica");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Belmopan");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Belmopán");
        values.put(CountryContract.CountryEntry.ID, "14");
        values.put(CountryContract.CountryEntry.FILE_NAME, "belize");
        values.put(CountryContract.CountryEntry.ANSWER, "Belize");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Belice");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Porto Novo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Porto Novo");
        values.put(CountryContract.CountryEntry.ID, "15");
        values.put(CountryContract.CountryEntry.FILE_NAME, "benin");
        values.put(CountryContract.CountryEntry.ANSWER, "Benin");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Benín");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Timbu");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Timbú");
        values.put(CountryContract.CountryEntry.ID, "16");
        values.put(CountryContract.CountryEntry.FILE_NAME, "bhutan");
        values.put(CountryContract.CountryEntry.ANSWER, "Bhutan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Bután");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Sucre");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Sucre");
        values.put(CountryContract.CountryEntry.ID, "17");
        values.put(CountryContract.CountryEntry.FILE_NAME, "bolivia");
        values.put(CountryContract.CountryEntry.ANSWER, "Bolivia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Bolivia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Sarajevo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Sarajevo");
        values.put(CountryContract.CountryEntry.ID, "18");
        values.put(CountryContract.CountryEntry.FILE_NAME, "bosnia_and_herzegovina");
        values.put(CountryContract.CountryEntry.ANSWER, "Bosnia and Herzegovina");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Bosnia y Herzegovina");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Gaborone");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Gaborone");
        values.put(CountryContract.CountryEntry.ID, "19");
        values.put(CountryContract.CountryEntry.FILE_NAME, "botswana");
        values.put(CountryContract.CountryEntry.ANSWER, "Botswana");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Botsuana");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Rio de Janeiro");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Rio de Janeiro");
        values.put(CountryContract.CountryEntry.ID, "20");
        values.put(CountryContract.CountryEntry.FILE_NAME, "brazil");
        values.put(CountryContract.CountryEntry.ANSWER, "Brazil");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Brasil");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bandar Seri Begawan");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bandar Seri Begawan");
        values.put(CountryContract.CountryEntry.ID, "21");
        values.put(CountryContract.CountryEntry.FILE_NAME, "brunei");
        values.put(CountryContract.CountryEntry.ANSWER, "Brunei");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Brunéi");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Sofia");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Sofía");
        values.put(CountryContract.CountryEntry.ID, "22");
        values.put(CountryContract.CountryEntry.FILE_NAME, "bulgary");
        values.put(CountryContract.CountryEntry.ANSWER, "Bulgary");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Bulgaria");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Uagadugu");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Uagadugú");
        values.put(CountryContract.CountryEntry.ID, "23");
        values.put(CountryContract.CountryEntry.FILE_NAME, "burkina_faso");
        values.put(CountryContract.CountryEntry.ANSWER, "Burkina Faso");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Burkina Faso");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Buyumbura");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Buyumbura");
        values.put(CountryContract.CountryEntry.ID, "24");
        values.put(CountryContract.CountryEntry.FILE_NAME, "burundi");
        values.put(CountryContract.CountryEntry.ANSWER, "Burundi");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Burundi");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Phnom Penh");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nom Pen");
        values.put(CountryContract.CountryEntry.ID, "25");
        values.put(CountryContract.CountryEntry.FILE_NAME, "cambodia");
        values.put(CountryContract.CountryEntry.ANSWER, "Cambodia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Camboya");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Yaounde");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Yaundé");
        values.put(CountryContract.CountryEntry.ID, "26");
        values.put(CountryContract.CountryEntry.FILE_NAME, "cameroon");
        values.put(CountryContract.CountryEntry.ANSWER, "Cameroon");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Camerún");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Ottawa");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ottawa");
        values.put(CountryContract.CountryEntry.ID, "27");
        values.put(CountryContract.CountryEntry.FILE_NAME, "canada");
        values.put(CountryContract.CountryEntry.ANSWER, "Canada");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Canadá");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Praia");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Praia");
        values.put(CountryContract.CountryEntry.ID, "28");
        values.put(CountryContract.CountryEntry.FILE_NAME, "cape_verde");
        values.put(CountryContract.CountryEntry.ANSWER, "Cape Verde");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Cabo Verde");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bangui");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bangui");
        values.put(CountryContract.CountryEntry.ID, "29");
        values.put(CountryContract.CountryEntry.FILE_NAME, "central_african_republic");
        values.put(CountryContract.CountryEntry.ANSWER, "Central African Republic");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República Centroafricana");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Yamena");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Yamena");
        values.put(CountryContract.CountryEntry.ID, "30");
        values.put(CountryContract.CountryEntry.FILE_NAME, "chad");
        values.put(CountryContract.CountryEntry.ANSWER, "Chad");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Chad");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Santiago de Chile");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Santiago de Chile");
        values.put(CountryContract.CountryEntry.ID, "31");
        values.put(CountryContract.CountryEntry.FILE_NAME, "chile");
        values.put(CountryContract.CountryEntry.ANSWER, "Chile");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Chile");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Pekin");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Pekín");
        values.put(CountryContract.CountryEntry.ID, "32");
        values.put(CountryContract.CountryEntry.FILE_NAME, "china");
        values.put(CountryContract.CountryEntry.ANSWER, "China");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "China");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bogota");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bogotá");
        values.put(CountryContract.CountryEntry.ID, "33");
        values.put(CountryContract.CountryEntry.FILE_NAME, "colombia");
        values.put(CountryContract.CountryEntry.ANSWER, "Colombia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Colombia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Brazzaville");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Brazzaville");
        values.put(CountryContract.CountryEntry.ID, "34");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_the_congo");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of the Congo");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República del Congo");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "San Jose");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "San José");
        values.put(CountryContract.CountryEntry.ID, "35");
        values.put(CountryContract.CountryEntry.FILE_NAME, "costa_rica");
        values.put(CountryContract.CountryEntry.ANSWER, "Costa Rica");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Costa Rica");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Zagreb");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Zagreb");
        values.put(CountryContract.CountryEntry.ID, "36");
        values.put(CountryContract.CountryEntry.FILE_NAME, "croatia");
        values.put(CountryContract.CountryEntry.ANSWER, "Croatia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Croacia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "La Habana");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "La Habana");
        values.put(CountryContract.CountryEntry.ID, "37");
        values.put(CountryContract.CountryEntry.FILE_NAME, "cuba");
        values.put(CountryContract.CountryEntry.ANSWER, "Cuba");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Cuba");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Nicosia");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nicosia");
        values.put(CountryContract.CountryEntry.ID, "38");
        values.put(CountryContract.CountryEntry.FILE_NAME, "cyprus");
        values.put(CountryContract.CountryEntry.ANSWER, "Cyprus");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Chipre");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Prague");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Praga");
        values.put(CountryContract.CountryEntry.ID, "39");
        values.put(CountryContract.CountryEntry.FILE_NAME, "czech_republic");
        values.put(CountryContract.CountryEntry.ANSWER, "Czech Republic");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República Checa");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kinsasa");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kinsasa");
        values.put(CountryContract.CountryEntry.ID, "40");
        values.put(CountryContract.CountryEntry.FILE_NAME, "democratic_republic_of_the_congo");
        values.put(CountryContract.CountryEntry.ANSWER, "Democratic Republic of the Congo");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Rep. Democràtica del Congo");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Copenhagen");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Copenhague");
        values.put(CountryContract.CountryEntry.ID, "41");
        values.put(CountryContract.CountryEntry.FILE_NAME, "denmark");
        values.put(CountryContract.CountryEntry.ANSWER, "Denmark");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Dinamarca");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Yibuti");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Yibuti");
        values.put(CountryContract.CountryEntry.ID, "42");
        values.put(CountryContract.CountryEntry.FILE_NAME, "djibouti");
        values.put(CountryContract.CountryEntry.ANSWER, "Djibouti");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Yibuti");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Santo Domingo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Santo Domingo");
        values.put(CountryContract.CountryEntry.ID, "43");
        values.put(CountryContract.CountryEntry.FILE_NAME, "dominican_republic");
        values.put(CountryContract.CountryEntry.ANSWER, "Dominican Republic");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República Dominicana");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Quito");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Quito");
        values.put(CountryContract.CountryEntry.ID, "44");
        values.put(CountryContract.CountryEntry.FILE_NAME, "ecuador");
        values.put(CountryContract.CountryEntry.ANSWER, "Ecuador");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Ecuador");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Cairo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Cairo");
        values.put(CountryContract.CountryEntry.ID, "45");
        values.put(CountryContract.CountryEntry.FILE_NAME, "egypt");
        values.put(CountryContract.CountryEntry.ANSWER, "Egypt");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Egipto");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "San Salvador");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "San Salvador");
        values.put(CountryContract.CountryEntry.ID, "46");
        values.put(CountryContract.CountryEntry.FILE_NAME, "el_salvador");
        values.put(CountryContract.CountryEntry.ANSWER, "El Salvador");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "El Salvador");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Malabo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Malabo");
        values.put(CountryContract.CountryEntry.ID, "47");
        values.put(CountryContract.CountryEntry.FILE_NAME, "equatorial_guinea");
        values.put(CountryContract.CountryEntry.ANSWER, "Equatorial Guinea");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Guinea Equatorial");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Asmara");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Asmara");
        values.put(CountryContract.CountryEntry.ID, "48");
        values.put(CountryContract.CountryEntry.FILE_NAME, "eritrea");
        values.put(CountryContract.CountryEntry.ANSWER, "Eritrea");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Eritrea");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bratislava");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bratislava");
        values.put(CountryContract.CountryEntry.ID, "49");
        values.put(CountryContract.CountryEntry.FILE_NAME, "slovakia");
        values.put(CountryContract.CountryEntry.ANSWER, "Slovakia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Eslovaquia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tallin");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Tallin");
        values.put(CountryContract.CountryEntry.ID, "50");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_estonia");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Estonia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Estonia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Addis Abeba");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Addis Abeba");
        values.put(CountryContract.CountryEntry.ID, "51");
        values.put(CountryContract.CountryEntry.FILE_NAME, "ethiopia");
        values.put(CountryContract.CountryEntry.ANSWER, "Ethiopia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Etiopía");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Thorshavn");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Tórshavn");
        values.put(CountryContract.CountryEntry.ID, "53");
        values.put(CountryContract.CountryEntry.FILE_NAME, "faroe_islands");
        values.put(CountryContract.CountryEntry.ANSWER, "Faroe Islands");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Islas Feroe");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Suva");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Suva");
        values.put(CountryContract.CountryEntry.ID, "54");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_fiji");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Fiji");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Fiyi");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Helsinki");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Helsinki");
        values.put(CountryContract.CountryEntry.ID, "55");
        values.put(CountryContract.CountryEntry.FILE_NAME, "finland");
        values.put(CountryContract.CountryEntry.ANSWER, "Finland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Finlandia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Paris");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "París");
        values.put(CountryContract.CountryEntry.ID, "56");
        values.put(CountryContract.CountryEntry.FILE_NAME, "france");
        values.put(CountryContract.CountryEntry.ANSWER, "France");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Francia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Libreville");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Libreville");
        values.put(CountryContract.CountryEntry.ID, "57");
        values.put(CountryContract.CountryEntry.FILE_NAME, "gabon");
        values.put(CountryContract.CountryEntry.ANSWER, "Gabon");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Gabón");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tbilisi");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Tiflis");
        values.put(CountryContract.CountryEntry.ID, "58");
        values.put(CountryContract.CountryEntry.FILE_NAME, "georgia");
        values.put(CountryContract.CountryEntry.ANSWER, "Georgia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Georgia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Berlin");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Berlín");
        values.put(CountryContract.CountryEntry.ID, "59");
        values.put(CountryContract.CountryEntry.FILE_NAME, "germany");
        values.put(CountryContract.CountryEntry.ANSWER, "Germany");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Alemania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Acra");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Acra");
        values.put(CountryContract.CountryEntry.ID, "60");
        values.put(CountryContract.CountryEntry.FILE_NAME, "ghana");
        values.put(CountryContract.CountryEntry.ANSWER, "Ghana");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Ghana");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Athens");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Atenas");
        values.put(CountryContract.CountryEntry.ID, "61");
        values.put(CountryContract.CountryEntry.FILE_NAME, "greece");
        values.put(CountryContract.CountryEntry.ANSWER, "Greece");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Grecia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Nuuk");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nuuk");
        values.put(CountryContract.CountryEntry.ID, "62");
        values.put(CountryContract.CountryEntry.FILE_NAME, "greenland");
        values.put(CountryContract.CountryEntry.ANSWER, "Greenland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Groenlandia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "St. George's");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "St. George's");
        values.put(CountryContract.CountryEntry.ID, "63");
        values.put(CountryContract.CountryEntry.FILE_NAME, "grenada");
        values.put(CountryContract.CountryEntry.ANSWER, "Grenada");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Grenada");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Guatemala City");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ciudad de Guatemala");
        values.put(CountryContract.CountryEntry.ID, "64");
        values.put(CountryContract.CountryEntry.FILE_NAME, "guatemala");
        values.put(CountryContract.CountryEntry.ANSWER, "Guatemala");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Guatemala");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bissau");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bisáu");
        values.put(CountryContract.CountryEntry.ID, "65");
        values.put(CountryContract.CountryEntry.FILE_NAME, "guinea_bissau");
        values.put(CountryContract.CountryEntry.ANSWER, "Guinea-Bissau");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Guinea-Bisáu");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Conakry");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Conakri");
        values.put(CountryContract.CountryEntry.ID, "66");
        values.put(CountryContract.CountryEntry.FILE_NAME, "guinea");
        values.put(CountryContract.CountryEntry.ANSWER, "Guinea");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Guinea");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Georgetown");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Georgetown");
        values.put(CountryContract.CountryEntry.ID, "67");
        values.put(CountryContract.CountryEntry.FILE_NAME, "guyana");
        values.put(CountryContract.CountryEntry.ANSWER, "Guyana");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Guyana");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Port-au-Prince");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Puerto Príncipe");
        values.put(CountryContract.CountryEntry.ID, "68");
        values.put(CountryContract.CountryEntry.FILE_NAME, "haiti");
        values.put(CountryContract.CountryEntry.ANSWER, "Haiti");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Haití");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tegucigalpa");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Tegucigalpa");
        values.put(CountryContract.CountryEntry.ID, "69");
        values.put(CountryContract.CountryEntry.FILE_NAME, "honduras");
        values.put(CountryContract.CountryEntry.ANSWER, "Honduras");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Honduras");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Budapest");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Budapest");
        values.put(CountryContract.CountryEntry.ID, "70");
        values.put(CountryContract.CountryEntry.FILE_NAME, "hungary");
        values.put(CountryContract.CountryEntry.ANSWER, "Hungary");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Hungría");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Reykjavik");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Reikiavik");
        values.put(CountryContract.CountryEntry.ID, "71");
        values.put(CountryContract.CountryEntry.FILE_NAME, "iceland");
        values.put(CountryContract.CountryEntry.ANSWER, "Iceland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Islandia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "New Delhi");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nueva Delhi");
        values.put(CountryContract.CountryEntry.ID, "72");
        values.put(CountryContract.CountryEntry.FILE_NAME, "india");
        values.put(CountryContract.CountryEntry.ANSWER, "India");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "India");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Jakarta");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Yakarta");
        values.put(CountryContract.CountryEntry.ID, "73");
        values.put(CountryContract.CountryEntry.FILE_NAME, "indonesia");
        values.put(CountryContract.CountryEntry.ANSWER, "Indonesia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Indonesia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tehran");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Teherán");
        values.put(CountryContract.CountryEntry.ID, "74");
        values.put(CountryContract.CountryEntry.FILE_NAME, "iran");
        values.put(CountryContract.CountryEntry.ANSWER, "Iran");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Irán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Baghdad");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Baghdad");
        values.put(CountryContract.CountryEntry.ID, "75");
        values.put(CountryContract.CountryEntry.FILE_NAME, "iraq");
        values.put(CountryContract.CountryEntry.ANSWER, "Iraq");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Iraq");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Dublin");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Dublín");
        values.put(CountryContract.CountryEntry.ID, "76");
        values.put(CountryContract.CountryEntry.FILE_NAME, "ireland");
        values.put(CountryContract.CountryEntry.ANSWER, "Ireland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Irlanda");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Jerusalem");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Jerusalén");
        values.put(CountryContract.CountryEntry.ID, "77");
        values.put(CountryContract.CountryEntry.FILE_NAME, "israel");
        values.put(CountryContract.CountryEntry.ANSWER, "Israel");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Israel");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Rome");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Roma");
        values.put(CountryContract.CountryEntry.ID, "78");
        values.put(CountryContract.CountryEntry.FILE_NAME, "italy");
        values.put(CountryContract.CountryEntry.ANSWER, "Italy");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Italia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Harare");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Harare");
        values.put(CountryContract.CountryEntry.ID, "79");
        values.put(CountryContract.CountryEntry.FILE_NAME, "zimbawe");
        values.put(CountryContract.CountryEntry.ANSWER, "Zimbabwe");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Zimbabue");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Yamoussoukro");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Yamusukro");
        values.put(CountryContract.CountryEntry.ID, "80");
        values.put(CountryContract.CountryEntry.FILE_NAME, "ivory_coast");
        values.put(CountryContract.CountryEntry.ANSWER, "Ivory Coast");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Costa de Marfil");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kingston");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kingston");
        values.put(CountryContract.CountryEntry.ID, "81");
        values.put(CountryContract.CountryEntry.FILE_NAME, "jamaica");
        values.put(CountryContract.CountryEntry.ANSWER, "Jamaica");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Jamaica");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tokyo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Tokio");
        values.put(CountryContract.CountryEntry.ID, "82");
        values.put(CountryContract.CountryEntry.FILE_NAME, "japan");
        values.put(CountryContract.CountryEntry.ANSWER, "Japan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Japón");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Amman");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Amán");
        values.put(CountryContract.CountryEntry.ID, "83");
        values.put(CountryContract.CountryEntry.FILE_NAME, "jordan");
        values.put(CountryContract.CountryEntry.ANSWER, "Jordan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Jordania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Astana");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Astaná");
        values.put(CountryContract.CountryEntry.ID, "84");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kazakhstan");
        values.put(CountryContract.CountryEntry.ANSWER, "Kazakhstan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Kazajistán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Nairobi");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nairobi");
        values.put(CountryContract.CountryEntry.ID, "85");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kenya");
        values.put(CountryContract.CountryEntry.ANSWER, "Kenya");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Kenia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kuwait City");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ciudad de Kuwait");
        values.put(CountryContract.CountryEntry.ID, "86");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kuwait");
        values.put(CountryContract.CountryEntry.ANSWER, "Kuwait");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Kuwait");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Vientiane");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Vientián");
        values.put(CountryContract.CountryEntry.ID, "87");
        values.put(CountryContract.CountryEntry.FILE_NAME, "laos");
        values.put(CountryContract.CountryEntry.ANSWER, "Laos");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Laos");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Riga");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Riga");
        values.put(CountryContract.CountryEntry.ID, "88");
        values.put(CountryContract.CountryEntry.FILE_NAME, "latvia");
        values.put(CountryContract.CountryEntry.ANSWER, "Latvia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Letonia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Beirut");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Beirut");
        values.put(CountryContract.CountryEntry.ID, "89");
        values.put(CountryContract.CountryEntry.FILE_NAME, "lebanon");
        values.put(CountryContract.CountryEntry.ANSWER, "Lebanon");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Líbano");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Monrovia");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Monrovia");
        values.put(CountryContract.CountryEntry.ID, "90");
        values.put(CountryContract.CountryEntry.FILE_NAME, "liberia");
        values.put(CountryContract.CountryEntry.ANSWER, "Liberia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Liberia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tripoli");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Trípoli");
        values.put(CountryContract.CountryEntry.ID, "91");
        values.put(CountryContract.CountryEntry.FILE_NAME, "libya");
        values.put(CountryContract.CountryEntry.ANSWER, "Libya");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Libia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Vilnius");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Vilna");
        values.put(CountryContract.CountryEntry.ID, "92");
        values.put(CountryContract.CountryEntry.FILE_NAME, "lithuania");
        values.put(CountryContract.CountryEntry.ANSWER, "Lithuania");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Lituania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Luxembourg City");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ciudad de Luxemburgo");
        values.put(CountryContract.CountryEntry.ID, "93");
        values.put(CountryContract.CountryEntry.FILE_NAME, "luxembourg");
        values.put(CountryContract.CountryEntry.ANSWER, "Luxembourg");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Luxemburgo");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Antananarivo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Antananarivo");
        values.put(CountryContract.CountryEntry.ID, "94");
        values.put(CountryContract.CountryEntry.FILE_NAME, "madagascar");
        values.put(CountryContract.CountryEntry.ANSWER, "Madagascar");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Madagascar");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kuala Lumpur");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kuala Lumpur");
        values.put(CountryContract.CountryEntry.ID, "95");
        values.put(CountryContract.CountryEntry.FILE_NAME, "malaysia");
        values.put(CountryContract.CountryEntry.ANSWER, "Malaysia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Malasia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Lilongwe");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Lilongüe");
        values.put(CountryContract.CountryEntry.ID, "96");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_malawi");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Malawi");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Malaui");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bamako");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bamako");
        values.put(CountryContract.CountryEntry.ID, "97");
        values.put(CountryContract.CountryEntry.FILE_NAME, "mali");
        values.put(CountryContract.CountryEntry.ANSWER, "Mali");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Mali");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Valletta");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "La Valeta ");
        values.put(CountryContract.CountryEntry.ID, "98");
        values.put(CountryContract.CountryEntry.FILE_NAME, "malta");
        values.put(CountryContract.CountryEntry.ANSWER, "Malta");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Malta");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Nouakchott");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nuakchot");
        values.put(CountryContract.CountryEntry.ID, "100");
        values.put(CountryContract.CountryEntry.FILE_NAME, "islamic_republic_of_mauritania");
        values.put(CountryContract.CountryEntry.ANSWER, "Islamic Rep. of Mauritania");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Rep. Islámica de Mauritania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Port Louis");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Port Louis");
        values.put(CountryContract.CountryEntry.ID, "101");
        values.put(CountryContract.CountryEntry.FILE_NAME, "mauritius");
        values.put(CountryContract.CountryEntry.ANSWER, "Mauritius");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Mauricio");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Ciudad de Mexico");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ciudad de Mexico");
        values.put(CountryContract.CountryEntry.ID, "102");
        values.put(CountryContract.CountryEntry.FILE_NAME, "mexico");
        values.put(CountryContract.CountryEntry.ANSWER, "Mexico");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "México");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Chișinău");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Chisináu");
        values.put(CountryContract.CountryEntry.ID, "103");
        values.put(CountryContract.CountryEntry.FILE_NAME, "moldova");
        values.put(CountryContract.CountryEntry.ANSWER, "Moldova");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Moldavia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Ulaanbaatar");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ulán Bator");
        values.put(CountryContract.CountryEntry.ID, "104");
        values.put(CountryContract.CountryEntry.FILE_NAME, "mongolia");
        values.put(CountryContract.CountryEntry.ANSWER, "Mongolia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Mongolia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Rabat");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Rabat");
        values.put(CountryContract.CountryEntry.ID, "105");
        values.put(CountryContract.CountryEntry.FILE_NAME, "morocco");
        values.put(CountryContract.CountryEntry.ANSWER, "Morocco");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Marruecos");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Maputo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Maputo");
        values.put(CountryContract.CountryEntry.ID, "106");
        values.put(CountryContract.CountryEntry.FILE_NAME, "mozambique");
        values.put(CountryContract.CountryEntry.ANSWER, "Mozambique");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Mozambique");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Naypyidaw");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Naipyidó");
        values.put(CountryContract.CountryEntry.ID, "107");
        values.put(CountryContract.CountryEntry.FILE_NAME, "myanmar");
        values.put(CountryContract.CountryEntry.ANSWER, "Myanmar");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Birmania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Windhoek");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Windhoek");
        values.put(CountryContract.CountryEntry.ID, "108");
        values.put(CountryContract.CountryEntry.FILE_NAME, "namibia");
        values.put(CountryContract.CountryEntry.ANSWER, "Namibia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Namibia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kathmandu");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Katmandú");
        values.put(CountryContract.CountryEntry.ID, "109");
        values.put(CountryContract.CountryEntry.FILE_NAME, "nepal");
        values.put(CountryContract.CountryEntry.ANSWER, "Nepal");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Nepal");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Amsterdam");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ámsterdam");
        values.put(CountryContract.CountryEntry.ID, "110");
        values.put(CountryContract.CountryEntry.FILE_NAME, "netherlands");
        values.put(CountryContract.CountryEntry.ANSWER, "Netherlands");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Holanda");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Wellington");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Wellington");
        values.put(CountryContract.CountryEntry.ID, "111");
        values.put(CountryContract.CountryEntry.FILE_NAME, "new_zeland");
        values.put(CountryContract.CountryEntry.ANSWER, "New Zeland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Nueva Zelanda");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Managua");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Managua");
        values.put(CountryContract.CountryEntry.ID, "112");
        values.put(CountryContract.CountryEntry.FILE_NAME, "nicaragua");
        values.put(CountryContract.CountryEntry.ANSWER, "Nicaragua");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Nicaragua");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Niamey");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Niamey");
        values.put(CountryContract.CountryEntry.ID, "113");
        values.put(CountryContract.CountryEntry.FILE_NAME, "niger");
        values.put(CountryContract.CountryEntry.ANSWER, "Niger");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Níger");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Pyongyang");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Pionyang");
        values.put(CountryContract.CountryEntry.ID, "114");
        values.put(CountryContract.CountryEntry.FILE_NAME, "north_korea");
        values.put(CountryContract.CountryEntry.ANSWER, "North Korea");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Corea del Norte");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Oslo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Oslo");
        values.put(CountryContract.CountryEntry.ID, "115");
        values.put(CountryContract.CountryEntry.FILE_NAME, "norway");
        values.put(CountryContract.CountryEntry.ANSWER, "Norway");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Noruega");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Muscat");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Mascate");
        values.put(CountryContract.CountryEntry.ID, "116");
        values.put(CountryContract.CountryEntry.FILE_NAME, "oman");
        values.put(CountryContract.CountryEntry.ANSWER, "Oman");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Oman");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Islamabad");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Islamabad");
        values.put(CountryContract.CountryEntry.ID, "117");
        values.put(CountryContract.CountryEntry.FILE_NAME, "pakistan");
        values.put(CountryContract.CountryEntry.ANSWER, "Pakistan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Pakistán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Panama City");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ciudad de Panamá");
        values.put(CountryContract.CountryEntry.ID, "118");
        values.put(CountryContract.CountryEntry.FILE_NAME, "panama");
        values.put(CountryContract.CountryEntry.ANSWER, "Panama");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Panamá");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Port Moresby");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Puerto Moresby");
        values.put(CountryContract.CountryEntry.ID, "119");
        values.put(CountryContract.CountryEntry.FILE_NAME, "papua_new_guinea");
        values.put(CountryContract.CountryEntry.ANSWER, "Papua New Guinea");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Papúa Nueva Guinea");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Asuncion");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Asunción");
        values.put(CountryContract.CountryEntry.ID, "120");
        values.put(CountryContract.CountryEntry.FILE_NAME, "paraguay");
        values.put(CountryContract.CountryEntry.ANSWER, "Paraguay");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Paraguay");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Lima");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Lima");
        values.put(CountryContract.CountryEntry.ID, "121");
        values.put(CountryContract.CountryEntry.FILE_NAME, "peru");
        values.put(CountryContract.CountryEntry.ANSWER, "Peru");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Perú");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Manila");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Manila");
        values.put(CountryContract.CountryEntry.ID, "122");
        values.put(CountryContract.CountryEntry.FILE_NAME, "philippines");
        values.put(CountryContract.CountryEntry.ANSWER, "Philippines");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Filipinas");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Warsaw");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Varsovia");
        values.put(CountryContract.CountryEntry.ID, "123");
        values.put(CountryContract.CountryEntry.FILE_NAME, "poland");
        values.put(CountryContract.CountryEntry.ANSWER, "Poland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Polonia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Lisbon");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Lisboa");
        values.put(CountryContract.CountryEntry.ID, "124");
        values.put(CountryContract.CountryEntry.FILE_NAME, "portugal");
        values.put(CountryContract.CountryEntry.ANSWER, "Portugal");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Portugal");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "San Juan");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "San Juan");
        values.put(CountryContract.CountryEntry.ID, "125");
        values.put(CountryContract.CountryEntry.FILE_NAME, "puerto_rico");
        values.put(CountryContract.CountryEntry.ANSWER, "Puerto Rico");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Puerto Rico");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Doha");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Doha");
        values.put(CountryContract.CountryEntry.ID, "126");
        values.put(CountryContract.CountryEntry.FILE_NAME, "qatar");
        values.put(CountryContract.CountryEntry.ANSWER, "Qatar");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Qatar");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Skopje");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Skopie");
        values.put(CountryContract.CountryEntry.ID, "127");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_macedonia");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Macedonia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Macedonia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bucharest");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bucarest");
        values.put(CountryContract.CountryEntry.ID, "128");
        values.put(CountryContract.CountryEntry.FILE_NAME, "romania");
        values.put(CountryContract.CountryEntry.ANSWER, "Romania");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Rumanía");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Moscow");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Moscú");
        values.put(CountryContract.CountryEntry.ID, "129");
        values.put(CountryContract.CountryEntry.FILE_NAME, "russia");
        values.put(CountryContract.CountryEntry.ANSWER, "Russia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Rusia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kigali");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kigali");
        values.put(CountryContract.CountryEntry.ID, "130");
        values.put(CountryContract.CountryEntry.FILE_NAME, "rwanda");
        values.put(CountryContract.CountryEntry.ANSWER, "Rwanda");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Ruanda");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Basseterre");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Basseterre");
        values.put(CountryContract.CountryEntry.ID, "131");
        values.put(CountryContract.CountryEntry.FILE_NAME, "saint_kitts_and_nevis");
        values.put(CountryContract.CountryEntry.ANSWER, "Saint Kitts and Nevis");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "San Cristóbal y Nieves");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "São Tome");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Santo Tomé");
        values.put(CountryContract.CountryEntry.ID, "132");
        values.put(CountryContract.CountryEntry.FILE_NAME, "sao_tome_and_principe");
        values.put(CountryContract.CountryEntry.ANSWER, "Sao Tome and Principe");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Santo Tomé y Príncipe");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Riyadh");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Riad");
        values.put(CountryContract.CountryEntry.ID, "133");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kingdom_of_saudi_arabia");
        values.put(CountryContract.CountryEntry.ANSWER, "Kingdom of Saudi Arabia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Arabia Saudita");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Dakar");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Dakar");
        values.put(CountryContract.CountryEntry.ID, "134");
        values.put(CountryContract.CountryEntry.FILE_NAME, "senegal");
        values.put(CountryContract.CountryEntry.ANSWER, "Senegal");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Senegal");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Belgrade");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Belgrado");
        values.put(CountryContract.CountryEntry.ID, "135");
        values.put(CountryContract.CountryEntry.FILE_NAME, "serbia");
        values.put(CountryContract.CountryEntry.ANSWER, "Serbia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Serbia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Victoria");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Victoria");
        values.put(CountryContract.CountryEntry.ID, "136");
        values.put(CountryContract.CountryEntry.FILE_NAME, "seychelles");
        values.put(CountryContract.CountryEntry.ANSWER, "Seychelles");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Seychelles");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Freetown");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Freetown");
        values.put(CountryContract.CountryEntry.ID, "137");
        values.put(CountryContract.CountryEntry.FILE_NAME, "sierra_leone");
        values.put(CountryContract.CountryEntry.ANSWER, "Sierra Leone");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Sierra Leona");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Singapore");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Singapur");
        values.put(CountryContract.CountryEntry.ID, "138");
        values.put(CountryContract.CountryEntry.FILE_NAME, "singapore");
        values.put(CountryContract.CountryEntry.ANSWER, "Singapore");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Singapur");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Honiara");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Honiara");
        values.put(CountryContract.CountryEntry.ID, "139");
        values.put(CountryContract.CountryEntry.FILE_NAME, "solomon_islands");
        values.put(CountryContract.CountryEntry.ANSWER, "Solomon Islands");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Islas Salomón");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Mogadishu");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Mogadiscio");
        values.put(CountryContract.CountryEntry.ID, "140");
        values.put(CountryContract.CountryEntry.FILE_NAME, "somalia");
        values.put(CountryContract.CountryEntry.ANSWER, "Somalia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Somalia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Pretoria");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Pretoria");
        values.put(CountryContract.CountryEntry.ID, "141");
        values.put(CountryContract.CountryEntry.FILE_NAME, "south_africa");
        values.put(CountryContract.CountryEntry.ANSWER, "South Africa");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Sudáfrica");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Seoul");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Seúl");
        values.put(CountryContract.CountryEntry.ID, "143");
        values.put(CountryContract.CountryEntry.FILE_NAME, "south_korea");
        values.put(CountryContract.CountryEntry.ANSWER, "South Korea");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Corea del Sud");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Juba");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Yuba");
        values.put(CountryContract.CountryEntry.ID, "144");
        values.put(CountryContract.CountryEntry.FILE_NAME, "south_sudan");
        values.put(CountryContract.CountryEntry.ANSWER, "South Sudan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Sudán del Sud");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Madrid");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Madrid");
        values.put(CountryContract.CountryEntry.ID, "145");
        values.put(CountryContract.CountryEntry.FILE_NAME, "spain");
        values.put(CountryContract.CountryEntry.ANSWER, "Spain");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "España");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kotte");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kotte");
        values.put(CountryContract.CountryEntry.ID, "146");
        values.put(CountryContract.CountryEntry.FILE_NAME, "sri_lanka");
        values.put(CountryContract.CountryEntry.ANSWER, "Sri Lanka");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Sri Lanka");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Khartoum");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Jartum");
        values.put(CountryContract.CountryEntry.ID, "147");
        values.put(CountryContract.CountryEntry.FILE_NAME, "sudan");
        values.put(CountryContract.CountryEntry.ANSWER, "Sudan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Sudán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Paramaribo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Paramaribo");
        values.put(CountryContract.CountryEntry.ID, "148");
        values.put(CountryContract.CountryEntry.FILE_NAME, "suriname");
        values.put(CountryContract.CountryEntry.ANSWER, "Suriname");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Surinam");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Mbabane");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Mbabane");
        values.put(CountryContract.CountryEntry.ID, "149");
        values.put(CountryContract.CountryEntry.FILE_NAME, "swaziland");
        values.put(CountryContract.CountryEntry.ANSWER, "Swaziland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Suazilandia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Stockholm");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Estocolmo");
        values.put(CountryContract.CountryEntry.ID, "150");
        values.put(CountryContract.CountryEntry.FILE_NAME, "sweden");
        values.put(CountryContract.CountryEntry.ANSWER, "Sweden");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Suecia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bern");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Berna");
        values.put(CountryContract.CountryEntry.ID, "151");
        values.put(CountryContract.CountryEntry.FILE_NAME, "switzerland");
        values.put(CountryContract.CountryEntry.ANSWER, "Switzerland");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Suiza");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Damascus");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Damasco");
        values.put(CountryContract.CountryEntry.ID, "152");
        values.put(CountryContract.CountryEntry.FILE_NAME, "syria");
        values.put(CountryContract.CountryEntry.ANSWER, "Syria");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Siria");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Taipei");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Taipéi");
        values.put(CountryContract.CountryEntry.ID, "153");
        values.put(CountryContract.CountryEntry.FILE_NAME, "taiwan");
        values.put(CountryContract.CountryEntry.ANSWER, "Taiwan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Taiwán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Dodoma");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Dodoma");
        values.put(CountryContract.CountryEntry.ID, "154");
        values.put(CountryContract.CountryEntry.FILE_NAME, "tanzania");
        values.put(CountryContract.CountryEntry.ANSWER, "Tanzania");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Tanzania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bangkok");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Bangkok");
        values.put(CountryContract.CountryEntry.ID, "155");
        values.put(CountryContract.CountryEntry.FILE_NAME, "thailand");
        values.put(CountryContract.CountryEntry.ANSWER, "Thailand");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Tailandia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Nassau");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nasáu");
        values.put(CountryContract.CountryEntry.ID, "156");
        values.put(CountryContract.CountryEntry.FILE_NAME, "the_bahamas");
        values.put(CountryContract.CountryEntry.ANSWER, "The Bahamas");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Las Bahamas");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Banjul");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Banjul");
        values.put(CountryContract.CountryEntry.ID, "157");
        values.put(CountryContract.CountryEntry.FILE_NAME, "the_gambia");
        values.put(CountryContract.CountryEntry.ANSWER, "The Gambia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "El Gambia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Lome");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Lomé");
        values.put(CountryContract.CountryEntry.ID, "158");
        values.put(CountryContract.CountryEntry.FILE_NAME, "togo");
        values.put(CountryContract.CountryEntry.ANSWER, "Togo");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Togo");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Port of Spain");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Puerto España");
        values.put(CountryContract.CountryEntry.ID, "159");
        values.put(CountryContract.CountryEntry.FILE_NAME, "trinidad_and_tobago");
        values.put(CountryContract.CountryEntry.ANSWER, "Trinidad And Tobago");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Trinidad y Tobago");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tunis");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Túnez");
        values.put(CountryContract.CountryEntry.ID, "160");
        values.put(CountryContract.CountryEntry.FILE_NAME, "tunisia");
        values.put(CountryContract.CountryEntry.ANSWER, "Tunisia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Túnez");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Ankara");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ankara");
        values.put(CountryContract.CountryEntry.ID, "161");
        values.put(CountryContract.CountryEntry.FILE_NAME, "turkey");
        values.put(CountryContract.CountryEntry.ANSWER, "Turkey");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Turquía");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Ashgabat");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Asjabad");
        values.put(CountryContract.CountryEntry.ID, "162");
        values.put(CountryContract.CountryEntry.FILE_NAME, "turkmenistan");
        values.put(CountryContract.CountryEntry.ANSWER, "Turkmenistan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Turkmenistán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kampala");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kampala");
        values.put(CountryContract.CountryEntry.ID, "163");
        values.put(CountryContract.CountryEntry.FILE_NAME, "uganda");
        values.put(CountryContract.CountryEntry.ANSWER, "Uganda");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Uganda");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kiev");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kiev");
        values.put(CountryContract.CountryEntry.ID, "164");
        values.put(CountryContract.CountryEntry.FILE_NAME, "ukraine");
        values.put(CountryContract.CountryEntry.ANSWER, "Ukraine");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Ucrania");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Abu Dhabi");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Abu Dhabi");
        values.put(CountryContract.CountryEntry.ID, "165");
        values.put(CountryContract.CountryEntry.FILE_NAME, "united_arab_emirates");
        values.put(CountryContract.CountryEntry.ANSWER, "United Arab Emirates");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Emiratos Árabes Unidos");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "London");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Londres");
        values.put(CountryContract.CountryEntry.ID, "166");
        values.put(CountryContract.CountryEntry.FILE_NAME, "united_kingdom");
        values.put(CountryContract.CountryEntry.ANSWER, "United Kingdom");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Reino Unido");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Montevideo");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Montevideo");
        values.put(CountryContract.CountryEntry.ID, "167");
        values.put(CountryContract.CountryEntry.FILE_NAME, "uruguay");
        values.put(CountryContract.CountryEntry.ANSWER, "Uruguay");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Uruguay");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Washington  D.C.");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Washington D.C.");
        values.put(CountryContract.CountryEntry.ID, "168");
        values.put(CountryContract.CountryEntry.FILE_NAME, "usa");
        values.put(CountryContract.CountryEntry.ANSWER, "USA");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Estados Unidos");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Tashkent");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Taskent");
        values.put(CountryContract.CountryEntry.ID, "169");
        values.put(CountryContract.CountryEntry.FILE_NAME, "uzbekistan");
        values.put(CountryContract.CountryEntry.ANSWER, "Uzbekistan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Uzbekistán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Port Vila");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Port Vila");
        values.put(CountryContract.CountryEntry.ID, "170");
        values.put(CountryContract.CountryEntry.FILE_NAME, "vanuatu");
        values.put(CountryContract.CountryEntry.ANSWER, "Vanuatu");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Vanuatu");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Caracas");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Caracas");
        values.put(CountryContract.CountryEntry.ID, "171");
        values.put(CountryContract.CountryEntry.FILE_NAME, "venezuela");
        values.put(CountryContract.CountryEntry.ANSWER, "Venezuela");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Venezuela");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Hanoi");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Hanoi");
        values.put(CountryContract.CountryEntry.ID, "172");
        values.put(CountryContract.CountryEntry.FILE_NAME, "vietnam");
        values.put(CountryContract.CountryEntry.ANSWER, "Vietnam");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Vietnam");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Sana'a");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Saná");
        values.put(CountryContract.CountryEntry.ID, "173");
        values.put(CountryContract.CountryEntry.FILE_NAME, "yemen");
        values.put(CountryContract.CountryEntry.ANSWER, "Yemen");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Yemen");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Lusaka");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Lusaka");
        values.put(CountryContract.CountryEntry.ID, "174");
        values.put(CountryContract.CountryEntry.FILE_NAME, "zambia");
        values.put(CountryContract.CountryEntry.ANSWER, "Zambia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Zambia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Ljubljana");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Liubliana");
        values.put(CountryContract.CountryEntry.ID, "175");
        values.put(CountryContract.CountryEntry.FILE_NAME, "slovenia");
        values.put(CountryContract.CountryEntry.ANSWER, "Slovenia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Eslovenia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Saint John");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Saint John");
        values.put(CountryContract.CountryEntry.ID, "176");
        values.put(CountryContract.CountryEntry.FILE_NAME, "antigua_and_barbuda");
        values.put(CountryContract.CountryEntry.ANSWER, "Antigua and Barbuda");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Antigua y Barbuda");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Moroni");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Moroni");
        values.put(CountryContract.CountryEntry.ID, "177");
        values.put(CountryContract.CountryEntry.FILE_NAME, "comoros");
        values.put(CountryContract.CountryEntry.ANSWER, "Comoros");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Comoras");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Pristina");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Pristina");
        values.put(CountryContract.CountryEntry.ID, "178");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kosovo");
        values.put(CountryContract.CountryEntry.ANSWER, "Kosovo");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Kosovo");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Roseau");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Roseau");
        values.put(CountryContract.CountryEntry.ID, "179");
        values.put(CountryContract.CountryEntry.FILE_NAME, "dominica");
        values.put(CountryContract.CountryEntry.ANSWER, "Dominica");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Dominica");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Dili");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Dili");
        values.put(CountryContract.CountryEntry.ID, "180");
        values.put(CountryContract.CountryEntry.FILE_NAME, "democratic_republic_of_timor_leste");
        values.put(CountryContract.CountryEntry.ANSWER, "Democratic Rep. of Timor-Leste");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Rep. Democr. de Timor Oriental");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Vatican City");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ciudad del Vaticano");
        values.put(CountryContract.CountryEntry.ID, "181");
        values.put(CountryContract.CountryEntry.FILE_NAME, "vatican_city");
        values.put(CountryContract.CountryEntry.ANSWER, "Vatican City");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Ciudad del Vaticano");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "South Tarawa");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Tarawa Sur");
        values.put(CountryContract.CountryEntry.ID, "182");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kiribati");
        values.put(CountryContract.CountryEntry.ANSWER, "Kiribati");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Kiribati");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Bishkek");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Biskek");
        values.put(CountryContract.CountryEntry.ID, "185");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kyrgyzstan");
        values.put(CountryContract.CountryEntry.ANSWER, "Kyrgyzstan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Kirguistán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Maseru");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Maseru");
        values.put(CountryContract.CountryEntry.ID, "186");
        values.put(CountryContract.CountryEntry.FILE_NAME, "lesotho");
        values.put(CountryContract.CountryEntry.ANSWER, "Lesotho");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Lesoto");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Vaduz");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Vaduz");
        values.put(CountryContract.CountryEntry.ID, "187");
        values.put(CountryContract.CountryEntry.FILE_NAME, "liechtenstein");
        values.put(CountryContract.CountryEntry.ANSWER, "Liechtenstein");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Liechtenstein");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Male");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Malé");
        values.put(CountryContract.CountryEntry.ID, "189");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_maldives");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Maldives");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Maldivas");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Majuro");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Majuro");
        values.put(CountryContract.CountryEntry.ID, "190");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_the_marshall_islands");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of the Marshall Islands");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de las Islas Marshall");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Palikir");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Palikir");
        values.put(CountryContract.CountryEntry.ID, "191");
        values.put(CountryContract.CountryEntry.FILE_NAME, "micronesia");
        values.put(CountryContract.CountryEntry.ANSWER, "Micronesia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Micronesia");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Monaco");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Mónaco");
        values.put(CountryContract.CountryEntry.ID, "192");
        values.put(CountryContract.CountryEntry.FILE_NAME, "principality_of_monaco");
        values.put(CountryContract.CountryEntry.ANSWER, "Principality of Monaco");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Principado de Monaco");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Podgorica");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Podgorica");
        values.put(CountryContract.CountryEntry.ID, "193");
        values.put(CountryContract.CountryEntry.FILE_NAME, "montenegro");
        values.put(CountryContract.CountryEntry.ANSWER, "Montenegro");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Montenegro");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Yaren");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Yaren");
        values.put(CountryContract.CountryEntry.ID, "194");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_nauru");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Nauru");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Nauru");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Abuja");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Abuya");
        values.put(CountryContract.CountryEntry.ID, "195");
        values.put(CountryContract.CountryEntry.FILE_NAME, "federal_republic_of_nigeria");
        values.put(CountryContract.CountryEntry.ANSWER, "Federal Republic of Nigeria");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República Federal de Nigeria");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Ngerulmud");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Ngerulmud");
        values.put(CountryContract.CountryEntry.ID, "196");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_palau");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Palau");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Palau");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Castries");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Castries");
        values.put(CountryContract.CountryEntry.ID, "197");
        values.put(CountryContract.CountryEntry.FILE_NAME, "saint_lucia");
        values.put(CountryContract.CountryEntry.ANSWER, "Saint Lucia");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Santa Lucía");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Kingstown");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Kingstown");
        values.put(CountryContract.CountryEntry.ID, "198");
        values.put(CountryContract.CountryEntry.FILE_NAME, "saint_vincent_and_the_grenadines");
        values.put(CountryContract.CountryEntry.ANSWER, "Saint Vincent and the Grenadines");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "San Vicente y las Granadinas");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Apia");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Apia");
        values.put(CountryContract.CountryEntry.ID, "199");
        values.put(CountryContract.CountryEntry.FILE_NAME, "independent_state_of_samoa");
        values.put(CountryContract.CountryEntry.ANSWER, "Independent State of Samoa");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Estado Independiente de Samoa");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "San Marino");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "San Marino");
        values.put(CountryContract.CountryEntry.ID, "200");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_san_marino");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of San Marino");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de San Marino");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Dushanbe");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Dusambé");
        values.put(CountryContract.CountryEntry.ID, "201");
        values.put(CountryContract.CountryEntry.FILE_NAME, "republic_of_tajikistan");
        values.put(CountryContract.CountryEntry.ANSWER, "Republic of Tajikistan");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "República de Tayikistán");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Nukuʻalofa");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Nukualofa");
        values.put(CountryContract.CountryEntry.ID, "99");
        values.put(CountryContract.CountryEntry.FILE_NAME, "kingdom_of_tonga");
        values.put(CountryContract.CountryEntry.ANSWER, "Kingdom of Tonga");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Reino de Tonga");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Funafuti");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Funafuti");
        values.put(CountryContract.CountryEntry.ID, "52");
        values.put(CountryContract.CountryEntry.FILE_NAME, "tuvalu");
        values.put(CountryContract.CountryEntry.ANSWER, "Tuvalu");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Tuvalu");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Cardiff");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Cardiff");
        values.put(CountryContract.CountryEntry.ID, "188");
        values.put(CountryContract.CountryEntry.FILE_NAME, "wales");
        values.put(CountryContract.CountryEntry.ANSWER, "Wales");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Gales");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "0");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();


        values.put(CountryContract.CountryEntry.CAPITAL, "Jerusalem (East)");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Jerusalem (Este)");
        values.put(CountryContract.CountryEntry.ID, "184");
        values.put(CountryContract.CountryEntry.FILE_NAME, "state_of_palestine");
        values.put(CountryContract.CountryEntry.ANSWER, "State of Palestine");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Estado de Palestina");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Manama");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Manama");
        values.put(CountryContract.CountryEntry.ID, "183");
        values.put(CountryContract.CountryEntry.FILE_NAME, "bahrain");
        values.put(CountryContract.CountryEntry.ANSWER, "Bahrain");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Baréin");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "2");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CountryContract.CountryEntry.CAPITAL, "Andorra la Vella");
        values.put(CountryContract.CountryEntry.CAPITAL_ES, "Andorra la Vella");
        values.put(CountryContract.CountryEntry.ID, "142");
        values.put(CountryContract.CountryEntry.FILE_NAME, "andorra");
        values.put(CountryContract.CountryEntry.ANSWER, "Andorra");
        values.put(CountryContract.CountryEntry.ANSWER_ES, "Andorra");
        values.put(CountryContract.CountryEntry.DIFFICULTY, "1");

        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, values);
        values.clear();

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }

    public void fillPlayers(SQLiteDatabase sqLiteDatabase){
        ContentValues values = new ContentValues();
        sqLiteDatabase.beginTransaction();

        // Pares clave-valor
        values.put(PlayerContract.PlayerEntry.ID, "1");
        values.put(PlayerContract.PlayerEntry.PLAYER_NAME, "a");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_0_easy, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_1_easy, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_2_easy, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_3_easy, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_0_medium, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_1_medium, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_2_medium, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_3_medium, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_0_hard, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_1_hard, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_2_hard, "0");
        values.put(PlayerContract.PlayerEntry.BEST_SCORE_3_hard, "0");
        values.put(PlayerContract.PlayerEntry.NO_LOGIN,"0");

        if(sqLiteDatabase.insert(PlayerContract.PlayerEntry.TABLE_NAME, null, values) == -1){
           // Log.d("insert SQL players", "error");
        }
        values.clear();

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }


}

