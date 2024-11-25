import java.io.Serializable;
import java.util.Objects;

public class Libro implements Comparable<Libro>, CSVSerializable, Serializable{
    private int id;
    private String titulo;
    private String autor;
    private Categoria categoria;
    
    public Libro(int id, String titulo, String autor, Categoria categoria){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }
    
    public int getId(){
        return id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public Categoria getCategoria(){
        return categoria;
    }
    
    @Override
    public String toCSV()
    {
        return id + "," + titulo + "," + autor + "," + categoria;
    }
    
    public static Libro fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Libro(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            Categoria.valueOf(parts[3])
        );
    }

    @Override
    public int compareTo(Libro o) {
        return Integer.compare(this.id, o.id);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id == libro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Libro { " + "id: " + id + ", titulo: " + titulo + '\'' +
                ", autor: " + autor + '\'' + ", categoria: " + categoria +  "}";
    }
}
