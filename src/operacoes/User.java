package operacoes;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class User {
	private static Workbook wb = null;
	private static  Cell c1,c2,c3;
	private static WritableWorkbook copy = null;
	private static String pathLinux="src//database//usuarios.xls";
	public String getUserBorrowed() {
		String user="";
		try {
			wb = Workbook.getWorkbook(new File(pathLinux));
			Sheet sheet = wb.getSheet(0);
			int lines = sheet.getRows();
			for (int i = 0; i < lines; i++) {
				c1 = sheet.getCell(1, i);
				c2 = sheet.getCell(0, i);
				if(c1.getContents().equals("1")) {
					c3 = sheet.getCell(2, i);
					user+=c2.getContents()+"          CPF: "+c3.getContents();
					if(i<lines-1) {
						user+="#";
					}
				}
			}
		}catch(IOException | BiffException e) {
			e.printStackTrace();
		}
		wb.close();
		return user;
	}
	public boolean userExists(String user) {
		boolean exist = false;
		try {
			wb = Workbook.getWorkbook(new File(pathLinux));
			Sheet sheet = wb.getSheet(0);
			int lines = sheet.getRows();
			for (int i = 0; i < lines; i++) {
				c1 = sheet.getCell(0, i);
				if(c1.getContents().equalsIgnoreCase(user)) {
					exist = true;
					break;
				}else if(c1.getContents().equalsIgnoreCase(null)) {
					break;
				}
			}		
		}catch(IOException | BiffException e) {
			e.printStackTrace();
		}
		wb.close();
		return exist;
	}
	public void addUser(String user,String cpf){
		try {
			wb = Workbook.getWorkbook(new File(pathLinux));
			copy = Workbook.createWorkbook(new File(pathLinux), wb);
			Sheet sheet = copy.getSheet(0);
			WritableSheet sheet2 = copy.getSheet(0);
			
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(c1.getContents().equalsIgnoreCase("0")||c1.getContents().equalsIgnoreCase("#")) {
					Label codigo = new Label(1,i,"0");
					Label users = new Label(0,i,user);
					Label cpfs = new Label(2,i,cpf);
					sheet2.addCell(codigo);
					sheet2.addCell(users);
					sheet2.addCell(cpfs);
					break;
				}
			}
			copy.write();
			copy.close();	
		} catch (BiffException | IOException | WriteException e) {
			e.printStackTrace();
		}finally {
			if (wb != null) {
				wb.close();
			}
		} 	
	}
	public void removeUser(String user) throws RowsExceededException, WriteException ,IOException{
		try {
			wb = Workbook.getWorkbook(new File(pathLinux));
			copy = Workbook.createWorkbook(new File(pathLinux), wb);
			Sheet sheet = copy.getSheet(0);
			WritableSheet sheet2 = copy.getSheet(0);
			
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(c1.getContents().equalsIgnoreCase(user)) {
					Label codigo = new Label(1,i,null);
					Label users = new Label(0,i,"#");
					Label cpf = new Label(2,i,null);
					sheet2.addCell(codigo);
					sheet2.addCell(users);
					sheet2.addCell(cpf);
					break;
				}
			}	
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}finally {
			if (wb != null) {
				wb.close();
			}
		} 
		copy.write();
		copy.close();		
	}
}
