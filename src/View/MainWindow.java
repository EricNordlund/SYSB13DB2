

package View;

import Controller.Controller;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class MainWindow extends javax.swing.JFrame {
Controller controller;
    
    
    public MainWindow() {
        initComponents();
        
    }
    
    /**
     * Får controller-referensen som används för att hämta data.
     * @param controller Controller-objektet.
     */
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
    
    public String getMenuString()
    {
        String data =  (String)tableMenu.getSelectedItem();
        return data;
    }
    
    /**
     * Converts a resultset into a tablemodel and inserts it into the jtable.
     * @param rs The resultset to be used.
     * @param tableData Boolean used to skip timestamp column.
     */
    public void resultSetToTable(ResultSet rs, Boolean tableData) 
    {
        //Hämtar resultaten från employee-tabellen om rs är null
        if(rs == null)
            rs = controller.getTableData(getMenuString());
       try {        
       
        ResultSetMetaData rsmd = rs.getMetaData();
        
        
        //Skapar en vector med kolumnnamn
        int numberOfColumns = 5;
        if(rsmd.getColumnCount() < 5)
            numberOfColumns = rsmd.getColumnCount();
        else if(tableData)
            numberOfColumns++;
                
        Vector columnNames = new Vector();
        
        //Used to skip the first column (timestamp) if the table is not a meta button.
        int column = 1;
        if(tableData)
        column++;
        // Hämtar namnet på varje kolumn.
        for (; column <= numberOfColumns; column++) 
        {
            columnNames.addElement(rsmd.getColumnLabel(column));
        }
        
        
        Vector dataRows = new Vector();

        //Hämtar data för varje ny rad. den börjar hämta vid kolumn två för att inte hämta timestamp.
        while (rs.next()) 
        {
            Vector newRow = new Vector();
            
            //Used to skip the first column (timestamp) if the table is not a meta button.
            int i = 1;
            if(tableData)
            i++;
            
            for (; i <= numberOfColumns; i++) 
            {
                newRow.addElement(rs.getObject(i));
            }

            dataRows.addElement(newRow);
        }
        
        
        //Sätter ut den nya tabellen
        DefaultTableModel dataTableModel = new DefaultTableModel(dataRows, columnNames);
        dataTable.setModel(dataTableModel);
        
        } catch (SQLException ex) 
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        tableScroll = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        tableMenu = new javax.swing.JComboBox();
        dataButton = new javax.swing.JButton();
        lableTabell = new javax.swing.JLabel();
        metaButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        showDBKeys = new javax.swing.JMenuItem();
        showDBIndexes = new javax.swing.JMenuItem();
        showDBConstraints = new javax.swing.JMenuItem();
        showDBTables = new javax.swing.JMenuItem();
        showDBTablesAlternative = new javax.swing.JMenuItem();
        showDBMostPopulatedTable = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Navision DB viewer");
        setResizable(false);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableScroll.setViewportView(dataTable);

        tableMenu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Employee", "Employee Absence", "Employee Qualification", "Employee Relative", "Employee Statistics Group", "Employment Contract" }));
        tableMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableMenuActionPerformed(evt);
            }
        });

        dataButton.setText("Data");
        dataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataButtonActionPerformed(evt);
            }
        });

        lableTabell.setText("Table");

        metaButton.setText("Meta data");
        metaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lableTabell)
                    .addComponent(tableMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(metaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lableTabell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(metaButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        exit.setText("Exit");
        exit.setMinimumSize(new java.awt.Dimension(0, 30));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Database");

        showDBKeys.setText("Show Keys");
        showDBKeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDBKeysActionPerformed(evt);
            }
        });
        jMenu2.add(showDBKeys);

        showDBIndexes.setText("Show Indexes");
        showDBIndexes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDBIndexesActionPerformed(evt);
            }
        });
        jMenu2.add(showDBIndexes);

        showDBConstraints.setText("Show Constraints");
        showDBConstraints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDBConstraintsActionPerformed(evt);
            }
        });
        jMenu2.add(showDBConstraints);

        showDBTables.setText("Show Tables");
        showDBTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDBTablesActionPerformed(evt);
            }
        });
        jMenu2.add(showDBTables);

        showDBTablesAlternative.setText("Show Tables (Alternative)");
        showDBTablesAlternative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDBTablesAlternativeActionPerformed(evt);
            }
        });
        jMenu2.add(showDBTablesAlternative);

        showDBMostPopulatedTable.setText("Show Table (Max rows)");
        showDBMostPopulatedTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDBMostPopulatedTableActionPerformed(evt);
            }
        });
        jMenu2.add(showDBMostPopulatedTable);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataButtonActionPerformed
    
        resultSetToTable(controller.getTableData(getMenuString()), true);
        
    }//GEN-LAST:event_dataButtonActionPerformed

    private void tableMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMenuActionPerformed

    private void metaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metaButtonActionPerformed
        resultSetToTable(controller.getTableMetaData(getMenuString()), false);
    }//GEN-LAST:event_metaButtonActionPerformed

    private void showDBKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDBKeysActionPerformed
         resultSetToTable(controller.getDBKeys(), false);
    }//GEN-LAST:event_showDBKeysActionPerformed

    private void showDBIndexesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDBIndexesActionPerformed
         resultSetToTable(controller.getDBIndexes(), false);
    }//GEN-LAST:event_showDBIndexesActionPerformed

    private void showDBConstraintsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDBConstraintsActionPerformed
         resultSetToTable(controller.getDBConstraints(), false);
    }//GEN-LAST:event_showDBConstraintsActionPerformed

    private void showDBTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDBTablesActionPerformed
         resultSetToTable(controller.getDBTables(), false);
    }//GEN-LAST:event_showDBTablesActionPerformed

    private void showDBTablesAlternativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDBTablesAlternativeActionPerformed
         resultSetToTable(controller.getDBTablesAlternative(), false);
    }//GEN-LAST:event_showDBTablesAlternativeActionPerformed

    private void showDBMostPopulatedTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDBMostPopulatedTableActionPerformed
         resultSetToTable(controller.getDBTableMostRows(), false);
    }//GEN-LAST:event_showDBMostPopulatedTableActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(1);
    }//GEN-LAST:event_exitActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dataButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lableTabell;
    private javax.swing.JButton metaButton;
    private javax.swing.JMenuItem showDBConstraints;
    private javax.swing.JMenuItem showDBIndexes;
    private javax.swing.JMenuItem showDBKeys;
    private javax.swing.JMenuItem showDBMostPopulatedTable;
    private javax.swing.JMenuItem showDBTables;
    private javax.swing.JMenuItem showDBTablesAlternative;
    private javax.swing.JComboBox tableMenu;
    private javax.swing.JScrollPane tableScroll;
    // End of variables declaration//GEN-END:variables
}
