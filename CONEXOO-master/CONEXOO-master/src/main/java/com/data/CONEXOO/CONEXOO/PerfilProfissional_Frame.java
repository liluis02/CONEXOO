/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class PerfilProfissional_Frame extends javax.swing.JFrame {
    private int idUsuario;
    private boolean modoEdicao = false;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PerfilProfissional_Frame.class.getName());

    /**
     * Creates new form PerfilProfissional_Frame
     */
    private final java.util.List<String[]> dadosExperiencias =
        new java.util.ArrayList<>();
    
    public PerfilProfissional_Frame() {
       initComponents();
        
    lstExperiencias.setModel(
       new javax.swing.DefaultListModel<String>()
);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
            // Painel que ocupa toda a largura
    javax.swing.JPanel painelExterno = new javax.swing.JPanel(
        new java.awt.BorderLayout()
    );

    painelExterno.setBackground(pnlPrincipal.getBackground());

    // Painel que centraliza o formulário
    javax.swing.JPanel painelCentral = new javax.swing.JPanel(
        new java.awt.FlowLayout(
            java.awt.FlowLayout.CENTER, 0, 0
        )
    );

    painelCentral.setBackground(pnlPrincipal.getBackground());
    painelCentral.add(pnlPrincipal);

    painelExterno.add(painelCentral, java.awt.BorderLayout.NORTH);

  
    jScrollPane2.setViewportView(painelExterno);

    jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);

 
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

    setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    lstHabilidades.setModel(
    new javax.swing.DefaultListModel<String>()
);  
}

    public PerfilProfissional_Frame(int idUsuario) {
    this();

    this.idUsuario = idUsuario;
    carregarDadosUsuario();
}
public PerfilProfissional_Frame(int idUsuario, boolean modoEdicao) {
    this(idUsuario);
    this.modoEdicao = modoEdicao;

    if (modoEdicao) {
        carregarPerfilParaEdicao();
        carregarHabilidadesParaEdicao();
        carregarExperienciasParaEdicao();
    }
}

