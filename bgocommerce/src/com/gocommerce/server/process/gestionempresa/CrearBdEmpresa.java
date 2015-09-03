/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.server.process.gestionempresa;

/**
 *
 * @author SISTEMAS
 */
public class CrearBdEmpresa {
    public static Boolean crearBdEmpresa(){
        if(NivelFisico.crearEmpresa()){
            return NivelLogico.insertarBdEmpresa();
        }
        return false;
    }
    
    private static class NivelFisico{
        public static boolean crearEmpresa(){
            return true;
        }       
    }
    
    private static class NivelLogico{
        public static boolean insertarBdEmpresa(){
            return true;
        }
    }
}
