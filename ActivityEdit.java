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

    private View btn;
    EditText edtProducto, edtMerchandising, edtEmisora, edtCodigo;


    private ContentValues generarContentValues(String nombre, String mechardising, String emisora, String codigo){

        ContentValues valores = new ContentValues();

        valores.put(Adaptador.nombre, nombre);
        valores.put(Adaptador.mechardising, mechardising);
        valores.put(Adaptador.emisora, emisora);
        valores.put(Adaptador.codigo, codigo);

        return valores;
    }

   // public void modificarNombre(String nuevoNombre, String mechardising, String emisora, String codigo){

     //   db.update(Adaptador.tabla_empresa, generarContentValues(nuevoNombre, mechardising, emisora, codigo), Adaptador.codigo + "=?", new String[]{codigo});
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtProducto = (EditText) findViewById(R.id.eTProducto);
        edtMerchandising = (EditText) findViewById(R.id.eTMerchandising);
        edtEmisora = (EditText) findViewById(R.id.eTEmisora);
        edtCodigo = (EditText) findViewById(R.id.eTCodigo);

        Toast.makeText(ActivityEdit.this, "Ingrese los datos por favor.", Toast.LENGTH_SHORT).show();

        btn = (Button) findViewById(R.id.buttonActualizar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pro = edtProducto.getText().toString();
                String mercha = edtMerchandising.getText().toString();
                String emi = edtEmisora.getText().toString();
                String cod = edtCodigo.getText().toString();

                empresaSQLiteHelper empresa = new empresaSQLiteHelper(ActivityEdit.this, "dbEmpresas", null, 1);
                SQLiteDatabase db = empresa.getWritableDatabase();

                if(ActivitySecond.isNumeric(cod) == true){

                    //String hol = "999";
                    //db.update(Adaptador.tabla_empresa, generarContentValues(pro, mercha, emi, cod), Adaptador.codigo + "=?", new String[]{hol});

                    db.execSQL("UPDATE " + Adaptador.tabla_empresa + " SET nombre = 'edd' WHERE codigo = 123 ");
                    //modificarNombre(pro, mercha, emi, cod);

                    db.close();

                    Toast.makeText(ActivityEdit.this, "¡SE MODIFICÖ LA EMPRESA!", Toast.LENGTH_SHORT).show();

                    //Limpia los campos
                    edtProducto.setText("");
                    edtMerchandising.setText("");
                    edtEmisora.setText("");
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
