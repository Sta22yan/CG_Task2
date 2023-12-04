module com.example.cg_task2_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cg_task2_javafx to javafx.fxml;
    exports com.example.cg_task2_javafx;
}