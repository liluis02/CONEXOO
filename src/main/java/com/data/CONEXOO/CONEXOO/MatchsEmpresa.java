/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

/**
 *
 * @author liluis
 */
public class MatchsEmpresa extends javax.swing.JPanel {
private int idUsuario;
private java.util.function.IntConsumer acaoVerPerfil;



    /**
     * Creates new form MatchsEmpresa
     */
    public MatchsEmpresa() {
        initComponents();
        
    jScrollPane1.setVerticalScrollBarPolicy(
    javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
);

jScrollPane1.setHorizontalScrollBarPolicy(
    javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);
       

jScrollPane1.getViewport().setView(
    Jpnlcurtidasprof
);

jScrollPane1.setHorizontalScrollBarPolicy(
    javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);
        
        jScrollPane1.setViewportView(Jpnlcurtidasprof);

jScrollPane1.setHorizontalScrollBarPolicy(
    javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);
        
        
        Jpnlcurtidasprof.setLayout(
        new javax.swing.BoxLayout(
            Jpnlcurtidasprof,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    // Configuração dos matches
    Jpnlmatcheempresa.setLayout(
        new javax.swing.BoxLayout(
            Jpnlmatcheempresa,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

    // Cores de fundo
    java.awt.Color fundo =
    new java.awt.Color(11, 37, 69);

    Jpnlcurtidasprof.setBackground(fundo);
    Jpnlmatcheempresa.setBackground(fundo);

    jScrollPane1.getViewport().setBackground(fundo);
    jScrollPane2.getViewport().setBackground(fundo);

    // Velocidade da rolagem
    jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
    jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
    
    
jScrollPane1.setViewportView(Jpnlcurtidasprof);

jScrollPane1.setHorizontalScrollBarPolicy(
    javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);

Jpnlcurtidasprof.addComponentListener(
    new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(java.awt.event.ComponentEvent e) {
            Jpnlcurtidasprof.revalidate();
        }
    }
);

    
    }
    
    public void setAcaoVerPerfil(
        java.util.function.IntConsumer acao) {

    this.acaoVerPerfil = acao;
}
    
    
public MatchsEmpresa(int idUsuario) {
    this();

    this.idUsuario = idUsuario;

    carregarProfissionaisCurtidos();
    carregarMatchesEmpresa();
}
    
    
private void carregarProfissionaisCurtidos() {
    Jpnlcurtidasprof.removeAll();
    
        Jpnlcurtidasprof.setLayout(
        new javax.swing.BoxLayout(
            Jpnlcurtidasprof,
            javax.swing.BoxLayout.Y_AXIS
        )
    );

String sql = """
    SELECT
        i.id_candidato,
        i.id_vaga,
        u.nome,
        u.area_atuacao,
        v.titulo AS titulo_vaga,
        i.data_interesse
    FROM interesses i
    INNER JOIN candidatos c
        ON i.id_candidato = c.id_candidato
    INNER JOIN usuarios u
        ON c.id_usuario = u.id
    INNER JOIN vagas v
        ON i.id_vaga = v.id_vaga
    INNER JOIN empresas e
        ON v.id_empresa = e.id_empresa
    WHERE e.id_usuario = ?
      AND i.origem = 'empresa'
    ORDER BY i.data_interesse DESC
    """;

    int quantidade = 0;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst =
                 conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                quantidade++;
                
                
                int idCandidato = rs.getInt("id_candidato");
                int idVaga = rs.getInt("id_vaga");
                String nome = rs.getString("nome");
                String area = rs.getString("area_atuacao");
                
                String tituloVaga = rs.getString("titulo_vaga");

                if (tituloVaga == null || tituloVaga.isBlank()) {
                tituloVaga = "Vaga não informada";
                }

                if (nome == null || nome.isBlank()) {
                    nome = "Profissional sem nome";
                }

                if (area == null || area.isBlank()) {
                    area = "Área não informada";
                }

                // Cartão do profissional
                javax.swing.JPanel card =
                    new javax.swing.JPanel(
                        new java.awt.BorderLayout(15, 0)
                    );

                card.setBackground(
                    new java.awt.Color(20, 55, 90)
                );

                card.setBorder(
    javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(0, 217, 255), 1
        ),
        javax.swing.BorderFactory.createEmptyBorder(
            15, 20, 15, 20
        )
    )
);

                // Nome
                javax.swing.JLabel lblNome =
                    new javax.swing.JLabel(nome);

                lblNome.setFont(
                    new java.awt.Font("Segoe UI", 1, 17)
                );

                lblNome.setForeground(java.awt.Color.WHITE);

                // Área de atuação
                javax.swing.JLabel lblArea =
                    new javax.swing.JLabel(area);

                lblArea.setFont(
                    new java.awt.Font("Segoe UI", 0, 14)
                );

                lblArea.setForeground(
                    new java.awt.Color(168, 201, 235)
                );
                
                javax.swing.JLabel lblVaga =
    new javax.swing.JLabel("Vaga: " + tituloVaga);

lblVaga.setFont(
    new java.awt.Font("Segoe UI", 0, 14)
);

lblVaga.setForeground(
    new java.awt.Color(22, 217, 244)
);

lblVaga.setAlignmentX(
    java.awt.Component.LEFT_ALIGNMENT
);

                // Informações
                javax.swing.JPanel pnlInfo =
                    new javax.swing.JPanel();

                pnlInfo.setOpaque(false);

                pnlInfo.setLayout(
                    new javax.swing.BoxLayout(
                        pnlInfo,
                        javax.swing.BoxLayout.Y_AXIS
                    )
                );

                lblNome.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

                lblArea.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

                pnlInfo.add(lblNome);

                pnlInfo.add(
                    javax.swing.Box.createVerticalStrut(8)
                );

                pnlInfo.add(lblArea);
                
                pnlInfo.add(
    javax.swing.Box.createVerticalStrut(5)
);

pnlInfo.add(lblVaga);

                // Montagem do cartão
                card.add(
                    pnlInfo,
                    java.awt.BorderLayout.CENTER
                );


javax.swing.JButton btnTirarCurtida =
    new javax.swing.JButton("Tirar curtida");

btnTirarCurtida.setFont(
    new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13)
);

btnTirarCurtida.setForeground(java.awt.Color.WHITE);

btnTirarCurtida.setBackground(
    new java.awt.Color(150, 45, 55)
);

btnTirarCurtida.setFocusPainted(false);

btnTirarCurtida.setCursor(
    new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
);

btnTirarCurtida.setPreferredSize(
    new java.awt.Dimension(130, 38)
);

btnTirarCurtida.addActionListener(e -> {
    tirarCurtida(idCandidato, idVaga);
});

javax.swing.JPanel pnlBotoes =
    new javax.swing.JPanel(
        new java.awt.FlowLayout(
            java.awt.FlowLayout.RIGHT, 10, 0
        )
    );

pnlBotoes.setOpaque(false);

pnlBotoes.add(btnTirarCurtida);

javax.swing.JPanel pnlCentralizar =
    new javax.swing.JPanel(new java.awt.GridBagLayout());

pnlCentralizar.setOpaque(false);
pnlCentralizar.add(pnlBotoes);

card.add(
    pnlCentralizar,
    java.awt.BorderLayout.EAST
);


                card.setAlignmentX(
                    java.awt.Component.LEFT_ALIGNMENT
                );

card.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

card.setMaximumSize(
    new java.awt.Dimension(Integer.MAX_VALUE, 110)
);

card.setPreferredSize(
    new java.awt.Dimension(790, 110)
);

card.setMinimumSize(
    new java.awt.Dimension(0, 110)
);

                Jpnlcurtidasprof.add(card);

                Jpnlcurtidasprof.add(
                    javax.swing.Box.createVerticalStrut(10)
                );
            }
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar profissionais curtidos: "
                + e.getMessage()
        );
    }

