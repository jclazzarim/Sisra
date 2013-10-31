/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela.fecharConta;

import br.unioeste.sisra.controle.validacao.Validacao;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.win.componente.Handler;
import br.unioeste.sisra.win.componente.SimpleTableModel;
import br.unioeste.sisra.win.tela.funcionario.FuncionarioActivity;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JCLazzarim
 */
public class ContaListagem extends javax.swing.JPanel {

    SimpleTableModel tblContaModel;
    private FecharContaActivity activity;
    private Handler handler;

    /**
     * Creates new form FuncionarioListagem
     */
    public ContaListagem(FecharContaActivity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;

        initTabelaContas();
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

        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContas = new javax.swing.JTable();
        txtBusca = new javax.swing.JTextField();

        btnEditar.setText("Fechar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnBuscar.setText("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblContas.setModel(tblContaModel);
        jScrollPane1.setViewportView(tblContas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(txtBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            String pk = (String) tblContaModel.getValueAt(tblContas.getSelectedRow(), FuncionarioActivity.Campo.ID);
            activity.editarConta(pk);
        } catch (Exception ex) {
            handler.handle(ex);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            String pk = (String) tblContaModel.getValueAt(tblContas.getSelectedRow(), FuncionarioActivity.Campo.ID);
            String nome = (String) tblContaModel.getValueAt(tblContas.getSelectedRow(), FuncionarioActivity.Campo.NOME);
            activity.excluirFuncionario(pk, nome);
        } catch (Exception ex) {
            handler.handle(ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            activity.buscarConta(txtBusca.getText(), FuncionarioActivity.Campo.ID);
        } catch (Exception e) {
            handler.handle(e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblContas;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables

    private void initTabelaContas() {
        String[] colunas = new String[5];
        ArrayList dadosTabela = new ArrayList();

        colunas[FecharContaActivity.Campo.ID] = "Id";
        colunas[FecharContaActivity.Campo.DESCRICAO] = "Descrição";
        colunas[FecharContaActivity.Campo.HR_ABERTURA] = "Hora abertura";
        colunas[FecharContaActivity.Campo.MESA] = "Mesa";
        colunas[FecharContaActivity.Campo.TOTAL] = "Total";

        tblContaModel = new SimpleTableModel(dadosTabela, colunas);
    }

    public void exibeBusca(ContaTO[] contas) {
        tblContaModel.removeAllElements();

        for (ContaTO c : contas) {
            tblContaModel.addRow(toArrayString(c));
        }
        tblContas.updateUI();
    }

    private void configurarHandler() {
        handler.add(Validacao.CHAVE_CAMPO_BUSCA, txtBusca);
    }

    public void atualizaConta(ContaTO c) {
        final int idLinha = tblContaModel.getIdLinha(FuncionarioActivity.Campo.ID, String.valueOf(c.getId()));
        if (idLinha >= 0) {
            tblContaModel.editarRow(idLinha, toArrayString(c));
        }
        tblContas.updateUI();
    }

    public void remover(String pk) {
        tblContaModel.removeRow(pk, FuncionarioActivity.Campo.ID);
        tblContas.updateUI();
    }

    private String[] toArrayString(ContaTO c) {
        String[] linha = new String[6];
        try {
            linha[FecharContaActivity.Campo.ID] = c.getId() == null ? "" : String.valueOf(c.getId());
            linha[FecharContaActivity.Campo.DESCRICAO] = c.getDescricao();
            linha[FecharContaActivity.Campo.HR_ABERTURA] = c.getHoraAberturaString();
            linha[FecharContaActivity.Campo.MESA] = c.getMesaTO().getId() == null ? "" : String.valueOf(c.getMesaTO().getId());
            linha[FecharContaActivity.Campo.TOTAL] = c.getTotal();
        } catch (Exception ex) {
            Logger.getLogger(ContaListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linha;
    }
}
