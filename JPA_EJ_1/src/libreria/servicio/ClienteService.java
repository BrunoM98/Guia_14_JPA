package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;

public class ClienteService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_EJ_1");
    EntityManager em = emf.createEntityManager();
    List<Cliente> clientes = new ArrayList();
    Scanner read = new Scanner(System.in);

    public void createdClient() {

        System.out.println("Ingrese elnombre del cliente");
        String name = read.next();

        try {

            if (SearchName(name).isEmpty()) {
                System.out.println("Ingrese el dni");
                System.out.println("Ingrese el aplellido");
                System.out.println("Ingrese el telefono");
                Cliente c1 = new Cliente(read.nextLong(), name, read.next(), read.next());

                em.getTransaction().begin();
                em.persist(c1);
                em.getTransaction().commit();
                System.out.println("Se ingreso correctamente");
            } else {
                System.out.println("El cliente ya existe en la base");
            }
        } catch (Exception e) {
        }
    }

    public void deletedClient() {

        System.out.println("Ingrese el id del cliente");
        Cliente idClient = searchID(read.nextInt());

        if (idClient != null) {

            em.getTransaction().begin();
            em.remove(idClient);
            em.getTransaction().commit();
            System.out.println("El cliente fue eliminado correctamente");
        } else {
            System.out.println("El cliente no existe");
        }
    }

    public void modifyClient() {

        System.out.println("Ingrese el id del cliente");
        Cliente Client = searchID(read.nextInt());

        if (Client != null) {

            System.out.println("Ingrese el nuevo nombre del cliente");
            Client.setNombre(read.next());
            System.out.println("Ingrese el nuevo apellido delcliente");
            Client.setApellido(read.next());
            em.getTransaction().begin();
            em.merge(Client);
            em.getTransaction().commit();
            System.out.println("Se modifico exitosamente el cliente");
        } else {
            System.out.println("El cliente ingresado no existe en la base");
        }

    }

    public Cliente searchID(int id) {

        try {
            Cliente c1 = new Cliente();
            return c1 = em.find(Cliente.class, id);
        } catch (Exception e) {
            throw e;
        }

    }

    public List SearchName(String name) {

        try {
            return clientes = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                    .setParameter("nombre", name).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }
        public void showquery(List<Cliente> autores) throws Exception{
        System.out.println("\nCLIENTES");
        System.out.println("__________________________");
        System.out.printf("|%-3s|%-13s|%-7s|\n", "ID", "NOMBRE", "ALTA", "");
        for (Cliente aux : autores) {
            System.out.printf("|%-3s|%-13s|%-7s|\n", aux.getId(), aux.getNombre(), "");
        }
        System.out.println("__________________________");
    }
}
