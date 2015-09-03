/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webgocommerce.client.view.tree;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.webgocommerce.server.beans.MenuBar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class TreeMenuModel implements TreeViewModel {

    private final DefaultSelectionEventManager<MenuBar> selectionManager
            = DefaultSelectionEventManager.createCheckboxManager();
    private Cell<MenuBar> menuBarCell;
    private final MultiSelectionModel<MenuBar> selectionModel = new MultiSelectionModel<MenuBar>(new ListDataProvider<MenuBar>());
    private MenuBar rootMenu;

    public TreeMenuModel(MenuBar rootMenu) {
        this.rootMenu = rootMenu;
        initComponents();
        initListener();
    }

    public TreeMenuModel() {
        initComponents();
        initListener();
    }

    private void initComponents() {
        List<HasCell<MenuBar, ?>> hasCells = new ArrayList<HasCell<MenuBar, ?>>();
        hasCells.add(new HasCell<MenuBar, Boolean>() {

            private CheckboxCell cell = new CheckboxCell(true, true);

            @Override
            public Cell<Boolean> getCell() {
                return cell;
            }

            @Override
            public FieldUpdater<MenuBar, Boolean> getFieldUpdater() {
                return new FieldUpdater<MenuBar, Boolean>() {
                    public void update(int index, MenuBar object, Boolean value) {
                        selectionModel.setSelected(object, value);
                        object.setOperacion("A");
                        object.setEstado(value==true?"A":"D");
                        if (object.getPadre() != null && !object.getPadre().getVariable().equalsIgnoreCase("root")) {
                            if (value) {
                                //selectionModel.setSelected(object.getPadre(), value);
                                //object.getPadre().setOperacion("A");
                                SelectedFather(object);
                            }
                        }
                        isSelectedChild(object, value);
                    }
                };
            }

            @Override
            public Boolean getValue(MenuBar object) {
                if (object.getEstado().equalsIgnoreCase("A") && object.getOperacion().equalsIgnoreCase("N")) {
                    selectionModel.setSelected(object, true);
                    return true;
                }
                return selectionModel.isSelected(object);
            }

            public void SelectedFather(MenuBar menuBar) {
                if (!selectionModel.isSelected(menuBar.getPadre())) {
                    selectionModel.setSelected(menuBar.getPadre(), true);
                    menuBar.getPadre().setOperacion("A");
                    menuBar.getPadre().setEstado("A");
                    if (menuBar.getPadre() != null) {
                        SelectedFather(menuBar.getPadre());
                    }
                }
            }

            public void isSelectedChild(MenuBar menuBar, Boolean val) {
                Iterator<MenuBar> iterador = menuBar.getHijos().iterator();
                while (iterador.hasNext()) {
                    MenuBar bean = iterador.next();
                    selectionModel.setSelected(bean, val);
                    bean.setOperacion("A");
                    bean.setEstado(val==true?"A":"D");
                    isSelectedChild(bean, val);
                }
            }

        });

        menuBarCell = new CompositeCell<MenuBar>(hasCells) {

            /*@Override
             public void onBrowserEvent(Cell.Context context, Element parent, MenuBar value,
             NativeEvent event, ValueUpdater<MenuBar> valueUpdater) {
             if ("keyup".equals(event.getType())
             && event.getKeyCode() == KeyCodes.KEY_ENTER) {
             selectionModel.setSelected(value, !selectionModel.isSelected(value));
             }
             }*/
            @Override
            public void render(Cell.Context context, MenuBar value, SafeHtmlBuilder sb) {
                sb.appendHtmlConstant("<table><tbody><tr>");
                super.render(context, value, sb);
                sb.appendHtmlConstant("</tr></tbody></table>");
            }

            @Override
            protected Element getContainerElement(Element parent) {
                return parent.getFirstChildElement().getFirstChildElement().getFirstChildElement();
            }

            @Override
            protected <X> void render(Cell.Context context, MenuBar value,
                    SafeHtmlBuilder sb, HasCell<MenuBar, X> hasCell) {
                Cell<X> cell = hasCell.getCell();
                sb.appendHtmlConstant("<td>");
                cell.render(context, hasCell.getValue(value), sb);
                sb.appendHtmlConstant(" " + value.getOrden());
                sb.appendHtmlConstant(" " + value.getDescripcion());
                sb.appendHtmlConstant("</td>");
            }
        };

        /*hasCells.add(new HasCell<MenuBar, MenuBar>() {

         private MenuBarCell cell = new MenuBarCell();

         public Cell<MenuBar> getCell() {
         return cell;
         }

         public FieldUpdater<MenuBar, MenuBar> getFieldUpdater() {
         return null;
         }

         public MenuBar getValue(MenuBar object) {
         return object;
         }
         });*/
    }

    private void initListener() {
        /*selectionModel.addSelectionChangeHandler(
         new SelectionChangeEvent.Handler() {
         @Override
         public void onSelectionChange(SelectionChangeEvent event) {

         }
         });*/
    }

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value) {
        ListDataProvider<MenuBar> dataProvider
                = new ListDataProvider<MenuBar>(((MenuBar) value).getHijos());
        /*Cell<MenuBar> cell
         = new AbstractCell<MenuBar>() {

         @Override
         public void render(Cell.Context context, MenuBar value, SafeHtmlBuilder sb) {
         if (value != null) {
         //sb.appendHtmlConstant("    <input type=\"checkbox\" tabindex=\"-1\"/>  ");
         sb.appendEscaped(value.getDescripcion());
         getNodeInfo(value);
         }
         }
         };*/

        /*CheckCellMenu<MenuBar> cell=new CheckCellMenu<MenuBar>();*/
        return new DefaultNodeInfo<MenuBar>(dataProvider, menuBarCell, selectionModel, selectionManager, null);
    }

    @Override
    public boolean isLeaf(Object value) {
        return ((MenuBar) value).getNumSubMenu() == 0;
    }

    /*public void setMenus(List<MenuBar> menus) {
    this.menus = menus;
    }*/
    /*static class MenuBarCell extends AbstractCell<MenuBar> {
    @Override
    public void render(Context context, MenuBar value, SafeHtmlBuilder sb) {
    // Value can be null, so do a null check..
    if (value == null) {
    return;
    }
    sb.appendHtmlConstant("<table>");
    // Add the contact image.
    sb.appendHtmlConstant("<tr><td rowspan='3'>");
    sb.appendHtmlConstant(" Imagen ");
    sb.appendHtmlConstant("</td>");
    // Add the name and address.
    sb.appendHtmlConstant("<td style='font-size:95%;'>");
    sb.appendEscaped(value.getDescripcion());
    sb.appendHtmlConstant("</td></tr><tr><td>");
    sb.appendEscaped(" " + value.getOrden());
    sb.appendHtmlConstant("</td></tr></table>");
    }
    }*/
    public MenuBar getRootMenu() {
        return rootMenu;
    }

    public void setRootMenu(MenuBar rootMenu) {
        this.rootMenu = rootMenu;
    }
    
    

}
