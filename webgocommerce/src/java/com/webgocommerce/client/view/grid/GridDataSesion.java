/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.grid;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.Header;
import com.webgocommerce.client.beanproxy.DataSesionProxy;
import com.webgocommerce.client.resource.MyResource;
import com.webgocommerce.client.uiutil.CheckCellHead;
import com.webgocommerce.client.uiutil.FilteredListDataProvider;
import com.webgocommerce.client.uiutil.IFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

/**
 *
 * @author SISTEMAS
 */
public class GridDataSesion extends DataGrid<DataSesionProxy> {
    private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy hh:mm:ss");    
    //private DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyyMMddHHmmss");        
    //private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");    
    private List<DataSesionProxy> data = new ArrayList<DataSesionProxy>();
    //private final SingleSelectionModel<DataSesionProxy> selectionModel = new SingleSelectionModel<DataSesionProxy>();
    private final MultiSelectionModel<DataSesionProxy> selectionModel = new MultiSelectionModel<DataSesionProxy>();
    private FilteredListDataProvider<DataSesionProxy> dataProvider = new FilteredListDataProvider<DataSesionProxy>(new IFilter<DataSesionProxy>() {

        @Override
        public boolean isValid(DataSesionProxy value, String filter) {
            if(filter == null || value == null){
                return true;
            }else{
                String values= value.getIdDataSesion().toString().toLowerCase();
                return values.contains(filter.toLowerCase());
            }
        }
    });

    private SimplePager pager;

    public GridDataSesion() {
        initComponents();
        initStyle();
    }

    private void initComponents() {
        this.setWidth("100%");
        this.setHeight("90%");
        initColumns();
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        dataProvider.setList(data);
        dataProvider.addDataDisplay(this);
        this.setVisible(true);
        //this.setSelectionModel(selectionModel);
        this.setSelectionModel(selectionModel, DefaultSelectionEventManager.<DataSesionProxy>createCheckboxManager());
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(this);
        pager.setVisible(true);        
    }
    
    private void selection(Boolean select) {
        if (dataProvider.getFilter() != null && !dataProvider.getFilter().isEmpty()) {
            selectionModel.clear();
            for (int i = 0; i < dataProvider.resulted.size(); i++) {
                selectionModel.setSelected(dataProvider.resulted.get(i), select);
            }
        } else {
            selectionModel.clear();
            for (int i = 0; i < data.size(); i++) {
                selectionModel.setSelected(data.get(i), select);
            }
        }
    }
    
