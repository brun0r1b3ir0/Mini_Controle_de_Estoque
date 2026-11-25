package org.example.view;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        itemCategorias = new javax.swing.JMenuItem();
        itemFornecedores = new javax.swing.JMenuItem();
        itemProdutos = new javax.swing.JMenuItem();
        menuEstoque = new javax.swing.JMenu();
        itemMovimentacao = new javax.swing.JMenuItem();
        menuSistema = new javax.swing.JMenu();
        itemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mini Controle de Estoque");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Mini Controle de Estoque");
        lblTitulo.setPreferredSize(new java.awt.Dimension(500, 250));
        getContentPane().add(lblTitulo, java.awt.BorderLayout.CENTER);

        menuCadastros.setText("Cadastros");

        itemCategorias.setText("Categorias");
        itemCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCategoriasActionPerformed(evt);
            }
        });
        menuCadastros.add(itemCategorias);

        itemFornecedores.setText("Fornecedores");
        itemFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFornecedoresActionPerformed(evt);
            }
        });
        menuCadastros.add(itemFornecedores);

        itemProdutos.setText("Produtos");
        itemProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProdutosActionPerformed(evt);
            }
        });
        menuCadastros.add(itemProdutos);

        barraMenu.add(menuCadastros);

        menuEstoque.setText("Estoque");

        itemMovimentacao.setText("Movimentação");
        itemMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMovimentacaoActionPerformed(evt);
            }
        });
        menuEstoque.add(itemMovimentacao);

        barraMenu.add(menuEstoque);

        menuSistema.setText("Sistema");

        itemSair.setText("Sair");
        itemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSairActionPerformed(evt);
            }
        });
        menuSistema.add(itemSair);

        barraMenu.add(menuSistema);

        setJMenuBar(barraMenu);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCategoriasActionPerformed
        new TelaCategoria().setVisible(true);
    }//GEN-LAST:event_itemCategoriasActionPerformed

    private void itemFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFornecedoresActionPerformed
        new TelaFornecedor().setVisible(true);
    }//GEN-LAST:event_itemFornecedoresActionPerformed

    private void itemProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProdutosActionPerformed
        new TelaProduto().setVisible(true);
    }//GEN-LAST:event_itemProdutosActionPerformed

    private void itemMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMovimentacaoActionPerformed
        new TelaMovimentacao().setVisible(true);
    }//GEN-LAST:event_itemMovimentacaoActionPerformed

    private void itemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem itemCategorias;
    private javax.swing.JMenuItem itemFornecedores;
    private javax.swing.JMenuItem itemMovimentacao;
    private javax.swing.JMenuItem itemProdutos;
    private javax.swing.JMenuItem itemSair;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuEstoque;
    private javax.swing.JMenu menuSistema;
    // End of variables declaration//GEN-END:variables
}
