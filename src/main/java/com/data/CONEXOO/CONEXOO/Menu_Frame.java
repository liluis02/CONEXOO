/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

public class Menu_Frame extends javax.swing.JFrame {

    private VisualizarPerfil_Panel painelPerfil;
    private javax.swing.JScrollPane scrollPerfil;
    private VisualizarPerfilEmpresa painelPerfilEmpresa;
    private javax.swing.JScrollPane scrollPerfilEmpresa;
    private int idUsuario;
    private String tipoUsuario;

    private void mostrarPainelPerfilEmpresa() {

    if (painelPerfilEmpresa == null) {
        painelPerfilEmpresa = new VisualizarPerfilEmpresa(idUsuario);

        scrollPerfilEmpresa = new javax.swing.JScrollPane(painelPerfilEmpresa);
        scrollPerfilEmpresa.getVerticalScrollBar().setUnitIncrement(20);

        pnlPrincipal.add(scrollPerfilEmpresa);
    }

    scrollPerfilEmpresa.setBounds(
        pnlMenuLateral.getX() + pnlMenuLateral.getWidth() + 20,
        0,
        pnlPrincipal.getWidth() - pnlMenuLateral.getWidth() - 40,
        pnlPrincipal.getHeight()
    );

    scrollPerfilEmpresa.setVisible(true);

    pnlPrincipal.revalidate();
    pnlPrincipal.repaint();
}
    