    private void initStyle(){           
        //MyResource.INSTANCE.getStlGridData().ensureInjected();	
        //this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
        this.getColumn(0).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        this.getColumn(1).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initColumns() {
        this.addColumn(checkColumn, headCheckAll);
        this.addColumn(id, "ID");
        this.addColumn(img, "");
        this.addColumn(estado, "ESTADO");
        this.addColumn(nivel, "NIVEL");
        this.addColumn(tokenGo, "TOKEN GO");                
        this.addColumn(idBdUsuarioGo, "IDBDUSUARIOGO");   
        this.addColumn(sesionIdSql, "SESSION BD"); 
        this.addColumn(hostName, "HOSTNAME"); 
        this.addColumn(bdId, "ID BD"); 
        this.addColumn(dbName, "DB NAME"); 
        this.addColumn(myToken, "TOKEN USER"); 
        this.addColumn(idBdUsuario, "IDBDUSUARIO");        
        this.addColumn(loginName, "LOGIN NAME");        
        this.addColumn(loginTime, "LOGIN TIME");        
        this.addColumn(usuario, "USUARIO");        
        this.addColumn(idSession, "SESSION SERVER");        
        this.addColumn(cookie, "COOKIE");                             
        this.addColumn(creationSesion, "CREATION SESSION");        
        this.addColumn(remoteAddr, "REMOTE ADDR");        
        this.addColumn(remoteHost, "REMOTE HOST");        
        this.addColumn(remotePort, "REMOTE PORT");        
        this.addColumn(localName, "LOCAL NAME");        
        this.addColumn(localAddr, "LOCAL ADDR");        
        this.addColumn(localPort, "LOCAL PORT");        
        this.addColumn(accept, "ACCEPT");        
        this.addColumn(connection, "CONNECTION");        
        this.addColumn(referer, "REFERER");        
        this.addColumn(pragma, "PRAGMA");        
        this.addColumn(acceptEncoding, "ACCEPT ENCODING");        
        this.addColumn(cacheControl, "CACHE CONTROL");        
        this.addColumn(xGwtModuleBase, "XGWTMODULEBASE");        
        this.addColumn(userAgent, "USER AGENT");        
        this.addColumn(contentType, "CONTENT TYPE");        
        this.addColumn(acceptLanguage, "ACCEPT LANGUAGE");        
        this.addColumn(contentLength, "CONTENT LENGTH");                
        this.addColumn(programName, "PROGRAM NAME");  
        this.setColumnWidth(checkColumn, 5, Unit.EM);
        this.setColumnWidth(id, 5, Unit.EM);    
        this.setColumnWidth(img, 5, Unit.EM);
        this.setColumnWidth(estado, 15, Unit.EM);        
        this.setColumnWidth(nivel, 9, Unit.EM);        
        this.setColumnWidth(sesionIdSql, 9, Unit.EM);        
        this.setColumnWidth(hostName, 9, Unit.EM);        
        this.setColumnWidth(bdId, 5, Unit.EM);        
        this.setColumnWidth(dbName, 9, Unit.EM);        
        this.setColumnWidth(myToken, 10, Unit.EM);        
        this.setColumnWidth(idBdUsuario, 10, Unit.EM);        
        this.setColumnWidth(loginName, 9, Unit.EM);        
        this.setColumnWidth(loginTime, 13, Unit.EM);        
        this.setColumnWidth(usuario, 9, Unit.EM);        
        this.setColumnWidth(idSession, 20, Unit.EM);        
        this.setColumnWidth(creationSesion, 13, Unit.EM);        
        this.setColumnWidth(remoteAddr, 10, Unit.EM);        
        this.setColumnWidth(remoteHost, 10, Unit.EM);        
        this.setColumnWidth(remotePort, 10, Unit.EM);        
        this.setColumnWidth(localName, 10, Unit.EM);        
        this.setColumnWidth(localAddr, 10, Unit.EM);        
        this.setColumnWidth(localPort, 10, Unit.EM);        
        this.setColumnWidth(accept, 35, Unit.EM);        
        this.setColumnWidth(connection, 10, Unit.EM);        
        this.setColumnWidth(referer, 35, Unit.EM);        
        this.setColumnWidth(pragma, 10, Unit.EM);        
        this.setColumnWidth(acceptEncoding, 12, Unit.EM);        
        this.setColumnWidth(cacheControl, 10, Unit.EM);        
        this.setColumnWidth(xGwtModuleBase, 35, Unit.EM);        
        this.setColumnWidth(userAgent, 40, Unit.EM);        
        this.setColumnWidth(contentType, 35, Unit.EM);        
        this.setColumnWidth(acceptLanguage, 35, Unit.EM);        
        this.setColumnWidth(contentLength, 12, Unit.EM);        
        this.setColumnWidth(cookie, 25, Unit.EM);        
        this.setColumnWidth(tokenGo, 10, Unit.EM);        
        this.setColumnWidth(idBdUsuarioGo, 10, Unit.EM);        
        this.setColumnWidth(programName, 30, Unit.EM);        
        
    }
    
    public CheckCellHead checkAll = new CheckCellHead();
    Header<Boolean> headCheckAll = new Header<Boolean>(checkAll) {

        @Override
        public Boolean getValue() {
            return checkAll.isIsSelected();
        }

        @Override
        public void onBrowserEvent(Cell.Context context,
                Element elem,
                NativeEvent event) {
            if (checkAll.isIsSelected()) {
                checkAll.setIsSelected(false);
                selection(false);
            } else {
                checkAll.setIsSelected(true);
                selection(true);
            }

        }
    };
    
    public void actualizarGrid() {
        this.setVisibleRangeAndClearData(this.getVisibleRange(), true);
        selectionModel.clear();
    }
    
    Column<DataSesionProxy, Boolean> checkColumn
            = new Column<DataSesionProxy, Boolean>(new CheckboxCell(true, false)) {
                @Override
                public Boolean getValue(DataSesionProxy object) {
                    // Get the value from the selection model.
                    return selectionModel.isSelected(object);
                }
            };
    
    private Column<DataSesionProxy, ImageResource> img
            = new Column<DataSesionProxy, ImageResource>(new ImageResourceCell()) {

                @Override
                public ImageResource getValue(DataSesionProxy object) {
                    if (object.getEstado().trim().equalsIgnoreCase("CONEXION ESTABLE")) {
                        return MyResource.INSTANCE.getImgGreen20();
                    } else if (object.getEstado().trim().equalsIgnoreCase("CONEXION ROTA")) {
                        return MyResource.INSTANCE.getImgOrange20();
                    } else if (object.getEstado().trim().equalsIgnoreCase("CONEXION FANTASMA")) {
                        return MyResource.INSTANCE.getImgRed20();
                    } else {
                        return MyResource.INSTANCE.getImgGray20();
                    }
                }

            };

    private Column<DataSesionProxy, Number> id
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getIdDataSesion();
                }
            };
    
    private Column<DataSesionProxy, String> estado
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getEstado();
                }

            }; 

    private Column<DataSesionProxy, String> nivel
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getNivel();
                }

            };        
    
    private Column<DataSesionProxy, String> tokenGo
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getTokenGo();
                }

            };
    
    private Column<DataSesionProxy, String> myToken
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getMyToken();
                }

            };
    
    private Column<DataSesionProxy, String> idSession
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getIdSession();
                }

            };    
    
    private Column<DataSesionProxy, String> usuario
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getUsuario();
                }

            }; 
    
    private Column<DataSesionProxy, String> usuarioBd
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getUsuariobd();
                }

            };
    
    private Column<DataSesionProxy, String> schemaBd
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getSchemabd();
                }

            };
    
    private Column<DataSesionProxy, String> remoteAddr
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getRemoteAddr();
                }

            };
    
    private Column<DataSesionProxy, String> remoteHost
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getRemoteHost();
                }

            };
    
    private Column<DataSesionProxy, Number> remotePort
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getRemotePort();
                }

            };
    
    private Column<DataSesionProxy, String> localName
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getLocalName();
                }

            };
    
    private Column<DataSesionProxy, String> localAddr
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getLocalAddr();
                }

            };
    
    private Column<DataSesionProxy, Number> localPort
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getLocalPort();
                }

            };
    
    private Column<DataSesionProxy, String> accept
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getAccept();
                }

            };
    
    private Column<DataSesionProxy, String> connection
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getConnection();
                }

            };
    
    private Column<DataSesionProxy, String> referer
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getReferer();
                }

            };
    
    private Column<DataSesionProxy, String> pragma
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getPragma();
                }

            };
    
    private Column<DataSesionProxy, String> acceptEncoding
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getAcceptEncoding();
                }

            };
    
    private Column<DataSesionProxy, String> cacheControl
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getCacheControl();
                }

            };
    
    private Column<DataSesionProxy, String> xGwtModuleBase
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getxGwtModuleBase();
                }

            };
    
    private Column<DataSesionProxy, String> userAgent
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getUserAgent();
                }

            };
    
    private Column<DataSesionProxy, String> contentType
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getContentType();
                }

            };
    
    private Column<DataSesionProxy, String> acceptLanguage
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getAcceptLanguage();
                }

            };
    
    private Column<DataSesionProxy, String> contentLength
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getContentLength();
                }

            };
    
    private Column<DataSesionProxy, String> cookie
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getCookie();
                }

            };
    
    private Column<DataSesionProxy, String> host
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getHost();
                }

            };
    
    private Column<DataSesionProxy, Date> creationSesion
            = new Column<DataSesionProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(DataSesionProxy object) {
                    return object.getCreationTimeSesion();
                }
            };
    
    private Column<DataSesionProxy, Date> lastSesion
            = new Column<DataSesionProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(DataSesionProxy object) {
                    return object.getLastTimeSesion();
                }
            }; 
    
    private Column<DataSesionProxy, Date> fechaIni
            = new Column<DataSesionProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(DataSesionProxy object) {
                    return object.getFechaIni();
                }
            };
    
    private Column<DataSesionProxy, Date> fechaFin
            = new Column<DataSesionProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(DataSesionProxy object) {
                    return object.getFechaFin();
                }
            };
    
    private Column<DataSesionProxy, Number> idBdUsuario
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getIdUsuarioBd();
                }

            };
    
    private Column<DataSesionProxy, Number> idBdUsuarioGo
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getIdUsuarioBdGO();
                }

            };
    
    private Column<DataSesionProxy, Number> sesionIdSql
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getSessionIdSQL();
                }

            };
    
   private Column<DataSesionProxy, String> loginName
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getLoginName();
                }

            };
   
   private Column<DataSesionProxy, String> hostName
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getHostName();
                }

            };
   
   private Column<DataSesionProxy, String> dbName
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getDbName();
                }

            };
   
   private Column<DataSesionProxy, String> programName
            = new Column<DataSesionProxy, String>(new TextCell()) {

                @Override
                public String getValue(DataSesionProxy object) {
                    return object.getProgramName();
                }

            };
   
   private Column<DataSesionProxy, Date> loginTime
            = new Column<DataSesionProxy, Date>(new DateCell(dateFormat)) {

                @Override
                public Date getValue(DataSesionProxy object) {
                    return object.getLoginTime();
                }
            }; 
   
   private Column<DataSesionProxy, Number> hostProcessId
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getHostProcessId();
                }

            };
   
   private Column<DataSesionProxy, Number> bdId
            = new Column<DataSesionProxy, Number>(new NumberCell()) {

                @Override
                public Number getValue(DataSesionProxy object) {
                    return object.getBdId();
                }

            };

    public void setData(List<DataSesionProxy> data) {
        this.data = data;
        this.setRowCount(data.size(), true);
        this.setRowData(0, data);
        this.setPageSize(data.size());
        dataProvider.setList(data);
        dataProvider.refresh();
    }

    public List<DataSesionProxy> getData() {
        return data;
    }
    
    

    public SimplePager getPager() {
        return pager;
    }

    @Override
    public MultiSelectionModel<DataSesionProxy> getSelectionModel() {
        return selectionModel;
    }

    public FilteredListDataProvider<DataSesionProxy> getDataProvider() {
        return dataProvider;
    }
}
