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
    private TextView faltasA;
    private TextView faltasR;
    private TextView cargaH;
    private TextView media;

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
        faltasA = (TextView) findViewById(R.id.tvFaltasA);
        faltasR = (TextView) findViewById(R.id.tvFaltasR);
        cargaH = (TextView) findViewById(R.id.tvCh);
        media = (TextView) findViewById(R.id.tvMedia);

        Bundle extra = getIntent().getExtras();
        banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");
        idb = new InfosDB(SituGeral.this);

        if(extra != null){
            Integer idMat = extra.getInt("ID");
            ArrayList<String> nomes = idb.recuperarInfo(banco);

            nomeMat.setText(nomes.get(idMat-1));

            Double ab1 = idb.getAb1().get(idb.getIds().indexOf(idMat));
            Double ab2 = idb.getAb2().get(idb.getIds().indexOf(idMat));
            Double reav = idb.getReav().get(idb.getIds().indexOf(idMat));
            Double pfinal = idb.getProvaFinal().get(idb.getIds().indexOf(idMat));

            String fa = idb.getFaltasA().get(idb.getIds().indexOf(idMat));
            int maxF = Integer.parseInt(idb.getFaltasMax().get(idb.getIds().indexOf(idMat)));
            int fr = maxF - Integer.parseInt(fa);
            if(fr < 0)
                fr = 0;

            String ch = idb.getCargaH().get(idb.getIds().indexOf(idMat));
            Double m = idb.getMediaFinal().get(idb.getIds().indexOf(idMat));


            reavtv.setText("Reav: " + reav );
            ab1tv.setText("AB1: " + ab1);
            ab2tv.setText("AB2: " + ab2);
            finaltv.setText("Final: " + pfinal);
            faltasA.setText("Faltas Atuais: "+fa);
            faltasR.setText("Faltas Restantes: " + fr);
            cargaH.setText("Carga Horária: " + ch +"h");
            media.setText("Média: " + m);

        }
    }
}
