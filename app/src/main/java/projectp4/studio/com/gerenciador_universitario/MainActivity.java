package projectp4.studio.com.gerenciador_universitario;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import projectp4.studio.com.gerenciador_universitario.Adapter.TabAdapter;
import projectp4.studio.com.gerenciador_universitario.DataBase.InfosDB;
import projectp4.studio.com.gerenciador_universitario.helper.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Button addMat;
    private SlidingTabLayout sltbl;
    private ViewPager vp;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);

        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");
        InfosDB idb = new InfosDB(MainActivity.this);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("materias", idb.recuperarInfo(banco));
        bundle.putIntegerArrayList("ids", idb.getIds());
        //bundle.putStringArrayList("faltas", idb.getFaltasA());
        //bundle.putStringArrayList("maxFaltas", idb.getFaltasMax());
        //bundle.putDoubleArray("ab1", idb.getAb1());
        //bundle.putStringArrayList("ab2", idb.getAb2());
        //bundle.putStringArrayList("provaFinal", idb.getProvaFinal());
        //bundle.putStringArrayList("mediaFinal", idb.getMediaFinal());




// set Fragmentclass Arguments
        /*Fragmentclass fragobj = new Fragmentclass();
        fragobj.setArguments(bundle);*/



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

                startActivity(new Intent(MainActivity.this, AddMat.class));

                /*FragmentManager frag = getFragmentManager();
                FragmentTransaction ftrans = frag.beginTransaction();
                Frags f = new Frags();

                ftrans.add(R.id.rl_layout, f);

                ftrans.commit();*/


            }
        });


    }




}
