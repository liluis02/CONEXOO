/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class Matchsprofissional extends javax.swing.JPanel {
private int idUsuario;

private java.util.function.IntConsumer acaoVerPerfilEmpresa;

public void setAcaoVerPerfilEmpresa(
        java.util.function.IntConsumer acao) {
    this.acaoVerPerfilEmpresa = acao;
}




public Matchsprofissional() {
    
    initComponents();

    pnlListaMatches.setLayout(
        new javax.swing.BoxLayout(
            pnlListaMatches,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    pnlListaMatches.setBackground(new java.awt.Color(7, 21, 45));

    jScrollPane1.getViewport().setBackground(
        new java.awt.Color(7, 21, 45)
    );

    jScrollPane1.setHorizontalScrollBarPolicy(
        javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
    

pnlListaMatches.revalidate();
pnlListaMatches.repaint();
}

public Matchsprofissional(int idUsuario) {

    this();

    this.idUsuario = idUsuario;

    carregarQuantidadeCurtidas();
    carregarQuantidadeMatches();
    carregarMatches();


}

private void carregarQuantidadeCurtidas() {

    String sql = """
        SELECT COUNT(*) AS total
        FROM interesses i
        INNER JOIN candidatos c
            ON i.id_candidato = c.id_candidato
        WHERE c.id_usuario = ?
          AND i.origem = 'candidato'
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                lblQuantidadecurtidasprof.setText(
                    String.valueOf(rs.getInt("total"))
                );
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar curtidas: " + e.getMessage()
        );
    }
}

private void carregarQuantidadeMatches() {

    String sql = """
        SELECT COUNT(*) AS total
        FROM interesses candidato
        INNER JOIN interesses empresa
            ON candidato.id_candidato = empresa.id_candidato
           AND candidato.id_vaga = empresa.id_vaga
        INNER JOIN candidatos c
            ON candidato.id_candidato = c.id_candidato
        WHERE c.id_usuario = ?
          AND candidato.origem = 'candidato'
          AND empresa.origem = 'empresa'
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

if (rs.next()) {

    int totalMatches = rs.getInt("total");

    lblQuantidadematchesprof.setText(
        String.valueOf(totalMatches)
    );

    if (totalMatches == 0) {
        mostrarMensagemSemMatches();
    }
}
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar matches: " + e.getMessage()
        );
    }
}


private void mostrarMensagemSemMatches() {

    pnlListaMatches.removeAll();

    javax.swing.JLabel lblMensagem = new javax.swing.JLabel(
        "<html><div style='text-align:center;'>"
        + "<h2>Sua próxima conexão está a caminho!</h2>"
        + "Ainda não rolou um match por aqui.<br><br>"
        + "Explore novas vagas, demonstre seu interesse<br>"
        + "e encontre empresas que combinam com você."
        + "</div></html>"
    );

    lblMensagem.setFont(new java.awt.Font(
        "Segoe UI", java.awt.Font.PLAIN, 15
    ));

    lblMensagem.setForeground(new java.awt.Color(168, 201, 235));
    lblMensagem.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    lblMensagem.setAlignmentX(
        javax.swing.JComponent.CENTER_ALIGNMENT
    );

    pnlListaMatches.add(
        javax.swing.Box.createVerticalStrut(70)
    );

    pnlListaMatches.add(lblMensagem);

    pnlListaMatches.revalidate();
    pnlListaMatches.repaint();
}

private void carregarMatches() {

    String sql = """
        SELECT
            e.id_empresa,
            COALESCE(
                NULLIF(e.nome_fantasia, ''),
                e.razao_social,
                'Empresa sem nome'
            ) AS nome_empresa,
            CONCAT_WS(' - ', e.cidade, e.estado) AS localizacao,
            v.titulo AS vaga,
            DATE_FORMAT(
                GREATEST(ic.data_interesse, ie.data_interesse),
                '%d/%m/%Y'
            ) AS data_match
        FROM interesses ic
        INNER JOIN interesses ie
            ON ic.id_candidato = ie.id_candidato
           AND ic.id_vaga = ie.id_vaga
        INNER JOIN candidatos c
            ON ic.id_candidato = c.id_candidato
        INNER JOIN vagas v
            ON ic.id_vaga = v.id_vaga
        INNER JOIN empresas e
            ON v.id_empresa = e.id_empresa
        WHERE c.id_usuario = ?
          AND ic.origem = 'candidato'
          AND ie.origem = 'empresa'
        ORDER BY GREATEST(
            ic.data_interesse, ie.data_interesse
        ) DESC
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

            pnlListaMatches.removeAll();

            boolean encontrouMatch = false;

            while (rs.next()) {

                encontrouMatch = true;

                criarCardMatch(
                    rs.getInt("id_empresa"),
                    rs.getString("nome_empresa"),
                    rs.getString("localizacao"),
                    rs.getString("vaga"),
                    rs.getString("data_match")
                );

                pnlListaMatches.add(
                    javax.swing.Box.createVerticalStrut(10)
                );
            }

            if (!encontrouMatch) {
                mostrarMensagemSemMatches();
            }

            pnlListaMatches.revalidate();
            pnlListaMatches.repaint();
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar matches: " + e.getMessage()
        );
    }
}

private void criarCardMatch(
        int idEmpresa,
        String nomeEmpresa,
        String localizacao,
        String vaga,
        String dataMatch) {

    java.awt.Color fundo = new java.awt.Color(16, 39, 71);
    java.awt.Color ciano = new java.awt.Color(6, 182, 212);
    java.awt.Color branco = java.awt.Color.WHITE;

    javax.swing.JPanel card = new javax.swing.JPanel(
            new java.awt.BorderLayout(20, 0)
    );

    card.setBackground(fundo);
    card.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(ciano, 2),
                    javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20)
            )
    );

    card.setPreferredSize(new java.awt.Dimension(780, 155));
    card.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 155));
    card.setMinimumSize(new java.awt.Dimension(0, 115));
    card.setAlignmentX(javax.swing.JComponent.LEFT_ALIGNMENT);

    // Informações da empresa
    javax.swing.JPanel painelEmpresa = new javax.swing.JPanel();
    painelEmpresa.setOpaque(false);
    painelEmpresa.setLayout(new javax.swing.BoxLayout(
            painelEmpresa, javax.swing.BoxLayout.Y_AXIS
    ));

    javax.swing.JLabel lblEmpresa = new javax.swing.JLabel(nomeEmpresa);
    lblEmpresa.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
    lblEmpresa.setForeground(branco);

    javax.swing.JLabel lblLocal = new javax.swing.JLabel(localizacao);
    lblLocal.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    lblLocal.setForeground(new java.awt.Color(182, 197, 216));

    painelEmpresa.add(lblEmpresa);
    painelEmpresa.add(javax.swing.Box.createVerticalStrut(12));
    painelEmpresa.add(lblLocal);

    // Informações do Match
    javax.swing.JPanel painelMatch = new javax.swing.JPanel();
    painelMatch.setOpaque(false);
    painelMatch.setLayout(new javax.swing.BoxLayout(
            painelMatch, javax.swing.BoxLayout.Y_AXIS
    ));

    javax.swing.JLabel lblVaga = new javax.swing.JLabel(
            "Vaga de interesse: " + vaga
    );
    lblVaga.setForeground(branco);
    lblVaga.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

    javax.swing.JLabel lblData = new javax.swing.JLabel(
            "Match em: " + dataMatch
    );
    lblData.setForeground(new java.awt.Color(182, 197, 216));

    painelMatch.add(lblVaga);
    painelMatch.add(javax.swing.Box.createVerticalStrut(12));
    painelMatch.add(lblData);

    // Botões
    javax.swing.JButton btnContato =
            new javax.swing.JButton("Entrar em contato");

    javax.swing.JButton btnDetalhes =
        new javax.swing.JButton("Ver perfil da empresa");
    
    btnDetalhes.addActionListener(e -> {
    if (acaoVerPerfilEmpresa != null) {
        acaoVerPerfilEmpresa.accept(idEmpresa);
    }
});

    btnContato.setBackground(new java.awt.Color(46, 184, 125));
btnContato.setForeground(java.awt.Color.WHITE);

    btnDetalhes.setBackground(fundo);
    btnDetalhes.setForeground(branco);
    btnDetalhes.setBorder(
            javax.swing.BorderFactory.createLineBorder(ciano, 1)
    );

    for (javax.swing.JButton botao :
            new javax.swing.JButton[]{btnContato, btnDetalhes}) {

        botao.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        botao.setFocusPainted(false);
        botao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    javax.swing.JPanel painelBotoes = new javax.swing.JPanel(
            new java.awt.GridLayout(2, 1, 0, 5)
    );
    painelBotoes.setOpaque(false);
    painelBotoes.setPreferredSize(new java.awt.Dimension(190, 0));

    painelBotoes.add(btnContato);
    painelBotoes.add(btnDetalhes);

    // Organização do cartão
javax.swing.JPanel painelInformacoes = new javax.swing.JPanel(
        new java.awt.BorderLayout(15, 0)
);

painelInformacoes.setOpaque(false);

painelEmpresa.setPreferredSize(new java.awt.Dimension(190, 0));

painelInformacoes.add(painelEmpresa, java.awt.BorderLayout.WEST);
painelInformacoes.add(painelMatch, java.awt.BorderLayout.CENTER);

    card.add(painelInformacoes, java.awt.BorderLayout.CENTER);
    card.add(painelBotoes, java.awt.BorderLayout.EAST);

    pnlListaMatches.add(card);
}

public void atualizarDados() {
    carregarQuantidadeCurtidas();
    carregarQuantidadeMatches();
    carregarMatches();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtitulomatchesprof = new javax.swing.JLabel();
        jsubtitulomatchesprof = new javax.swing.JLabel();
        pnlVagasRecomendadas1 = new javax.swing.JPanel();
        lblQuantidadecurtidasprof = new javax.swing.JLabel();
        lblcurtidasprof = new javax.swing.JLabel();
        pnlVagasRecomendadas2 = new javax.swing.JPanel();
        lblQuantidadematchesprof = new javax.swing.JLabel();
        lblmacthesprof = new javax.swing.JLabel();
        jtitulomatchesprof1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlListaMatches = new javax.swing.JPanel();

        setBackground(new java.awt.Color(7, 21, 45));

        jtitulomatchesprof.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jtitulomatchesprof.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof.setText("Empresas com matche");

        jsubtitulomatchesprof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jsubtitulomatchesprof.setForeground(new java.awt.Color(168, 201, 235));
        jsubtitulomatchesprof.setText("Avançe novas etapas com seus matches");

        pnlVagasRecomendadas1.setBackground(new java.awt.Color(11, 37, 69));
        pnlVagasRecomendadas1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVagasRecomendadas1.setPreferredSize(new java.awt.Dimension(209, 110));

        lblQuantidadecurtidasprof.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadecurtidasprof.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadecurtidasprof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadecurtidasprof.setText("4");

        lblcurtidasprof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblcurtidasprof.setForeground(new java.awt.Color(168, 201, 235));
        lblcurtidasprof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcurtidasprof.setText("Vaga(s) curtida(s)");

        javax.swing.GroupLayout pnlVagasRecomendadas1Layout = new javax.swing.GroupLayout(pnlVagasRecomendadas1);
        pnlVagasRecomendadas1.setLayout(pnlVagasRecomendadas1Layout);
        pnlVagasRecomendadas1Layout.setHorizontalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addGroup(pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas1Layout.createSequentialGroup()
                        .addComponent(lblQuantidadecurtidasprof)
                        .addGap(175, 175, 175))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas1Layout.createSequentialGroup()
                        .addComponent(lblcurtidasprof)
                        .addGap(122, 122, 122))))
        );
        pnlVagasRecomendadas1Layout.setVerticalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lblQuantidadecurtidasprof)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblcurtidasprof)
                .addGap(12, 12, 12))
        );

        pnlVagasRecomendadas2.setBackground(new java.awt.Color(11, 37, 69));
        pnlVagasRecomendadas2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVagasRecomendadas2.setPreferredSize(new java.awt.Dimension(209, 110));

        lblQuantidadematchesprof.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadematchesprof.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadematchesprof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadematchesprof.setText("4");

        lblmacthesprof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblmacthesprof.setForeground(new java.awt.Color(168, 201, 235));
        lblmacthesprof.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmacthesprof.setText("Matches");

        javax.swing.GroupLayout pnlVagasRecomendadas2Layout = new javax.swing.GroupLayout(pnlVagasRecomendadas2);
        pnlVagasRecomendadas2.setLayout(pnlVagasRecomendadas2Layout);
        pnlVagasRecomendadas2Layout.setHorizontalGroup(
            pnlVagasRecomendadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas2Layout.createSequentialGroup()
                .addContainerGap(185, Short.MAX_VALUE)
                .addGroup(pnlVagasRecomendadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas2Layout.createSequentialGroup()
                        .addComponent(lblmacthesprof)
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas2Layout.createSequentialGroup()
                        .addComponent(lblQuantidadematchesprof)
                        .addGap(170, 170, 170))))
        );
        pnlVagasRecomendadas2Layout.setVerticalGroup(
            pnlVagasRecomendadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblQuantidadematchesprof)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblmacthesprof)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jtitulomatchesprof1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jtitulomatchesprof1.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof1.setText("Meus Matches");

        jScrollPane1.setBackground(new java.awt.Color(16, 39, 71));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlListaMatches.setBackground(new java.awt.Color(16, 39, 71));

        javax.swing.GroupLayout pnlListaMatchesLayout = new javax.swing.GroupLayout(pnlListaMatches);
        pnlListaMatches.setLayout(pnlListaMatchesLayout);
        pnlListaMatchesLayout.setHorizontalGroup(
            pnlListaMatchesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        pnlListaMatchesLayout.setVerticalGroup(
            pnlListaMatchesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlListaMatches);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsubtitulomatchesprof, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtitulomatchesprof1)
                            .addComponent(jtitulomatchesprof)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlVagasRecomendadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(0, 585, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jtitulomatchesprof1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jsubtitulomatchesprof)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlVagasRecomendadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtitulomatchesprof)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jsubtitulomatchesprof;
    private javax.swing.JLabel jtitulomatchesprof;
    private javax.swing.JLabel jtitulomatchesprof1;
    private javax.swing.JLabel lblQuantidadecurtidasprof;
    private javax.swing.JLabel lblQuantidadematchesprof;
    private javax.swing.JLabel lblcurtidasprof;
    private javax.swing.JLabel lblmacthesprof;
    private javax.swing.JPanel pnlListaMatches;
    private javax.swing.JPanel pnlVagasRecomendadas1;
    private javax.swing.JPanel pnlVagasRecomendadas2;
    // End of variables declaration//GEN-END:variables
}
