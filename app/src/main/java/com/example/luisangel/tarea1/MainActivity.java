package com.example.luisangel.tarea1;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridView gvLista;
    ArrayAdapter<String> lstAdapter;
    CRUD crud = new CRUD();
    Dialog dlInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gvLista = (GridView)findViewById(R.id.gvLista);

        gvLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(dlInformacion != null){
                    mostrarDialog(position);
                }else {
                    dlInformacion.dismiss();
                }
            }

        });

        final FloatingActionButton fbtnAccion  = (FloatingActionButton)findViewById(R.id.btnAccion);

        fbtnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialog(-1);
            }
        });






    }
    private void mostrarDialog (final int posicion){
        dlInformacion = new Dialog(this);
        dlInformacion.setTitle("Mantenimiento de nombres");
        dlInformacion.setContentView(R.layout.input_dialog);

        final EditText txtNombres = (EditText)dlInformacion.findViewById(R.id.txtNombre);
        Button btnAgregar = (Button)dlInformacion.findViewById(R.id.btnAgregar);
        Button btnEliminar = (Button)dlInformacion.findViewById(R.id.btnEliminar);
        Button btnActualizar = (Button)dlInformacion.findViewById(R.id.btnActualizar);


        if(posicion == -1){
            btnAgregar.setEnabled(true);
            btnEliminar.setEnabled(false);
            btnActualizar.setEnabled(false);
        }else {
            btnAgregar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnActualizar.setEnabled(true);
            txtNombres.setText(crud.getNombres().get(posicion));
        }


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombres.getText().toString();
                if(nombre.length()> 0 && nombre != null){
                    crud.guardar(nombre);
                    txtNombres.setText("");
                    lstAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,crud.getNombres());
                    gvLista.setAdapter(lstAdapter);
                }else{
                    Toast.makeText(MainActivity.this, "El nombre no puede ser vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = txtNombres.getText().toString();
                if(nuevoNombre.length()> 0 && nuevoNombre != null){

                    if(crud.actualizar(posicion,nuevoNombre)){
                        txtNombres.setText(nuevoNombre);
                        lstAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,crud.getNombres());
                        gvLista.setAdapter(lstAdapter);

                    }
                }else{
                    Toast.makeText(MainActivity.this, "El nombre no puede ser vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(crud.eliminar(posicion)){
                    txtNombres.setText("");
                    lstAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,crud.getNombres());
                    gvLista.setAdapter(lstAdapter);
                }
            }
        });



        dlInformacion.show();

    }
}
