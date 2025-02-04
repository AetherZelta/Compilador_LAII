/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectola2;

import compilerTools.Functions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Equipo 3
 */
public class Compiler extends javax.swing.JFrame {

    /**
     * Creates new form Compiler
     */
    public Compiler() {
        initComponents();
        init();
        this.setLocationRelativeTo(this);
        SetImageLabel(jLabel1, "src/images/tecnm4.png");
        SetImageLabel(jLabel2, "src/images/tecnm3.png");

        // Configuración inicial de los botones
        btnLexico.setBackground(new Color(0, 153, 51)); // Verde inicial
        btnSintactico.setBackground(new Color(204, 204, 204)); // Gris inicial
        btnSemantico.setBackground(new Color(204, 204, 204));// Gris inicial

        btnLexico.setOpaque(true);
        btnSintactico.setOpaque(true);
        btnSemantico.setOpaque(true);
    }

    private void init() {
        Functions.setLineNumberOnJTextComponent(jtpCode);

        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"Tipo", "Lexema", "Línea", "Columna", "Valor"}, 0);
        tblSymbols.setModel(modeloTabla);

        StyledDocument doc = jtpCode.getStyledDocument();
        SimpleAttributeSet estiloPalabraReservada = new SimpleAttributeSet();
        StyleConstants.setForeground(estiloPalabraReservada, Color.BLUE);
        StyleConstants.setBold(estiloPalabraReservada, true);

        SimpleAttributeSet estiloTextoNormal = new SimpleAttributeSet();
        StyleConstants.setForeground(estiloTextoNormal, Color.BLACK);
        StyleConstants.setBold(estiloTextoNormal, false);
        jtpCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                resaltarCodigo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                resaltarCodigo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No es necesario para documentos de texto plano
            }

            private void resaltarCodigo() {
                SwingUtilities.invokeLater(() -> {
                    try {
                        String texto = jtpCode.getText();
                        doc.setCharacterAttributes(0, texto.length(), estiloTextoNormal, true); // Limpia estilos previos

                        String[] palabras = texto.split("\\b"); // Divide por límites de palabras
                        int posicion = 0;

                        for (String palabra : palabras) {
                            if (palabrasReservadas.contains(palabra)) {
                                doc.setCharacterAttributes(
                                        posicion, palabra.length(), estiloPalabraReservada, true
                                );
                            }
                            posicion += palabra.length();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });
    }
    private List<Token> tokens;

    private void resetearBotones() {
        btnLexico.setBackground(new Color(0, 153, 51)); // Verde
        btnSintactico.setBackground(new Color(204, 204, 204)); // Gris
        btnSemantico.setBackground(new Color(204, 204, 204)); // Gris
        btnSintactico.setEnabled(false);
        btnSemantico.setEnabled(false);
    }

    private final Set<String> palabrasReservadas = Set.of(
            "int", "float", "boolean", "string", "if", "else", "while", "print", "true", "false"
    );

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnLexico = new javax.swing.JButton();
        btnSintactico = new javax.swing.JButton();
        btnSemantico = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSymbols = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmFile = new javax.swing.JMenu();
        jtbnNew = new javax.swing.JMenuItem();
        jbtnOpen = new javax.swing.JMenuItem();
        jbtnSave = new javax.swing.JMenuItem();
        jmHelp = new javax.swing.JMenu();
        jbtnAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Compilador MiniScript");

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        btnLexico.setBackground(new java.awt.Color(255, 255, 255));
        btnLexico.setForeground(new java.awt.Color(0, 0, 0));
        btnLexico.setText("Lexico");
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });

        btnSintactico.setText("Sintactico");
        btnSintactico.setEnabled(false);
        btnSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSintacticoActionPerformed(evt);
            }
        });

        btnSemantico.setText("Semantico");
        btnSemantico.setEnabled(false);
        btnSemantico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemanticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLexico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSintactico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSemantico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLexico)
                    .addComponent(btnSintactico)
                    .addComponent(btnSemantico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Analisis");

        jScrollPane1.setViewportView(jtpCode);

        jtaConsole.setColumns(20);
        jtaConsole.setRows(5);
        jScrollPane2.setViewportView(jtaConsole);

        tblSymbols.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Tipo"
            }
        ));
        jScrollPane3.setViewportView(tblSymbols);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tabla de Simbolos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jmFile.setText("Archivo");

        jtbnNew.setText("Nuevo");
        jtbnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnNewActionPerformed(evt);
            }
        });
        jmFile.add(jtbnNew);

        jbtnOpen.setText("Abrir");
        jbtnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOpenActionPerformed(evt);
            }
        });
        jmFile.add(jbtnOpen);

        jbtnSave.setText("Guardar");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });
        jmFile.add(jbtnSave);

        jMenuBar1.add(jmFile);

        jmHelp.setText("Ayuda");

        jbtnAbout.setText("Acerca de");
        jbtnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAboutActionPerformed(evt);
            }
        });
        jmHelp.add(jbtnAbout);

        jMenuBar1.add(jmHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexicoActionPerformed
        // TODO add your handling code here:
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        String code = jtpCode.getText();
        tokens = lexer.analyze(code);
        List<LexicalError> errors = lexer.getErrors();
        jtaConsole.setText("");
        resetearBotones();

        if (!errors.isEmpty()) {
            StringBuilder sbErrores = new StringBuilder("Errores léxicos detectados:\n");
            for (LexicalError error : errors) {
                sbErrores.append(error.toString()).append("\n");
            }
            jtaConsole.setText(sbErrores.toString());
            btnLexico.setBackground(new Color(255, 0, 0)); // Rojo si hay errores
            btnSintactico.setEnabled(false);
        } else {
            jtaConsole.setText("Análisis léxico completado sin errores.");
            btnLexico.setBackground(new Color(21, 198, 37)); // Verde
            btnSintactico.setEnabled(true);
        }

        actualizarTablaSimbolos(tokens);
        //analyzeCode();

    }//GEN-LAST:event_btnLexicoActionPerformed

    private void jtbnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnNewActionPerformed
        int option = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas guardar los cambios actuales?",
                "Nuevo Archivo",
                JOptionPane.YES_NO_CANCEL_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            jbtnSave.doClick(); // Simula el clic del botón Guardar
        } else if (option == JOptionPane.CANCEL_OPTION) {
            return; // Cancela la operación
        }

        jtpCode.setText(""); // Limpia el área de texto
        jtaConsole.setText(""); // Limpia la consola (si aplica)
        DefaultTableModel modeloTabla = (DefaultTableModel) tblSymbols.getModel();
        modeloTabla.setRowCount(0);
        resetearBotones();
    }//GEN-LAST:event_jtbnNewActionPerformed

    private void jbtnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOpenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos MSC (*.msc)", "msc");
        fileChooser.setFileFilter(filter);
        jtpCode.setText("");

        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();

                // Verificar que el archivo tenga la extensión ".msc"
                if (!file.getName().endsWith(".msc")) {
                    JOptionPane.showMessageDialog(null, "El archivo seleccionado no tiene la extensión .msc", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DefaultTableModel modeloTabla = (DefaultTableModel) tblSymbols.getModel();
                modeloTabla.setRowCount(0);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                reader.close();
                jtpCode.setText(content.toString()); // Carga el contenido en el área de texto
                resetearBotones();
                JOptionPane.showMessageDialog(null, "Archivo abierto: " + file.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el archivo: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbtnOpenActionPerformed

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos MSC (*.msc)", "msc");
        fileChooser.setFileFilter(filter);

        int option = fileChooser.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();

                // Asegurarse de que el archivo tenga la extensión ".msc"
                if (!file.getName().endsWith(".msc")) {
                    file = new File(file.getAbsolutePath() + ".msc");
                }

                FileWriter writer = new FileWriter(file);
                writer.write(jtpCode.getText()); // Guarda el contenido del área de texto
                writer.close();
                JOptionPane.showMessageDialog(null, "Archivo guardado con éxito en: " + file.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void btnSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSintacticoActionPerformed
        // TODO add your handling code here:
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        String code = jtpCode.getText();
        List<Token> tokens = lexer.analyze(code);
        btnSemantico.setBackground(new Color(204, 204, 204)); // Gris
        btnSemantico.setEnabled(false);
        //btnLexico.setEnabled(false);

        syntaxAnalyzer.analyze(tokens);
        List<SyntaxError> errors = syntaxAnalyzer.getErrors();

        if (!errors.isEmpty()) {
            StringBuilder sbErrores = new StringBuilder("Errores sintácticos detectados:\n");
            for (SyntaxError error : errors) {
                sbErrores.append(error.toString()).append("\n");
            }
            jtaConsole.setText(sbErrores.toString());
            btnSintactico.setBackground(new Color(255, 0, 0)); // Rojo si hay errores
            
        } else {
            jtaConsole.setText("Análisis sintáctico completado sin errores.");
            btnLexico.setBackground(new Color(21, 198, 37)); // Léxico pasa a verde
            btnSintactico.setBackground(new Color(21, 198, 37)); // Sintáctico pasa a verde
            btnSemantico.setEnabled(true); // Opcional: activar el botón semántico
        }

        actualizarTablaSimbolos(tokens);
    }//GEN-LAST:event_btnSintacticoActionPerformed

    private void btnSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemanticoActionPerformed
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        //btnSintactico.setEnabled(false);

        // Obtener el código del área de texto
        String code = jtpCode.getText();

        // Realizar análisis léxico
        List<Token> tokens = lexer.analyze(code);

        // Realizar análisis semántico
        List<SemanticError> errors = semanticAnalyzer.analyze(tokens);

        // Manejar los resultados del análisis semántico
        if (!errors.isEmpty()) {
            // Si hay errores semánticos
            StringBuilder sbErrores = new StringBuilder("Errores semánticos detectados:\n");
            for (SemanticError error : errors) {
                sbErrores.append(error.toString()).append("\n");
            }

            // Mostrar errores en la consola
            jtaConsole.setText(sbErrores.toString());

            // Cambiar el color del botón a rojo
            btnSemantico.setBackground(new Color(255, 0, 0));
        } else {
            // Si no hay errores
            jtaConsole.setText("Análisis semántico completado sin errores.");

            // Cambiar el color del botón a verde
            btnSemantico.setBackground(new Color(21, 198, 37));

            // Opcional: Habilitar siguiente paso o botón
            // Por ejemplo, btnGeneradorCodigo.setEnabled(true);
        }

        // Actualizar tabla de símbolos
        actualizarTablaSimbolos(tokens);
    }//GEN-LAST:event_btnSemanticoActionPerformed

    private void jbtnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAboutActionPerformed
        // Crear el marco "Acerca de"
        JFrame aboutFrame = new JFrame("Acerca de");
        aboutFrame.setSize(600, 500);
        aboutFrame.setLayout(new BorderLayout());

        // Asegurar que se abre en el centro de la pantalla
        aboutFrame.setLocationRelativeTo(null);

        // Panel para imágenes y nombres
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(2, 4, 10, 10)); // 2 filas (imágenes y nombres), 4 columnas

        String[] authors = {"Isaac Salvador Bravo Estrada", "Luis Fernando Mendoza Javalera", "Maria del Carmen Chavez Patiño", "Guillermo Peasland Aguilar"}; // Nombres de autores

        for (int i = 0; i < 4; i++) {
            // Subpanel para cada autor (imagen y nombre)
            JPanel authorPanel = new JPanel();
            authorPanel.setLayout(new BorderLayout());

            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            ImageIcon image = new ImageIcon("src/images/autor" + (i + 1) + ".jpeg"); // Ruta de cada imagen
            imageLabel.setIcon(new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

            JLabel nameLabel = new JLabel(authors[i]);
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Añadir imagen y nombre al panel del autor
            authorPanel.add(imageLabel, BorderLayout.CENTER);
            authorPanel.add(nameLabel, BorderLayout.SOUTH);

            imagePanel.add(authorPanel); // Añadir panel del autor al panel principal
        }

        // Panel para la lista de PDFs
        JPanel linkPanel = new JPanel();
        linkPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Una columna, espacio entre filas

        // Arreglo con los nombres y enlaces de los PDF
        PdfLink[] pdfLinks = {
            new PdfLink("ACTIVIDAD LAII-A_A1", "src/documentacion/2024-08-19_TNM_CELAYA_LAII-A_A1_EQUIPO_03_20030048_BRAVO_ESTRADA_ISAAC_SALVADOR_AGO-DIC24.pdf"),
            new PdfLink("ACTIVIDAD LAII-A_A2", "src/documentacion/2024-08-22_TNM_CELAYA_LAII-A_A2_EQUIPO_03_20030048_BRAVO_ESTRADA_ISAAC_SALVADOR_AGO-DIC24.pdf"),
            new PdfLink("ACTIVIDAD LAII-A_A3", "src/documentacion/2024-08-27_TNM_CELAYA_LAII-A_A3_EQUIPO_03_20030048_BRAVO_ESTRADA_ISAAC_SALVADOR_AGO-DIC24.pdf"),
            new PdfLink("ACTIVIDAD LAII-A_A4", "src/documentacion/2024-09-10_TNM_CELAYA_LAII-A_A4_EQUIPO_3_20030048_BRAVO_ESTRADA_ISAAC SALVADOR_AGO-DIC24.pdf"),
            new PdfLink("ACTIVIDAD LAII-A_A5", "src/documentacion/2024-09-17_TNM_CELAYA_LAII-A_A5_EQUIPO_03_20030048_BRAVO_ESTRADA_ISAAC_SALVADOR_AGO-DIC24.pdf"),
            new PdfLink("ACTIVIDAD LAII-A_A6", "src/documentacion/2024-10-07_TNM_CELAYA_LAII-A_A6_EQUIPO_03_20030048_BRAVO_ESTRADA_ISAAC_SALVADOR_AGO-DIC24.pdf"),
            new PdfLink("ACTIVIDAD LAII-A_A7", "src/documentacion/2024-10-21_TNM_CELAYA_LAII-A_A7_EQUIPO_3_20030048_BRAVO_ESTRADA_ISAAC_SALVADOR_AGO-DIC24.pdf"),
            new PdfLink("ACTIVIDAD LAII-A_A8", "src/documentacion/2024-11-11_TNM_CELAYA_LAII-A_A8_EQUIPO_3_20030048_BRAVO_ESTRADA_ISAAC_SALVADOR_AGO-DIC24.pdf"),};

        // Añadir botones para cada PDF
        for (PdfLink pdf : pdfLinks) {
            JButton linkButton = new JButton(pdf.name); // Usa el nombre personalizado
            linkButton.setHorizontalAlignment(SwingConstants.LEFT);
            linkButton.addActionListener(e -> {
                try {
                    File file = new File(pdf.url); // Convierte la ruta en un archivo
                    Desktop.getDesktop().open(file); // Usa Desktop.open para archivos locales
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo: " + pdf.url);
                }
            });
            linkPanel.add(linkButton);
        }

        // Añadir paneles a la ventana
        aboutFrame.add(imagePanel, BorderLayout.NORTH);
        aboutFrame.add(new JScrollPane(linkPanel), BorderLayout.CENTER);

        // Hacer visible la ventana
        aboutFrame.setVisible(true);
    }//GEN-LAST:event_jbtnAboutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compiler().setVisible(true);
            }
        });
    }

    private void actualizarTablaSimbolos(List<Token> tokens) {
        // Modelo de tabla
        DefaultTableModel modelo = (DefaultTableModel) tblSymbols.getModel();
        modelo.setRowCount(0); // Limpia la tabla

        Map<String, SymbolTableEntry> symbolTable = SyntaxAnalyzer.getSymbolTable();

        for (Token token : tokens) {
            Object valor = "N/A"; // Valor por defecto para tokens que no son variables

            // Si es un identificador (variable), intentar obtener su valor
            if (token.getType() == 400) { // 400 es el tipo de token para identificadores
                SymbolTableEntry entry = symbolTable.get(token.getLexeme());
                if (entry != null) {
                    valor = entry.getCurrentValue();
                }
            }

            // Agregar fila para TODOS los tokens, con valor para variables
            modelo.addRow(new Object[]{
                token.getType(),
                token.getLexeme(),
                token.getLine(),
                token.getColumn(),
                valor // Nueva columna con valor para variables, "N/A" para otros tokens
            });
        }
        /*for (Token token : tokens) {
            modelo.addRow(new Object[]{
                token.getType(),
                token.getLexeme(),
                token.getLine(),
                token.getColumn()
            });
        }*/
    }

    private void SetImageLabel(JLabel labelName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLexico;
    private javax.swing.JButton btnSemantico;
    private javax.swing.JButton btnSintactico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem jbtnAbout;
    private javax.swing.JMenuItem jbtnOpen;
    private javax.swing.JMenuItem jbtnSave;
    private javax.swing.JMenu jmFile;
    private javax.swing.JMenu jmHelp;
    private javax.swing.JTextArea jtaConsole;
    private javax.swing.JMenuItem jtbnNew;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JTable tblSymbols;
    // End of variables declaration//GEN-END:variables
}
