/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import org.nfunk.jep.JEP;

/**
 *
 * @author mez29
 */
public class Simpson13 {

    public String metodoSimpson13(String a1, String b1, String fx, String particiones) {
         
        String mensaje = "error de datos";
        
        try {
            JEP objJEP = new JEP();
            objJEP.addStandardFunctions(); //agrega funciones matematicas
            objJEP.addStandardConstants(); //agrega constantes matematicas como pi, e...
            objJEP.setImplicitMul(true);
            
            objJEP.parseExpression(a1);
            double a= objJEP.getValue();
            
            objJEP.parseExpression(b1);
            double b= objJEP.getValue();
            
            int n= Integer.parseInt(particiones);
            
            if (n%2!=0){
                throw new Exception("Error");
            }
            
            double h= (b-a)/n;
            
            double x[]=new double[n+1];
            x[0]=a;
            x[n]=b;
            
            for (int i=1; i<x.length -1; i++){
                x[i]=x[0]+(i*h);
            } 
            
            double fxi[]= new double [n+1]; 
            
            double m = 0;
            
            for (int i = 0; i <x.length; i++) {
                fxi[i]=objJEP.addVariable("x", x[i]);
                objJEP.parseExpression(fx);
                fxi[i]=objJEP.getValue();
            }
            for (int i = 1; i <=n-1; i++) {
                if (i%2!=0){
                    m=m+(4*fxi[i]);
                }
                else{
                    m=m+(2*fxi[i]);
                }
            }
            
            m=m + fxi[0]+fxi[n];
            
            m = (h*m)/3;
            
            mensaje = Double.toString(m);
        } 
        catch (Exception e) {
            mensaje = "error de datos";
        
        }
         
        return mensaje; 
    }
    
}
