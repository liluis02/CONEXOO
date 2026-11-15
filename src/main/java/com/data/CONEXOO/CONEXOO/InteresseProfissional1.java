/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class InteresseProfissional1 extends javax.swing.JPanel {
private int idUsuario;
private static final int COMPATIBILIDADE_MINIMA = 50;

public InteresseProfissional1(int idUsuario) {
    this();

    this.idUsuario = idUsuario;

    jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
    jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
    pnlListaRecomendadas.setBackground(new java.awt.Color(7, 21, 45));
jScrollPane2.getViewport().setBackground(new java.awt.Color(7, 21, 45));
    

    pnlListaVagas.setLayout(
        new javax.swing.BoxLayout(
            pnlListaVagas,
            javax.swing.BoxLayout.Y_AXIS
        )
    );
    
    pnlListaRecomendadas.setLayout(
    new javax.swing.BoxLayout(
        pnlListaRecomendadas,
        javax.swing.BoxLayout.Y_AXIS
    )
);

    carregarVagasCurtidas();
    carregarVagasRecomendadas();
    
    
}
private java.util.function.IntConsumer acaoVerDetalhes;

public void setAcaoVerDetalhes(
        java.util.function.IntConsumer acao) {
    this.acaoVerDetalhes = acao;
}
private void carregarVagasCurtidas() {

    pnlListaVagas.removeAll();

    String sql = "SELECT v.id_vaga, v.titulo, "
            + "v.localizacao, v.salario_min, v.salario_max, "
            + "e.nome_fantasia "
            + "FROM interesses i "
            + "INNER JOIN candidatos c "
            + "ON i.id_candidato = c.id_candidato "
            + "INNER JOIN vagas v "
            + "ON i.id_vaga = v.id_vaga "
            + "INNER JOIN empresas e "
            + "ON v.id_empresa = e.id_empresa "
            + "WHERE c.id_usuario = ? "
            + "AND i.origem = 'candidato' "
            + "ORDER BY i.data_interesse DESC";

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

criarCardVaga(
    rs.getInt("id_vaga"),
    rs.getString("titulo"),
    rs.getString("nome_fantasia"),
    rs.getString("localizacao"),
    rs.getBigDecimal("salario_min"),
    rs.getBigDecimal("salario_max"),
    pnlListaVagas
);
            }
        }

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar vagas curtidas: " + e.getMessage()
        );
    }
    if (pnlListaVagas.getComponentCount() == 0) {

    javax.swing.JLabel lblVazio = new javax.swing.JLabel(
        "Você ainda não curtiu nenhuma vaga."
    );

    lblVazio.setForeground(new java.awt.Color(182, 197, 216));
    lblVazio.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    lblVazio.setAlignmentX(javax.swing.JComponent.LEFT_ALIGNMENT);

    pnlListaVagas.add(lblVazio);
}
    pnlListaVagas.revalidate();
    pnlListaVagas.repaint();
}
private int compararTecnologias(
        String titulo1, String descricao1,
        String titulo2, String descricao2) {

    String texto1 = ((titulo1 == null ? "" : titulo1) + " "
            + (descricao1 == null ? "" : descricao1)).toLowerCase();

    String texto2 = ((titulo2 == null ? "" : titulo2) + " "
            + (descricao2 == null ? "" : descricao2)).toLowerCase();

    String[] tecnologias = {
        "java", "python", "javascript", "typescript",
        "react", "angular", "vue", "spring",
        "php", "laravel", "node.js", "c#",
        "c++", "sql", "mysql", "postgresql",
        "mongodb", "docker", "aws", "azure",
        "kotlin", "swift", "flutter"
    };

    int tecnologiasComuns = 0;
    int tecnologiasEncontradas = 0;

    for (String tecnologia : tecnologias) {

        boolean possui1 = texto1.contains(tecnologia);
        boolean possui2 = texto2.contains(tecnologia);

        if (possui1 || possui2) {
            tecnologiasEncontradas++;
        }

        if (possui1 && possui2) {
            tecnologiasComuns++;
        }
    }

    if (tecnologiasEncontradas == 0) {
        return 0;
    }

    return (int) Math.round(
            35.0 * tecnologiasComuns / tecnologiasEncontradas
    );
}
private int compararCargos(String titulo1, String titulo2) {

    if (titulo1 == null || titulo2 == null) {
        return 0;
    }

    String t1 = titulo1.toLowerCase().trim();
    String t2 = titulo2.toLowerCase().trim();

    if (t1.equals(t2)) {
        return 25;
    }

    String[][] grupos = {
        {"desenvolvedor", "programador", "software engineer"},
        {"analista de dados", "cientista de dados", "data analyst"},
        {"suporte", "help desk", "service desk"},
        {"devops", "engenheiro de infraestrutura", "sre"},
        {"segurança da informação", "cibersegurança", "cybersecurity"},
        {"designer ux", "designer ui", "product designer"},
        {"testador", "qa", "analista de testes"}
    };

    for (String[] grupo : grupos) {

        boolean encontrado1 = false;
        boolean encontrado2 = false;

        for (String cargo : grupo) {
            if (t1.contains(cargo)) {
                encontrado1 = true;
            }

            if (t2.contains(cargo)) {
                encontrado2 = true;
            }
        }

        if (encontrado1 && encontrado2) {
            return 25;
        }
    }

    return 0;
}
private int compararSenioridade(String titulo1, String titulo2) {

    if (titulo1 == null || titulo2 == null) {
        return 0;
    }

    String t1 = java.text.Normalizer.normalize(
            titulo1.toLowerCase(),
            java.text.Normalizer.Form.NFD
    ).replaceAll("\\p{M}", "");

    String t2 = java.text.Normalizer.normalize(
            titulo2.toLowerCase(),
            java.text.Normalizer.Form.NFD
    ).replaceAll("\\p{M}", "");

    String nivel1 = identificarSenioridade(t1);
    String nivel2 = identificarSenioridade(t2);

    if (nivel1.isEmpty() || nivel2.isEmpty()) {
        return 0;
    }

    return nivel1.equals(nivel2) ? 15 : 0;
}

