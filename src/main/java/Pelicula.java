import lombok.Data;

import java.io.*;
import java.util.ArrayList;

@Data
public class Pelicula {
    private int id;
    private String titulo;
    private int anho;
    private String director;
    private String genero;

    public Pelicula(int id, String titulo, int anho, String director, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.director = director;
        this.genero = genero;
    }

    public static void filtrarPorAnho(Integer anho, Boolean posterior){

        ArrayList<Pelicula> peliculas=new ArrayList<>();
        String linea;
        int anhonuevo;
        String directorNuevo;

        try(BufferedReader br = new BufferedReader(new FileReader("peliculas 1.csv"));
            ){
            while((linea= br.readLine())!=null){

                String[] trozos = linea.split(",");
                Pelicula pel=new Pelicula(Integer.parseInt(trozos[0]),trozos[1],Integer.parseInt(trozos[2]),trozos[3],trozos[4]);

                //Si posterior es TRUE
                if(posterior){
                    //Filtra la pelicula a meter en el array peliculas si son igual o posteriores al año
                    if(pel.getAnho()>=anho){
                        anhonuevo=pel.getAnho();
                        directorNuevo=pel.getDirector();
                        peliculas.add(pel);

                        //Escribir en nuevo archivo
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter("posterior"+pel.getAnho()+".csv"));){
                            bw.write(linea);
                            bw.newLine();

                        }

                        //Si no, lee la siguiente linea
                    }else br.readLine();

                //Si posterior es FALSE
                }else{
                    if(pel.getAnho()<anho){
                        anhonuevo=pel.getAnho();
                        directorNuevo=pel.getDirector();
                        peliculas.add(pel);

                        //Escribir en nuevo archivo
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter("anterior"+pel.getAnho()+".csv"));){
                            bw.write(linea);
                            bw.newLine();
                        }

                        //Si no, lee la siguiente linea
                    }else br.readLine();
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void filtrarPorTitulo(String cadena){
        ArrayList<Pelicula> peliculas=new ArrayList<>();
        String linea;
        String directorNuevo;

        try(BufferedReader br = new BufferedReader(new FileReader("peliculas 1.csv"));
        ){
            while((linea= br.readLine())!=null){
                String[] trozos = linea.split(",");
                Pelicula pel=new Pelicula(Integer.parseInt(trozos[0]),trozos[1],Integer.parseInt(trozos[2]),trozos[3],trozos[4]);
                //Filtra la pelicula a meter en el array por el titulo
                if(cadena.equals(pel.getTitulo())){
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(pel.getTitulo()+".csv"))){
                        bw.write(linea);
                        bw.newLine();
                    }
                    //Si no, lee la siguiente linea
                }else br.readLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
