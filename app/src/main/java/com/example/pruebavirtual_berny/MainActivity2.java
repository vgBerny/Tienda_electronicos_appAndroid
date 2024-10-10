package com.example.pruebavirtual_berny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle=getIntent().getExtras();

        String ArrayProductos = bundle.getString("paramProductos");
        TextView tvProductos =(TextView) findViewById(R.id.tvProductos);
        tvProductos.setText(ArrayProductos);

        double ArrayPrecios = bundle.getDouble("paramPrecios");
        TextView tvPrecios =(TextView) findViewById(R.id.tvPrecios);
        tvPrecios.setText("" + ArrayPrecios);

        String ArrayRetiro = bundle.getString("paramRetiro");
        TextView tvRetiro =(TextView) findViewById(R.id.tvRetiro);
        tvRetiro.setText(ArrayRetiro);

        Button btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishAffinity();
                System.exit(0);
            }
        });
    }
}