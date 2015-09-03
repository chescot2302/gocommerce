/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.client.view.uiitemserie;

import com.webgocommerce.client.beanproxy.AlmacenProxy;
import com.webgocommerce.client.beanproxy.ItemProxy;

/**
 *
 * @author SISTEMAS
 */
public interface InterUIItemSerie {
    void loadData(ItemProxy beanItem,AlmacenProxy beanAlmacen);
    void addSeries();
    void removeSeries();
    boolean isValidData();
}
