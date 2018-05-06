package projectp4.studio.com.gerenciador_universitario.DataBase;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import projectp4.studio.com.gerenciador_universitario.R;

/**
 * Created by Lucas on 05/05/2018.
 */
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
    private ArrayList<String> cargaH;

    public InfosDB (Context context){
        this.context = context;
    }

    public ListView recuperarInfoList(SQLiteDatabase banco, ListView listaMat){
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
                mediaFinal.add(cursor.getDouble(indexmediaFinal));
                cursor.moveToNext();
            }
        }catch(Exception e){}
        finally {
            return listaMat;
        }
    }


    public  ArrayList<String> recuperarInfo(SQLiteDatabase banco){
        try{
            cursor = banco.rawQuery("SELECT id, nome,faltas,maxFaltas,cargaHoraria, ab1, ab2, reav, provaFinal, mediaFinal  FROM materias", null);

            int indexNome = cursor.getColumnIndex("nome");
            int indexId = cursor.getColumnIndex("id");
            int indexFaltas = cursor.getColumnIndex("faltas");
            int indexMaxF = cursor.getColumnIndex("maxFaltas");
            int indexab1 = cursor.getColumnIndex("ab1");
            int indexab2 = cursor.getColumnIndex("ab2");
            int indexreav = cursor.getColumnIndex("reav");
            int indexprovaFinal = cursor.getColumnIndex("provaFinal");
            int indexmediaFinal = cursor.getColumnIndex("mediaFinal");
            int indexCargaH = cursor.getColumnIndex("cargaHoraria");
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
            cargaH = new ArrayList<String>();

            listaMaterias = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, mat);
            listaMaterias.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            while(cursor != null){
                mat.add( cursor.getString(indexNome) );
                ids.add( Integer.parseInt(cursor.getString(indexId)) );
                faltasA.add( cursor.getString(indexFaltas) );
                faltasMax.add( cursor.getString(indexMaxF) );
                ab1.add(cursor.getDouble(indexab1));
                ab2.add(cursor.getDouble(indexab2));
                reav.add(cursor.getDouble(indexreav));
                provaFinal.add(cursor.getDouble(indexprovaFinal));
                mediaFinal.add(cursor.getDouble(indexmediaFinal));
                cargaH.add(cursor.getString(indexCargaH));
                cursor.moveToNext();
            }
        }catch(Exception e){}
        finally {
            return mat;
        }
    }

/*
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
    }*/

    public void updateFaltas (SQLiteDatabase banco, int f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET faltas="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfoList(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }

    public SQLiteDatabase pegarBanco (String nome, Context context){
        SQLiteDatabase banco = context.openOrCreateDatabase(nome, Context.MODE_PRIVATE, null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS materias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, " +
                "cargaHoraria INT(2), maxFaltas INT(2), faltas INT(2), ab1 DOUBLE, ab2 DOUBLE, " +
                "reav DOUBLE, provaFinal DOUBLE, mediaFinal DOUBLE)");
        return banco;
    }

    public void removerMateria(SQLiteDatabase banco, Context c, Integer id){
        //Toast.makeText(getContext(), "j", Toast.LENGTH_LONG).show();
        try{
            banco.execSQL("DELETE FROM materias WHERE id=" + id);
            Toast.makeText(c, "Materia Excluida", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(c, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void updateAb1 (SQLiteDatabase banco, double f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET ab1="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfoList(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    public void updateAb2 (SQLiteDatabase banco, double f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET ab2="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfoList(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    public void updateReav (SQLiteDatabase banco, double f, ListView listaMat, Integer id){
        try{
            banco.execSQL("UPDATE materias SET reav="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();
            recuperarInfoList(banco, listaMat);

        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    public void updateProvaFinal (SQLiteDatabase banco, double f, Integer id){
        try{
            banco.execSQL("UPDATE materias SET provaFinal="+ f +" WHERE id=" + id);
            Toast.makeText(context, "Falta adicionada!", Toast.LENGTH_LONG).show();

        }catch(Exception e){
            e.printStackTrace();;
        }
    }

  // public Double[] arrayDoubleConv(ArrayList<Double>)

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

    public ArrayList<String> getCargaH() {
        return cargaH;
    }

}