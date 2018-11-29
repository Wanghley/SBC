package operacoes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
	public static String finalPass="",finalUser="",p,u;
	protected static void getPassword() {
		try {
			FileReader arq = new FileReader("src/database/untitled");
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			int begin = linha.indexOf("#");
			int fim = linha.indexOf("&");
			p=linha.substring(begin+1, fim);
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}
		
	}
	protected static void Descrypt() {
		char caracs[]=p.toCharArray();
		for (int i = 0; i < caracs.length; i++) {
			int aux = (int)caracs[i];
			aux -=3;
			char c = (char) aux;
			finalPass+=c+"";
		}
	}
	public boolean comparePassword(String password) {
		getPassword();
		Descrypt();
		if(password.equals(finalPass)) {
			return true;
		}else {
			return false;
		}
	}

//User
	protected static void getUser() {
		try {
			FileReader arq = new FileReader("src/database/untitled");
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			int begin = linha.indexOf("§");
			int fim = linha.indexOf("¬");
			u=linha.substring(begin+1, fim);
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}
	}
	protected static void DescryptUser() {
		char caracs[]=u.toCharArray();
		for (int i = 0; i < caracs.length; i++) {
			int aux = (int)caracs[i];
			aux -=3;
			char c = (char) aux;
			finalUser+=c+"";
		}
	}
	public boolean getAdminUser(String admin) {
		System.out.println(finalUser);
		if(admin.equals(finalUser)) {
			return true;
		}else {
			return false;
		}
	}
}