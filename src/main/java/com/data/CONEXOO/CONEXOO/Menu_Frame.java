/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

public class Menu_Frame extends javax.swing.JFrame {
    
    private void carregarQuantidadeVagas() {
    String sql = "SELECT COUNT(*) FROM vagas WHERE status = 'ATIVA'";

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst = conexao.prepareStatement(sql);
         java.sql.ResultSet rs = pst.executeQuery()) {

        if (rs.next()) {
            int quantidade = rs.getInt(1);
            lblQuantidadeVagas1.setText(String.valueOf(quantidade));
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao contar vagas: " + e.getMessage()
        );
    }
}

    private VisualizarPerfil_Panel painelPerfil;
    private javax.swing.JScrollPane scrollPerfil;
    private VisualizarPerfilEmpresa painelPerfilEmpresa;
    private javax.swing.JScrollPane scrollPerfilEmpresa;
    private Vagas_Perfilprofissional1 painelVagas;
    private javax.swing.JScrollPane scrollVagas;
    private Vagas_PerfilEmpresa painelVagasEmpresa;
    private javax.swing.JScrollPane scrollVagasEmpresa;
    private int idUsuario;
    private String tipoUsuario;

    private void mostrarPainelPerfilEmpresa() {
        if (scrollVagas != null) {
    scrollVagas.setVisible(false);
}

    if (painelPerfilEmpresa == null) {
        painelPerfilEmpresa = new VisualizarPerfilEmpresa(idUsuario);

        scrollPerfilEmpresa = new javax.swing.JScrollPane(painelPerfilEmpresa);
        scrollPerfilEmpresa.setHorizontalScrollBarPolicy(
    javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);
        scrollPerfilEmpresa.getVerticalScrollBar().setUnitIncrement(20);

        pnlPrincipal.add(scrollPerfilEmpresa);
    }

int inicioConteudo = pnlMenuLateral.getX()
        + pnlMenuLateral.getWidth();

scrollPerfilEmpresa.setBounds(
    inicioConteudo,
    0,
    pnlPrincipal.getWidth() - inicioConteudo,
    pnlPrincipal.getHeight()
);

    scrollPerfilEmpresa.setVisible(true);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
    private void mostrarPainelVagas() {
if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {
    mostrarPainelVagasEmpresa();
    return;
}

if (!"PROFISSIONAL".equalsIgnoreCase(tipoUsuario)) {
    return;
}
        
        if (scrollPerfil != null) {
    scrollPerfil.setVisible(false);
}

if (scrollPerfilEmpresa != null) {
    scrollPerfilEmpresa.setVisible(false);
}

if (painelVagas == null) {
    painelVagas = new Vagas_Perfilprofissional1();
    painelVagas.setAcaoVerDetalhes(idVaga -> {
    mostrarDetalhesVaga(idVaga);
});

    scrollVagas = new javax.swing.JScrollPane(painelVagas);

    scrollVagas.setHorizontalScrollBarPolicy(
        javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollVagas.getVerticalScrollBar().setUnitIncrement(20);
    pnlPrincipal.add(scrollVagas);
}

    int inicioConteudo = pnlMenuLateral.getX()
            + pnlMenuLateral.getWidth();

    scrollVagas.setBounds(
        inicioConteudo,
        0,
        pnlPrincipal.getWidth() - inicioConteudo,
        pnlPrincipal.getHeight()
    );

scrollVagas.setVisible(true);
pnlPrincipal.setComponentZOrder(scrollVagas, 0);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
    
    public Menu_Frame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public Menu_Frame(int idUsuario, String tipoUsuario) {
        initComponents();
jScrollPane1.setOpaque(false);
jScrollPane1.getViewport().setOpaque(false);

txtDescricaoBanner.setOpaque(false);
txtDescricaoBanner.setForeground(new java.awt.Color(168, 201, 235));
       
        getContentPane().setBackground(new java.awt.Color(5, 20, 43));
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        
if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {
    lblEmpresasPlataforma.setText("Sobre a empresa");
    carregarDescricaoEmpresa();
} else {
    lblEmpresasPlataforma.setText("Empresas na plataforma");
    txtDescricaoBanner.setText(
        "Descubra oportunidades de trabalho na área de TI"
    );
}

        carregarNomeUsuario();
        carregarQuantidadeVagas();
if ("PROFISSIONAL".equalsIgnoreCase(tipoUsuario)) {

    carregarEmpresasPlataforma();
    carregarResumoProfissional();

} else if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {

    
    lblvagasdisponiveis3.setText("Vagas publicadas");

    
    lblDescricaoVagas3.setText("Vagas ativas");

    
    lblDescricaoVagas2.setText("Candidaturas recebidas");

    
    lblQuantidadeVagas1.setText("0");
    lblQuantidadeVagas3.setText("0");
    lblQuantidadeVagas2.setText("0");
    carregarQuantidadeVagasEmpresa();
    lblEmpresasPlataforma.setText("Minhas vagas");
    lblAtividadesRecentes.setText("Resumo da empresa");
    carregarMinhasVagasInicio();
    carregarResumoEmpresa();
}
    }
    
private void carregarResumoProfissional() {

    pnlListaAtividades.removeAll();

    pnlListaAtividades.setLayout(
        new javax.swing.BoxLayout(
            pnlListaAtividades,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    String sobreMim = "Nenhuma descrição cadastrada.";
    String habilidades = "Nenhuma habilidade cadastrada.";
    String experiencias = "Nenhuma experiência cadastrada.";

    try (java.sql.Connection conexao = Conexao.conectar()) {

        // SOBRE MIM
        String sqlSobre = "SELECT descricao FROM candidatos "
                + "WHERE id_usuario = ?";

        try (java.sql.PreparedStatement pst =
                conexao.prepareStatement(sqlSobre)) {

            pst.setInt(1, idUsuario);

            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String descricao = rs.getString("descricao");

                    if (descricao != null && !descricao.trim().isEmpty()) {
                        sobreMim = descricao.trim();
                    }
                }
            }
        }

        
        String sqlHabilidades = """
            SELECT h.nome, hc.nivel
            FROM habilidades_candidato hc
            INNER JOIN habilidades h
                ON hc.id_habilidade = h.id_habilidade
            INNER JOIN candidatos c
                ON hc.id_candidato = c.id_candidato
            WHERE c.id_usuario = ?
            ORDER BY h.nome
            LIMIT 3
            """;

        try (java.sql.PreparedStatement pst =
                conexao.prepareStatement(sqlHabilidades)) {

            pst.setInt(1, idUsuario);

            try (java.sql.ResultSet rs = pst.executeQuery()) {

                StringBuilder lista = new StringBuilder();

                while (rs.next()) {
                    if (lista.length() > 0) {
                        lista.append(", ");
                    }

                    lista.append(rs.getString("nome"))
                         .append(" — ")
                         .append(rs.getString("nivel"));
                }

                if (lista.length() > 0) {
                    habilidades = lista.toString();
                }
            }
        }
        String sqlExperiencias = """
            SELECT cargo, nome_empresa
            FROM experiencias
            WHERE id_candidato = (
                SELECT id_candidato
                FROM candidatos
                WHERE id_usuario = ?
            )
            ORDER BY data_contratacao DESC
            LIMIT 2
            """;

        try (java.sql.PreparedStatement pst =
                conexao.prepareStatement(sqlExperiencias)) {

            pst.setInt(1, idUsuario);

            try (java.sql.ResultSet rs = pst.executeQuery()) {

                StringBuilder lista = new StringBuilder();

                while (rs.next()) {
                    if (lista.length() > 0) {
                        lista.append(" | ");
                    }

                    lista.append(rs.getString("cargo"))
                         .append(" - ")
                         .append(rs.getString("nome_empresa"));
                }

                if (lista.length() > 0) {
                    experiencias = lista.toString();
                }
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar resumo profissional: " + e.getMessage()
        );
    }

    adicionarCampoResumo("Sobre mim", sobreMim);
    adicionarCampoResumo("Habilidades profissionais", habilidades);
    adicionarCampoResumo("Experiências profissionais", experiencias);

    pnlListaAtividades.revalidate();
    pnlListaAtividades.repaint();
}

private void adicionarCampoResumo(String titulo, String conteudo) {

    javax.swing.JPanel card = new javax.swing.JPanel(
        new java.awt.BorderLayout(0, 5)
    );

    card.setBackground(new java.awt.Color(20, 55, 90));

    card.setBorder(
        javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15)
    );

    javax.swing.JLabel lblTitulo = new javax.swing.JLabel(titulo);

    lblTitulo.setForeground(new java.awt.Color(22, 217, 244));
    lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 15));

    String texto = conteudo.replace("\n", " ").replace("\r", " ");

    if (texto.length() > 130) {
        texto = texto.substring(0, 127) + "...";
    }

    javax.swing.JTextArea txtConteudo =
        new javax.swing.JTextArea(texto);

    txtConteudo.setEditable(false);
    txtConteudo.setFocusable(false);
    txtConteudo.setLineWrap(true);
    txtConteudo.setWrapStyleWord(true);
    txtConteudo.setOpaque(true);
txtConteudo.setBackground(new java.awt.Color(20, 55, 90));
txtConteudo.setBorder(null);

    txtConteudo.setForeground(java.awt.Color.WHITE);
    txtConteudo.setFont(new java.awt.Font("Segoe UI", 0, 13));

    card.add(lblTitulo, java.awt.BorderLayout.NORTH);
    card.add(txtConteudo, java.awt.BorderLayout.CENTER);

    card.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

    card.setMaximumSize(
        new java.awt.Dimension(Integer.MAX_VALUE, 100)
    );

    card.setPreferredSize(
        new java.awt.Dimension(400, 100)
    );

    pnlListaAtividades.add(card);

    pnlListaAtividades.add(
        javax.swing.Box.createVerticalStrut(10)
    );
}

    
private void carregarEmpresasPlataforma() {

    pnlListaEmpresas.removeAll();

    pnlListaEmpresas.setLayout(
        new javax.swing.BoxLayout(
            pnlListaEmpresas,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    String sql = "SELECT e.nome_fantasia, e.area_atuacao, "
            + "e.cidade, e.estado, "
            + "COUNT(v.id_vaga) AS total_vagas "
            + "FROM empresas e "
            + "LEFT JOIN vagas v ON e.id_empresa = v.id_empresa "
            + "AND v.status = 'ATIVA' "
            + "GROUP BY e.id_empresa, e.nome_fantasia, "
            + "e.area_atuacao, e.cidade, e.estado "
            + "ORDER BY total_vagas DESC "
            + "LIMIT 3";

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst = conexao.prepareStatement(sql);
         java.sql.ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {

            String nome = rs.getString("nome_fantasia");
            String area = rs.getString("area_atuacao");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            int vagas = rs.getInt("total_vagas");

            if (nome == null || nome.trim().isEmpty()) {
                nome = "Empresa sem nome";
            }

            if (area == null || area.trim().isEmpty()) {
                area = "Área não informada";
            }

            String localizacao = "";

            if (cidade != null && !cidade.trim().isEmpty()) {
                localizacao = cidade.trim();
            }

            if (estado != null && !estado.trim().isEmpty()) {
                if (!localizacao.isEmpty()) {
                    localizacao += " - ";
                }
                localizacao += estado.trim();
            }

            if (localizacao.isEmpty()) {
                localizacao = "Localização não informada";
            }

            // Card principal
            javax.swing.JPanel cardEmpresa = new javax.swing.JPanel(
                new java.awt.BorderLayout(15, 0)
            );

            cardEmpresa.setBackground(
                new java.awt.Color(20, 55, 90)
            );

            cardEmpresa.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(
                    12, 15, 12, 15
                )
            );

            // Nome e área
            javax.swing.JLabel lblEmpresa = new javax.swing.JLabel(
                nome + "  |  " + area
            );

            lblEmpresa.setForeground(java.awt.Color.WHITE);
            lblEmpresa.setFont(
                new java.awt.Font("Segoe UI", 1, 14)
            );

            // Localização
            javax.swing.JLabel lblLocalizacao =
                new javax.swing.JLabel(localizacao);

            lblLocalizacao.setForeground(
                new java.awt.Color(168, 201, 235)
            );

            lblLocalizacao.setFont(
                new java.awt.Font("Segoe UI", 0, 12)
            );

            // Informações à esquerda
            javax.swing.JPanel pnlInfoEmpresa =
                new javax.swing.JPanel();

            pnlInfoEmpresa.setLayout(
                new javax.swing.BoxLayout(
                    pnlInfoEmpresa,
                    javax.swing.BoxLayout.Y_AXIS
                )
            );

            pnlInfoEmpresa.setOpaque(false);

            lblEmpresa.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
            );

            lblLocalizacao.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
            );

            pnlInfoEmpresa.add(lblEmpresa);

            pnlInfoEmpresa.add(
                javax.swing.Box.createVerticalStrut(5)
            );

            pnlInfoEmpresa.add(lblLocalizacao);

            // Quantidade de vagas à direita
            javax.swing.JLabel lblVagasEmpresa =
                new javax.swing.JLabel(
                    vagas + (vagas == 1
                        ? " vaga ativa"
                        : " vagas ativas")
                );

            lblVagasEmpresa.setForeground(
                new java.awt.Color(22, 217, 244)
            );

            lblVagasEmpresa.setFont(
                new java.awt.Font("Segoe UI", 1, 13)
            );

            // Montagem do card
            cardEmpresa.add(
                pnlInfoEmpresa,
                java.awt.BorderLayout.CENTER
            );

            cardEmpresa.add(
                lblVagasEmpresa,
                java.awt.BorderLayout.EAST
            );

            cardEmpresa.setAlignmentX(
                java.awt.Component.LEFT_ALIGNMENT
            );

            cardEmpresa.setMaximumSize(
                new java.awt.Dimension(Integer.MAX_VALUE, 75)
            );

            cardEmpresa.setPreferredSize(
                new java.awt.Dimension(600, 75)
            );

            cardEmpresa.setMinimumSize(
                new java.awt.Dimension(100, 75)
            );

            pnlListaEmpresas.add(cardEmpresa);

            pnlListaEmpresas.add(
                javax.swing.Box.createVerticalStrut(10)
            );
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar empresas: " + e.getMessage()
        );
    }

    pnlListaEmpresas.revalidate();
    pnlListaEmpresas.repaint();
}
private void carregarMinhasVagasInicio() {

    pnlListaEmpresas.removeAll();

    pnlListaEmpresas.setLayout(
        new javax.swing.BoxLayout(
            pnlListaEmpresas,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    String sql = """
        SELECT v.titulo, v.localizacao, v.status
        FROM vagas v
        INNER JOIN empresas e ON v.id_empresa = e.id_empresa
        WHERE e.id_usuario = ?
        ORDER BY v.data_publicacao DESC
        LIMIT 3
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst = conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                String titulo = rs.getString("titulo");
                String localizacao = rs.getString("localizacao");
                String status = rs.getString("status");

                javax.swing.JPanel card = new javax.swing.JPanel(
                    new java.awt.BorderLayout(15, 0)
                );

                card.setBackground(new java.awt.Color(20, 55, 90));

                card.setBorder(
                    javax.swing.BorderFactory.createEmptyBorder(
                        12, 15, 12, 15
                    )
                );

                javax.swing.JLabel lblTitulo = new javax.swing.JLabel(
                    titulo == null ? "Vaga sem título" : titulo
                );

                lblTitulo.setForeground(java.awt.Color.WHITE);
                lblTitulo.setFont(
                    new java.awt.Font("Segoe UI", 1, 14)
                );

                javax.swing.JLabel lblLocalizacao = new javax.swing.JLabel(
                    localizacao == null ? "Não informada" : localizacao
                );

                lblLocalizacao.setForeground(
                    new java.awt.Color(168, 201, 235)
                );

                javax.swing.JPanel pnlInfo = new javax.swing.JPanel();

                pnlInfo.setLayout(
                    new javax.swing.BoxLayout(
                        pnlInfo,
                        javax.swing.BoxLayout.Y_AXIS
                    )
                );

                pnlInfo.setOpaque(false);

                pnlInfo.add(lblTitulo);
                pnlInfo.add(
                    javax.swing.Box.createVerticalStrut(5)
                );
                pnlInfo.add(lblLocalizacao);

                javax.swing.JLabel lblStatus = new javax.swing.JLabel(
                    status == null ? "Sem status" : status
                );

                lblStatus.setForeground(
                    new java.awt.Color(22, 217, 244)
                );

                lblStatus.setFont(
                    new java.awt.Font("Segoe UI", 1, 13)
                );

                card.add(pnlInfo, java.awt.BorderLayout.CENTER);
                card.add(lblStatus, java.awt.BorderLayout.EAST);

                card.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

                card.setMaximumSize(
                    new java.awt.Dimension(Integer.MAX_VALUE, 75)
                );

                card.setPreferredSize(
                    new java.awt.Dimension(600, 75)
                );

                pnlListaEmpresas.add(card);

                pnlListaEmpresas.add(
                    javax.swing.Box.createVerticalStrut(10)
                );
            }
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar vagas da empresa: " + e.getMessage()
        );
    }

    pnlListaEmpresas.revalidate();
    pnlListaEmpresas.repaint();
}

    private void carregarDescricaoEmpresa() {

    String sql = "SELECT descricao FROM empresas WHERE id_usuario = ?";

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String descricao = rs.getString("descricao");
                System.out.println("Descrição encontrada: " + descricao);

                if (descricao != null && !descricao.trim().isEmpty()) {
                    txtDescricaoBanner.setText(descricao);
                    txtDescricaoBanner.setCaretPosition(0);
                } else {
                    txtDescricaoBanner.setText(
    "Sua empresa ainda não possui uma descrição."
);
                }
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar descrição da empresa: " + e.getMessage()
        );
    }
}
private void ocultarPainelPerfil() {
    if (scrollPerfil != null) {
        scrollPerfil.setVisible(false);
    }
}
    private void mostrarPainelPerfil() {
        if (scrollVagas != null) {
    scrollVagas.setVisible(false);
}
        if (scrollDetalhesVaga != null) {
    scrollDetalhesVaga.setVisible(false);
}

    if (painelPerfil == null) {
        painelPerfil = new VisualizarPerfil_Panel(idUsuario);

scrollPerfil = new javax.swing.JScrollPane(painelPerfil);
scrollPerfil.setHorizontalScrollBarPolicy(
    javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);
scrollPerfil.getVerticalScrollBar().setUnitIncrement(20);

pnlPrincipal.add(scrollPerfil);
    }

    scrollPerfil.setBounds(
        pnlMenuLateral.getX() + pnlMenuLateral.getWidth() + 20,
        0,
        pnlPrincipal.getWidth() - pnlMenuLateral.getWidth() - 40,
        pnlPrincipal.getHeight()
    );

    scrollPerfil.setVisible(true);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
private void carregarNomeUsuario() {

    String sql;

    if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {
        sql = """
            SELECT u.nome, u.nome_empresa, e.nome_fantasia, e.razao_social,
                   e.area_atuacao, e.cidade, e.estado
            FROM usuarios u
            LEFT JOIN empresas e ON e.id_usuario = u.id
            WHERE u.id = ?
            """;
    } else {
        sql = "SELECT nome, area_atuacao FROM usuarios WHERE id = ?";
    }

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        System.out.println("Buscando descrição para o usuário ID: " + idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {

String nomeEmpresa = rs.getString("nome_empresa");

if (nomeEmpresa == null || nomeEmpresa.isBlank()) {
    nomeEmpresa = rs.getString("nome_fantasia");
}

if (nomeEmpresa == null || nomeEmpresa.isBlank()) {
    nomeEmpresa = rs.getString("razao_social");
}

                    lblBoasVindas.setText("Olá, " + nomeEmpresa + "!");
                    String cidade = rs.getString("cidade");
String estado = rs.getString("estado");

if (cidade != null && !cidade.isBlank()
        && estado != null && !estado.isBlank()) {

    lblAreaatuacao.setText(
        "Empresa localizada em " + cidade + " - " + estado
    );

} else {
    lblAreaatuacao.setText("Localização não informada");
}

                    

                } else {

                    String nome = rs.getString("nome");
                    String area = rs.getString("area_atuacao");

                    lblBoasVindas.setText("Olá, " + nome + "!");
                    lblAreaatuacao.setText(
                        "Área de atuação: " +
                        (area == null || area.isBlank()
                            ? "Não informada" : area)
                    );
                }
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar nome: " + e.getMessage()
        );
    }
}
    private boolean perfilJaCadastrado() {

    String sql;

    if (tipoUsuario.equalsIgnoreCase("PROFISSIONAL")) {

        sql = "SELECT id_candidato FROM candidatos WHERE id_usuario = ?";

    } else if (tipoUsuario.equalsIgnoreCase("EMPRESA")) {

        sql = "SELECT id_empresa FROM empresas WHERE id_usuario = ?";

    } else {
        return false;
    }

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao verificar perfil: " + e.getMessage()
        );

        return false;
    }
}
    private void ocultarPaginaInicial() {
    pnlBoasVindas.setVisible(false);
    pnlBanner.setVisible(false);
    pnlVagasRecomendadas1.setVisible(false);
    pnlMatchesRealizados.setVisible(false);
    pnlCompatibilidadeMedia.setVisible(false);
    lblvagasdisponiveis3.setVisible(false);
    Jvertodasvagas1.setVisible(false);
    pnlEmpresasPlataforma.setVisible(false);
    pnlAtividadesRecentes.setVisible(false);
 
}
    private void mostrarPaginaInicial() {

    pnlBoasVindas.setVisible(true);
    pnlBanner.setVisible(true);
    pnlVagasRecomendadas1.setVisible(true);
    pnlMatchesRealizados.setVisible(true);
    pnlCompatibilidadeMedia.setVisible(true);
    lblvagasdisponiveis3.setVisible(true);
    Jvertodasvagas1.setVisible(true);
    pnlEmpresasPlataforma.setVisible(true);
    pnlAtividadesRecentes.setVisible(true);

    
}
    

private void mostrarPainelVagasEmpresa() {

    if (scrollPerfil != null) {
        scrollPerfil.setVisible(false);
    }

    if (scrollPerfilEmpresa != null) {
        scrollPerfilEmpresa.setVisible(false);
    }

    if (scrollVagas != null) {
        scrollVagas.setVisible(false);
    }

    if (scrollVagasEmpresa == null) {

        painelVagasEmpresa = new Vagas_PerfilEmpresa(idUsuario);
        painelVagasEmpresa.setAcaoPublicarVaga(
        () -> mostrarPainelPublicarVaga()
);
        painelVagasEmpresa.setAcaoEditarVaga(idVaga -> {
    mostrarPainelEditarVaga(idVaga);
});

        scrollVagasEmpresa =
                new javax.swing.JScrollPane(painelVagasEmpresa);

        scrollVagasEmpresa.setHorizontalScrollBarPolicy(
                javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollVagasEmpresa.getVerticalScrollBar()
                .setUnitIncrement(20);

        pnlPrincipal.add(scrollVagasEmpresa);
    }

    int inicioConteudo = pnlMenuLateral.getX()
            + pnlMenuLateral.getWidth();

    scrollVagasEmpresa.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    scrollVagasEmpresa.setVisible(true);
    pnlPrincipal.setComponentZOrder(scrollVagasEmpresa, 0);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
private void mostrarPainelEditarVaga(int idVaga) {
    mostrarPainelPublicarVaga();

    PublicarVaga_Panel painel =
        (PublicarVaga_Panel) scrollPublicarVaga.getViewport().getView();

    painel.configurarEdicao(idVaga);
}

private javax.swing.JScrollPane scrollPublicarVaga;

private void mostrarPainelPublicarVaga() {

    // Esconde a tela de gerenciamento de vagas
    if (scrollVagasEmpresa != null) {
        scrollVagasEmpresa.setVisible(false);
    }

    // Esconde as outras telas
    if (scrollPerfil != null) {
        scrollPerfil.setVisible(false);
    }

    if (scrollPerfilEmpresa != null) {
        scrollPerfilEmpresa.setVisible(false);
    }

    if (scrollVagas != null) {
        scrollVagas.setVisible(false);
    }

    // Cria o formulário somente uma vez
    if (scrollPublicarVaga == null) {

        PublicarVaga_Panel painelPublicar =
        new PublicarVaga_Panel(idUsuario);
painelPublicar.setAcaoPublicacaoConcluida(() -> {
    scrollPublicarVaga.setVisible(false);

    painelVagasEmpresa.atualizarVagasEmpresa();

    mostrarPainelVagasEmpresa();
});
painelPublicar.setAcaoCancelar(() -> {
    scrollPublicarVaga.setVisible(false);
    mostrarPainelVagasEmpresa();
});
        scrollPublicarVaga =
                new javax.swing.JScrollPane(painelPublicar);

        scrollPublicarVaga.setHorizontalScrollBarPolicy(
                javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPublicarVaga.getVerticalScrollBar()
                .setUnitIncrement(20);

        pnlPrincipal.add(scrollPublicarVaga);
    }

    // Posiciona o formulário ao lado do menu lateral
    int inicioConteudo = pnlMenuLateral.getX()
            + pnlMenuLateral.getWidth();

    scrollPublicarVaga.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    scrollPublicarVaga.setVisible(true);
    PublicarVaga_Panel painel =
    (PublicarVaga_Panel) scrollPublicarVaga.getViewport().getView();

painel.configurarNovaVaga();
    pnlPrincipal.setComponentZOrder(scrollPublicarVaga, 0);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}

private javax.swing.JScrollPane scrollDetalhesVaga;

private void mostrarDetalhesVaga(int idVaga) {

    if (scrollVagas != null) scrollVagas.setVisible(false);
    if (scrollVagasEmpresa != null) scrollVagasEmpresa.setVisible(false);
    if (scrollPerfil != null) scrollPerfil.setVisible(false);
    if (scrollPerfilEmpresa != null) scrollPerfilEmpresa.setVisible(false);
    if (scrollPublicarVaga != null) scrollPublicarVaga.setVisible(false);

    if (scrollDetalhesVaga != null) {
        pnlPrincipal.remove(scrollDetalhesVaga);
    }

    DetalhesVaga_Panel painelDetalhes =
            new DetalhesVaga_Panel(idVaga);
    
painelDetalhes.setAcaoVoltarVagas(() -> {
    scrollDetalhesVaga.setVisible(false);
    mostrarPainelVagas();
});
    scrollDetalhesVaga =
            new javax.swing.JScrollPane(painelDetalhes);

    scrollDetalhesVaga.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollDetalhesVaga.getVerticalScrollBar().setUnitIncrement(20);

    pnlPrincipal.add(scrollDetalhesVaga);

    int inicioConteudo =
            pnlMenuLateral.getX() + pnlMenuLateral.getWidth();

    scrollDetalhesVaga.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    scrollDetalhesVaga.setVisible(true);
    pnlPrincipal.setComponentZOrder(scrollDetalhesVaga, 0);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
private void carregarQuantidadeVagasEmpresa() {

    String sql = """
        SELECT
            COUNT(*) AS total_publicadas,
            COUNT(CASE WHEN v.status = 'ATIVA' THEN 1 END) AS total_ativas
        FROM vagas v
        INNER JOIN empresas e ON v.id_empresa = e.id_empresa
        WHERE e.id_usuario = ?
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst = conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                lblQuantidadeVagas1.setText(
                    String.valueOf(rs.getInt("total_publicadas"))
                );

                lblQuantidadeVagas3.setText(
                    String.valueOf(rs.getInt("total_ativas"))
                );
            }
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar vagas da empresa: " + e.getMessage()
        );
    }
}

