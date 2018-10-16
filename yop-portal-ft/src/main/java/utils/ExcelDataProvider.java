package utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;

/**
 * Created by 袁丽霞 on 18/9/25
 * 从excel获取数据的方法
 */
public class ExcelDataProvider {

    Workbook book;
    Sheet sheet;
    Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7;
    int i;

    public void excelReader(String path) {
        try {
            book = Workbook.getWorkbook(new File(path)); //获得要读取的excel文件名
            sheet = book.getSheet(0);//获得第一个工作表对象
            cell1 = sheet.getCell(0, 0);//获得左上角的单元格
            System.out.println("标题是：" + cell1.getContents());
            i = 1;
            while (true) {//获取每一行的单元格
                cell1 = sheet.getCell(0, i);//（列，行）
                cell2 = sheet.getCell(1, i);
                cell3 = sheet.getCell(2, i);
                cell4 = sheet.getCell(3, i);
                cell5 = sheet.getCell(4, i);
                cell6 = sheet.getCell(5, i);
                cell7 = sheet.getCell(6, i);
                if ("".equals(cell1.getContents()) == true)
                    break;
                System.out.println(cell1.getContents() + "\t" + cell2.getContents() + "\t" + cell3.getContents() + "\t" + cell4.getContents()
                        + "\t" + cell5.getContents() + "\t" + cell6.getContents() + "\t" + cell7.getContents());
                i++;
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
