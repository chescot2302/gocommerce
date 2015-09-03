/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.uisesion;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.webgocommerce.client.beanproxy.InitParamProxy;
import com.webgocommerce.client.beanproxy.TipoCambioProxy;
import com.webgocommerce.client.model.JHeaderMenu;
import com.webgocommerce.client.model.UIScreenSesion;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.PopupProgress;
import com.webgocommerce.client.uiutil.UIInfoEstado;
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
import com.webgocommerce.client.view.uimenubar.UIMenuBarImpl;
import com.webgocommerce.server.beans.Usuario;
import java.util.HashMap;

/**
 *
 * @author SISTEMAS
 */
public class UISesion extends UIScreenSesion implements InterUISesion,ClickHandler {
    PopupProgress popup = new PopupProgress();
    private JHeaderMenu header;
    private HTML lblTitulo;
    private Image btnLogout;
    private Image btnUpdateGui;
    protected UIInfoEstado infoEstado;
    protected UIMenuBarImpl mbSystem;
    public static TabLayoutPanel tabPanel;
    public static UIHomeBdEmpresa uiBdEmpresa;
    public static UIHomeBdUsuario uiBdUsuario;
    public static UIHomevd uiVdEntrada;
    public static UIHomerv uiRvEntrada;
    public static UIHomeCategoriaLista uiCategoriaLista;
    public static UIHomeLocalidad uiLocalidad;
    public static UIHomeSupervisor uiSupervisor;
    public static UIHomeGerZonal uiGerZonal;
    public static UIHomeCoordinador uiCoordinador;
    public static UIHomeMesa uiMesa;
    public static UIHomeClienteCall uiClienteCall;
    public static UIHomeDocVenta uiDocVenta;
    public static UIHomeRegVentaCe uiRegVenta;
    public static UIHomeParam uiParam;
    public static UIHomeListaPrecio uiListaPrecio;
    public static UIHomeCorreUser uiCorreUser;
    public static UIHomeSucursal uiSucursal;
    public static UIHomeVendedor uiVendedor;
    public static UIHomeMenu uiMenu;
    public static String keyPublic="";
    public static InitParamProxy beanInitParam;
    public static Usuario beanUsuario;
    public static TipoCambioProxy beanTipoCambio;
    public static HashMap<String,String> param=new HashMap();
    

    public UISesion() {
        initComponents();
        initStyle();
        reCalcularWindows();
        initListener();
    }

    private void initComponents() {
        btnLogout=new Image(MyResource.INSTANCE.getImgLogout32());
        btnUpdateGui=new Image(MyResource.INSTANCE.getImgUpdateGui32());
        btnLogout.setTitle("Cerrar Sesion");
        btnUpdateGui.setTitle("Actualizar interfaz");
        header=new JHeaderMenu();
        HorizontalPanel pnlTitulo=new HorizontalPanel();
        lblTitulo = new HTML("Distribuciones M. Olano S.A.C");
        pnlTitulo.add(lblTitulo);
        header.setLeftWidget(pnlTitulo);
        HorizontalPanel pnlImg=new HorizontalPanel();
        pnlImg.add(btnUpdateGui);
        pnlImg.add(btnLogout);
        header.setRightWidget(pnlImg);
        this.setComponent(UIScreenSesion.TITULO, header);
        infoEstado=new UIInfoEstado();
        this.setComponent(UIScreenSesion.ESTADO, infoEstado);
        
        tabPanel = new TabLayoutPanel(2.5, Unit.EM);
        tabPanel.setAnimationDuration(1000);
        this.setComponent(UIScreenSesion.TAB, tabPanel);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
        /*Window.addWindowClosingHandler(new ClosingHandler(){

            @Override
            public void onWindowClosing(Window.ClosingEvent event) {
                logout();
            }
        });*/
    }
    
    private void initListener(){
        btnLogout.addClickHandler(this);
        btnUpdateGui.addClickHandler(this);
        infoEstado.infoTc.lblTipoCambio.addClickHandler(this);
    }

    private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 125;
        int ancho = Window.getClientWidth() - 5;
        tabPanel.setWidth(ancho + "px");
        tabPanel.setHeight(alto + "px");
    }

    private void initStyle() {
        btnLogout.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        btnUpdateGui.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        infoEstado.infoTc.lblTipoCambio.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        lblTitulo.getElement().setId("lblTitulo");
        //lblEstado.getElement().setId("lblEstado");        
        MyResource.INSTANCE.getStlUISesion().ensureInjected();
    }

    @Override
    public void loadInitParam() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadTipoCambioNow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getKeyPublic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadUbicacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClick(ClickEvent event) {
        if(event.getSource().equals(btnLogout)){
            logout();
        }else if(event.getSource().equals(btnUpdateGui)){
            popup.showPopup();
            loadGui();
            popup.hidePopup();
        }else if(event.getSource().equals(infoEstado.infoTc.lblTipoCambio)){
            popup.showPopup();
            loadTipoCambioNow();
            popup.hidePopup();
        }
    }

    @Override
    public void loadMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadParam() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadGui() {
        
    }
}
