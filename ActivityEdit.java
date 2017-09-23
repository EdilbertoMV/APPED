package app.oneapp.eddy.myapp.com.oneapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityEdit extends AppCompatActivity {

    private View btn, btn2;
    EditText edtProducto, edtMerchandising, edtCodigo;


    private ContentValues generarContentValues(String nombre, String mechardising, String codigo){

        ContentValues valores = new ContentValues();

        valores.put(Adaptador.nombre, nombre);
        valores.put(Adaptador.mechardising, mechardising);
        valores.put(Adaptador.codigoEmpresa, codigo);

        return valores;
    }

   // public void modificarNombre(String nuevoNombre, String mechardising, String emisora, String codigo){

     //   db.update(Adaptador.tabla_empresa, generarContentValues(nuevoNombre, mechardising, emisora, codigo), Adaptador.codigo + "=?", new String[]{codigo});
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtProducto = (EditText) findViewById(R.id.eTProductoEdit);
        edtMerchandising = (EditText) findViewById(R.id.eTMerchandisingEdit);
        edtCodigo = (EditText) findViewById(R.id.eTCodigoEdit);
        btn = (Button) findViewById(R.id.buttonActualizar);
        btn2 = (Button) findViewById(R.id.buttonCancelarEdit);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null && extras.getString("nombreI") != null){

            String nombreIn = extras.getString("nombreI");
            String mecharIn = extras.getString("mecharI");
            final String codIn = extras.getString("codigoI");

            edtProducto.setText(nombreIn);
            edtMerchandising.setText(mecharIn);
            edtCodigo.setText(codIn);

            Toast.makeText(ActivityEdit.this, "Ingrese los datos por favorED.", Toast.LENGTH_SHORT).show();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nuevoNombre = edtProducto.getText().toString();
                    String nuevoMechar = edtMerchandising.getText().toString();
                    String nuevoCodigo = edtCodigo.getText().toString();

                    empresaSQLiteHelper empresa = new empresaSQLiteHelper(ActivityEdit.this, "dbEmpresas", null, 1);
                    SQLiteDatabase db = empresa.getWritableDatabase();

                    if(ActivityCreate.isNumeric(nuevoCodigo) == true){

                        //db.update(Adaptador.tabla_empresa, generarContentValues(pro, mercha, emi, cod), Adaptador.codigo + "=?", new String[]{hol});

                        db.execSQL("UPDATE " + Adaptador.tabla_empresa + " SET nombre = '" + nuevoNombre + "', "
                                + "mechardising = '" + nuevoMechar + "', " + "codigoEmpresa = " + nuevoCodigo
                                + " WHERE codigoEmpresa = " + codIn);
                        //modificarNombre(pro, mercha, emi, cod);

                        db.close();

                        Toast.makeText(ActivityEdit.this, "¡SE MODIFICÖ LA EMPRESA!", Toast.LENGTH_SHORT).show();

                        //Limpia los campos
                        edtProducto.setText("");
                        edtMerchandising.setText("");
                        edtCodigo.setText("");

                        //Volver a la activity  inicio
                        Intent intent = new Intent(ActivityEdit.this, ActivityInicio.class);
                        startActivity(intent);

                        //Algún campo sin llenar
                    }else {
                        Toast.makeText(ActivityEdit.this, "¡Paila el codigo debe ser númerico!", Toast.LENGTH_SHORT).show();

                    }
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Volver a la activity  inicio
                    Intent intent = new Intent(ActivityEdit.this, ActivityInicio.class);
                    startActivity(intent);
                }
            });

        }else{

            Toast.makeText(ActivityEdit.this, "Ingrese los datos por favor.", Toast.LENGTH_SHORT).show();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pro = edtProducto.getText().toString();
                    String mercha = edtMerchandising.getText().toString();
                    String cod = edtCodigo.getText().toString();

                    empresaSQLiteHelper empresa = new empresaSQLiteHelper(ActivityEdit.this, "dbEmpresas", null, 1);
                    SQLiteDatabase db = empresa.getWritableDatabase();

                    if(ActivityCreate.isNumeric(cod) == true){

                        //String hol = "999";
                        //db.update(Adaptador.tabla_empresa, generarContentValues(pro, mercha, emi, cod), Adaptador.codigo + "=?", new String[]{hol});

                        db.execSQL("UPDATE " + Adaptador.tabla_empresa + " SET nombre = 'edd' WHERE codigo = 123 ");
                        //modificarNombre(pro, mercha, emi, cod);

                        db.close();

                        Toast.makeText(ActivityEdit.this, "¡SE MODIFICÖ LA EMPRESA!", Toast.LENGTH_SHORT).show();

                        //Limpia los campos
                        edtProducto.setText("");
                        edtMerchandising.setText("");
                        edtCodigo.setText("");

                        //Volver a la activity  inicio
                        Intent intent = new Intent(ActivityEdit.this, ActivityInicio.class);
                        startActivity(intent);

                        //Algún campo sin llenar
                    }else {
                        Toast.makeText(ActivityEdit.this, "¡Paila!", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }



    }
}