private String identificarSenioridade(String titulo) {

    if (titulo.matches(".*\\b(junior|jr)\\b.*")) {
        return "junior";
    }

    if (titulo.matches(".*\\b(pleno|pl)\\b.*")) {
        return "pleno";
    }

    if (titulo.matches(".*\\b(senior|sr)\\b.*")) {
        return "senior";
    }

    return "";
}
private int compararModeloTrabalho(
        String modelo1, String modelo2) {

    if (modelo1 == null || modelo2 == null) {
        return 0;
    }

    String m1 = java.text.Normalizer.normalize(
            modelo1.toLowerCase().trim(),
            java.text.Normalizer.Form.NFD
    ).replaceAll("\\p{M}", "");

    String m2 = java.text.Normalizer.normalize(
            modelo2.toLowerCase().trim(),
            java.text.Normalizer.Form.NFD
    ).replaceAll("\\p{M}", "");

    if (m1.isEmpty() || m2.isEmpty()) {
        return 0;
    }

    return m1.equals(m2) ? 10 : 0;
}
private int compararTipoContrato(
        String contrato1, String contrato2) {

    if (contrato1 == null || contrato2 == null) {
        return 0;
    }

    String c1 = contrato1.toLowerCase().trim();
    String c2 = contrato2.toLowerCase().trim();

    if (c1.isEmpty() || c2.isEmpty()) {
        return 0;
    }

    return c1.equals(c2) ? 5 : 0;
}
private int compararSalarios(
        java.math.BigDecimal min1,
        java.math.BigDecimal max1,
        java.math.BigDecimal min2,
        java.math.BigDecimal max2) {

    if (min1 == null || max1 == null
            || min2 == null || max2 == null) {
        return 0;
    }

    if (min1.compareTo(max1) > 0
            || min2.compareTo(max2) > 0) {
        return 0;
    }

    if (min1.compareTo(max2) <= 0
            && min2.compareTo(max1) <= 0) {
        return 10;
    }

    return 0;
}
private int calcularCompatibilidade(
        String titulo1, String descricao1,
        String modelo1, String contrato1,
        java.math.BigDecimal salarioMin1,
        java.math.BigDecimal salarioMax1,
        String titulo2, String descricao2,
        String modelo2, String contrato2,
        java.math.BigDecimal salarioMin2,
        java.math.BigDecimal salarioMax2) {

    int pontos = 0;

    pontos += compararTecnologias(
            titulo1, descricao1, titulo2, descricao2);

    pontos += compararCargos(titulo1, titulo2);

    pontos += compararSenioridade(titulo1, titulo2);

    pontos += compararModeloTrabalho(modelo1, modelo2);

    pontos += compararTipoContrato(contrato1, contrato2);

    pontos += compararSalarios(
            salarioMin1, salarioMax1,
            salarioMin2, salarioMax2);

    return pontos;
}
private void carregarVagasRecomendadas() {

    pnlListaRecomendadas.removeAll();
    String sqlCurtidas = """
    SELECT v.titulo, v.descricao,
           v.modelo_trabalho, v.tipo_contrato,
           v.salario_min, v.salario_max
    FROM interesses i
    INNER JOIN candidatos c
        ON i.id_candidato = c.id_candidato
    INNER JOIN vagas v
        ON i.id_vaga = v.id_vaga
    WHERE c.id_usuario = ?
      AND i.origem = 'candidato'
    """;

String sql = """
    SELECT v.id_vaga, v.titulo, v.descricao,
           v.localizacao, v.modelo_trabalho,
           v.tipo_contrato, v.salario_min,
           v.salario_max, e.nome_fantasia
        FROM vagas v
        INNER JOIN empresas e
            ON v.id_empresa = e.id_empresa
        WHERE v.status = 'ATIVA'
          AND v.id_vaga NOT IN (
              SELECT i.id_vaga
              FROM interesses i
              INNER JOIN candidatos c
                  ON i.id_candidato = c.id_candidato
              WHERE c.id_usuario = ?
                AND i.origem = 'candidato'
          )
             ORDER BY v.data_publicacao DESC
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
            java.sql.PreparedStatement stmtCurtidas =
        conexao.prepareStatement(sqlCurtidas);
         java.sql.PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {
        stmtCurtidas.setInt(1, idUsuario);
        stmt.setInt(1, idUsuario);
        java.util.List<String[]> vagasCurtidas = new java.util.ArrayList<>();

try (java.sql.ResultSet rsCurtidas = stmtCurtidas.executeQuery()) {

    while (rsCurtidas.next()) {
        vagasCurtidas.add(new String[]{
            rsCurtidas.getString("titulo"),
            rsCurtidas.getString("descricao"),
            rsCurtidas.getString("modelo_trabalho"),
            rsCurtidas.getString("tipo_contrato"),
            rsCurtidas.getString("salario_min"),
            rsCurtidas.getString("salario_max")
        });
    }
}


        try (java.sql.ResultSet rs = stmt.executeQuery()) {
            java.util.List<Object[]> vagasRecomendadas =
        new java.util.ArrayList<>();
            
            while (rs.next()) {
                int maiorPontuacao = 0;

for (String[] curtida : vagasCurtidas) {

    int pontos = calcularCompatibilidade(
            curtida[0],
            curtida[1],
            curtida[2],
            curtida[3],
            curtida[4] == null ? null
                    : new java.math.BigDecimal(curtida[4]),
            curtida[5] == null ? null
                    : new java.math.BigDecimal(curtida[5]),
            rs.getString("titulo"),
            rs.getString("descricao"),
            rs.getString("modelo_trabalho"),
            rs.getString("tipo_contrato"),
            rs.getBigDecimal("salario_min"),
            rs.getBigDecimal("salario_max")
    );

    maiorPontuacao = Math.max(maiorPontuacao, pontos);
}
if (maiorPontuacao < COMPATIBILIDADE_MINIMA) {
    continue;
}

vagasRecomendadas.add(new Object[]{
    rs.getInt("id_vaga"),
    rs.getString("titulo"),
    rs.getString("nome_fantasia"),
    rs.getString("localizacao"),
    rs.getBigDecimal("salario_min"),
    rs.getBigDecimal("salario_max"),
    maiorPontuacao
});


               
            }
            vagasRecomendadas.sort(
    (vaga1, vaga2) -> Integer.compare(
        (Integer) vaga2[6],
        (Integer) vaga1[6]
    )
);
            for (Object[] vaga : vagasRecomendadas) {

    criarCardVaga(
        (Integer) vaga[0],
        (String) vaga[1],
        (String) vaga[2],
        (String) vaga[3],
        (java.math.BigDecimal) vaga[4],
        (java.math.BigDecimal) vaga[5],
        pnlListaRecomendadas
    );
}
        }

    }
    catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar recomendações: " + e.getMessage()
        );
    }
if (pnlListaRecomendadas.getComponentCount() == 0) {

    javax.swing.JLabel lblVazio = new javax.swing.JLabel(
        "Nenhuma recomendação disponível no momento. Curta vagas para receber sugestões compatíveis."
    );

    lblVazio.setForeground(new java.awt.Color(182, 197, 216));
    lblVazio.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    lblVazio.setAlignmentX(javax.swing.JComponent.LEFT_ALIGNMENT);

    pnlListaRecomendadas.add(lblVazio);
}
    pnlListaRecomendadas.revalidate();
    pnlListaRecomendadas.repaint();
}
public void atualizarVagasCurtidas() {
    carregarVagasCurtidas();
    carregarVagasRecomendadas();
}
private void criarCardVaga(
        int idVaga,
        String titulo,
        String empresa,
        String localizacao,
        java.math.BigDecimal salarioMin,
        java.math.BigDecimal salarioMax,
        javax.swing.JPanel painelDestino) { 

    javax.swing.JPanel card = new javax.swing.JPanel();
    
    card.setPreferredSize(new java.awt.Dimension(500, 130));
card.setMaximumSize(new java.awt.Dimension(
    Integer.MAX_VALUE, 130
));
card.setMinimumSize(new java.awt.Dimension(0, 130));

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

    painelDestino.add(card);
}
  /**
     * Creates new form InteresseProfissional1
     */
    public InteresseProfissional1() {
        initComponents();
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlListaRecomendadas = new javax.swing.JPanel();

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
        jLabel1.setText("Vagas recomendadas");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Minhas Vagas Curtidas");

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlListaRecomendadas.setBackground(new java.awt.Color(16, 39, 71));

        javax.swing.GroupLayout pnlListaRecomendadasLayout = new javax.swing.GroupLayout(pnlListaRecomendadas);
        pnlListaRecomendadas.setLayout(pnlListaRecomendadasLayout);
        pnlListaRecomendadasLayout.setHorizontalGroup(
            pnlListaRecomendadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 806, Short.MAX_VALUE)
        );
        pnlListaRecomendadasLayout.setVerticalGroup(
            pnlListaRecomendadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlListaRecomendadas);

        javax.swing.GroupLayout pnlVagasLayout = new javax.swing.GroupLayout(pnlVagas);
        pnlVagas.setLayout(pnlVagasLayout);
        pnlVagasLayout.setHorizontalGroup(
            pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasLayout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addGroup(pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVagasLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlVagasLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasLayout.createSequentialGroup()
                        .addGroup(pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(629, 629, 629))))
        );
        pnlVagasLayout.setVerticalGroup(
            pnlVagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerDetalhesVaga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEmpresaVaga;
    private javax.swing.JLabel lblLocalizacaoVaga;
    private javax.swing.JLabel lblSalarioVaga;
    private javax.swing.JLabel lblTituloVaga;
    private javax.swing.JPanel pnlCardVaga;
    private javax.swing.JPanel pnlListaRecomendadas;
    private javax.swing.JPanel pnlListaVagas;
    private javax.swing.JPanel pnlVagas;
    // End of variables declaration//GEN-END:variables
}
