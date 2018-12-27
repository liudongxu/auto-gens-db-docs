package io.feikuai.service.impl;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.springframework.stereotype.Service;

import io.feikuai.dao.GensDAO;
import io.feikuai.model.Colum;
import io.feikuai.model.Table;
import io.feikuai.service.GensService;
import lombok.extern.slf4j.Slf4j;

/**
 * 自动生成服务实现
 * @author liudo
 * @date 2018/12/27
 */
@Slf4j
@Service("gensService")
public class GensServiceImpl implements GensService {
	/**
	 * 自动生成文档DAO
	 */
	@Resource(name = "gensDAO")
	GensDAO gensDAO;

	/**
	 * 自动生成文档
	 * @throws Exception 
	 */
	@Override
	public byte[] autoGensDocs() throws Exception {
		// 获取所有的表名
		List<String> tableNames = gensDAO.selectTables();
		// 获取数据库名称
		String dbName = gensDAO.selectDatabase();
		XWPFDocument doc = new XWPFDocument();
		for (String tableName : tableNames) {
			Table table = gensDAO.selectTableDetail(dbName, tableName);
			XWPFParagraph para = doc.createParagraph();
			XWPFRun run = para.createRun();
			run.setBold(true);
			run.setText(table.toString());
			log.info(table.toString());
			List<Colum> colums = gensDAO.selectColums(dbName, tableName);
			XWPFTable tb = doc.createTable(colums.size() + 1, 4);
			List<XWPFTableRow> rows = tb.getRows();
			CTTblPr tablePr = tb.getCTTbl().addNewTblPr();
			CTTblWidth width = tablePr.addNewTblW();
			width.setW(BigInteger.valueOf(10000L));
			for (int i = -1; i < colums.size(); i++) {
				XWPFTableRow row = (XWPFTableRow) rows.get(i + 1);
				row.setHeight(300);

				List<XWPFTableCell> cells = row.getTableCells();
				int cellSize = cells.size();
				for (int j = 0; j < cellSize; j++) {
					XWPFTableCell cell = (XWPFTableCell) cells.get(j);
					if (i >= 0) {
						cell.setText(colums.get(i).getPos(j));
					} else {
						cell.setText(Colum.getColum(j));
					}
					CTTcPr cellPr = cell.getCTTc().addNewTcPr();
					cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
					if (j == 1) {
						cellPr.addNewTcW().setW(BigInteger.valueOf(1500L));
					} else if (j == 3) {
						cellPr.addNewTcW().setW(BigInteger.valueOf(800L));
					} else {
						cellPr.addNewTcW().setW(BigInteger.valueOf(3000L));
					}
				}
			}
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		doc.write(outputStream);
		return outputStream.toByteArray();
	}

}
