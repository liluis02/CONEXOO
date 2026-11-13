/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class Vagas_Perfilprofissional1 extends javax.swing.JPanel {
    private java.util.function.IntConsumer acaoVerDetalhes;

public void setAcaoVerDetalhes(
        java.util.function.IntConsumer acao) {
    this.acaoVerDetalhes = acao;
}

    /**
     * Creates new form Vagas_Perfilprofissional1
     */
    public Vagas_Perfilprofissional1() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        pnlListaVagas.setLayout(
    new javax.swing.BoxLayout(
        pnlListaVagas,
        javax.swing.BoxLayout.Y_AXIS
    )
);
        carregarVagas();
    }
    
    private void carregarVagas() {
            pnlListaVagas.removeAll();
            
            String sql = "SELECT v.id_vaga, v.titulo, "
            + "v.localizacao, v.salario_min, v.salario_max, "
            + "e.nome_fantasia "
            + "FROM vagas v "
            + "INNER JOIN empresas e ON v.id_empresa = e.id_empresa "
            + "WHERE v.status = 'ATIVA'";

            try {
        java.sql.Connection conexao =
                java.sql.DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/conexoo",
                        "root",
                        "123456"
                );

        java.sql.PreparedStatement pst =
                conexao.prepareStatement(sql);

        java.sql.ResultSet rs = pst.executeQuery();
        while (rs.next()) {

    int idVaga = rs.getInt("id_vaga");
    String titulo = rs.getString("titulo");
    String empresa = rs.getString("nome_fantasia");
    String localizacao = rs.getString("localizacao");

    java.math.BigDecimal salarioMin = rs.getBigDecimal("salario_min");
    java.math.BigDecimal salarioMax = rs.getBigDecimal("salario_max");
    
    criarCardVaga(
        idVaga,
        titulo,
        empresa,
        localizacao,
        salarioMin,
        salarioMax
);

}

        rs.close();
        pst.close();
        conexao.close();

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar vagas: " + e.getMessage()
        );
    }
    
            pnlListaVagas.revalidate();
pnlListaVagas.repaint();
    }
    private void pesquisarVagas(String pesquisa) {

    pnlListaVagas.removeAll();
    pnlListaVagas.setLayout(
    new javax.swing.BoxLayout(
        pnlListaVagas,
        javax.swing.BoxLayout.Y_AXIS
    )
);

    String sql = "SELECT v.id_vaga, v.titulo, "
            + "v.localizacao, v.salario_min, v.salario_max, "
            + "e.nome_fantasia "
            + "FROM vagas v "
            + "INNER JOIN empresas e ON v.id_empresa = e.id_empresa "
            + "WHERE v.status = 'ATIVA' "
            + "AND (v.titulo LIKE ? "
            + "OR e.nome_fantasia LIKE ? "
            + "OR v.localizacao LIKE ?)";

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {

        String termo = "%" + pesquisa + "%";

        stmt.setString(1, termo);
        stmt.setString(2, termo);
        stmt.setString(3, termo);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

            boolean encontrouVagas = false;

            while (rs.next()) {

                encontrouVagas = true;

                criarCardVaga(
                    rs.getInt("id_vaga"),
                    rs.getString("titulo"),
                    rs.getString("nome_fantasia"),
                    rs.getString("localizacao"),
                    rs.getBigDecimal("salario_min"),
                    rs.getBigDecimal("salario_max")
                );
            }

            if (!encontrouVagas) {
                mostrarMensagemSemVagas();
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao pesquisar vagas: " + e.getMessage()
        );
    }

    pnlListaVagas.revalidate();
    pnlListaVagas.repaint();
}
    private void criarCardVaga( 
        int idVaga,
        String titulo,
        String empresa,
        String localizacao,
        java.math.BigDecimal salarioMin,
        java.math.BigDecimal salarioMax) {

    javax.swing.JPanel card = new javax.swing.JPanel();

    card.setBackground(new java.awt.Color(16, 39, 71));
    card.setBorder(
    javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(56, 189, 248), 2
        ),
        javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15)
    )
);
    card.setLayout(new java.awt.BorderLayout(15, 10));


    javax.swing.JLabel lblTitulo = new javax.swing.JLabel(titulo);
    lblTitulo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
    lblTitulo.setForeground(java.awt.Color.WHITE);

    
    javax.swing.JLabel lblEmpresa = new javax.swing.JLabel(
    empresa + " | " + localizacao
);

