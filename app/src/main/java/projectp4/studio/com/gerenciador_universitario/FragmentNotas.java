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
public class FragmentNotas extends Fragment {

    private Button ranking;
    private Button addNotas;
    private Button verNotas;


    public FragmentNotas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_notas, container, false);

        ranking = (Button) view.findViewById(R.id.ranking);
        addNotas = (Button) view.findViewById(R.id.AddNotas);
        verNotas = (Button) view.findViewById(R.id.verNotas);

        ranking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Ranking.class);
                startActivity(intent);
            }
        });

        addNotas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AddNotas.class);
                startActivity(intent);
            }
        });

        verNotas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), VerNotas.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
