package principal;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import operacoes.Emprestimo;

import javax.swing.JButton;

public class Empresta extends javax.swing.JFrame {
	/**
	 * @author wanghley 
	 */
	private static final long serialVersionUID = 1L;
	public Empresta(){
		setTitle("SBC - Sistema de Controle Bibliotecario");
		initComponents();
	}
	public void initComponents() {
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setFont(new Font("Dialog", Font.BOLD, 18));
		txtUsuario = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jButton1 = new javax.swing.JButton();
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ActionPerformed(evt);
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel2.setText("Emprestimo");

		txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

		jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jButton1.setText("Confirmar");

		JLabel lblDigiteOUsurio = new JLabel("Digite o usuario:");

		JButton btnCancelar = new JButton();
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Voltar(evt);
			}
		});
		btnCancelar.setText("Cancelar");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(Alignment.LEADING)
																.addGroup(layout.createSequentialGroup()
																		.addGap(28)
																		.addComponent(lblDigiteOUsurio))
																.addGroup(layout.createSequentialGroup()
																		.addGap(41)
																		.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
														.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
												.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
														.addContainerGap()
														.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
														.addGap(37)))
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
														.addGap(41))
												.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addGap(233)
										.addComponent(jLabel2)))
						.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jLabel2)
						.addGap(29)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(lblDigiteOUsurio)
										.addGap(31)
										.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addGap(50)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		jList1 = new javax.swing.JList<>();
		jScrollPane1.setViewportView(jList1);
		
		jList1.setModel(new javax.swing.AbstractListModel<String>() { 
			/**
			 * @author wanghley
			 */
			private static final long serialVersionUID = 1L;
			String[] split = new Emprestimo().getBookListjxl().split("#");
			public int getSize() { return split.length; }
			public String getElementAt(int i) { return split[i]; }
		});
		getContentPane().setLayout(layout);
		setLocationRelativeTo(null);
		pack();

	}
	private void ActionPerformed(ActionEvent e) {
		Emprestimo emprestimo = new Emprestimo();
		String user = txtUsuario.getText();
		boolean userExists=false;
		try {
			userExists = emprestimo.UserExists(user);
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String title = jList1.getSelectedValue();
		if(title==null) {
			JOptionPane.showMessageDialog(null,"Selecione um livro para emprestimo!",
					"Erro",JOptionPane.ERROR_MESSAGE);
		}else {
			if(userExists) {
				boolean semDividas=false;;
				boolean emprestado=false;
				try {
					semDividas = emprestimo.UserCanBorrow(user);
					emprestado = emprestimo.BookIsBorrowed(title);
				} catch (WriteException | BiffException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(semDividas && emprestado) {
					String cod = emprestimo.getCodigo();
					Date dataDevolver = emprestimo.getDate();
					try {
						emprestimo.setBorrowCode(Integer.parseInt(cod), user, title,dataDevolver);
						emprestimo.setBookState(title);
						emprestimo.UserBorrowed(user);
					} catch (NumberFormatException | WriteException | BiffException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Livro emprestado com sucesso!!\n"
							+ "Código de Empréstimo:\n"+cod);
					this.dispose();
					Begin b = new Begin();
					b.setLocationRelativeTo(null);
					b.setVisible(true);
				}else {
					if(!semDividas) {
						JOptionPane.showMessageDialog(null,"Usuario com emprestimo pendente!",
								"Erro",JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null,"Livro ja foi emprestado!",
								"Erro",JOptionPane.ERROR_MESSAGE);
					}
				}
			}else {
				if(!userExists) {
					JOptionPane.showMessageDialog(null,"Usuario Inexistente!",
							"Erro",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Livro inexistente!",
							"Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private void Voltar(ActionEvent e) {
		this.dispose();
		Begin begin = new Begin();
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
			java.util.logging.Logger.getLogger(Empresta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Empresta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Empresta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Empresta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Empresta().setVisible(true);

			}
		});
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel2;
	private static javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField txtUsuario;
}
