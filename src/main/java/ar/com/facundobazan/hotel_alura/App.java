package ar.com.facundobazan.hotel_alura;

import ar.com.facundobazan.hotel_alura.views.MenuPrincipal;

public class App {

    public static void main(String[] args) {

        /*
            CREAR USUARIO DEV
            usuario: dev@dev
            contraseña: dev
        AuthService authService = new AuthService();
        authService.RegistrarUsuario(new RegistroUsuario("dev@dev", "dev", "dev"));*/

        var form = new MenuPrincipal();
        form.setVisible(true);
    }
}
