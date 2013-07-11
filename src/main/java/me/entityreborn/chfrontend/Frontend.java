package me.entityreborn.chfrontend;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import me.entityreborn.chfrontend.Profiles.Profile;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

/**
 *
 * @author import
 */
public class Frontend extends JFrame {

    private static Context ctx;
    private static Frontend front;
    private static Profile currProfile;
    Config config;

    public static Context getContext() {
        if (ctx == null) {
            ctx = ZMQ.context(1);
        }

        return ctx;
    }

    public static Frontend getFrontend() {
        if (front == null) {
            front = new Frontend();
        }

        return front;
    }
    private boolean populatingCombo;

    private Frontend() {
        initComponents();

        config = new Config();
        
        populateCombo();
        
        setProfile("Default");

        reconnect();

        txtMessage.grabFocus();
    }
    
    private void populateCombo() {
        populatingCombo = true;
        comboProfiles.removeAllItems();
        
        for (Profile profile : Profiles.getProfiles()) {
            comboProfiles.addItem(profile);
        }
        
        comboProfiles.setSelectedItem(currProfile);
        
        populatingCombo = false;
    }
    
    private void setProfile(String name) {
        Profile prof = Profiles.getProfile(name);
        
        if (prof == null) {
            prof = Profiles.getProfile("Default");
        }
        
        currProfile = prof;
        
        comboProfiles.setSelectedItem(prof);
    }

    public void addLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        final JScrollBar bar = jScrollPane1.getVerticalScrollBar();

        int val = bar.getValue();
        int max = bar.getMaximum() - bar.getVisibleAmount();

        boolean scroll = false;

        if (val == max || max == 0) {
            scroll = true;
        }

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

        txtReceived.append(format.format(now) + " " + line + "\r\n");

        if (scroll) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            bar.setValue(bar.getMaximum());
                        }
                    });
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtReceived = new javax.swing.JTextArea();
        txtMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnConfig = new javax.swing.JButton();
        comboProfiles = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtReceived.setEditable(false);
        txtReceived.setColumns(20);
        txtReceived.setRows(5);
        jScrollPane1.setViewportView(txtReceived);

        txtMessage.setToolTipText("Message to send");
        txtMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMessageKeyPressed(evt);
            }
        });

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtName.setEditable(false);
        txtName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtName.setText("RemoteUser");

        jLabel1.setText("Your name");

        btnConfig.setText("Config");
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });

        comboProfiles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default" }));
        comboProfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProfilesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboProfiles, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfig)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(comboProfiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend)
                    .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfig))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMessageKeyPressed
        btnSend.setEnabled(!txtMessage.getText().trim().isEmpty());

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            send("chat");
        }
    }//GEN-LAST:event_txtMessageKeyPressed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        send("chat");
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        config.setVisible(true);
    }//GEN-LAST:event_btnConfigActionPerformed

    private void comboProfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProfilesActionPerformed
        if (populatingCombo) {
            return;
        }
        
        Profile prof = (Profile)comboProfiles.getSelectedItem();
        currProfile.disconnect();
        currProfile = prof;
        
        updateUI();
        
        reconnect();
    }//GEN-LAST:event_comboProfilesActionPerformed

    private void send(String channel) {
        if (txtMessage.getText().trim().isEmpty()) {
            return;
        }

        if (channel == null || channel.trim().isEmpty()) {
            return;
        }

        String message = txtMessage.getText();
        
        currProfile.send(channel, message);

        txtMessage.setText("");
        btnSend.setEnabled(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnSend;
    private javax.swing.JComboBox comboProfiles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtMessage;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtReceived;
    // End of variables declaration//GEN-END:variables

    void reconnect() {
        currProfile.connect();
    }

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
                Frontend.getFrontend().setVisible(true);
            }
        });
    }

    private void updateUI() {
        txtName.setText(currProfile.getUsername());
    }
}
