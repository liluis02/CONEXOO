/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class Vagas_PerfilEmpresa extends javax.swing.JPanel {
    private Runnable acaoPublicarVaga;

public void setAcaoPublicarVaga(Runnable acaoPublicarVaga) {
    this.acaoPublicarVaga = acaoPublicarVaga;
}
private int idUsuario;
    /**
     * Creates new form Vagas_PerfilEmpresa
     */
    public Vagas_PerfilEmpresa(int idUsuario) {
    initComponents();
    this.idUsuario = idUsuario;
    pnlCardVagaEmpresa.setVisible(false);
    pnlListaVagasEmpresa.setLayout(
        new javax.swing.BoxLayout(
            pnlListaVagasEmpresa,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    carregarVagasEmpresa();
}

    private void atualizarMensagemSemVagas() {
    lblSemVagasEmpresa.setVisible(
        pnlListaVagasEmpresa.getComponentCount() == 1
    );
}

private void carregarVagasEmpresa() {

    String sql = "SELECT v.id_vaga, v.titulo, "
            + "v.localizacao, v.salario_min, v.salario_max, v.status "
            + "FROM vagas v "
            + "INNER JOIN empresas e ON v.id_empresa = e.id_empresa "
            + "WHERE e.id_usuario = ? AND v.status = 'ATIVA'";

    
    pnlListaVagasEmpresa.removeAll();

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

            boolean encontrouVagas = false;

            while (rs.next()) {

                encontrouVagas = true;

                criarCardVagaEmpresa(
                        rs.getInt("id_vaga"),
                        rs.getString("titulo"),
                        rs.getString("localizacao"),
                        rs.getBigDecimal("salario_min"),
                        rs.getBigDecimal("salario_max"),
                        rs.getString("status")
                );
            }

            if (!encontrouVagas) {
                pnlListaVagasEmpresa.add(lblSemVagasEmpresa);
                lblSemVagasEmpresa.setVisible(true);
            }
        }

        pnlListaVagasEmpresa.revalidate();
        pnlListaVagasEmpresa.repaint();

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar vagas: " + e.getMessage()
        );
    }
}
public void atualizarVagasEmpresa() {
    carregarVagasEmpresa();
}

    
    private void criarCardVagaEmpresa(
        int idVaga,
        String titulo,
        String localizacao,
        java.math.BigDecimal salarioMin,
        java.math.BigDecimal salarioMax,
        String status) {

    // Copia o visual do card criado no Design
    javax.swing.JPanel card = new javax.swing.JPanel();
    card.setBackground(pnlCardVagaEmpresa.getBackground());
    card.setBorder(pnlCardVagaEmpresa.getBorder());
    card.setLayout(new java.awt.BorderLayout(15, 10));

    // Informações da vaga
    javax.swing.JPanel painelInfo = new javax.swing.JPanel();
    painelInfo.setOpaque(false);
    painelInfo.setLayout(new javax.swing.BoxLayout(
            painelInfo, javax.swing.BoxLayout.Y_AXIS));

    javax.swing.JLabel tituloLabel =
            new javax.swing.JLabel(titulo);
    tituloLabel.setFont(lblTituloVagaEmpresa.getFont());
    tituloLabel.setForeground(lblTituloVagaEmpresa.getForeground());

    javax.swing.JLabel localLabel =
            new javax.swing.JLabel(localizacao);
    localLabel.setFont(lblLocalizacaoVagaEmpresa.getFont());
    localLabel.setForeground(lblLocalizacaoVagaEmpresa.getForeground());

    java.text.NumberFormat moeda =
            java.text.NumberFormat.getCurrencyInstance(
                    new java.util.Locale("pt", "BR"));

    String salario = "Salário: "
            + moeda.format(salarioMin)
            + " até "
            + moeda.format(salarioMax);

    javax.swing.JLabel salarioLabel =
            new javax.swing.JLabel(salario);
    salarioLabel.setFont(lblSalarioVagaEmpresa.getFont());
    salarioLabel.setForeground(lblSalarioVagaEmpresa.getForeground());

    javax.swing.JLabel statusLabel =
            new javax.swing.JLabel("Status: " + status);
    statusLabel.setFont(lblStatusVagaEmpresa.getFont());
    statusLabel.setForeground(lblStatusVagaEmpresa.getForeground());

    painelInfo.add(tituloLabel);
    painelInfo.add(localLabel);
    painelInfo.add(salarioLabel);
    painelInfo.add(statusLabel);

    // Botões
    javax.swing.JPanel painelBotoes = new javax.swing.JPanel();
    painelBotoes.setOpaque(false);
    painelBotoes.setLayout(new javax.swing.BoxLayout(
            painelBotoes, javax.swing.BoxLayout.Y_AXIS));

    javax.swing.JButton editar = new javax.swing.JButton("Editar Vaga");
    editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    editar.setBackground(btnEditarVaga.getBackground());
    editar.setForeground(btnEditarVaga.getForeground());
    editar.addActionListener(e -> {
    if (acaoEditarVaga != null) {
        acaoEditarVaga.accept(idVaga);
    }
});

    javax.swing.JButton encerrar = new javax.swing.JButton("Encerrar Vaga");
    encerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    encerrar.setBackground(btnEncerrarVaga.getBackground());
    encerrar.setForeground(btnEncerrarVaga.getForeground());
    encerrar.addActionListener(e -> {
    encerrarVaga(idVaga);
});
    java.awt.Dimension tamanhoBotao =
        new java.awt.Dimension(120, 30);

editar.setPreferredSize(tamanhoBotao);
editar.setMaximumSize(tamanhoBotao);

encerrar.setPreferredSize(tamanhoBotao);
encerrar.setMaximumSize(tamanhoBotao);

    painelBotoes.add(editar);
    painelBotoes.add(javax.swing.Box.createVerticalStrut(10));
    painelBotoes.add(encerrar);

    card.add(painelInfo, java.awt.BorderLayout.CENTER);
    card.add(painelBotoes, java.awt.BorderLayout.EAST);

    // Define o tamanho de cada card
card.setPreferredSize(new java.awt.Dimension(750, 115));
card.setMaximumSize(new java.awt.Dimension(
    Integer.MAX_VALUE, 115));

    pnlListaVagasEmpresa.add(card);
    pnlListaVagasEmpresa.add(
            javax.swing.Box.createVerticalStrut(10));
}
private void encerrarVaga(int idVaga) {

    int confirmacao = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "Deseja realmente encerrar esta vaga?",
        "Confirmar encerramento",
        javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (confirmacao != javax.swing.JOptionPane.YES_OPTION) {
        return;
    }

    String sql = "UPDATE vagas SET status = 'ENCERRADA' "
               + "WHERE id_vaga = ? AND id_empresa IN "
               + "(SELECT id_empresa FROM empresas WHERE id_usuario = ?) "
               + "AND status = 'ATIVA'";

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idVaga);
        stmt.setInt(2, idUsuario);

        int linhas = stmt.executeUpdate();

        if (linhas > 0) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Vaga encerrada com sucesso!"
            );

            carregarVagasEmpresa();
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Não foi possível encerrar esta vaga."
            );
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao encerrar vaga: " + e.getMessage()
        );
    }
}
private java.util.function.IntConsumer acaoEditarVaga;