private void carregarResumoEmpresa() {

    pnlListaAtividades.removeAll();

    pnlListaAtividades.setLayout(
        new javax.swing.BoxLayout(
            pnlListaAtividades,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    String area = "Não informada";
    String localizacao = "Não informada";
    String descricao = "Nenhuma descrição cadastrada.";

    String sql = """
        SELECT area_atuacao, cidade, estado, descricao
        FROM empresas
        WHERE id_usuario = ?
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst = conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                String areaBanco = rs.getString("area_atuacao");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String descricaoBanco = rs.getString("descricao");

                if (areaBanco != null && !areaBanco.isBlank()) {
                    area = areaBanco.trim();
                }

                if (cidade != null && !cidade.isBlank()) {
                    localizacao = cidade.trim();

                    if (estado != null && !estado.isBlank()) {
                        localizacao += " - " + estado.trim();
                    }
                } else if (estado != null && !estado.isBlank()) {
                    localizacao = estado.trim();
                }

                if (descricaoBanco != null && !descricaoBanco.isBlank()) {
                    descricao = descricaoBanco.trim();
                }
            }
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar resumo da empresa: " + e.getMessage()
        );
    }

    adicionarCampoResumo("Área de atuação", area);
    adicionarCampoResumo("Localização", localizacao);
    adicionarCampoResumo("Descrição da empresa", descricao);

    pnlListaAtividades.revalidate();
    pnlListaAtividades.repaint();
}








    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlMenuLateral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnMeuPerfil = new javax.swing.JButton();
        btnInicio1 = new javax.swing.JButton();
        btnVagas2 = new javax.swing.JButton();
        btnInteresses = new javax.swing.JButton();
        btnMatches1 = new javax.swing.JButton();
        btnMatches = new javax.swing.JButton();
        pnlBoasVindas = new javax.swing.JPanel();
        lblBoasVindas = new javax.swing.JLabel();
        lblAreaatuacao = new javax.swing.JLabel();
        pnlBanner = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricaoBanner = new javax.swing.JTextArea();
        lblTituloBanner1 = new javax.swing.JLabel();
        pnlVagasRecomendadas1 = new javax.swing.JPanel();
        lblQuantidadeVagas1 = new javax.swing.JLabel();
        lblvagasdisponiveis3 = new javax.swing.JLabel();
        pnlCompatibilidadeMedia = new javax.swing.JPanel();
        lblQuantidadeVagas3 = new javax.swing.JLabel();
        lblDescricaoVagas3 = new javax.swing.JLabel();
        pnlMatchesRealizados = new javax.swing.JPanel();
        lblQuantidadeVagas2 = new javax.swing.JLabel();
        lblDescricaoVagas2 = new javax.swing.JLabel();
        pnlEmpresasPlataforma = new javax.swing.JPanel();
        lblEmpresasPlataforma = new javax.swing.JLabel();
        pnlListaEmpresas = new javax.swing.JPanel();
        Jvertodasvagas1 = new javax.swing.JLabel();
        pnlAtividadesRecentes = new javax.swing.JPanel();
        lblAtividadesRecentes = new javax.swing.JLabel();
        pnlListaAtividades = new javax.swing.JPanel();
        btnVerPerfilCompleto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONEXOO | Início");
        setBackground(new java.awt.Color(7, 21, 45));
        setMinimumSize(new java.awt.Dimension(1000, 650));

        pnlPrincipal.setBackground(new java.awt.Color(5, 20, 43));
        pnlPrincipal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(19, 216, 245)));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(1200, 750));

        pnlMenuLateral.setBackground(new java.awt.Color(11, 29, 56));
        pnlMenuLateral.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(19, 216, 245)));
        pnlMenuLateral.setPreferredSize(new java.awt.Dimension(250, 750));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(19, 216, 245));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONEXOO");
        jLabel1.setPreferredSize(new java.awt.Dimension(220, 45));

        btnMeuPerfil.setBackground(new java.awt.Color(11, 29, 56));
        btnMeuPerfil.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMeuPerfil.setForeground(new java.awt.Color(168, 201, 235));
        btnMeuPerfil.setText(" Meu Perfil");
        btnMeuPerfil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMeuPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMeuPerfil.setFocusPainted(false);
        btnMeuPerfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMeuPerfil.setPreferredSize(new java.awt.Dimension(220, 50));
        btnMeuPerfil.addActionListener(this::btnMeuPerfilActionPerformed);

        btnInicio1.setBackground(new java.awt.Color(8, 127, 167));
        btnInicio1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnInicio1.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio1.setText(" Início");
        btnInicio1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInicio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio1.setFocusPainted(false);
        btnInicio1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio1.setPreferredSize(new java.awt.Dimension(220, 50));
        btnInicio1.addActionListener(this::btnInicio1ActionPerformed);

        btnVagas2.setBackground(new java.awt.Color(11, 29, 56));
        btnVagas2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnVagas2.setForeground(new java.awt.Color(168, 201, 235));
        btnVagas2.setText(" Vagas");
        btnVagas2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnVagas2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVagas2.setFocusPainted(false);
        btnVagas2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVagas2.setPreferredSize(new java.awt.Dimension(220, 50));
        btnVagas2.addActionListener(this::btnVagas2ActionPerformed);

        btnInteresses.setBackground(new java.awt.Color(11, 29, 56));
        btnInteresses.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnInteresses.setForeground(new java.awt.Color(168, 201, 235));
        btnInteresses.setText(" Interesses");
        btnInteresses.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInteresses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInteresses.setFocusPainted(false);
        btnInteresses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInteresses.setPreferredSize(new java.awt.Dimension(220, 50));
        btnInteresses.addActionListener(this::btnInteressesActionPerformed);

        btnMatches1.setBackground(new java.awt.Color(11, 29, 56));
        btnMatches1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMatches1.setForeground(new java.awt.Color(168, 201, 235));
        btnMatches1.setText(" Matchs");
        btnMatches1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMatches1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMatches1.setFocusPainted(false);
        btnMatches1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMatches1.setPreferredSize(new java.awt.Dimension(220, 50));
        btnMatches1.addActionListener(this::btnMatches1ActionPerformed);

        btnMatches.setBackground(new java.awt.Color(11, 29, 56));
        btnMatches.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMatches.setForeground(new java.awt.Color(168, 201, 235));
        btnMatches.setText(" Sair");
        btnMatches.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMatches.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMatches.setFocusPainted(false);
        btnMatches.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMatches.setPreferredSize(new java.awt.Dimension(220, 50));
        btnMatches.addActionListener(this::btnMatchesActionPerformed);

        javax.swing.GroupLayout pnlMenuLateralLayout = new javax.swing.GroupLayout(pnlMenuLateral);
        pnlMenuLateral.setLayout(pnlMenuLateralLayout);
        pnlMenuLateralLayout.setHorizontalGroup(
            pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVagas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMeuPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInicio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMatches1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInteresses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMatches, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pnlMenuLateralLayout.setVerticalGroup(
            pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMeuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVagas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInteresses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMatches1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMatches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBoasVindas.setBackground(new java.awt.Color(11, 29, 56));
        pnlBoasVindas.setPreferredSize(new java.awt.Dimension(1050, 95));

        lblBoasVindas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblBoasVindas.setForeground(new java.awt.Color(255, 255, 255));
        lblBoasVindas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBoasVindas.setText("Olá, usuário!");
        lblBoasVindas.setPreferredSize(new java.awt.Dimension(350, 35));

        lblAreaatuacao.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblAreaatuacao.setForeground(new java.awt.Color(168, 201, 235));
        lblAreaatuacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAreaatuacao.setText("Área de atuação atual: Não informada");
        lblAreaatuacao.setPreferredSize(new java.awt.Dimension(250, 25));

        javax.swing.GroupLayout pnlBoasVindasLayout = new javax.swing.GroupLayout(pnlBoasVindas);
        pnlBoasVindas.setLayout(pnlBoasVindasLayout);
        pnlBoasVindasLayout.setHorizontalGroup(
            pnlBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoasVindasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAreaatuacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBoasVindas, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlBoasVindasLayout.setVerticalGroup(
            pnlBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoasVindasLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(lblBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAreaatuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pnlBanner.setBackground(new java.awt.Color(7, 56, 120));
        pnlBanner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(22, 137, 244)));
        pnlBanner.setPreferredSize(new java.awt.Dimension(1050, 180));

        jScrollPane1.setBackground(new java.awt.Color(7, 56, 120));
        jScrollPane1.setBorder(null);

        txtDescricaoBanner.setEditable(false);
        txtDescricaoBanner.setBackground(new java.awt.Color(7, 56, 120));
        txtDescricaoBanner.setColumns(20);
        txtDescricaoBanner.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDescricaoBanner.setForeground(new java.awt.Color(168, 201, 235));
        txtDescricaoBanner.setLineWrap(true);
        txtDescricaoBanner.setRows(5);
        txtDescricaoBanner.setWrapStyleWord(true);
        txtDescricaoBanner.setBorder(null);
        txtDescricaoBanner.setOpaque(false);
        jScrollPane1.setViewportView(txtDescricaoBanner);

        lblTituloBanner1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTituloBanner1.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloBanner1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTituloBanner1.setText("Encontre vagas que combinam com você!");
        lblTituloBanner1.setPreferredSize(new java.awt.Dimension(650, 40));

        javax.swing.GroupLayout pnlBannerLayout = new javax.swing.GroupLayout(pnlBanner);
        pnlBanner.setLayout(pnlBannerLayout);
        pnlBannerLayout.setHorizontalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBannerLayout.createSequentialGroup()
                        .addComponent(lblTituloBanner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 587, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlBannerLayout.setVerticalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblTituloBanner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlVagasRecomendadas1.setBackground(new java.awt.Color(11, 37, 69));
        pnlVagasRecomendadas1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVagasRecomendadas1.setPreferredSize(new java.awt.Dimension(209, 110));

        lblQuantidadeVagas1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadeVagas1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadeVagas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadeVagas1.setText("24");

        lblvagasdisponiveis3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblvagasdisponiveis3.setForeground(new java.awt.Color(168, 201, 235));
        lblvagasdisponiveis3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblvagasdisponiveis3.setText("Vagas disponíveis");

        javax.swing.GroupLayout pnlVagasRecomendadas1Layout = new javax.swing.GroupLayout(pnlVagasRecomendadas1);
        pnlVagasRecomendadas1.setLayout(pnlVagasRecomendadas1Layout);
        pnlVagasRecomendadas1Layout.setHorizontalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addGroup(pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas1Layout.createSequentialGroup()
                        .addComponent(lblvagasdisponiveis3)
                        .addGap(145, 145, 145))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas1Layout.createSequentialGroup()
                        .addComponent(lblQuantidadeVagas1)
                        .addGap(187, 187, 187))))
        );
        pnlVagasRecomendadas1Layout.setVerticalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblQuantidadeVagas1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblvagasdisponiveis3)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnlCompatibilidadeMedia.setBackground(new java.awt.Color(11, 37, 69));
        pnlCompatibilidadeMedia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlCompatibilidadeMedia.setPreferredSize(new java.awt.Dimension(240, 110));

        lblQuantidadeVagas3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadeVagas3.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadeVagas3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadeVagas3.setText("85%");

        lblDescricaoVagas3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescricaoVagas3.setForeground(new java.awt.Color(168, 201, 235));
        lblDescricaoVagas3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescricaoVagas3.setText("Candidaturas enviadas");

        javax.swing.GroupLayout pnlCompatibilidadeMediaLayout = new javax.swing.GroupLayout(pnlCompatibilidadeMedia);
        pnlCompatibilidadeMedia.setLayout(pnlCompatibilidadeMediaLayout);
        pnlCompatibilidadeMediaLayout.setHorizontalGroup(
            pnlCompatibilidadeMediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompatibilidadeMediaLayout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addGroup(pnlCompatibilidadeMediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompatibilidadeMediaLayout.createSequentialGroup()
                        .addComponent(lblDescricaoVagas3)
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompatibilidadeMediaLayout.createSequentialGroup()
                        .addComponent(lblQuantidadeVagas3)
                        .addGap(188, 188, 188))))
        );
        pnlCompatibilidadeMediaLayout.setVerticalGroup(
            pnlCompatibilidadeMediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompatibilidadeMediaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblQuantidadeVagas3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoVagas3)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnlMatchesRealizados.setBackground(new java.awt.Color(11, 37, 69));
        pnlMatchesRealizados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlMatchesRealizados.setPreferredSize(new java.awt.Dimension(209, 110));

        lblQuantidadeVagas2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadeVagas2.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadeVagas2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadeVagas2.setText("3");

        lblDescricaoVagas2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescricaoVagas2.setForeground(new java.awt.Color(168, 201, 235));
        lblDescricaoVagas2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescricaoVagas2.setText("Matches realizados");

        javax.swing.GroupLayout pnlMatchesRealizadosLayout = new javax.swing.GroupLayout(pnlMatchesRealizados);
        pnlMatchesRealizados.setLayout(pnlMatchesRealizadosLayout);
        pnlMatchesRealizadosLayout.setHorizontalGroup(
            pnlMatchesRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatchesRealizadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMatchesRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchesRealizadosLayout.createSequentialGroup()
                        .addComponent(lblQuantidadeVagas2)
                        .addGap(163, 163, 163))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchesRealizadosLayout.createSequentialGroup()
                        .addComponent(lblDescricaoVagas2)
                        .addGap(112, 112, 112))))
        );
        pnlMatchesRealizadosLayout.setVerticalGroup(
            pnlMatchesRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchesRealizadosLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lblQuantidadeVagas2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoVagas2)
                .addGap(25, 25, 25))
        );

        pnlEmpresasPlataforma.setBackground(new java.awt.Color(11, 37, 69));
        pnlEmpresasPlataforma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblEmpresasPlataforma.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblEmpresasPlataforma.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpresasPlataforma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEmpresasPlataforma.setText("Empresas na plataforma");
        lblEmpresasPlataforma.setPreferredSize(new java.awt.Dimension(650, 40));

        pnlListaEmpresas.setBackground(new java.awt.Color(11, 37, 69));
        pnlListaEmpresas.setLayout(new javax.swing.BoxLayout(pnlListaEmpresas, javax.swing.BoxLayout.LINE_AXIS));

        Jvertodasvagas1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Jvertodasvagas1.setForeground(new java.awt.Color(22, 217, 244));
        Jvertodasvagas1.setText("Ver todas →");
        Jvertodasvagas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Jvertodasvagas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jvertodasvagas1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlEmpresasPlataformaLayout = new javax.swing.GroupLayout(pnlEmpresasPlataforma);
        pnlEmpresasPlataforma.setLayout(pnlEmpresasPlataformaLayout);
        pnlEmpresasPlataformaLayout.setHorizontalGroup(
            pnlEmpresasPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpresasPlataformaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEmpresasPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlListaEmpresas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEmpresasPlataformaLayout.createSequentialGroup()
                        .addComponent(lblEmpresasPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Jvertodasvagas1)))
                .addContainerGap())
        );
        pnlEmpresasPlataformaLayout.setVerticalGroup(
            pnlEmpresasPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpresasPlataformaLayout.createSequentialGroup()
                .addGroup(pnlEmpresasPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmpresasPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEmpresasPlataformaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Jvertodasvagas1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlListaEmpresas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlAtividadesRecentes.setBackground(new java.awt.Color(11, 37, 69));
        pnlAtividadesRecentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblAtividadesRecentes.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblAtividadesRecentes.setForeground(new java.awt.Color(255, 255, 255));
        lblAtividadesRecentes.setText("Resumo profissional");

        pnlListaAtividades.setBackground(new java.awt.Color(11, 37, 69));
        pnlListaAtividades.setLayout(new javax.swing.BoxLayout(pnlListaAtividades, javax.swing.BoxLayout.LINE_AXIS));

        btnVerPerfilCompleto.setBackground(new java.awt.Color(13, 39, 70));
        btnVerPerfilCompleto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerPerfilCompleto.setForeground(new java.awt.Color(22, 217, 244));
        btnVerPerfilCompleto.setText("Ver perfil completo →");
        btnVerPerfilCompleto.setBorder(null);
        btnVerPerfilCompleto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerPerfilCompleto.addActionListener(this::btnVerPerfilCompletoActionPerformed);

        javax.swing.GroupLayout pnlAtividadesRecentesLayout = new javax.swing.GroupLayout(pnlAtividadesRecentes);
        pnlAtividadesRecentes.setLayout(pnlAtividadesRecentesLayout);
        pnlAtividadesRecentesLayout.setHorizontalGroup(
            pnlAtividadesRecentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtividadesRecentesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAtividadesRecentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtividadesRecentesLayout.createSequentialGroup()
                        .addComponent(lblAtividadesRecentes, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 264, Short.MAX_VALUE))
                    .addGroup(pnlAtividadesRecentesLayout.createSequentialGroup()
                        .addGroup(pnlAtividadesRecentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAtividadesRecentesLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnVerPerfilCompleto))
                            .addComponent(pnlListaAtividades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlAtividadesRecentesLayout.setVerticalGroup(
            pnlAtividadesRecentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtividadesRecentesLayout.createSequentialGroup()
                .addComponent(lblAtividadesRecentes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlListaAtividades, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerPerfilCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlBanner, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE)
                            .addComponent(pnlBoasVindas, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(pnlEmpresasPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlAtividadesRecentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCompatibilidadeMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlMatchesRealizados, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)))
                        .addGap(87, 87, 87))))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlMenuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(pnlBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(pnlBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlCompatibilidadeMedia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(pnlMatchesRealizados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlEmpresasPlataforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlAtividadesRecentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 1534, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMeuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeuPerfilActionPerformed

     if (perfilJaCadastrado()) {

        ocultarPaginaInicial();

        if (tipoUsuario.equalsIgnoreCase("PROFISSIONAL")) {

            mostrarPainelPerfil();

        } else if (tipoUsuario.equalsIgnoreCase("EMPRESA")) {

    if (scrollVagasEmpresa != null) {
        scrollVagasEmpresa.setVisible(false);
    }

    mostrarPainelPerfilEmpresa();

        }

    } else {

if (tipoUsuario.equalsIgnoreCase("PROFISSIONAL")) {

PerfilProfissional_Frame perfil =
        new PerfilProfissional_Frame(idUsuario);

perfil.setVisible(true);
this.setVisible(false);

        } else if (tipoUsuario.equalsIgnoreCase("EMPRESA")) {

            PerfilEmpresa perfil = new PerfilEmpresa(idUsuario);
            perfil.setVisible(true);
            this.setVisible(false);
        }

        //this.dispose();
    }
    }//GEN-LAST:event_btnMeuPerfilActionPerformed

    private void btnInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicio1ActionPerformed
    
    ocultarPainelPerfil();

    if (scrollPerfilEmpresa != null) {
        scrollPerfilEmpresa.setVisible(false);
    }

    if (scrollVagas != null) {
        scrollVagas.setVisible(false);
    }

    if (scrollVagasEmpresa != null) {
        scrollVagasEmpresa.setVisible(false);
    }

    if (scrollDetalhesVaga != null) {
        scrollDetalhesVaga.setVisible(false);
    }

    if (scrollPublicarVaga != null) {
        scrollPublicarVaga.setVisible(false);
    }

    mostrarPaginaInicial();
    if (tipoUsuario.equalsIgnoreCase("EMPRESA")) {
    carregarQuantidadeVagasEmpresa();
} else {
    carregarQuantidadeVagas();
}

   if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {
    carregarMinhasVagasInicio();
}
    }//GEN-LAST:event_btnInicio1ActionPerformed

    private void btnMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatchesActionPerformed
        Login_Frame login = new Login_Frame();
login.setLocationRelativeTo(null);
login.setVisible(true);

this.dispose();
    }//GEN-LAST:event_btnMatchesActionPerformed

    private void btnVagas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVagas2ActionPerformed
        mostrarPainelVagas();
        ocultarPaginaInicial();
    }//GEN-LAST:event_btnVagas2ActionPerformed

    private void btnInteressesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInteressesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInteressesActionPerformed

    private void btnMatches1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatches1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMatches1ActionPerformed

    private void btnVerPerfilCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPerfilCompletoActionPerformed
    ocultarPaginaInicial();
        ocultarPaginaInicial();

    if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {

        mostrarPainelPerfilEmpresa();

    } else if ("PROFISSIONAL".equalsIgnoreCase(tipoUsuario)) {

        mostrarPainelPerfil();

    }
    }//GEN-LAST:event_btnVerPerfilCompletoActionPerformed

    private void Jvertodasvagas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jvertodasvagas1MouseClicked
        if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {
    mostrarPainelVagasEmpresa();
} else {
    mostrarPainelVagas();
}

ocultarPaginaInicial();
    }//GEN-LAST:event_Jvertodasvagas1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Frame.class.getName())
            .log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Menu_Frame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jvertodasvagas1;
    private javax.swing.JButton btnInicio1;
    private javax.swing.JButton btnInteresses;
    private javax.swing.JButton btnMatches;
    private javax.swing.JButton btnMatches1;
    private javax.swing.JButton btnMeuPerfil;
    private javax.swing.JButton btnVagas2;
    private javax.swing.JButton btnVerPerfilCompleto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAreaatuacao;
    private javax.swing.JLabel lblAtividadesRecentes;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JLabel lblDescricaoVagas2;
    private javax.swing.JLabel lblDescricaoVagas3;
    private javax.swing.JLabel lblEmpresasPlataforma;
    private javax.swing.JLabel lblQuantidadeVagas1;
    private javax.swing.JLabel lblQuantidadeVagas2;
    private javax.swing.JLabel lblQuantidadeVagas3;
    private javax.swing.JLabel lblTituloBanner1;
    private javax.swing.JLabel lblvagasdisponiveis3;
    private javax.swing.JPanel pnlAtividadesRecentes;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlBoasVindas;
    private javax.swing.JPanel pnlCompatibilidadeMedia;
    private javax.swing.JPanel pnlEmpresasPlataforma;
    private javax.swing.JPanel pnlListaAtividades;
    private javax.swing.JPanel pnlListaEmpresas;
    private javax.swing.JPanel pnlMatchesRealizados;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlVagasRecomendadas1;
    private javax.swing.JTextArea txtDescricaoBanner;
    // End of variables declaration//GEN-END:variables
}
