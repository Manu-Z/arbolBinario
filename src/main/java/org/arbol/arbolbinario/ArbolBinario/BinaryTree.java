package org.arbol.arbolbinario.ArbolBinario;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public BinaryTree() {
        this.root = null;
    }

    /// esta Vacio

    public boolean estaVacio(){
        return root == null;
    }
    /// agregar dato
    public void agregarDato(int valor){
        root = insertarRecursivo(root, valor);
    }

    private Node insertarRecursivo(Node nodo, int valor) {
        if (nodo == null) {
            return new Node(valor);
        }

        if (valor < nodo.getValue()) {
            nodo.setLeft(insertarRecursivo(nodo.getLeft(), valor));
        } else if (valor > nodo.getValue()) {
            nodo.setRight(insertarRecursivo(nodo.getRight(), valor));
        }


        return nodo;
    }
    // recorrer arbol inorden
    public void inOrderTraversal() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node current) {
        if (current != null) {
            inOrderRecursive(current.getLeft());
            System.out.print(current.getValue() + " ");
            inOrderRecursive(current.getRight());
        }
    }
    // recorrer arbol posOrden
    public void postOrderTraversal() {
        postOrderRecursive(root);
    }

    private void postOrderRecursive(Node current) {
        if (current != null) {
            postOrderRecursive(current.getLeft());
            postOrderRecursive(current.getRight());
            System.out.print(current.getValue() + " ");
        }
    }
    // recorrer arbol preOrden
    public void preOrderTraversal() {
        preOrderRecursive(root);
    }

    private void preOrderRecursive(Node current) {
        if (current != null) {
            System.out.print(current.getValue() + " ");
            preOrderRecursive(current.getLeft());
            preOrderRecursive(current.getRight());
        }
    }

    /// existe dato

    public boolean existeDato(int valor) {
        return existeRecursivo(root, valor);
    }

    private boolean existeRecursivo(Node nodo, int valor) {
        if (nodo == null) return false;
        if (valor == nodo.getValue()) return true;
        return valor < nodo.getValue() ? existeRecursivo(nodo.getLeft(), valor) : existeRecursivo(nodo.getRight(), valor);
    }

    /// obtener peso
    public int obtenerPeso() {
        return obtenerPesoRecursivo(root);
    }

    private int obtenerPesoRecursivo(Node nodo) {
        if (nodo == null) return 0;
        return 1 + obtenerPesoRecursivo(nodo.getLeft()) + obtenerPesoRecursivo(nodo.getRight());
    }

    /// obtener altura
    public int obtenerAltura() {
        return obtenerAlturaRecursivo(root);
    }

    private int obtenerAlturaRecursivo(Node nodo) {
        if (nodo == null) return 0;
        return Math.max(obtenerAlturaRecursivo(nodo.getLeft()), obtenerAlturaRecursivo(nodo.getRight())) + 1;
    }

    /// obtener nivel
    public int obtenerNivel(int valor) {
        return obtenerNivelRecursivo(root, valor, 1);
    }

    private int obtenerNivelRecursivo(Node nodo, int valor, int nivel) {
        if (nodo == null) return -1;
        if (nodo.getValue() == valor) return nivel;
        int nivelIzq = obtenerNivelRecursivo(nodo.getLeft(), valor, nivel + 1);
        if (nivelIzq != -1) return nivelIzq;
        return obtenerNivelRecursivo(nodo.getRight(), valor, nivel + 1);
    }

    /// contar Hojas
    public int contarHojas() {
        return contarHojasRecursivo(root);
    }

    private int contarHojasRecursivo(Node nodo) {
        if (nodo == null) return 0;
        if (nodo.getLeft()== null && nodo.getRight()== null) return 1;
        return contarHojasRecursivo(nodo.getLeft()) + contarHojasRecursivo(nodo.getRight());
    }

    /// obtener Menor
    public int obtenerMenor() {
        return obtenerMenorRecursivo(root);
    }

    private int obtenerMenorRecursivo(Node nodo) {
        if (nodo.getLeft() == null) return nodo.getValue();
        return obtenerMenorRecursivo(nodo.getLeft());
    }

    // imprimir Amplitud
    public void imprimirAmplitud() {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node nodo = queue.poll();
            System.out.print(nodo.getValue() + " ");

            if (nodo.getLeft() != null) queue.add(nodo.getLeft());
            if (nodo.getRight() != null) queue.add(nodo.getRight());
        }
    }

    //Eliminar dato
    public void eliminarDato(int valor) {
        root  = eliminarRecursivo(root, valor);
    }

    private Node eliminarRecursivo(Node nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.getValue()) {
            nodo.setLeft(eliminarRecursivo(nodo.getLeft(), valor));
        } else if (valor > nodo.getValue()) {
            nodo.setRight( eliminarRecursivo(nodo.getRight(), valor));
        } else {
            if (nodo.getLeft() == null) {
                return nodo.getRight();
            } else if (nodo.getRight() == null) {
                return nodo.getLeft();
            }

            nodo.setValue( obtenerMenorRecursivo(nodo.getRight()));
            nodo.setRight( eliminarRecursivo(nodo.getRight(), nodo.getValue()));
        }
        return nodo;
    }

    // obtnerNodoMayor
    public int obtenerNodoMayor() {
        return obtenerNodoMayorRecursivo(root);
    }

    private int obtenerNodoMayorRecursivo(Node nodo) {
        if (nodo.getRight()== null) return nodo.getValue();
        return obtenerNodoMayorRecursivo(nodo.getRight());
    }


    // borrar el arbol
    public void borrarArbol() {
        root = null;
    }


}
