package principal;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ARUser extends JFrame {
	/**
	 * @author wanghley
	 */
	private static final long serialVersionUID = 1L;
	public ARUser() {
		setTitle("SBC");
		this.setSize(361, 282);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnAdicionarNovoLivro = new JButton("Cadastrar Novo Usuario");
		btnAdicionarNovoLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUser(e);
			}
		});
		btnAdicionarNovoLivro.setBounds(65, 12, 237, 50);
		getContentPane().add(btnAdicionarNovoLivro);
		
		JButton btnRemoverLivroExistente = new JButton("Remover Usuario");
		btnRemoverLivroExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveUser(e);
			}
		});
		btnRemoverLivroExistente.setBounds(98, 85, 165, 50);
		getContentPane().add(btnRemoverLivroExistente);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voltar(e);
			}
		});
		btnVoltar.setBounds(122, 209, 105, 27);
		getContentPane().add(btnVoltar);
		
		JButton btnUsuriosComEmprstimos = new JButton("Usuarios com Emprestimos");
		btnUsuriosComEmprstimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				UserBorrowed(evt);
			}
		});
		btnUsuriosComEmprstimos.setBounds(65, 147, 237, 50);
		getContentPane().add(btnUsuriosComEmprstimos);
	}
	private void NewUser(java.awt.event.ActionEvent evt) {
		this.dispose();
		new AddUser().setVisible(true);
	}
	private void RemoveUser(java.awt.event.ActionEvent evt) {
		this.dispose();
		new RemoveUser().setVisible(true);
	}
	private void Voltar(java.awt.event.ActionEvent evt) {
		new Begin().setVisible(true);
		this.dispose();
	}
	private void UserBorrowed(ActionEvent evt) {
		this.dispose();
		try {
			UserEmprestimosList a = new UserEmprestimosList();
			a.setLocationRelativeTo(null);
			a.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main() {
		ARUser pd=new ARUser();
		pd.setVisible(true);
	}
}
