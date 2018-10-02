package com.example.luisangel.tarea1;

import java.util.ArrayList;

public class CRUD {

    private ArrayList<String> nombres = new ArrayList<String>();

    public void guardar(String nombre){
        nombres.add(nombre);

    }

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public boolean actualizar(int posicion, String nuevoNombre){
        try{
            nombres.remove(posicion);
            nombres.add(posicion, nuevoNombre);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar (int posicion){
        try{
            nombres.remove(posicion);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }


}
