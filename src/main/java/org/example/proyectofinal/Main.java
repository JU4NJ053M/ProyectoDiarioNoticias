package org.example.proyectofinal;

import org.example.proyectofinal.controller.ModelFactoryController;
import org.example.proyectofinal.model.Articulo;
import org.example.proyectofinal.model.Publicador;
import org.example.proyectofinal.util.ArchivosUtil;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

        Publicador p1 = new Publicador("Juan@gmail.com", "1234", "Juan Jose",
                "1111", new ArrayList<>(),new ArrayList<>(),
                "https://www.eltiempo.com/");

        Publicador p2 = new Publicador("Maicol@gmail.com", "265", "Maicol",
                "1112", new ArrayList<>(),new ArrayList<>(),
                "https://www.eltiempo.com/");

        Publicador p3 = new Publicador("Hernan@gmail.com", "958", "Hernan",
                "1112", new ArrayList<>(),new ArrayList<>(),
                "https://www.eltiempo.com/");



        modelFactoryController.agregarPublicador(p1);
        modelFactoryController.agregarPublicador(p2);
        modelFactoryController.agregarPublicador(p3);

        Articulo articulo1 = new Articulo("123","Las mujeres", "05/03/2023",
                "El 5 de octubre de 1789, las mujeres parisinas partieron desde los barrios obreros hacia la residencia real de Versailles, este suceso dió comienzo a la revolución.");
        Articulo articulo2 = new Articulo("234","Sociedad francesa", "05/09/2005",
                "La sociedad francesa estaba dividida en estamentos dependiendo de sus clases sociales, el poder mas alto lo tenía el rey, detrás estaban la nobleza y el clero y el nivel mas bajo de poder lo tenia el tercer estado que estaba constituido por la burguesía, los artesanos y los campesinos.");
        Articulo articulo3 = new Articulo("546","Vida de los flamencos", "05/09/2005",
                "Los flamencos son aves gregarias altamente especializadas, que habitan sistemas salinos de donde obtienen su alimento (compuesto generalmente de algas microscópicas e invertebrados) y materiales para desarrollar sus hábitos reproductivos.");
        Articulo articulo4 = new Articulo("876","tigres blancos", "08/07/2014",
                "Un tigre que cuando cachorro habia sido capturado por humanos fue liberado luego de varios años de vida domestica. La vida entre los hombres no habia menguado sus fuerzas ni sus instintos en cuanto; lo liberaron, corrio a la selva. Ya en la espesura, sus hermanos teniéndolo otra vez entre ellos, le preguntaron:");

        modelFactoryController.agregarArticulo(articulo1,p1);
        modelFactoryController.agregarArticulo(articulo2,p2);
        modelFactoryController.agregarArticulo(articulo3,p3);
        modelFactoryController.agregarArticulo(articulo4,p3);

    }
}
