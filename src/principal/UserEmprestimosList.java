package principal;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import operacoes.User;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class UserEmprestimosList extends javax.swing.JFrame {
	/**
	 * @author wanghley
	 */
	private static final long serialVersionUID = 1L;
	public UserEmprestimosList() throws IOException {
		setTitle("SBC - Sistema de Controle Bibliotecario");
		initComponents();
		getTitles();
	}
	public void initComponents() throws IOException {
		setLocationRelativeTo(null);
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setFont(new Font("Dialog", Font.BOLD, 18));
		jScrollPane1 = new javax.swing.JScrollPane();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel2.setText("Usuarios com emprestimo(s) pendente:");

		jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JButton btnCancelar = new JButton();
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Voltar(evt);
			}
		});
		btnCancelar.setText("Voltar");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(192, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addGap(188))
				.addGroup(layout.createSequentialGroup()
					.addGap(28)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(jLabel2)
					.addGap(18)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnCancelar)
					.addContainerGap())
		);
		jList1 = new javax.swing.JList<>();
		jScrollPane1.setViewportView(jList1);
		getContentPane().setLayout(layout);
		setLocationRelativeTo(null);
		pack();

	}
	private static void getTitles() throws IOException {
		User user = new User();
		jList1.setModel(new javax.swing.AbstractListModel<String>() { 
			/**
			 * @author wanghley
			 */
			private static final long serialVersionUID = 1L;
			String[] split = user.getUserBorrowed().split("#");
			//String[] split = new Emprestimo().getBookListjxl().split("#");
			public int getSize() { return split.length; }
			public String getElementAt(int i) { return split[i]; }
		});
	}
	private void Voltar(ActionEvent e) {
		this.dispose();
		ARUser begin = new ARUser();
		begin.setLocationRelativeTo(null);
		begin.setVisible(true);
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
			java.util.logging.Logger.getLogger(UserEmprestimosList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UserEmprestimosList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UserEmprestimosList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UserEmprestimosList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new UserEmprestimosList().setVisible(true);
				} catch (IOException ex) {
					Logger.getLogger(UserEmprestimosList.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});
	}
	private javax.swing.JLabel jLabel2;
	private static javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane1;
}
