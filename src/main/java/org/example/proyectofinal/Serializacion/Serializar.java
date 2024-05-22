package org.example.proyectofinal.Serializacion;



import org.example.proyectofinal.model.Empresa;
import org.example.proyectofinal.model.Usuario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializar {
    private static final String archivo = "src/main/java/org/example/proyectofinal/BaseDatos/Persistencia/empresa.txt";

    public Serializar() {
    }

    public static void serializar(Object objeto) {
        try {
            Throwable var1 = null;
            Object var2 = null;

            try {
                ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo));

                try {
                    salida.writeObject(objeto);
                    System.out.println("Objeto serializado correctamente");
                } finally {
                    if (salida != null) {
                        salida.close();
                    }

                }
            } catch (Throwable var11) {
                if (var1 == null) {
                    var1 = var11;
                } else if (var1 != var11) {
                    var1.addSuppressed(var11);
                }

                throw var1;
            }
        } catch (Throwable var12) {
            var12.printStackTrace(System.out);
        }

    }

    public static Empresa deserializar() {
        Empresa empresa = null;

        try {
            ObjectInputStream recuperando_ficheroConcesionario = new ObjectInputStream(new FileInputStream(archivo));
            empresa = (Empresa)recuperando_ficheroConcesionario.readObject();
            System.out.println("Se recupero el objeto correctamente");
            recuperando_ficheroConcesionario.close();
        } catch (Exception _) {
        }

        return empresa;
    }
}
