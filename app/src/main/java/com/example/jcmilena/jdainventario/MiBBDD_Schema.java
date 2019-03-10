package com.example.jcmilena.jdainventario;

import android.provider.BaseColumns;

public class MiBBDD_Schema {

    static final String SQLCreate =
            "CREATE TABLE "+ EntradaBBDD.TABLE_NAME + " (" +
                    "_ID INTEGER PRIMARY KEY, " +
                    EntradaBBDD.COLUMNA1 + " TEXT, " +
                    EntradaBBDD.COLUMNA2 + " TEXT, " +
                    EntradaBBDD.COLUMNA3 + " TEXT, " +
                    EntradaBBDD.COLUMNA4 + " TEXT)";

    static final String SQLUpgrade =
            "DROP TABLE IF EXISTS "+ EntradaBBDD.TABLE_NAME;

    private MiBBDD_Schema(){};

    public class EntradaBBDD implements BaseColumns {

        static final String TABLE_NAME = "INVENTARIO";
        static final String COLUMNA1 = "FABRICANTE";
        static final String COLUMNA2 = "MODELO";
        static final String COLUMNA3 = "MAC";
        static final String COLUMNA4 = "AULA";

    }

}
