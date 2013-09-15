package br.unioeste.sisra.win.componente;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;

public final class SimpleTableModel extends AbstractTableModel {

    private String[] colunas;
    private ArrayList linhas;

    public SimpleTableModel(ArrayList dados, String[] colunas) {
        setLinhas(dados);
        setColunas(colunas);
    }

    public String[] getColunas() {
        return colunas;
    }

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setColunas(String[] strings) {
        colunas = strings;
    }

    public void setLinhas(ArrayList list) {
        linhas = list;
    }

    public void editarRow(int rowIndex, String[] novoValor) {
        getLinhas().set(rowIndex, novoValor);
    }

    public void editarRow(int rowIndex, int collumnIndex, String novoValor) {
        ((String[]) getLinhas().get(rowIndex))[collumnIndex] = novoValor;
    }

    /**
     * Retorna o numero de colunas no modelo
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return getColunas().length;
    }

    /**
     * Retorna o numero de linhas existentes no modelo
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return getLinhas().size();
    }

    /**
     * Obtem o valor na linha e coluna
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Obtem a linha, que é uma String []
        String[] linha = (String[]) getLinhas().get(rowIndex);
        // Retorna o objeto que esta na coluna
        return linha[columnIndex];
    }

    public void addRow(String[] dadosLinha) {
        getLinhas().add(dadosLinha);
        // Informa a jtable de que houve linhas incluidas no modelo
        // COmo adicionamos no final, pegamos o tamanho total do modelo
        // menos 1 para obter a linha incluida.
        int linha = getLinhas().size() - 1;
        fireTableRowsInserted(linha, linha);
        return;
    }

    // Implementação do getColumnName
    /**
     * Retorna o nome da coluna.
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int col) {
        return getColunas()[col];
    }

    public void removeRow(int row) {
        getLinhas().remove(0);
        // informa a jtable que houve dados deletados passando a
        // linha reovida
        fireTableRowsDeleted(row, row);
    }

    public void removeAllElements(){
        while(!getLinhas().isEmpty()){
            removeRow(0);
        }
    }

    /**
     * Remove a linha pelo valor da coluna informada
     * @param val
     * @param col
     * @return
     */
    public boolean removeRow(String val, int col) {
        // obtem o iterator
        Iterator i = getLinhas().iterator();
        int linha = -1;
        // Faz um looping em cima das linhas
        while (i.hasNext()) {
            // Obtem as colunas da linha atual
            String[] linhaCorrente = (String[]) i.next();
            linha++;
            // compara o conteudo String da linha atual na coluna desejada
            // com o valor informado
            if (linhaCorrente[col].equals(val)) {
                getLinhas().remove(linha);
                // informa a jtable que houve dados deletados passando a
                // linha removida
                fireTableRowsDeleted(linha, linha);
                return true;
            }
        }
        // Nao encontrou nada
        return false;
    }

    public int getIdLinha(int coluna, String valor) {
        for (int i=0; i<linhas.size(); i++) {
            String str = (String) getValueAt(i, coluna);
            if(str.equals(valor))
                return i;
        }
        return -1;
    }
}
