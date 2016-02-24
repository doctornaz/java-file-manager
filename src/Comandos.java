import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import static java.nio.file.StandardCopyOption.*;

public class Comandos{

    /**
     * Comando para crear un archivo sencillo.
     * @param filename El nombre del archivo. Ej: "jorge.txt"
     * @param path La ruta a donde se creara del archivo. Ej: C:\Users\Jorge\Documents\Java
     * @throws IOException Arroja IOException si se intenta crear un archivo sin ejecutar el programa
     * sin privilegios elevados (Administrador), ya que no hay permiso para crear el archivo (Ej: en System32)
     */
    static void crearArchivo(String filename, String path){ //CREAR filename, path
        File f = new File(path +"\\"+ filename);
        try{
            if(f.exists()){ //Si el archivo existe, mostrar que ya existe.
                System.out.println("El Archivo ya existe.");
            }
            else{ //Si el archivo NO existe, crearlo y mostrar un mensaje de confirmacion.
                f.createNewFile();
                System.out.println("Archivo "+ f + " creado con EXITO.");
            }
        } catch(Exception e){
            //Atajar el error cuando el usuario intenta crear el archivo en un sitio o manera indebida.
            System.out.println("El archivo no pudo ser creado. Se produjo una " + e.getClass().getSimpleName());
        }
    }

    /**
     * Edtar Archivo .txt
     * @param file Archivo a editar. Con ruta.
     */
    static void editarArchivo(String file){	//EDITAR filename, path
        File f = new File(file);
        if(getFileExt(file).equals("txt")){
            try{
                if(f.exists()){
                    System.out.println("Editando " + f + "\nEscribir >exit para dejar de editar.");
                    Writer editor = new BufferedWriter(new FileWriter(f, true));
                    Scanner scan = new Scanner(System.in);
                    String texto = "";
                    while(!texto.equals(">exit")){
                        editor.append(texto);
                        editor.append("\n");
                        texto = scan.nextLine();
                    }
                    editor.close();
                    System.out.println("Archivo editado con EXITO.");
                }
                else{
                    System.out.println("El archivo " + file + " no existe.");
                }
            } catch (Exception e){
                System.out.println("El archivo no pudo ser creado. Se produjo una " + e.getClass().getSimpleName());
            }

        } else{ //Si el archivo no es un .txt
            System.out.println("El archivo a editar no es un .txt");
        }
    }

    /**
     * Borra un archivo. Si el archivo existe, pregunta si se desea borrar.
     * @param filename Nombre completo con ruta del archivo.
     */
    static void borrarArchivo(String filename){ //BORRAR filename, path
        File archivo = new File(filename);
        if(archivo.exists()){
            System.out.println("Seguro que desea borrar el archivo? Y/N: ");
            String com = new Scanner(System.in).nextLine();
            switch (com){
                case "Y":
                    archivo.delete();
                    System.out.println("Archivo "+ archivo + " borrado con EXITO.");
                    break;
                case "N":
                    System.out.println("No se borro el archivo.");
                    break;
                default:
                    System.out.println("Opcion Invalida.");
            }
        }
        else{
            System.out.println("El archivo no existe.");
        }
    }

    /**
     * Copiar Archivo. Crea una copia exacta de file, la cual se encuentra en origen; en destino.
     * @param file Nombre del archivo a copiar. Ej: "jorge.txt"
     * @param origen Ruta del archivo a copiar.
     * @param destino Destino en donde se creara el archivo.
     * @throws IOException
     */
    static void copiarArchivo(String file, String origen, String destino) throws IOException{ //COPIAR origen, destino
        File archivo = new File(origen+ "\\"+ file);
        Path orig = Paths.get(origen + "\\" +  file);
        Path dest = Paths.get(destino + "\\Copia malvada de " + file);
        if(archivo.exists()){
            Files.copy(orig, dest);
            System.out.println("Archivo " + orig + " copiado con EXITO.");
        }
        else{
            System.out.println("El archivo " + orig + " NO Existe.");
        }
    }

//    /**
//     *
//     * @param file
//     * @param origen
//     * @param destino
//     * @throws IOException
//     */
//    static void MoverArchivo(String file, String origen, String destino) throws IOException{ //MOVER origen, destino
//        File archivo = new File(origen+ "\\"+ file);
//        Path orig = Paths.get(origen + "\\" +  file);
//        Path dest = Paths.get(destino +"\\" +file);
//        if(archivo.exists() == true){
//            Files.move(orig, dest, REPLACE_EXISTING);
//            System.out.println("Archivo movido con EXITO.");
//        }
//        else{
//            System.out.println("El Archivo de origen NO EXISTE.");
//        }
//        Main.main(null);
//    }

    static String getFileExt(String filename){
        int i = filename.lastIndexOf('.');
        return (i >= 0 )? filename.substring(i+1) : filename;
    }

}