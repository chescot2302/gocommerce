/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uidocventa;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.webgocommerce.client.beanproxy.CabeceraVentaProxy;
import com.webgocommerce.client.uiutil.UIFormMantenimiento;
import com.webgocommerce.client.uiutil.UISeparador;
import com.webgocommerce.client.view.grid.GridCabeceraVenta;
import com.webgocommerce.client.view.grid.GridDetailVenta;
import com.webgocommerce.client.view.uisesion.UISesion;

/**
 *
 * @author SISTEMAS
 */
public class UIDocVenta extends UIFormMantenimiento implements InterUIDocVenta, KeyUpHandler,ClickHandler{
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DefaultFormat defaultFormat=new DefaultFormat(dateFormat);
    private Label lblDesde;
    protected DateBox boxDateIni;
    private Label lblHasta;
    protected DateBox boxDateFin;
    private Label lblExcluirFechas;
    protected CheckBox chkExcluirFechas;
    private Label lblSoloAnulados;
    protected CheckBox chkSoloAnulados;
    protected MSearchBox txtBuscar;
     private HorizontalPanel pnlToolDoc;
     protected GridCabeceraVenta grid;
     protected GridDetailVenta grid2;
     private FlexTable pnlData;
     private FlexTable pnlSearch;
     private Label lblDocumento;
     protected Label lblTipoDoc;
     protected Label lblSerie;
     private Label lblSeparador;
     protected Label lblPreImpreso;
     protected String docSeries;
     protected ListBox lstFiltro;
     protected CabeceraVentaProxy headVenta;
     protected Button btnCambiarEstado;
     protected Button btnExportar;
     
    
    public UIDocVenta(){
        initComponents();
        initStyle();
        initListener();
        reCalcularWindows();
    }
    
    private void initComponents(){
        btnCambiarEstado=new Button("Ver estado");
        btnExportar=new Button("Exportar");
        lstFiltro=new ListBox();
        lstFiltro.addItem("DOC. CLIENTE", "RUCCLIENTE");
        lstFiltro.addItem("NOM. CLIENTE", "DESCCLIENTE");
        lstFiltro.addItem("CORRELATIVO", "CORRELATIVO");
        pnlSearch=new FlexTable();
        chkExcluirFechas=new CheckBox();
        chkSoloAnulados=new CheckBox();
        boxDateIni=new DateBox(new DatePicker(),UISesion.beanInitParam.getFechaServer(),defaultFormat);        
        boxDateFin=new DateBox(new DatePicker(),UISesion.beanInitParam.getFechaServer(),defaultFormat);
        lblDesde=new Label("Fecha Emision:  Desde");
        lblHasta=new Label("Hasta");
        lblExcluirFechas=new Label("ExcluirFechas");
        lblSoloAnulados=new Label("Mostrar solo Anulados");
        pnlSearch.setWidget(0, 0, lblDesde);
        pnlSearch.setWidget(0, 1, boxDateIni);
        pnlSearch.setWidget(0, 2, lblHasta);
        pnlSearch.setWidget(0, 3, boxDateFin);
        pnlSearch.setWidget(0, 4, lblExcluirFechas);
        pnlSearch.setWidget(0, 5, chkExcluirFechas);        
        pnlSearch.setWidget(0, 6, new UISeparador());        
        pnlSearch.setWidget(0, 7, lblSoloAnulados);        
        pnlSearch.setWidget(0, 8, chkSoloAnulados);        
        lblSeparador=new Label("-");
        lblTipoDoc=new Label("");
        lblSerie=new Label("");
        lblPreImpreso=new Label("");
        lblDocumento=new Label("Detalle del documento:");
        txtBuscar = new MSearchBox();
        txtBuscar.setPlaceHolder("Buscar");
        pnlToolDoc=new HorizontalPanel();
        pnlToolDoc.add(lblDocumento);
        pnlToolDoc.add((new UISeparador()).isSpace());
        pnlToolDoc.add(lblTipoDoc);
        pnlToolDoc.add((new UISeparador()).isSpace());
        pnlToolDoc.add(lblSerie);
        pnlToolDoc.add(lblSeparador);
        pnlToolDoc.add(lblPreImpreso);
        pnlData=new FlexTable();       
        grid = new GridCabeceraVenta();
        grid.setMinimumTableWidth(1024, Style.Unit.PX);
        grid2 = new GridDetailVenta();
        grid2.setMinimumTableWidth(1024, Style.Unit.PX);
        pnlData.setWidget(0, 0, grid);
        pnlData.setWidget(1, 0, pnlToolDoc);
        pnlData.setWidget(2, 0, grid2);
        this.getPnlTabla().add(pnlData);
        pnlSearch.setWidget(1, 0, lstFiltro);
        pnlSearch.setWidget(1, 1, txtBuscar);        
        pnlSearch.getFlexCellFormatter().setColSpan(1, 1, 8);        
        pnlSearch.setCellSpacing(5);
        this.getPnlBusqueda().add(pnlSearch);
        this.btnOper1.setVisible(false);
        this.btnOper2.setVisible(false);
        this.btnOper3.setVisible(false);     
        this.addComponent(btnCambiarEstado);
        btnCambiarEstado.setVisible(false);
        this.addComponent(btnExportar);
        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }
    
