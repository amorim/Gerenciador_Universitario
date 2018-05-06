package projectp4.studio.com.gerenciador_universitario;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import projectp4.studio.com.gerenciador_universitario.Adapter.TabAdapter;
import projectp4.studio.com.gerenciador_universitario.DataBase.InfosDB;
import projectp4.studio.com.gerenciador_universitario.helper.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Button addMat;
    private SlidingTabLayout sltbl;
    private ViewPager vp;
    private SQLiteDatabase banco;
    private Bundle bundle = new Bundle();
    private InfosDB idb = new InfosDB(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");


        bundle.putStringArrayList("materias", idb.recuperarInfo(banco));
        bundle.putIntegerArrayList("ids", idb.getIds());

        addMat = (Button) findViewById(R.id.addMat);
        sltbl = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        vp = (ViewPager) findViewById(R.id.vp_pagina);

        //configurando sliding tabs
        sltbl.setDistributeEvenly(true);
        sltbl.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.seletor));

        //conf adapter
        TabAdapter tba = new TabAdapter( getSupportFragmentManager(), bundle );
        vp.setAdapter( tba );
        sltbl.setViewPager( vp );

        addMat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(MainActivity.this, AddMat.class));
                addMat();
            }
        });
    }

    private void addMat(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        //Configurações do Dialog
        alertDialog.setTitle("Nova matéria");
        alertDialog.setCancelable(false);

        Context context = MainActivity.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Add a TextView here for the "Title" label, as noted in the comments
        final EditText nome = new EditText(context);
        nome.setHint("Nome da Matéria");
        layout.addView(nome);

        // Add another TextView here for the "Description" label
        final EditText cargaH = new EditText(context);
        cargaH.setHint("Carga Horária");
        cargaH.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(cargaH);

        alertDialog.setView(layout); // Again this is a set method, not add

        //Configura botões
        alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m = nome.getText().toString();
                String c = cargaH.getText().toString();

                if(m.equals("") || c.equals("")){
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show();
                }else {
                    Double maxFaltas = Integer.parseInt(c)*0.25;
                    int mf = maxFaltas.intValue();
                    String toAdd = "'" + m + "'," + c + "," + mf;

                    try {
                        banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);
                        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");

                        banco.execSQL("INSERT INTO materias (nome, cargaHoraria, maxFaltas, faltas) VALUES (" + toAdd + ",0)");

                        bundle.putStringArrayList("materias", idb.recuperarInfo(banco));
                        bundle.putIntegerArrayList("ids", idb.getIds());

                        TabAdapter tba = new TabAdapter( getSupportFragmentManager(), bundle );
                        vp.setAdapter( tba );
                        sltbl.setViewPager( vp );

                        Toast.makeText(MainActivity.this, "Matéria Adicionada!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Erro ao adicionar", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        alertDialog.create();
        alertDialog.show();

    }

}