    public Menu_Frame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public Menu_Frame(int idUsuario, String tipoUsuario) {
        initComponents();

        getContentPane().setBackground(new java.awt.Color(5, 20, 43));
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;

        carregarNomeUsuario();
    }
private void ocultarPainelPerfil() {
    if (scrollPerfil != null) {
        scrollPerfil.setVisible(false);
    }
} 
    private void mostrarPainelPerfil() {

    if (painelPerfil == null) {
        painelPerfil = new VisualizarPerfil_Panel(idUsuario);

scrollPerfil = new javax.swing.JScrollPane(painelPerfil);
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

    String sql = "SELECT nome, area_atuacao FROM usuarios WHERE id = ?";

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
            );
         java.sql.PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {

    String nome = rs.getString("nome");
    String areaAtuacao = rs.getString("area_atuacao");

    lblBoasVindas.setText("Olá, " + nome + "!");
    lblAreaatuacao.setText("Área de atuação: " + areaAtuacao);
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
    pnlInteressesEnviados.setVisible(false);
    pnlVagasRecomendadas1.setVisible(false);
    pnlMatchesRealizados.setVisible(false);
    pnlCompatibilidadeMedia.setVisible(false);
    lblVagasRecomendadas.setVisible(false);
    lblVerTodasVagas.setVisible(false);
    pnlVaga1.setVisible(false);
    pnlVaga2.setVisible(false);
    pnlVaga3.setVisible(false);
}
    private void mostrarPaginaInicial() {

    pnlBoasVindas.setVisible(true);
    pnlBanner.setVisible(true);
    pnlInteressesEnviados.setVisible(true);
    pnlVagasRecomendadas1.setVisible(true);
    pnlMatchesRealizados.setVisible(true);
    pnlCompatibilidadeMedia.setVisible(true);
    lblVagasRecomendadas.setVisible(true);
    lblVerTodasVagas.setVisible(true);

    pnlVaga1.setVisible(true);
    pnlVaga2.setVisible(true);
    pnlVaga3.setVisible(true);
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
        lblTituloBanner = new javax.swing.JLabel();
        lblSubtituloBanner = new javax.swing.JLabel();
        pnlInteressesEnviados = new javax.swing.JPanel();
        lblQuantidadeVagas = new javax.swing.JLabel();
        lblDescricaoVagas = new javax.swing.JLabel();
        pnlVagasRecomendadas1 = new javax.swing.JPanel();
        lblQuantidadeVagas1 = new javax.swing.JLabel();
        lblDescricaoVagas1 = new javax.swing.JLabel();
        pnlCompatibilidadeMedia = new javax.swing.JPanel();
        lblQuantidadeVagas3 = new javax.swing.JLabel();
        lblDescricaoVagas3 = new javax.swing.JLabel();
        lblVagasRecomendadas = new javax.swing.JLabel();
        lblVerTodasVagas = new javax.swing.JLabel();
        pnlVaga1 = new javax.swing.JPanel();
        lblEmpresaVaga1 = new javax.swing.JLabel();
        lblCargoVaga1 = new javax.swing.JLabel();
        lblLocalVaga1 = new javax.swing.JLabel();
        lblHabilidadesVaga1 = new javax.swing.JLabel();
        lblCompatibilidadeVaga1 = new javax.swing.JLabel();
        btnInteresseVaga1 = new javax.swing.JButton();
        pnlVaga2 = new javax.swing.JPanel();
        lblEmpresaVaga2 = new javax.swing.JLabel();
        lblCargoVaga2 = new javax.swing.JLabel();
        lblLocalVaga2 = new javax.swing.JLabel();
        lblHabilidadesVaga2 = new javax.swing.JLabel();
        lblCompatibilidadeVaga2 = new javax.swing.JLabel();
        btnInteresseVaga2 = new javax.swing.JButton();
        pnlVaga3 = new javax.swing.JPanel();
        lblEmpresaVaga3 = new javax.swing.JLabel();
        lblCargoVaga3 = new javax.swing.JLabel();
        lblLocalVaga3 = new javax.swing.JLabel();
        lblHabilidadesVaga3 = new javax.swing.JLabel();
        lblCompatibilidadeVaga3 = new javax.swing.JLabel();
        btnInteresseVaga3 = new javax.swing.JButton();
        pnlMatchesRealizados = new javax.swing.JPanel();
        lblQuantidadeVagas2 = new javax.swing.JLabel();
        lblDescricaoVagas2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONEXOO | Início");
        setBackground(new java.awt.Color(7, 21, 45));
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setPreferredSize(new java.awt.Dimension(1200, 750));

        pnlPrincipal.setBackground(new java.awt.Color(7, 21, 45));
        pnlPrincipal.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(19, 216, 245)));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(1535, 885));

        pnlMenuLateral.setBackground(new java.awt.Color(11, 29, 56));
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
                    .addComponent(btnInicio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMatches, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMatches1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVagas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMeuPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInteresses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlMenuLateralLayout.setVerticalGroup(
            pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVagas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMeuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInteresses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMatches1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMatches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        lblAreaatuacao.setText("Área de atuação: Não informada");
        lblAreaatuacao.setPreferredSize(new java.awt.Dimension(250, 25));

        javax.swing.GroupLayout pnlBoasVindasLayout = new javax.swing.GroupLayout(pnlBoasVindas);
        pnlBoasVindas.setLayout(pnlBoasVindasLayout);
        pnlBoasVindasLayout.setHorizontalGroup(
            pnlBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoasVindasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlBoasVindasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, 1128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAreaatuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
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

        lblTituloBanner.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTituloBanner.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloBanner.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTituloBanner.setText("Encontre vagas que combinam com você!");
        lblTituloBanner.setPreferredSize(new java.awt.Dimension(650, 40));

        lblSubtituloBanner.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSubtituloBanner.setForeground(new java.awt.Color(168, 201, 235));
        lblSubtituloBanner.setText("Descubra oportunidades de trabalho na área de TI");
        lblSubtituloBanner.setPreferredSize(new java.awt.Dimension(650, 30));

        javax.swing.GroupLayout pnlBannerLayout = new javax.swing.GroupLayout(pnlBanner);
        pnlBanner.setLayout(pnlBannerLayout);
        pnlBannerLayout.setHorizontalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubtituloBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(553, Short.MAX_VALUE))
        );
        pnlBannerLayout.setVerticalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTituloBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSubtituloBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pnlInteressesEnviados.setBackground(new java.awt.Color(11, 37, 69));
        pnlInteressesEnviados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlInteressesEnviados.setPreferredSize(new java.awt.Dimension(209, 110));

        lblQuantidadeVagas.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadeVagas.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadeVagas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadeVagas.setText("7");

        lblDescricaoVagas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescricaoVagas.setForeground(new java.awt.Color(168, 201, 235));
        lblDescricaoVagas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescricaoVagas.setText("Interesses enviados");

        javax.swing.GroupLayout pnlInteressesEnviadosLayout = new javax.swing.GroupLayout(pnlInteressesEnviados);
        pnlInteressesEnviados.setLayout(pnlInteressesEnviadosLayout);
        pnlInteressesEnviadosLayout.setHorizontalGroup(
            pnlInteressesEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInteressesEnviadosLayout.createSequentialGroup()
                .addGroup(pnlInteressesEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInteressesEnviadosLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(lblDescricaoVagas))
                    .addGroup(pnlInteressesEnviadosLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblQuantidadeVagas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInteressesEnviadosLayout.setVerticalGroup(
            pnlInteressesEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInteressesEnviadosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblQuantidadeVagas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoVagas)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnlVagasRecomendadas1.setBackground(new java.awt.Color(11, 37, 69));
        pnlVagasRecomendadas1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVagasRecomendadas1.setPreferredSize(new java.awt.Dimension(209, 110));

        lblQuantidadeVagas1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadeVagas1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadeVagas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadeVagas1.setText("24");

        lblDescricaoVagas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescricaoVagas1.setForeground(new java.awt.Color(168, 201, 235));
        lblDescricaoVagas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescricaoVagas1.setText("Vagas recomendadas");

        javax.swing.GroupLayout pnlVagasRecomendadas1Layout = new javax.swing.GroupLayout(pnlVagasRecomendadas1);
        pnlVagasRecomendadas1.setLayout(pnlVagasRecomendadas1Layout);
        pnlVagasRecomendadas1Layout.setHorizontalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(lblQuantidadeVagas1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(lblDescricaoVagas1)
                .addGap(64, 64, 64))
        );
        pnlVagasRecomendadas1Layout.setVerticalGroup(
            pnlVagasRecomendadas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblQuantidadeVagas1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoVagas1)
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
        lblDescricaoVagas3.setText("Compatibilidade média");

        javax.swing.GroupLayout pnlCompatibilidadeMediaLayout = new javax.swing.GroupLayout(pnlCompatibilidadeMedia);
        pnlCompatibilidadeMedia.setLayout(pnlCompatibilidadeMediaLayout);
        pnlCompatibilidadeMediaLayout.setHorizontalGroup(
            pnlCompatibilidadeMediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompatibilidadeMediaLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(pnlCompatibilidadeMediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompatibilidadeMediaLayout.createSequentialGroup()
                        .addComponent(lblDescricaoVagas3)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompatibilidadeMediaLayout.createSequentialGroup()
                        .addComponent(lblQuantidadeVagas3)
                        .addGap(94, 94, 94))))
        );
        pnlCompatibilidadeMediaLayout.setVerticalGroup(
            pnlCompatibilidadeMediaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompatibilidadeMediaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblQuantidadeVagas3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoVagas3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblVagasRecomendadas.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblVagasRecomendadas.setForeground(new java.awt.Color(255, 255, 255));
        lblVagasRecomendadas.setText("Vagas recomendadas para você");
        lblVagasRecomendadas.setPreferredSize(new java.awt.Dimension(450, 40));

        lblVerTodasVagas.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblVerTodasVagas.setForeground(new java.awt.Color(19, 216, 245));
        lblVerTodasVagas.setText("Ver todas →");

        pnlVaga1.setBackground(new java.awt.Color(11, 37, 69));
        pnlVaga1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVaga1.setPreferredSize(new java.awt.Dimension(330, 240));

        lblEmpresaVaga1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblEmpresaVaga1.setForeground(new java.awt.Color(168, 201, 235));
        lblEmpresaVaga1.setText("TechSolutions");

        lblCargoVaga1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        lblCargoVaga1.setForeground(new java.awt.Color(255, 255, 255));
        lblCargoVaga1.setText("Desenvolvedor Java");

        lblLocalVaga1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLocalVaga1.setForeground(new java.awt.Color(168, 201, 235));
        lblLocalVaga1.setText("Bom Despacho/MG - Remoto");

        lblHabilidadesVaga1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblHabilidadesVaga1.setForeground(new java.awt.Color(19, 216, 245));
        lblHabilidadesVaga1.setText("\tJava  •  Spring Boot  •  MySQL");

        lblCompatibilidadeVaga1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblCompatibilidadeVaga1.setForeground(new java.awt.Color(49, 234, 162));
        lblCompatibilidadeVaga1.setText("92%");

        btnInteresseVaga1.setBackground(new java.awt.Color(8, 185, 216));
        btnInteresseVaga1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInteresseVaga1.setForeground(new java.awt.Color(255, 255, 255));
        btnInteresseVaga1.setText("Tenho interesse");
        btnInteresseVaga1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInteresseVaga1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInteresseVaga1.setFocusPainted(false);
        btnInteresseVaga1.setPreferredSize(new java.awt.Dimension(110, 22));
        btnInteresseVaga1.addActionListener(this::btnInteresseVaga1ActionPerformed);

        javax.swing.GroupLayout pnlVaga1Layout = new javax.swing.GroupLayout(pnlVaga1);
        pnlVaga1.setLayout(pnlVaga1Layout);
        pnlVaga1Layout.setHorizontalGroup(
            pnlVaga1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVaga1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlVaga1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVaga1Layout.createSequentialGroup()
                        .addGroup(pnlVaga1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCargoVaga1)
                            .addGroup(pnlVaga1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblHabilidadesVaga1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLocalVaga1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(202, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVaga1Layout.createSequentialGroup()
                        .addGroup(pnlVaga1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnInteresseVaga1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVaga1Layout.createSequentialGroup()
                                .addComponent(lblEmpresaVaga1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCompatibilidadeVaga1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        pnlVaga1Layout.setVerticalGroup(
            pnlVaga1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVaga1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlVaga1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpresaVaga1)
                    .addComponent(lblCompatibilidadeVaga1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCargoVaga1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLocalVaga1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHabilidadesVaga1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInteresseVaga1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlVaga2.setBackground(new java.awt.Color(11, 37, 69));
        pnlVaga2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVaga2.setPreferredSize(new java.awt.Dimension(330, 240));

        lblEmpresaVaga2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblEmpresaVaga2.setForeground(new java.awt.Color(168, 201, 235));
        lblEmpresaVaga2.setText("NextCode");

        lblCargoVaga2.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        lblCargoVaga2.setForeground(new java.awt.Color(255, 255, 255));
        lblCargoVaga2.setText("Analista de Dados");

        lblLocalVaga2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLocalVaga2.setForeground(new java.awt.Color(168, 201, 235));
        lblLocalVaga2.setText("Bom Despacho/MG - Híbrido");

        lblHabilidadesVaga2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblHabilidadesVaga2.setForeground(new java.awt.Color(19, 216, 245));
        lblHabilidadesVaga2.setText("SQL • Python • Power BI");

        lblCompatibilidadeVaga2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblCompatibilidadeVaga2.setForeground(new java.awt.Color(49, 234, 162));
        lblCompatibilidadeVaga2.setText("88%");

        btnInteresseVaga2.setBackground(new java.awt.Color(8, 185, 216));
        btnInteresseVaga2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInteresseVaga2.setForeground(new java.awt.Color(255, 255, 255));
        btnInteresseVaga2.setText("Tenho interesse");
        btnInteresseVaga2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInteresseVaga2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInteresseVaga2.setFocusPainted(false);
        btnInteresseVaga2.setPreferredSize(new java.awt.Dimension(110, 22));
        btnInteresseVaga2.addActionListener(this::btnInteresseVaga2ActionPerformed);

        javax.swing.GroupLayout pnlVaga2Layout = new javax.swing.GroupLayout(pnlVaga2);
        pnlVaga2.setLayout(pnlVaga2Layout);
        pnlVaga2Layout.setHorizontalGroup(
            pnlVaga2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVaga2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlVaga2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVaga2Layout.createSequentialGroup()
                        .addGroup(pnlVaga2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCargoVaga2)
                            .addGroup(pnlVaga2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblHabilidadesVaga2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLocalVaga2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVaga2Layout.createSequentialGroup()
                        .addGroup(pnlVaga2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnInteresseVaga2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVaga2Layout.createSequentialGroup()
                                .addComponent(lblEmpresaVaga2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCompatibilidadeVaga2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        pnlVaga2Layout.setVerticalGroup(
            pnlVaga2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVaga2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlVaga2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpresaVaga2)
                    .addComponent(lblCompatibilidadeVaga2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCargoVaga2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLocalVaga2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHabilidadesVaga2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInteresseVaga2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlVaga3.setBackground(new java.awt.Color(11, 37, 69));
        pnlVaga3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVaga3.setPreferredSize(new java.awt.Dimension(330, 240));

        lblEmpresaVaga3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblEmpresaVaga3.setForeground(new java.awt.Color(168, 201, 235));
        lblEmpresaVaga3.setText("InovaTech");

        lblCargoVaga3.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        lblCargoVaga3.setForeground(new java.awt.Color(255, 255, 255));
        lblCargoVaga3.setText("Desenvolvedor Front-end");

        lblLocalVaga3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLocalVaga3.setForeground(new java.awt.Color(168, 201, 235));
        lblLocalVaga3.setText("Bom Despacho/MG - Presencial");

        lblHabilidadesVaga3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblHabilidadesVaga3.setForeground(new java.awt.Color(19, 216, 245));
        lblHabilidadesVaga3.setText("\tReact • HTML • CSS");

        lblCompatibilidadeVaga3.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblCompatibilidadeVaga3.setForeground(new java.awt.Color(49, 234, 162));
        lblCompatibilidadeVaga3.setText("\t80%");

        btnInteresseVaga3.setBackground(new java.awt.Color(8, 185, 216));
        btnInteresseVaga3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInteresseVaga3.setForeground(new java.awt.Color(255, 255, 255));
        btnInteresseVaga3.setText("Tenho interesse");
        btnInteresseVaga3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInteresseVaga3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInteresseVaga3.setFocusPainted(false);
        btnInteresseVaga3.setPreferredSize(new java.awt.Dimension(110, 22));
        btnInteresseVaga3.addActionListener(this::btnInteresseVaga3ActionPerformed);

        javax.swing.GroupLayout pnlVaga3Layout = new javax.swing.GroupLayout(pnlVaga3);
        pnlVaga3.setLayout(pnlVaga3Layout);
        pnlVaga3Layout.setHorizontalGroup(
            pnlVaga3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVaga3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlVaga3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVaga3Layout.createSequentialGroup()
                        .addGroup(pnlVaga3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCargoVaga3)
                            .addGroup(pnlVaga3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblHabilidadesVaga3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLocalVaga3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(144, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVaga3Layout.createSequentialGroup()
                        .addGroup(pnlVaga3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnInteresseVaga3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlVaga3Layout.createSequentialGroup()
                                .addComponent(lblEmpresaVaga3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCompatibilidadeVaga3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        pnlVaga3Layout.setVerticalGroup(
            pnlVaga3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVaga3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlVaga3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpresaVaga3)
                    .addComponent(lblCompatibilidadeVaga3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCargoVaga3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLocalVaga3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHabilidadesVaga3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInteresseVaga3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(pnlMatchesRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchesRealizadosLayout.createSequentialGroup()
                        .addComponent(lblDescricaoVagas2)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchesRealizadosLayout.createSequentialGroup()
                        .addComponent(lblQuantidadeVagas2)
                        .addGap(134, 134, 134))))
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

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(pnlBanner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlBoasVindas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(lblVagasRecomendadas, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlInteressesEnviados, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlCompatibilidadeMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMatchesRealizados, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVerTodasVagas, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(pnlVaga1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlVaga2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlVaga3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlBoasVindas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(pnlVagasRecomendadas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pnlCompatibilidadeMedia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlMatchesRealizados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(pnlInteressesEnviados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblVagasRecomendadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVerTodasVagas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlVaga2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVaga1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVaga3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 208, Short.MAX_VALUE))
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMeuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeuPerfilActionPerformed

     if (perfilJaCadastrado()) {

        ocultarPaginaInicial();

        if (tipoUsuario.equalsIgnoreCase("PROFISSIONAL")) {

            mostrarPainelPerfil();

        } else if (tipoUsuario.equalsIgnoreCase("EMPRESA")) {

            mostrarPainelPerfilEmpresa();

        }

    } else {

        if (tipoUsuario.equalsIgnoreCase("PROFISSIONAL")) {

            PerfilProfissional_Frame perfil =
                    new PerfilProfissional_Frame(idUsuario);

            perfil.setVisible(true);

        } else if (tipoUsuario.equalsIgnoreCase("EMPRESA")) {

            PerfilEmpresa perfil = new PerfilEmpresa(idUsuario);
            perfil.setVisible(true);
        }

        this.dispose();
    }
    }//GEN-LAST:event_btnMeuPerfilActionPerformed

    private void btnInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicio1ActionPerformed
        ocultarPainelPerfil();

    if (scrollPerfilEmpresa != null) {
        scrollPerfilEmpresa.setVisible(false);
    }

    mostrarPaginaInicial();
    }//GEN-LAST:event_btnInicio1ActionPerformed

    private void btnMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatchesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMatchesActionPerformed

    private void btnVagas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVagas2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVagas2ActionPerformed

    private void btnInteressesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInteressesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInteressesActionPerformed

    private void btnMatches1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatches1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMatches1ActionPerformed

    private void btnInteresseVaga1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInteresseVaga1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInteresseVaga1ActionPerformed

    private void btnInteresseVaga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInteresseVaga2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInteresseVaga2ActionPerformed

    private void btnInteresseVaga3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInteresseVaga3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInteresseVaga3ActionPerformed

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
    private javax.swing.JButton btnInicio1;
    private javax.swing.JButton btnInteresseVaga1;
    private javax.swing.JButton btnInteresseVaga2;
    private javax.swing.JButton btnInteresseVaga3;
    private javax.swing.JButton btnInteresses;
    private javax.swing.JButton btnMatches;
    private javax.swing.JButton btnMatches1;
    private javax.swing.JButton btnMeuPerfil;
    private javax.swing.JButton btnVagas2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAreaatuacao;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JLabel lblCargoVaga1;
    private javax.swing.JLabel lblCargoVaga2;
    private javax.swing.JLabel lblCargoVaga3;
    private javax.swing.JLabel lblCompatibilidadeVaga1;
    private javax.swing.JLabel lblCompatibilidadeVaga2;
    private javax.swing.JLabel lblCompatibilidadeVaga3;
    private javax.swing.JLabel lblDescricaoVagas;
    private javax.swing.JLabel lblDescricaoVagas1;
    private javax.swing.JLabel lblDescricaoVagas2;
    private javax.swing.JLabel lblDescricaoVagas3;
    private javax.swing.JLabel lblEmpresaVaga1;
    private javax.swing.JLabel lblEmpresaVaga2;
    private javax.swing.JLabel lblEmpresaVaga3;
    private javax.swing.JLabel lblHabilidadesVaga1;
    private javax.swing.JLabel lblHabilidadesVaga2;
    private javax.swing.JLabel lblHabilidadesVaga3;
    private javax.swing.JLabel lblLocalVaga1;
    private javax.swing.JLabel lblLocalVaga2;
    private javax.swing.JLabel lblLocalVaga3;
    private javax.swing.JLabel lblQuantidadeVagas;
    private javax.swing.JLabel lblQuantidadeVagas1;
    private javax.swing.JLabel lblQuantidadeVagas2;
    private javax.swing.JLabel lblQuantidadeVagas3;
    private javax.swing.JLabel lblSubtituloBanner;
    private javax.swing.JLabel lblTituloBanner;
    private javax.swing.JLabel lblVagasRecomendadas;
    private javax.swing.JLabel lblVerTodasVagas;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlBoasVindas;
    private javax.swing.JPanel pnlCompatibilidadeMedia;
    private javax.swing.JPanel pnlInteressesEnviados;
    private javax.swing.JPanel pnlMatchesRealizados;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlVaga1;
    private javax.swing.JPanel pnlVaga2;
    private javax.swing.JPanel pnlVaga3;
    private javax.swing.JPanel pnlVagasRecomendadas1;
    // End of variables declaration//GEN-END:variables
}
