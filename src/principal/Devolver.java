package principal;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import operacoes.Devolucao;

import java.io.IOException;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Devolver extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Devolver() {
        initComponents();
    }
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnConfirma = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SCB");

        jLabel2.setText("Devolucao");

        btnConfirma.setText("Confirmar");
        btnConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					Devolve(evt);
				} catch (WriteException | IOException e) {
					e.printStackTrace();
				}
            }
        });

        jLabel3.setText("Codigo de Emprestimo:");
        
        btnCancelar = new JButton();
        btnCancelar.setText("Voltar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		Voltar(evt);
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(156)
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel2)
        			.addContainerGap(136, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(27)
        					.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(37)
        					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addGap(25)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
        					.addComponent(btnConfirma, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addGap(42))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(jLabel2))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
        					.addComponent(jLabel3)
        					.addGap(72))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(28)
        					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnConfirma, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        setLocationRelativeTo(null);
        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void Devolve(java.awt.event.ActionEvent evt) throws RowsExceededException, WriteException, IOException {
        Devolucao dev = new Devolucao();
    	String codigo = jTextField1.getText();
        boolean codExists = dev.CodeExists(codigo);
        if(codExists) {
        	Date dataDev = dev.getDate();
        	boolean podeDevolverSemMulta = dev.canGiveBack(dataDev);
        	if(podeDevolverSemMulta) {
        		//DevoluÃ§Ã£o normal
        		dev.deleteCodData();
    			dev.setbookData();
    			dev.setUserData();
        		JOptionPane.showMessageDialog(null,"Devolucao Realizada com Sucesso!!!",
    					"DevoluÃ§Ã£o",JOptionPane.INFORMATION_MESSAGE);
        		new Begin().setVisible(true);
        		this.dispose();
        	}else {
        		//Gera Multa
        		Object[] options = { "Pago", "Cancelar" };
        		int opt = JOptionPane.showOptionDialog(null, "Multa gerada no valor de R$"+dev.getTicket(),
        				"Confirma Multa", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        				null, options, options[0]);
        		if(opt==0) {
        			dev.deleteCodData();
        			dev.setbookData();
        			dev.setUserData();
        			JOptionPane.showMessageDialog(null,"Pagamento realizado com sucesso!\nUSUARIO LIBERADO!",
        					null, JOptionPane.PLAIN_MESSAGE);
        			new Begin().setVisible(true);
            		this.dispose();
        		}else {
        			dev.setbookData();
        			JOptionPane.showMessageDialog(null,"Pagamento nao realizado!!\nUSUARIO TRAVADO!!",
        					null, JOptionPane.WARNING_MESSAGE);
        			new Begin().setVisible(true);
            		this.dispose();
        		}
        	}
        }else 
        	JOptionPane.showMessageDialog(null,"Codigo nao Encontrado! Emprestimo nao realizado!",
					"Erro",JOptionPane.ERROR_MESSAGE);
    }
    private void Voltar(java.awt.event.ActionEvent evt) {
    	new Begin().setVisible(true);
    	this.dispose();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Devolver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Devolver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Devolver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Devolver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Devolver().setVisible(true);
            }
        });
    }
    private javax.swing.JButton btnConfirma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private JButton btnCancelar;
}
