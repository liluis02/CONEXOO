/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class VisualizarPerfilEmpresa extends javax.swing.JPanel {

    private int idUsuario;

    public VisualizarPerfilEmpresa() {
        initComponents();
    }

    public VisualizarPerfilEmpresa(int idUsuario) {
        initComponents();
        this.idUsuario = idUsuario;

        carregarDadosEmpresa();
    }

    private void carregarDadosEmpresa() {

         String sql = "SELECT "
            + "razao_social, "
            + "nome_fantasia, "
            + "porte, "
            + "area_atuacao, "
            + "cnpj, "
            + "telefone, "
            + "estado, "
            + "cidade, "
            + "descricao "
            + "FROM empresas "
            + "WHERE id_usuario = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                lblNomeFantasiaEmpresa1.setText(
                        rs.getString("nome_fantasia")
                );

                lblRazaoSocial1.setText(
                        rs.getString("razao_social")
                );

                lblPorte01.setText(rs.getString("porte"));

                lblAreaAtuacaoEmpresa1.setText(
                        rs.getString("area_atuacao")
                );

                String cnpj = rs.getString("cnpj");

if (cnpj != null && !cnpj.isEmpty()) {
    String numeros = cnpj.replaceAll("\\D", "");

    if (numeros.length() >= 2) {
        String ultimos2 = numeros.substring(numeros.length() - 2);
        lblCNPJEmpresa1.setText("**.***.***/****-" + ultimos2);
    } else {
        lblCNPJEmpresa1.setText("**************");
    }
} else {
    lblCNPJEmpresa1.setText("Não informado");
}

                String telefone = rs.getString("telefone");

if (telefone != null && !telefone.isEmpty()) {
    String numeros = telefone.replaceAll("\\D", "");

    if (numeros.length() >= 4) {
        String ultimos4 = numeros.substring(numeros.length() - 4);
        lblTelefonePerfil.setText("(**) *****-" + ultimos4);
    } else {
        lblTelefonePerfil.setText("****");
    }
} else {
    lblTelefonePerfil.setText("Não informado");
}

                lblEstadoPerfil.setText(rs.getString("estado"));

                lblCidadeEmpresa1.setText(rs.getString("cidade"));

                String descricao = rs.getString("descricao");

                lstSobreaEmpresaDescricao.setListData(
                        new String[]{
                            descricao == null ? "" : descricao
                        }
                );

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum perfil de empresa encontrado."
                );
            }
        }

    } catch (SQLException erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar perfil: " + erro.getMessage()
            );
        }
    }

    // A partir daqui continua o seu initComponents()
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPerfilEmpresa = new javax.swing.JPanel();
        lblTituloPerfilEmpresa = new javax.swing.JLabel();
        lblSubtituloPerfilEmpresa = new javax.swing.JLabel();
        pnlIdentificacao = new javax.swing.JPanel();
        lblNomeFantasiaEmpresa1 = new javax.swing.JLabel();
        lblPorte01 = new javax.swing.JLabel();
        lblRazaoSocial1 = new javax.swing.JLabel();
        lblAreaAtuacaoEmpresa1 = new javax.swing.JLabel();
        lblporteempresa2 = new javax.swing.JLabel();
        lblrazaosocial2 = new javax.swing.JLabel();
        lblareaatuacaoempresa2 = new javax.swing.JLabel();
        pnlInformacoesPessoais = new javax.swing.JPanel();
        lblTituloInformacoesEmpresa1 = new javax.swing.JLabel();
        lblcnpj1 = new javax.swing.JLabel();
        lblCNPJEmpresa1 = new javax.swing.JLabel();
        lblTituloEstado = new javax.swing.JLabel();
        lblEstadoPerfil = new javax.swing.JLabel();
        lblTituloTelefone = new javax.swing.JLabel();
        lblTelefonePerfil = new javax.swing.JLabel();
        lblTituloCidadeEmpresa = new javax.swing.JLabel();
        lblCidadeEmpresa1 = new javax.swing.JLabel();
        pnlHabilidades = new javax.swing.JPanel();
        lblSobreaEmpresa = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstSobreaEmpresaDescricao = new javax.swing.JList<>();
        btnEditarPerfil = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1535, 885));

        jPanelPerfilEmpresa.setBackground(new java.awt.Color(7, 27, 51));
        jPanelPerfilEmpresa.setPreferredSize(new java.awt.Dimension(1535, 885));

        lblTituloPerfilEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTituloPerfilEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPerfilEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTituloPerfilEmpresa.setText("Minha Empresa");

        lblSubtituloPerfilEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubtituloPerfilEmpresa.setForeground(new java.awt.Color(159, 185, 205));
        lblSubtituloPerfilEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSubtituloPerfilEmpresa.setText("Visualize todas as informações da sua empresa!");

        pnlIdentificacao.setBackground(new java.awt.Color(16, 45, 75));
        pnlIdentificacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblNomeFantasiaEmpresa1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblNomeFantasiaEmpresa1.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeFantasiaEmpresa1.setText("Nome Fantasia da Empresa");

        lblPorte01.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPorte01.setForeground(new java.awt.Color(19, 220, 233));
        lblPorte01.setText("Porte da Empresa");

        lblRazaoSocial1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblRazaoSocial1.setForeground(new java.awt.Color(19, 220, 233));
        lblRazaoSocial1.setText("Razão social da sua empresa");

        lblAreaAtuacaoEmpresa1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAreaAtuacaoEmpresa1.setForeground(new java.awt.Color(19, 220, 233));
        lblAreaAtuacaoEmpresa1.setText("Área de atuação");

        lblporteempresa2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblporteempresa2.setForeground(new java.awt.Color(145, 180, 201));
        lblporteempresa2.setText("Porte da empresa");

        lblrazaosocial2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblrazaosocial2.setForeground(new java.awt.Color(145, 180, 201));
        lblrazaosocial2.setText("Razão social");

        lblareaatuacaoempresa2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblareaatuacaoempresa2.setForeground(new java.awt.Color(145, 180, 201));
        lblareaatuacaoempresa2.setText("Área de atuação da empresa");

        javax.swing.GroupLayout pnlIdentificacaoLayout = new javax.swing.GroupLayout(pnlIdentificacao);
        pnlIdentificacao.setLayout(pnlIdentificacaoLayout);
        pnlIdentificacaoLayout.setHorizontalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeFantasiaEmpresa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                        .addGroup(pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAreaAtuacaoEmpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblrazaosocial2)
                            .addComponent(lblRazaoSocial1)
                            .addComponent(lblporteempresa2)
                            .addComponent(lblPorte01)
                            .addComponent(lblareaatuacaoempresa2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlIdentificacaoLayout.setVerticalGroup(
            pnlIdentificacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentificacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeFantasiaEmpresa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblrazaosocial2)
                .addGap(1, 1, 1)
                .addComponent(lblRazaoSocial1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblporteempresa2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPorte01)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblareaatuacaoempresa2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAreaAtuacaoEmpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlInformacoesPessoais.setBackground(new java.awt.Color(16, 45, 75));
        pnlInformacoesPessoais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblTituloInformacoesEmpresa1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTituloInformacoesEmpresa1.setForeground(new java.awt.Color(19, 220, 233));
        lblTituloInformacoesEmpresa1.setText("Informações da empresa");

        lblcnpj1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblcnpj1.setForeground(new java.awt.Color(145, 180, 201));
        lblcnpj1.setText("CNPJ");

        lblCNPJEmpresa1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCNPJEmpresa1.setForeground(new java.awt.Color(255, 255, 255));
        lblCNPJEmpresa1.setText("CNPJ DA EMPRESA");

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

        lblTituloCidadeEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTituloCidadeEmpresa.setForeground(new java.awt.Color(145, 180, 201));
        lblTituloCidadeEmpresa.setText("Cidade");

        lblCidadeEmpresa1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCidadeEmpresa1.setForeground(new java.awt.Color(255, 255, 255));
        lblCidadeEmpresa1.setText("Cidade da Empresa");

        javax.swing.GroupLayout pnlInformacoesPessoaisLayout = new javax.swing.GroupLayout(pnlInformacoesPessoais);
        pnlInformacoesPessoais.setLayout(pnlInformacoesPessoaisLayout);
        pnlInformacoesPessoaisLayout.setHorizontalGroup(
            pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloInformacoesEmpresa1)
                    .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcnpj1)
                            .addComponent(lblCNPJEmpresa1)
                            .addComponent(lblTituloTelefone)
                            .addComponent(lblTelefonePerfil))
                        .addGap(82, 82, 82)
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCidadeEmpresa1)
                            .addComponent(lblTituloCidadeEmpresa)
                            .addComponent(lblEstadoPerfil)
                            .addComponent(lblTituloEstado))))
                .addContainerGap(852, Short.MAX_VALUE))
        );
        pnlInformacoesPessoaisLayout.setVerticalGroup(
            pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloInformacoesEmpresa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInformacoesPessoaisLayout.createSequentialGroup()
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloEstado)
                            .addComponent(lblcnpj1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstadoPerfil)
                            .addComponent(lblCNPJEmpresa1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTituloTelefone))
                    .addComponent(lblTituloCidadeEmpresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCidadeEmpresa1)
                    .addComponent(lblTelefonePerfil))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlHabilidades.setBackground(new java.awt.Color(16, 45, 75));
        pnlHabilidades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lblSobreaEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSobreaEmpresa.setForeground(new java.awt.Color(19, 220, 233));
        lblSobreaEmpresa.setText("Sobre a empresa");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(8, 127, 167)));

        lstSobreaEmpresaDescricao.setBackground(new java.awt.Color(16, 45, 75));
        lstSobreaEmpresaDescricao.setBorder(null);
        lstSobreaEmpresaDescricao.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lstSobreaEmpresaDescricao.setForeground(new java.awt.Color(255, 255, 255));
        lstSobreaEmpresaDescricao.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstSobreaEmpresaDescricao.setSelectionBackground(new java.awt.Color(23, 97, 122));
        lstSobreaEmpresaDescricao.setSelectionForeground(new java.awt.Color(255, 255, 255));
        lstSobreaEmpresaDescricao.setVisibleRowCount(4);
        jScrollPane2.setViewportView(lstSobreaEmpresaDescricao);

        javax.swing.GroupLayout pnlHabilidadesLayout = new javax.swing.GroupLayout(pnlHabilidades);
        pnlHabilidades.setLayout(pnlHabilidadesLayout);
        pnlHabilidadesLayout.setHorizontalGroup(
            pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                        .addComponent(lblSobreaEmpresa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(20, 20, 20))
        );
        pnlHabilidadesLayout.setVerticalGroup(
            pnlHabilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHabilidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSobreaEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditarPerfil.setBackground(new java.awt.Color(19, 220, 233));
        btnEditarPerfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditarPerfil.setForeground(new java.awt.Color(7, 27, 51));
        btnEditarPerfil.setText("️ Editar perfil");
        btnEditarPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarPerfil.setFocusPainted(false);
        btnEditarPerfil.setIconTextGap(10);
        btnEditarPerfil.addActionListener(this::btnEditarPerfilActionPerformed);

        javax.swing.GroupLayout jPanelPerfilEmpresaLayout = new javax.swing.GroupLayout(jPanelPerfilEmpresa);
        jPanelPerfilEmpresa.setLayout(jPanelPerfilEmpresaLayout);
        jPanelPerfilEmpresaLayout.setHorizontalGroup(
            jPanelPerfilEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPerfilEmpresaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelPerfilEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditarPerfil)
                    .addGroup(jPanelPerfilEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblSubtituloPerfilEmpresa)
                        .addComponent(lblTituloPerfilEmpresa)
                        .addComponent(pnlIdentificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlInformacoesPessoais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlHabilidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanelPerfilEmpresaLayout.setVerticalGroup(
            jPanelPerfilEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPerfilEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloPerfilEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtituloPerfilEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInformacoesPessoais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1535, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanelPerfilEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 909, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelPerfilEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPerfilActionPerformed
     PerfilEmpresa perfil = new PerfilEmpresa(idUsuario);

    perfil.setVisible(true);

    javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_btnEditarPerfilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarPerfil;
    private javax.swing.JPanel jPanelPerfilEmpresa;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAreaAtuacaoEmpresa1;
    private javax.swing.JLabel lblCNPJEmpresa1;
    private javax.swing.JLabel lblCidadeEmpresa1;
    private javax.swing.JLabel lblEstadoPerfil;
    private javax.swing.JLabel lblNomeFantasiaEmpresa1;
    private javax.swing.JLabel lblPorte01;
    private javax.swing.JLabel lblRazaoSocial1;
    private javax.swing.JLabel lblSobreaEmpresa;
    private javax.swing.JLabel lblSubtituloPerfilEmpresa;
    private javax.swing.JLabel lblTelefonePerfil;
    private javax.swing.JLabel lblTituloCidadeEmpresa;
    private javax.swing.JLabel lblTituloEstado;
    private javax.swing.JLabel lblTituloInformacoesEmpresa1;
    private javax.swing.JLabel lblTituloPerfilEmpresa;
    private javax.swing.JLabel lblTituloTelefone;
    private javax.swing.JLabel lblareaatuacaoempresa2;
    private javax.swing.JLabel lblcnpj1;
    private javax.swing.JLabel lblporteempresa2;
    private javax.swing.JLabel lblrazaosocial2;
    private javax.swing.JList<String> lstSobreaEmpresaDescricao;
    private javax.swing.JPanel pnlHabilidades;
    private javax.swing.JPanel pnlIdentificacao;
    private javax.swing.JPanel pnlInformacoesPessoais;
    // End of variables declaration//GEN-END:variables
}
