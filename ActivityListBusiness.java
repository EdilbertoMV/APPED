package app.oneapp.eddy.myapp.com.oneapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityListBusiness extends AppCompatActivity {

    ArrayList<Empresa> listaEmpresas;
    RecyclerView recyclerEmpresas;

    empresaSQLiteHelper  conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_business);

        conn = new empresaSQLiteHelper(ActivityListBusiness.this, "dbEmpresas", null, 1);

        listaEmpresas = new ArrayList<>();

        recyclerEmpresas = (RecyclerView) findViewById(R.id.recyclerid);
        recyclerEmpresas.setLayoutManager(new GridLayoutManager(this, 3));

        consultarListaEmpresas();

        empresaSQLiteHelper empresa = new empresaSQLiteHelper(ActivityListBusiness.this, "dbEmpresas", null, 1);
        final SQLiteDatabase db = empresa.getWritableDatabase();

        AdaptadorEmpresas adapter = new AdaptadorEmpresas((listaEmpresas));

        adapter.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(final View view) {

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                final String hora = sdf.format(date);

                final String[] opciones = {"Editar", "Eliminar", "Cancelar"};
                final String[] opcionesEliminar  = {"Aceptar", "Cancelar"};


                final AlertDialog.Builder alertOpcionesEliminar = new AlertDialog.Builder(ActivityListBusiness.this);
                alertOpcionesEliminar.setTitle("Seleccione una Opción");

                alertOpcionesEliminar.setItems(opcionesEliminar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (opcionesEliminar[i].equals("Aceptar")) {


                            int oli = listaEmpresas.get(recyclerEmpresas.getChildAdapterPosition(view)).getCodigo();
                            String s = Integer.toString(oli);

                            db.delete(Adaptador.tabla_empresa, Adaptador.codigoEmpresa + "=?", new String[]{s});

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), "SE HA ELIMINADO LA EMPRESA",
                                    Toast.LENGTH_SHORT).show();

                        }else{
                            if (opcionesEliminar[i].equals("Cancelar")) {

                                dialogInterface.dismiss();


                            }
                        }
                    }
                });


                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(ActivityListBusiness.this);
                alertOpciones.setTitle("Seleccione una Opción");

                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (opciones[i].equals("Editar")) {

                            Intent intent = new Intent(ActivityListBusiness.this, ActivityEdit.class);

                            String name = listaEmpresas.get(recyclerEmpresas.getChildAdapterPosition(view)).getNombre();
                            String mec = listaEmpresas.get(recyclerEmpresas.getChildAdapterPosition(view)).getMechardising();
                            int cod = listaEmpresas.get(recyclerEmpresas.getChildAdapterPosition(view)).getCodigo();
                            String codigoS = Integer.toString(cod);

                            //Pasar a activity Edit pasar datos de empresa
                            intent.putExtra("nombreI", name);
                            intent.putExtra("mecharI", mec);
                            intent.putExtra("codigoI", codigoS);

                            startActivity(intent);

                        } else {
                            if (opciones[i].equals("Eliminar")) {

                                //Eliminar empresa de la db
                                alertOpcionesEliminar.show();

                                dialogInterface.dismiss();

                            } else {
                                if (opciones[i].equals("Cancelar")) {

                                    dialogInterface.dismiss();
                                }

                            }
                        }
                    }
                });

                alertOpciones.show();

            }
        });

        recyclerEmpresas.setAdapter(adapter);
    }

    //Metodo para realizar la consulta a la db
    private void consultarListaEmpresas() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Empresa empresa = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Adaptador.tabla_empresa, null);

        while(cursor.moveToNext()){

            empresa = new Empresa();
            empresa.setNombre(cursor.getString(0));
            empresa.setMechardising(cursor.getString(1));
            empresa.setCodigo(cursor.getInt(2));
            empresa.setFoto(R.drawable.ic_imagep);

            listaEmpresas.add(empresa);
        }
        db.close();
    }

    public  void encontrarEmpresa(){

        SQLiteDatabase db = conn.getReadableDatabase();

        Empresa empresa = null;

        //select nombre, mechardising, emisora, codigo
        //from empresa
        //where codigo = 999

        Cursor cursor = db.rawQuery("SELECT * FROM " + Adaptador.tabla_empresa, null);

        while(cursor.moveToNext()){

            empresa = new Empresa();
            empresa.setNombre(cursor.getString(0));
            empresa.setMechardising(cursor.getString(1));
            empresa.setCodigo(cursor.getInt(2));
            empresa.setFoto(R.drawable.ic_imagep);

            listaEmpresas.add(empresa);
        }

    }
}
