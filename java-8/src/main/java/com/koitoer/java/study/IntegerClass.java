package com.koitoer.java.study;

public class IntegerClass {

    public static void main(String[] args) {
        int valor = 0;
        new IntegerClass().myMethod(valor);
        System.out.println("ValorMain -> " + valor);

        int returnValue = new IntegerClass().myMethodReturn(valor);
        System.out.println("ValorMainReturn -> " + returnValue);
    }

    private void myMethod(int valor) {
        valor++;
        System.out.println("MyMethod - > " + valor);
        if (valor == 10) {
            return;
        } else {
            myMethod(valor);
        }
    }

    private int myMethodReturn(int valor){
        int aux = valor ++ ;
        System.out.println("MyMethod - > " + aux);
        if (aux == 10) {
            return aux;
        } else {
            return myMethodReturn(aux);
        }
    }
}
