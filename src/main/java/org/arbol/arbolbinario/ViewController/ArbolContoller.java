package org.arbol.arbolbinario.ViewController;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.arbol.arbolbinario.ArbolBinario.BinaryTree;
import org.arbol.arbolbinario.ArbolBinario.Node;

public class ArbolContoller {

    private BinaryTree arbol = new BinaryTree();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnInorden;

    @FXML
    private Button btnPosOrden;

    @FXML
    private Button btnPreorden;

    @FXML
    private TextArea textareaRecorrido;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtIngesarValor;

    @FXML
    private TextField txtMayor;

    @FXML
    private TextField txtMenor;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtnumHojas;
    @FXML
    private AnchorPane paneArbol;


    @FXML
    void OnAgregar(ActionEvent event) {
        try {
            int valor = Integer.parseInt(txtIngesarValor.getText());
            arbol.agregarDato(valor);
            txtIngesarValor.clear();

            // Mostrar recorridos actualizados
            mostrarRecorridos();
            mostrarInformacion();
            dibujarArbolGrafico();

        } catch (NumberFormatException e) {
            textareaRecorrido.setText("Por favor ingrese un número válido.");
        }
    }

//// mostrar

    private void mostrarInformacion() {
        txtAltura.setText(String.valueOf(arbol.obtenerAltura()));
        txtPeso.setText(String.valueOf(arbol.obtenerPeso()));
        txtnumHojas.setText(String.valueOf(arbol.contarHojas()));
        txtMayor.setText(String.valueOf(arbol.obtenerNodoMayor()));
        txtMenor.setText(String.valueOf(arbol.obtenerMenor()));
    }

// mostrar
    private void mostrarRecorridos() {

            StringBuilder sb = new StringBuilder();
            sb.append("Inorden: ");
            capturarInorden(sb);
            sb.append("\nPreorden: ");
            capturarPreorden(sb);
            sb.append("\nPostorden: ");
            capturarPostorden(sb);
            textareaRecorrido.setText(sb.toString());


    }

    private void capturarInorden(StringBuilder sb) {
        capturarInordenRecursivo(arbol.getRoot(), sb);
    }
    private void capturarInordenRecursivo(Node nodo, StringBuilder sb) {
        if (nodo != null) {
            capturarInordenRecursivo(nodo.getLeft(), sb);
            sb.append(nodo.getValue()).append(" ");
            capturarInordenRecursivo(nodo.getRight(), sb);
        }
    }

    private void capturarPreorden(StringBuilder sb) {
        capturarPreordenRecursivo(arbol.getRoot(), sb);
    }
    private void capturarPreordenRecursivo(Node nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.getValue()).append(" ");
            capturarPreordenRecursivo(nodo.getLeft(), sb);
            capturarPreordenRecursivo(nodo.getRight(), sb);
        }
    }

    private void capturarPostorden(StringBuilder sb) {
        capturarPostordenRecursivo(arbol.getRoot(), sb);
    }
    private void capturarPostordenRecursivo(Node nodo, StringBuilder sb) {
        if (nodo != null) {
            capturarPostordenRecursivo(nodo.getLeft(), sb);
            capturarPostordenRecursivo(nodo.getRight(), sb);
            sb.append(nodo.getValue()).append(" ");
        }
    }



    @FXML
    void OnbtnInorden(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        capturarInorden(sb);
        textareaRecorrido.setText("Recorrido Inorden:\n" + sb.toString());
    }

    @FXML
    void Onpreorden(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        capturarPreorden(sb);
        textareaRecorrido.setText("Recorrido Preorden:\n" + sb.toString());
    }

    @FXML
    void OnposOrden(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        capturarPostorden(sb);
        textareaRecorrido.setText("Recorrido Postorden:\n" + sb.toString());
    }



    @FXML
    void Oneliminar(ActionEvent event) {
        try {
            int valor = Integer.parseInt(txtIngesarValor.getText());
            arbol.eliminarDato(valor);
            txtIngesarValor.clear();


            mostrarRecorridos();
            mostrarInformacion();
            dibujarArbolGrafico();

        } catch (NumberFormatException e) {
            textareaRecorrido.setText("Por favor ingrese un número válido para eliminar.");
        }
    }

    private void dibujarArbolGrafico() {
        paneArbol.getChildren().clear(); // Limpia el pane antes de redibujar
        if (arbol.getRoot() != null) {
            dibujarNodo(arbol.getRoot(), paneArbol.getWidth() / 2, 30, paneArbol.getWidth() / 4, 1);
        }
    }

    private void dibujarNodo(Node nodo, double x, double y, double offset, int nivel) {
        if (nodo == null) return;

        // Dibuja conexiones con hijos
        if (nodo.getLeft() != null) {
            double xHijo = x - offset;
            double yHijo = y + 60;
            paneArbol.getChildren().add(new javafx.scene.shape.Line(x, y, xHijo, yHijo));
            dibujarNodo(nodo.getLeft(), xHijo, yHijo, offset / 1.7, nivel + 1);
        }

        if (nodo.getRight() != null) {
            double xHijo = x + offset;
            double yHijo = y + 60;
            paneArbol.getChildren().add(new javafx.scene.shape.Line(x, y, xHijo, yHijo));
            dibujarNodo(nodo.getRight(), xHijo, yHijo, offset / 1.7, nivel + 1);
        }

        // Dibuja el nodo (círculo + texto)
        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(x, y, 20);
        circle.setFill(javafx.scene.paint.Color.LIGHTBLUE);
        circle.setStroke(javafx.scene.paint.Color.BLACK);

        javafx.scene.text.Text texto = new javafx.scene.text.Text(String.valueOf(nodo.getValue()));
        texto.setX(x - 6);
        texto.setY(y + 5);

        paneArbol.getChildren().addAll(circle, texto);
    }




    @FXML
    void initialize() {

    }

}
