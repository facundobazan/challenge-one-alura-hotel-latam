package ar.com.facundobazan.hotel_alura;

import ar.com.facundobazan.hotel_alura.entities.records.RegistroPrecio;
import ar.com.facundobazan.hotel_alura.services.PrecioServicio;
import ar.com.facundobazan.hotel_alura.views.MenuPrincipal;

public class App {

    public static void main(String[] args) {

        inicializarPrecios();
        /*
            CREAR USUARIO DEV
            usuario: dev@dev
            contraseña: dev
        */
        //AuthService authService = new AuthService();
        //authService.RegistrarUsuario(new RegistroUsuario("dev@dev", "dev", "dev"));

        var form = new MenuPrincipal();
        form.setVisible(true);
    }

    private static void inicializarPrecios() {

        PrecioServicio precioServicio = new PrecioServicio();
        RegistroPrecio ultimoRegistro = precioServicio.obtenerUltimaActualizacion();
        if (ultimoRegistro == null){

            System.out.println("Inicializando precios...");

            RegistroPrecio preciosIniciales = new RegistroPrecio(
                    99.99, // Precio base
                    9.99, // Precio hab. simple
                    13.49, // Precio hab. doble
                    14.99, // Precio hab. matrimonial
                    24.99, // Precio hab. suite
                    4.99, // Precio gimnasio
                    4.99, // Precio solarium
                    0.99, // Precio canales premium
                    6.99, // Precio servicio a la hab.
                    4.99, // Precio desayuno
                    4.99, // Precio merienda
                    7.99  // Precio cena
            );

            precioServicio.ActualizarPrecios(preciosIniciales);
        }

        System.out.println("Precios OK");
    }
}
