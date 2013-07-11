package me.entityreborn.chfrontend;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import me.entityreborn.chfrontend.Profiles.Profile;

/**
 *
 * @author import
 */
public class Config extends javax.swing.JFrame {
    private Profile currProfile;
    boolean populatingCombo = false;
    boolean standalone = false;
    
    public Config() {
        this(false);
    }
    
    public Config(boolean standalone) {
        this.standalone = standalone;
        initComponents();
        
        Profiles.load();
        populateCombo();
        
        setProfile("Default");
        
        if (!standalone) {
            setDefaultCloseOperation(HIDE_ON_CLOSE);
        }
    }
    
    private void populateCombo() {
        populatingCombo = true;
        comboProfile.removeAllItems();
        
        for (Profile profile : Profiles.getProfiles()) {
            comboProfile.addItem(profile);
        }
        
        comboProfile.setSelectedItem(currProfile);
        
        populatingCombo = false;
    }
    
    public boolean setProfile(String profile) {
        Profile prof = Profiles.getProfile(profile);
        
        if (prof != null) {
            currProfile = prof;
            comboProfile.setSelectedItem(prof);
            
            return true;
        }
        
        return false;
    }
    
    public void update() {
        update(true);
    }
    
    public void update(boolean reload) {
        if (reload) {
            populateCombo();
        }
        
        txtUsername.setText(currProfile.username);
        txtServOut.setText(currProfile.outgoing);
        txtServIn.setText(currProfile.incoming);
        portOut.setValue(currProfile.outport);
        portIn.setValue(currProfile.inport);
    }
    
    public void load() {
        
    }
    
    public void save() {
        currProfile.username = txtUsername.getText();
        currProfile.outgoing = txtServOut.getText();
        currProfile.incoming = txtServIn.getText();
        currProfile.inport = (Long)portIn.getValue();
        currProfile.outport = (Long)portOut.getValue();
        
        Profiles.save();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtServIn = new javax.swing.JTextField();
        portIn = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        txtServOut = new javax.swing.JTextField();
        portOut = new javax.swing.JSpinner();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        comboProfile = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnRename = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username:");

        txtUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsername.setText("RemoteUser");

        jLabel2.setText("Outgoing address:");

        txtServIn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtServIn.setText("localhost");

        portIn.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(25560L), Long.valueOf(1L), Long.valueOf(65535L), Long.valueOf(1L)));
        portIn.setValue(25560);

        jLabel3.setText("Incoming address:");

        txtServOut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtServOut.setText("localhost");

        portOut.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(25559L), Long.valueOf(1L), Long.valueOf(65535L), Long.valueOf(1L)));
        portOut.setValue(25560);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        comboProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProfileActionPerformed(evt);
            }
        });

        jLabel4.setText("Profile:");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDel.setText("Del");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnRename.setText("Rename");
        btnRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(comboProfile, 0, 156, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRename))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtServIn)
                                    .addComponent(txtServOut))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(portOut, javax.swing.GroupLayout.PREFERRED_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(portIn, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                            .addComponent(txtUsername))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnAdd)
                    .addComponent(btnDel)
                    .addComponent(btnRename))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtServIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtServOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
        
        if (!standalone) {
            setVisible(false);
        } else {
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        update();
        
        if (!standalone) {
            setVisible(false);
        } else {
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        Profile item = (Profile)comboProfile.getSelectedItem();
        
        comboProfile.setSelectedItem(Profiles.getProfile("Default"));
        
        Profiles.delProfile(item);
        
        update();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hhmmss");
        
        String name = "New Profile - " + format.format(now);
        
        Profile prof = new Profile();
        prof.profname = name;
        Profiles.addProfile(prof);
        
        save();
        load();
        
        update();
        
        setProfile(name);
    }//GEN-LAST:event_btnAddActionPerformed

    private void comboProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProfileActionPerformed
        if (populatingCombo) {
            return;
        }
        
        Profile item = (Profile)comboProfile.getSelectedItem();
        
        btnRename.setEnabled(!"Default".equals(item.profname));
        btnDel.setEnabled(!"Default".equals(item.profname));
        
        comboProfile.setSelectedItem(item);
        currProfile = item;

        update(false);
    }//GEN-LAST:event_comboProfileActionPerformed

    private void btnRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameActionPerformed
        Profile prof = (Profile)comboProfile.getSelectedItem();
        
        if ("Default".equals(prof.profname)) {
            JOptionPane.showMessageDialog(this, "Cannot rename the Default profile!",
                "Oops!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String newname = JOptionPane.showInputDialog(this, "Please enter the new name:");
        
        if (newname == null || newname.trim().isEmpty()) {
            return;
        }
        
        if (Profiles.getProfile(newname) != null) {
            JOptionPane.showMessageDialog(this, "That profile name already exists!",
                "Oops!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Profiles.renameProfile(prof, newname);
        
        update();
    }//GEN-LAST:event_btnRenameActionPerformed

    public static void main(String args[]) {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Config(true).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnRename;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox comboProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner portIn;
    private javax.swing.JSpinner portOut;
    private javax.swing.JTextField txtServIn;
    private javax.swing.JTextField txtServOut;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
