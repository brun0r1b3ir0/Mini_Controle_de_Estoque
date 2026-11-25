package org.example.view;

import org.example.dados.BancoDeDados;
import org.example.model.Categoria;
import org.example.model.Fornecedor;
import org.example.model.Produto;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TelaProduto extends javax.swing.JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nome", "Categoria", "Fornecedor", "Preço", "Quantidade"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public TelaProduto() {
        initComponents();
        tabela.setModel(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                carregarSelecionado();
            }
        });
        carregarCombos();
        carregarTabela();
    }

    private void carregarCombos() {
        Object categoriaAtual = cbCategoria.getSelectedItem();
        Object fornecedorAtual = cbFornecedor.getSelectedItem();

        cbCategoria.setModel(new DefaultComboBoxModel<>(BancoDeDados.categorias.listarTodos().toArray(new Categoria[0])));
        cbFornecedor.setModel(new DefaultComboBoxModel<>(BancoDeDados.fornecedores.listarTodos().toArray(new Fornecedor[0])));

        cbCategoria.setSelectedItem(categoriaAtual);
        cbFornecedor.setSelectedItem(fornecedorAtual);
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        for (Produto p : BancoDeDados.produtos.listarTodos()) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getCategoria(), p.getFornecedor(),
                    String.format("R$ %.2f", p.getPreco()), p.getQuantidade()});
        }
    }

    private void carregarSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            return;
        }
        Produto p = BancoDeDados.produtos.buscarPorId((int) modelo.getValueAt(linha, 0));
        txtId.setText(String.valueOf(p.getId()));
        txtNome.setText(p.getNome());
        txtPreco.setText(String.format("%.2f", p.getPreco()));
        txtQuantidade.setText(String.valueOf(p.getQuantidade()));
        cbCategoria.setSelectedItem(p.getCategoria());
        cbFornecedor.setSelectedItem(p.getFornecedor());
    }

    private void limparCampos() {
        tabela.clearSelection();
        txtId.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        cbCategoria.setSelectedIndex(-1);
        cbFornecedor.setSelectedIndex(-1);
        txtNome.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelCampos = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblPreco = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        lblQuantidade = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        lblFornecedor = new javax.swing.JLabel();
        cbFornecedor = new javax.swing.JComboBox<>();
        scrollTabela = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        painelBotoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        painelCampos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelCampos.setLayout(new java.awt.GridLayout(0, 2, 5, 5));

        lblId.setText("ID:");
        painelCampos.add(lblId);

        txtId.setEditable(false);
        painelCampos.add(txtId);

        lblNome.setText("Nome:");
        painelCampos.add(lblNome);
        painelCampos.add(txtNome);

        lblPreco.setText("Preço (R$):");
        painelCampos.add(lblPreco);
        painelCampos.add(txtPreco);

        lblQuantidade.setText("Quantidade em estoque:");
        painelCampos.add(lblQuantidade);

        txtQuantidade.setEditable(false);
        painelCampos.add(txtQuantidade);

        lblCategoria.setText("Categoria:");
        painelCampos.add(lblCategoria);
        painelCampos.add(cbCategoria);

        lblFornecedor.setText("Fornecedor:");
        painelCampos.add(lblFornecedor);
        painelCampos.add(cbFornecedor);

        getContentPane().add(painelCampos, java.awt.BorderLayout.NORTH);

        scrollTabela.setPreferredSize(new java.awt.Dimension(650, 250));

        scrollTabela.setViewportView(tabela);

        getContentPane().add(scrollTabela, java.awt.BorderLayout.CENTER);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        painelBotoes.add(btnNovo);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        painelBotoes.add(btnSalvar);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        painelBotoes.add(btnExcluir);

        getContentPane().add(painelBotoes, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // a tela de categoria/fornecedor/movimentação pode ter mudado algo enquanto essa estava aberta
        carregarCombos();
        carregarTabela();
    }//GEN-LAST:event_formWindowActivated

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto.");
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(txtPreco.getText().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido. Use apenas números, ex.: 9,90");
            return;
        }
        if (preco <= 0) {
            JOptionPane.showMessageDialog(this, "O preço deve ser maior que zero.");
            return;
        }

        Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
        Fornecedor fornecedor = (Fornecedor) cbFornecedor.getSelectedItem();
        if (categoria == null || fornecedor == null) {
            JOptionPane.showMessageDialog(this, "Selecione a categoria e o fornecedor.");
            return;
        }

        if (txtId.getText().isEmpty()) {
            BancoDeDados.produtos.inserir(new Produto(nome, preco, 0, categoria, fornecedor));
        } else {
            // altera o próprio objeto pra não perder a quantidade em estoque
            Produto p = BancoDeDados.produtos.buscarPorId(Integer.parseInt(txtId.getText()));
            p.setNome(nome);
            p.setPreco(preco);
            p.setCategoria(categoria);
            p.setFornecedor(fornecedor);
            BancoDeDados.produtos.alterar(p);
        }
        carregarTabela();
        limparCampos();
        JOptionPane.showMessageDialog(this, "Produto salvo com sucesso.");
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja excluir o produto " + txtNome.getText() + "?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            BancoDeDados.produtos.excluir(Integer.parseInt(txtId.getText()));
            carregarTabela();
            limparCampos();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<org.example.model.Categoria> cbCategoria;
    private javax.swing.JComboBox<org.example.model.Fornecedor> cbFornecedor;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblFornecedor;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
