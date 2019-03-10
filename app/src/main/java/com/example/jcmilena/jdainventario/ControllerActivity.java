package com.example.jcmilena.jdainventario;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ControllerActivity extends AppCompatActivity implements AddEquipoFragment.OnAddEquipoListener, InventarioFragment.OnInventarioFragmentListener, SearchFragment.OnSearchFragmentListener {

    List<EquipoInformatico> inventario = new ArrayList<>();
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        db = new MiBBDD_Helper(this).getWritableDatabase();

        Fragment fragment = new WelcomeFragment();
        cargar_fragment(fragment);


    }

    private void cargar_fragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.jdainventario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void writeSQLite(EquipoInformatico equipo) {
        MiBBDD_Helper dbhelp = new MiBBDD_Helper(this);
        SQLiteDatabase db = dbhelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put(MiBBDD_Schema.EntradaBBDD.COLUMNA1, equipo.getFabricante());
            values.put(MiBBDD_Schema.EntradaBBDD.COLUMNA2, equipo.getModelo());
            values.put(MiBBDD_Schema.EntradaBBDD.COLUMNA3, equipo.getMAC());
            values.put(MiBBDD_Schema.EntradaBBDD.COLUMNA4, equipo.getAula());
            long newRowid = db.insert(MiBBDD_Schema.EntradaBBDD.TABLE_NAME, null, values);

            Toast toast = Toast.makeText(this, "Se han añanido correctamente a la base de datos " + MiBBDD_Schema.EntradaBBDD.TABLE_NAME, Toast.LENGTH_LONG);
            toast.show();

        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Error al insertar en la base de datos " + MiBBDD_Schema.EntradaBBDD.TABLE_NAME, Toast.LENGTH_LONG);
            toast.show();
        }

        Fragment fragment = new WelcomeFragment();
        cargar_fragment(fragment);

    }

    @Override
    public List<EquipoInformatico> getEquiposList() {

        return inventario;
    }

    @Override
    public void searchSQLite(String columna, String valor) {

        MiBBDD_Helper dbhelp = new MiBBDD_Helper(this);
        SQLiteDatabase db = dbhelp.getWritableDatabase();
        String[] valores = new String[]{valor};
        String selection="";
        EquipoInformatico nuevo=null;

        if(columna.equals("fabricante")) {//Si esta buscando por fabricante
            selection = MiBBDD_Schema.EntradaBBDD.COLUMNA1 + " = ?";
        }else if(columna.equals("modelo")){
            selection = MiBBDD_Schema.EntradaBBDD.COLUMNA2 + " = ?";
        }else if(columna.equals("mac")){
            selection = MiBBDD_Schema.EntradaBBDD.COLUMNA3 + " = ?";
        }else if(columna.equals("aula")){
            selection = MiBBDD_Schema.EntradaBBDD.COLUMNA4 + " = ?";
        }

                                    //En que tabla buscamos, que columnas pedimos, where=, valor a buscar...
        Cursor cursor = db.query(MiBBDD_Schema.EntradaBBDD.TABLE_NAME, null, selection, valores, null, null, null);

        if (cursor.moveToFirst()) {//Recorrer lo que nos ha devuelto
            do {
                String fabricante = cursor.getString(0);
                String modelo = cursor.getString(1);
                String mac = cursor.getString(2);
                String aula = cursor.getString(3);

                nuevo = new EquipoInformatico(fabricante, modelo, mac, aula);
            } while (cursor.moveToNext());

        } else {
            Toast toast = Toast.makeText(this, "No se ha encontrado nada en la tabla " + MiBBDD_Schema.EntradaBBDD.TABLE_NAME, Toast.LENGTH_LONG);
            toast.show();
            return;

        }
        Toast toast = Toast.makeText(this, cursor.getCount()+" rows in set (0.02 sec)", Toast.LENGTH_LONG);
        toast.show();

        Fragment fragment = new InventarioFragment();
        cargar_fragment(fragment);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.equipo:
                Fragment fragment = new AddEquipoFragment();
                cargar_fragment(fragment);
                return true;

            case R.id.buscar:
                fragment = new SearchFragment();
                cargar_fragment(fragment);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