lblEmpresa.setForeground(java.awt.Color.WHITE);

java.text.NumberFormat formato =
    java.text.NumberFormat.getCurrencyInstance(
        new java.util.Locale("pt", "BR")
    );

String salarioTexto = "Salário: "
    + (salarioMin != null
        ? formato.format(salarioMin)
        : "Não informado")
    + " até "
    + (salarioMax != null
        ? formato.format(salarioMax)
        : "Não informado");

javax.swing.JLabel lblSalario = new javax.swing.JLabel(salarioTexto);

lblSalario.setForeground(java.awt.Color.WHITE);

javax.swing.JButton btnVerDetalhes =
        new javax.swing.JButton("Ver Detalhes");

btnVerDetalhes.setBackground(new java.awt.Color(6, 182, 212));
btnVerDetalhes.setForeground(java.awt.Color.BLACK);
btnVerDetalhes.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
);
btnVerDetalhes.setFocusPainted(false);
btnVerDetalhes.setCursor(
        new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
);
btnVerDetalhes.addActionListener(e -> {
    if (acaoVerDetalhes != null) {
    acaoVerDetalhes.accept(idVaga);
}
});

javax.swing.JPanel painelInferior = new javax.swing.JPanel(
        new java.awt.BorderLayout(15, 0)
);

painelInferior.setOpaque(false);

painelInferior.add(lblSalario, java.awt.BorderLayout.CENTER);
painelInferior.add(btnVerDetalhes, java.awt.BorderLayout.EAST);

