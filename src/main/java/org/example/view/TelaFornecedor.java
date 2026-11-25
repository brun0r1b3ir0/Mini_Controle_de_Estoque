package org.example.view;

import org.example.dados.BancoDeDados;
import org.example.model.Fornecedor;
import org.example.model.Produto;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TelaFornecedor extends javax.swing.JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "CNPJ", "Telefone"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public TelaFornecedor() {
        initComponents();
        tabela.setModel(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                carregarSelecionado();
            }
        });
        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        for (Fornecedor f : BancoDeDados.fornecedores.listarTodos()) {
            modelo.addRow(new Object[]{f.getId(), f.getNome(), f.getCnpj(), f.getTelefone()});
        }
    }

    private void carregarSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            return;
        }
        Fornecedor f = BancoDeDados.fornecedores.buscarPorId((int) modelo.getValueAt(linha, 0));
        txtId.setText(String.valueOf(f.getId()));
        txtNome.setText(f.getNome());
        txtCnpj.setText(f.getCnpj());
        txtTelefone.setText(f.getTelefone());
    }

    private void limparCampos() {
        tabela.clearSelection();
        txtId.setText("");
        txtNome.setText("");
        txtCnpj.setText("");
        txtTelefone.setText("");
        txtNome.requestFocus();
    }

    private boolean emUso(int idFornecedor) {
        for (Produto p : BancoDeDados.produtos.listarTodos()) {
            if (p.getFornecedor() != null && p.getFornecedor().getId() == idFornecedor) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelCampos = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCnpj = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        scrollTabela = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        painelBotoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Fornecedores");

        painelCampos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelCampos.setLayout(new java.awt.GridLayout(0, 2, 5, 5));

        lblId.setText("ID:");
        painelCampos.add(lblId);

        txtId.setEditable(false);
        painelCampos.add(txtId);

        lblNome.setText("Nome:");
        painelCampos.add(lblNome);
        painelCampos.add(txtNome);

        lblCnpj.setText("CNPJ:");
        painelCampos.add(lblCnpj);
        painelCampos.add(txtCnpj);

        lblTelefone.setText("Telefone:");
        painelCampos.add(lblTelefone);
        painelCampos.add(txtTelefone);

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

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do fornecedor.");
            return;
        }
        String cnpj = txtCnpj.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (txtId.getText().isEmpty()) {
            BancoDeDados.fornecedores.inserir(new Fornecedor(nome, cnpj, telefone));
        } else {
            Fornecedor f = BancoDeDados.fornecedores.buscarPorId(Integer.parseInt(txtId.getText()));
            f.setNome(nome);
            f.setCnpj(cnpj);
            f.setTelefone(telefone);
            BancoDeDados.fornecedores.alterar(f);
        }
        carregarTabela();
        limparCampos();
        JOptionPane.showMessageDialog(this, "Fornecedor salvo com sucesso.");
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor na tabela.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        if (emUso(id)) {
            JOptionPane.showMessageDialog(this, "Esse fornecedor está vinculado a algum produto e não pode ser excluído.");
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja excluir o fornecedor " + txtNome.getText() + "?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            BancoDeDados.fornecedores.excluir(id);
            carregarTabela();
            limparCampos();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCnpj;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtCnpj;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
