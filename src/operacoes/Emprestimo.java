package operacoes;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Emprestimo {;
	private static Workbook wb = null;
	private static  Cell c1,c2;
	private static WritableWorkbook copy = null;
	//Substitui AddCodetoXLS.FileWrite()
	public void setBorrowCode(int cod,String users,String title,Date data) 
			throws WriteException, IOException, BiffException  {
		try {
			wb = Workbook.getWorkbook(new File("src//database//codigos.xls"));
			copy = Workbook.createWorkbook(new File("src//database//codigos.xls"), wb);
			Sheet sheet = copy.getSheet(0);
			WritableSheet sheet2 = copy.getSheet(0);
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(c1.getContents().equalsIgnoreCase("0")) {
					Label codigo = new Label(0,i,Integer.toString(cod));
					Label user = new Label(1,i,users);
					Label titles = new Label(2,i,title);
					Label dataDev = new Label(3,i,format.format(data.getTime()).toString());
					sheet2.addCell(codigo);
					sheet2.addCell(user);
					sheet2.addCell(titles);
					sheet2.addCell(dataDev);
					break;
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (wb != null) {
				wb.close();
			}
		} 
		copy.write();
		copy.close();		
	}
	
	public String getBookListjxl() {
		String title="";
		try {
			wb = Workbook.getWorkbook(new File("src//database//Livros.xls"));
			Sheet sheet = wb.getSheet(0);
			int lines = sheet.getRows();
			for (int i = 0; i < lines; i++) {
				Cell c1 = sheet.getCell(0, i);
				if(c1.getContents().equals("0")) {
					break;
				}else if(!c1.getContents().equalsIgnoreCase("#")){
					title+=c1.getContents();
					if(i<lines-1) {
						title+="#";
					}
				}				
			}
		}catch(IOException | BiffException e) {
			e.printStackTrace();
		}
		wb.close();
		return title;
	}
	//Substitui ExcelRead.read()
	/*public String getBookList() throws IOException{
		String title = "";
		HSSFWorkbook wb = null;
        //Worksheet vai usar, aquelas abinhas que tem lá embaixo no Excel
       HSSFSheet sheet;
        try {
         //passa qual WorkBook vai ser usada, de acordo com o caminho
        	
        if(SO.equalsIgnoreCase("windows")) {
        	wb = new HSSFWorkbook(new FileInputStream("src\\\\DataBase\\\\Livros.xls"));
        }else if(SO.equalsIgnoreCase("Linux")) {
        	wb = new HSSFWorkbook(new FileInputStream("src//database//Livros.xls"));
        }
        //Passa qual WorkSheet vai ser usada. Nesse caso a primera (0)
        sheet = wb.getSheetAt(0);
         
         int linhas = sheet.getLastRowNum();
        for (int i = 0; i < linhas; i++) {
            //Cria a linha de acordo com o parametro
            HSSFRow row = sheet.getRow(i);
            //Com a linha criada, pega a célula
            HSSFCell titulo = row.getCell((short)0);
            if(titulo.equals("0")) {
            	break;
            }else if(!titulo.equals("#")){
            	title+=titulo;
                if(i<linhas-1){
                    title+="#";
                }
            }      
        }
        //Pronto, a partir da célula pega a string  
          

    } catch (FileSystemNotFoundException | IOException e) {
        e.printStackTrace();
    } finally {
    	wb.close();
    return title; 
    }
	}*/
	//Substitui IdentificaUSer.existe()
	public boolean UserExists(String nome) throws BiffException {
		boolean existe=false;
		try {
			wb = Workbook.getWorkbook(new File("src//database//usuarios.xls"));
			Sheet sheet = wb.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(nome.equalsIgnoreCase(c1.getContents())) {
					existe = true;
					break;
				}
				if(i==sheet.getRows()-1 && !nome.equalsIgnoreCase(c1.getContents())) {
					existe = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (wb != null) {
				wb.close();
			}
		}
		return existe;
	}
	//Substitui IdentificaUser.podeEmprestar()
	public boolean UserCanBorrow(String nome) throws WriteException, IOException, BiffException {
		boolean autorizado = false;
		try {
			wb = Workbook.getWorkbook(new File("src//database//usuarios.xls"));
			Sheet sheet = wb.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(nome.equalsIgnoreCase(c1.getContents())) {
					c2 = sheet.getCell(1,i);
					if(c2.getContents().equals("0")) {
						autorizado = true;
						break;
					}else {
						autorizado = false;
						break;
					}	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				wb.close();
			}
		}
		return autorizado;		
	}
	//Substitui IdentificaUser.PegouEmprestado()
	public void UserBorrowed(String nome) throws WriteException, IOException {
		try {
			wb = Workbook.getWorkbook(new File("src//database//usuarios.xls"));
			copy = Workbook.createWorkbook(new File("src//database//usuarios.xls"), wb);
			Sheet sheet = copy.getSheet(0);
			WritableSheet sheet2 = copy.getSheet(0); 
			for (int i = 0; i < sheet2.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(nome.equalsIgnoreCase(c1.getContents())) {
					c2 = sheet.getCell(1,i);
					if(c2.getContents().equals("0")) {
						Label label = new Label(1, i, "1"); 
						sheet2.addCell(label);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				wb.close();
			}
		}
		copy.write();
		copy.close();
	}
	//Substitui VerificaLivro.Emprestado()
	public boolean BookIsBorrowed(String title) throws WriteException, IOException {
		boolean opt = false;
		try {
			wb = Workbook.getWorkbook(new File("src//database//Livros.xls"));
			Sheet sheet = wb.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				c2 = sheet.getCell(2,i);
				if(title.equalsIgnoreCase(c1.getContents())) {
					if(c2.getContents().equalsIgnoreCase("0")) {
						opt=true;
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {

			if (wb != null) {
				wb.close();
			}

		}
		return opt;
	}
	//Substitui VerificaLivro.Codigo()
	public String getCodigo(){
		Random num = new Random();
		int aux;
		String CodeEmp = "";
		for (int i = 0; i < 6; i++) {
			aux= num.nextInt(10);
			if(i==0 && aux == 0){
				aux = 1;
			}
			CodeEmp += aux;
		}
		return CodeEmp;
	}
	//Substitui VerificaLivro.ConfirmaEmprestimo()
	public void setBookState(String title) throws IOException, WriteException {
		try {
			wb = Workbook.getWorkbook(new File("src//database//Livros.xls"));
			copy = Workbook.createWorkbook(new File("src//database//Livros.xls"),wb);
			Sheet sheet = wb.getSheet(0);
			WritableSheet sheet2 = copy.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				c2 = sheet.getCell(2,i);
				if(title.equalsIgnoreCase(c1.getContents())) {
					if(c2.getContents().equalsIgnoreCase("0")) {
						Label emprestado = new Label(2,i,"1");
						sheet2.addCell(emprestado);
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {

			if (wb != null) {
				wb.close();
			}

		}
		copy.write();
		copy.close();
	}
	//Substitui DataDevolver.getDateDev
	public Date getDate() {
		Calendar data = Calendar.getInstance();
		int dia = data.get(Calendar.DAY_OF_MONTH);
		int mes = data.get(Calendar.MONTH);
		int ano = data.get(Calendar.YEAR);
		int dia1 = dia+15;
		int mes1 = mes;
		if(dia>15 || (mes1==2 && dia>13)) {
			if(mes1==2) {
				dia1 = Math.abs((dia+15)-28);
			}else{
				dia1 = Math.abs(dia-15);
				if(mes1==12) {
					mes1 = 1;
					ano +=1;
				}else {
					mes1+=1;
				}
			}
		}else {
			dia1 = dia+15;
		}
		Calendar c = Calendar.getInstance();
		c.set(ano, mes1, dia1);
		return c.getTime();
	}
}
