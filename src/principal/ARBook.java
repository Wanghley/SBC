package principal;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ARBook extends JFrame {
	/**
	 * @author wanghley
	 */
	private static final long serialVersionUID = 1L;
	public ARBook() {
		setTitle("SBC");
		this.setSize(300, 237);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnAdicionarNovoLivro = new JButton("Adicionar\n Novo Livro");
		btnAdicionarNovoLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewBook(e);
			}
		});
		btnAdicionarNovoLivro.setBounds(47, 24, 203, 34);
		getContentPane().add(btnAdicionarNovoLivro);
		
		JButton btnRemoverLivroExistente = new JButton("Remover Livro");
		btnRemoverLivroExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveBook(e);
			}
		});
		btnRemoverLivroExistente.setBounds(47, 70, 203, 34);
		getContentPane().add(btnRemoverLivroExistente);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voltar(e);
			}
		});
		btnVoltar.setBounds(47, 163, 203, 27);
		getContentPane().add(btnVoltar);
		
		JButton btnVerificarLivrosEmprestados = new JButton("Livros Emprestados");
		btnVerificarLivrosEmprestados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LivrosEmprestados(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVerificarLivrosEmprestados.setBounds(47, 116, 203, 34);
		getContentPane().add(btnVerificarLivrosEmprestados);
	}
	private void NewBook(java.awt.event.ActionEvent evt) {
		this.dispose();
		new AddBook().setVisible(true);
	}
	private void RemoveBook(java.awt.event.ActionEvent evt) {
		this.dispose();
		new RemoveBook().setVisible(true);
	}
	private void Voltar(java.awt.event.ActionEvent evt) {
		this.dispose();
		new Begin().setVisible(true);
	}
	private void LivrosEmprestados(java.awt.event.ActionEvent evt) throws IOException {
		this.dispose();
		new LivrosEmprestadosList().setVisible(true);
	}
	public static void main() {
		ARBook pd=new ARBook();
		pd.setVisible(true);
	}
}
