package org.example.proyectofinal.util;


import org.example.proyectofinal.model.Articulo;
import org.example.proyectofinal.model.Contenido;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.example.proyectofinal.util.Constantes.RUTA_CARPETA_PUBLICADOR;

@SuppressWarnings("All")
public class ArchivosUtil {


    public static void crearArticuloXml(String nombrePublicador, Articulo articulo, String ruta) throws IOException {
        String rutaArchivo = String.format("%s/%s/%s/%s.xml", ruta, nombrePublicador,
                "articulos", articulo.getTitulo());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            String xmlContent = crearArticuloXmlString(articulo, nombrePublicador);
            writer.write(xmlContent);
        }
    }

    public static void crearCsv(String ruta) throws IOException {
        String rutaArchivo = String.format("%s/info.csv", ruta);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            String csvContent = crearCsvString();
            writer.write(csvContent);
        }
    }
    public static void agregarArticuloCsv(String nombrePublicador, Contenido contenido, String ruta) throws IOException {
        String rutaArchivo = String.format("%s/info.csv", ruta);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo,true))) {
            String csvContent = agregarArticuloCsvString(contenido, nombrePublicador);
            writer.write(csvContent);
        }
    }


    public static String crearArticuloXmlString(Articulo articulo, String nombrePublicador) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <nitf xmlns="" xmlns:xsi="" xsi:schemaLocation="">
                    </head>
                        <title>%s</title>
                        <docdata>
                            <date.issue>%s</date.issue>
                            <copyright>%s</copyright>
                        </docdata>
                    </head>
                    <body>
                        <body.content>%s</body.content>
                    </body>
                </nitf>
                """.formatted(articulo.getTitulo(), articulo.getFechaPublicacion(), nombrePublicador, articulo.getCuerpo());
    }

    public static String crearCsvString() {
        return "Titulo;Fecha publicacion;Cuerpo;Publicador";
    }
    public static String agregarArticuloCsvString(Contenido contenido, String nombrePublicador) {
        return "\n%s;%s;%s;%s"
                .formatted(contenido.getTitulo(), contenido.getFechaPublicacion(),
                        "\""+contenido.getCuerpo()+"\"", nombrePublicador);
    }

    public static void eliminarCarpeta(String rutaCarpeta) {
        // Convertir la ruta de String a Path
        Path carpeta = Paths.get(rutaCarpeta);

        try {
            Files.walk(carpeta).sorted((a, b) -> {
                return -a.compareTo(b);
            }).forEach((path) -> {
                try {
                    Files.delete(path);
                    System.out.println("Eliminado: " + path);
                } catch (IOException var2) {
                    System.out.println("Error al eliminar " + path + ": " + var2.getMessage());
                }

            });
            System.out.println("Carpeta eliminada exitosamente.");
        } catch (IOException var3) {
            System.out.println("Error al eliminar la carpeta: " + var3.getMessage());
        }
    }

}