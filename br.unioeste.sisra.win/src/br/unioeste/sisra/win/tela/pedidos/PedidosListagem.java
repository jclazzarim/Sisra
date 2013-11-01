/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela.pedidos;

import br.unioeste.sisra.controle.validacao.Validacao;
import br.unioeste.sisra.modelo.to.PedidoTO;
import br.unioeste.sisra.win.componente.Handler;
import br.unioeste.sisra.win.componente.SimpleTableModel;
import br.unioeste.sisra.win.tela.fecharConta.ContaListagem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JCLazzarim
 */
public class PedidosListagem extends javax.swing.JPanel {

    SimpleTableModel tblPedidosModel;
    private PedidosActivity activity;
    private Handler handler;

    PedidosListagem(PedidosActivity aThis, Handler handler) {
        this.activity = aThis;
        this.handler = handler;

        initTabelaPedidos();
        initComponents();
        configurarHandler();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();

        tblPedidos.setModel(tblPedidosModel);
        jScrollPane1.setViewportView(tblPedidos);

        btnBuscar.setText("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 216, Short.MAX_VALUE)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            activity.buscarPedido();
        } catch (Exception e) {
            handler.handle(e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
            String pk = (String) tblPedidosModel.getValueAt(tblPedidos.getSelectedRow(), PedidosActivity.Campo.ID);
            activity.finalizarPedido(pk);
        } catch (Exception ex) {
            handler.handle(ex);
        }
        btnBuscarActionPerformed(null);
        
    }//GEN-LAST:event_btnFinalizarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables

   private void initTabelaPedidos() {
        String[] colunas = new String[3];
        ArrayList dadosTabela = new ArrayList();

        colunas[PedidosActivity.Campo.ID] = "Id";
        colunas[PedidosActivity.Campo.ATENDIDO] = "Atendido";
        colunas[PedidosActivity.Campo.HR_PEDIDO] = "Hora Pedido";

        tblPedidosModel = new SimpleTableModel(dadosTabela, colunas);
    }

    public void exibeBusca(PedidoTO[] pedidos) {
        tblPedidosModel.removeAllElements();

        for (PedidoTO p : pedidos) {
            tblPedidosModel.addRow(toArrayString(p));
        }
        tblPedidos.updateUI();
    }

    private void configurarHandler() {
    }

    public void remover(String pk) {
        tblPedidosModel.removeRow(pk, PedidosActivity.Campo.ID);
        tblPedidos.updateUI();
    }

    private String[] toArrayString(PedidoTO p) {
        String[] linha = new String[3];
        try {
            linha[PedidosActivity.Campo.ID] = p.getIdPedido()== null ? "" : String.valueOf(p.getIdPedido());
            linha[PedidosActivity.Campo.ATENDIDO] = p.isAtendido()? "Sim" : "Nao";
            linha[PedidosActivity.Campo.HR_PEDIDO] = p.getHoraPedido().toString();
        } catch (Exception ex) {
            Logger.getLogger(ContaListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linha;
    }

}