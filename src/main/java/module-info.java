module org.arbol.arbolbinario {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.arbol.arbolbinario to javafx.fxml;
    exports org.arbol.arbolbinario;

    opens org.arbol.arbolbinario.ViewController;
    exports org.arbol.arbolbinario.ViewController;
}