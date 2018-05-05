package projectp4.studio.com.gerenciador_universitario;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private InfosDB idb;
    //private SQLiteDatabase banco = new SQLiteDatabase(getActivity());;
    private TextView semNotas;
    private ListView lista;
    private ArrayList<String> args;
    private ArrayList<Integer> ids;


    public FragmentMaterias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_fragment_materias, container, false);
        //banco = view.openOrCreateDatabase("Gerenciador_universitario", MODE_PRIVATE, null);

        //banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");

        semNotas = (TextView) view.findViewById(R.id.semNotas);

        if(getArguments().getStringArrayList("materias") == null){
            semNotas.setText("Não há matérias listadas, use o botão 'Adicionar Matérias' logo abaixo para começar");
        }else{
            args = getArguments().getStringArrayList("materias");
            ids = getArguments().getIntegerArrayList("ids");
            lista = (ListView) view.findViewById(R.id.listaMat);

            ArrayAdapter<String> listaMaterias = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, args);
            listaMaterias.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            lista.setAdapter(listaMaterias);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent i = new Intent(getContext(), SituGeral.class);
                    Toast.makeText(getContext(), "YO " +ids.get(position), Toast.LENGTH_LONG).show();
                    i.putExtra("ID", ids.get(position));
                    startActivity(i);
                }
            });
        }


        //setar texto como "Não há matérias listadas, use o botão "Adicionar Matérias" logo abaixo para começar"

        return view;
    }

}
