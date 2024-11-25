import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;

public class Inventario<T extends CSVSerializable & Comparable<T>> {
    private List<T> items;

    public Inventario() {
        this.items = new ArrayList<>();
    }

    public void agregar(T item) {
        items.add(item);
    }

    public void paraCadaElemento(Consumer<? super T> accion) {
        items.forEach(accion);
    }

    public void eliminar(int index) {
        items.remove(index);
    }

    public List<T> filtrar(Predicate<T> criterio) 
    {
        List<T> filtrados = new ArrayList<>();
        for (T item : items) {
            if (criterio.test(item)) 
            {
                filtrados.add(item);
            }
        }
        return filtrados;
    }

    public void ordenarNatural() 
    {
        Collections.sort(items);
    }

    public void ordenar(Comparator<T> comparador) 
    {
        items.sort(comparador);
    }

    public void guardarEnArchivo(String ruta) 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) 
        {
            oos.writeObject(items);
        } catch (IOException e) 
        {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo(String ruta) 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) 
        {
            items = (List<T>) ois.readObject();
        } catch (IOException e) 
        {
            System.err.println("Error al cargar el archivo binario: " + e.getMessage());
        } catch (ClassNotFoundException e) 
        {
            System.err.println("Error al deserializar los objetos: " + e.getMessage());
        }
    }

    public void guardarEnCSV(String ruta) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) 
        {
            for (T item : items) 
            {
                bw.write(item.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }

    public void cargarDesdeCSV(String ruta, Function<String, T> fromCSV) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) 
        {
            String linea;
            while ((linea = br.readLine()) != null) 
            {
                T item = fromCSV.apply(linea);
                items.add(item);
            }
        } catch (IOException e) 
        {
            System.err.println("Error al cargar el archivo CSV: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Inventario{" + "items: " + items + '}';
    }
}
