package app.oneapp.eddy.myapp.com.oneapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySecond extends AppCompatActivity {

    private View btn;
    EditText edtProducto, edtMerchandising, edtEmisora, edtCodigo;

    //Comprobar si una cadena es un número
    public static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edtProducto = (EditText) findViewById(R.id.eTProducto);
        edtMerchandising = (EditText) findViewById(R.id.eTMerchandising);
        edtEmisora = (EditText) findViewById(R.id.eTEmisora);
        edtCodigo = (EditText) findViewById(R.id.eTCodigo);

        Toast.makeText(ActivitySecond.this, "Ingrese los datos por favor.", Toast.LENGTH_SHORT).show();

        btn = (Button) findViewById(R.id.buttonGuardar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pro = edtProducto.getText().toString();
                String mercha = edtMerchandising.getText().toString();
                String emi = edtEmisora.getText().toString();
                String cod = edtCodigo.getText().toString();


                //Verifica que los campos estén llenos
                if(pro.length() > 0 && mercha.length() > 0 && emi.length() > 0 && cod.length() > 0){

                    if(ActivitySecond.isNumeric(cod) == true){

                        // db.execSQL("INSERT INTO Empresa (indice, nombre, merchandising, emisora, codigo) VALUES (" + pro + ", '" + mercha + ", '" + emi + ", '" + cod + "')");
                        // db.close();

                        empresaSQLiteHelper empresa = new empresaSQLiteHelper(ActivitySecond.this, "dbEmpresas", null, 1);
                        SQLiteDatabase db = empresa.getWritableDatabase();

                        //String insert = "INSERT INTO " + Adaptador.tabla_empresa + " (" + Adaptador.nombre + ", "
                        //       + Adaptador.mechardising + ", " + Adaptador.emisora + ", " + Adaptador.codigo + ") VALUES ( '"
                        //        + pro + "', '" + mercha + "', '" + emi + "', '" + cod + "')";

                        db.execSQL(Adaptador.insertar(pro, mercha, emi, cod));

                        //db.execSQL(insert);
                        db.close();

                        Toast.makeText(ActivitySecond.this, "¡SE CREO UNA EMPRESA NUEVA!", Toast.LENGTH_SHORT).show();

                        //Limpia los campos
                        edtProducto.setText("");
                        edtMerchandising.setText("");
                        edtEmisora.setText("");
                        edtCodigo.setText("");

                        //Volver a la activity  inicio
                        Intent intent = new Intent(ActivitySecond.this, ActivityInicio.class);
                        startActivity(intent);

                        //Algún campo sin llenar
                    }else {

                        Toast.makeText(ActivitySecond.this, "Debe llenar todos los datos para continuar.", Toast.LENGTH_SHORT).show();

                    }

                }else{

                    Toast.makeText(ActivitySecond.this, "El codigo debe ser númerico.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