if (quantidade == 0) {

    Jpnlcurtidasprof.setLayout(
        new java.awt.BorderLayout()
    );

    javax.swing.JPanel pnlVazio =
        new javax.swing.JPanel(new java.awt.GridBagLayout());

    pnlVazio.setBackground(
        new java.awt.Color(13, 39, 72)
    );

    javax.swing.JLabel lblMensagem =
        new javax.swing.JLabel(
            "<html><div style='text-align:center;'>"
            + "<span style='font-size:18px; color:#FFFFFF;'>"
            + "Suas conexões começam aqui!"
            + "</span><br><br>"
            + "<span style='color:#A8C9EB;'>"
            + "Explore profissionais na aba Interesses<br>"
            + "e encontre talentos para sua empresa."
            + "</span></div></html>"
        );

    lblMensagem.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    pnlVazio.add(lblMensagem);

    Jpnlcurtidasprof.add(
        pnlVazio,
        java.awt.BorderLayout.CENTER
    );

} else {

    Jpnlcurtidasprof.setLayout(
        new javax.swing.BoxLayout(
            Jpnlcurtidasprof,
            javax.swing.BoxLayout.Y_AXIS
        )
    );
}


lblQuantidadematchesprof.setText(
    String.valueOf(quantidade)
);
jScrollPane1.setVisible(true);



