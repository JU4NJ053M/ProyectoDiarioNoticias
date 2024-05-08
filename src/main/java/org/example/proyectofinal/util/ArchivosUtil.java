package org.example.proyectofinal.util;


import org.example.proyectofinal.model.Articulo;

import java.io.File;
import java.io.IOException;
import java.io.*;

import static org.example.proyectofinal.util.Constantes.RUTA_CARPETA_PUBLICADOR;

@SuppressWarnings("All")
public class ArchivosUtil {
    public static boolean crearCarpetaPublicador(String nombrePublicador) {

        String feedDeEntradaArticulos = String.format(
                "%s/%s/%s",
                RUTA_CARPETA_PUBLICADOR,
                nombrePublicador,
                "articulos"
        );

        String feedDeEntradaFotos = String.format(
                "%s/%s/%s",
                RUTA_CARPETA_PUBLICADOR,
                nombrePublicador,
                "fotos"
        );

        File creadorCarpetaArticulos = new File(feedDeEntradaArticulos);
        File creadorCarpetaFotos = new File(feedDeEntradaFotos);

        return creadorCarpetaArticulos.mkdirs() && creadorCarpetaFotos.mkdirs();
    }

    public static void crearArticuloXml(String nombrePublicador, Articulo articulo) throws IOException {
        String rutaArchivo = String.format("%s/%s/%s/%s.xml", RUTA_CARPETA_PUBLICADOR, nombrePublicador,
                "articulos", articulo.getTitulo());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            String xmlContent = crearArticuloXmlString(articulo, nombrePublicador);
            writer.write(xmlContent);
        }
    }

    public static void crearCsv() throws IOException {
        String rutaArchivo = String.format("%s/info.csv", RUTA_CARPETA_PUBLICADOR);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            String csvContent = crearCsvString();
            writer.write(csvContent);
        }
    }
    public static void agregarArticuloCsv(String nombrePublicador, Articulo articulo) throws IOException {
        String rutaArchivo = String.format("%s/info.csv", RUTA_CARPETA_PUBLICADOR);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo,true))) {
            String csvContent = agregarArticuloCsvString(articulo, nombrePublicador);
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
    public static String agregarArticuloCsvString(Articulo articulo, String nombrePublicador) {
        return "\n%s;%s;%s;%s"
                .formatted(articulo.getTitulo(), articulo.getFechaPublicacion(),
                        "\""+articulo.getCuerpo()+"\"", nombrePublicador);
    }
}