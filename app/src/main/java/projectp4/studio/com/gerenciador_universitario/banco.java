package projectp4.studio.com.gerenciador_universitario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InfosDB {

    private Context context;
    private Cursor cursor;
    private ArrayAdapter<String> listaMaterias;
    private ArrayList<String> mat;
    private ArrayList<Integer> ids;
    private ArrayList<Double> ab1;
    private ArrayList<Double> ab2;
    private ArrayList<Double> reav;
    private ArrayList<Double> provaFinal;
    private ArrayList<Double> mediaFinal;
    private ArrayList<String> faltasA;
    private ArrayList<String> faltasMax;

    public InfosDB (Context context){
        this.context = context;
    }

    public ListView recuperarInfo(SQLiteDatabase banco, ListView listaMat){
        try{
            cursor = banco.rawQuery("SELECT id, nome,faltas,maxFaltas, ab1, ab2, reav, provaFinal, mediaFinal  FROM materias", null);

            int indexNome = cursor.getColumnIndex("nome");
            int indexId = cursor.getColumnIndex("id");
            int indexFaltas = cursor.getColumnIndex("faltas");
            int indexMaxF = cursor.getColumnIndex("maxFaltas");
            int indexab1 = cursor.getColumnIndex("ab1");
            int indexab2 = cursor.getColumnIndex("ab2");
            int indexprovaFinal = cursor.getColumnIndex("provaFinal");
            int indexmediaFinal = cursor.getColumnIndex("mediaFinal");
            cursor.moveToFirst();
            //Adapter
            mat = new ArrayList<String>();
            ids = new ArrayList<Integer>();
            faltasA = new ArrayList<String>();
            faltasMax = new ArrayList<String>();
            ab1 = new ArrayList<Double>();
            ab2 = new ArrayList<Double>();
            reav = new ArrayList<Double>();
            provaFinal = new ArrayList<Double>();
            mediaFinal = new ArrayList<Double>();
            listaMaterias = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_2, android.R.id.text2, mat);
            listaMat.setAdapter(listaMaterias);

            while(cursor != null){
                mat.add( cursor.getString(indexNome) );
                ids.add( Integer.parseInt(cursor.getString(indexId)) );
                faltasA.add( cursor.getString(indexFaltas) );
                faltasMax.add( cursor.getString(indexMaxF) );
                ab1.add(cursor.getDouble(indexab1));
                ab2.add(cursor.getDouble(indexab2));
                provaFinal.add(cursor.getDouble(indexprovaFinal));
                mediaFinal.add(cursor.getDouble(mediaFinal));
                cursor.moveToNext();
            }
        }catch(Exception e){}
        finally {
            return listaMat;
        }
    }

    public ArrayList<String> getDados (int position){
        ArrayList<String> extra = new ArrayList<String>();
        extra.add(mat.get(position));
        extra.add(faltasA.get(position));
        extra.add(faltasMax.get(position));
        extra.add(ab1.get(position));
        extra.add(ab2.get(position));
        extra.add(reav.get(position));
        extra.add(provaFinal.get(position));
        extra.add(mediaFinal.get(position));
        return extra;
    }

    public void updateFaltas (SQLiteDatabase banco, int f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET faltas="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfo(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }

    public void updateAb1 (SQLiteDatabase banco, double f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET ab1="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfo(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    public void updateAb2 (SQLiteDatabase banco, double f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET ab2="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfo(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    public void updateReav (SQLiteDatabase banco, double f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET reav="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfo(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    public void updateProvaFinal (SQLiteDatabase banco, double f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET provaFinal="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfo(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    public Context getContext() {
        return context;
    }

    public ArrayList<Double> getAb1() {
        return ab1;
    }

    public ArrayList<Double> getAb2() {
        return ab2;
    }

    public ArrayList<Double> getReav() {
        return reav;
    }

    public ArrayList<Double> getProvaFinal() {
        return provaFinal;
    }

    public ArrayList<Double> getMediaFinal() {
        return mediaFinal;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public ArrayAdapter<String> getListaMaterias() {
        return listaMaterias;
    }

    public ArrayList<String> getMat() {
        return mat;
    }

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public ArrayList<String> getFaltasA() {
        return faltasA;
    }

    public ArrayList<String> getFaltasMax() {
        return faltasMax;
    }
}