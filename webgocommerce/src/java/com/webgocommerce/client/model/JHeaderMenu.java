/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.model;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;

public class JHeaderMenu extends FlexTable{
	
	public JHeaderMenu(){
		initComponents();
	}
	
	private void initComponents(){
		FlexCellFormatter headCellFormatter = this.getFlexCellFormatter();		
		this.setWidth("100%");
		//this.setHeight("12mm");
		headCellFormatter.setHorizontalAlignment(
		        0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		headCellFormatter.setHorizontalAlignment(
		        0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		headCellFormatter.setHorizontalAlignment(
		        0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		headCellFormatter.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);		
		headCellFormatter.setWidth(0, 0, "32px");
		headCellFormatter.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_MIDDLE);
		headCellFormatter.setWidth(0, 2, "32px");
	}
	
	public void setCenterWidget(Widget w) {
		this.setWidget(0, 1, w);		
	}
	
	public void setLeftWidget(Widget w) {
		this.setWidget(0, 0, w);
	}
	
	public void setRightWidget(Widget w) {
		this.setWidget(0, 2, w);
	}
	
	

}
