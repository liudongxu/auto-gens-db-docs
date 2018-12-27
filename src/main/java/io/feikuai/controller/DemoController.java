package io.feikuai.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.feikuai.service.GensService;
import lombok.extern.slf4j.Slf4j;

/**
  * demo
  * @author liudo
  * @date 2018/12/26
  */
@Slf4j
@Controller
@RequestMapping("/")
public class DemoController {
	/**
	 * 生成文档服务类
	 */
	@Resource(name = "gensService")
	private GensService gensService;

	/**
	 * 生成文档
	 */
	@RequestMapping("/gens")
	public void code(String tables, HttpServletResponse response) throws IOException {
		byte[] data;
		try {
			data = gensService.autoGensDocs();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"feikuai.docx\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream; charset=UTF-8");

			IOUtils.write(data, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
