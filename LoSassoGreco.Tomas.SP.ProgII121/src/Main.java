import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear un inventario de libros
            Inventario<Libro> inventarioLibros = new Inventario<>();
            inventarioLibros.agregar(new Libro(1, "1984", "George Orwell", Categoria.LITERATURA));
            inventarioLibros.agregar(new Libro(2, "El senior de los anillos", "J.R.R. Tolkien", Categoria.ENTRETENIMIENTO));
            inventarioLibros.agregar(new Libro(3, "Cien anios de soledad", "Gabriel Garcia Marquez", Categoria.LITERATURA));
            inventarioLibros.agregar(new Libro(4, "El origen de las especies", "Charles Darwin", Categoria.CIENCIA));
            inventarioLibros.agregar(new Libro(5, "La guerra de los mundos", "H.G. Wells", Categoria.CIENCIA));

            // Mostrar todos los libros en el inventario
            System.out.println("Inventario de libros:");
            inventarioLibros.paraCadaElemento(libro -> System.out.println(libro));

            // Filtrar libros por categoría LITERATURA
            System.out.println("\nLibros de la categoria LITERATURA:");
            inventarioLibros.filtrar(libro -> libro.getCategoria() == Categoria.LITERATURA)
                            .forEach(libro -> System.out.println(libro));

            // Filtrar libros cuyo titulo contiene "1984"
            System.out.println("\nLibros cuyo titulo contiene '1984':");
            inventarioLibros.filtrar(libro -> libro.getTitulo().contains("1984"))
                            .forEach(libro -> System.out.println(libro));

            // Ordenar libros de manera natural (por id)
            System.out.println("\nLibros ordenados de manera natural (por id):");
            inventarioLibros.ordenarNatural();
            inventarioLibros.paraCadaElemento(libro -> System.out.println(libro));

            // Ordenar libros por titulo utilizando un Comparator
            System.out.println("\nLibros ordenados por titulo:");
            inventarioLibros.ordenar((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo()));
            inventarioLibros.paraCadaElemento(libro -> System.out.println(libro));

            // Guardar el inventario en un archivo binario
            inventarioLibros.guardarEnArchivo("src/data/libros.dat");
            System.out.println("\nInventario guardado en archivo binario.");

            // Cargar el inventario desde un archivo binario
            Inventario<Libro> inventarioCargado = new Inventario<>();
            inventarioCargado.cargarDesdeArchivo("src/data/libros.dat");
            System.out.println("\nLibros cargados desde archivo binario:");
            inventarioCargado.paraCadaElemento(libro -> System.out.println(libro));

            // Guardar el inventario en un archivo CSV
            inventarioLibros.guardarEnCSV("src/data/libros.csv");
            System.out.println("\nInventario guardado en archivo CSV.");

            // Cargar el inventario desde un archivo CSV
            inventarioCargado.cargarDesdeCSV("src/data/libros.csv", Libro::fromCSV);
            System.out.println("\nLibros cargados desde archivo CSV:");
            inventarioCargado.paraCadaElemento(libro -> System.out.println(libro));

        } catch (Exception e) 
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
