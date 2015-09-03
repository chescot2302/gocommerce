/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gocommerce.main;

import com.gocommerce.server.util.NavaEncrypt;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SISTEMAS
 */
public class Tester {
    public static void main(String arg[]){
        try {
            //System.out.print(NavaEncrypt.decript("¸µª·¸“ š"));
            TimeZone timeZone = TimeZone.getDefault();
            System.out.println(new Date(1437741703393L-timeZone.getOffset(System.currentTimeMillis())));
            System.out.println((new Date()).getTime());
        } catch (Exception ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
