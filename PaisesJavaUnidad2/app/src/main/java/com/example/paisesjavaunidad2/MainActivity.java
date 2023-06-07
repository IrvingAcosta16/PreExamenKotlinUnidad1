package com.example.paisesjavaunidad2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //SE DECLARA EL NOMBRE DEL SPINNER
    private Spinner spnPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AQUI COMIENZA LA MAGIA DEL ADAPTADOR
        spnPaises = (Spinner) findViewById(R.id.spnPaises);
        ArrayAdapter<String> Adaptador = new
        ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.paises));
        spnPaises.setAdapter(Adaptador);
        spnPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            //SE HACE LA CONSULTA DEL PAIS
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long 1) {
                Toast.makeText(MainActivity.this,"Seleccione el pais" + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }});}}