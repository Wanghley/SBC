package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import jxl.write.WriteException;
import operacoes.User;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class RemoveUser extends JFrame {

	/**
	 * @author wanghley
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveUser frame = new RemoveUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RemoveUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 209);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblAdicionarNovoLivro = new JLabel("Remover Usuário");
		lblAdicionarNovoLivro.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		lblAdicionarNovoLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarNovoLivro.setBounds(0, 12, 500, 17);
		getContentPane().add(lblAdicionarNovoLivro);

		textField = new JTextField();
		textField.setBounds(129, 53, 333, 32);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblTtulo = new JLabel("Nome:");
		lblTtulo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTtulo.setBounds(52, 60, 59, 17);
		getContentPane().add(lblTtulo);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Confirma(evt);
			}
		});
		btnConfirmar.setBounds(283, 117, 133, 32);
		getContentPane().add(btnConfirmar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Volta(evt);
			}
		});
		btnVoltar.setBounds(59, 117, 133, 32);
		getContentPane().add(btnVoltar);
	}

	private void Confirma(java.awt.event.ActionEvent evt) {
		User user = new User();
		String nome = textField.getText();
		boolean exist = user.userExists(nome);
		if(exist) {
			try {
				user.removeUser(nome);
			} catch (WriteException | IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!","SUCESSO",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			new Begin().setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "Usuario Inexistente!","ERRO",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void Volta(java.awt.event.ActionEvent evt) {
		this.dispose();
		new ARUser().setVisible(true);
	}
}
