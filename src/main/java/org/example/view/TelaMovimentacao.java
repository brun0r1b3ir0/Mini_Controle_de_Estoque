package org.example.view;

import org.example.dados.BancoDeDados;
import org.example.model.Produto;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;

public class TelaMovimentacao extends javax.swing.JFrame {

    private static final int ESTOQUE_MINIMO = 5;
    private static final Color COR_ESTOQUE_BAIXO = new Color(255, 205, 205);

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Produto", "Categoria", "Quantidade"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public TelaMovimentacao() {
        initComponents();
        tabela.setModel(modelo);
        tabela.setRowSelectionAllowed(false);
        tabela.setDefaultRenderer(Object.class, new RenderizadorEstoque());
        tabela.setDefaultRenderer(Integer.class, new RenderizadorEstoque());
        carregarDados();
    }

    private void carregarDados() {
        Object selecionado = cbProduto.getSelectedItem();
        cbProduto.setModel(new DefaultComboBoxModel<>(BancoDeDados.produtos.listarTodos().toArray(new Produto[0])));
        if (selecionado != null) {
            cbProduto.setSelectedItem(selecionado);
        }
        atualizarEstoqueSelecionado();
        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        int estoqueBaixo = 0;
        for (Produto p : BancoDeDados.produtos.listarTodos()) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getCategoria(), p.getQuantidade()});
            if (p.getQuantidade() < ESTOQUE_MINIMO) {
                estoqueBaixo++;
            }
        }
        lblEstoqueBaixo.setText("Itens com estoque baixo (menos de " + ESTOQUE_MINIMO + "): " + estoqueBaixo);
    }

    private void atualizarEstoqueSelecionado() {
        Produto p = (Produto) cbProduto.getSelectedItem();
        txtEstoque.setText(p == null ? "" : String.valueOf(p.getQuantidade()));
    }

    /** Pinta de vermelho as linhas dos produtos com estoque abaixo do mínimo. */
    private class RenderizadorEstoque extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            int quantidade = (int) modelo.getValueAt(table.convertRowIndexToModel(row), 3);
            c.setBackground(quantidade < ESTOQUE_MINIMO ? COR_ESTOQUE_BAIXO : table.getBackground());
            return c;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        painelCampos = new javax.swing.JPanel();
        lblProduto = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox<>();
        lblEstoque = new javax.swing.JLabel();
        txtEstoque = new javax.swing.JTextField();
        lblQuantidade = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        painelTipo = new javax.swing.JPanel();
        rbEntrada = new javax.swing.JRadioButton();
        rbSaida = new javax.swing.JRadioButton();
        scrollTabela = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        painelBotoes = new javax.swing.JPanel();
        lblEstoqueBaixo = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movimentação de Estoque");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        painelCampos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelCampos.setLayout(new java.awt.GridLayout(0, 2, 5, 5));

        lblProduto.setText("Produto:");
        painelCampos.add(lblProduto);

        cbProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProdutoActionPerformed(evt);
            }
        });
        painelCampos.add(cbProduto);

        lblEstoque.setText("Estoque atual:");
        painelCampos.add(lblEstoque);

        txtEstoque.setEditable(false);
        painelCampos.add(txtEstoque);

        lblQuantidade.setText("Quantidade:");
        painelCampos.add(lblQuantidade);
        painelCampos.add(txtQuantidade);

        lblTipo.setText("Tipo:");
        painelCampos.add(lblTipo);

        painelTipo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        grupoTipo.add(rbEntrada);
        rbEntrada.setSelected(true);
        rbEntrada.setText("Entrada");
        painelTipo.add(rbEntrada);

        grupoTipo.add(rbSaida);
        rbSaida.setText("Saída");
        painelTipo.add(rbSaida);

        painelCampos.add(painelTipo);

        getContentPane().add(painelCampos, java.awt.BorderLayout.NORTH);

        scrollTabela.setPreferredSize(new java.awt.Dimension(650, 250));

        scrollTabela.setViewportView(tabela);

        getContentPane().add(scrollTabela, java.awt.BorderLayout.CENTER);

        painelBotoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        painelBotoes.setLayout(new java.awt.BorderLayout());

        lblEstoqueBaixo.setText("Itens com estoque baixo: 0");
        painelBotoes.add(lblEstoqueBaixo, java.awt.BorderLayout.CENTER);

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        painelBotoes.add(btnConfirmar, java.awt.BorderLayout.EAST);

        getContentPane().add(painelBotoes, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Recarrega ao voltar para a tela, para pegar produtos cadastrados/alterados em outras telas.
        carregarDados();
    }//GEN-LAST:event_formWindowActivated

    private void cbProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProdutoActionPerformed
        atualizarEstoqueSelecionado();
    }//GEN-LAST:event_cbProdutoActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        Produto produto = (Produto) cbProduto.getSelectedItem();
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }

        int qtd;
        try {
            qtd = Integer.parseInt(txtQuantidade.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe uma quantidade inteira válida.");
            txtQuantidade.requestFocus();
            return;
        }

        try {
            if (rbEntrada.isSelected()) {
                produto.entrada(qtd);
            } else {
                produto.saida(qtd);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Movimentação não realizada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BancoDeDados.produtos.alterar(produto);
        carregarDados();
        txtQuantidade.setText("");
        JOptionPane.showMessageDialog(this, (rbEntrada.isSelected() ? "Entrada" : "Saída")
                + " registrada. Estoque atual de " + produto.getNome() + ": " + produto.getQuantidade());
    }//GEN-LAST:event_btnConfirmarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<org.example.model.Produto> cbProduto;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JLabel lblEstoqueBaixo;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JPanel painelTipo;
    private javax.swing.JRadioButton rbEntrada;
    private javax.swing.JRadioButton rbSaida;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
