package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import operacoes.User;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class AddUser extends JFrame {

	/**
	 * @author wanghley
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
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
	public AddUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblAdicionarNovoLivro = new JLabel("Cadastro novo Usuario");
		lblAdicionarNovoLivro.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		lblAdicionarNovoLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarNovoLivro.setBounds(0, 12, 500, 17);
		getContentPane().add(lblAdicionarNovoLivro);

		textField = new JTextField();
		textField.setBounds(129, 53, 333, 32);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(129, 115, 333, 32);
		getContentPane().add(textField_1);

		JLabel lblTtulo = new JLabel("Nome:");
		lblTtulo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTtulo.setBounds(52, 60, 59, 17);
		getContentPane().add(lblTtulo);

		JLabel lblAutor = new JLabel("CPF:");
		lblAutor.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAutor.setBounds(52, 122, 59, 17);
		getContentPane().add(lblAutor);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Confirma(evt);
			}
		});
		btnConfirmar.setBounds(286, 187, 133, 32);
		getContentPane().add(btnConfirmar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Volta(evt);
			}
		});
		btnVoltar.setBounds(62, 187, 133, 32);
		getContentPane().add(btnVoltar);
	}

	private void Confirma(java.awt.event.ActionEvent evt) {
		User user = new User();
		String nome = textField.getText();
		String cpf = textField_1.getText();
		boolean userExist = user.userExists(nome);
		if(!userExist) {
			user.addUser(nome, cpf);
			JOptionPane.showMessageDialog(null, "Usuario "+nome+" cadastrado"
					+ " com sucesso","SUCESSO",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			new Begin().setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null,"Usuario ja existe no sistema!",
					"Erro",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void Volta(java.awt.event.ActionEvent evt) {
		this.dispose();
		new ARUser().setVisible(true);
	}
}
