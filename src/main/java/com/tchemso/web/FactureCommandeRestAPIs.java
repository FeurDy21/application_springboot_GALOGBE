package com.tchemso.web;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tchemso.entities.Article;
import com.tchemso.metier.impl_Article;
import com.tchemso.utils.PDFGenerator;

@RestController
@RequestMapping("/api/pdf")
public class FactureCommandeRestAPIs {

	@Autowired
	impl_Article impl_Article;
	

	@GetMapping(value = "/Factur", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customerReport() throws IOException {
		List<Article> article = impl_Article.getAllArticle();

		ByteArrayInputStream bis = PDFGenerator.ArticlePDFReport(article);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=articles.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}