public void setAcaoEditarVaga(java.util.function.IntConsumer acaoEditarVaga) {
    this.acaoEditarVaga = acaoEditarVaga;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloVagasEmpresa = new javax.swing.JLabel();
        lblSubtituloVagasEmpresa = new javax.swing.JLabel();
        btnPublicarNovaVaga = new javax.swing.JButton();
        scrollVagasEmpresa = new javax.swing.JScrollPane();
        pnlListaVagasEmpresa = new javax.swing.JPanel();
        lblSemVagasEmpresa = new javax.swing.JLabel();
        pnlCardVagaEmpresa = new javax.swing.JPanel();
        lblTituloVagaEmpresa = new javax.swing.JLabel();
        lblLocalizacaoVagaEmpresa = new javax.swing.JLabel();
        lblSalarioVagaEmpresa = new javax.swing.JLabel();
        lblStatusVagaEmpresa = new javax.swing.JLabel();
        btnEditarVaga = new javax.swing.JButton();
        btnEncerrarVaga = new javax.swing.JButton();

        setBackground(new java.awt.Color(7, 27, 51));

        lblTituloVagasEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloVagasEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTituloVagasEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloVagasEmpresa.setText("Gerenciamento de vagas");

        lblSubtituloVagasEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubtituloVagasEmpresa.setForeground(new java.awt.Color(182, 197, 216));
        lblSubtituloVagasEmpresa.setText("Publique e gerencie as oportunidades da sua empresa.");

        btnPublicarNovaVaga.setBackground(new java.awt.Color(0, 207, 232));
        btnPublicarNovaVaga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPublicarNovaVaga.setForeground(new java.awt.Color(7, 21, 45));
        btnPublicarNovaVaga.setText("+ Publicar Nova Vaga");
        btnPublicarNovaVaga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPublicarNovaVaga.addActionListener(this::btnPublicarNovaVagaActionPerformed);

        scrollVagasEmpresa.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(19, 216, 245)));
        scrollVagasEmpresa.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlListaVagasEmpresa.setBackground(new java.awt.Color(16, 39, 71));

        lblSemVagasEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSemVagasEmpresa.setForeground(new java.awt.Color(169, 190, 213));
        lblSemVagasEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSemVagasEmpresa.setText("\tVocê ainda não publicou nenhuma vaga.");

        pnlCardVagaEmpresa.setBackground(new java.awt.Color(25, 55, 85));
        pnlCardVagaEmpresa.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblTituloVagaEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloVagaEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloVagaEmpresa.setText("Desenvolvedor java");

        lblLocalizacaoVagaEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLocalizacaoVagaEmpresa.setForeground(new java.awt.Color(169, 190, 213));
        lblLocalizacaoVagaEmpresa.setText("Bom Despacho - MG");

        lblSalarioVagaEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSalarioVagaEmpresa.setForeground(new java.awt.Color(0, 207, 232));
        lblSalarioVagaEmpresa.setText("Salário: R$ 3.000,00 até R$ 5.000,00");

        lblStatusVagaEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblStatusVagaEmpresa.setForeground(new java.awt.Color(34, 197, 94));
        lblStatusVagaEmpresa.setText("Status: Ativa");

        btnEditarVaga.setBackground(new java.awt.Color(8, 145, 178));
        btnEditarVaga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditarVaga.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarVaga.setText("\tEditar Vaga");
        btnEditarVaga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnEncerrarVaga.setBackground(new java.awt.Color(220, 38, 38));
        btnEncerrarVaga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEncerrarVaga.setForeground(new java.awt.Color(255, 255, 255));
        btnEncerrarVaga.setText("Encerrar Vaga");
        btnEncerrarVaga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlCardVagaEmpresaLayout = new javax.swing.GroupLayout(pnlCardVagaEmpresa);
        pnlCardVagaEmpresa.setLayout(pnlCardVagaEmpresaLayout);
        pnlCardVagaEmpresaLayout.setHorizontalGroup(
            pnlCardVagaEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardVagaEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCardVagaEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSalarioVagaEmpresa)
                    .addComponent(lblStatusVagaEmpresa)
                    .addComponent(lblLocalizacaoVagaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloVagaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCardVagaEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEncerrarVaga, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btnEditarVaga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        pnlCardVagaEmpresaLayout.setVerticalGroup(
            pnlCardVagaEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardVagaEmpresaLayout.createSequentialGroup()
                .addGroup(pnlCardVagaEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCardVagaEmpresaLayout.createSequentialGroup()
                        .addComponent(lblTituloVagaEmpresa)
                        .addGap(9, 9, 9)
                        .addComponent(lblLocalizacaoVagaEmpresa))
                    .addComponent(btnEditarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCardVagaEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCardVagaEmpresaLayout.createSequentialGroup()
                        .addComponent(lblSalarioVagaEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatusVagaEmpresa))
                    .addComponent(btnEncerrarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlListaVagasEmpresaLayout = new javax.swing.GroupLayout(pnlListaVagasEmpresa);
        pnlListaVagasEmpresa.setLayout(pnlListaVagasEmpresaLayout);
        pnlListaVagasEmpresaLayout.setHorizontalGroup(
            pnlListaVagasEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaVagasEmpresaLayout.createSequentialGroup()
                .addGroup(pnlListaVagasEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListaVagasEmpresaLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(lblSemVagasEmpresa))
                    .addGroup(pnlListaVagasEmpresaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlCardVagaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1256, Short.MAX_VALUE))
        );
        pnlListaVagasEmpresaLayout.setVerticalGroup(
            pnlListaVagasEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaVagasEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCardVagaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172)
                .addComponent(lblSemVagasEmpresa)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        scrollVagasEmpresa.setViewportView(pnlListaVagasEmpresa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollVagasEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSubtituloVagasEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(btnPublicarNovaVaga))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTituloVagasEmpresa)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloVagasEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSubtituloVagasEmpresa)
                        .addGap(30, 30, 30)
                        .addComponent(scrollVagasEmpresa))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPublicarNovaVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPublicarNovaVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicarNovaVagaActionPerformed
            if (acaoPublicarVaga != null) {
        acaoPublicarVaga.run();
    }
    }//GEN-LAST:event_btnPublicarNovaVagaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarVaga;
    private javax.swing.JButton btnEncerrarVaga;
    private javax.swing.JButton btnPublicarNovaVaga;
    private javax.swing.JLabel lblLocalizacaoVagaEmpresa;
    private javax.swing.JLabel lblSalarioVagaEmpresa;
    private javax.swing.JLabel lblSemVagasEmpresa;
    private javax.swing.JLabel lblStatusVagaEmpresa;
    private javax.swing.JLabel lblSubtituloVagasEmpresa;
    private javax.swing.JLabel lblTituloVagaEmpresa;
    private javax.swing.JLabel lblTituloVagasEmpresa;
    private javax.swing.JPanel pnlCardVagaEmpresa;
    private javax.swing.JPanel pnlListaVagasEmpresa;
    private javax.swing.JScrollPane scrollVagasEmpresa;
    // End of variables declaration//GEN-END:variables
}
