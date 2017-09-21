package app.oneapp.eddy.myapp.com.oneapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityInicio extends AppCompatActivity {

    private View btn, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Forzar y cargar icono en el Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        empresaSQLiteHelper empresa = new empresaSQLiteHelper(ActivityInicio.this, "dbEmpresas", null, 1);
        SQLiteDatabase db = empresa.getWritableDatabase();

        //Botones del menú inicial
        btn =  findViewById(R.id.buttonCrearEmpresa);
        btn2 = findViewById(R.id.buttonEditarEmpresa);
        btn3 = findViewById(R.id.buttonVerEmpresas);

        btn.setOnClickListener(new View.OnClickListener(){
                                   @Override
                                   public void onClick(View view) {
                                       Intent intent = new Intent(ActivityInicio.this, ActivitySecond.class);
                                       //intent.putExtra("g",gg );
                                       startActivity(intent);
                                   }
                               }
        );

        btn2.setOnClickListener(new View.OnClickListener(){
                                   @Override
                                   public void onClick(View view) {
                                       Intent intent = new Intent(ActivityInicio.this, ActivityEdit.class);
                                       startActivity(intent);
                                   }
                               }
        );

        btn3.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(ActivityInicio.this, ActivityListBusiness.class);
                                        startActivity(intent);
                                    }
                                }
        );
    }
}