int alturaConteudo = quantidade == 0
        ? 230
        : Math.max(230, quantidade * 120);

Jpnlcurtidasprof.setPreferredSize(
    new java.awt.Dimension(790, alturaConteudo)
);

Jpnlcurtidasprof.revalidate();
jScrollPane1.revalidate();
jScrollPane1.repaint();

Jpnlcurtidasprof.revalidate();
Jpnlcurtidasprof.repaint();
}

private void carregarMatchesEmpresa() {

    Jpnlmatcheempresa.removeAll();

    String sql = """
        SELECT
            i.id_candidato,
            i.id_vaga, 
            c.id_usuario AS id_usuario_profissional,
            u.nome,
            u.area_atuacao,
            u.email,
            v.titulo AS titulo_vaga
        FROM interesses i
        INNER JOIN candidatos c
            ON i.id_candidato = c.id_candidato
        INNER JOIN usuarios u
            ON c.id_usuario = u.id
        INNER JOIN vagas v
            ON i.id_vaga = v.id_vaga
        INNER JOIN empresas e
            ON v.id_empresa = e.id_empresa
        WHERE e.id_usuario = ?
          AND i.origem = 'empresa'
          AND EXISTS (
              SELECT 1
              FROM interesses ic
              WHERE ic.id_candidato = i.id_candidato
                AND ic.id_vaga = i.id_vaga
                AND ic.origem = 'candidato'
          )
        ORDER BY i.data_interesse DESC
        """;

    int quantidade = 0;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst =
                 conexao.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);

        try (java.sql.ResultSet rs = pst.executeQuery()) {

            
while (rs.next()) {

    quantidade++;

    
    int idUsuarioProfissional =
        rs.getInt("id_usuario_profissional");
    
    String nome = rs.getString("nome");
    String area = rs.getString("area_atuacao");
    String tituloVaga = rs.getString("titulo_vaga");

    if (nome == null || nome.isBlank()) {
        nome = "Profissional sem nome";
    }

    if (area == null || area.isBlank()) {
        area = "Área não informada";
    }

    if (tituloVaga == null || tituloVaga.isBlank()) {
        tituloVaga = "Vaga não informada";
    }

    // Cartão do match
    javax.swing.JPanel card = new javax.swing.JPanel(
        new java.awt.BorderLayout(15, 0)
    );

    card.setBackground(new java.awt.Color(20, 55, 90));

    card.setBorder(
        javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 217, 255), 1
            ),
            javax.swing.BorderFactory.createEmptyBorder(
                15, 20, 15, 20
            )
        )
    );

    // Informações do profissional
    javax.swing.JPanel pnlInfo = new javax.swing.JPanel();
    pnlInfo.setOpaque(false);
    pnlInfo.setLayout(new javax.swing.BoxLayout(
        pnlInfo, javax.swing.BoxLayout.Y_AXIS
    ));

    javax.swing.JLabel lblNome = new javax.swing.JLabel(nome);
    lblNome.setFont(new java.awt.Font("Segoe UI", 1, 17));
    lblNome.setForeground(java.awt.Color.WHITE);

    javax.swing.JLabel lblArea = new javax.swing.JLabel(area);
    lblArea.setFont(new java.awt.Font("Segoe UI", 0, 14));
    lblArea.setForeground(new java.awt.Color(168, 201, 235));

    javax.swing.JLabel lblVaga =
        new javax.swing.JLabel("Vaga: " + tituloVaga);
    lblVaga.setFont(new java.awt.Font("Segoe UI", 0, 14));
    lblVaga.setForeground(new java.awt.Color(22, 217, 244));

    pnlInfo.add(lblNome);
    pnlInfo.add(javax.swing.Box.createVerticalStrut(8));
    pnlInfo.add(lblArea);
    pnlInfo.add(javax.swing.Box.createVerticalStrut(5));
    pnlInfo.add(lblVaga);

    card.add(pnlInfo, java.awt.BorderLayout.CENTER);

    // Botões
    javax.swing.JButton btnVerPerfil =
        new javax.swing.JButton("Ver perfil");
    
    btnVerPerfil.addActionListener(e -> {

    if (acaoVerPerfil != null) {
        acaoVerPerfil.accept(idUsuarioProfissional);
    }

});
    

    javax.swing.JButton btnContato =
        new javax.swing.JButton("Entrar em contato");

    btnVerPerfil.setFont(new java.awt.Font("Segoe UI", 1, 13));
    btnVerPerfil.setForeground(java.awt.Color.WHITE);
    btnVerPerfil.setBackground(new java.awt.Color(8, 127, 167));
    btnVerPerfil.setFocusPainted(false);
    btnVerPerfil.setCursor(new java.awt.Cursor(
        java.awt.Cursor.HAND_CURSOR
    ));

    btnContato.setFont(new java.awt.Font("Segoe UI", 1, 13));
    btnContato.setForeground(java.awt.Color.WHITE);
    btnContato.setBackground(new java.awt.Color(22, 135, 91));
    btnContato.setFocusPainted(false);
    btnContato.setCursor(new java.awt.Cursor(
        java.awt.Cursor.HAND_CURSOR
    ));

    javax.swing.JPanel pnlBotoes = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0)
    );
    pnlBotoes.setOpaque(false);

    pnlBotoes.add(btnVerPerfil);
    pnlBotoes.add(btnContato);

    javax.swing.JPanel pnlCentralizar = new javax.swing.JPanel(
        new java.awt.GridBagLayout()
    );
    pnlCentralizar.setOpaque(false);
    pnlCentralizar.add(pnlBotoes);

    card.add(pnlCentralizar, java.awt.BorderLayout.EAST);

    card.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
    card.setPreferredSize(new java.awt.Dimension(790, 110));
    card.setMaximumSize(
        new java.awt.Dimension(Integer.MAX_VALUE, 110)
    );
    card.setMinimumSize(new java.awt.Dimension(0, 110));

    Jpnlmatcheempresa.add(card);
    Jpnlmatcheempresa.add(
        javax.swing.Box.createVerticalStrut(10)
    );
}

        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar matches: " + e.getMessage()
        );
    }
    
    
    
