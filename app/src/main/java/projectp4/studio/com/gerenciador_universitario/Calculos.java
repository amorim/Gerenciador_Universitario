package projectp4.studio.com.gerenciador_universitario;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lucas on 06/05/2018.
 */
public class Calculos extends Activity{

    private Context context;

    public Calculos(Context c){
        this.context = c;
    }


    public String calcStatus(Integer fA, Integer maxF){
        //ACEITAVEL -> ate 50% [0, 50)
        //PERIGOSO -> entre 50% e 90% [50, 90)
        //CRITICO -> mais de 90% [90, 100]
        //ULTRAPASSADO -> mais de 100% (100, +inf)

        int nvl = (int)((fA*100)/maxF);
        if( nvl < 50 ){
            return "ACEITÁVEL";
        }else if (nvl >= 50 && nvl < 80){
            return "PERIGOSO!";
        }else if (nvl >= 80 && nvl <= 100){
            return "CRÍTICO!!!";
        }else{
            return "LIMITE ULTRAPASSADO!";
        }
    }

    public double media (int av, ArrayList<Double> notas){
        //Toast.makeText(Calculos.this, "Dados passados:" + notas.toString(), Toast.LENGTH_LONG).show();
        double mediaA = (notas.get(0) + notas.get(1))/2;

        if(av == 0 || av == 1){
            return mediaA;
        }else{
            if(notas.get(0) < notas.get(1))
                mediaA = (notas.get(1) + notas.get(2))/2;
            else
                mediaA = (notas.get(0) + notas.get(2))/2;

            if(av == 2)
                return mediaA;
            else
                return mediaA*0.6 + notas.get(3) * 0.4;
        }
    }

    public String conceito (double media, boolean f, boolean cb){

        String saida;
        if(media >= 7){
            saida = "Aprovado";
        }else if (media >= 5.5 && f){
            saida = "Aprovado";
        }else if(media < 5.5 || (media < 7 && f)){
            saida = "Reprovado";
        }

        /*if(cb){

        }*/

        return "";

    }






}
