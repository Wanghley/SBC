package principal;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.JButton;
public class Begin extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Begin() {
        initComponents();
    }                         
    private void initComponents() {
        lblTitle = new javax.swing.JLabel();
        btnDevolve = new javax.swing.JButton();
        btnNewUser = new javax.swing.JButton();
        btnEmprestimo = new javax.swing.JButton();
        btnNewLivro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTitle.setText("Sistema de Controle Bibliotecario");

        btnDevolve.setText("Devolucao");
        btnDevolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnDevolveActionPerformed(evt);
            }
        });

        btnNewUser.setText("Administracao Usuarios");
        btnNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnNewUserActionPerformed(evt);
            }
        });

        btnEmprestimo.setText("Emprestimo");
        btnEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnEmprestimoActionPerformed(evt);
            }
        });

        btnNewLivro.setText("Administracao Livros");
        btnNewLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewLivroActionPerformed(evt);
            }
        });
        
        lblIco1 = new JLabel("");
        lblIco1.setIcon(new ImageIcon("src//database//Livros.png"));
        
        lblIco2 = new JLabel("");
        lblIco2.setIcon(new ImageIcon("src//database//Livros.png"));
        
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sair(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(28)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblIco1)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblTitle)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblIco2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
        					.addGap(71))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(btnEmprestimo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnNewLivro, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addComponent(btnDevolve, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnNewUser, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
        					.addGap(47))))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(247)
        			.addComponent(btnSair)
        			.addContainerGap(274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblTitle)
        				.addComponent(lblIco1)
        				.addComponent(lblIco2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
        			.addGap(39)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnEmprestimo, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        					.addGap(52))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnDevolve, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnNewLivro, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
        				.addComponent(btnNewUser, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
        			.addGap(38)
        			.addComponent(btnSair)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        pack();
        setLocationRelativeTo(null);
    }        

    private void btnEmprestimoActionPerformed(java.awt.event.ActionEvent evt){  //empréstimo                                      
        Empresta emp = new Empresta();
        emp.setLocationRelativeTo(null);
        emp.setVisible(true);
        this.dispose();
    }                                        
    private void btnDevolveActionPerformed(java.awt.event.ActionEvent evt) {  //devolução                                       
        Devolver dev = new Devolver();
        dev.setLocationRelativeTo(null);
        dev.setVisible(true);
        this.dispose();
    }                                       
    private void btnNewLivroActionPerformed(java.awt.event.ActionEvent evt) {    //cadastro novo livro                                      
    	this.dispose();
    	LoginBook log = new LoginBook();
    	log.setLocationRelativeTo(null);
    	log.setVisible(true);
    }
    private void btnNewUserActionPerformed(java.awt.event.ActionEvent evt) {      //cadastro novo usuário                                   
        this.dispose();
        new LoginUser().setVisible(true);
    }    
    private void Sair(java.awt.event.ActionEvent evt) {
    	System.exit(0);
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
            java.util.logging.Logger.getLogger(Begin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Begin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Begin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Begin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Begin().setVisible(true);
            }
        });
    }
                    
    private javax.swing.JButton btnDevolve;
    private javax.swing.JButton btnNewUser;
    private javax.swing.JButton btnEmprestimo;
    private javax.swing.JButton btnNewLivro;
    private javax.swing.JLabel lblTitle;
    private JLabel lblIco1;
    private JLabel lblIco2;
}
