package projectp4.studio.com.gerenciador_universitario;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFaltas extends Fragment {

    private Button addfaltas;
    private Button situAtual;


    public FragmentFaltas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_faltas, container, false);

        addfaltas = (Button) view.findViewById(R.id.addFaltas);
        situAtual = (Button) view.findViewById(R.id.situAtual);

        addfaltas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AddFaltas.class);
                startActivity(intent);
            }
        });

        situAtual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), SituAtual.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
