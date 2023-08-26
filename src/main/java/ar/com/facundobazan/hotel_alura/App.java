package ar.com.facundobazan.hotel_alura;

import ar.com.facundobazan.hotel_alura.views.MenuPrincipal;

public class App {

    public static void main(String[] args) {

        /*
            TEST
        List<Huesped> huespedes = new ArrayList<>();
        try(EntityManager em = JPAUtil.getEntityManager()){

            HuespedDAO dao = new HuespedDAO(em);
            huespedes = dao.getAll();
        }

        for (Huesped h : huespedes) System.out.println(String.format("%s, %s", h.getApellido(), h.getNombre()));*/

        var form = new MenuPrincipal();
        form.setVisible(true);
    }
}
