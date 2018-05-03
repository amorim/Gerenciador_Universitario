package projectp4.studio.com.gerenciador_universitario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SituAtual extends AppCompatActivity {

    private String[] niveisF ={"Aceitável", "Perigoso", "Crítico", "Limite Ultrapassado"};
    private Spinner nvlsF;
    private TextView teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situ_atual);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, niveisF);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        nvlsF = (Spinner) findViewById(R.id.nvlsF);
        nvlsF.setAdapter(adapter);

        teste = (TextView) findViewById(R.id.teste);

        nvlsF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                teste.setText("Vc selecionou " + niveisF[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
