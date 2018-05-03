package projectp4.studio.com.gerenciador_universitario.Adapter;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import projectp4.studio.com.gerenciador_universitario.FragmentConfig;
import projectp4.studio.com.gerenciador_universitario.FragmentFaltas;
import projectp4.studio.com.gerenciador_universitario.FragmentMaterias;
import projectp4.studio.com.gerenciador_universitario.FragmentNotas;

/**
 * Created by Lucas on 01/05/2018.
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    private String[] tituloAbas ={"LISTA", "NOTAS", "FALTAS", "CONFIG."};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        if(position == 0) {
            f = new FragmentMaterias();
        }else if(position == 1){
            f = new FragmentNotas();
        }else if(position == 2){
            f = new FragmentFaltas();
        }else{
            f = new FragmentConfig();
        }
        return f;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle (int position){
        return tituloAbas[position];
    }
}
