package br.unioeste.sisra.win.componente;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Tharle J. Camargo <tharles.josefidecamargo@gmail.com />
 */
public class SimpleComboBoxModel implements ComboBoxModel{
    ArrayList<String> itens;
    int selectedItem;
    ArrayList<ListDataListener> listeners;

    public SimpleComboBoxModel(String[] itens) {
        this.itens = new ArrayList<String>();
        this.itens.addAll(Arrays.asList(itens));
        this.selectedItem = 0;
        listeners  = new ArrayList<ListDataListener>();
    }

    public SimpleComboBoxModel(ArrayList<String> itens) {
        this.itens = new ArrayList<String>();
        this.itens = itens;
        this.selectedItem = 0;
        listeners  = new ArrayList<ListDataListener>();
    }

    public void setSelectedItem(Object anItem) {
        if(anItem.getClass().equals(String.class)){
            String item = (String) anItem;
            for (int i = 0; i < itens.size(); i++) {
                String aux = itens.get(i);
                if(item.equals(aux)){
                    this.selectedItem = i;
                    break;
                }
            }
        }
    }

    public void setSelectedItem(int index){
        selectedItem = index;
    }

    public Object getSelectedItem() {
        return itens.get(selectedItem);
    }

    public int getSize() {
        return itens.size();
    }

    public Object getElementAt(int index) {
        return itens.get(index);
    }

    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    public int getSelectedItemId() {
        return selectedItem;
    }

}
