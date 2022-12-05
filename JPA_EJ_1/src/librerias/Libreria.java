
package librerias;

import java.util.Scanner;
import libreria.servicio.AutorService;
import libreria.servicio.ClienteService;
import libreria.servicio.EditorialService;
import libreria.servicio.LibroService;
import libreria.servicio.PrestamoService;




public class Libreria {


    public static void main(String[] args) throws Exception {

        LibroService ls = new LibroService();
         AutorService as = new AutorService();
         EditorialService es = new EditorialService();
         ClienteService cs = new ClienteService();
         PrestamoService ps = new PrestamoService();
         Scanner read = new Scanner(System.in);
        
         int answer;  
        do {
            System.out.println("\n============= LIBRERIA =============\n");
            System.out.println("Show option about");
            System.out.println("1) AUTOR\n2) CLIENTE\n3) EDITORIAL\n4) LIBRO\n"
            + "5) PRESTAMO\n6) SALIR\n");
            answer = read.nextInt(); 
            switch (answer){
                case 1:
                    do {
                        System.out.println("\n============= AUTOR =============\n");
                        System.out.println("What would you like to do?");
                        System.out.println("1) Crear autor\n2) Modificar autor\n"
                        + "3) Borrar autor\n4) Buscar autor por nombre\n5) Volver");
                        answer = read.nextInt();
                        switch (answer){
                            case 1: as.createaAuthor();
                            break;
                            case 2: as.modifyAuthor();
                            break;
                            case 3: as.deletedAuthor();
                            break;
                            case 4: System.out.println("ingrese el nombre del autor a buscar");
                                    String nombre = read.next();
                                    as.showquery(as.SearchName(nombre));
                            break;
                            case 5: break;
                            default: System.out.println("respuesta invalida, intetelo nuevamente");                            
                        }                                   
                    } while (answer!=5);
                break;
                case 2:
                    do {
                        System.out.println("\n============= CLIENTE =============\n");
                        System.out.println("What would you like to do?");
                        System.out.println("1) Crear cliente\n2) Modificar cliente\n"
                        + "3) Borrar cliente\n4) Buscar cliente por nombre\n5) Volver");
                        answer = read.nextInt();
                        switch (answer){
                            case 1: cs.createdClient();
                            break;
                            case 2: cs.modifyClient();
                            break;
                            case 3: cs.deletedClient();
                            break;
                            case 4: System.out.println("ingrese el nombre del cliente a buscar");
                                    String nombre = read.next();      
                                    cs.showquery(cs.SearchName(nombre));
                            break;
                            case 5: break;
                            default: System.out.println("respuesta invalida, intetelo nuevamente");
                        }                                   
                    } while (answer!=5);  
                break;                            
                case 3:
                    do {
                        System.out.println("\n============= EDITORIAL =============\n");
                        System.out.println("What would you like to do?");
                        System.out.println("1) Crear editorial\n2) Modificar editorial\n"
                        + "3) Borrar editorial\n4) Buscar editorial por nombre\n5) Volver");
                        answer = read.nextInt();
                        switch (answer){
                            case 1: es.createdEditorial();
                            break;
                            case 2: es.modifyEditorial();
                            break;
                            case 3: es.removeEditorial();
                            break;
                            case 4: System.out.println("ingrese el nombre de la editorial a buscar");
                                    String nombre = read.next();      
                                    es.showquery(es.searchNombre(nombre));
                            break;
                            case 5: break;
                            default: System.out.println("respuesta invalida, intetelo nuevamente");
                        }        
                    } while (answer!=5);  
                break;     
                case 4:
                    do {
                        System.out.println("\n============= LIBRO =============\n");
                        System.out.println("What would you like to do?");
                        System.out.println("1) Crear libro\n2) Borrar libro\n3) Buscar libro por titulo\n"
                        + "4) Buscar libro por autor\n5) Buscar libro por titulo\n6) Volver");
                        answer = read.nextInt();
                        switch (answer){
                            case 1: ls.createdBook();
                            break;
                            case 2: ls.removeBook();
                            break;
                            case 3: System.out.println("ingrese el titulo del libro a buscar");
                                    String titulo = read.next();
                                    ls.showquery(ls.searchTitulo(titulo));
                            break;
                            case 4: System.out.println("ingrese el autor del libro a buscar");
                                    String autor = read.next();
                                    ls.showquery(ls.searchAutor(autor));
                            break;
                            case 5: System.out.println("ingrese la editorial del libro a buscar");
                                    String editorial = read.next();   
                                    ls.showquery(ls.searchEditorial(editorial));
                            break;                            
                            case 6: break;
                            default: System.out.println("respuesta invalida, intetelo nuevamente");
                        }                                      
                    } while (answer!=6);  
                break;
                case 5:
                    do {
                        System.out.println("\n============= PRESTAMO =============\n");
                        System.out.println("What would you like to do?");
                        System.out.println("1) Crear prestamo\n2) Modificar prestamo\n"
                        + "3) Borrar prestamo\n4) Buscar prestamo por cliente\n5) Volver");
                        answer = read.nextInt();
                        switch (answer){
                            case 1: ps.createdLoan();
                            break;
                            case 2: ps.modificarPrestamo();
                            break;
                            case 3: ps.removeLoan();
                            break;
                            case 4: System.out.println("ingrese el nombre del cliente a buscar");
                                    String nombre = read.next();      
                                    ps.showquery(ps.searchClient(nombre));
                            break;
                            case 5: break;
                            default: System.out.println("respuesta invalida, intetelo nuevamente");
                        }        
                    } while (answer!=5);  
                break; 
                case 6: break;
                default: System.out.println("respuesta invalida, intetelo nuevamente");
            }        
        } while (answer!=6); 
    }
        
    }
    