if (quantidade == 0) {

    Jpnlmatcheempresa.setLayout(
        new java.awt.BorderLayout()
    );

    javax.swing.JPanel pnlVazio =
        new javax.swing.JPanel(new java.awt.GridBagLayout());

    pnlVazio.setBackground(
        new java.awt.Color(13, 39, 72)
    );

    javax.swing.JLabel lblMensagem =
        new javax.swing.JLabel(
            "<html><div style='text-align:center;'>"
            + "<span style='font-size:18px; color:#FFFFFF;'>"
            + "Suas conexões estão a caminho!"
            + "</span><br><br>"
            + "<span style='color:#A8C9EB;'>"
            + "Quando você e um profissional demonstrarem<br>"
            + "interesse um pelo outro, o match aparecerá aqui."
            + "</span></div></html>"
        );

    lblMensagem.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    pnlVazio.add(lblMensagem);

    Jpnlmatcheempresa.add(
        pnlVazio,
        java.awt.BorderLayout.CENTER
    );

} else {

    Jpnlmatcheempresa.setLayout(
        new javax.swing.BoxLayout(
            Jpnlmatcheempresa,
            javax.swing.BoxLayout.Y_AXIS
        )
    );
}

    
    lblQuantidadematchesprof1.setText(
        String.valueOf(quantidade)
    );

    
    int alturaConteudo = Math.max(230, quantidade * 120);