    private void reCalcularWindows() {
        int alto = Window.getClientHeight()-165;
        this.getPnlTabla().setHeight(alto + "px");
        this.getPnlBotones().setHeight(alto + "px");
        alto=alto-135;
        alto=alto/2;
        grid.setHeight(alto+"px");        
        grid2.setHeight(alto+"px");
        pnlToolDoc.setHeight("32px");
        pnlData.setWidth("100%");       
    }
    
    private void initStyle(){                   
        lblDocumento.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
        lblDocumento.getElement().getStyle().setProperty("display", "table-cell");
        lblDocumento.setHeight("32px");
        lblTipoDoc.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
        lblTipoDoc.getElement().getStyle().setProperty("display", "table-cell");
        lblTipoDoc.setHeight("32px");
        lblSerie.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
        lblSerie.getElement().getStyle().setProperty("display", "table-cell");
        lblSerie.setHeight("32px");
        lblPreImpreso.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
        lblPreImpreso.getElement().getStyle().setProperty("display", "table-cell");
        lblPreImpreso.setHeight("32px");        
        lblSeparador.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
        lblSeparador.getElement().getStyle().setProperty("display", "table-cell");
        lblSeparador.setHeight("32px");
        grid.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        grid.getElement().getStyle().setBorderWidth(0.2, Style.Unit.PX);
        grid2.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        grid2.getElement().getStyle().setBorderWidth(0.2, Style.Unit.PX);
        txtBuscar.getElement().getFirstChild().getFirstChild().removeFromParent();  
        lstFiltro.setWidth("100%");
    }
    
    private void initListener(){
        txtBuscar.textBox.addKeyUpHandler(this);
        grid.addHandler(this, KeyUpEvent.getType());
        btnExportar.addClickHandler(this);
        btnCambiarEstado.addClickHandler(this);
    }

    @Override
    public void loadSeriesUserVenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if(txtBuscar.textBox.equals(event.getSource())){
            grid.getDataProvider().setFilter(txtBuscar.getText());
            grid.getDataProvider().refresh();
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER){
                grid.setFocus(true);
            }            
        }else if(grid.equals(event.getSource())){
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER || event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SPACE) {                
                itemSelected();
                showUIOper4();
            }            
        }
    }
    
    private void itemSelected() {
        if (grid.getDataProvider().hasFilter()) {
            headVenta = grid.getDataProvider().resulted.get(grid.getKeyboardSelectedRow());
            grid.getSelectionModel().setSelected(headVenta, true);            
        } else {
            headVenta = grid.getData().get(grid.getKeyboardSelectedRow());
            grid.getSelectionModel().setSelected(headVenta, true);            
        }
    }
    
    @Override
    public void onClick(ClickEvent event) {
        // TODO Auto-generated method stub
        if (event.getSource().equals(btnOper1)) {
            showUIOper1();
        } else if (event.getSource().equals(btnOper2)) {
            showUIOper2();
        } else if (event.getSource().equals(btnOper3)) {
            showUIOper3();
        } else if (event.getSource().equals(btnOper4)) {
            showUIOper4();
        } else if (event.getSource().equals(btnOper0)) {
            loadSeriesUserVenta();
        } else if(event.getSource().equals(btnExportar)){
                exportarData();
        } else if(event.getSource().equals(btnCambiarEstado)){
                showUICambiarEstado();
        }
    }
    
    @Override
    public void exportarData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showUICambiarEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
