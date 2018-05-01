package projectp4.studio.com.gerenciador_universitario;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConfig extends Fragment {

    private Button share;
    public FragmentConfig() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_config, container, false);

            share = (Button) view.findViewById(R.id.share);
            share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Compartilhar em The Dank Network");
                dialog.setMessage("Deseja divulgar o app através de uma postagem em The Dank Network?");
                dialog.setCancelable(false);

                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String encoded = "";
                        String imgURL =  "";
                        try {
                            encoded= URLEncoder.encode("Estou usando o Gerenciador Universitário!", "utf-8").replace("+", "%20");
                            imgURL = URLEncoder.encode("https://www.santanderuniversidades.com.br/projetos-especiais/PublishingImages/Paginas/app-universitario/bolsas-card.svg", "utf-8");
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://the-dank-network.herokuapp.com/post?content=" + encoded+
                            "&imageUrl="+imgURL)));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

        return view;
    }

}
