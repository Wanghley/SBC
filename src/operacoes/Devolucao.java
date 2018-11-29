package operacoes;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Devolucao {
	private static String user,title;
	private static Workbook wb = null;
	private static  Cell c1,c2;
	private static WritableWorkbook copy = null;
	private static int line;
	private static Date dataDev; 
	public boolean CodeExists(String codigo) {
		boolean existe=false;
		try {
			wb = Workbook.getWorkbook(new File("src//database//codigos.xls"));
			Sheet sheet = wb.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				c1 = sheet.getCell(0, i);
				if(codigo.equalsIgnoreCase(c1.getContents())) {
					existe = true;
					c2 = sheet.getCell(1,i);
					user = c2.getContents();
					c2 = sheet.getCell(2,i);
					title = c2.getContents();
					line=i;
					break;
				}
				if(i==sheet.getRows()-1 && !codigo.equalsIgnoreCase(c1.getContents())) {
					existe = false;
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
		return existe;
	}
	public Date getDate() {
		dataDev=null;
		try {
			wb = Workbook.getWorkbook(new File("src//database//codigos.xls"));
			Sheet sheet = wb.getSheet(0);
				c1 = sheet.getCell(3, line);
				String aux = c1.getContents();				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dataDev = sdf.parse(aux);
				} catch (ParseException e) {
					e.printStackTrace();
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
		return dataDev;
	}
	public boolean canGiveBack(Date dataDev) {
		boolean r=true;
		Date hoje = Calendar.getInstance().getTime();
		hoje.setTime(hoje.getTime());
		if(dataDev.compareTo(hoje)<=0) {
			r = false;
		}
		return r;
	}
	public double getTicket() {
		Calendar hoje = Calendar.getInstance();
		Calendar DataDev = new GregorianCalendar();
		DataDev.setTime(dataDev);
		int diasAtraso = Math.abs(DataDev.get(Calendar.DAY_OF_YEAR)-hoje.get(Calendar.DAY_OF_YEAR));
		double multa = diasAtraso*0.5;
		return multa;
	}
	public void deleteCodData() throws RowsExceededException, WriteException, IOException {
		try {
			wb = Workbook.getWorkbook(new File("src//database//codigos.xls"));
			copy = Workbook.createWorkbook(new File("src//database//codigos.xls"), wb);
			WritableSheet sheet2 = copy.getSheet(0);
			Label codigo = new Label(0,line,"0");
			Label user = new Label(1,line,"0");
			Label titles = new Label(2,line,"0");
			Label dataDev = new Label(3,line,"0");
			sheet2.addCell(codigo);
			sheet2.addCell(user);
			sheet2.addCell(titles);
			sheet2.addCell(dataDev);

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
	public void setUserData() throws RowsExceededException, WriteException, IOException {
		try {
			wb = Workbook.getWorkbook(new File("src//database//usuarios.xls"));
			copy = Workbook.createWorkbook(new File("src//database//usuarios.xls"), wb);
			WritableSheet sheet2 = copy.getSheet(0);
			for (int i = 0; i < sheet2.getRows(); i++) {
				c1 = sheet2.getCell(0,i);
				if(c1.getContents().equalsIgnoreCase(user)) {
					Label codigo = new Label(1,i,"0");
					sheet2.addCell(codigo);
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
	public void setbookData() throws RowsExceededException, WriteException, IOException {
		try {
			wb = Workbook.getWorkbook(new File("src//database//Livros.xls"));
			copy = Workbook.createWorkbook(new File("src//database//Livros.xls"), wb);
			WritableSheet sheet2 = copy.getSheet(0);
			for (int i = 0; i < sheet2.getRows(); i++) {
				c1 = sheet2.getCell(0,i);
				if(c1.getContents().equalsIgnoreCase(title)) {
					Label codigo = new Label(2,i,"0");
					sheet2.addCell(codigo);
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
