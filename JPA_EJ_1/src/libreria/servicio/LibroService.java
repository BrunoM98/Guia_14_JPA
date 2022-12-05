package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import static libreria.entidades.Libro_.isbn;

public class LibroService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_EJ_1");
    EntityManager em = emf.createEntityManager();

    Scanner read = new Scanner(System.in);

    List<Libro> books = new ArrayList();
    AutorService authores = new AutorService();
    EditorialService editoriales = new EditorialService();

    public void createdBook() {

        System.out.println("Ingreseel ISBN del libro");
        Long isbn = read.nextLong();
        try {
            if (searchISBN(isbn) == null) {

                System.out.println("Ingrese el titulo");
                System.out.println("ingrese el año");
                System.out.println("ingrese el total ejemplares");
                System.out.println("ingrese la cantidad ejemplares prestados");
                System.out.println("ingrese la cantidad ejemplares restantes");
                System.out.println("ingrese el id del autor");
                System.out.println("ingrese el id de la editorial");

                Libro libro = new Libro(isbn, read.next(), read.nextInt(), read.nextInt(), read.nextInt(), read.nextInt(), authores.searchID(read.nextInt()), editoriales.searchID(read.nextInt()));
                
                em.getTransaction().begin();
                em.persist(libro);
                em.getTransaction().commit();
                
                System.out.println("se cargo correctamente el libro");
            } else {
                System.out.println("el ISBNdel libroya existe");
            }
        } catch (Exception e) {
        }
    }

    public void modifyBook(Long isbn, int prestados) {

        System.out.println("Ingrese el ISBN del libro para modificarlo");
        Libro libro = searchISBN(read.nextLong());

        try {
            if (libro != null) {
                System.out.println("Ingrese el titulo");
                libro.setTitulo(read.next());
                System.out.println("Ingrese anio");
                libro.setAnio(read.nextInt());
                System.out.println("total de ejemplares");
                libro.setEjemplares(read.nextInt());
                libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestamos());
                em.getTransaction().begin();
                em.merge(libro);
                em.getTransaction().commit();
                System.out.println("Modificacion exitosa");
            }else{
                System.out.println("El ISBN noexiste en la basede datos");
            }
        } catch (Exception e) {
        }
    }
    
    public void removeBook(){
        System.out.println("Ingrese el ISBN del libro que desea remover");
        Libro libro = searchISBN(read.nextLong());
        try {
            if(libro != null) {
             em.getTransaction().begin();
             em.remove(libro);
             em.getTransaction().commit();
                System.out.println("El libro fue removido");
            }else {
                System.out.println("El ISBN no existeenla base de datos");
            }
        } catch (Exception e) {
        }
    }

    public Libro searchISBN(Long isbn) {

        try {
            Libro l1 = new Libro();
            return l1 = em.find(Libro.class, isbn);
        } catch (Exception e) {
            throw e;
        }
    }
      public List searchTitulo(String Title){

        try{
            return books = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                    .setParameter("titulo",Title).getResultList();
        }catch(Exception e){
            throw e;
        }
    }
    
    public List searchAutor(String author){

        try{
            return books = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre LIKE :nombre")
                    .setParameter("nombre",author).getResultList();
        }catch(Exception e){
            throw e;
        }
    }
    
    public List searchEditorial(String editorial){

        try{
            return books = em.createQuery("SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre LIKE :nombre")
                    .setParameter("nombre",editorial).getResultList();
        }catch(Exception e){
            throw e;
        }
    }
        public void showquery(List<Libro> books) throws Exception{
        System.out.println("\nLIBROS");
        System.out.println("__________________________________________________________________________________________________________________________________");
        System.out.printf("|%-20s|%-20s|%-7s|%-13s|%-13s|%-13s|%-7s|%-13s|%-13s|\n", "ISBN", "TITULO", "AÑO", "EJEMPLARES", "PRESTADOS", "RESTANTES", "ALTA", "AUTOR", "EDITORIAL", "");
        for (Libro aux : books) {
            System.out.printf("|%-20s|%-20s|%-7s|%-13s|%-13s|%-13s|%-7s|%-13s|%-13s|\n",
            aux.getIsbn(), aux.getTitulo(), aux.getAnio(), aux.getEjemplares(), aux.getEjemplaresPrestamos(), aux.getEjemplaresRestantes(), aux.isAlta(), aux.getAutor().getNombre(), aux.getEditorial().getNombre(), "");
        }
        System.out.println("__________________________________________________________________________________________________________________________________");
    }
}






