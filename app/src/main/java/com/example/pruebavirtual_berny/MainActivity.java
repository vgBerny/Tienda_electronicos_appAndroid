package com.example.pruebavirtual_berny;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[] productos = new String[14];
    private double[] precios =   new double[14];

    private CheckBox[] chk = new CheckBox[14];
    private ListView lvContenido;
    private RadioButton rbEnvio, rbSucursal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos[0] = "Prosesadores"; productos[1] = "Tarjeta Madre"; productos[2] = "Memoria RAM"; productos[3] = "Targetas de video";
        productos[4] = "Almacenamiento"; productos[5] = "Enfriamiento"; productos[6] = "Fuentes de poder"; productos[7] = "Cases";
        productos[8] = "Ventiladores"; productos[9] = "Monitores"; productos[10] = "Reguladores y UPS"; productos[11] = "Escritorios";
        productos[12] = "Sillas"; productos[13] = "Controles";

        precios[0] = 40000; precios[1] = 45000; precios[2] = 35000; precios[3] = 85000; precios[4] = 40000; precios[5] = 60000;
        precios[6] = 95000; precios[7] = 70000; precios[8] = 20000; precios[9] = 120000; precios[10] = 85000; precios[11] = 1100000;
        precios[12] = 60000; precios[13] = 38000;

        chk[0] =(CheckBox) findViewById(R.id.chkProducto0); chk[1] =(CheckBox) findViewById(R.id.chkProducto1); chk[2] =(CheckBox) findViewById(R.id.chkProducto2);
        chk[3] =(CheckBox) findViewById(R.id.chkProducto3); chk[4] =(CheckBox) findViewById(R.id.chkProducto4); chk[5] =(CheckBox) findViewById(R.id.chkProducto5);
        chk[6] =(CheckBox) findViewById(R.id.chkProducto6); chk[7] =(CheckBox) findViewById(R.id.chkProducto7); chk[8] =(CheckBox) findViewById(R.id.chkProducto8);
        chk[9] =(CheckBox) findViewById(R.id.chkProducto9); chk[10] =(CheckBox) findViewById(R.id.chkProducto10); chk[11] =(CheckBox) findViewById(R.id.chkProducto11);
        chk[12] =(CheckBox) findViewById(R.id.chkProducto12); chk[13] =(CheckBox) findViewById(R.id.chkProducto13);

        rbEnvio = (RadioButton) findViewById(R.id.rbEnvio);
        rbSucursal = (RadioButton) findViewById(R.id.rbSucursal);

        lvContenido= (ListView) findViewById(R.id.lvContenido);
        ArrayAdapter<String>adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productos);
        lvContenido.setAdapter(adaptador);
        lvContenido.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //Mensaje cuando se selecciona un producto
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                String mensajeAMostrar = "EL Producto " +lvContenido.getItemAtPosition(i) +" tiene un valor de " + precios[i] + " colones";
                MensajeDeNotificacion(mensajeAMostrar);
            }
        });

        Button btnVerificar = findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean preciosBool = false, retiroBool = false;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                //Extraer los productos de las  casillas seleccionadas y pasarlos al segundo activity
                StringBuilder ArregloTexto = new StringBuilder();
                double ArregloPrecios = 0;
                for (int i = 0; i< chk.length; i++ ){
                    if(chk[i].isChecked()){
                        ArregloTexto.append(productos[i]).append(" ");
                        ArregloPrecios = ArregloPrecios +  precios[i];
                        preciosBool = true;
                    }
                }
                String textoArreglo = ArregloTexto.toString();
                intent.putExtra("paramProductos", textoArreglo);
                double preciosArreglo = ArregloPrecios;
                intent.putExtra("paramPrecios", preciosArreglo);

                //Envia el total de los precios al segundo activity
                String strEnvio = "Envio de producto";
                String strSucursal = "Retira en la sucursal";
                String retiro = "";
                if(rbEnvio.isChecked()){
                    retiro = strEnvio;
                    retiroBool = true;
                } else if (rbSucursal.isChecked()) {
                    retiro = strSucursal;
                    retiroBool= true;
                }
                intent.putExtra("paramRetiro", retiro);

                if (preciosBool == false) {
                    Toast notificacion = Toast.makeText(MainActivity.this, "Debe seleccionar al menos un producto para continuar.", Toast.LENGTH_SHORT);
                    notificacion.show();
                } else if (retiroBool== false) {
                    Toast notificacion = Toast.makeText(MainActivity.this, "Debe seleccionar un tipo de retiro para continuar.", Toast.LENGTH_SHORT);
                    notificacion.show();
                } else {
                Toast notificacion = Toast.makeText(MainActivity.this, "Datos seleccionados correctamente.", Toast.LENGTH_SHORT);
                notificacion.show();
                Toast notificacion2 = Toast.makeText(MainActivity.this, "Productos: "+ textoArreglo, Toast.LENGTH_SHORT);
                notificacion2.show();
                Toast notificacion3 = Toast.makeText(MainActivity.this, "Total a pagar: " + preciosArreglo, Toast.LENGTH_SHORT);
                notificacion3.show();
                startActivity(intent);
                }
            }

        });
    }

    //Arreglo que envia un Toast cuando se selecciona un producto.
    private void MensajeDeNotificacion(String mensajeAMostrar){
        Context context =getApplicationContext();
        int duracion= Toast.LENGTH_SHORT;
        Toast toast =Toast.makeText(context, mensajeAMostrar, duracion);
        toast.show();
    }
}