package projectp4.studio.com.gerenciador_universitario;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import projectp4.studio.com.gerenciador_universitario.DataBase.InfosDB;

public class SituGeral extends AppCompatActivity {

    private TextView dados;
    private SQLiteDatabase banco;
    private InfosDB idb;
    private TextView ab1tv;
    private TextView ab2tv;
    private TextView reavtv;
    private TextView finaltv;
    private TextView nomeMat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situ_geral);

        dados = (TextView) findViewById(R.id.Dados);
        ab1tv = (TextView) findViewById(R.id.ab1);
        ab2tv = (TextView) findViewById(R.id.ab2);
        reavtv = (TextView) findViewById(R.id.reav);
        finaltv = (TextView) findViewById(R.id.pfinal);
        nomeMat = (TextView) findViewById(R.id.nomeMat);

        Bundle extra = getIntent().getExtras();
        banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");
        idb = new InfosDB(SituGeral.this);

        if(extra != null){
            Integer idMat = extra.getInt("ID");
            ArrayList<String> nomes = idb.recuperarInfo(banco);
            //Toast.makeText(SituGeral.this, "kk eae men!", Toast.LENGTH_LONG).show();

            nomeMat.setText(nomes.get(idMat-1));

            Double ab1 = idb.getAb1().get(idMat-1);
            Double ab2 = idb.getAb2().get(idMat-1);
            Double reav = idb.getReav().get(idMat-1);
            Double pfinal = idb.getProvaFinal().get(idMat-1);

            reavtv.setText("Reav: " + reav );
            ab1tv.setText("AB1: " + ab1);
            ab2tv.setText("AB2: " + ab2);
            finaltv.setText("Final: " + pfinal);

        }
    }
}
