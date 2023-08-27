module ar.com.facundobazan.hotel_alura {
    requires javafx.fxml;
    requires de.mkammerer.argon2.nolibs;
    requires com.sun.jna;
    requires jakarta.persistence;
    requires java.desktop;
    requires javafx.graphics;
    requires jcalendar;
    requires javafx.controls;

    opens ar.com.facundobazan.hotel_alura to javafx.fxml;
    exports ar.com.facundobazan.hotel_alura.dao;
    exports ar.com.facundobazan.hotel_alura.entities;
    exports ar.com.facundobazan.hotel_alura.services;
    exports ar.com.facundobazan.hotel_alura.utils;
    exports ar.com.facundobazan.hotel_alura.views;
}
