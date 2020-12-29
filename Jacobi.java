
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mez29
 */
class Jacobi {
    int filas;
    int columnas;
    double m[][];

    public Jacobi(double[][] m) {
        this.m = m;
        this.filas = m.length;
        this.columnas = m[0].length;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void imprimirMatriz() {
    for (int i=0; i<m.length; i++){
        for (int j= 0; j< m[i].length; j++){
            if (m [i][j] >=0){
                System.out.printf("\t%s%.2f", " ", m[i][j]);
            }
            else {
                System.out.printf("\t%.2f", m[i][j]);
            }
            
        }
        System.out.println("");
    }
    System.out.println("");
    System.out.println("");  
    }
    public void llenarMatriz(){
        Scanner entrada = new Scanner(System.in);
        for (int i=0; i< m.length; i++){
            for (int j=0; j<m[i].length; j++){
                System.out.print("Ingrese el elemento [ " + (i + 1) + "," + (j + 1 ) + "]: ");
                m[i][j] = entrada.nextInt();
            }
        }
        System.out.println("");
    }
    public void llenarVector(double v[]){
        Scanner entrada = new Scanner (System.in);
        for (int i=0; i<v.length; i++){
            System.out.print("Ingrese el elemnto [ " + (i+1) + "]:");
            v[i] = entrada.nextDouble();
        }
        System.out.println(" ");
    }
    public double [] restaVectores(double v1[], double v2[]){
        double resta [] = new double [v1.length];
        for (int i=0; i<resta.length; i++){
            resta[i] = v1[i]-v2[i];
        }
        return resta;
    }
    public double normaVector(double v[]){
        double norma=0.0;
        double suma= 0.0;
        for (int i=0; i<v.length; i++){
            suma += Math.pow(v[i], 2);
        }
        norma = Math.sqrt(suma);
        return norma;
    }
    public double[] Jacobi (double b[], double epsilon){
        int N = m.length;
        
        double X_Anterior[] = new double[N];
        for (int i=0; i<X_Anterior.length; i++){
            X_Anterior[i]= 0.0;
        }
        double X_Actual[]=new double[N];
        for (int i=0; i<X_Actual.length; i++){
            X_Actual[i]= 0.0;
        }
        double norma = 1.0;
        double sumaAux;
        int contadorIteraciones =0;
        
        while((norma/normaVector(X_Actual))>=epsilon){
            sumaAux =0.0;
            contadorIteraciones++;
            
            System.out.println("Iteracio " + contadorIteraciones + ":");
            
            for (int i=0; i<X_Anterior.length; i++){
                X_Anterior[i] = X_Actual[i];
            }
            for (int i=0; i<=N-1; i++){
                for(int j=0; j<=N-1; j++){
                    if (j!=i){
                        sumaAux += m[i][j]*X_Anterior[j];
                    }
                }
                X_Actual[i]=(b[i]-sumaAux)/m[i][i];
                System.out.println("X["+(i+1)+"]:" + X_Actual[i]);
                sumaAux=0.0;
            }
            System.out.println("");
            System.out.println("");
            
            norma = normaVector(restaVectores(X_Actual, X_Anterior));
        }
        System.out.println("");
        return X_Actual;
    }
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nIngrese la dimensión de la matriz: ");
        int dim= entrada.nextInt();
        double A[][]=new double [dim][dim];
        Jacobi matriz = new Jacobi(A);
        matriz.llenarMatriz();
        System.out.println("\nLa matriz ingresada A es: \n");
        matriz.imprimirMatriz();
        
        double b[]=new double [dim];
        System.out.println("\nIngrese el vector b: ");
        matriz.llenarVector(b);
        double epsilon;
        System.out.println("\nIngrese el criterio de iteración: ");
        epsilon= entrada.nextDouble();
        
        System.out.println("\nAproximando la solución del sistema de ecuaciones" + "por el metodo de Jacobi...");
        System.out.println("\nEl vector de aproximación inicial es [0, 0, 0, 0]\n");
        
        matriz.Jacobi(b, epsilon);
    }
}
