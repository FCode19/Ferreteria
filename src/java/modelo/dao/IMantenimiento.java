package modelo.dao;

import java.util.ArrayList;


public interface IMantenimiento<T> {

    public ArrayList<T> listar();

    public T buscar(int id);

    public void registrar(T t);

    public void modificar(T t);

    public void eliminar(int id);
}
