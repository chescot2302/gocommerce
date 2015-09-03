/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.webgocommerce.client.resource.cssresource.BorderLayoutCss;
import com.webgocommerce.client.resource.cssresource.FBorderLayoutCss;
import com.webgocommerce.client.resource.cssresource.GridDataCss;
import com.webgocommerce.client.resource.cssresource.HeaderMenuCss;
import com.webgocommerce.client.resource.cssresource.JBorderLayoutCss;
import com.webgocommerce.client.resource.cssresource.ModelCss;
import com.webgocommerce.client.resource.cssresource.NotificationCss;
import com.webgocommerce.client.resource.cssresource.TabCloseCss;
import com.webgocommerce.client.resource.cssresource.UIFormMantenimientoCss;
import com.webgocommerce.client.resource.cssresource.UIMenuBarCss;
import com.webgocommerce.client.resource.cssresource.UIScreenSesionCss;
import com.webgocommerce.client.resource.cssresource.UISesionCss;
import com.webgocommerce.client.resource.cssresource.UIVdEntradaCss;

/**
 *
 * @author SISTEMAS
 */
public interface MyResource extends ClientBundle{	
	public static final MyResource INSTANCE=GWT.create(MyResource.class);
        
        @Source("style/Notification.css")
	NotificationCss getStlNotification();
        
        @Source("style/Model.css")
	ModelCss getStlModel();
        
        @Source("style/HeaderMenu.css")
	HeaderMenuCss getStlHeaderMenu();
        
        @Source("image/cerrar16.png")
	ImageResource getImgCerrar16();
        
        @Source("image/personAdd32.png")
	ImageResource getImgPersonAdd32();
        
        @Source("image/abajo32.png")
	ImageResource getImgAbajo32();
        
        @Source("style/TabClose.css")
	TabCloseCss getStlTabClose();
        
        @Source("style/UIFormMantenimiento.css")
	UIFormMantenimientoCss getStlUIFormMantenimiento();
        
        @Source("style/BorderLayout.css")
	BorderLayoutCss getStlBorderLayout();
        
        @Source("style/JBorderLayout.css")
	JBorderLayoutCss getStlJBorderLayout();
        
        @Source("style/FBorderLayout.css")
	FBorderLayoutCss getStlFBorderLayout();
        
        @Source("style/UIScreenSesion.css")
	UIScreenSesionCss getStlUIScreenSesion();
        
        @Source("style/UISesion.css")
	UISesionCss getStlUISesion();
        
        @Source("style/UIMenuBar.css")
	UIMenuBarCss getStlUIMenuBar();
        
        @Source("style/UIVdEntrada.css")
	UIVdEntradaCss getStlUIVdEntrada();
        
        @Source("style/GridData.css")
	GridDataCss getStlGridData();
        
        @Source("image/back32.png")
	ImageResource getImgBack32();
        
        @Source("image/refresh32.png")
	ImageResource getImgRefresh32();
        
        @Source("image/select32.png")
	ImageResource getImgSelect32();
        
        @Source("image/person32.png")
	ImageResource getImgPerson32();
        
        @Source("image/tipocambio32.png")
	ImageResource getImgTipoCambio32();
        
        @Source("image/ubicacion32.png")
	ImageResource getImgUbicacion32();
        
        @Source("image/green20.png")
	ImageResource getImgGreen20();
        
        @Source("image/red20.png")
	ImageResource getImgRed20();
        
        @Source("image/gray20.png")
	ImageResource getImgGray20();
        
        @Source("image/orange20.png")
	ImageResource getImgOrange20();
        
        @Source("image/yellow20.png")
	ImageResource getImgYellow20();
        
        @Source("image/search32.png")
	ImageResource getImgSearch32();
        
        @Source("image/logout32.png")
	ImageResource getImgLogout32();
        
        @Source("image/dimosac.jpg")
	ImageResource getImgWallPaper();
        
        @Source("image/alert32.png")
	ImageResource getImgAlert32();
        
        @Source("image/warning32.png")
	ImageResource getImgWarning32();
        
        @Source("image/information32.png")
	ImageResource getImgInformation32();
        
        @Source("image/updategui32.png")
	ImageResource getImgUpdateGui32();
        
}
