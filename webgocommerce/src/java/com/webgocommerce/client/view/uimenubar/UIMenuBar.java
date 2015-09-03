/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uimenubar;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.TabClose;
import com.webgocommerce.client.view.uihomebdempresa.UIHomeBdEmpresa;
import com.webgocommerce.client.view.uihomebdusuario.UIHomeBdUsuario;
import com.webgocommerce.client.view.uihomecategorialista.UIHomeCategoriaLista;
import com.webgocommerce.client.view.uihomeclientecall.UIHomeClienteCall;
import com.webgocommerce.client.view.uihomecoordinador.UIHomeCoordinador;
import com.webgocommerce.client.view.uihomecorreuser.UIHomeCorreUser;
import com.webgocommerce.client.view.uihomedocventa.UIHomeDocVenta;
import com.webgocommerce.client.view.uihomedocventa.UIHomeRegVentaCe;
import com.webgocommerce.client.view.uihomegerzonal.UIHomeGerZonal;
import com.webgocommerce.client.view.uihomelistaprecio.UIHomeListaPrecio;
import com.webgocommerce.client.view.uihomelocalidad.UIHomeLocalidad;
import com.webgocommerce.client.view.uihomemenu.UIHomeMenu;
import com.webgocommerce.client.view.uihomemesa.UIHomeMesa;
import com.webgocommerce.client.view.uihomeparam.UIHomeParam;
import com.webgocommerce.client.view.uihomerv.UIHomerv;
import com.webgocommerce.client.view.uihomesucursal.UIHomeSucursal;
import com.webgocommerce.client.view.uihomesupervisor.UIHomeSupervisor;
import com.webgocommerce.client.view.uihomevd.UIHomevd;
import com.webgocommerce.client.view.uihomevendedor.UIHomeVendedor;
import com.webgocommerce.client.view.uisesion.UISesion;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author SISTEMAS
 */
public class UIMenuBar extends Composite implements InterUIMenuBar{
    public MenuBar menuRoot;
    /*public MenuBar mbSeguridad;
    private MenuItem miBdEmpresa;
    private MenuItem miBdUsuario;
    private MenuItem miMenu;
    public MenuBar mbVentas;
    private MenuItem miVentaDirecta;
    public MenuBar mbMantenimiento;
    private MenuItem miCategoriaLista;    
    private MenuItem miLocalidad;    
    private MenuItem miListaPrecio;
    private MenuItem miUsuarioCorrelativo;
    private MenuItem miSucursal;
    private MenuItem miVendedor;*/
    public static ArrayList<TabClose> tabs = new ArrayList<TabClose>();
    public static HashMap<String,Command> comandos=new HashMap();
    
    public UIMenuBar(){
        initComponents();
        loadCommands();
        initStyle();
    }
    
    private void initComponents(){
        menuRoot=new MenuBar();
        menuRoot.setAnimationEnabled(true);
        menuRoot.setAutoOpen(true);
        //initMenuMain();                
        //initMenuSeguridad();
        //initMenuVentas();
        //initMenuMantenimiento();
        initWidget(menuRoot);
    }
    
    private void initStyle(){
        MyResource.INSTANCE.getStlUIMenuBar().ensureInjected();
    }
    
    /*private void initMenuMain(){
        mbSeguridad=new MenuBar(true);
        menuBar.addItem("Seguridad", mbSeguridad);
        mbVentas=new MenuBar(true);
        menuBar.addItem("Ventas", mbVentas);
        mbMantenimiento=new MenuBar(true);
        menuBar.addItem("Mantenimientos", mbMantenimiento);
    }*/
    
    /*private void initMenuSeguridad(){
        miBdEmpresa=new MenuItem("Bd Empresas",showUIBdEmpresa);        
        miBdUsuario=new MenuItem("Bd Usuario",showUIBdUsuario);   
        miMenu=new MenuItem("Menus",showUIMenu);           
        mbSeguridad.addItem(miBdEmpresa);
        mbSeguridad.addItem(miBdUsuario);      
        mbSeguridad.addItem(miMenu);          
    }*/
    
    /*private void initMenuVentas(){
        miVentaDirecta=new MenuItem("Venta Directa",showUIvdEntrada);        
        mbVentas.addItem(miVentaDirecta);
    }*/
    
