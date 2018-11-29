package operacoes;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Books {
	private static Workbook wb = null;
	private static  Cell c1,c2;
	private static WritableWorkbook copy = null;
	private static String pathLinux="src//database//Livros.xls";
	public boolean bookExists(String title) {
		boolean exist = false;
		try {
			wb = Workbook.getWorkbook(new File(pathLinux));
			Sheet sheet = wb.getSheet(0);
			int lines = sheet.getRows();
			for (int i = 0; i < lines; i++) {
				c1 = sheet.getCell(0, i);
				if(c1.getContents().equalsIgnoreCase(title)) {
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
	public void addBook(String title,String author){
		try {
			wb = Workbook.getWorkbook(new File(pathLinux));
			copy = Workbook.createWorkbook(new File(pathLinux), wb);
			Sheet sheet = copy.getSheet(0);
			WritableSheet sheet2 = copy.getSheet(0);
			
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(c1.getContents().equalsIgnoreCase("0")||c1.getContents().equalsIgnoreCase("#")) {
					Label authors = new Label(1,i,author);
					Label titles = new Label(0,i,title);
					Label codigo = new Label(2,i,"0");
					sheet2.addCell(codigo);
					sheet2.addCell(authors);
					sheet2.addCell(titles);
					break;
				}
			}	
		} catch (BiffException | IOException | WriteException e) {
			e.printStackTrace();
		}finally {
			if (wb != null) {
				wb.close();
			}
		} 
		try {
			copy.write();
			copy.close();
		} catch (IOException | WriteException e) {
			e.printStackTrace();
		}		
	}
	public void removeBook(String title) throws RowsExceededException, WriteException ,IOException{
		try {
			wb = Workbook.getWorkbook(new File(pathLinux));
			copy = Workbook.createWorkbook(new File(pathLinux), wb);
			Sheet sheet = copy.getSheet(0);
			WritableSheet sheet2 = copy.getSheet(0);
			
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(c1.getContents().equalsIgnoreCase(title)) {
					Label authors = new Label(1,i,"#");
					Label titles = new Label(0,i,"#");
					Label codigo = new Label(2,i,"0");
					sheet2.addCell(codigo);
					sheet2.addCell(authors);
					sheet2.addCell(titles);
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
	public String getBorrowedBooks() {
		String title="";
		try {
			wb = Workbook.getWorkbook(new File("src//database//Livros.xls"));
			Sheet sheet = wb.getSheet(0);
			int lines = sheet.getRows();
			for (int i = 0; i < lines; i++) {
				c1 = sheet.getCell(2, i);
				c2 = sheet.getCell(0, i);
				if(c1.getContents().equals("1")) {
					title+=c2.getContents();
					if(i<lines-1) {
						title+="#";
					}
				}
			}
			wb.close();
		}catch(IOException | BiffException e) {
			e.printStackTrace();
		}
		return title;
	}
}