private void carregarExperienciasParaEdicao() {

    String sql = """
        SELECT e.cargo, e.nome_empresa, e.descricao,
               e.data_contratacao, e.data_demissao
        FROM experiencias e
        JOIN candidatos c
            ON e.id_candidato = c.id_candidato
        WHERE c.id_usuario = ?
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
            );
         java.sql.PreparedStatement pst =
            conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            javax.swing.DefaultListModel<String> modelo =
                (javax.swing.DefaultListModel<String>) lstExperiencias.getModel();

            modelo.clear();
            dadosExperiencias.clear();

            java.time.format.DateTimeFormatter formato =
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu");

            while (rs.next()) {

                String cargo = rs.getString("cargo");
                String empresa = rs.getString("nome_empresa");
                String descricao = rs.getString("descricao");

                java.sql.Date dataInicio = rs.getDate("data_contratacao");
                java.sql.Date dataFim = rs.getDate("data_demissao");

                String inicio = dataInicio.toLocalDate().format(formato);
                String termino = dataFim == null
                    ? null
                    : dataFim.toLocalDate().format(formato);

                String periodo = termino == null
                    ? inicio + " - Atualmente"
                    : inicio + " - " + termino;

                modelo.addElement(
                    cargo + " | " + empresa + " | " + periodo
                );

                dadosExperiencias.add(new String[] {
                    cargo,
                    empresa,
                    descricao,
                    inicio,
                    termino
                });
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar experiências: " + e.getMessage()
        );
    }
}
    private void carregarHabilidadesParaEdicao() {

    String sql = """
        SELECT h.nome, hc.nivel
        FROM habilidades_candidato hc
        JOIN habilidades h
            ON hc.id_habilidade = h.id_habilidade
        JOIN candidatos c
            ON hc.id_candidato = c.id_candidato
        WHERE c.id_usuario = ?
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
            );
         java.sql.PreparedStatement pst =
            conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            javax.swing.DefaultListModel<String> modelo =
                (javax.swing.DefaultListModel<String>) lstHabilidades.getModel();

            modelo.clear();

            while (rs.next()) {
                String habilidade = rs.getString("nome");
                String nivel = rs.getString("nivel");

                modelo.addElement(habilidade + " - " + nivel);
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar habilidades: " + e.getMessage()
        );
    }
}

    
private void carregarDadosUsuario() {

    String sql = "SELECT nome FROM usuarios WHERE id = ?";

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
                txtNomeCompleto1.setText(rs.getString("nome"));
            }
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar dados do usuário: " + e.getMessage()
        );
    }
}
private void carregarPerfilParaEdicao() {

    String sql = """
        SELECT cpf, telefone, cidade, estado,
               descricao, cargo_desejado, nivel_profissional
        FROM candidatos
        WHERE id_usuario = ?
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
            );
         java.sql.PreparedStatement pst =
            conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                txtCPF.setText(rs.getString("cpf"));
                txtTelefone1.setText(rs.getString("telefone"));
                txtCidade1.setText(rs.getString("cidade"));
                cmbEstado1.setSelectedItem(rs.getString("estado"));

                txtCargoDesejado1.setText(
                    rs.getString("cargo_desejado")
                );

                txtDescricaoProfissional1.setText(
                    rs.getString("descricao")
                );

                String nivel = rs.getString("nivel_profissional");

                if (nivel != null) {
                    cmbNivelProfissional2.setSelectedItem(nivel);
                }
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar perfil: " + e.getMessage()
        );
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();
        lblTituloPerfil = new javax.swing.JLabel();
        lblSubtituloPerfil = new javax.swing.JLabel();
        pnlInformacoesPessoais1 = new javax.swing.JPanel();
        lblInformacoesPessoais1 = new javax.swing.JLabel();
        lblNomeCompleto1 = new javax.swing.JLabel();
        lblTelefone1 = new javax.swing.JLabel();
        txtTelefone1 = new javax.swing.JFormattedTextField();
        lblCidade1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbEstado1 = new javax.swing.JComboBox<>();
        txtNomeCompleto1 = new javax.swing.JTextField();
        txtCidade1 = new javax.swing.JTextField();
        lblCPF = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        pnlHabilidades = new javax.swing.JPanel();
        lblHabilidades = new javax.swing.JLabel();
        cmbNiveldeconhecimento = new javax.swing.JComboBox<>();
        lblNivelconhecimento = new javax.swing.JLabel();
        lblHabilidade = new javax.swing.JLabel();
        cmbHabilidade = new javax.swing.JComboBox<>();
        btnAdicionarHabilidade = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHabilidades = new javax.swing.JList<>();
        btnRemoverHabilidade = new javax.swing.JButton();
        pnlInformacoesProfissionais = new javax.swing.JPanel();
        lblInformacoesProfissionais1 = new javax.swing.JLabel();
        cmbNivelProfissional2 = new javax.swing.JComboBox<>();
        lblNivelProfissional2 = new javax.swing.JLabel();
        txtCargoDesejado1 = new javax.swing.JTextField();
        lblCargoDesejado2 = new javax.swing.JLabel();
        lblDescricaoProfissional1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescricaoProfissional1 = new javax.swing.JTextArea();
        pnlExperienciasProfissionais = new javax.swing.JPanel();
        lblExperienciasProfissionais = new javax.swing.JLabel();
        lblCargoExperiencia = new javax.swing.JLabel();
        txtCargoExperiencia = new javax.swing.JTextField();
        lblEmpresaExperiencia = new javax.swing.JLabel();
        txtEmpresaExperiencia = new javax.swing.JTextField();
        lblDataInicio = new javax.swing.JLabel();
        txtDataInicio = new javax.swing.JFormattedTextField();
        lblDataTermino = new javax.swing.JLabel();
        txtDataTermino = new javax.swing.JFormattedTextField();
        chkTrabalhoAtual = new javax.swing.JCheckBox();
        btnAdicionarExperiencia = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstExperiencias = new javax.swing.JList<>();
        btnRemoverExperiencia = new javax.swing.JButton();
        lblDescricaoExperiencia = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDescricaoExperiencia = new javax.swing.JTextArea();
        btnSalvarPerfil = new javax.swing.JButton();
        btnVoltarInicio4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONEXOO | Meu Perfil");
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setPreferredSize(new java.awt.Dimension(1200, 750));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlPrincipal.setBackground(new java.awt.Color(7, 21, 45));
        pnlPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(1080, 1520));

        lblTituloPerfil.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblTituloPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPerfil.setText("Complete seu perfil");
        lblTituloPerfil.setPreferredSize(new java.awt.Dimension(350, 45));

        lblSubtituloPerfil.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSubtituloPerfil.setForeground(new java.awt.Color(168, 201, 235));
        lblSubtituloPerfil.setText("Preencha suas informações profissionais para começar a encontrar oportunidades.");
        lblSubtituloPerfil.setPreferredSize(new java.awt.Dimension(700, 30));

        pnlInformacoesPessoais1.setBackground(new java.awt.Color(11, 37, 69));
        pnlInformacoesPessoais1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlInformacoesPessoais1.setPreferredSize(new java.awt.Dimension(1100, 280));

        lblInformacoesPessoais1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblInformacoesPessoais1.setForeground(new java.awt.Color(255, 255, 255));
        lblInformacoesPessoais1.setText("Informações pessoais");

        lblNomeCompleto1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblNomeCompleto1.setForeground(new java.awt.Color(168, 201, 235));
        lblNomeCompleto1.setText("Nome completo");

        lblTelefone1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblTelefone1.setForeground(new java.awt.Color(168, 201, 235));
        lblTelefone1.setText("Telefone");

        txtTelefone1.setBackground(new java.awt.Color(7, 21, 45));
        txtTelefone1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtTelefone1.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtTelefone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone1.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTelefone1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        lblCidade1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblCidade1.setForeground(new java.awt.Color(168, 201, 235));
        lblCidade1.setText("Cidade");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(168, 201, 235));
        jLabel2.setText("Estado");

        cmbEstado1.setBackground(new java.awt.Color(7, 21, 45));
        cmbEstado1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cmbEstado1.setForeground(new java.awt.Color(255, 255, 255));
        cmbEstado1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione seu estado", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        cmbEstado1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        cmbEstado1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbEstado1.setPreferredSize(new java.awt.Dimension(250, 40));

        txtNomeCompleto1.setBackground(new java.awt.Color(7, 21, 45));
        txtNomeCompleto1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNomeCompleto1.setForeground(new java.awt.Color(255, 255, 255));
        txtNomeCompleto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtNomeCompleto1.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNomeCompleto1.addActionListener(this::txtNomeCompleto1ActionPerformed);

        txtCidade1.setBackground(new java.awt.Color(7, 21, 45));
        txtCidade1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtCidade1.setForeground(new java.awt.Color(255, 255, 255));
        txtCidade1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtCidade1.setCaretColor(new java.awt.Color(255, 255, 255));

        lblCPF.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblCPF.setForeground(new java.awt.Color(168, 201, 235));
        lblCPF.setText("CPF");

        txtCPF.setBackground(new java.awt.Color(7, 21, 45));
        txtCPF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtCPF.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setCaretColor(new java.awt.Color(255, 255, 255));
        txtCPF.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout pnlInformacoesPessoais1Layout = new javax.swing.GroupLayout(pnlInformacoesPessoais1);
        pnlInformacoesPessoais1.setLayout(pnlInformacoesPessoais1Layout);
        pnlInformacoesPessoais1Layout.setHorizontalGroup(
            pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesPessoais1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacoesPessoais1Layout.createSequentialGroup()
                        .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefone1)
                            .addComponent(txtNomeCompleto1)
                            .addComponent(cmbEstado1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlInformacoesPessoais1Layout.createSequentialGroup()
                                .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTelefone1)
                                    .addComponent(lblNomeCompleto1)
                                    .addComponent(lblInformacoesPessoais1))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCPF)
                            .addComponent(txtCidade1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                            .addComponent(lblCidade1)
                            .addComponent(txtCPF))
                        .addGap(106, 106, 106))
                    .addGroup(pnlInformacoesPessoais1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlInformacoesPessoais1Layout.setVerticalGroup(
            pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesPessoais1Layout.createSequentialGroup()
                .addComponent(lblInformacoesPessoais1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCompleto1)
                    .addComponent(lblCidade1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeCompleto1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone1)
                    .addComponent(lblCPF))
                .addGap(1, 1, 1)
                .addGroup(pnlInformacoesPessoais1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pnlHabilidades.setBackground(new java.awt.Color(11, 37, 69));
        pnlHabilidades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblHabilidades.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblHabilidades.setForeground(new java.awt.Color(255, 255, 255));
        lblHabilidades.setText("Habilidades técnicas");

        cmbNiveldeconhecimento.setBackground(new java.awt.Color(7, 21, 45));
        cmbNiveldeconhecimento.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cmbNiveldeconhecimento.setForeground(new java.awt.Color(255, 255, 255));
        cmbNiveldeconhecimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione seu nível de conhecimento", "Básico", "Intermediário", "Avançado", " ", " " }));
        cmbNiveldeconhecimento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        cmbNiveldeconhecimento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbNiveldeconhecimento.setPreferredSize(new java.awt.Dimension(250, 40));

        lblNivelconhecimento.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblNivelconhecimento.setForeground(new java.awt.Color(168, 201, 235));
        lblNivelconhecimento.setText("Nível de conhecimento");

        lblHabilidade.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblHabilidade.setForeground(new java.awt.Color(168, 201, 235));
        lblHabilidade.setText("Habilidade");

        cmbHabilidade.setBackground(new java.awt.Color(7, 21, 45));
        cmbHabilidade.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cmbHabilidade.setForeground(new java.awt.Color(255, 255, 255));
        cmbHabilidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma habilidade", "Java", "Python", "JavaScript", "C++", "C#", "SQL", "MySQL", "HTML", "CSS", "React", "Spring Boot", "Git", "Docker", "AWS", " ", " ", " " }));
        cmbHabilidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        cmbHabilidade.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbHabilidade.setPreferredSize(new java.awt.Dimension(250, 40));
        cmbHabilidade.addActionListener(this::cmbHabilidadeActionPerformed);

        btnAdicionarHabilidade.setBackground(new java.awt.Color(8, 184, 213));
        btnAdicionarHabilidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdicionarHabilidade.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarHabilidade.setText("+ Adicionar habilidade e nível de conhecimento");
        btnAdicionarHabilidade.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarHabilidade.addActionListener(this::btnAdicionarHabilidadeActionPerformed);

        lstHabilidades.setBackground(new java.awt.Color(11, 37, 69));
        lstHabilidades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        lstHabilidades.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstHabilidades.setForeground(new java.awt.Color(255, 255, 255));
        lstHabilidades.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstHabilidades.setSelectionBackground(new java.awt.Color(8, 184, 213));
        lstHabilidades.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(lstHabilidades);

        btnRemoverHabilidade.setBackground(new java.awt.Color(11, 37, 69));
        btnRemoverHabilidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemoverHabilidade.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverHabilidade.setText("Remover habilidade e nível de conhecimento");
        btnRemoverHabilidade.setBorder(null);
        btnRemoverHabilidade.addActionListener(this::btnRemoverHabilidadeActionPerformed);

        javax.swing.GroupLayout pnlHabilidadesLayout = new javax.swing.GroupLayout(pnlHabilidades);
        pnlHabilidades.setLayout(pnlHabilidadesLayout);
        pnlHabilidadesLayout.setHorizontalGroup(
            pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(lblHabilidades)
                    .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                        .addComponent(lblHabilidade)
                        .addGap(389, 389, 389)
                        .addComponent(lblNivelconhecimento))
                    .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                        .addComponent(cmbHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbNiveldeconhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                        .addComponent(btnAdicionarHabilidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoverHabilidade)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlHabilidadesLayout.setVerticalGroup(
            pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHabilidadesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHabilidades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHabilidade)
                    .addComponent(lblNivelconhecimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNiveldeconhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarHabilidade)
                    .addComponent(btnRemoverHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        pnlInformacoesProfissionais.setBackground(new java.awt.Color(11, 37, 69));
        pnlInformacoesProfissionais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblInformacoesProfissionais1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblInformacoesProfissionais1.setForeground(new java.awt.Color(255, 255, 255));
        lblInformacoesProfissionais1.setText("Informações profissionais");

        cmbNivelProfissional2.setBackground(new java.awt.Color(7, 21, 45));
        cmbNivelProfissional2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cmbNivelProfissional2.setForeground(new java.awt.Color(255, 255, 255));
        cmbNivelProfissional2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione seu nível profissional", "Estagiário", "Júnior", "Pleno", "Sênior", " ", " ", " " }));
        cmbNivelProfissional2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        cmbNivelProfissional2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbNivelProfissional2.setPreferredSize(new java.awt.Dimension(250, 40));

        lblNivelProfissional2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblNivelProfissional2.setForeground(new java.awt.Color(168, 201, 235));
        lblNivelProfissional2.setText("Nível profissional");

        txtCargoDesejado1.setBackground(new java.awt.Color(7, 21, 45));
        txtCargoDesejado1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtCargoDesejado1.setForeground(new java.awt.Color(255, 255, 255));
        txtCargoDesejado1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtCargoDesejado1.setCaretColor(new java.awt.Color(255, 255, 255));

        lblCargoDesejado2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblCargoDesejado2.setForeground(new java.awt.Color(168, 201, 235));
        lblCargoDesejado2.setText("Cargo desejado");

        lblDescricaoProfissional1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblDescricaoProfissional1.setForeground(new java.awt.Color(168, 201, 235));
        lblDescricaoProfissional1.setText("Descrição profissional");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        txtDescricaoProfissional1.setBackground(new java.awt.Color(7, 21, 45));
        txtDescricaoProfissional1.setColumns(20);
        txtDescricaoProfissional1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtDescricaoProfissional1.setForeground(new java.awt.Color(255, 255, 255));
        txtDescricaoProfissional1.setLineWrap(true);
        txtDescricaoProfissional1.setRows(5);
        txtDescricaoProfissional1.setWrapStyleWord(true);
        txtDescricaoProfissional1.setBorder(null);
        txtDescricaoProfissional1.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(txtDescricaoProfissional1);

        javax.swing.GroupLayout pnlInformacoesProfissionaisLayout = new javax.swing.GroupLayout(pnlInformacoesProfissionais);
        pnlInformacoesProfissionais.setLayout(pnlInformacoesProfissionaisLayout);
        pnlInformacoesProfissionaisLayout.setHorizontalGroup(
            pnlInformacoesProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesProfissionaisLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlInformacoesProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescricaoProfissional1)
                    .addComponent(lblInformacoesProfissionais1)
                    .addGroup(pnlInformacoesProfissionaisLayout.createSequentialGroup()
                        .addGroup(pnlInformacoesProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCargoDesejado1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCargoDesejado2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacoesProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNivelProfissional2)
                            .addComponent(cmbNivelProfissional2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlInformacoesProfissionaisLayout.setVerticalGroup(
            pnlInformacoesProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesProfissionaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInformacoesProfissionais1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCargoDesejado2)
                    .addComponent(lblNivelProfissional2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCargoDesejado1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNivelProfissional2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoProfissional1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlExperienciasProfissionais.setBackground(new java.awt.Color(11, 37, 69));
        pnlExperienciasProfissionais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblExperienciasProfissionais.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblExperienciasProfissionais.setForeground(new java.awt.Color(255, 255, 255));
        lblExperienciasProfissionais.setText("Experiências profissionais");

        lblCargoExperiencia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblCargoExperiencia.setForeground(new java.awt.Color(168, 201, 235));
        lblCargoExperiencia.setText("Cargo exercido");

        txtCargoExperiencia.setBackground(new java.awt.Color(7, 21, 45));
        txtCargoExperiencia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtCargoExperiencia.setForeground(new java.awt.Color(255, 255, 255));
        txtCargoExperiencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtCargoExperiencia.setCaretColor(new java.awt.Color(255, 255, 255));

        lblEmpresaExperiencia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblEmpresaExperiencia.setForeground(new java.awt.Color(168, 201, 235));
        lblEmpresaExperiencia.setText("Nome da empresa");

        txtEmpresaExperiencia.setBackground(new java.awt.Color(7, 21, 45));
        txtEmpresaExperiencia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtEmpresaExperiencia.setForeground(new java.awt.Color(255, 255, 255));
        txtEmpresaExperiencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtEmpresaExperiencia.setCaretColor(new java.awt.Color(255, 255, 255));

        lblDataInicio.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblDataInicio.setForeground(new java.awt.Color(168, 201, 235));
        lblDataInicio.setText("Data de início(DD/MM/AAAA)");

        txtDataInicio.setBackground(new java.awt.Color(7, 21, 45));
        txtDataInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtDataInicio.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataInicio.setCaretColor(new java.awt.Color(255, 255, 255));

        lblDataTermino.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblDataTermino.setForeground(new java.awt.Color(168, 201, 235));
        lblDataTermino.setText("Data de término(DD/MM/AAAA)");

        txtDataTermino.setBackground(new java.awt.Color(7, 21, 45));
        txtDataTermino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        txtDataTermino.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txtDataTermino.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataTermino.setCaretColor(new java.awt.Color(255, 255, 255));

        chkTrabalhoAtual.setBackground(new java.awt.Color(11, 37, 69));
        chkTrabalhoAtual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkTrabalhoAtual.setForeground(new java.awt.Color(255, 255, 255));
        chkTrabalhoAtual.setText("Trabalho atualmente nesta empresa");
        chkTrabalhoAtual.addActionListener(this::chkTrabalhoAtualActionPerformed);

        btnAdicionarExperiencia.setBackground(new java.awt.Color(8, 184, 213));
        btnAdicionarExperiencia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdicionarExperiencia.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarExperiencia.setText("+ Adicionar experiência");
        btnAdicionarExperiencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarExperiencia.addActionListener(this::btnAdicionarExperienciaActionPerformed);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lstExperiencias.setBackground(new java.awt.Color(11, 37, 69));
        lstExperiencias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstExperiencias.setForeground(new java.awt.Color(255, 255, 255));
        lstExperiencias.setSelectionBackground(new java.awt.Color(8, 184, 213));
        lstExperiencias.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(lstExperiencias);

        btnRemoverExperiencia.setBackground(new java.awt.Color(11, 37, 69));
        btnRemoverExperiencia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRemoverExperiencia.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverExperiencia.setText("Remover experiência");
        btnRemoverExperiencia.setBorder(null);
        btnRemoverExperiencia.addActionListener(this::btnRemoverExperienciaActionPerformed);

        lblDescricaoExperiencia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblDescricaoExperiencia.setForeground(new java.awt.Color(168, 201, 235));
        lblDescricaoExperiencia.setText("Descrição da experiência");

        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        txtDescricaoExperiencia.setBackground(new java.awt.Color(7, 21, 45));
        txtDescricaoExperiencia.setColumns(20);
        txtDescricaoExperiencia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtDescricaoExperiencia.setForeground(new java.awt.Color(255, 255, 255));
        txtDescricaoExperiencia.setLineWrap(true);
        txtDescricaoExperiencia.setRows(5);
        txtDescricaoExperiencia.setWrapStyleWord(true);
        txtDescricaoExperiencia.setBorder(null);
        txtDescricaoExperiencia.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane6.setViewportView(txtDescricaoExperiencia);

        javax.swing.GroupLayout pnlExperienciasProfissionaisLayout = new javax.swing.GroupLayout(pnlExperienciasProfissionais);
        pnlExperienciasProfissionais.setLayout(pnlExperienciasProfissionaisLayout);
        pnlExperienciasProfissionaisLayout.setHorizontalGroup(
            pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExperienciasProfissionaisLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescricaoExperiencia)
                    .addGroup(pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtEmpresaExperiencia)
                        .addComponent(lblEmpresaExperiencia)
                        .addComponent(lblCargoExperiencia)
                        .addComponent(lblExperienciasProfissionais)
                        .addComponent(txtCargoExperiencia)
                        .addGroup(pnlExperienciasProfissionaisLayout.createSequentialGroup()
                            .addGroup(pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDataInicio))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDataTermino)
                                .addComponent(txtDataTermino, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)))
                        .addComponent(chkTrabalhoAtual)
                        .addGroup(pnlExperienciasProfissionaisLayout.createSequentialGroup()
                            .addComponent(btnAdicionarExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnRemoverExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        pnlExperienciasProfissionaisLayout.setVerticalGroup(
            pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExperienciasProfissionaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExperienciasProfissionais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCargoExperiencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCargoExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmpresaExperiencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmpresaExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoExperiencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataInicio)
                    .addComponent(lblDataTermino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkTrabalhoAtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlExperienciasProfissionaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarExperiencia)
                    .addComponent(btnRemoverExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSalvarPerfil.setBackground(new java.awt.Color(8, 184, 213));
        btnSalvarPerfil.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSalvarPerfil.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarPerfil.setText("Salvar perfil");
        btnSalvarPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvarPerfil.addActionListener(this::btnSalvarPerfilActionPerformed);

        btnVoltarInicio4.setBackground(new java.awt.Color(7, 21, 45));
        btnVoltarInicio4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnVoltarInicio4.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarInicio4.setText("← Voltar ao início");
        btnVoltarInicio4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        btnVoltarInicio4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltarInicio4.addActionListener(this::btnVoltarInicio4ActionPerformed);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(btnVoltarInicio4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnlExperienciasProfissionais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTituloPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSubtituloPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlHabilidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlInformacoesProfissionais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlInformacoesPessoais1, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtituloPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInformacoesPessoais1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInformacoesProfissionais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlExperienciasProfissionais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltarInicio4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(pnlPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtNomeCompleto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeCompleto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeCompleto1ActionPerformed

    private void btnAdicionarHabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarHabilidadeActionPerformed
        if (cmbHabilidade.getSelectedIndex() <= 0 ||
            cmbNiveldeconhecimento.getSelectedIndex() <= 0) {

            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Selecione uma habilidade e o nível de conhecimento!"
            );
            return;
        }

        String habilidade = cmbHabilidade.getSelectedItem().toString();
        String nivel = cmbNiveldeconhecimento.getSelectedItem().toString();

        javax.swing.DefaultListModel<String> modelo =
        (javax.swing.DefaultListModel<String>) lstHabilidades.getModel();

        String novaHabilidade = habilidade + " - " + nivel;

        // Verifica se a habilidade já foi adicionada
        for (int i = 0; i < modelo.getSize(); i++) {

            String item = modelo.getElementAt(i);
            String habilidadeExistente = item.split(" - ", 2)[0];

            if (habilidadeExistente.equalsIgnoreCase(habilidade)) {

                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Esta habilidade já foi adicionada!"
                );
                return;
            }
        }

        // Adiciona somente se não estiver na lista
        modelo.addElement(novaHabilidade);
    }//GEN-LAST:event_btnAdicionarHabilidadeActionPerformed

    private void btnRemoverHabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverHabilidadeActionPerformed
        
int indice = lstHabilidades.getSelectedIndex();

if (indice == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Selecione uma habilidade para remover!"
    );
    return;
}

javax.swing.DefaultListModel<String> modelo =
    (javax.swing.DefaultListModel<String>) lstHabilidades.getModel();

modelo.remove(indice);
    }//GEN-LAST:event_btnRemoverHabilidadeActionPerformed

    private void chkTrabalhoAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTrabalhoAtualActionPerformed
        if (chkTrabalhoAtual.isSelected()) {
    txtDataTermino.setText("");
    txtDataTermino.setEnabled(false);
} else {
    txtDataTermino.setEnabled(true);
}
    }//GEN-LAST:event_chkTrabalhoAtualActionPerformed

    private void btnAdicionarExperienciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarExperienciaActionPerformed
    
String cargo = txtCargoExperiencia.getText().trim();
String empresa = txtEmpresaExperiencia.getText().trim();    
String descricao = txtDescricaoExperiencia.getText().trim();
String inicio = txtDataInicio.getText().trim();
String termino = txtDataTermino.getText().trim();

boolean trabalhoAtual = chkTrabalhoAtual.isSelected();

if (cargo.isEmpty() || empresa.isEmpty() 
        || inicio.replaceAll("[^0-9]", "").length() != 8
        || (!trabalhoAtual
        && termino.replaceAll("[^0-9]", "").length() != 8)) {

    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Preencha todos os campos obrigatórios da experiência!"
    );
    return;
}

try {
    java.time.format.DateTimeFormatter formato =
        java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(
                java.time.format.ResolverStyle.STRICT
            );

    java.time.LocalDate.parse(inicio, formato);

} catch (java.time.format.DateTimeParseException e) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Data de início inválida! Informe uma data válida."
    );
    txtDataInicio.requestFocus();
    return;
}


if (!trabalhoAtual) {
    boolean dataInvalida = false;

    try {
        java.time.format.DateTimeFormatter formato =
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(java.time.format.ResolverStyle.STRICT);

        java.time.LocalDate dataInicio =
            java.time.LocalDate.parse(inicio, formato);

        java.time.LocalDate dataTermino =
            java.time.LocalDate.parse(termino, formato);

        dataInvalida = dataTermino.isBefore(dataInicio);

    } catch (java.time.format.DateTimeParseException e) {
        dataInvalida = true;
    }

    if (dataInvalida) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Data de término inválida! Informe uma data válida."
        );
        txtDataTermino.requestFocus();
        return;
    }
}


String periodo = trabalhoAtual
        ? inicio + " - Atualmente"
        : inicio + " - " + termino;

javax.swing.DefaultListModel<String> modelo =
    (javax.swing.DefaultListModel<String>) lstExperiencias.getModel();

modelo.addElement(
    cargo + " | " + empresa + " | " + periodo
);
dadosExperiencias.add(new String[] {
    cargo,
    empresa,
    descricao,
    inicio,
    trabalhoAtual ? null : termino
});
    }//GEN-LAST:event_btnAdicionarExperienciaActionPerformed

    private void btnRemoverExperienciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverExperienciaActionPerformed
        int indice = lstExperiencias.getSelectedIndex();

if (indice == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Selecione uma experiência para remover!"
    );
    return;
}

javax.swing.DefaultListModel<String> modelo =
    (javax.swing.DefaultListModel<String>) lstExperiencias.getModel();

modelo.remove(indice);
dadosExperiencias.remove(indice);
    }//GEN-LAST:event_btnRemoverExperienciaActionPerformed

    private void btnSalvarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPerfilActionPerformed
        String nome = txtNomeCompleto1.getText().trim();

if (nome.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Preencha seu nome completo!"
    );

    txtNomeCompleto1.requestFocus();
    return;
}
String cpf = txtCPF.getText().replaceAll("\\D", "");

if (cpf.length() != 11) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Preencha seu CPF corretamente!"
    );

    txtCPF.requestFocus();
    return;
}
String telefone = txtTelefone1.getText().replaceAll("\\D", "");

if (telefone.length() != 11) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Preencha seu telefone corretamente!"
    );

    txtTelefone1.requestFocus();
    return;
}
String cidade = txtCidade1.getText().trim();

if (cidade.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Preencha sua cidade!"
    );

    txtCidade1.requestFocus();
    return;
}
if (cmbEstado1.getSelectedIndex() == 0) {

    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Selecione seu estado!"
    );

    cmbEstado1.requestFocus();
    return;
}
String cargoDesejado = txtCargoDesejado1.getText().trim();

if (cargoDesejado.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Informe seu cargo desejado!"
    );

    txtCargoDesejado1.requestFocus();
    return;
}
int idCandidato;

if (modoEdicao) {
    idCandidato = atualizarDadosCandidato();
} else {
    idCandidato = salvarDadosCandidato();
}

if (idCandidato == -1) {
    return;
}

boolean habilidadesSalvas = salvarHabilidades(idCandidato);

if (!habilidadesSalvas) {
    return;
}

boolean experienciasSalvas = salvarExperiencias(idCandidato);

if (!experienciasSalvas) {
    return;
}
javax.swing.JOptionPane.showMessageDialog(
    this,
    "Perfil profissional salvo com sucesso!"
);
Menu_Frame menu = new Menu_Frame(idUsuario, "PROFISSIONAL");
menu.setVisible(true);
this.dispose();
    }//GEN-LAST:event_btnSalvarPerfilActionPerformed

    private void cmbHabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHabilidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHabilidadeActionPerformed

    private void btnVoltarInicio4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarInicio4ActionPerformed
for (java.awt.Window janela : java.awt.Window.getWindows()) {

    if (janela instanceof Menu_Frame) {
        Menu_Frame menu = (Menu_Frame) janela;

        menu.setVisible(true);
        menu.toFront();
        menu.requestFocus();

        this.dispose();
        return;
    }
}
    }//GEN-LAST:event_btnVoltarInicio4ActionPerformed
private int atualizarDadosCandidato() {

    String sql = """
        UPDATE candidatos
        SET cpf = ?, telefone = ?, cidade = ?, estado = ?,
            descricao = ?, cargo_desejado = ?, nivel_profissional = ?
        WHERE id_usuario = ?
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
            );
         java.sql.PreparedStatement pst =
            conexao.prepareStatement(sql)) {

        pst.setString(1, txtCPF.getText().replaceAll("\\D", ""));
        pst.setString(2, txtTelefone1.getText().replaceAll("\\D", ""));
        pst.setString(3, txtCidade1.getText().trim());
        pst.setString(4, cmbEstado1.getSelectedItem().toString());

        String descricao = txtDescricaoProfissional1.getText().trim();
        pst.setString(5, descricao.isEmpty() ? null : descricao);

        pst.setString(6, txtCargoDesejado1.getText().trim());

        if (cmbNivelProfissional2.getSelectedIndex() == 0) {
            pst.setNull(7, java.sql.Types.VARCHAR);
        } else {
            pst.setString(7, cmbNivelProfissional2.getSelectedItem().toString());
        }

        pst.setInt(8, idUsuario);

        int linhasAtualizadas = pst.executeUpdate();

        if (linhasAtualizadas == 0) {
            return -1;
        }

        String sqlId = "SELECT id_candidato FROM candidatos WHERE id_usuario = ?";

        try (java.sql.PreparedStatement pstId =
                conexao.prepareStatement(sqlId)) {

            pstId.setInt(1, idUsuario);

            try (java.sql.ResultSet rs = pstId.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_candidato");
                }
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao atualizar perfil: " + e.getMessage()
        );
    }

    return -1;
}
    private int salvarDadosCandidato() {

String sql = """
        INSERT INTO candidatos
        (id_usuario, cpf, telefone, cidade, estado,
         descricao, cargo_desejado, nivel_profissional)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try (java.sql.Connection conexao =
            java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
            )) {

try (java.sql.PreparedStatement pst =
        conexao.prepareStatement(
            sql,
            java.sql.Statement.RETURN_GENERATED_KEYS
        )) {
        
pst.setInt(1, idUsuario);

pst.setString(2, txtCPF.getText().replaceAll("\\D", ""));

pst.setString(3, txtTelefone1.getText().replaceAll("\\D", ""));

pst.setString(4, txtCidade1.getText().trim());

pst.setString(5, cmbEstado1.getSelectedItem().toString());

String descricao = txtDescricaoProfissional1.getText().trim();

pst.setString(6, descricao.isEmpty() ? null : descricao);

pst.setString(7, txtCargoDesejado1.getText().trim());

if (cmbNivelProfissional2.getSelectedIndex() == 0) {
    pst.setNull(8, java.sql.Types.VARCHAR);
} else {
    pst.setString(8, cmbNivelProfissional2.getSelectedItem().toString());
}

pst.executeUpdate();

try (java.sql.ResultSet rs = pst.getGeneratedKeys()) {
if (rs.next()) {
    int idCandidato = rs.getInt(1);
    return idCandidato;
}
    }
}
    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao conectar ao banco de dados: " + e.getMessage()
        );
    }
    return -1;
    }


private boolean salvarHabilidades(int idCandidato) {
        String sql = """
        INSERT INTO habilidades_candidato
        (id_candidato, id_habilidade, nivel)
        VALUES (?, ?, ?)
        """;
        
        String sqlBuscarHabilidade = """
    SELECT id_habilidade
    FROM habilidades
    WHERE nome = ?
    """;
        
javax.swing.ListModel<String> modelo = lstHabilidades.getModel();
try (java.sql.Connection conexao =
        java.sql.DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
            "root",
            "123456"
        )) {
    if (modoEdicao) {
    String sqlExcluir =
        "DELETE FROM habilidades_candidato WHERE id_candidato = ?";

    try (java.sql.PreparedStatement pstExcluir =
            conexao.prepareStatement(sqlExcluir)) {

        pstExcluir.setInt(1, idCandidato);
        pstExcluir.executeUpdate();
    }
}
    for (int i = 0; i < modelo.getSize(); i++) {

        String item = modelo.getElementAt(i);

        String[] partes = item.split(" - ", 2);

        if (partes.length != 2) {
            continue;
        }

        String habilidade = partes[0].trim();
        String nivel = partes[1].trim();
        int idHabilidade = -1;

try (java.sql.PreparedStatement pstBusca =
        conexao.prepareStatement(sqlBuscarHabilidade)) {

    pstBusca.setString(1, habilidade);

    try (java.sql.ResultSet rs = pstBusca.executeQuery()) {

        if (rs.next()) {
            idHabilidade = rs.getInt("id_habilidade");
        }
    }
}
if (idHabilidade == -1) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Habilidade não encontrada no banco de dados: " + habilidade
    );
    return false;
}
try (java.sql.PreparedStatement pst =
        conexao.prepareStatement(sql)) {

    pst.setInt(1, idCandidato);
    pst.setInt(2, idHabilidade);
    pst.setString(3, nivel);

    pst.executeUpdate();
}
    }
} catch (java.sql.SQLException e) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Erro ao salvar habilidades: " + e.getMessage()
    );
return false;    
}
return true;
}
private boolean salvarExperiencias(int idCandidato) {

    String sql = """
        INSERT INTO experiencias
        (id_candidato, nome_empresa, cargo,
         descricao, data_contratacao, data_demissao)
        VALUES (?, ?, ?, ?, ?, ?)
        """;
    try (java.sql.Connection conexao =
        java.sql.DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/conexoo?useSSL=false&serverTimezone=UTC",
            "root",
            "123456"
        )) {
        if (modoEdicao) {
    String sqlExcluir =
        "DELETE FROM experiencias WHERE id_candidato = ?";

    try (java.sql.PreparedStatement pstExcluir =
            conexao.prepareStatement(sqlExcluir)) {

        pstExcluir.setInt(1, idCandidato);
        pstExcluir.executeUpdate();
    }
}
    for (String[] experiencia : dadosExperiencias) {

    String cargo = experiencia[0];
    String empresa = experiencia[1];
    String descricao = experiencia[2];
    String inicio = experiencia[3];
    String termino = experiencia[4];

    try (java.sql.PreparedStatement pst =
        conexao.prepareStatement(sql)) {

    pst.setInt(1, idCandidato);
    pst.setString(2, empresa);
    pst.setString(3, cargo);
    pst.setString(4, descricao.isEmpty() ? null : descricao);
    
    
java.time.format.DateTimeFormatter formato =
        java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

java.time.LocalDate dataInicio =
        java.time.LocalDate.parse(inicio, formato);

pst.setDate(5, java.sql.Date.valueOf(dataInicio));

if (termino == null) {
    pst.setNull(6, java.sql.Types.DATE);
} else {
    java.time.LocalDate dataTermino =
            java.time.LocalDate.parse(termino, formato);

    pst.setDate(6, java.sql.Date.valueOf(dataTermino));
}
pst.executeUpdate();
}
}
}
catch (java.sql.SQLException e) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Erro ao salvar experiências: " + e.getMessage()
    );
    return false;
}

catch (java.time.format.DateTimeParseException e) {
    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Data inválida! Verifique as datas das experiências."
    );
    return false;
}

return true;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarExperiencia;
    private javax.swing.JButton btnAdicionarHabilidade;
    private javax.swing.JButton btnRemoverExperiencia;
    private javax.swing.JButton btnRemoverHabilidade;
    private javax.swing.JButton btnSalvarPerfil;
    private javax.swing.JButton btnVoltarInicio4;
    private javax.swing.JCheckBox chkTrabalhoAtual;
    private javax.swing.JComboBox<String> cmbEstado1;
    private javax.swing.JComboBox<String> cmbHabilidade;
    private javax.swing.JComboBox<String> cmbNivelProfissional2;
    private javax.swing.JComboBox<String> cmbNiveldeconhecimento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCargoDesejado2;
    private javax.swing.JLabel lblCargoExperiencia;
    private javax.swing.JLabel lblCidade1;
    private javax.swing.JLabel lblDataInicio;
    private javax.swing.JLabel lblDataTermino;
    private javax.swing.JLabel lblDescricaoExperiencia;
    private javax.swing.JLabel lblDescricaoProfissional1;
    private javax.swing.JLabel lblEmpresaExperiencia;
    private javax.swing.JLabel lblExperienciasProfissionais;
    private javax.swing.JLabel lblHabilidade;
    private javax.swing.JLabel lblHabilidades;
    private javax.swing.JLabel lblInformacoesPessoais1;
    private javax.swing.JLabel lblInformacoesProfissionais1;
    private javax.swing.JLabel lblNivelProfissional2;
    private javax.swing.JLabel lblNivelconhecimento;
    private javax.swing.JLabel lblNomeCompleto1;
    private javax.swing.JLabel lblSubtituloPerfil;
    private javax.swing.JLabel lblTelefone1;
    private javax.swing.JLabel lblTituloPerfil;
    private javax.swing.JList<String> lstExperiencias;
    private javax.swing.JList<String> lstHabilidades;
    private javax.swing.JPanel pnlExperienciasProfissionais;
    private javax.swing.JPanel pnlHabilidades;
    private javax.swing.JPanel pnlInformacoesPessoais1;
    private javax.swing.JPanel pnlInformacoesProfissionais;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtCargoDesejado1;
    private javax.swing.JTextField txtCargoExperiencia;
    private javax.swing.JTextField txtCidade1;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JFormattedTextField txtDataTermino;
    private javax.swing.JTextArea txtDescricaoExperiencia;
    private javax.swing.JTextArea txtDescricaoProfissional1;
    private javax.swing.JTextField txtEmpresaExperiencia;
    private javax.swing.JTextField txtNomeCompleto1;
    private javax.swing.JFormattedTextField txtTelefone1;
    // End of variables declaration//GEN-END:variables
}