    /*private void initMenuMantenimiento(){
        miLocalidad=new MenuItem("Localidad",showUILocalidad); 
        miCategoriaLista=new MenuItem("Categoria Lista",showUICategoriaLista); 
        miListaPrecio=new MenuItem("Lista de Precios",showUIListaPrecio); 
        miUsuarioCorrelativo=new MenuItem("Usuario correlativo",showUICorreUser); 
        miSucursal=new MenuItem("Sucursal",showUISucursal); 
        miVendedor=new MenuItem("Vendedor",showUIVendedor); 
        mbMantenimiento.addItem(miCategoriaLista);
        mbMantenimiento.addItem(miListaPrecio);
        mbMantenimiento.addItem(miUsuarioCorrelativo);
        mbMantenimiento.addItem(miLocalidad);
        mbMantenimiento.addItem(miSucursal);
        mbMantenimiento.addItem(miVendedor);
    }*/        
    
    Command showUIvdEntrada = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiVdEntrada == null) {                
                UISesion.uiVdEntrada = new UIHomevd();                
                TabClose tab = new TabClose(UISesion.uiVdEntrada, "Reg. Venta CP");
                UISesion.tabPanel.add(UISesion.uiVdEntrada, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiVdEntrada);
            }else{
                TabClose tab = new TabClose(UISesion.uiVdEntrada, "Reg. Venta CP");
                UISesion.tabPanel.add(UISesion.uiVdEntrada, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiVdEntrada);
            }
        }

    };
    
    Command showUIrvEntrada = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiRvEntrada == null) {                
                UISesion.uiRvEntrada = new UIHomerv();                
                TabClose tab = new TabClose(UISesion.uiRvEntrada, "Reg. Documento CE");
                UISesion.tabPanel.add(UISesion.uiRvEntrada, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiRvEntrada);
            }else{
                TabClose tab = new TabClose(UISesion.uiRvEntrada, "Reg. Documento CE");
                UISesion.tabPanel.add(UISesion.uiRvEntrada, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiRvEntrada);
            }
        }

    };
    
    Command showUIBdEmpresa = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiBdEmpresa == null) {                
                UISesion.uiBdEmpresa = new UIHomeBdEmpresa();                
                TabClose tab = new TabClose(UISesion.uiBdEmpresa, "BD Empresa");
                UISesion.tabPanel.add(UISesion.uiBdEmpresa, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiBdEmpresa);
            }else{
                TabClose tab = new TabClose(UISesion.uiBdEmpresa, "BD Empresa");
                UISesion.tabPanel.add(UISesion.uiBdEmpresa, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiBdEmpresa);
            }
        }

    };
    
    Command showUIListaPrecio = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiListaPrecio == null) {                
                UISesion.uiListaPrecio = new UIHomeListaPrecio();                
                TabClose tab = new TabClose(UISesion.uiListaPrecio, "Lista de Precios");
                UISesion.tabPanel.add(UISesion.uiListaPrecio, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiListaPrecio);
            }else{
                TabClose tab = new TabClose(UISesion.uiListaPrecio, "Lista de Precios");
                UISesion.tabPanel.add(UISesion.uiListaPrecio, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiListaPrecio);
            }
        }

    };
    
    Command showUICorreUser = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiCorreUser == null) {                
                UISesion.uiCorreUser = new UIHomeCorreUser();                
                TabClose tab = new TabClose(UISesion.uiCorreUser, "Usuario Correlativo");
                UISesion.tabPanel.add(UISesion.uiCorreUser, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiCorreUser);
            }else{
                TabClose tab = new TabClose(UISesion.uiCorreUser, "Usuario Correlativo");
                UISesion.tabPanel.add(UISesion.uiCorreUser, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiCorreUser);
            }
        }

    };
    
    Command showUICategoriaLista = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiCategoriaLista == null) {                
                UISesion.uiCategoriaLista = new UIHomeCategoriaLista();                
                TabClose tab = new TabClose(UISesion.uiCategoriaLista, "Categoria Lista");
                UISesion.tabPanel.add(UISesion.uiCategoriaLista, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiCategoriaLista);
            }else{
                TabClose tab = new TabClose(UISesion.uiCategoriaLista, "Categoria Lista");
                UISesion.tabPanel.add(UISesion.uiCategoriaLista, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiCategoriaLista);
            }
        }

    };
    
    Command showUILocalidad = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiLocalidad == null) {                
                UISesion.uiLocalidad = new UIHomeLocalidad();                
                TabClose tab = new TabClose(UISesion.uiLocalidad, "Localidad");
                UISesion.tabPanel.add(UISesion.uiLocalidad, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiLocalidad);
            }else{
                TabClose tab = new TabClose(UISesion.uiLocalidad, "Localidad");
                UISesion.tabPanel.add(UISesion.uiLocalidad, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiLocalidad);
            }
        }

    };
    
    Command showUISupervisor = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiSupervisor == null) {                
                UISesion.uiSupervisor = new UIHomeSupervisor();                
                TabClose tab = new TabClose(UISesion.uiSupervisor, "Supervisor");
                UISesion.tabPanel.add(UISesion.uiSupervisor, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiSupervisor);
            }else{
                TabClose tab = new TabClose(UISesion.uiSupervisor, "Supervisor");
                UISesion.tabPanel.add(UISesion.uiSupervisor, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiSupervisor);
            }
        }

    };
    
    Command showUIGerenteZonal = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiGerZonal == null) {                
                UISesion.uiGerZonal = new UIHomeGerZonal();                
                TabClose tab = new TabClose(UISesion.uiGerZonal, "Ger. Zonal");
                UISesion.tabPanel.add(UISesion.uiGerZonal, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiGerZonal);
            }else{
                TabClose tab = new TabClose(UISesion.uiGerZonal, "Ger. Zonal");
                UISesion.tabPanel.add(UISesion.uiGerZonal, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiGerZonal);
            }
        }

    };
    
    Command showUICoordinador = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiCoordinador == null) {                
                UISesion.uiCoordinador = new UIHomeCoordinador();                
                TabClose tab = new TabClose(UISesion.uiCoordinador, "Coordinador");
                UISesion.tabPanel.add(UISesion.uiCoordinador, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiCoordinador);
            }else{
                TabClose tab = new TabClose(UISesion.uiCoordinador, "Coordinador");
                UISesion.tabPanel.add(UISesion.uiCoordinador, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiCoordinador);
            }
        }

    };
    
    Command showUIMesa = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiMesa == null) {                
                UISesion.uiMesa = new UIHomeMesa();                
                TabClose tab = new TabClose(UISesion.uiMesa, "Mesa");
                UISesion.tabPanel.add(UISesion.uiMesa, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiMesa);
            }else{
                TabClose tab = new TabClose(UISesion.uiMesa, "Mesa");
                UISesion.tabPanel.add(UISesion.uiMesa, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiMesa);
            }
        }

    };
    
    Command showUIParam = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiParam == null) {                
                UISesion.uiParam = new UIHomeParam();                
                TabClose tab = new TabClose(UISesion.uiParam, "Parametros");
                UISesion.tabPanel.add(UISesion.uiParam, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiParam);
            }else{
                TabClose tab = new TabClose(UISesion.uiParam, "Parametros");
                UISesion.tabPanel.add(UISesion.uiParam, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiParam);
            }
        }

    };
    
    Command showUISucursal = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiSucursal == null) {                
                UISesion.uiSucursal = new UIHomeSucursal();                
                TabClose tab = new TabClose(UISesion.uiSucursal, "Sucursal");
                UISesion.tabPanel.add(UISesion.uiSucursal, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiSucursal);
            }else{
                TabClose tab = new TabClose(UISesion.uiSucursal, "Sucursal");
                UISesion.tabPanel.add(UISesion.uiSucursal, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiSucursal);
            }
        }

    };
    
    Command showUIVendedor = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiVendedor == null) {                
                UISesion.uiVendedor = new UIHomeVendedor();                
                TabClose tab = new TabClose(UISesion.uiVendedor, "Vendedor");
                UISesion.tabPanel.add(UISesion.uiVendedor, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiVendedor);
            }else{
                TabClose tab = new TabClose(UISesion.uiVendedor, "Vendedor");
                UISesion.tabPanel.add(UISesion.uiVendedor, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiVendedor);
            }
        }

    };
    
    Command showUIBdUsuario = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiBdUsuario == null) {                
                UISesion.uiBdUsuario = new UIHomeBdUsuario();                
                TabClose tab = new TabClose(UISesion.uiBdUsuario, "BD Usuario");
                UISesion.tabPanel.add(UISesion.uiBdUsuario, tab);                      
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiBdUsuario);
            }else{
                TabClose tab = new TabClose(UISesion.uiBdUsuario, "BD Usuario");
                UISesion.tabPanel.add(UISesion.uiBdUsuario, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiBdUsuario);
            }
        }

    };
    
    Command showUIMenu = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiMenu == null) {                
                UISesion.uiMenu = new UIHomeMenu();                
                TabClose tab = new TabClose(UISesion.uiMenu, "Menus");
                UISesion.tabPanel.add(UISesion.uiMenu, tab);                      
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiMenu);
            }else{
                TabClose tab = new TabClose(UISesion.uiMenu, "Menus");
                UISesion.tabPanel.add(UISesion.uiMenu, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiMenu);
            }
        }

    };
    
    
    Command showUIClienteCall = new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiClienteCall == null) {                
                UISesion.uiClienteCall = new UIHomeClienteCall();                
                TabClose tab = new TabClose(UISesion.uiClienteCall, "ClienteCall");
                UISesion.tabPanel.add(UISesion.uiClienteCall, tab);                      
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiClienteCall);
            }else{
                TabClose tab = new TabClose(UISesion.uiClienteCall, "ClienteCall");
                UISesion.tabPanel.add(UISesion.uiClienteCall, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiClienteCall);
            }
        }

    };
    
    Command showUIDocVenta= new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiDocVenta == null) {                
                UISesion.uiDocVenta = new UIHomeDocVenta();                
                TabClose tab = new TabClose(UISesion.uiDocVenta, "Doc. Venta CP");
                UISesion.tabPanel.add(UISesion.uiDocVenta, tab);                      
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiDocVenta);
            }else{
                TabClose tab = new TabClose(UISesion.uiDocVenta, "Doc. Venta CP");
                UISesion.tabPanel.add(UISesion.uiDocVenta, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiDocVenta);
            }
        }

    };
    
    Command showUIRegVentaCe= new Command() {

        @Override
        public void execute() {   
            if (UISesion.uiRegVenta == null) {                
                UISesion.uiRegVenta = new UIHomeRegVentaCe();                
                TabClose tab = new TabClose(UISesion.uiRegVenta, "Documento CE");
                UISesion.tabPanel.add(UISesion.uiRegVenta, tab);                      
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiRegVenta);
            }else{
                TabClose tab = new TabClose(UISesion.uiRegVenta, "Documento CE");
                UISesion.tabPanel.add(UISesion.uiRegVenta, tab);                
                tabs.add(tab);
                UISesion.tabPanel.selectTab(UISesion.uiRegVenta);
            }
        }

    };

    @Override
    public void drawMenuBar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadCommands() {
        comandos.put("showUIMenu", showUIMenu);
        comandos.put("showUIBdUsuario", showUIBdUsuario);
        comandos.put("showUIVendedor", showUIVendedor);
        comandos.put("showUISucursal", showUISucursal);
        comandos.put("showUILocalidad", showUILocalidad);
        comandos.put("showUICategoriaLista", showUICategoriaLista);
        comandos.put("showUICorreUser", showUICorreUser);
        comandos.put("showUIListaPrecio", showUIListaPrecio);
        comandos.put("showUIBdEmpresa", showUIBdEmpresa);
        comandos.put("showUIvdEntrada", showUIvdEntrada);
        comandos.put("showUIParam", showUIParam);
        comandos.put("showUIClienteCall", showUIClienteCall);
        comandos.put("showUIDocVenta", showUIDocVenta);
        comandos.put("showUIRegVentaCe", showUIRegVentaCe);
        comandos.put("showUIMesa", showUIMesa);
        comandos.put("showUISupervisor", showUISupervisor);
        comandos.put("showUIGerenteZonal", showUIGerenteZonal);
        comandos.put("showUICoordinador", showUICoordinador);
        comandos.put("showUIrvEntrada", showUIrvEntrada);
    }
}
