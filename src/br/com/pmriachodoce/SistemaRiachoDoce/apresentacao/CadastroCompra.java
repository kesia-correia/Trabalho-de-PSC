/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.apresentacao;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Cliente;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Compra;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.ItensCompra;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Produto;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Usuario;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.CampoObrigatorioException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.CamposVaziosException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.InvalidoException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.ProdutoExistenteException;
import br.com.pmriachodoce.SistemaRiachoDoce.negocio.ClienteBO;
import br.com.pmriachodoce.SistemaRiachoDoce.negocio.CompraBO;
import br.com.pmriachodoce.SistemaRiachoDoce.negocio.ProdutoBO;
import br.com.pmriachodoce.SistemaRiachoDoce.negocio.UsuarioBO;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Késia Correia
 */
public class CadastroCompra extends javax.swing.JFrame {
 List<ItensCompra> itensCompra = null;
    Usuario usuario = null;
    Produto produtoAtual = null;
    Compra  compra = null;
    Cliente cliente = null;

    /**
     * Creates new form CadastroCompra
     */
    public CadastroCompra() throws SQLException {
        initComponents();
        this.itensCompra = new ArrayList<>();
        carregarDadosTabelaItens();
        compra = new Compra();
        this.setDadosVenda();
        this.buscarUsuario();
    }
    
     public CadastroCompra(Usuario usuario) {
        initComponents();
        this.configurarComponestesTela();
        this.itensCompra = new ArrayList<>();
        carregarDadosTabelaItens();
        compra = new Compra();
        this.setDadosVenda();
        this.usuario = usuario;
    }

    public void atualizarDadosTelaCompleta() throws SQLException {
        this.configurarComponestesTela();
        this.itensCompra = new ArrayList<>();
        carregarDadosTabelaItens();
        cliente = null;
        compra = null;
        compra = new Compra();
        this.setDadosVenda();
        this.buscarUsuario();
        this.limparDadosClienteTela();
    }
    
    private void addTodosDadosVenda() {
        this.compra.setCliente(cliente);
        this.compra.setUsuario(usuario);
        this.compra.setItensCompra(itensCompra);
    }

    public void buscarUsuario() throws SQLException {
            UsuarioBO usuarioBO = new UsuarioBO();
            usuario = usuarioBO.buscarTodos().get(0);
    }

    public void setDadosVenda() {
        lblDataCompra.setText(compra.getDataFormatada());
        lblValorTotal.setText("R$ " + compra.getValorTotal());
    }

    public void setDadosProdutoTela(Produto produto) {
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        txtPrecoUnitario.setText(formatador.format(produto.getValorUnitario()));
    }

    private void configurarComponestesTela() {
        lblProduto.setVisible(false);
        jLabel3.setVisible(false);
    }

    private void setNomeClienteTela() {
        lblNomeCliente.setVisible(true);
        jLabel3.setText(cliente.getNome());
        jLabel3.setVisible(true);
    }
      
    private void limparDadosClienteTela() {
        txtCPF.setText("");
        lblNomeCliente.setText("");
        jLabel3.setText("");
        configurarComponestesTela();
    }

    private void limparDadosProdutoInserido() {
        txtCodigoProduto.setText("");
        txtNome.setText("");
        txtPrecoUnitario.setText("");
    }

    private int lerQuantidadeProduto() {
        String strQuantidade = txtNome.getText().trim();

        String mensagem = "";
        int quantidade = -1;
        if (strQuantidade.isEmpty()) {
            mensagem = "Informe a quantidade do produto";
        } else {
            try {
                quantidade = Integer.parseInt(strQuantidade);
            } catch (NumberFormatException nfe) {
                mensagem = "Quantidade invalida";
            }
        }
        if (quantidade <= 0) {
            mensagem = "Quantidade invalida";
        }
        if (!(mensagem == "" || mensagem == null)) {
            throw new CamposVaziosException(mensagem);
        }

        return quantidade;
    }

    public void carregarDadosTabelaItens() {
        Model model = new Model();
        this.JTProdutosAderidos.setModel(model);
    }

    public void verificaProdutoJaInseridoNaLista(int codigo) {
        for (ItensCompra item : itensCompra) {
            if (item.getProduto().getCodigo() == codigo) {
                throw new ProdutoExistenteException("Selecione o produto que deseja alterar e clique no botão EDITAR!");
            }
        }
    }

