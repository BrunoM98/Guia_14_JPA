package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

public class EditorialService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_EJ_1");
    EntityManager em = emf.createEntityManager();
    Scanner read = new Scanner(System.in);
    List<Editorial> editoriales = new ArrayList();
    Editorial editorial1 = new Editorial();

    public void createdEditorial() {

        System.out.println("ingrese el nombre");

        String nombre = read.next();
        try {
            if (searchNombre(nombre) == null) {

                Editorial e = new Editorial(nombre, true);

                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modifyEditorial() {

        System.out.println("Ingrese el ID de de la editorial para modificarlo");
        Editorial e1 = searchID(read.nextInt());

        try {
            if (e1 != null) {
                System.out.println("Ingrese el nuevonombrede laediorial");
                e1.setNombre(read.next());
                em.getTransaction().begin();
                em.merge(e1);
                em.getTransaction().commit();
                System.out.println("semodifico exitosamente la editorial");
            } else {
                System.out.println("no se encontro el IDde la editorial");
            }
        } catch (Exception e) {
        }
    }

    public void removeEditorial() {

        System.out.println("Ingrese el ID de la editorial");
        Editorial e1 = searchID(read.nextInt());

        try {
            if (e1 != null) {

                em.getTransaction().begin();
                em.remove(e1);
                em.getTransaction().commit();
                System.out.println("Se elimino correctamente la editorial");
            } else {
                System.out.println("no se encontro el ID dela editorial");
            }
        } catch (Exception e) {
        }

    }

    public Editorial searchID(int id) {

        try {
            Editorial e1 = new Editorial();
            return e1 = em.find(Editorial.class, id);
        } catch (Exception e) {
            throw e;
        }
    }

    public List searchNombre(String nombre) {

        try {
            return editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre")
                    .setParameter("nombre", nombre).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
        public void showquery(List<Editorial> editoriales) throws Exception{
        System.out.println("\nEDITORIALES");
        System.out.println("__________________________");
        System.out.printf("|%-3s|%-13s|%-7s|\n", "ID", "NOMBRE", "ALTA", "");
        for (Editorial aux : editoriales) {
            System.out.printf("|%-3s|%-13s|%-7s|\n", aux.getId(), aux.getNombre(), "");
        }
        System.out.println("__________________________");
    } 
    
}
