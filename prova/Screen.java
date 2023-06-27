package com.mycompany.prova;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Screen extends JFrame implements ActionListener{
    JLabel lNome, lRua, lCidade, lEstado, lNum;
    JTextField tfNome, tfRua, tfNum;
    JButton bSalvar, bLimpar, bExcluir, bSair;
    JRadioButton rbM, rbF;
    JCheckBox cbTermos;
    ButtonGroup bg = new ButtonGroup();
    JComboBox cbCidade, cbEstado;
    JMenu mOptions;
    JMenuBar mbBarra;
    JMenuItem miSair;
    String std [] = {"","Maranhão","Piaui"};
    String ma [] = {"Imperatriz", "São Luis"};
    String pi [] = {"Altos", "Teresina"};
    JTable tabela;
    DefaultTableModel modelo;
    JScrollPane scroll;
    String colunas[] = {"Nome", "Rua","Número", "Sexo","Cidade","Estado"};
    String linhas[][] = {{}};
    
    public Screen(){
       setLayout(new FlowLayout());
        
        mOptions = new JMenu("Options");
        mbBarra = new JMenuBar();
        miSair = new JMenuItem("Sair");
        lNome = new JLabel("Nome:");
        lRua = new JLabel("Rua:");
        lCidade = new JLabel("Cidade:");
        lEstado = new JLabel("Estado:");
        lNum = new JLabel("Número:");
        tfNome = new JTextField(20);
        tfRua = new JTextField(10);
        tfNum = new JTextField(5);
        bSalvar = new JButton("Salvar");
        bLimpar = new JButton("Limpar");
        bExcluir= new JButton("Excluir");
        bSair = new JButton("Sair");
        rbM = new JRadioButton("Masculino");
        rbF = new JRadioButton("feminino");
        cbTermos = new JCheckBox("Aceito todos os termos");
        cbEstado = new JComboBox(std);
        cbCidade = new JComboBox();
        tabela = new JTable(linhas, colunas);
        modelo = new DefaultTableModel(linhas, colunas);
        scroll = new JScrollPane();
        
        mOptions.add(miSair);
        mbBarra.add(mOptions);
        setJMenuBar(mbBarra);
        add(lNome);
        add(tfNome);
        add(lRua);
        add(tfRua);
        add(lNum);
        add(tfNum);
        add(rbM);
        add(rbF);
        bg.add(rbM);
        bg.add(rbF);
        add(lEstado);
        add(cbEstado);
        add(lCidade);
        add(cbCidade);
        add(cbTermos);
        add(bSalvar);
        add(bLimpar);
        add(bExcluir);
        add(bSair);
        tabela.setModel(modelo);
        add(tabela);
        scroll.setViewportView(tabela);
        add(scroll);
        
        tfNome.addActionListener(this);
        tfRua.addActionListener(this);
        rbM.addActionListener(this);
        rbF.addActionListener(this);
        cbEstado.addActionListener(this);
        cbTermos.setSelected(true);
        bSalvar.addActionListener(this);
        bLimpar.addActionListener(this);
        bExcluir.addActionListener(this);
        bSair.addActionListener(this);
        miSair.addActionListener(this);
        
        setVisible(true);
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cbEstado){
            if(cbEstado.getSelectedIndex() == 0){
                cbCidade.removeAllItems();
                String vazio[] = {""};
                cbCidade.insertItemAt(vazio[0],0);
            }
            else if(cbEstado.getSelectedIndex() == 1){
                cbCidade.removeAllItems();
                for(int i = 0; i<ma.length; i++){
                    cbCidade.insertItemAt(ma[i],i);
                }
            }
            else if(cbEstado.getSelectedIndex() == 2){
                cbCidade.removeAllItems();
                for(int i = 0; i<pi.length; i++){
                    cbCidade.insertItemAt(pi[i],i);
                }
            }
            
        }
        
        if(e.getSource() == bSalvar){
            if(tfNome.getText().length() > 0){
                String sex = "";
                if(rbM.isSelected() == true){
                sex = "masculino";
                }
                else{
                sex = "feminino";
                }
                String[] texto = {tfNome.getText(), tfRua.getText(),tfNum.getText(), sex, cbCidade.getSelectedItem().toString(), cbEstado.getSelectedItem().toString()};
                modelo.addRow(texto);
            }
            else {
                JOptionPane.showMessageDialog(null, "Existem espaços em branco!");
            }   
        }
        
        if(e.getSource() == bExcluir){
            if(tabela.getSelectedRow()== -1){
                JOptionPane.showMessageDialog(null,"Selecione uma linha!");
            }
            else{
                modelo.removeRow(tabela.getSelectedRow());
            }
        }
        
        if(e.getSource() == bLimpar) {
            tfNome.setText(null);
            tfRua.setText(null);
            tfNum.setText(null);
            bg.clearSelection();
            cbEstado.setSelectedIndex(0);
        }
        
        if(e.getSource() == bSair || e.getSource() == miSair){
            System.exit(0);
        }
    } 
}
