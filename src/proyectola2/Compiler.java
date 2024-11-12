/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectola2;

import compilerTools.Functions;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

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
    }

    private void init() {
        Functions.setLineNumberOnJTextComponent(jtpCode);
    }

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
        jtbnSave = new javax.swing.JMenuItem();
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

        btnLexico.setBackground(new java.awt.Color(0, 153, 51));
        btnLexico.setForeground(new java.awt.Color(0, 0, 0));
        btnLexico.setText("Lexico");
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });

        btnSintactico.setText("Sintactico");
        btnSintactico.setEnabled(false);

        btnSemantico.setText("Semantico");
        btnSemantico.setEnabled(false);

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
        jmFile.add(jtbnNew);

        jbtnOpen.setText("Abrir");
        jmFile.add(jbtnOpen);

        jtbnSave.setText("Guardar");
        jmFile.add(jtbnSave);

        jMenuBar1.add(jmFile);

        jmHelp.setText("Ayuda");

        jbtnAbout.setText("Acerca de");
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
        analyzeCode();
    }//GEN-LAST:event_btnLexicoActionPerformed

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

    public class Token {

        private String type;
        private String value;

        public Token(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("Type: %s, Value: %s", type, value);
        }
    }

    public class SymbolTable {

        private HashMap<String, String> symbols;

        public SymbolTable() {
            symbols = new HashMap<>();
        }

        public void addSymbol(String name, String type) {
            symbols.put(name, type);
        }

        public String getSymbolType(String name) {
            return symbols.get(name);
        }

        public HashMap<String, String> getSymbols() {
            return symbols;
        }
    }

    public class LexicalAnalyzer {

        private SymbolTable symbolTable;
        private ArrayList<Token> tokens;
        private StringBuilder errors;
        private boolean expectingIdentifier;

        private final String[] keywords = {"int", "float", "boolean", "string", "if", "else", "while", "return"};

        public LexicalAnalyzer() {
            symbolTable = new SymbolTable();
            tokens = new ArrayList<>();
            errors = new StringBuilder();
            expectingIdentifier = false;
        }

        public void analyze(String code) {
            tokens.clear();
            errors.setLength(0);
            expectingIdentifier = false;

            String[] lines = code.split("\\n");

            Pattern pattern = Pattern.compile(
                    "\\b(int|float|boolean|string|if|else|while|return)\\b|"
                    + // palabras clave
                    "\\b(true|false)\\b|"
                    + // literales booleanos
                    "\"[^\"]*\"|"
                    + // literales de cadena
                    "[a-zA-Z_][a-zA-Z0-9_]*|"
                    + // identificadores
                    "\\d+\\.\\d+|\\d+|"
                    + // números (int y float)
                    "\\+|\\-|\\*|\\/|=|==|!=|<=|>=|<|>|\\(|\\)|\\{|\\}|;|," // operadores y símbolos
            );

            for (int lineNum = 0; lineNum < lines.length; lineNum++) {
                Matcher matcher = pattern.matcher(lines[lineNum]);

                while (matcher.find()) {
                    String match = matcher.group();

                    if (isKeyword(match)) {
                        tokens.add(new Token("KEYWORD", match));
                        expectingIdentifier = match.equals("int") || match.equals("float")
                                || match.equals("boolean") || match.equals("string");
                    } else if (match.equals("true") || match.equals("false")) { // Literales booleanos
                        tokens.add(new Token("BOOLEAN_LITERAL", match));
                    } else if (match.startsWith("\"") && match.endsWith("\"")) { // Literales de cadena
                        tokens.add(new Token("STRING_LITERAL", match));
                    } else if (match.matches("[a-zA-Z_][a-zA-Z0-9_]*")) { // Identificadores
                        if (expectingIdentifier) {
                            tokens.add(new Token("IDENTIFIER", match));
                            if (!symbolTable.getSymbols().containsKey(match)) {
                                symbolTable.addSymbol(match, "undefined");
                            }
                            expectingIdentifier = false;
                        } else if (!symbolTable.getSymbols().containsKey(match)) {
                            errors.append("Error en línea ").append(lineNum + 1)
                                    .append(": identificador '").append(match).append("' sin tipo de dato.\n");
                        } else {
                            tokens.add(new Token("IDENTIFIER", match));
                        }
                    } else if (match.matches("\\d+\\.\\d+")) { // Float
                        tokens.add(new Token("FLOAT_LITERAL", match));
                    } else if (match.matches("\\d+")) { // Int
                        tokens.add(new Token("INT_LITERAL", match));
                    } else if (match.matches("\\+|\\-|\\*|\\/|=|==|!=|<=|>=|<|>|\\(|\\)|\\{|\\}|;|,")) { // Operadores y símbolos
                        tokens.add(new Token("SYMBOL", match));
                    } else {
                        errors.append("Error en línea ").append(lineNum + 1)
                                .append(": símbolo no reconocido '").append(match).append("'\n");
                    }
                }

                if (expectingIdentifier) {
                    errors.append("Error en línea ").append(lineNum + 1).append(": declaración incompleta, se esperaba un identificador después de tipo de dato.\n");
                    expectingIdentifier = false;
                }
            }
        }

        private boolean isKeyword(String word) {
            for (String keyword : keywords) {
                if (keyword.equals(word)) {
                    return true;
                }
            }
            return false;
        }

        public ArrayList<Token> getTokens() {
            return tokens;
        }

        public String getErrors() {
            return errors.toString();
        }

        public SymbolTable getSymbolTable() {
            return symbolTable;
        }
    }

    private void SetImageLabel(JLabel labelName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }

    private void analyzeCode() {
        String code = jtpCode.getText();
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        lexicalAnalyzer.analyze(code);

        jtaConsole.setText(lexicalAnalyzer.getErrors());

        String errorMessages = lexicalAnalyzer.getErrors();
        if (errorMessages.isEmpty()) {
            jtaConsole.setText("Compilación correcta");
        } else {
            jtaConsole.setText(errorMessages);
        }
        
        updateSymbolTable();

        /*DefaultTableModel model = (DefaultTableModel) symbolTable.getModel();
        model.setRowCount(0); // Clear table
        for (String name : lexicalAnalyzer.getSymbolTable().getSymbols().keySet()) {
            String type = lexicalAnalyzer.getSymbolTable().getSymbols().get(name);
            model.addRow(new Object[]{name, type});
        }*/
    }

    private void updateSymbolTable() {
        // Obtener la tabla de símbolos del analizador léxico
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        SymbolTable symbolTable = lexicalAnalyzer.getSymbolTable();
        
        if (symbolTable.getSymbols().isEmpty()) {
            System.out.println("La tabla de símbolos está vacía.");
        } else {
            System.out.println("Tabla de símbolos contiene " + symbolTable.getSymbols().size() + " símbolos.");
        }

        // Crear un modelo de tabla para actualizar el JTable
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Identificador");
        model.addColumn("Tipo de Dato");

        // Llenar el modelo con los datos de la tabla de símbolos
        for (Map.Entry<String, String> entry : symbolTable.getSymbols().entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        // Establecer el modelo de la tabla en el JTable
        tblSymbols.setModel(model);
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
    private javax.swing.JMenu jmFile;
    private javax.swing.JMenu jmHelp;
    private javax.swing.JTextArea jtaConsole;
    private javax.swing.JMenuItem jtbnNew;
    private javax.swing.JMenuItem jtbnSave;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JTable tblSymbols;
    // End of variables declaration//GEN-END:variables
}
