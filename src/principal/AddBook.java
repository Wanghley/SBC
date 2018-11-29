package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import operacoes.Books;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class AddBook extends JFrame {

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
					AddBook frame = new AddBook();
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
	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblAdicionarNovoLivro = new JLabel("Adicionar novo livro:");
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

		JLabel lblTtulo = new JLabel("Título:");
		lblTtulo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTtulo.setBounds(52, 60, 59, 17);
		getContentPane().add(lblTtulo);

		JLabel lblAutor = new JLabel("Autor:");
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
		Books book = new Books();
		String title = textField.getText();
		String autor = textField_1.getText();
		boolean exist = book.bookExists(title);
		if(!exist) {
			book.addBook(title, autor);
			JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso","SUCESSO",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			new Begin().setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null,"Livro ja existe no sistema!",
					"Erro",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void Volta(java.awt.event.ActionEvent evt) {
		this.dispose();
		new ARBook().setVisible(true);
	}
}
