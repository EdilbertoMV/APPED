package app.oneapp.eddy.myapp.com.oneapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityListTransmition extends AppCompatActivity {

    ArrayList<Empresa> listaEmpresas;
    RecyclerView recyclerEmpresas;

    empresaSQLiteHelper  conn;
    //empresaSQLiteHelper Empresa = new empresaSQLiteHelper(ActivityListBusiness.this, "dbEmpresas", null, 1);
    //SQLiteDatabase db = Empresa.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_business);

        conn = new empresaSQLiteHelper(ActivityListTransmition.this, "dbEmpresas", null, 1);

        listaEmpresas = new ArrayList<>();

        recyclerEmpresas = (RecyclerView) findViewById(R.id.recyclerid);
        //recyclerEmpresas.setLayoutManager(new LinearLayoutManager(this));
        recyclerEmpresas.setLayoutManager(new GridLayoutManager(this, 3));

        consultarListaEmpresas();

        //Log.i("INFO", String.valueOf(listaEmpresas.get(0)));
        //llenarEmpresas();

       // AdaptadorEmpresas adapter = new AdaptadorEmpresas((listaEmpresas));
        //recyclerEmpresas.setAdapter(adapter);

        AdaptadorEmpresas adapter = new AdaptadorEmpresas((listaEmpresas));



        adapter.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(final View view) {

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                final String hora = sdf.format(date);

                final String[] opciones = {"Confirmar", "Cancelar", "Otra empresa"};
                final String[] opcionesEmpresas;

                opcionesEmpresas = new String[listaEmpresas.size()];

                for(int i=0; i < listaEmpresas.size(); i++){

                    opcionesEmpresas[i] = listaEmpresas.get(i).getNombre();
                }

                final AlertDialog.Builder alertOpcionesEmpresas = new AlertDialog.Builder(ActivityListTransmition.this);
                alertOpcionesEmpresas.setTitle("Seleccione una Opción");

                alertOpcionesEmpresas.setItems(opcionesEmpresas, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (opcionesEmpresas[i].equals("eddy")) {

                            Toast.makeText(getApplicationContext(),
                                    listaEmpresas.get(recyclerEmpresas.getChildAdapterPosition(view)).getNombre() +
                                            " Hora: " + hora,
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            if (opcionesEmpresas[i].equals("Confirmar")) {

                                dialogInterface.dismiss();

                            } else {
                                if (opcionesEmpresas[i].equals("Confirmar")) {

                                    dialogInterface.dismiss();

                                } else {

                                    dialogInterface.dismiss();

                                }
                            }
                        }
                    }
                });


                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(ActivityListTransmition.this);
                alertOpciones.setTitle("Seleccione una Opción");

                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (opciones[i].equals("Confirmar")) {

                            Toast.makeText(getApplicationContext(),
                                    listaEmpresas.get(recyclerEmpresas.getChildAdapterPosition(view)).getNombre() +
                                            " Hora: " + hora,
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            if (opciones[i].equals("Cancelar")) {

                                dialogInterface.dismiss();

                            } else {
                                if (opciones[i].equals("Otra empresa")) {

                                    dialogInterface.dismiss();

                                    alertOpcionesEmpresas.show();

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
            //empresa.setEmisora(cursor.getString(2));
            empresa.setCodigo(cursor.getInt(2));
            empresa.setFoto(R.drawable.ic_imagep);

            listaEmpresas.add(empresa);
        }

    }

    /*
    public void llenarEmpresas(){

        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));
        listaEmpresas.add(new EmpresaVO("Eddy", R.drawable.ic_imagep));


    }*/
}
