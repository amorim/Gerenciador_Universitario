package projectp4.studio.com.gerenciador_universitario;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import projectp4.studio.com.gerenciador_universitario.DataBase.InfosDB;

public class Ranking extends AppCompatActivity {

    private InfosDB idb = new InfosDB(Ranking.this);
    private Calculos c = new Calculos(Ranking.this);
    private SQLiteDatabase banco;
    private ListView rk;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        rk = (ListView) findViewById(R.id.rk);

        banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);

        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE, conceito TEXT, nivelDeFaltas TEXT)");

        idb.recuperarInfo(banco);


        adapter = new ArrayAdapter<String>(Ranking.this, R.layout.support_simple_spinner_dropdown_item, idb.Ranking());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //adapter.notifyDataSetChanged(); NOP

        rk.setAdapter(adapter);

        //c.ranking(idb.getMat(), idb.getIds(), idb.idsOrdenadas(banco)); NOP




    }
}
