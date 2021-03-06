/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.compression;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vishesh
 */
public class MyImages extends javax.swing.JFrame {

    /**
     * Creates new form MyImages
     */
    private static final int IMG_WIDTH = 110;
	private static final int IMG_HEIGHT = 110;
	
    DefaultTableModel midImage;
    String user;
    public MyImages() {
        initComponents();
        user="Guest";
        binddata();
    }
    public MyImages(String u) {
        initComponents();
        user=u;
        binddata();
    }
    void binddata()
    {
        midImage=new DefaultTableModel(){
            public Class getColumnClass(int column){
                return ImageIcon.class;
            }
        };
        try
        {
            myconnection obj=new myconnection();
            String q="select * from tbimages where username=?";
            ResultSet rs;
            PreparedStatement pst=obj.db.prepareStatement(q);
            pst.setString(1,user);
            rs=pst.executeQuery();
            String s[]=new String[]{"image"};
            int i=0;
            midImage.addColumn("");
            midImage.addColumn("");
            midImage.addColumn("");
            midImage.addColumn("");
            int j=0;    
            int c=0;
            midImage.addRow(new Vector());
            while(rs.next())
            {
                /*byte[] bo=rs.getBytes("Original");
                BufferedImage bio=ImageIO.read(new ByteArrayInputStream(bo));
                int type1 = bio.getType() == 0? BufferedImage.TYPE_INT_ARGB : bio.getType();
		BufferedImage OriginalImage = resizeImage(bio, type1);
                ImageIcon fo=new ImageIcon(OriginalImage);*/
                
                //midImage.setValueAt(fo, j, 0);
                if(c==4)
                {
                    midImage.addRow(new Vector());
                    c=0;
                    j++;
                }
                byte[] bc=rs.getBytes("Compressed");
                System.out.println("compressed s "+bc);
                BufferedImage bic=ImageIO.read(new ByteArrayInputStream(bc));
		int type = bic.getType() == 0? BufferedImage.TYPE_INT_ARGB : bic.getType();
		BufferedImage resizeImageJpg = resizeImage(bic, type);
                ImageIcon fc=new ImageIcon(resizeImageJpg);
                midImage.setValueAt(fc, j, c);
                c++;
            }
            jTable1.setModel(midImage);
            jTable1.setRowHeight(90);
            jTable1.setRowMargin(3);
            jTable1.getColumnModel().setColumnMargin(2);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
     private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
		
	return resizedImage;
    }
	

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 102));
        setForeground(java.awt.Color.white);
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Click on Image on Download:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\vishesh bangla\\Desktop\\CUHP2 copy.png")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Click on Download Button to download image");
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        Icon icon = new ImageIcon("yourFile.gif");
        JButton jButton = getButton(optionPane, "DownLoad", icon,jTable1.getSelectedRow(),jTable1.getSelectedColumn(),user);
        optionPane.setOptions(new Object[] { jButton });
        JDialog dialog = optionPane.createDialog(this, "Icon/Text Button");
        dialog.setVisible(true);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Imageupload obj=new Imageupload(user);
        obj.setVisible(true);
        this.setVisible(false);
        obj.setLocation(this.getLocation());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Login obj1=new Login();
        obj1.setVisible(true);
        this.setVisible(false);
        obj1.setLocation(this.getLocation());
    }//GEN-LAST:event_jButton2ActionPerformed

    public static JButton getButton(final JOptionPane optionPane, String text, Icon icon,int row,int col,String us) {
        final JButton button = new JButton(text, icon);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionPane.setValue(button.getText());
                JFileChooser f=new JFileChooser();
                f.setDialogTitle("Specify a file to save");
                f.setFileFilter(new FileNameExtensionFilter("jpeg", "jpeg"));
                f.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
                f.addChoosableFileFilter(new FileNameExtensionFilter("gif", "gif"));
                File dir=new File("C:\\Users\\vishesh bangla\\Desktop");
                f.setCurrentDirectory(dir);
                int s=f.showSaveDialog(null);
                if(s==f.APPROVE_OPTION)
                {
                    try
                    {
                        myconnection obj=new myconnection();
                        String q="select * from tbimages where username=?";
                        ResultSet rs;
                        PreparedStatement pst=obj.db.prepareStatement(q);
                        pst.setString(1,us);
                        rs=pst.executeQuery();
                        int i=0,j=0;
                        while(rs.next())
                        {
                            if(j==4)
                            {
                                j=0;
                                i++;
                            }
                            if(i==row&&j==col)
                            {
                            InputStream in = rs.getBinaryStream(3);
                            OutputStream fo = new FileOutputStream(f.getSelectedFile()+"."+f.getFileFilter().getDescription());
                            i++;
                            int c = 0;
                            byte[] b=new byte[2048];
                            while ((c = in.read(b)) > -1) {
                                fo.write(b,0,c);
                            }
                            fo.close();
                            in.close();
                            }
                            j++;
                        }
                    }
                    catch(Exception e1)
                    {
                        System.out.println(e1.getMessage());
                    }
                }
                System.out.println(button.getText());
            }
        };
        button.addActionListener(actionListener);
        return button;
    }
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
            java.util.logging.Logger.getLogger(MyImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyImages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
