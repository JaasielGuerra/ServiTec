package com.umg.servitecweb.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.umg.servitecweb.repository.IClienteRepo;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@CrossOrigin
@RestController
@RequestMapping("/reportes")
public class RestReportesController {

	@Autowired
	private IClienteRepo clienteRepo;
	

	private static final Logger log = LoggerFactory.getLogger(RestReportesController.class);

	@GetMapping(value = "/print/{id}")
	public @ResponseBody byte[] report(HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {

			
		File pdfFile = File.createTempFile("invoice", ".pdf");

		log.info(String.format("pdf path : %s", pdfFile.getAbsolutePath()));

		try (FileOutputStream pos = new FileOutputStream(pdfFile)) {
			// Load invoice jrxml template.
			//JasperReport report = (JasperReport) JRLoader
				//	.loadObjectFromFile(ResourceUtils.getFile("classpath:rpt_clientes.jrxml").getAbsolutePath());
			
			JasperReport report = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:rpt_clientes.jrxml").getAbsolutePath());

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clienteRepo.findAll());

			// Render as PDF.
			JasperReportsUtils.renderAsPdf(report, null, dataSource, pos);

		} catch (final Exception e) {
			log.error(String.format("An error occured during PDF creation: %s", e));
		}

		byte[] bytes = Files.readAllBytes(Paths.get(pdfFile.getAbsolutePath()));

		return bytes;
	}

}
