/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.apresentacao;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.ComprasRealizadasClientes;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Késia Correia
 */
public class GerenciamentoComprasRealizadas extends javax.swing.JFrame {

    /**
     * Creates new form GerenciamentoComprasRealizadas
     */
    
    List<ComprasRealizadasClientes> contasExibidasNaTela = null;

    public GerenciamentoComprasRealizadas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBuscaProdutos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblComprasRealizadas = new javax.swing.JTable();
        lblFotoProduto = new javax.swing.JLabel();
        BtnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelBuscaProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relação de Compras Realizadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblComprasRealizadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblComprasRealizadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComprasRealizadasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblComprasRealizadas);

        javax.swing.GroupLayout jPanelBuscaProdutosLayout = new javax.swing.GroupLayout(jPanelBuscaProdutos);
        jPanelBuscaProdutos.setLayout(jPanelBuscaProdutosLayout);
        jPanelBuscaProdutosLayout.setHorizontalGroup(
            jPanelBuscaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscaProdutosLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFotoProduto)
                .addContainerGap())
        );
        jPanelBuscaProdutosLayout.setVerticalGroup(
            jPanelBuscaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscaProdutosLayout.createSequentialGroup()
                .addGroup(jPanelBuscaProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBuscaProdutosLayout.createSequentialGroup()
                        .addComponent(lblFotoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 148, Short.MAX_VALUE))
                    .addGroup(jPanelBuscaProdutosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        BtnSair.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/Cancelar.jpg"))); // NOI18N
        BtnSair.setText("Sair");
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanelBuscaProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public void carregarDadosTabela(List<ComprasRealizadasClientes> comprasRealizadasClientes) {
        this.contasExibidasNaTela = comprasRealizadasClientes;
        Model model = new Model(comprasRealizadasClientes);
        this.tblComprasRealizadas.setModel(model);
    }
    
    
    private void tblComprasRealizadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprasRealizadasMouseClicked

    }//GEN-LAST:event_tblComprasRealizadasMouseClicked

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoComprasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoComprasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoComprasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoComprasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciamentoComprasRealizadas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSair;
    private javax.swing.JPanel jPanelBuscaProdutos;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblFotoProduto;
    private javax.swing.JTable tblComprasRealizadas;
    // End of variables declaration//GEN-END:variables


    public class Model extends AbstractTableModel {

        List<ComprasRealizadasClientes> contasT = null;

        public Model(List<ComprasRealizadasClientes> comprasRealizadasClientes) {
            this.contasT = comprasRealizadasClientes;
        }

        @Override
        public int getRowCount() {
            return contasT.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            ComprasRealizadasClientes c = contasT.get(linha);
            if (coluna == 0) {
                return c.getNumeroCompra();
            } else if (coluna == 1) {
                return c.getCompra().getCliente().getNome();
            } else if (coluna == 2) {
                return c.getDataCompra();
            } else if (coluna == 3) {
                if (c.isStatus()) {
                    return "EM DÍVIDA";
                } else {
                    return "PAGA";
                }
            } else {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(c.getValorTotal());
            }

        }

        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "Código";
            } else if (coluna == 1) {
                return "Cliente";
            } else if (coluna == 2) {
                return "Data";
            } else if (coluna == 3) {
                return "Staus";
            } else {
                return "Valor";
            }
        }

    }



}