Jpnlmatcheempresa.setPreferredSize(
    new java.awt.Dimension(790, alturaConteudo)
);
    
    Jpnlmatcheempresa.revalidate();
    Jpnlmatcheempresa.repaint();
}


public void atualizarDados() {
    carregarProfissionaisCurtidos();
    carregarMatchesEmpresa();
}


private void tirarCurtida(int idCandidato, int idVaga) {

    int resposta = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "Deseja realmente retirar a curtida deste profissional?",
        "Confirmar remoção",
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.QUESTION_MESSAGE
    );

    if (resposta != javax.swing.JOptionPane.YES_OPTION) {
        return;
    }

    String sql = """
        DELETE i
        FROM interesses i
        INNER JOIN vagas v
            ON i.id_vaga = v.id_vaga
        INNER JOIN empresas e
            ON v.id_empresa = e.id_empresa
        WHERE i.id_candidato = ?
          AND i.id_vaga = ?
          AND i.origem = 'empresa'
          AND e.id_usuario = ?
        """;

    try (java.sql.Connection conexao = Conexao.conectar();
         java.sql.PreparedStatement pst =
                 conexao.prepareStatement(sql)) {

        pst.setInt(1, idCandidato);
        pst.setInt(2, idVaga);
        pst.setInt(3, idUsuario);

        int linhas = pst.executeUpdate();

if (linhas > 0) {

    javax.swing.JOptionPane.showMessageDialog(
        this,
        "Curtida removida com sucesso!"
    );

    carregarProfissionaisCurtidos();
    carregarMatchesEmpresa();
}else {

            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Nenhuma curtida foi encontrada para remover."
            );
        }

    } catch (java.sql.SQLException e) {

        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao remover curtida: " + e.getMessage()
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

        jtitulomatchesprof1 = new javax.swing.JLabel();
        jsubtitulomatchesprof = new javax.swing.JLabel();
        pnlVagasRecomendadas2 = new javax.swing.JPanel();
        lblQuantidadematchesprof = new javax.swing.JLabel();
        lblmacthesprof = new javax.swing.JLabel();
        pnlVagasRecomendadas3 = new javax.swing.JPanel();
        lblQuantidadematchesprof1 = new javax.swing.JLabel();
        lblmacthesprof1 = new javax.swing.JLabel();
        jtitulomatchesprof = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jpnlcurtidasprof = new javax.swing.JPanel();
        jtitulomatchesprof2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Jpnlmatcheempresa = new javax.swing.JPanel();

        setBackground(new java.awt.Color(7, 21, 45));

        jtitulomatchesprof1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jtitulomatchesprof1.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof1.setText("Matches e curtidas da empresa");

        jsubtitulomatchesprof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jsubtitulomatchesprof.setForeground(new java.awt.Color(168, 201, 235));
        jsubtitulomatchesprof.setText("Encontre profissionais para suas vagas");

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
        lblmacthesprof.setText("Sua(s) curtidada(s)");

        javax.swing.GroupLayout pnlVagasRecomendadas2Layout = new javax.swing.GroupLayout(pnlVagasRecomendadas2);
        pnlVagasRecomendadas2.setLayout(pnlVagasRecomendadas2Layout);
        pnlVagasRecomendadas2Layout.setHorizontalGroup(
            pnlVagasRecomendadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas2Layout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addGroup(pnlVagasRecomendadas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas2Layout.createSequentialGroup()
                        .addComponent(lblQuantidadematchesprof)
                        .addGap(180, 180, 180))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas2Layout.createSequentialGroup()
                        .addComponent(lblmacthesprof)
                        .addGap(118, 118, 118))))
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

        pnlVagasRecomendadas3.setBackground(new java.awt.Color(11, 37, 69));
        pnlVagasRecomendadas3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));
        pnlVagasRecomendadas3.setPreferredSize(new java.awt.Dimension(209, 110));

        lblQuantidadematchesprof1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblQuantidadematchesprof1.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidadematchesprof1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuantidadematchesprof1.setText("4");

        lblmacthesprof1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblmacthesprof1.setForeground(new java.awt.Color(168, 201, 235));
        lblmacthesprof1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmacthesprof1.setText("Matches");

        javax.swing.GroupLayout pnlVagasRecomendadas3Layout = new javax.swing.GroupLayout(pnlVagasRecomendadas3);
        pnlVagasRecomendadas3.setLayout(pnlVagasRecomendadas3Layout);
        pnlVagasRecomendadas3Layout.setHorizontalGroup(
            pnlVagasRecomendadas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas3Layout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addGroup(pnlVagasRecomendadas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas3Layout.createSequentialGroup()
                        .addComponent(lblQuantidadematchesprof1)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVagasRecomendadas3Layout.createSequentialGroup()
                        .addComponent(lblmacthesprof1)
                        .addGap(168, 168, 168))))
        );
        pnlVagasRecomendadas3Layout.setVerticalGroup(
            pnlVagasRecomendadas3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVagasRecomendadas3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblQuantidadematchesprof1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblmacthesprof1)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jtitulomatchesprof.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jtitulomatchesprof.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof.setText("Matches da empresa");

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Jpnlcurtidasprof.setBackground(new java.awt.Color(11, 37, 69));

        javax.swing.GroupLayout JpnlcurtidasprofLayout = new javax.swing.GroupLayout(Jpnlcurtidasprof);
        Jpnlcurtidasprof.setLayout(JpnlcurtidasprofLayout);
        JpnlcurtidasprofLayout.setHorizontalGroup(
            JpnlcurtidasprofLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        JpnlcurtidasprofLayout.setVerticalGroup(
            JpnlcurtidasprofLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(Jpnlcurtidasprof);

        jtitulomatchesprof2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jtitulomatchesprof2.setForeground(new java.awt.Color(255, 255, 255));
        jtitulomatchesprof2.setText("Profissionais curtidos");

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Jpnlmatcheempresa.setBackground(new java.awt.Color(11, 37, 69));

        javax.swing.GroupLayout JpnlmatcheempresaLayout = new javax.swing.GroupLayout(Jpnlmatcheempresa);
        Jpnlmatcheempresa.setLayout(JpnlmatcheempresaLayout);
        JpnlmatcheempresaLayout.setHorizontalGroup(
            JpnlmatcheempresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        JpnlmatcheempresaLayout.setVerticalGroup(
            JpnlmatcheempresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(Jpnlmatcheempresa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtitulomatchesprof1)
                            .addComponent(jsubtitulomatchesprof, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pnlVagasRecomendadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pnlVagasRecomendadas3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtitulomatchesprof)
                            .addComponent(jtitulomatchesprof2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(509, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtitulomatchesprof1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsubtitulomatchesprof)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlVagasRecomendadas3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVagasRecomendadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtitulomatchesprof2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtitulomatchesprof)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpnlcurtidasprof;
    private javax.swing.JPanel Jpnlmatcheempresa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jsubtitulomatchesprof;
    private javax.swing.JLabel jtitulomatchesprof;
    private javax.swing.JLabel jtitulomatchesprof1;
    private javax.swing.JLabel jtitulomatchesprof2;
    private javax.swing.JLabel lblQuantidadematchesprof;
    private javax.swing.JLabel lblQuantidadematchesprof1;
    private javax.swing.JLabel lblmacthesprof;
    private javax.swing.JLabel lblmacthesprof1;
    private javax.swing.JPanel pnlVagasRecomendadas2;
    private javax.swing.JPanel pnlVagasRecomendadas3;
    // End of variables declaration//GEN-END:variables
}
