package projectp4.studio.com.gerenciador_universitario;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import projectp4.studio.com.gerenciador_universitario.DataBase.InfosDB;

public class AddFaltas extends AppCompatActivity {

    private Spinner materias;
    private Button add;
    private InfosDB idb;
    private SQLiteDatabase banco;
    private int posMat;
    private Integer id;
    private AlertDialog.Builder dialog;
    private NotificationCompat.Builder notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faltas);

        materias = (Spinner) findViewById(R.id.spMateria);
        add = (Button) findViewById(R.id.bt_Add);
        idb = new InfosDB(AddFaltas.this);

        try {
            banco = openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);
            banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, idb.recuperarInfo(banco));
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            materias.setAdapter(adapter);
            materias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    posMat = i;
                    id = idb.getIds().get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    idb = new InfosDB(AddFaltas.this);
                    idb.recuperarInfo(banco);

                    dialog = new AlertDialog.Builder(AddFaltas.this);
                    dialog.setTitle("Adicionar falta");
                    dialog.setMessage("Deseja adicionar uma falta nessa matéria?");
                    dialog.setCancelable(false);

                    dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    });

                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            updateFaltas(id, idb.getFaltasA().get(posMat));
/*
                                String prevStatus = s.calcStatus(Integer.parseInt(idb.getFaltasA().get(position)), Integer.parseInt(idb.getFaltasMax().get(position)));
                                updateFaltas(idb.getIds().get(position), idb.getFaltasA().get(position));
                                String newStatus = s.calcStatus(Integer.parseInt(idb.getFaltasA().get(position)), Integer.parseInt(idb.getFaltasMax().get(position)));

                                if(!prevStatus.equals(newStatus)){
                                    int nvl = (int)((Integer.parseInt(idb.getFaltasA().get(position))*100)/Integer.parseInt(idb.getFaltasMax().get(position)));
                                    if (nvl >= 50 && nvl < 80){
                                        notificar("Perigoso", position);
                                    }else if (nvl >= 80 && nvl <= 100){
                                        notificar("CRÍTICO", position);
                                    }else if(nvl > 100){
                                        notificar("ULTRAPASSADO", position);
                                    }
                                }*/
                            }
                    });
                    dialog.create();
                    dialog.show();
                }
            });

        }catch (Exception e){}

    }

    public void updateFaltas (Integer id, String faltas){
        try{
            int f = Integer.parseInt(faltas) + 1;
            banco.execSQL("UPDATE materias SET faltas="+ f +" WHERE id=" + id);
            Toast.makeText(AddFaltas.this, "Falta adicionada!", Toast.LENGTH_LONG).show();
            idb.recuperarInfo(banco);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void notificar(String status, int position){

        notification = new NotificationCompat.Builder(AddFaltas.this);
        notification.setAutoCancel(true);

        if(status.equals("Perigoso")){
            notification.setSmallIcon(R.drawable.notification_perigoso);
        }else if (status.equals("CRÍTICO")){
            notification.setSmallIcon(R.drawable.notification_critico);
        }else{
            notification.setSmallIcon(R.drawable.notification_limite_ultrapassado);
        }

        notification.setTicker("Nível de Faltas " + status + "!");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Nível de Faltas " + status + "!");
        notification.setContentText("Clique para ver o status da matéria");
        int id = (int) System.currentTimeMillis();
        notification.setVibrate(new long[] {150, 800});

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(AddFaltas.this, som);
            toque.play();
        }catch(Exception e){}

        ArrayList<String> extra = new ArrayList<String>();
        extra.add(idb.getMat().get(position));
        extra.add(idb.getFaltasA().get(position));
        extra.add(idb.getFaltasMax().get(position));

        Intent i = new Intent(AddFaltas.this, SituGeral.class);
        i.putExtra("Dados", extra);

        PendingIntent pi = PendingIntent.getActivity(AddFaltas.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pi);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(id, notification.build());
    }
}
