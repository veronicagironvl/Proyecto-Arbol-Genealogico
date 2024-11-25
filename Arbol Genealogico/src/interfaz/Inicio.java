/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author veron
 */
public class Inicio extends javax.swing.JFrame {

    // Variables para almacenar instancias unicas de las ventanas
    private VentanaArbolGenealogico ventanaArbolGenealogico;
    private VentanaListaGeneracion ventanaListaGeneracion;
    private VentanaBuscarPorNombre ventanaBuscarPorNombre;
    private VentanaMostrarAntepasados ventanaMostrarAntepasados;
    private VentanaBuscarPorTitulo ventanaBuscarPorTitulo;
    private VentanaDiagramaDeClases ventanaDiagramaDeClases;

    public Inicio() {
        initComponents();
        // Inicializar las instancias de las ventanas una sola vez
        ventanaArbolGenealogico = new VentanaArbolGenealogico();
        ventanaListaGeneracion = new VentanaListaGeneracion();
        ventanaBuscarPorNombre = new VentanaBuscarPorNombre();
        ventanaMostrarAntepasados = new VentanaMostrarAntepasados();
        ventanaBuscarPorTitulo = new VentanaBuscarPorTitulo();
        ventanaDiagramaDeClases = new VentanaDiagramaDeClases(); 

        // Mostrar inicialmente la ventana de árbol genealógico
        mostrarVentana(ventanaArbolGenealogico);
    }
    
    // Método para mostrar una ventana dentro del panel contenido
    private void mostrarVentana(javax.swing.JFrame ventana) {
        // Elimina el contenido previo del panel
        contenido.removeAll();
        
        // Agrega el contenido del JFrame al panel
        JPanel panel = (JPanel) ventana.getContentPane();
        panel.setSize(1030, 650);
        panel.setLocation(0, 0);
        
        contenido.setLayout(new BorderLayout());
        contenido.add(panel, BorderLayout.CENTER);
        
        // Actualiza el contenido
        contenido.revalidate();
        contenido.repaint();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botonArbolGenealogico = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botonBuscarPorNombre = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        botonMostrarAntepasados = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        botonBuscarPorTitulo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        botonListaGeneracion1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        botonListaGeneracion = new javax.swing.JButton();
        contenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 244, 233));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(247, 244, 233));
        jPanel3.setForeground(new java.awt.Color(247, 244, 233));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/arbol-alternativo.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(75, 46, 25));
        jLabel5.setText("Árbol Genealógico");
        jLabel5.setToolTipText("");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        botonArbolGenealogico.setBackground(new java.awt.Color(247, 244, 233));
        botonArbolGenealogico.setForeground(new java.awt.Color(247, 244, 233));
        botonArbolGenealogico.setBorder(null);
        botonArbolGenealogico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonArbolGenealogicoActionPerformed(evt);
            }
        });
        jPanel3.add(botonArbolGenealogico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 300, 60));

        jPanel4.setBackground(new java.awt.Color(247, 244, 233));
        jPanel4.setForeground(new java.awt.Color(247, 244, 233));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/cabeza-de-conocimiento (1).png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 20, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(75, 46, 25));
        jLabel4.setText("Buscar por Nombre");
        jLabel4.setToolTipText("");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        botonBuscarPorNombre.setBackground(new java.awt.Color(247, 244, 233));
        botonBuscarPorNombre.setBorder(null);
        botonBuscarPorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarPorNombreActionPerformed(evt);
            }
        });
        jPanel4.add(botonBuscarPorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 300, 60));

        jPanel5.setBackground(new java.awt.Color(247, 244, 233));
        jPanel5.setForeground(new java.awt.Color(247, 244, 233));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/vestido-familiar.png"))); // NOI18N
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(75, 46, 25));
        jLabel8.setText("Mostrar Antepasados");
        jLabel8.setToolTipText("");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        botonMostrarAntepasados.setBackground(new java.awt.Color(247, 244, 233));
        botonMostrarAntepasados.setForeground(new java.awt.Color(247, 244, 233));
        botonMostrarAntepasados.setText("jButton1");
        botonMostrarAntepasados.setBorder(null);
        botonMostrarAntepasados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrarAntepasadosActionPerformed(evt);
            }
        });
        jPanel5.add(botonMostrarAntepasados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 300, 60));

        jPanel6.setBackground(new java.awt.Color(247, 244, 233));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/buscar-alt.png"))); // NOI18N
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(75, 46, 25));
        jLabel10.setText("Buscar por Título");
        jLabel10.setToolTipText("");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        botonBuscarPorTitulo.setBackground(new java.awt.Color(247, 244, 233));
        botonBuscarPorTitulo.setForeground(new java.awt.Color(247, 244, 233));
        botonBuscarPorTitulo.setText("jButton2");
        botonBuscarPorTitulo.setBorder(null);
        botonBuscarPorTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarPorTituloActionPerformed(evt);
            }
        });
        jPanel6.add(botonBuscarPorTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 300, 60));

        jPanel7.setBackground(new java.awt.Color(247, 244, 233));
        jPanel7.setForeground(new java.awt.Color(247, 244, 233));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/imagenes/arbol-de-listas.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        botonListaGeneracion1.setBackground(new java.awt.Color(247, 244, 233));
        botonListaGeneracion1.setToolTipText("");
        botonListaGeneracion1.setBorder(null);
        botonListaGeneracion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListaGeneracion1ActionPerformed(evt);
            }
        });
        jPanel7.add(botonListaGeneracion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(75, 46, 25));
        jLabel11.setText("Lista de una Generación");
        jLabel11.setToolTipText("");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 300, 60));

        jLabel6.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(75, 46, 25));
        jLabel6.setText("Ver diagrama de clases");
        jLabel6.setToolTipText("");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, -1, -1));

        botonListaGeneracion.setBackground(new java.awt.Color(247, 244, 233));
        botonListaGeneracion.setToolTipText("");
        botonListaGeneracion.setBorder(null);
        botonListaGeneracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListaGeneracionActionPerformed(evt);
            }
        });
        jPanel1.add(botonListaGeneracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 300, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 650));

        contenido.setBackground(new java.awt.Color(255, 255, 255));
        contenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(contenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 1030, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBuscarPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarPorNombreActionPerformed
        mostrarVentana(ventanaBuscarPorNombre); 
    }//GEN-LAST:event_botonBuscarPorNombreActionPerformed

    private void botonArbolGenealogicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonArbolGenealogicoActionPerformed
        mostrarVentana(ventanaArbolGenealogico); 
    }//GEN-LAST:event_botonArbolGenealogicoActionPerformed

    private void botonListaGeneracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListaGeneracionActionPerformed
        mostrarVentana(ventanaDiagramaDeClases);
    }//GEN-LAST:event_botonListaGeneracionActionPerformed

    private void botonMostrarAntepasadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrarAntepasadosActionPerformed
        mostrarVentana(ventanaMostrarAntepasados);
    }//GEN-LAST:event_botonMostrarAntepasadosActionPerformed

    private void botonBuscarPorTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarPorTituloActionPerformed
        mostrarVentana(ventanaBuscarPorTitulo);
    }//GEN-LAST:event_botonBuscarPorTituloActionPerformed

    private void botonListaGeneracion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListaGeneracion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonListaGeneracion1ActionPerformed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonArbolGenealogico;
    private javax.swing.JButton botonBuscarPorNombre;
    private javax.swing.JButton botonBuscarPorTitulo;
    private javax.swing.JButton botonListaGeneracion;
    private javax.swing.JButton botonListaGeneracion1;
    private javax.swing.JButton botonMostrarAntepasados;
    private javax.swing.JPanel contenido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
