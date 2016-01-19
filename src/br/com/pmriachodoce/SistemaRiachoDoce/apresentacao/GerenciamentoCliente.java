/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.apresentacao;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Cliente;
import br.com.pmriachodoce.SistemaRiachoDoce.negocio.ClienteBO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Késia Correia
 */
public class GerenciamentoCliente extends javax.swing.JFrame {
    List<Cliente> clientes = new ArrayList<>();
    MenuPrincipal menuPrincipal = null;
    
    /**
     * Creates new form GerenciamentoCliente
     */
    public GerenciamentoCliente() {
        initComponents();
    }
    

    
    public GerenciamentoCliente(MenuPrincipal menuPrincipal) {
        initComponents();  
        buscarTodosCliente();
        this.menuPrincipal = menuPrincipal;
    }

    
    public void buscarTodosCliente(){
        
        try{         
            ClienteBO clienteBO = new ClienteBO();
            clientes = clienteBO.buscarTodos();
            carregarDadosTabela();
        }catch(Exception e){
            exibirMensagemErro( "Erro desconhecido. Contate com administrador", "Consultar Cliente");
        }
    }
    
    public void carregarDadosTabela(){
        Model modelo = new Model();
        this.tbClientes.setModel(modelo);
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

        jPanelBuscaClientes = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        lblFotoCliente = new javax.swing.JLabel();
        BtnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciamento: Cliente");

        jPanelBuscaClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relação de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbClientes);

        javax.swing.GroupLayout jPanelBuscaClientesLayout = new javax.swing.GroupLayout(jPanelBuscaClientes);
        jPanelBuscaClientes.setLayout(jPanelBuscaClientesLayout);
        jPanelBuscaClientesLayout.setHorizontalGroup(
            jPanelBuscaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscaClientesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFotoCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBuscaClientesLayout.setVerticalGroup(
            jPanelBuscaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuscaClientesLayout.createSequentialGroup()
                .addGroup(jPanelBuscaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFotoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBuscaClientesLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/Botão_Excluir.jpg"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBuscaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanelBuscaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked
        //this.btnExcluir.setEnabled(true);
        //this.btnSalvarAlteracao.setEnabled(true);

        try {
            // preencher();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_tbClientesMouseClicked

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        int linhaSelecionada = this.tbClientes.getSelectedRow();
        if(linhaSelecionada != -1){
                 Cliente clienteSelecionado = this.clientes.get(linhaSelecionada);  
                 EdicaoCliente telaEditarDadosCliente = new EdicaoCliente(this, clienteSelecionado);
        }
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linhaSelecionada = this.tbClientes.getSelectedRow();
        if(linhaSelecionada != -1){
            Cliente clienteSelecionado = this.clientes.get(linhaSelecionada);

            int resposta;
            String mensagem = "Deseja excluir cliente " + clienteSelecionado.getNome() + " ?";
            String titulo = "Excluir cliente";
            resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                try {
                    ClienteBO clienteBO = new ClienteBO();
                    clienteBO.deletarCliente(clienteSelecionado);
                    this.buscarTodosCliente();
                    exibirMensagemSucesso("Dados excluidos com sucesso!", "Excluir cliente");                
                } catch (Exception e) {
                    mensagem = "Erro desconhecido. Entre em contato com o administrador do sistema.";
                    exibirMensagemErro(mensagem, "Excluir cliente");
                }
            }else{
                exibirMensagemSucesso("Dados não excluidos", "Excluir cliente");
            }
        }else{
          String mensagem = "Nenhum cliente selecionado.";
          JOptionPane.showMessageDialog(this, mensagem, "Excluir cliente", JOptionPane.INFORMATION_MESSAGE);  
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciamentoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciamentoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciamentoCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JPanel jPanelBuscaClientes;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblFotoCliente;
    private javax.swing.JTable tbClientes;
    // End of variables declaration//GEN-END:variables

 public class Model extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return clientes.size();
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            Cliente cliente = clientes.get(linha);
            if(coluna == 0){
                return cliente.getCPF();
            }else if(coluna == 1){
                return cliente.getRG();
            }else if(coluna == 2){
               return cliente.getNome();
            }else if(coluna == 3){
                return cliente.getTelefone();
            }else if(coluna == 4){
                return cliente.getEndereco();
            }else{
                if(cliente.isStatus()){
                    return "Positivo";
                }else{
                    return "Negativo";
                }
            }
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
            }else{
                return "Situação";
            }
        }
    
    }

}
