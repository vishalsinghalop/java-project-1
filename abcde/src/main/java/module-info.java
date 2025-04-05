module com.example.abcde {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.almasb.fxgl.all;
    requires org.json;
    requires okhttp3;

    opens com.example.abcde to javafx.fxml;
    exports com.example.abcde;
}
