package projectp4.studio.com.gerenciador_universitario;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMat extends Activity {

    private EditText materia;
    private EditText cargaH;
    private Button add;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mat);

        materia = (EditText) findViewById(R.id.etNome);
        cargaH = (EditText) findViewById(R.id.etCargaH);
        add = (Button) findViewById(R.id.btAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = materia.getText().toString();
                String c = cargaH.getText().toString();

                if(m.equals("") || c.equals("")){
                    Toast.makeText(AddMat.this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show();
                }else {
                    Double maxFaltas = Integer.parseInt(c)*0.25;
                    int mf = maxFaltas.intValue();
                    String toAdd = "'" + m + "'," + c + "," + mf;

                    try {
                        banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);

                        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE, conceito TEXT, nivelDeFaltas TEXT)");

                        banco.execSQL("INSERT INTO materias (nome, cargaHoraria, maxFaltas, faltas) VALUES (" + toAdd + ",0)");
                        Toast.makeText(AddMat.this, "Matéria Adicionada!", Toast.LENGTH_LONG).show();
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(AddMat.this, "Erro ao adicionar", Toast.LENGTH_LONG).show();
                        //Toast.makeText(AddMateria.this, "EXCEPTION " + e.toString(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
