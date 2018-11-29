package principal;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginBook extends javax.swing.JFrame {
    /**
	 * @author wanghley
	 */
	private static final long serialVersionUID = 1L;
	public LoginBook() {
        initComponents();
    }
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnConfirma = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SCB");

        jLabel2.setText("Login");

        btnConfirma.setText("Login");
        btnConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Devolve(evt);
            }
        });

        jLabel3.setText("Usuário:");
        
        btnCancelar = new JButton();
        btnCancelar.setText("Voltar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		Voltar(evt);
        	}
        });
        
        JLabel lblSenha = new JLabel();
        lblSenha.setText("Senha:");
        
        passwordField = new JPasswordField();
        
        textField = new JTextField();
        textField.setColumns(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(37)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(38)
        							.addComponent(jLabel1)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jLabel2)
        							.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE))
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
        					.addComponent(btnConfirma, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        			.addGap(42))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(jLabel2))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblSenha)
        				.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(37)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnConfirma, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void Devolve(java.awt.event.ActionEvent evt){
    	char[] pass = passwordField.getPassword();
    	String pas = "";
    	for (int i = 0; i < pass.length; i++) {
			if(pass[i]!='[') {
				pas+=pass[i];
			}else {
				break;
			}
		}
    	operacoes.Login log = new operacoes.Login();
    	boolean passCompare = log.comparePassword(pas);
    	if(passCompare) {
    		this.dispose();
    		ARBook book = new ARBook();
    		book.setVisible(true);
    	}else{
    		JOptionPane.showMessageDialog(null, "Senha/usuário Inválidos","ERRO",JOptionPane.ERROR_MESSAGE);
    	}
    }
    private void Voltar(ActionEvent evt) {
		this.dispose();
		Begin b = new Begin();
		b.setVisible(true);
		
	}
    public static void main(String args[]) {     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginBook().setVisible(true);
            }
        });
    }
    private javax.swing.JButton btnConfirma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private JButton btnCancelar;
    private JPasswordField passwordField;
    private JTextField textField;
}
