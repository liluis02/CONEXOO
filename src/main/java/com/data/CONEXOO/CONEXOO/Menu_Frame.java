/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

public class Menu_Frame extends javax.swing.JFrame {
    private boolean detalhesVeioDeInteresses = false;
    
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
        
        if (scrollMatchesEmpresa != null) {
    scrollMatchesEmpresa.setVisible(false);
}
        
        if (scrollPerfilProfissionalInteresse != null) {
    scrollPerfilProfissionalInteresse.setVisible(false);
}
        
        if (scrollInteressesEmpresa != null) {
    scrollInteressesEmpresa.setVisible(false);
}
        
        if (scrollVagas != null) {
    scrollVagas.setVisible(false);
}

    if (painelPerfilEmpresa == null) {
        painelPerfilEmpresa = new VisualizarPerfilEmpresa(idUsuario);
        
        painelPerfilEmpresa.carregarPerfilProprio();

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
    private InteresseProfissional1 painelInteresses;
    private javax.swing.JScrollPane scrollInteresses;
    
    private InteresseEmpresa painelInteressesEmpresa;
    private javax.swing.JScrollPane scrollInteressesEmpresa;
    
    private Matchsprofissional painelMatches;
    private javax.swing.JScrollPane scrollMatches;
    private javax.swing.JScrollPane scrollPerfilEmpresaMatch;
    private MatchsEmpresa painelMatchesEmpresa;
    private javax.swing.JScrollPane scrollMatchesEmpresa;
    
    
    private void mostrarPainelMatchesEmpresa() {

    // Oculta as outras páginas
    if (scrollPerfil != null)
        scrollPerfil.setVisible(false);

    if (scrollPerfilEmpresa != null)
        scrollPerfilEmpresa.setVisible(false);

    if (scrollVagas != null)
        scrollVagas.setVisible(false);

    if (scrollVagasEmpresa != null)
        scrollVagasEmpresa.setVisible(false);

    if (scrollInteresses != null)
        scrollInteresses.setVisible(false);

    if (scrollInteressesEmpresa != null)
        scrollInteressesEmpresa.setVisible(false);

    if (scrollMatches != null)
        scrollMatches.setVisible(false);

    if (scrollDetalhesVaga != null)
        scrollDetalhesVaga.setVisible(false);

    if (scrollPublicarVaga != null)
        scrollPublicarVaga.setVisible(false);

    if (scrollPerfilProfissionalInteresse != null)
        scrollPerfilProfissionalInteresse.setVisible(false);

    if (scrollPerfilEmpresaMatch != null)
        scrollPerfilEmpresaMatch.setVisible(false);

    // Cria o painel apenas uma vez
    if (painelMatchesEmpresa == null) {

        painelMatchesEmpresa = new MatchsEmpresa(idUsuario);
            painelMatchesEmpresa.setAcaoVerPerfil(idProfissional -> {
        mostrarPerfilProfissionalMatches(idProfissional);
        });
        scrollMatchesEmpresa =
            new javax.swing.JScrollPane(painelMatchesEmpresa);

        scrollMatchesEmpresa.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollMatchesEmpresa.getVerticalScrollBar()
            .setUnitIncrement(20);

        pnlPrincipal.add(scrollMatchesEmpresa);
    }
    
    if (painelMatchesEmpresa != null) {
    painelMatchesEmpresa.atualizarDados();
}

    // Posiciona ao lado do menu lateral
    int inicioConteudo =
        pnlMenuLateral.getX() + pnlMenuLateral.getWidth();

    scrollMatchesEmpresa.setBounds(
        inicioConteudo,
        0,
        pnlPrincipal.getWidth() - inicioConteudo,
        pnlPrincipal.getHeight()
    );

    scrollMatchesEmpresa.setVisible(true);

    pnlPrincipal.setComponentZOrder(
        scrollMatchesEmpresa, 0
    );

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
    pnlPrincipal.setComponentZOrder(pnlMenuLateral, 0);
}
    
    
    private void mostrarPainelInteressesEmpresa() {
        
        
    if (scrollPerfilProfissionalInteresse != null) {
    scrollPerfilProfissionalInteresse.setVisible(false);
}
    if (scrollVagas != null) scrollVagas.setVisible(false);
    if (scrollVagasEmpresa != null) scrollVagasEmpresa.setVisible(false);
    if (scrollDetalhesVaga != null) scrollDetalhesVaga.setVisible(false);
    if (scrollPerfil != null) scrollPerfil.setVisible(false);
    if (scrollPerfilEmpresa != null) scrollPerfilEmpresa.setVisible(false);
    if (scrollPublicarVaga != null) scrollPublicarVaga.setVisible(false);
    if (scrollInteresses != null) scrollInteresses.setVisible(false);
    if (scrollMatches != null) scrollMatches.setVisible(false);

    if (painelInteressesEmpresa == null) {

        painelInteressesEmpresa = new InteresseEmpresa(idUsuario);
        
        painelInteressesEmpresa.setAcaoVerPerfil(idProfissional -> {
    mostrarPerfilProfissionalInteresse(idProfissional);
});

        scrollInteressesEmpresa =
                new javax.swing.JScrollPane(painelInteressesEmpresa);

        scrollInteressesEmpresa.setHorizontalScrollBarPolicy(
                javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollInteressesEmpresa.getVerticalScrollBar()
                .setUnitIncrement(20);

        pnlPrincipal.add(scrollInteressesEmpresa);
    }

    int inicioConteudo =
            pnlMenuLateral.getX() + pnlMenuLateral.getWidth();

    scrollInteressesEmpresa.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    scrollInteressesEmpresa.setVisible(true);

    pnlPrincipal.setComponentZOrder(scrollInteressesEmpresa, 0);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
    
    private javax.swing.JScrollPane scrollPerfilProfissionalInteresse;

private void mostrarPerfilProfissionalInteresse(int idProfissional) {

    if (scrollInteressesEmpresa != null) {
        scrollInteressesEmpresa.setVisible(false);
    }

    if (scrollPerfilProfissionalInteresse != null) {
        pnlPrincipal.remove(scrollPerfilProfissionalInteresse);
    }

    VisualizarPerfil_Panel perfil =
            new VisualizarPerfil_Panel(idProfissional);

    perfil.configurarVisualizacaoEmpresa(() -> {

        pnlPrincipal.remove(scrollPerfilProfissionalInteresse);
        scrollPerfilProfissionalInteresse = null;

        mostrarPainelInteressesEmpresa();
    });

    scrollPerfilProfissionalInteresse =
            new javax.swing.JScrollPane(perfil);

    scrollPerfilProfissionalInteresse.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollPerfilProfissionalInteresse.getVerticalScrollBar()
            .setUnitIncrement(20);

    int inicioConteudo =
            pnlMenuLateral.getX() + pnlMenuLateral.getWidth();

    scrollPerfilProfissionalInteresse.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    pnlPrincipal.add(scrollPerfilProfissionalInteresse);

    pnlPrincipal.setComponentZOrder(
            scrollPerfilProfissionalInteresse, 0
    );

    scrollPerfilProfissionalInteresse.setVisible(true);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
    

private void mostrarPerfilProfissionalMatches(int idProfissional) {

    // Oculta a aba Matches da empresa
    if (scrollMatchesEmpresa != null) {
        scrollMatchesEmpresa.setVisible(false);
    }

    // Remove uma visualização anterior, se existir
    if (scrollPerfilProfissionalInteresse != null) {
        pnlPrincipal.remove(scrollPerfilProfissionalInteresse);
        scrollPerfilProfissionalInteresse = null;
    }

    // Cria o perfil do profissional selecionado
    VisualizarPerfil_Panel perfil =
            new VisualizarPerfil_Panel(idProfissional);

    // Configura o botão Voltar
    perfil.configurarVisualizacaoEmpresa(() -> {

    pnlPrincipal.remove(scrollPerfilProfissionalInteresse);
    scrollPerfilProfissionalInteresse = null;

    mostrarPainelMatchesEmpresa();

}, "Matches");

    // Cria a área de rolagem
    scrollPerfilProfissionalInteresse =
            new javax.swing.JScrollPane(perfil);

    scrollPerfilProfissionalInteresse.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollPerfilProfissionalInteresse.getVerticalScrollBar()
            .setUnitIncrement(20);

    // Posiciona ao lado do menu lateral
    int inicioConteudo =
            pnlMenuLateral.getX() + pnlMenuLateral.getWidth();

    scrollPerfilProfissionalInteresse.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    pnlPrincipal.add(scrollPerfilProfissionalInteresse);

    pnlPrincipal.setComponentZOrder(
            scrollPerfilProfissionalInteresse, 0
    );

    scrollPerfilProfissionalInteresse.setVisible(true);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}


    private void mostrarPainelMatches() {
        
        

    if (scrollVagas != null) {
        scrollVagas.setVisible(false);
    }

    if (scrollDetalhesVaga != null) {
        scrollDetalhesVaga.setVisible(false);
    }

    if (scrollPerfil != null) {
        scrollPerfil.setVisible(false);
    }

    if (scrollPerfilEmpresa != null) {
        scrollPerfilEmpresa.setVisible(false);
    }

    if (scrollPublicarVaga != null) {
        scrollPublicarVaga.setVisible(false);
    }

    if (scrollInteresses != null) {
        scrollInteresses.setVisible(false);
    }

    if (painelMatches == null) {

        painelMatches = new Matchsprofissional(idUsuario);
        
        painelMatches.setAcaoVerPerfilEmpresa(idEmpresa -> {
    mostrarPerfilEmpresaMatch(idEmpresa);
});

        scrollMatches = new javax.swing.JScrollPane(painelMatches);

        scrollMatches.setHorizontalScrollBarPolicy(
                javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollMatches.getVerticalScrollBar().setUnitIncrement(20);

        pnlPrincipal.add(scrollMatches);
    }
    painelMatches.atualizarDados();

    int inicioConteudo = pnlMenuLateral.getX()
            + pnlMenuLateral.getWidth();

    scrollMatches.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    scrollMatches.setVisible(true);
    pnlPrincipal.setComponentZOrder(scrollMatches, 0);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
    
private void mostrarPerfilEmpresaMatch(int idEmpresa) {
    
    if (scrollPerfilEmpresaMatch != null) {
    pnlPrincipal.remove(scrollPerfilEmpresaMatch);
    scrollPerfilEmpresaMatch = null;
}

    // Oculta a tela de Matches
    if (scrollMatches != null) {
        scrollMatches.setVisible(false);
    }

    // Oculta outras telas
    if (scrollVagas != null) {
        scrollVagas.setVisible(false);
    }

    if (scrollInteresses != null) {
        scrollInteresses.setVisible(false);
    }

    if (scrollDetalhesVaga != null) {
        scrollDetalhesVaga.setVisible(false);
    }

    if (scrollPerfil != null) {
        scrollPerfil.setVisible(false);
    }

    if (scrollPerfilEmpresa != null) {
        scrollPerfilEmpresa.setVisible(false);
    }

    // Cria o perfil da empresa selecionada
    VisualizarPerfilEmpresa perfilEmpresa =
            new VisualizarPerfilEmpresa(idUsuario);
    

    perfilEmpresa.visualizarOutraEmpresa(idEmpresa);

scrollPerfilEmpresaMatch =
        new javax.swing.JScrollPane(perfilEmpresa);

javax.swing.JScrollPane scrollPerfilMatch =
        scrollPerfilEmpresaMatch;

perfilEmpresa.setAcaoVoltarMatches(() -> {
    pnlPrincipal.remove(scrollPerfilMatch);
    mostrarPainelMatches();
});

    scrollPerfilMatch.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollPerfilMatch.getVerticalScrollBar().setUnitIncrement(20);

    int inicioConteudo = pnlMenuLateral.getX()
            + pnlMenuLateral.getWidth();

    scrollPerfilMatch.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    pnlPrincipal.add(scrollPerfilMatch);
    pnlPrincipal.setComponentZOrder(scrollPerfilMatch, 0);

    scrollPerfilMatch.setVisible(true);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}

    
    private void mostrarPainelVagas() {
        
        if (scrollInteressesEmpresa != null) {
    scrollInteressesEmpresa.setVisible(false);
}
        
        if (scrollMatches != null) {
    scrollMatches.setVisible(false);
}
        
        if (scrollInteresses != null) {
    scrollInteresses.setVisible(false);
}
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
    detalhesVeioDeInteresses = false;
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
    private void mostrarPainelInteresses() {
    
    if (scrollMatches != null) {
    scrollMatches.setVisible(false);
}
    
    if (scrollVagas != null) {
        scrollVagas.setVisible(false);
    }

    if (scrollDetalhesVaga != null) {
        scrollDetalhesVaga.setVisible(false);
    }

    if (scrollPerfil != null) {
        scrollPerfil.setVisible(false);
    }

    if (scrollPerfilEmpresa != null) {
        scrollPerfilEmpresa.setVisible(false);
    }

    if (scrollPublicarVaga != null) {
        scrollPublicarVaga.setVisible(false);
    }

    if (painelInteresses == null) {

        painelInteresses = new InteresseProfissional1(idUsuario);
painelInteresses.setAcaoVerDetalhes(idVaga -> {
    detalhesVeioDeInteresses = true;
    mostrarDetalhesVaga(idVaga);
});

        scrollInteresses =
                new javax.swing.JScrollPane(painelInteresses);

        scrollInteresses.setHorizontalScrollBarPolicy(
                javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollInteresses.getVerticalScrollBar().setUnitIncrement(20);

        pnlPrincipal.add(scrollInteresses);
    }
    painelInteresses.atualizarVagasCurtidas();
    

    int inicioConteudo = pnlMenuLateral.getX()
            + pnlMenuLateral.getWidth();

    scrollInteresses.setBounds(
            inicioConteudo,
            0,
            pnlPrincipal.getWidth() - inicioConteudo,
            pnlPrincipal.getHeight()
    );

    scrollInteresses.setVisible(true);
    pnlPrincipal.setComponentZOrder(scrollInteresses, 0);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
    
    public Menu_Frame() {
        initComponents();
        setLocationRelativeTo(null);
    }
    private void carregarFotoInicio() {

    String sql = "SELECT foto_perfil FROM usuarios WHERE id = ?";

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/conexoo",
                    "root",
                    "123456"
            );

         java.sql.PreparedStatement pst =
                 conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                byte[] foto = rs.getBytes("foto_perfil");
                if (foto == null || foto.length == 0) {
    lblFotoInicio.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource( "/com/data/CONEXOO/CONEXOO/perfil_padrao.png")
        )
    );
    lblFotoInicio.setText("");
    return;
}

if (foto == null || foto.length == 0) {

    java.net.URL caminho = getClass().getResource(
        "/com/data/CONEXOO/CONEXOO/perfil_padrao.png"
    );

    if (caminho != null) {

        javax.swing.ImageIcon imagem =
                new javax.swing.ImageIcon(caminho);

        java.awt.Image imagemRedimensionada =
                imagem.getImage().getScaledInstance(
                    70, 70,
                    java.awt.Image.SCALE_SMOOTH
                );

        lblFotoInicio.setIcon(
            new javax.swing.ImageIcon(imagemRedimensionada)
        );

        lblFotoInicio.setText("");

    } else {
        System.out.println("ERRO: perfil_padrao.png não encontrado!");
    }

    return;
} else {

    javax.swing.ImageIcon imagem =
            new javax.swing.ImageIcon(foto);

    java.awt.Image imagemRedimensionada =
            imagem.getImage().getScaledInstance(
                    70, 70,
                    java.awt.Image.SCALE_SMOOTH
            );

    lblFotoInicio.setIcon(
            new javax.swing.ImageIcon(imagemRedimensionada)
    );

    lblFotoInicio.setText("");
}
            }
        }

    } catch (java.sql.SQLException ex) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar foto da página inicial: "
                        + ex.getMessage()
        );
    }
}

    public Menu_Frame(int idUsuario, String tipoUsuario) {
        initComponents();
        selecionarBotao(btnInicio1);
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
        carregarFotoInicio();
if ("PROFISSIONAL".equalsIgnoreCase(tipoUsuario)) {

    carregarEmpresasPlataforma();
    carregarResumoProfissional();

} else if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {

    
    lblvagasdisponiveis3.setText("Vagas publicadas");

    lblQuantidadeVagas1.setText("0");
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
        
if (scrollPerfilEmpresaMatch != null) {
    pnlPrincipal.remove(scrollPerfilEmpresaMatch);
    scrollPerfilEmpresaMatch = null;
}
        
        if (scrollMatches != null) {
    scrollMatches.setVisible(false);
}
        
        if (scrollInteresses != null) {
    scrollInteresses.setVisible(false);
}
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
    lblvagasdisponiveis3.setVisible(false);
    Jvertodasvagas1.setVisible(false);
    pnlEmpresasPlataforma.setVisible(false);
    pnlAtividadesRecentes.setVisible(false);
 
}
    private void mostrarPaginaInicial() {
        
        if (scrollMatchesEmpresa != null) {
    scrollMatchesEmpresa.setVisible(false);
}
        
        if (scrollInteressesEmpresa != null) {
    scrollInteressesEmpresa.setVisible(false);
}
        
        if (scrollMatches != null) {
    scrollMatches.setVisible(false);
}
        
        if (scrollInteresses != null) {
    scrollInteresses.setVisible(false);
}

    pnlBoasVindas.setVisible(true);
    pnlBanner.setVisible(true);
    pnlVagasRecomendadas1.setVisible(true);
    lblvagasdisponiveis3.setVisible(true);
    Jvertodasvagas1.setVisible(true);
    pnlEmpresasPlataforma.setVisible(true);
    pnlAtividadesRecentes.setVisible(true);

    
    System.out.println("===== IDENTIFICANDO SCROLLPANES =====");

for (java.awt.Component c : pnlPrincipal.getComponents()) {

    if (c instanceof javax.swing.JScrollPane) {

        javax.swing.JScrollPane scroll =
                (javax.swing.JScrollPane) c;

        java.awt.Component conteudo =
                scroll.getViewport().getView();

        System.out.println(
            "Scroll: " + scroll
            + " | Conteúdo: " + conteudo.getClass().getName()
            + " | Visível: " + scroll.isVisible()
            + " | É perfil empresa match: "
            + (scroll == scrollPerfilEmpresaMatch)
        );
    }
}
    
    System.out.println("===== COMPONENTES DO PN LPRINCIPAL =====");

for (java.awt.Component componente : pnlPrincipal.getComponents()) {
    System.out.println(
        componente.getClass().getSimpleName()
        + " | Nome: " + componente.getName()
        + " | Visível: " + componente.isVisible()
        + " | Posição: " + componente.getBounds()
    );
}
}
    public void voltarParaInicio() {

    if (scrollVagas != null) scrollVagas.setVisible(false);
    if (scrollVagasEmpresa != null) scrollVagasEmpresa.setVisible(false);
    if (scrollPerfil != null) scrollPerfil.setVisible(false);
    if (scrollPerfilEmpresa != null) scrollPerfilEmpresa.setVisible(false);
    if (scrollPublicarVaga != null) scrollPublicarVaga.setVisible(false);
    if (scrollInteresses != null) scrollInteresses.setVisible(false);
    if (scrollMatches != null) scrollMatches.setVisible(false);
if (scrollPerfilEmpresaMatch != null) {
    pnlPrincipal.remove(scrollPerfilEmpresaMatch);
    scrollPerfilEmpresaMatch = null;
}
    if (scrollDetalhesVaga != null) scrollDetalhesVaga.setVisible(false);
    if (scrollInteressesEmpresa != null)scrollInteressesEmpresa.setVisible(false);
    if (scrollPerfilProfissionalInteresse != null) {scrollPerfilProfissionalInteresse.setVisible(false);
    if (scrollMatchesEmpresa != null) {scrollMatchesEmpresa.setVisible(false);
}
}

mostrarPaginaInicial();

pnlPrincipal.revalidate();
pnlPrincipal.repaint();

javax.swing.SwingUtilities.invokeLater(() -> {
    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
});


}
    

private void mostrarPainelVagasEmpresa() {
    
    if (scrollPerfilProfissionalInteresse != null) {
    scrollPerfilProfissionalInteresse.setVisible(false);
}

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
    if (scrollInteresses != null) {
    scrollInteresses.setVisible(false);
}
    if (scrollDetalhesVaga != null) {
        pnlPrincipal.remove(scrollDetalhesVaga);
    }

    DetalhesVaga_Panel painelDetalhes =
            new DetalhesVaga_Panel(idVaga, idUsuario);
    painelDetalhes.definirTextoVoltar(detalhesVeioDeInteresses);
    
painelDetalhes.setAcaoVoltarVagas(() -> {
    scrollDetalhesVaga.setVisible(false);

    if (detalhesVeioDeInteresses) {
        mostrarPainelInteresses();
    } else {
        mostrarPainelVagas();
    }
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

private void selecionarBotao(javax.swing.JButton botaoSelecionado) {

    java.awt.Color corNormal = new java.awt.Color(11, 29, 56);
    java.awt.Color corSelecionada = new java.awt.Color(8, 127, 167);

    java.awt.Color textoNormal = new java.awt.Color(168, 201, 235);
    java.awt.Color textoSelecionado = java.awt.Color.WHITE;

    javax.swing.JButton[] botoes = {
        btnInicio1,
        btnMeuPerfil,
        btnVagas2,
        btnInteresses,
        btnMatches1
    };

    for (javax.swing.JButton botao : botoes) {

        boolean selecionado = botao == botaoSelecionado;

        botao.setBackground(
            selecionado ? corSelecionada : corNormal
        );

        botao.setForeground(
            selecionado ? textoSelecionado : textoNormal
        );

        botao.setOpaque(true);
        botao.repaint();
    }
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
        lblFotoInicio = new javax.swing.JLabel();
        pnlBanner = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricaoBanner = new javax.swing.JTextArea();
        lblTituloBanner1 = new javax.swing.JLabel();
        pnlVagasRecomendadas1 = new javax.swing.JPanel();
        lblQuantidadeVagas1 = new javax.swing.JLabel();
        lblvagasdisponiveis3 = new javax.swing.JLabel();
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

        pnlBoasVindas.setBackground(new java.awt.Color(11, 37, 69));
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
                .addComponent(lblFotoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(pnlBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAreaatuacao, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBoasVindasLayout.setVerticalGroup(
            pnlBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoasVindasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAreaatuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(lblFotoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        lblvagasdisponiveis3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblvagasdisponiveis3.setForeground(new java.awt.Color(168, 201, 235));
        lblvagasdisponiveis3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblvagasdisponiveis3.setText("Vagas disponíveis");

        javax.swing.GroupLayout pnlVagasRecomendadas1Layout = new javax.swing.GroupLayout(pnlVagasRecomendadas1);
        pnlVagasRecomendadas1.setLayout(pnlVagasRecomendadas1Layout);
        pnlVagasRecomendadas1Layout.setHorizontalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblvagasdisponiveis3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblQuantidadeVagas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlVagasRecomendadas1Layout.setVerticalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblvagasdisponiveis3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblQuantidadeVagas1)
                .addContainerGap(11, Short.MAX_VALUE))
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
                .addGroup(pnlEmpresasPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlListaEmpresas, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEmpresasPlataformaLayout.createSequentialGroup()
                        .addComponent(lblEmpresasPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Jvertodasvagas1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEmpresasPlataformaLayout.setVerticalGroup(
            pnlEmpresasPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpresasPlataformaLayout.createSequentialGroup()
                .addGroup(pnlEmpresasPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpresasPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jvertodasvagas1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlListaEmpresas, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
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
                        .addGap(0, 276, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAtividadesRecentesLayout.createSequentialGroup()
                        .addGroup(pnlAtividadesRecentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlListaAtividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlAtividadesRecentesLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnVerPerfilCompleto)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlEmpresasPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlAtividadesRecentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE)
                            .addComponent(pnlBanner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE)
                            .addComponent(pnlBoasVindas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlMenuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(pnlBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
     
     selecionarBotao(btnMeuPerfil);
     
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

perfil.setMenuOrigem(this);

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
    
        
    selecionarBotao(btnInicio1);    
    ocultarPainelPerfil();

    voltarParaInicio();

    if (tipoUsuario.equalsIgnoreCase("EMPRESA")) {
        carregarQuantidadeVagasEmpresa();
        carregarMinhasVagasInicio();
    } else {
        carregarQuantidadeVagas();
    }
    }//GEN-LAST:event_btnInicio1ActionPerformed

    private void btnMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatchesActionPerformed
        Login_Frame login = new Login_Frame();
login.setLocationRelativeTo(null);
login.setVisible(true);

this.dispose();
    }//GEN-LAST:event_btnMatchesActionPerformed

    private void btnVagas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVagas2ActionPerformed
        selecionarBotao(btnVagas2);
        mostrarPainelVagas();
        ocultarPaginaInicial();
    }//GEN-LAST:event_btnVagas2ActionPerformed

    private void btnInteressesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInteressesActionPerformed
selecionarBotao(btnInteresses);
        if ("empresa".equalsIgnoreCase(tipoUsuario)) {
    mostrarPainelInteressesEmpresa();
} else {
    mostrarPainelInteresses();
}
 ocultarPaginaInicial();
    }//GEN-LAST:event_btnInteressesActionPerformed

    private void btnMatches1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatches1ActionPerformed
        selecionarBotao(btnMatches1); 
        ocultarPaginaInicial();

    if ("EMPRESA".equalsIgnoreCase(tipoUsuario)) {
        mostrarPainelMatchesEmpresa();
    } else if ("PROFISSIONAL".equalsIgnoreCase(tipoUsuario)) {
        mostrarPainelMatches();
    }
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
    private javax.swing.JLabel lblEmpresasPlataforma;
    private javax.swing.JLabel lblFotoInicio;
    private javax.swing.JLabel lblQuantidadeVagas1;
    private javax.swing.JLabel lblTituloBanner1;
    private javax.swing.JLabel lblvagasdisponiveis3;
    private javax.swing.JPanel pnlAtividadesRecentes;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlBoasVindas;
    private javax.swing.JPanel pnlEmpresasPlataforma;
    private javax.swing.JPanel pnlListaAtividades;
    private javax.swing.JPanel pnlListaEmpresas;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlVagasRecomendadas1;
    private javax.swing.JTextArea txtDescricaoBanner;
    // End of variables declaration//GEN-END:variables
}
