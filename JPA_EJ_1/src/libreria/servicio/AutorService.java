package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import static libreria.entidades.Autor_.id;
import libreria.entidades.Editorial;

public class AutorService {

    List<Autor> autores = new ArrayList();
    Scanner read = new Scanner(System.in);

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_EJ_1");
    EntityManager em = emf.createEntityManager();

    public void
            createaAuthor() {

        System.out.println("ingrese el nombre del autor");
        String name = read.next();
        try {
            if (SearchName(name) == null) {

                Autor autor1 = new Autor(name, true);

                em.getTransaction().begin();
                em.persist(autor1);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void deletedAuthor() {

        System.out.println("Ingreseel ID del autor que desearemover");

        Autor a = searchID(read.nextInt());
        try {
            if (a != null) {
                em.getTransaction().begin();
                em.remove(a);
                em.getTransaction().commit();
                System.out.println("el autor se removio exitosamente");
            } else {
                System.out.println("el autor no existe en la base de datos");
            }
        } catch (Exception e) {
        }

    }

    public void
            modifyAuthor() {

        System.out.println("Ingrese el ID delautor para modificar");
        Autor autor1 = searchID(read.nextInt());

        try {
            if (autor1 != null) {

                System.out.println("Ingrese el nuievo nombre del autor");
                autor1.setNombre(read.next());
                em.getTransaction().begin();
                em.merge(autor1);
                em.getTransaction().commit();
                System.out.println("El nombrese cambio exitosamente");
            } else {
                System.out.println("El IDno existe en la base de datos");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public Autor searchID(int id) {

        try {
            Autor a2 = new Autor();
            return a2 = em.find(Autor.class, id);
        } catch (Exception e) {
            throw e;
        }
    }

    public List SearchName(String name) {

        try {
            return autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                    .setParameter("nombre", name).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
       public void showquery(List<Autor> autores) throws Exception{
        System.out.println("\nAUTORES");
        System.out.println("__________________________");
        System.out.printf("|%-3s|%-13s|%-7s|\n", "ID", "NOMBRE", "ALTA", "");
        for (Autor aux : autores) {
            System.out.printf("|%-3s|%-13s|%-7s|\n", aux.getId(), aux.getNombre(), "");
        }
        System.out.println("__________________________");
    }
    
}
