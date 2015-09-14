package entrega2;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Entrega2 {


    public static void main(String[] args) {
        String user="root";
        String password="rootpassword";
        String user2="jchakon8";//db4free
        String password2="db4password";
        String nombre1;
        String autor1;
        String año1;
        String codigo1;
        String cedula1="0";
        String cedula2="0";        
        int cantidad1=0;
        String area1="r";
        int opc;
        int opc1;
        int opc2;
        String id1="0";
        int aux=0;
        
       
        Scanner lector=new Scanner(System.in);
        int a=0;
        
        int array[]=new int[100];

        for(int j=0;j<99;j++){
            array[j]=0; // espacio libre
        }
        
        

        
        try {
            System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/libros",user,password);
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/libros",user2,password2);

            
            System.out.println("Conexión exitosa.. =D");
            
            Statement estado = con.createStatement();
            ResultSet resultado=estado.executeQuery("SELECT * FROM `libro`");
            ResultSet resultado2=estado.executeQuery("SELECT * FROM `prestamo`");
            
            do{
                System.out.println("**********MENU**********");
                System.out.println("1.Ingresar libro\n2.Modificar libro\n3.Eliminar libro\n4.Ver información de libro\n5.Hacer préstamo\n6.Devolver libro\n7.Ver libros prestados\n8.Salir");
                a=lector.nextInt();
                switch(a){
                    case 1: 
                        System.out.println("Digite el nombre");    
                        nombre1=lector.nextLine();
                        nombre1=lector.nextLine();
                        resultado=estado.executeQuery("SELECT * FROM `libro` WHERE `nombre` LIKE '"+nombre1+"'");
                        if(resultado.next()){
                            System.out.println("El libro ya existe");
                        }else{
                            
                            System.out.println("Digite el autor");    
                            autor1=lector.nextLine();
                            System.out.println("Digite el año de publicación");    
                            año1=lector.nextLine();
                            System.out.println("Digite el código");    
                            codigo1=lector.nextLine();
                            System.out.println("Digite la cantidad");    
                            cantidad1=lector.nextInt();
                            System.out.println("Ingrese el área del libro");
                            System.out.println("1.Física\n2.Química\n3.Tecnología\n4.Cálculo\n5.Programación");
                            opc=lector.nextInt();
                            if(opc==1){
                                area1=lector.nextLine();
                                area1="fisica";
                            }
                            if(opc==2){
                                area1=lector.nextLine();
                                area1="quimica";
                            }
                            if(opc==3){
                                area1=lector.nextLine();
                                area1="tecnologia";
                            }
                            if(opc==4){
                                area1=lector.nextLine();
                                area1="calculo";
                            }
                            if(opc==5){
                                area1=lector.nextLine();
                                area1="programacion";
                            }
                            //opc=lector.nextInt();

                            estado.executeUpdate("INSERT INTO `libro` VALUES(NULL, '"+nombre1+"','"+autor1+"','"+año1+"','"+codigo1+"','"+cantidad1+"','"+area1+"')");
                            System.out.println("Libro agregado con éxito");
                        }
                        break;
                    case 2:
                        System.out.println("Ingrese el nombre del libro a modificar \n\n");
                        nombre1=lector.nextLine();
                        nombre1=lector.nextLine();
                        resultado=estado.executeQuery("SELECT * FROM `libro` WHERE `nombre` LIKE '"+nombre1+"'");
                        if(resultado.next()){
                            resultado.beforeFirst();    
                            while(resultado.next()){
                                id1=resultado.getString("id");
                            }
                            System.out.println("Qué quiere modificar?");
                            System.out.println("1.Nombre\n2.Autor\n3.Año de publicación\n4.Código de libros\n5.Cantidad de libros\n6.Área de libro\n7.Ninguno");
                            opc1=lector.nextInt();
                            switch(opc1){
                                case 1:
                                    nombre1=lector.nextLine();  
                                    System.out.println("Ingrese el nombre del libro");
                                    nombre1=lector.nextLine(); 
                                    estado.executeUpdate("UPDATE `libros`.`libro` SET `nombre` = '"+nombre1+"' WHERE `libro`.`id` = '"+id1+"'");
                                    System.out.println("Modificación realizada");
                                    break;
                                case 2:
                                    autor1=lector.nextLine();
                                    System.out.println("Ingrese el nombre del autor del libro");
                                    autor1=lector.nextLine();
                                    estado.executeUpdate("UPDATE `libros`.`libro` SET `autor` = '"+autor1+"' WHERE `libro`.`id` = '"+id1+"'");
                                    System.out.println("Modificación realizada");
                                    break;
                                case 3:
                                    año1=lector.nextLine();
                                    System.out.println("Ingrese el año de publicación del libro");
                                    año1=lector.nextLine();
                                    estado.executeUpdate("UPDATE `libros`.`libro` SET `año` = '"+año1+"' WHERE `libro`.`id` = '"+id1+"'");
                                    System.out.println("Modificación realizada");
                                    break;
                                case 4:
                                    codigo1=lector.nextLine();
                                    System.out.println("Ingrese el código del libro");
                                    codigo1=lector.nextLine();
                                    estado.executeUpdate("UPDATE `libros`.`libro` SET `codigo` = '"+codigo1+"' WHERE `libro`.`id` = '"+id1+"'");
                                    System.out.println("Modificación realizada");
                                    break;
                                case 5:
                                    //cantidad1=lector.nextInt();
                                    System.out.println("Ingrese la cantidad de libros que hay");
                                    cantidad1=lector.nextInt();
                                    estado.executeUpdate("UPDATE `libros`.`libro` SET `cantidad` = '"+cantidad1+"' WHERE `libro`.`id` = '"+id1+"'");
                                    System.out.println("Modificación realizada");
                                    break;
                                case 6:
                                    System.out.println("Ingrese el área del libro");
                                    System.out.println("1.Física\n2.Química\n3.Tecnología\n4.Cálculo\n5.Programación");
                                    opc2=lector.nextInt();
                                    if(opc2==1){
                                        area1=lector.nextLine();
                                        area1="fisica";
                                        estado.executeUpdate("UPDATE `libros`.`libro` SET `area` = '"+area1+"' WHERE `libro`.`id` = '"+id1+"'");
                                        System.out.println("Modificación realizada");
                                    }
                                    if(opc2==2){
                                        area1=lector.nextLine();
                                        area1="quimica";
                                        estado.executeUpdate("UPDATE `libros`.`libro` SET `area` = '"+area1+"' WHERE `libro`.`id` = '"+id1+"'");
                                        System.out.println("Modificación realizada");
                                    }
                                    if(opc2==3){
                                        area1=lector.nextLine();
                                        area1="tecnologia";
                                        estado.executeUpdate("UPDATE `libros`.`libro` SET `area` = '"+area1+"' WHERE `libro`.`id` = '"+id1+"'");
                                        System.out.println("Modificación realizada");
                                    }
                                    if(opc2==4){
                                        area1=lector.nextLine();
                                        area1="calculo";
                                        estado.executeUpdate("UPDATE `libros`.`libro` SET `area` = '"+area1+"' WHERE `libro`.`id` = '"+id1+"'");
                                        System.out.println("Modificación realizada");
                                    }
                                    if(opc2==5){
                                        area1=lector.nextLine();
                                        area1="programacion";
                                        estado.executeUpdate("UPDATE `libros`.`libro` SET `area` = '"+area1+"' WHERE `libro`.`id` = '"+id1+"'");
                                        System.out.println("Modificación realizada");
                                    }
                                    break;
                                case 7:
                                    System.out.println("Ha decidido no hacer modificaciones");
                                    break;
                                default:
                                    System.out.println("No es una opción válida");
                                    break;
                                }                    
                       //     UPDATE `libros`.`libro` SET `codigo` = 'poiu' WHERE `libro`.`id` = id1;
                        }else{
                            System.out.println("El libro solicitado, no se encuentra en la base de datos");
                        }    
                        break;
                    case 3:
                        System.out.println("Ingrese el nombre del libro a eliminar \n\n");
                        nombre1=lector.nextLine();
                        nombre1=lector.nextLine();
                        resultado=estado.executeQuery("SELECT * FROM `libro` WHERE `nombre` LIKE '"+nombre1+"'");
                        if(resultado.next()){
                            resultado.beforeFirst();    
                            while(resultado.next()){
                                                    
                            }   
                            estado.executeUpdate("DELETE FROM `libro` WHERE `nombre` LIKE '"+nombre1+"'");   
                            System.out.println("Libro eliminado");
                        }else{
                            System.out.println("El libro no existe");
                        }
                        break;
                    case 4:
                        System.out.println("Digite el nombre a buscar \n\n");
                        nombre1=lector.nextLine();
                        nombre1=lector.nextLine();
                        resultado=estado.executeQuery("SELECT * FROM `libro` WHERE `nombre` LIKE '"+nombre1+"'");
                        if(resultado.next()){
                            resultado.beforeFirst();    
                            while(resultado.next()){
                                System.out.println(resultado.getString("id")+"\t"+resultado.getString("nombre")+"\t\t"+resultado.getString("autor")+"\t\t"+resultado.getString("año")+"\t\t"+resultado.getString("codigo")+"\t\t"+resultado.getString("cantidad")+"\t\t"+resultado.getString("area"));
                            }
                        }else{
                            System.out.println("El libro solicitado, no se encuentra en la base de datos");
                        }
                        break;
                    case 5:
                            System.out.println("Qué libro desea prestar?");
                            nombre1=lector.nextLine();
                            nombre1=lector.nextLine();
                            resultado=estado.executeQuery("SELECT * FROM `libro` WHERE `nombre` LIKE '"+nombre1+"'");
                            if(resultado.next()){
                                resultado.beforeFirst();    
                                while(resultado.next()){
                                    id1=resultado.getString("id");
                                     cantidad1=resultado.getInt("cantidad");
                                }
                           
                            if(cantidad1==0){
                                    System.out.println("No hay unidades disponibles");
                                }else{ 
                                    System.out.println("Ingrese el número de cédula");
                                    cedula1=lector.nextLine();
                                    cantidad1--;
                                    estado.executeUpdate("UPDATE `libros`.`libro` SET `cantidad` = '"+cantidad1+"' WHERE `libro`.`id` = '"+id1+"'");
                                    estado.executeUpdate("INSERT INTO `prestamo` VALUES(NULL, '"+cedula1+"','"+nombre1+"')");
                                    System.out.println("Préstamo realizado");
                                }
                        }else{
                            System.out.println("El libro solicitado, no se encuentra en la base de datos");
                        }
                        break;
                    case 6:
                            System.out.println("Qué libro desea devolver?");
                            nombre1=lector.nextLine();
                            nombre1=lector.nextLine();
                            System.out.println("Ingrese la cédula");
                            cedula1=lector.nextLine();
                            aux=0;
                            resultado=estado.executeQuery("SELECT * FROM `libro` WHERE `nombre` LIKE '"+nombre1+"'");
                            resultado2=estado.executeQuery("SELECT * FROM `prestamo` WHERE `libro` LIKE '"+nombre1+"'");

                            if(resultado2.next()){
                                resultado2.beforeFirst();    
                                while(resultado2.next()){
                                       cedula2=resultado2.getString("cedula");
                                       cantidad1=resultado.getInt("cantidad");
                                       id1=resultado.getString("id");
                                       if(cedula2.compareTo(cedula1)==0 && aux==0){
                                           
                                            cantidad1++;
                                            aux=1; 
                                            estado.executeUpdate("UPDATE `libros`.`libro` SET `cantidad` = '"+cantidad1+"' WHERE `libro`.`id` = '"+id1+"'");
                                            estado.executeUpdate("DELETE FROM `prestamo` WHERE `libro` LIKE '"+nombre1+"' AND `cedula` LIKE '"+cedula1+"'");
                                       }else{
                                            System.out.println("El libro solicitado, no se encuentra en la base de datos");

                                       }
                                       

                                }
                            
                           
                            }else{
                                System.out.println("El libro solicitado, no se encuentra en la base de datos");
                            }
                            break;
                    case 7:
                        resultado2=estado.executeQuery("SELECT * FROM `prestamo`");
                        resultado2.beforeFirst();
                        while(resultado2.next()){
                            System.out.println(resultado2.getString("libro"));
                        }
                        
                        
                        
                        break;
                    case 8:
                        System.out.println("Ha decidido salir");
                        break;
                        
                    default:
                        System.out.println("Opción inválida");           
                        break; 
                }
            }while(a!=8);
            /*ResultSet resultado=estado.executeQuery("SELECT * FROM `libros`");
            while(resultado.next()){
            System.out.println(resultado.getString("id")+"\t");//+resultado.getString("name")+"\t\t"+resultado.getString("lastname")+"\t\t"+resultado.getString("phone"));
            }*/
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de mysql");
        } catch(Exception e){
            System.out.println("Se ha encontrado un error que es: "+e.getMessage());
        }
        
        
       
    
    
    }
    
}