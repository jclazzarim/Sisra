/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela;

import br.unioeste.sisra.win.tela.fecharConta.FecharContaActivity;
import br.unioeste.sisra.win.tela.funcionario.FuncionarioActivity;
import br.unioeste.sisra.win.tela.item.ItemActivity;
import br.unioeste.sisra.win.tela.mesa.MesaActivity;
import java.awt.CardLayout;

/**
 *
 * @author J.C
 */
public class TelaPrincial extends javax.swing.JFrame {

    FuncionarioActivity funcionarioActivity;
    ItemActivity itemActivity;
    MesaActivity mesaActivity;
    FecharContaActivity contaActivity;

    class Tela {

        static final String FUNCIONARIO = "funcionario";
        static final String ITEM = "item";
        static final String MESA = "mesa";
        static final String CONTA = "conta";
    }
    CardLayout cl;

    /**
     * Creates new form TelaPrincial
     */
    public TelaPrincial() {
        initComponents();
        setTitle("Sisra");

        this.funcionarioActivity = new FuncionarioActivity(this);
        this.itemActivity = new ItemActivity(this);
        this.mesaActivity = new MesaActivity(this);
        this.contaActivity = new FecharContaActivity(this);
        this.cl = new CardLayout();
        this.jpPrincipal.setLayout(cl);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jtbMenus = new javax.swing.JToolBar();
        btFuncionario = new javax.swing.JButton();
        btItem = new javax.swing.JButton();
        btMesa = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        btConta = new javax.swing.JButton();
        jpPrincipal = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jtbMenus.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro"));
        jtbMenus.setFloatable(false);
        jtbMenus.setRollover(true);

        btFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/unioeste/sisra/win/recursos/imagem/funcionario_48x48.png"))); // NOI18N
        btFuncionario.setText("Funcionários");
        btFuncionario.setFocusable(false);
        btFuncionario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btFuncionario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFuncionarioActionPerformed(evt);
            }
        });
        jtbMenus.add(btFuncionario);

        btItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/unioeste/sisra/win/recursos/imagem/product_48x48.png"))); // NOI18N
        btItem.setText("Itens");
        btItem.setFocusable(false);
        btItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btItemActionPerformed(evt);
            }
        });
        jtbMenus.add(btItem);

        btMesa.setText("Mesas");
        btMesa.setFocusable(false);
        btMesa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMesa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMesaActionPerformed(evt);
            }
        });
        jtbMenus.add(btMesa);

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gerenciamento"));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/unioeste/sisra/win/recursos/imagem/pedidos_48x48.png"))); // NOI18N
        jButton2.setText("Pedido");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        btConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/unioeste/sisra/win/recursos/imagem/conta_48x48.png"))); // NOI18N
        btConta.setText("Contas");
        btConta.setFocusable(false);
        btConta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btConta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContaActionPerformed(evt);
            }
        });
        jToolBar1.add(btConta);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jtbMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbMenus, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton1.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFuncionarioActionPerformed
        jpPrincipal.removeAll();
        jpPrincipal.add(funcionarioActivity.getTela(), Tela.FUNCIONARIO);
        jpPrincipal.revalidate();
    }//GEN-LAST:event_btFuncionarioActionPerformed

    private void btItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btItemActionPerformed
        jpPrincipal.removeAll();
        jpPrincipal.add(itemActivity.getTela(), Tela.ITEM);
        jpPrincipal.revalidate();
    }//GEN-LAST:event_btItemActionPerformed

    private void btMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMesaActionPerformed
        jpPrincipal.removeAll();
        jpPrincipal.add(mesaActivity.getTela(), Tela.MESA);
        jpPrincipal.revalidate();
    }//GEN-LAST:event_btMesaActionPerformed

    private void btContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContaActionPerformed
        jpPrincipal.removeAll();
        jpPrincipal.add(contaActivity.getTela(), Tela.CONTA);
        jpPrincipal.revalidate();
    }//GEN-LAST:event_btContaActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConta;
    private javax.swing.JButton btFuncionario;
    private javax.swing.JButton btItem;
    private javax.swing.JButton btMesa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JToolBar jtbMenus;
    // End of variables declaration//GEN-END:variables

    public void mudarTelaPrincipal(String str) {
        this.cl.show(this.jpPrincipal, str);
        jpPrincipal.revalidate();
    }
}
