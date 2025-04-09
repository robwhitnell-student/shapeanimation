module edu.guilford {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens edu.guilford to javafx.fxml;
    exports edu.guilford;
}