card.add(lblTitulo, java.awt.BorderLayout.NORTH);
card.add(lblEmpresa, java.awt.BorderLayout.CENTER);
card.add(painelInferior, java.awt.BorderLayout.SOUTH);

    pnlListaVagas.add(card);
}
    private void mostrarMensagemSemVagas() {

    pnlListaVagas.removeAll();

    javax.swing.JLabel lblMensagem = new javax.swing.JLabel(
        "Nenhuma vaga encontrada. Tente pesquisar por outros termos."
    );

    lblMensagem.setForeground(java.awt.Color.WHITE);
    lblMensagem.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16)
    );

    lblMensagem.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    pnlListaVagas.setLayout(new java.awt.BorderLayout());
    pnlListaVagas.add(lblMensagem, java.awt.BorderLayout.CENTER);

    pnlListaVagas.revalidate();
    pnlListaVagas.repaint();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlVagas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlListaVagas = new javax.swing.JPanel();
        pnlCardVaga = new javax.swing.JPanel();
        lblTituloVaga = new javax.swing.JLabel();
        lblEmpresaVaga = new javax.swing.JLabel();
        lblLocalizacaoVaga = new javax.swing.JLabel();
        lblSalarioVaga = new javax.swing.JLabel();
        btnVerDetalhesVaga = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPesquisarVagas = new javax.swing.JTextField();
        btnPesquisarVagas = new javax.swing.JButton();

        pnlVagas.setBackground(new java.awt.Color(7, 21, 45));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlListaVagas.setBackground(new java.awt.Color(7, 21, 45));
        pnlListaVagas.setLayout(new javax.swing.BoxLayout(pnlListaVagas, javax.swing.BoxLayout.LINE_AXIS));

        pnlCardVaga.setBackground(new java.awt.Color(16, 39, 71));

        lblTituloVaga.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloVaga.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloVaga.setText("Desenvolvedor Java");

        lblEmpresaVaga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmpresaVaga.setForeground(new java.awt.Color(182, 197, 216));
        lblEmpresaVaga.setText("TechVision");

        lblLocalizacaoVaga.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLocalizacaoVaga.setForeground(new java.awt.Color(182, 197, 216));
        lblLocalizacaoVaga.setText("Belo Horizonte - MG");

        lblSalarioVaga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSalarioVaga.setForeground(new java.awt.Color(0, 207, 232));
        lblSalarioVaga.setText("R$ 4.000,00 - R$ 6.000,00");

        btnVerDetalhesVaga.setBackground(new java.awt.Color(0, 207, 232));
        btnVerDetalhesVaga.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnVerDetalhesVaga.setForeground(new java.awt.Color(7, 21, 45));
        btnVerDetalhesVaga.setText("Ver detalhes");
        btnVerDetalhesVaga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerDetalhesVaga.setFocusPainted(false);

        javax.swing.GroupLayout pnlCardVagaLayout = new javax.swing.GroupLayout(pnlCardVaga);
        pnlCardVaga.setLayout(pnlCardVagaLayout);
        pnlCardVagaLayout.setHorizontalGroup(
            pnlCardVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardVagaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCardVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloVaga)
                    .addComponent(lblEmpresaVaga)
                    .addComponent(lblLocalizacaoVaga)
                    .addGroup(pnlCardVagaLayout.createSequentialGroup()
                        .addComponent(lblSalarioVaga)
                        .addGap(495, 495, 495)
                        .addComponent(btnVerDetalhesVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(541, Short.MAX_VALUE))
        );
        pnlCardVagaLayout.setVerticalGroup(
            pnlCardVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardVagaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloVaga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmpresaVaga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLocalizacaoVaga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCardVagaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalarioVaga)
                    .addComponent(btnVerDetalhesVaga))
                .addContainerGap(604, Short.MAX_VALUE))
        );

        pnlListaVagas.add(pnlCardVaga);

        jScrollPane1.setViewportView(pnlListaVagas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Vagas disponíveis");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(182, 197, 216));
        jLabel2.setText("Encontre oportunidades que combinam com seu perfil profissional.");

        txtPesquisarVagas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPesquisarVagas.setForeground(new java.awt.Color(7, 21, 45));

        btnPesquisarVagas.setBackground(new java.awt.Color(0, 207, 232));
        btnPesquisarVagas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPesquisarVagas.setForeground(new java.awt.Color(7, 21, 45));
        btnPesquisarVagas.setText("Pesquisar");
        btnPesquisarVagas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesquisarVagas.addActionListener(this::btnPesquisarVagasActionPerformed);

        javax.swing.GroupLayout pnlVagasLayout = new javax.swing.GroupLayout(pnlVagas);
        pnlVagas.setLayout(pnlVagasLayout);
        pnlVagasLayout.setHorizontalGroup(
            pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlVagasLayout.createSequentialGroup()
                        .addGroup(pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(pnlVagasLayout.createSequentialGroup()
                                .addComponent(txtPesquisarVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisarVagas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(629, 629, 629))
        );
        pnlVagasLayout.setVerticalGroup(
            pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addGroup(pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarVagas))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarVagasActionPerformed
        String pesquisa = txtPesquisarVagas.getText().trim();

pesquisarVagas(pesquisa);
    }//GEN-LAST:event_btnPesquisarVagasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisarVagas;
    private javax.swing.JButton btnVerDetalhesVaga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmpresaVaga;
    private javax.swing.JLabel lblLocalizacaoVaga;
    private javax.swing.JLabel lblSalarioVaga;
    private javax.swing.JLabel lblTituloVaga;
    private javax.swing.JPanel pnlCardVaga;
    private javax.swing.JPanel pnlListaVagas;
    private javax.swing.JPanel pnlVagas;
    private javax.swing.JTextField txtPesquisarVagas;
    // End of variables declaration//GEN-END:variables
}
