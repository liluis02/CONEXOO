/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class VisualizarPerfil_Panel extends javax.swing.JPanel {
       private int idUsuario;
    /**
     * Creates new form VisualizarPerfil_Panel
     */
    public VisualizarPerfil_Panel() {
        initComponents();
    }
    public VisualizarPerfil_Panel(int idUsuario) {
    this();
    this.idUsuario = idUsuario;
    carregarDadosPerfil();
    carregarHabilidades();
    carregarExperiencias();
}
    
private void carregarDadosPerfil() {

    String sql = """
        SELECT u.nome, u.area_atuacao,
               c.cargo_desejado, c.cidade,
               c.estado, c.telefone,
               c.nivel_profissional, c.descricao
        FROM usuarios u
        INNER JOIN candidatos c
            ON u.id = c.id_usuario
        WHERE u.id = ?
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456");

         java.sql.PreparedStatement pst =
            conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                lblNomeProfissional.setText(rs.getString("nome"));
                lblCargoDesejado.setText(rs.getString("cargo_desejado"));
                lblAreaAtuacaoPerfil.setText(rs.getString("area_atuacao"));

                lblCidadePerfil.setText(rs.getString("cidade"));
                lblEstadoPerfil.setText(rs.getString("estado"));
                lblTelefonePerfil.setText(rs.getString("telefone"));
                lblNivelProfissional.setText(rs.getString("nivel_profissional"));

                txtDescricaoPerfil.setText(rs.getString("descricao"));

            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Perfil profissional não encontrado!"
                );
            }
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar perfil: " + e.getMessage()
        );
    }
}
       private void carregarHabilidades() {

    javax.swing.DefaultListModel<String> modelo =
            new javax.swing.DefaultListModel<>();

    String sql = """
        SELECT h.nome, hc.nivel
        FROM habilidades_candidato hc
        INNER JOIN habilidades h
            ON hc.id_habilidade = h.id_habilidade
        INNER JOIN candidatos c
            ON hc.id_candidato = c.id_candidato
        WHERE c.id_usuario = ?
        ORDER BY h.nome
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456");

         java.sql.PreparedStatement pst =
            conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                String habilidade = rs.getString("nome");
                String nivel = rs.getString("nivel");

                modelo.addElement(habilidade + " — " + nivel);
            }
        }

        lstHabilidadesPerfil.setModel(modelo);

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar habilidades: " + e.getMessage()
        );
    }
       }
       private void carregarExperiencias() {
    javax.swing.DefaultListModel<String> modelo =
            new javax.swing.DefaultListModel<>();

    String sql = """
        SELECT cargo, nome_empresa,
               data_contratacao, data_demissao
        FROM experiencias
        WHERE id_candidato = (
            SELECT id_candidato
            FROM candidatos
            WHERE id_usuario = ?
        )
        ORDER BY data_contratacao DESC
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456");

         java.sql.PreparedStatement pst =
            conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            java.time.format.DateTimeFormatter formato =
                    java.time.format.DateTimeFormatter.ofPattern("MM/yyyy");

            while (rs.next()) {

                String cargo = rs.getString("cargo");
                String empresa = rs.getString("nome_empresa");

                java.sql.Date dataInicio =
                        rs.getDate("data_contratacao");

                java.sql.Date dataFim =
                        rs.getDate("data_demissao");

                String inicio = dataInicio.toLocalDate().format(formato);

                String fim = (dataFim == null)
                        ? "Atual"
                        : dataFim.toLocalDate().format(formato);

                String experiencia = cargo + " — " + empresa
                        + " (" + inicio + " - " + fim + ")";

                modelo.addElement(experiencia);
            }
        }

        lstExperienciasPerfil.setModel(modelo);

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar experiências: " + e.getMessage()
        );
    }

      

}

       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloPerfil = new javax.swing.JLabel();
        lblSubtituloPerfil = new javax.swing.JLabel();
        pnlIdentificacao = new javax.swing.JPanel();
        lblNomeProfissional = new javax.swing.JLabel();
        lblCargoDesejado = new javax.swing.JLabel();
        lblAreaAtuacaoPerfil = new javax.swing.JLabel();
        lblCargodesejado2 = new javax.swing.JLabel();
        pnlInformacoesPessoais = new javax.swing.JPanel();
        lblTituloInformacoesPessoais = new javax.swing.JLabel();
        lblTituloCidade = new javax.swing.JLabel();
        lblCidadePerfil = new javax.swing.JLabel();
        lblTituloEstado = new javax.swing.JLabel();
        lblEstadoPerfil = new javax.swing.JLabel();
        lblTituloTelefone = new javax.swing.JLabel();
        lblTelefonePerfil = new javax.swing.JLabel();
        lblTituloNivelProfissional = new javax.swing.JLabel();
        lblNivelProfissional = new javax.swing.JLabel();
        pnlSobreMim = new javax.swing.JPanel();
        lblTituloSobreMim = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricaoPerfil = new javax.swing.JTextArea();
        pnlHabilidades = new javax.swing.JPanel();
        lblTituloHabilidades = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstHabilidadesPerfil = new javax.swing.JList<>();
        pnlExperiencias = new javax.swing.JPanel();
        lblTituloExperiencias = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstExperienciasPerfil = new javax.swing.JList<>();
        btnEditarPerfil = new javax.swing.JButton();

        setBackground(new java.awt.Color(7, 27, 51));
        setPreferredSize(new java.awt.Dimension(730, 1000));

        lblTituloPerfil.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTituloPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPerfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTituloPerfil.setText("Meu Perfil");

        lblSubtituloPerfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubtituloPerfil.setForeground(new java.awt.Color(159, 185, 205));
        lblSubtituloPerfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSubtituloPerfil.setText("Visualize todas as suas informações pessoais");

        pnlIdentificacao.setBackground(new java.awt.Color(16, 45, 75));
        pnlIdentificacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblNomeProfissional.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblNomeProfissional.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeProfissional.setText("Nome do profissional");

        lblCargoDesejado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCargoDesejado.setForeground(new java.awt.Color(255, 255, 255));
        lblCargoDesejado.setText("Cargo desejado");

        lblAreaAtuacaoPerfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAreaAtuacaoPerfil.setForeground(new java.awt.Color(19, 220, 233));
        lblAreaAtuacaoPerfil.setText("Área de atuação");

        lblCargodesejado2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblCargodesejado2.setForeground(new java.awt.Color(145, 180, 201));
        lblCargodesejado2.setText("Cargo desejado");

        javax.swing.GroupLayout pnlIdentificacaoLayout = new javax.swing.GroupLayout(pnlIdentificacao);
        pnlIdentificacao.setLayout(pnlIdentificacaoLayout);
        pnlIdentificacaoLayout.setHorizontalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeProfissional, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                        .addComponent(lblCargodesejado2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblCargoDesejado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAreaAtuacaoPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlIdentificacaoLayout.setVerticalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeProfissional)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCargodesejado2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCargoDesejado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAreaAtuacaoPerfil)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlInformacoesPessoais.setBackground(new java.awt.Color(16, 45, 75));
        pnlInformacoesPessoais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblTituloInformacoesPessoais.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloInformacoesPessoais.setForeground(new java.awt.Color(19, 220, 233));
        lblTituloInformacoesPessoais.setText("Informações pessoais");

        lblTituloCidade.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTituloCidade.setForeground(new java.awt.Color(145, 180, 201));
        lblTituloCidade.setText("Cidade");

        lblCidadePerfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCidadePerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblCidadePerfil.setText("Cidade do profissional");

        lblTituloEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTituloEstado.setForeground(new java.awt.Color(145, 180, 201));
        lblTituloEstado.setText("Estado");

        lblEstadoPerfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEstadoPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblEstadoPerfil.setText("Estado do profissional");

        lblTituloTelefone.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTituloTelefone.setForeground(new java.awt.Color(145, 180, 201));
        lblTituloTelefone.setText("Telefone");

        lblTelefonePerfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTelefonePerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefonePerfil.setText("(00) 00000-0000");

        lblTituloNivelProfissional.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTituloNivelProfissional.setForeground(new java.awt.Color(145, 180, 201));
        lblTituloNivelProfissional.setText("\tNível profissional");

        lblNivelProfissional.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNivelProfissional.setForeground(new java.awt.Color(255, 255, 255));
        lblNivelProfissional.setText("Nível do profissional");

        javax.swing.GroupLayout pnlInformacoesPessoaisLayout = new javax.swing.GroupLayout(pnlInformacoesPessoais);
        pnlInformacoesPessoais.setLayout(pnlInformacoesPessoaisLayout);
        pnlInformacoesPessoaisLayout.setHorizontalGroup(
            pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloInformacoesPessoais)
                    .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloCidade)
                            .addComponent(lblCidadePerfil)
                            .addComponent(lblTituloTelefone)
                            .addComponent(lblTelefonePerfil))
                        .addGap(82, 82, 82)
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNivelProfissional)
                            .addComponent(lblTituloNivelProfissional)
                            .addComponent(lblEstadoPerfil)
                            .addComponent(lblTituloEstado))))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        pnlInformacoesPessoaisLayout.setVerticalGroup(
            pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloInformacoesPessoais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloEstado)
                            .addComponent(lblTituloCidade, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstadoPerfil)
                            .addComponent(lblCidadePerfil))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTituloTelefone))
                    .addComponent(lblTituloNivelProfissional))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNivelProfissional)
                    .addComponent(lblTelefonePerfil))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlSobreMim.setBackground(new java.awt.Color(16, 45, 75));
        pnlSobreMim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblTituloSobreMim.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloSobreMim.setForeground(new java.awt.Color(19, 220, 233));
        lblTituloSobreMim.setText("Sobre mim");

        txtDescricaoPerfil.setEditable(false);
        txtDescricaoPerfil.setBackground(new java.awt.Color(16, 45, 75));
        txtDescricaoPerfil.setColumns(20);
        txtDescricaoPerfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescricaoPerfil.setForeground(new java.awt.Color(213, 229, 240));
        txtDescricaoPerfil.setLineWrap(true);
        txtDescricaoPerfil.setRows(5);
        txtDescricaoPerfil.setText("Descrição do profissional");
        txtDescricaoPerfil.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescricaoPerfil);

        javax.swing.GroupLayout pnlSobreMimLayout = new javax.swing.GroupLayout(pnlSobreMim);
        pnlSobreMim.setLayout(pnlSobreMimLayout);
        pnlSobreMimLayout.setHorizontalGroup(
            pnlSobreMimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSobreMimLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlSobreMimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloSobreMim))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSobreMimLayout.setVerticalGroup(
            pnlSobreMimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSobreMimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloSobreMim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlHabilidades.setBackground(new java.awt.Color(16, 45, 75));
        pnlHabilidades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblTituloHabilidades.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloHabilidades.setForeground(new java.awt.Color(19, 220, 233));
        lblTituloHabilidades.setText("Habilidades profissionais");

        lstHabilidadesPerfil.setBackground(new java.awt.Color(16, 45, 75));
        lstHabilidadesPerfil.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lstHabilidadesPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lstHabilidadesPerfil.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstHabilidadesPerfil.setSelectionBackground(new java.awt.Color(23, 97, 122));
        lstHabilidadesPerfil.setSelectionForeground(new java.awt.Color(255, 255, 255));
        lstHabilidadesPerfil.setVisibleRowCount(4);
        jScrollPane2.setViewportView(lstHabilidadesPerfil);

        javax.swing.GroupLayout pnlHabilidadesLayout = new javax.swing.GroupLayout(pnlHabilidades);
        pnlHabilidades.setLayout(pnlHabilidadesLayout);
        pnlHabilidadesLayout.setHorizontalGroup(
            pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloHabilidades)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHabilidadesLayout.setVerticalGroup(
            pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloHabilidades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlExperiencias.setBackground(new java.awt.Color(16, 45, 75));
        pnlExperiencias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblTituloExperiencias.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloExperiencias.setForeground(new java.awt.Color(19, 220, 233));
        lblTituloExperiencias.setText("Experiências profissionais");

        lstExperienciasPerfil.setBackground(new java.awt.Color(16, 45, 75));
        lstExperienciasPerfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstExperienciasPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lstExperienciasPerfil.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstExperienciasPerfil.setSelectionBackground(new java.awt.Color(23, 97, 122));
        lstExperienciasPerfil.setSelectionForeground(new java.awt.Color(255, 255, 255));
        lstExperienciasPerfil.setVisibleRowCount(5);
        jScrollPane3.setViewportView(lstExperienciasPerfil);

        javax.swing.GroupLayout pnlExperienciasLayout = new javax.swing.GroupLayout(pnlExperiencias);
        pnlExperiencias.setLayout(pnlExperienciasLayout);
        pnlExperienciasLayout.setHorizontalGroup(
            pnlExperienciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExperienciasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlExperienciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloExperiencias)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlExperienciasLayout.setVerticalGroup(
            pnlExperienciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExperienciasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloExperiencias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnEditarPerfil.setBackground(new java.awt.Color(19, 220, 233));
        btnEditarPerfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditarPerfil.setForeground(new java.awt.Color(7, 27, 51));
        btnEditarPerfil.setIcon(new javax.swing.ImageIcon("C:\\Users\\gemar\\Downloads\\square-pen.png")); // NOI18N
        btnEditarPerfil.setText("️ Editar perfil");
        btnEditarPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarPerfil.setFocusPainted(false);
        btnEditarPerfil.setIconTextGap(10);
        btnEditarPerfil.addActionListener(this::btnEditarPerfilActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSubtituloPerfil)
                    .addComponent(lblTituloPerfil)
                    .addComponent(pnlIdentificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInformacoesPessoais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSobreMim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHabilidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlExperiencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditarPerfil)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtituloPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlInformacoesPessoais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlSobreMim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlHabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlExperiencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPerfilActionPerformed
           PerfilProfissional_Frame perfil =
    new PerfilProfissional_Frame(idUsuario, true);

perfil.setVisible(true);

javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_btnEditarPerfilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarPerfil;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAreaAtuacaoPerfil;
    private javax.swing.JLabel lblCargoDesejado;
    private javax.swing.JLabel lblCargodesejado2;
    private javax.swing.JLabel lblCidadePerfil;
    private javax.swing.JLabel lblEstadoPerfil;
    private javax.swing.JLabel lblNivelProfissional;
    private javax.swing.JLabel lblNomeProfissional;
    private javax.swing.JLabel lblSubtituloPerfil;
    private javax.swing.JLabel lblTelefonePerfil;
    private javax.swing.JLabel lblTituloCidade;
    private javax.swing.JLabel lblTituloEstado;
    private javax.swing.JLabel lblTituloExperiencias;
    private javax.swing.JLabel lblTituloHabilidades;
    private javax.swing.JLabel lblTituloInformacoesPessoais;
    private javax.swing.JLabel lblTituloNivelProfissional;
    private javax.swing.JLabel lblTituloPerfil;
    private javax.swing.JLabel lblTituloSobreMim;
    private javax.swing.JLabel lblTituloTelefone;
    private javax.swing.JList<String> lstExperienciasPerfil;
    private javax.swing.JList<String> lstHabilidadesPerfil;
    private javax.swing.JPanel pnlExperiencias;
    private javax.swing.JPanel pnlHabilidades;
    private javax.swing.JPanel pnlIdentificacao;
    private javax.swing.JPanel pnlInformacoesPessoais;
    private javax.swing.JPanel pnlSobreMim;
    private javax.swing.JTextArea txtDescricaoPerfil;
    // End of variables declaration//GEN-END:variables
}

