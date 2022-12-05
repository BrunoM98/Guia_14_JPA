package libreria.servicio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Prestamo;
import static libreria.entidades.Prestamo_.libro;

public class PrestamoService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_EJ_1");
    EntityManager em = emf.createEntityManager();
    List<Prestamo> prestamos = new ArrayList();
    Prestamo p = new Prestamo();
    Scanner read = new Scanner(System.in);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    LibroService ls = new LibroService();
    ClienteService cs = new ClienteService();

    public void createdLoan() throws Exception {

        Date today = new Date();
        System.out.println("ingrese la fecha");
        String dateP = read.next();
        System.out.println("ingrese el ISBN del libro");
        Long book = read.nextLong();
        System.out.println("ingrese el id del cliente");
        int cliente = read.nextInt();
        boolean equal = true;
        Iterator<Prestamo> iterato = searchLoan().iterator();
        while (iterato.hasNext() && equal) {
            Prestamo aux = iterato.next();
            if (aux.getCliente().getId() == cliente && aux.getLibro().equals(libro) && aux.getFechaPrestamo().equals(sdf.parse(dateP))) {
            } 
                equal = false;
        }
        try {
            if (ls.searchISBN(book).getEjemplaresRestantes() > 0 && sdf.parse(dateP).compareTo(today) > 0 && equal) {
                ls.modifyBook(book, 1);
                p = new Prestamo(sdf.parse(dateP), ls.searchISBN(book), cs.searchID(cliente));
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
                System.out.println("Carga exitosa");
            } else if (equal == false) {
                System.out.println("Este prestamo ya fue realizado");
            } else {
                System.out.println("No hay libros disponibles");
            }
        } catch (Exception e) {
            throw e;
        }
    }
        public void modificarPrestamo() throws Exception{
            
        System.out.println("ingrese el nombre del cliente");
        String cliente = read.next();
        System.out.println("ingrese el titulo del libro");
        String libro = read.next();
        int id;
        boolean equals = true;
        Iterator<Prestamo> it = prestamos.iterator();
            while(it.hasNext() && equals){
                Prestamo aux = it.next();
                if (aux.getCliente().getNombre().equals(cliente ) && aux.getLibro().getTitulo().equals(libro)) {
                    p = aux;
                    equals = false;
                }
            }
        try{
            if (p != null && p.getLibro().getEjemplaresPrestamos() > 0) {
                ls.modifyBook(p.getLibro().getIsbn(), -1);
                System.out.println("ingrese la fecha de devolucion");
                String fechaD = read.next();
                p.setFechaDevolucion(sdf.parse(fechaD));
                em.getTransaction().begin();
                em.merge(p);
                em.getTransaction().commit();
                System.out.println("Modificacion exitosa");
            }else
                System.out.println("No se encontro el prestamo");
        }catch(Exception e){
            throw e;
        }
    }

    public void removeLoan() {

        System.out.println("Ingrese el id de su prestamo");
        
        Prestamo p = searchID(read.nextInt());
        try {
            if(p != null){
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
                System.out.println("se removie el Prestamo exitosamente");
            }else{
                System.out.println("el prestamo no existe en la base");
            }
        } catch (Exception e) {
        }

    }

    public Prestamo searchID(int id) {

        try {

            Prestamo p1 = new Prestamo();
            return p1 = em.find(Prestamo.class, id);
        } catch (Exception e) {
            throw e;
        }
    }

    public List searchClient(String client) {

        try {
            return prestamos = em.createQuery("SELECT p FROM Prestamo p JOIN p.cliente c WHERE c.nombre LIKE :nombre")
                    .setParameter("nombre", client).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public List searchBook(String book) {

        try {
            return prestamos = em.createQuery("SELECT p FROM Prestamo p JOIN p.libro l WHERE l.titulo LIKE :titulo")
                    .setParameter("titulo", book).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public List searchDateP(String dateP) {

        try {
            return prestamos = em.createQuery("SELECT p FROM Prestamo p WHERE p.fechaPrestamo LIKE :fechaPrestamo")
                    .setParameter("fechaPrestamo", dateP).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public List searchLoan() {

        try {
            return prestamos = em.createQuery("SELECT p FROM Prestamo p ").getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
        public void showquery(List<Prestamo> autores) throws Exception{
        System.out.println("\nPRESTAMOS");
        System.out.println("__________________________");
         System.out.printf("|%-3s|%-13s|%-13s|%-13s|%-13s|\n", "ID", "PRESTAMO", "DEVOLUCION", "LIBRO", "CLIENTE", "");
        for (Prestamo aux : autores) {
           System.out.printf("|%-13s|%-7s|%-26s|%-13s|%-13s|%-13s|%-13s|\n", aux.getId(), aux.getCliente(),aux.getFechaDevolucion(), aux.getFechaPrestamo(), aux.getLibro().getTitulo(), "");
        }
        System.out.println("__________________________");
    }

}
