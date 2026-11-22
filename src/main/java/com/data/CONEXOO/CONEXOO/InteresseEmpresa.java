/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class InteresseEmpresa extends javax.swing.JPanel {
private int idUsuarioEmpresa;

    private java.util.function.IntConsumer acaoVerPerfil;
    
    public InteresseEmpresa() {
        initComponents();
        
    pnlListaProfissionais.setLayout(
        new javax.swing.BoxLayout(
            pnlListaProfissionais,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    pnlListaProfissionais.setBackground(
        new java.awt.Color(7, 21, 45)
    );

    scrollProfissionais.setHorizontalScrollBarPolicy(
        javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollProfissionais.getVerticalScrollBar()
        .setUnitIncrement(20);
    
    mostrarMensagemInicial();
    }
    
    public void atualizarProfissionais() {

    Object item = cmbAreaAtuacao.getSelectedItem();

    if (item == null || cmbAreaAtuacao.getSelectedIndex() <= 0) {
        mostrarMensagemInicial();
        return;
    }

    String area = item.toString();

    buscarProfissionais(area);
}

private boolean curtirProfissional(int idUsuarioProfissional, int idVaga) {

    String sqlCurtida = """
        INSERT INTO interesses
            (id_candidato, id_vaga, origem, data_interesse)
        SELECT c.id_candidato, v.id_vaga, 'empresa', NOW()
        FROM candidatos c
        INNER JOIN vagas v ON v.id_vaga = ?
        INNER JOIN empresas e ON v.id_empresa = e.id_empresa
        WHERE c.id_usuario = ?
          AND e.id_usuario = ?
          AND v.status = 'ATIVA'
        RETURNING id_candidato
        """;

    String sqlMatch = """
        INSERT INTO matches (id_candidato, id_vaga)
        SELECT ?, ?
        WHERE EXISTS (
            SELECT 1
            FROM interesses
            WHERE id_candidato = ?
              AND id_vaga = ?
              AND origem = 'candidato'
        )
        ON CONFLICT (id_candidato, id_vaga) DO NOTHING
        """;

    try (java.sql.Connection conexao = Conexao.conectar()) {

        conexao.setAutoCommit(false);

        try {
            int idCandidato;

            try (java.sql.PreparedStatement stmt =
                    conexao.prepareStatement(sqlCurtida)) {

                stmt.setInt(1, idVaga);
                stmt.setInt(2, idUsuarioProfissional);
                stmt.setInt(3, idUsuarioEmpresa);

                try (java.sql.ResultSet rs = stmt.executeQuery()) {

                    if (!rs.next()) {
                        conexao.rollback();

                        javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Não foi possível registrar a curtida. "
                            + "Verifique o candidato e a vaga ativa."
                        );

                        return false;
                    }

                    idCandidato = rs.getInt("id_candidato");
                }
            }

            int matchesCriados;

            try (java.sql.PreparedStatement stmt =
                    conexao.prepareStatement(sqlMatch)) {

                stmt.setInt(1, idCandidato);
                stmt.setInt(2, idVaga);
                stmt.setInt(3, idCandidato);
                stmt.setInt(4, idVaga);

                matchesCriados = stmt.executeUpdate();
            }

            conexao.commit();

            if (matchesCriados > 0) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "É um Match! Vocês demonstraram interesse mútuo!"
                );
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Profissional curtido com sucesso!"
                );
            }

            return true;

        } catch (java.sql.SQLException e) {

            conexao.rollback();
            throw e;
        }

    } catch (java.sql.SQLException e) {

        if ("23505".equals(e.getSQLState())) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Você já curtiu este profissional para essa vaga."
            );
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao curtir profissional: " + e.getMessage()
            );
        }

        return false;
    }
}


    
    
    private java.util.Map<String, Integer> buscarVagasDaEmpresa() {

    java.util.Map<String, Integer> vagas =
            new java.util.LinkedHashMap<>();

    String sql = """
        SELECT v.id_vaga, v.titulo
        FROM vagas v
        INNER JOIN empresas e
            ON v.id_empresa = e.id_empresa
        WHERE e.id_usuario = ?
          AND v.status = 'ATIVA'
        ORDER BY v.titulo
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuarioEmpresa);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int idVaga = rs.getInt("id_vaga");
                String titulo = rs.getString("titulo");

                vagas.put(titulo, idVaga);
            }
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao buscar vagas da empresa: " + e.getMessage()
        );
    }

    return vagas;
}
    
    
    public InteresseEmpresa(int idUsuarioEmpresa) {
    this();
    this.idUsuarioEmpresa = idUsuarioEmpresa;
}
    
    
    public void setAcaoVerPerfil(
        java.util.function.IntConsumer acao) {

    this.acaoVerPerfil = acao;
}
    
    private void criarCardProfissional(
        int idUsuario,
        String nome,
        String cargo,
        String nivel,
        String cidade,
        String estado) {

    // Painel principal do card
    javax.swing.JPanel card = new javax.swing.JPanel();
    card.setBackground(new java.awt.Color(16, 39, 71));
    card.setLayout(new java.awt.BorderLayout(20, 10));

card.setBorder(
    javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(0, 207, 232), 2
        ),
        javax.swing.BorderFactory.createEmptyBorder(
            15, 20, 15, 20
        )
    )
);

    // Nome do profissional
    javax.swing.JLabel lblNome =
            new javax.swing.JLabel(nome);

    lblNome.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18)
    );
    lblNome.setForeground(java.awt.Color.WHITE);

    // Cargo e nível profissional
    String cargoTexto = cargo == null || cargo.isBlank()
            ? "Cargo não informado" : cargo;

    String nivelTexto = nivel == null || nivel.isBlank()
            ? "Nível não informado" : nivel;

    javax.swing.JLabel lblCargo = new javax.swing.JLabel(
        cargoTexto + " | " + nivelTexto
    );

    lblCargo.setForeground(new java.awt.Color(182, 197, 216));

    // Localização
    String cidadeTexto = cidade == null ? "" : cidade;
    String estadoTexto = estado == null ? "" : estado;

    javax.swing.JLabel lblLocalizacao = new javax.swing.JLabel(
        cidadeTexto + " - " + estadoTexto
    );

    lblLocalizacao.setForeground(
        new java.awt.Color(182, 197, 216)
    );

    // Painel com as informações
    javax.swing.JPanel painelInformacoes =
            new javax.swing.JPanel();

    painelInformacoes.setOpaque(false);
    painelInformacoes.setLayout(
        new javax.swing.BoxLayout(
            painelInformacoes,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    painelInformacoes.add(lblNome);
    painelInformacoes.add(
        javax.swing.Box.createVerticalStrut(5)
    );
    painelInformacoes.add(lblCargo);
    painelInformacoes.add(
        javax.swing.Box.createVerticalStrut(5)
    );
    painelInformacoes.add(lblLocalizacao);

    // Botão Curtir
    javax.swing.JButton btnCurtir =
            new javax.swing.JButton("♡ Curtir");

    btnCurtir.setBackground(new java.awt.Color(16, 39, 71));
    btnCurtir.setForeground(java.awt.Color.WHITE);
    btnCurtir.setFocusPainted(false);
    btnCurtir.setCursor(
        new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
    );
    btnCurtir.setBorder(
        javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(0, 207, 232), 2
        )
    );
    btnCurtir.setPreferredSize(
        new java.awt.Dimension(110, 38)
    );
    
    if (profissionalJaCurtido(idUsuario)) {
    configurarBotaoCurtido(btnCurtir);
}
    // Botão Ver Perfil
    javax.swing.JButton btnVerPerfil =
            new javax.swing.JButton("Ver Perfil");

    btnVerPerfil.setBackground(new java.awt.Color(0, 207, 232));
    btnVerPerfil.setPreferredSize(
    new java.awt.Dimension(110, 38)
);
    
    btnVerPerfil.setForeground(new java.awt.Color(7, 21, 45));
    btnVerPerfil.setFont(
        new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)
    );
    btnVerPerfil.setFocusPainted(false);
    btnVerPerfil.setCursor(
        new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
    );

btnCurtir.addActionListener(e -> {
    
    if (profissionalJaCurtido(idUsuario)) {
    return;
}

    java.util.Map<String, Integer> vagas = buscarVagasDaEmpresa();

    if (vagas.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Você precisa ter uma vaga ativa para curtir um profissional!"
        );
        return;
    }

    CurtirProfissional_Panel painel = new CurtirProfissional_Panel();

    painel.definirProfissional(nome);
    painel.carregarVagas(vagas);

    javax.swing.JDialog janela = new javax.swing.JDialog(
        javax.swing.SwingUtilities.getWindowAncestor(this),
        "Curtir profissional",
        java.awt.Dialog.ModalityType.APPLICATION_MODAL
    );

janela.setContentPane(painel);
janela.pack();
janela.setResizable(false);
janela.setLocationRelativeTo(
    javax.swing.SwingUtilities.getWindowAncestor(this)
);

    
painel.setAcaoConfirmarCurtida(idVaga -> {

    boolean sucesso = curtirProfissional(idUsuario, idVaga);

    if (sucesso) {
    configurarBotaoCurtido(btnCurtir);
}

    janela.dispose();
});

    janela.setVisible(true);
});

btnVerPerfil.addActionListener(e -> {

    if (acaoVerPerfil != null) {
        acaoVerPerfil.accept(idUsuario);
    }

});

    // Painel dos botões
    javax.swing.JPanel painelBotoes =
            new javax.swing.JPanel(
                new java.awt.FlowLayout(
                    java.awt.FlowLayout.RIGHT, 15, 0
                )
            );

    painelBotoes.setOpaque(false);
    painelBotoes.add(btnCurtir);
    painelBotoes.add(btnVerPerfil);

    // Montagem do card
    card.add(
        painelInformacoes,
        java.awt.BorderLayout.CENTER
    );
    card.add(
        painelBotoes,
        java.awt.BorderLayout.EAST
    );

    // Tamanho do card
    card.setPreferredSize(
        new java.awt.Dimension(900, 115)
    );
    card.setMaximumSize(
        new java.awt.Dimension(Integer.MAX_VALUE, 115)
    );
    card.setMinimumSize(
        new java.awt.Dimension(400, 115)
    );

    // Adicionar à lista
    pnlListaProfissionais.add(card);
}
    
    
private void configurarBotaoCurtido(javax.swing.JButton btnCurtir) {

    btnCurtir.setText("\u2665 Curtido");

    // Mantém habilitado visualmente
    btnCurtir.setEnabled(true);

    // Fundo azul escuro
    btnCurtir.setBackground(
        new java.awt.Color(35, 65, 95)
    );

    // Texto branco
    btnCurtir.setForeground(java.awt.Color.WHITE);

    // Fonte
    btnCurtir.setFont(
        new java.awt.Font("Segoe UI Symbol", java.awt.Font.BOLD, 14)
    );

    // Remove a borda
    btnCurtir.setBorder(
        javax.swing.BorderFactory.createEmptyBorder()
    );

    btnCurtir.setFocusPainted(false);
    btnCurtir.setOpaque(true);
}

    
    
private boolean profissionalJaCurtido(int idUsuarioProfissional) {

    String sql = """
        SELECT 1
        FROM interesses i
        INNER JOIN candidatos c
            ON i.id_candidato = c.id_candidato
        INNER JOIN vagas v
            ON i.id_vaga = v.id_vaga
        INNER JOIN empresas e
            ON v.id_empresa = e.id_empresa
        WHERE c.id_usuario = ?
          AND e.id_usuario = ?
          AND i.origem = 'empresa'
        LIMIT 1
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst =
                 conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuarioProfissional);
        pst.setInt(2, idUsuarioEmpresa);

        try (java.sql.ResultSet rs = pst.executeQuery()) {
            return rs.next();
        }

    } catch (java.sql.SQLException e) {
        System.err.println(
            "Erro ao verificar curtida: " + e.getMessage()
        );
        return false;
    }
}

    
    private void buscarProfissionais(String area) {

    String sql = """
        SELECT
            u.id,
            u.nome,
            u.area_atuacao,
            c.cargo_desejado,
            c.nivel_profissional,
            c.cidade,
            c.estado
        FROM usuarios u
        LEFT JOIN candidatos c
            ON c.id_usuario = u.id
        WHERE u.tipo_perfil = 'PROFISSIONAL'
          AND u.area_atuacao = ?
        ORDER BY u.nome
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst =
                 conexao.prepareStatement(sql)) {

        pst.setString(1, area);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

pnlListaProfissionais.removeAll();

int quantidade = 0;

while (rs.next()) {

    int idUsuario = rs.getInt("id");
    String nome = rs.getString("nome");
    String cargo = rs.getString("cargo_desejado");
    String nivel = rs.getString("nivel_profissional");
    String cidade = rs.getString("cidade");
    String estado = rs.getString("estado");

    criarCardProfissional(
        idUsuario,
        nome,
        cargo,
        nivel,
        cidade,
        estado
    );

    quantidade++;
}

if (quantidade == 0) {

    javax.swing.JLabel lblVazio =
        new javax.swing.JLabel(
            "Nenhum profissional encontrado nesta área."
        );

    lblVazio.setForeground(java.awt.Color.WHITE);

    lblVazio.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(
            20, 15, 20, 15
        )
    );

    pnlListaProfissionais.add(lblVazio);
}

pnlListaProfissionais.revalidate();
pnlListaProfissionais.repaint();
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao buscar profissionais: " + e.getMessage()
        );
    }
}
    
    private void mostrarMensagemInicial() {

    pnlListaProfissionais.removeAll();

    javax.swing.JLabel lblMensagem = new javax.swing.JLabel(
        "<html><div style='text-align:center;'>"
        + "<span style='font-size:20px; color:#00CFE8;'>"
        + "&#9734; Seu próximo talento está a um clique!"
        + "</span><br><br>"
        + "<span style='font-size:13px; color:#B6C5D8;'>"
        + "Selecione uma área de atuação acima e descubra profissionais"
        + "<br>que podem fazer a diferença na sua empresa."
        + "</span>"
        + "</div></html>"
    );

    lblMensagem.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    lblMensagem.setAlignmentX(
        java.awt.Component.CENTER_ALIGNMENT
    );

    lblMensagem.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(
            90, 20, 20, 20
        )
    );

    pnlListaProfissionais.add(lblMensagem);

    pnlListaProfissionais.revalidate();
    pnlListaProfissionais.repaint();
}
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtitulomatchesprof1 = new javax.swing.JLabel();
        jsubtitulomatchesprof = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jtitulomatchesprof2 = new javax.swing.JLabel();
        cmbAreaAtuacao = new javax.swing.JComboBox<>();
        jtitulomatchesprof3 = new javax.swing.JLabel();
        scrollProfissionais = new javax.swing.JScrollPane();
        pnlListaProfissionais = new javax.swing.JPanel();

        setBackground(new java.awt.Color(7, 21, 45));

        jtitulomatchesprof1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jtitulomatchesprof1.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof1.setText("Procura de Profissionais  ");

        jsubtitulomatchesprof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jsubtitulomatchesprof.setForeground(new java.awt.Color(168, 201, 235));
        jsubtitulomatchesprof.setText("Encontre e demonstre interesse em profissionais de TI para suas vagas");

        jPanel1.setBackground(new java.awt.Color(7, 21, 45));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 216, 245)));

        jtitulomatchesprof2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jtitulomatchesprof2.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof2.setText("Área de atuação do profissional:");

        cmbAreaAtuacao.setBackground(new java.awt.Color(7, 21, 45));
        cmbAreaAtuacao.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cmbAreaAtuacao.setForeground(new java.awt.Color(255, 255, 255));
        cmbAreaAtuacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma área de atuação", "Desenvolvimento Front-end", "Desenvolvimento Back-end", "Desenvolvimento Full Stack", "Desenvolvimento Mobile", "Desenvolvimento Desktop", "Desenvolvimento de Jogos", "Engenharia de Software", "Arquitetura de Software", "Qualidade de Software (QA)", "Automação de Testes", "Banco de Dados", "Administração de Banco de Dados (DBA)", "Análise de Dados", "Ciência de Dados", "Engenharia de Dados", "Business Intelligence (BI)", "Inteligência Artificial", "Machine Learning", "Redes de Computadores", "Administração de Sistemas", "Infraestrutura de TI", "Suporte Técnico", "Segurança da Informação", "Cibersegurança", "Segurança Ofensiva (Pentest)", "Computação em Nuvem (Cloud)", "DevOps", "Engenharia de Confiabilidade (SRE)", "Análise de Sistemas", "Gestão de Projetos de TI", "Gestão de Produtos Digitais", "Governança de TI", "Consultoria de TI", "Análise de Negócios", "Sistemas ERP", "Scrum Master", "UX/UI Design", "Internet das Coisas (IoT)", "Robótica e Automação", "Blockchain" }));
        cmbAreaAtuacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        cmbAreaAtuacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbAreaAtuacao.setPreferredSize(new java.awt.Dimension(250, 40));
        cmbAreaAtuacao.addActionListener(this::cmbAreaAtuacaoActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtitulomatchesprof2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAreaAtuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtitulomatchesprof2)
                    .addComponent(cmbAreaAtuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jtitulomatchesprof3.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jtitulomatchesprof3.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof3.setText("Profissionais encontrados");

        scrollProfissionais.setBorder(null);

        pnlListaProfissionais.setBackground(new java.awt.Color(16, 39, 71));

        javax.swing.GroupLayout pnlListaProfissionaisLayout = new javax.swing.GroupLayout(pnlListaProfissionais);
        pnlListaProfissionais.setLayout(pnlListaProfissionaisLayout);
        pnlListaProfissionaisLayout.setHorizontalGroup(
            pnlListaProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        pnlListaProfissionaisLayout.setVerticalGroup(
            pnlListaProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
        );

        scrollProfissionais.setViewportView(pnlListaProfissionais);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsubtitulomatchesprof, javax.swing.GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtitulomatchesprof1)
                            .addComponent(jtitulomatchesprof3)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollProfissionais))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtitulomatchesprof1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsubtitulomatchesprof)
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtitulomatchesprof3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollProfissionais)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbAreaAtuacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAreaAtuacaoActionPerformed
Object item = cmbAreaAtuacao.getSelectedItem();

if (item == null || cmbAreaAtuacao.getSelectedIndex() <= 0) {
    mostrarMensagemInicial();
    return;
}

String area = item.toString();

buscarProfissionais(area);
    }//GEN-LAST:event_cmbAreaAtuacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbAreaAtuacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jsubtitulomatchesprof;
    private javax.swing.JLabel jtitulomatchesprof1;
    private javax.swing.JLabel jtitulomatchesprof2;
    private javax.swing.JLabel jtitulomatchesprof3;
    private javax.swing.JPanel pnlListaProfissionais;
    private javax.swing.JScrollPane scrollProfissionais;
    // End of variables declaration//GEN-END:variables
}
