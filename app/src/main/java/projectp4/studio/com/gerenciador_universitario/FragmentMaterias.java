package projectp4.studio.com.gerenciador_universitario;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import projectp4.studio.com.gerenciador_universitario.DataBase.InfosDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaterias extends Fragment {

    private InfosDB idb = new InfosDB(getContext());
    private TextView semNotas;
    private ListView lista;
    private ArrayList<String> args;
    private ArrayList<Integer> ids;
    private AlertDialog.Builder dialog;
    private ArrayAdapter<String> listaMaterias;

    public FragmentMaterias() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "kk eae men", Toast.LENGTH_LONG).show();
        args = getArguments().getStringArrayList("materias");
        listaMaterias = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, args);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_fragment_materias, container, false);

        semNotas = (TextView) view.findViewById(R.id.semNotas);

        if(getArguments().getStringArrayList("materias") == null){
            semNotas.setText("Não há matérias listadas, use o botão 'Adicionar Matérias' logo abaixo para começar");
        }else{
            //args = getArguments().getStringArrayList("materias");
            ids = getArguments().getIntegerArrayList("ids");
            lista = (ListView) view.findViewById(R.id.listaMat);


            //listaMaterias = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, args);
            listaMaterias.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            listaMaterias.notifyDataSetChanged();

            lista.setAdapter(listaMaterias);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    //Toast.makeText(getContext(), "Clicou no: "+ids.get(position), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getContext(), SituGeral.class);
                    i.putExtra("ID", ids.get(position));
                    startActivity(i);
                }
            });

            lista.setLongClickable(true);
            lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    dialog = new AlertDialog.Builder(getContext());
                    dialog.setTitle("Excluir matéria");
                    dialog.setMessage("Deseja excluir a matéria da lista?");
                    dialog.setCancelable(false);
                    dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    });
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //idb.removerMateria(idb.pegarBanco("Gerenciador_universitario"), ids.get(position) );
                            //ArrayAdapter<String> listaMaterias = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, args);
                           // listaMaterias.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            //lista.setAdapter(listaMaterias);
                            //fin
                        }
                    });
                    dialog.create();
                    dialog.show();
                    return true;
                }
            });

        }

        return view;
    }


}