    public void existeProdutoParaAdicionar() {
        if (produtoAtual == null) {
            throw new InvalidoException(("Por favor, adicione um produto!"));
        }
    }
    
    private String lerCPF() {
        String cpf = txtCPF.getText().trim();

        String mensagem = "";

        if (cpf.length() < 11) {
            mensagem = "CPF inválido.";
        }

        if (!(mensagem == "" || mensagem == null)) {
            throw new CamposVaziosException(mensagem);
        }

        return cpf;
    }
   
    private int lerCodigoProduto() {
        String strCodigo = txtCodigoProduto.getText().trim();

        String mensagem = "";
        int codigo = -1;
        if (strCodigo.isEmpty()) {
            mensagem = "Informe o código do produto";
        } else {
            try {
                codigo = Integer.parseInt(strCodigo);
            } catch (NumberFormatException nfe) {
                mensagem = "Código invalido";
            }
        }

        if (!(mensagem == "" || mensagem == null)) {
            throw new CamposVaziosException(mensagem);
        }

        return codigo;
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JpCadastroCompra = new javax.swing.JPanel();
        lblDataCompra = new javax.swing.JLabel();
        lblClienteCompra = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        lblNomeCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JPProdutosAderidos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTProdutosAderidos = new javax.swing.JTable();
        Editar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        JPProdutos = new javax.swing.JPanel();
        lblProduto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        txtPrecoUnitario = new javax.swing.JFormattedTextField();
        BtnAdicionarProduto = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        txtCodigoProduto = new javax.swing.JTextField();
        btnConsultarProduto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        JPPagamento = new javax.swing.JPanel();
        lblValorTotal = new javax.swing.JLabel();
        lblVendaAVista = new javax.swing.JLabel();
        cbxClienteAVista = new javax.swing.JCheckBox();
        BtnCadastrar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Padaria & Mercearia Riacho Doce");

        JpCadastroCompra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblDataCompra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDataCompra.setText("Data da Compra:");

        lblClienteCompra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblClienteCompra.setText("CPF:");

        txtCPF.setText("   .   .   -");

        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/pesquisar.png"))); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        lblNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomeCliente.setText("Nome:");

        javax.swing.GroupLayout JpCadastroCompraLayout = new javax.swing.GroupLayout(JpCadastroCompra);
        JpCadastroCompra.setLayout(JpCadastroCompraLayout);
        JpCadastroCompraLayout.setHorizontalGroup(
            JpCadastroCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpCadastroCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpCadastroCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataCompra)
                    .addGroup(JpCadastroCompraLayout.createSequentialGroup()
                        .addGroup(JpCadastroCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpCadastroCompraLayout.createSequentialGroup()
                                .addComponent(lblNomeCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpCadastroCompraLayout.createSequentialGroup()
                                .addComponent(lblClienteCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpCadastroCompraLayout.setVerticalGroup(
            JpCadastroCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpCadastroCompraLayout.createSequentialGroup()
                .addComponent(lblDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(JpCadastroCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClienteCompra)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpCadastroCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpCadastroCompraLayout.createSequentialGroup()
                        .addComponent(lblNomeCliente)
                        .addContainerGap())
                    .addGroup(JpCadastroCompraLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        JPProdutosAderidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos Aderidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        JTProdutosAderidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTProdutosAderidos);

        Editar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/botão_Editar2.jpg"))); // NOI18N
        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
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

        javax.swing.GroupLayout JPProdutosAderidosLayout = new javax.swing.GroupLayout(JPProdutosAderidos);
        JPProdutosAderidos.setLayout(JPProdutosAderidosLayout);
        JPProdutosAderidosLayout.setHorizontalGroup(
            JPProdutosAderidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPProdutosAderidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(JPProdutosAderidosLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(Editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExcluir)
                .addGap(80, 80, 80))
        );
        JPProdutosAderidosLayout.setVerticalGroup(
            JPProdutosAderidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPProdutosAderidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPProdutosAderidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Editar)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPProdutos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblProduto.setText("Produto:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        lblPreco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPreco.setText("Preço Unitário:");

        txtPrecoUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        BtnAdicionarProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/add.png"))); // NOI18N
        BtnAdicionarProduto.setText("Adicionar");
        BtnAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdicionarProdutoActionPerformed(evt);
            }
        });

        txtNome.setForeground(new java.awt.Color(153, 153, 153));

        txtCodigoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProdutoActionPerformed(evt);
            }
        });

        btnConsultarProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConsultarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/pesquisar.png"))); // NOI18N
        btnConsultarProduto.setText("Consultar");
        btnConsultarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarProdutoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Quantidade:");

        javax.swing.GroupLayout JPProdutosLayout = new javax.swing.GroupLayout(JPProdutos);
        JPProdutos.setLayout(JPProdutosLayout);
        JPProdutosLayout.setHorizontalGroup(
            JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblProduto)
                    .addComponent(jLabel4))
                .addGroup(JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPProdutosLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(lblPreco)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnAdicionarProduto)
                        .addGap(55, 55, 55))
                    .addGroup(JPProdutosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultarProduto)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))))
        );
        JPProdutosLayout.setVerticalGroup(
            JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarProduto)
                    .addComponent(lblProduto)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPProdutosLayout.createSequentialGroup()
                        .addGroup(JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnAdicionarProduto))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPProdutosLayout.createSequentialGroup()
                                .addComponent(lblPreco)
                                .addGap(7, 7, 7)))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPProdutosLayout.createSequentialGroup()
                        .addGroup(JPProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18))))
        );

        JPPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblValorTotal.setText("Valor Total:");

        lblVendaAVista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblVendaAVista.setText("Compra À Vista");

        cbxClienteAVista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClienteAVistaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPPagamentoLayout = new javax.swing.GroupLayout(JPPagamento);
        JPPagamento.setLayout(JPPagamentoLayout);
        JPPagamentoLayout.setHorizontalGroup(
            JPPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPPagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorTotal)
                    .addGroup(JPPagamentoLayout.createSequentialGroup()
                        .addComponent(lblVendaAVista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxClienteAVista, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPPagamentoLayout.setVerticalGroup(
            JPPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPPagamentoLayout.createSequentialGroup()
                .addGroup(JPPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVendaAVista)
                    .addComponent(cbxClienteAVista))
                .addGap(14, 14, 14)
                .addComponent(lblValorTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnCadastrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/botão_cadastrar.png"))); // NOI18N
        BtnCadastrar.setText("Cadastrar");
        BtnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCadastrarActionPerformed(evt);
            }
        });

        BtnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pmriachodoce/SistemaRiachoDoce/apresentacao/icones/botão_cancelar.png"))); // NOI18N
        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpCadastroCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPProdutosAderidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(BtnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpCadastroCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPProdutosAderidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCadastrar)
                    .addComponent(BtnCancelar))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
       this.dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            ClienteBO clienteBO = new ClienteBO();
            cliente = clienteBO.buscarCpf(lerCPF());   
            this.setNomeClienteTela();
        }catch(SQLException e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Compra", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
       int linhaSelecionada = this.JTProdutosAderidos.getSelectedRow();
        if (linhaSelecionada != -1) {
                ItensCompra item = this.itensCompra.get(linhaSelecionada);
                produtoAtual = item.getProduto();
                txtCodigoProduto.setText(String.valueOf(produtoAtual.getCodigo()));
                txtNome.setText(String.valueOf(item.getQuantidade()));
                setDadosProdutoTela(produtoAtual);
                compra.setValorTotal(compra.getValorTotal() - item.getValorTotal());
                this.itensCompra.remove(item);
                carregarDadosTabelaItens();
        } else {
            String mensagem = "Nenhum produto selecionado.";
            JOptionPane.showMessageDialog(this, mensagem, "Alterar produto", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void BtnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdicionarProdutoActionPerformed
        existeProdutoParaAdicionar();
        ItensCompra item = new ItensCompra();
        int quantidade = -1;
        quantidade = lerQuantidadeProduto();
        verificaProdutoJaInseridoNaLista(produtoAtual.getCodigo());
        if (quantidade <= produtoAtual.getQuantidadeEstoque()) {
            item.setProduto(produtoAtual);
            item.setQuantidade(quantidade);
            item.setValorUnitario(produtoAtual.getValorUnitario());
            item.setValorTotal(quantidade * produtoAtual.getValorUnitario());
            this.itensCompra.add(item);
            compra.setValorTotal(compra.getValorTotal() + (quantidade * produtoAtual.getValorUnitario()));
            this.setDadosVenda();
            this.carregarDadosTabelaItens();
            limparDadosProdutoInserido();
            produtoAtual = null;
        }else{
            System.out.println("Adicione um produdto!");
        }
    }//GEN-LAST:event_BtnAdicionarProdutoActionPerformed

    private void txtCodigoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProdutoActionPerformed

    private void btnConsultarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarProdutoActionPerformed
        ProdutoBO produtoBO = new ProdutoBO();
     try {
         produtoAtual = produtoBO.buscarByCodigo(lerCodigoProduto());
     } catch (SQLException ex) {
         Logger.getLogger(CadastroCompra.class.getName()).log(Level.SEVERE, null, ex);
     }
            if (produtoAtual == null) {
                System.out.println("Produto não encontrado!");
            } else {
                this.setDadosProdutoTela(produtoAtual);
            }
    }//GEN-LAST:event_btnConsultarProdutoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        int linhaSelecionada = this.JTProdutosAderidos.getSelectedRow();

        if (linhaSelecionada != -1) {
                ItensCompra item = this.itensCompra.get(linhaSelecionada);
                compra.setValorTotal(compra.getValorTotal() - item.getValorTotal());
                this.itensCompra.remove(item);
                setDadosVenda();
                carregarDadosTabelaItens();
        } else {  
             System.out.println("Nenhum produto selecionado! ");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed
    
    private void exibirMensagemSucesso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exibirMensagemErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public void setCompraAVista() throws SQLException{
            ClienteBO clienteBO = new ClienteBO();
            cliente = clienteBO.buscarCpf("000.000.000-00");
            this.setNomeClienteTela();
    }
    
    private void BtnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCadastrarActionPerformed
        try {
            this.addTodosDadosVenda();
            CompraBO compraBO = new CompraBO();
            compraBO.inserir(compra);
            this.atualizarDadosTelaCompleta();
        exibirMensagemSucesso("Cliente Cadastrado com sucesso!", "Cadastro de Cliente");    
        }catch(SQLException e){
           String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de cliente", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnCadastrarActionPerformed
public void desabilitarCamposInserirCliente(){
        txtCPF.setVisible(false);
        lblClienteCompra.setVisible(false);
        btnConsultar.setVisible(false);
    }
    
    public void habilitarCamposInserirCliente(){
        txtCPF.setVisible(true);
        jLabel3.setVisible(true);
        btnConsultar.setVisible(true);
        limparDadosClienteTela();
        cliente = new Cliente(); 
        setNomeClienteTela();
        this.limparDadosClienteTela();
    }
    
    private void cbxClienteAVistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClienteAVistaActionPerformed
        // TODO add your handling code here:
        if(cbxClienteAVista.isSelected()){
            this.desabilitarCamposInserirCliente();
            try {
                this.setCompraAVista();
            } catch (SQLException ex) {
                Logger.getLogger(CadastroCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            this.habilitarCamposInserirCliente();
        }
    }//GEN-LAST:event_cbxClienteAVistaActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroCompra().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroCompra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdicionarProduto;
    private javax.swing.JButton BtnCadastrar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton Editar;
    private javax.swing.JPanel JPPagamento;
    private javax.swing.JPanel JPProdutos;
    private javax.swing.JPanel JPProdutosAderidos;
    private javax.swing.JTable JTProdutosAderidos;
    private javax.swing.JPanel JpCadastroCompra;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarProduto;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JCheckBox cbxClienteAVista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblClienteCompra;
    private javax.swing.JLabel lblDataCompra;
    private javax.swing.JLabel lblNomeCliente;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JLabel lblVendaAVista;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtPrecoUnitario;
    // End of variables declaration//GEN-END:variables
 public class Model extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return itensCompra.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            ItensCompra item = itensCompra.get(linha);
            if (coluna == 0) {
                return item.getProduto().getCodigo();
            } else if (coluna == 1) {
                return item.getProduto().getNome();
            } else if (coluna == 2) {
                return item.getQuantidade();
            } else if (coluna == 3) {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(item.getValorUnitario());
            } else {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(item.getValorTotal());
            }
        }

        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "CODIGO";
            } else if (coluna == 1) {
                return "NOME";
            } else if (coluna == 2) {
                return "QUANTIDADE";
            } else if (coluna == 3) {
                return "VALOR UNITÁRIO";
            } else {
                return "VALOR TOTAL";
            }
        }

    }




}
