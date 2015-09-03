/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.webgocommerce.client.view.listmodel.ListModelDocVenta;
import com.webgocommerce.client.view.listmodel.ListModelSerieCorrelativo;

/**
 *
 * @author SISTEMAS
 */
public class UIInfoDoc extends Composite{
    private FlexTable pnlContenedor;
    public UILabel lblDocumento;
    public ListModelDocVenta lstTipoDoc;
    public ListModelSerieCorrelativo lstSerieCorre;
    public TextBox txtPreImpreso;
    
    public UIInfoDoc(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        lstTipoDoc=new ListModelDocVenta();        
        lblDocumento=new UILabel("Documento");
        lstSerieCorre=new ListModelSerieCorrelativo();
        lstSerieCorre.setEnabled(true);
        txtPreImpreso=new TextBox();
        txtPreImpreso.setEnabled(false);
        pnlContenedor=new FlexTable();
        pnlContenedor.setWidget(0, 0, lblDocumento);
        pnlContenedor.setWidget(0, 1, lstTipoDoc);
        pnlContenedor.setWidget(0, 2, lstSerieCorre);
        pnlContenedor.setWidget(0, 3, txtPreImpreso);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        //txtSerie.getElement().getStyle().setMargin(5, Style.Unit.PX);
        txtPreImpreso.getElement().getStyle().setMargin(5, Style.Unit.PX);
        //txtSerie.setWidth("35px");
        txtPreImpreso.setWidth("100px");
    }
}
