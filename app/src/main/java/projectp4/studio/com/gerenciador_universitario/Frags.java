package projectp4.studio.com.gerenciador_universitario;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frags extends Fragment {


    public Frags() {
        // Required empty public constructor
    }

    private Button bt;
    private EditText time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frags, container, false);

        bt = (Button) v.findViewById(R.id.bt);
        time = (EditText) v.findViewById(R.id.time);

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        return v;
    }

}
