/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.shared;

import com.google.gwt.user.client.Window;
import com.webgocommerce.client.uiutil.Notification;

/**
 *
 * @author SISTEMAS
 */
public class FieldVerifier {

    public static boolean isValidDNI(String name) {
        if (isEmpty(name)) {
            //Window.alert("DNI es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"DNI es un campo obligatorio");
            not.showPopup();
            return false;
        } else if (name.length() != 8) {
            //Window.alert("DNI debe tener 8 digitos");
            Notification not=new Notification(Notification.ALERT,"DNI debe tener 8 digitos");
            not.showPopup();
            return false;
        } else if(!isCadenaNumberEntero(name)){
            //Window.alert("DNI solo acepta valores numericos");
            Notification not=new Notification(Notification.ALERT,"DNI solo acepta valores numericos");
            not.showPopup();
            return false;
        } else {
            return true;
        }
            
    }

    public static boolean isEmpty(String name) {
        if (name == null) {
            return true;
        } else if (name.isEmpty()) {
            return true;
        } else if (name.length() == 0) {
            return true;
        } else if (name.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidRUC(String name) {
        if (isEmpty(name)) {
            //Window.alert("RUC es un campo obligatorio");
            Notification not=new Notification(Notification.ALERT,"RUC es un campo obligatorio");
            not.showPopup();
            return false;
        } else if (name.length() != 11) {
            //Window.alert("RUC debe tener 11 digitos");
            Notification not=new Notification(Notification.ALERT,"RUC debe tener 11 digitos");
            not.showPopup();
            return false;
        } else if(!isCadenaNumberEntero(name)){
            //Window.alert("RUC solo acepta valores numericos");
            Notification not=new Notification(Notification.ALERT,"RUC solo acepta valores numericos");
            not.showPopup();
            return false;
        } else{
            int[][] arrayRuc=new int[11][3];
            int suma=0;
            for (int i = 0; i < name.length(); i++) {
                    arrayRuc[i][0]=Integer.parseInt(String.valueOf(name.charAt(i)));
                    switch(i){
                        case 0:
                            arrayRuc[i][1]=5;
                            arrayRuc[i][2]=5*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 1:
                            arrayRuc[i][1]=4;
                            arrayRuc[i][2]=4*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 2:
                            arrayRuc[i][1]=3;
                            arrayRuc[i][2]=3*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 3:
                            arrayRuc[i][1]=2;
                            arrayRuc[i][2]=2*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 4:
                            arrayRuc[i][1]=7;
                            arrayRuc[i][2]=7*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 5:
                            arrayRuc[i][1]=6;
                            arrayRuc[i][2]=6*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 6:
                            arrayRuc[i][1]=5;
                            arrayRuc[i][2]=5*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 7:
                            arrayRuc[i][1]=4;
                            arrayRuc[i][2]=4*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 8:
                            arrayRuc[i][1]=3;
                            arrayRuc[i][2]=3*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 9:
                            arrayRuc[i][1]=2;
                            arrayRuc[i][2]=2*Integer.parseInt(String.valueOf(name.charAt(i)));
                            suma=suma+arrayRuc[i][2];
                            break;
                        case 10:
                            arrayRuc[i][1]=0;
                            arrayRuc[i][2]=0*Integer.parseInt(String.valueOf(name.charAt(i)));;
                            suma=suma+arrayRuc[i][2];
                            break;                        
                    }
            }     
            int cociente=suma/11;
            int ultimoDigito=11-(suma-(cociente*11));
            if(ultimoDigito==arrayRuc[10][0]){               
                return true;
            }else{
                //Window.alert("Ruc invalido");
                Notification not=new Notification(Notification.ALERT,"Ruc invalido");
                not.showPopup();
                return false;
            }            
        }
        
    }

    public static boolean isNumber(String name) {
        try {
            Double numero = Double.parseDouble(name);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean notIsNumberPositivo(String name, String dato) {
        try {
            Double numero = Double.parseDouble(name);
            if (numero >= 0) {
                return false;
            } else {
                //Window.alert(dato + " es un campo numerico positivo");
                Notification not=new Notification(Notification.ALERT,dato + " es un campo numerico positivo");
                not.showPopup();
                return true;
            }
        } catch (Exception ex) {
            //Window.alert(dato + " es un campo numerico positivo");
            Notification not=new Notification(Notification.ALERT,dato + " es un campo numerico positivo");
            not.showPopup();
            return true;
        }
    }

    public static boolean notIsEnteroPositivo(String name, String dato) {
        if (isCadenaNumberEntero(name)) {
            int numero = Integer.parseInt(name);
            if (numero >= 0) {
                return false;
            } else {
                //Window.alert(dato + " es un campo entero positivo");
                Notification not=new Notification(Notification.ALERT,dato + " es un campo entero positivo");
                not.showPopup();
                return true;
            }
        } else {
            //Window.alert(dato + " es un campo entero positivo");
            Notification not=new Notification(Notification.ALERT,dato + " es un campo entero positivo");
            not.showPopup();
            return true;
        }
    }

    public static boolean isCadenaNumberEntero(String name) {
        if (name.length() > 0) {
            for (int i = 0; i < name.length(); i++) {
                try {
                    Integer.parseInt(String.valueOf(name.charAt(i)));
                } catch (Exception ex) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean areStringEguals(String cadena1, String cadena2) {
        if (isEmpty(cadena1)) {
            return false;
        }
        if (isEmpty(cadena2)) {
            return false;
        }
        if (cadena1.equalsIgnoreCase(cadena2)) {
            return true;
        } else {
            return false;
        }
    }
}
