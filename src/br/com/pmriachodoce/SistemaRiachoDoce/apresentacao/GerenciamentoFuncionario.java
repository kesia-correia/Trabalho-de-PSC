/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.apresentacao;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Usuario;
import br.com.pmriachodoce.SistemaRiachoDoce.negocio.UsuarioBO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Késia Correia
 */
public class GerenciamentoFuncionario extends javax.swing.JFrame {
    
    List<Usuario> usuarios = new ArrayList<>();
    MenuPrincipal menuPrincipal = null;
    /**
     * Creates new form GerenciamentoFuncionario
     */
    public GerenciamentoFuncionario() {
        initComponents();
    }
    
    public GerenciamentoFuncionario(MenuPrincipal menuPrincipal) {
        initComponents();  
        buscarTodosFuncionario();
        this.menuPrincipal = menuPrincipal;
    }
    
    public void buscarTodosFuncionario(){
        
        try{         
            UsuarioBO usuarioBO = new UsuarioBO();
            usuarios = usuarioBO.buscarTodos();
            carregarDadosTabela();
        }catch(Exception e){
            exibirMensagemErro( "Erro desconhecido. Contate com administrador", "Consultar Usuario");
        }
    }
    
    public void carregarDadosTabela(){
        Model modelo = new GerenciamentoFuncionario.Model();
        this.tbUsuario.setModel(modelo);
    }
    
    private void exibirMensagemErro(String mensagem, String titulo) {
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
        }

        private void exibirMensagemSucesso(String mensagem, String titulo) {
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBuscaFuncionario = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbUsuario = new javax.swing.JTable();
        lblFuncionarioFoto = new javax.swing.JLabel();
        BtnEditar = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciamento: Funcionario");

        jPanelBuscaFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relação de Funcionários", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tbUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        tbUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuarioMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbUsuario);

        javax.swing.GroupLayout jPanelBuscaFuncionarioLayout = new javax.swing.GroupLayout(jPanelBuscaFuncionario);
        jPanelBuscaFuncionario.setLayout(jPanelBuscaFuncionarioLayout);
        jPanelBuscaFuncionarioLayout.setHorizontalGroup(
            jPanelBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscaFuncionarioLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFuncionarioFoto)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanelBuscaFuncionarioLayout.setVerticalGroup(
            jPanelBuscaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscaFuncionarioLayout.createSequentialGroup()
                .addComponent(lblFuncionarioFoto)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        BtnEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/botão_Editar2.jpg"))); // NOI18N
        BtnEditar.setText("Editar");
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/Botão_Excluir.jpg"))); // NOI18N
        BtnExcluir.setText("Excluir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBuscaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBuscaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuarioMouseClicked
     
    }//GEN-LAST:event_tbUsuarioMouseClicked

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        int linhaSelecionada = this.tbUsuario.getSelectedRow();
        
        if(linhaSelecionada != -1){
                 Usuario usuarioSelecionado = this.usuarios.get(linhaSelecionada);
                 EdicaoUsuario telaEditarDadosUsuario = new EdicaoUsuario(this, usuarioSelecionado);
        }
    }//GEN-LAST:event_BtnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciamentoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciamentoFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JPanel jPanelBuscaFuncionario;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblFuncionarioFoto;
    private javax.swing.JTable tbUsuario;
    // End of variables declaration//GEN-END:variables

public class Model extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return usuarios.size();
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
           Usuario usuario = usuarios.get(linha);
            if(coluna == 0){
                return usuario.getCPF();
            }else if(coluna == 1){
                return usuario.getRG();
            }else if(coluna == 2){
               return usuario.getNome();
            }else if(coluna == 3){
                return usuario.getTelefone();
            }else if(coluna == 4){
                return usuario.getEndereco();
            }
            return null;
        }
        
        @Override
        public String getColumnName(int coluna){
            if(coluna == 0){
                return "CPF";
            }else if(coluna == 1){
                return "RG";
            }else if(coluna == 2){
               return "Nome"; 
            }else if(coluna == 3){
                return "Telefone";
            }else if(coluna == 4){
                return "Endereço";
            }
            return null;
    }
}
}