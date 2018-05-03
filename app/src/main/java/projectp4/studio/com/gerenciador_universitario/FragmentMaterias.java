package projectp4.studio.com.gerenciador_universitario;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaterias extends Fragment {

    private TextView semNotas;


    public FragmentMaterias() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragment_materias, container, false);

        semNotas = (TextView) view.findViewById(R.id.semNotas);

        //setar texto como "Não há matérias listadas, use o botão "Adicionar Matérias" logo abaixo para começar"

        return view;
    }

}
