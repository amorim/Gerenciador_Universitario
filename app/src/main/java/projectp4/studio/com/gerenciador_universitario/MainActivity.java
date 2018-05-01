package projectp4.studio.com.gerenciador_universitario;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button notas;
    private Button faltas;
    private Button addMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notas = (Button) findViewById(R.id.notas);
        faltas = (Button) findViewById(R.id.faltas);
        addMat = (Button) findViewById(R.id.addMat);

        notas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MainNotas.class));
            }
        });

        faltas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MainFaltas.class));
            }
        });

        addMat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager frag = getFragmentManager();
                FragmentTransaction ftrans = frag.beginTransaction();
                AddMat f = new AddMat();

                ftrans.add(R.id.rl_layout, f);

                ftrans.commit();


            }
        });


    }




}
