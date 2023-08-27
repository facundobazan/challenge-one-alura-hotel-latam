package ar.com.facundobazan.hotel_alura;

import ar.com.facundobazan.hotel_alura.entities.records.RegistroPrecio;
import ar.com.facundobazan.hotel_alura.services.PrecioServicio;
import ar.com.facundobazan.hotel_alura.views.MenuPrincipal;

import java.math.BigDecimal;

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
                    new BigDecimal(99.99), // Precio base
                    new BigDecimal(9.99), // Precio hab. simple
                    new BigDecimal(13.49), // Precio hab. doble
                    new BigDecimal(14.99), // Precio hab. matrimonial
                    new BigDecimal(24.99), // Precio hab. suite
                    new BigDecimal(4.99), // Precio gimnasio
                    new BigDecimal(4.99), // Precio solarium
                    new BigDecimal(0.99), // Precio canales premium
                    new BigDecimal(6.99), // Precio servicio a la hab.
                    new BigDecimal(4.99), // Precio desayuno
                    new BigDecimal(4.99), // Precio merienda
                    new BigDecimal(7.99)  // Precio cena
            );

            precioServicio.ActualizarPrecios(preciosIniciales);
        }

        System.out.println("Precios OK");
    }
}
