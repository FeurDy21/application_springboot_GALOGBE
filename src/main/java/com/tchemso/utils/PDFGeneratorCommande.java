package com.tchemso.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.CellEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tchemso.entities.Article;
import com.tchemso.entities.CommandeClient;
import com.tchemso.entities.LigneCommandeClient;
import com.tchemso.entities.LigneVente;
import com.tchemso.entities.Vente;
import com.tchemso.web.venteControler;

public class PDFGeneratorCommande {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGeneratorCommande.class);
   
	public static ByteArrayInputStream ArticlePDFReport(List<LigneCommandeClient> ligneCommandeClients,CommandeClient commandeClient) throws IOException {
		Document document = new Document();
		//pour donner une taille au pdf
		document.setPageSize(com.itextpdf.text.PageSize.A6);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLUE);
			Paragraph para = new Paragraph("DYA FREE SHOP", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			Font font2 = FontFactory.getFont(FontFactory.COURIER, 6, BaseColor.BLACK);
		Paragraph para2 = new Paragraph("Aéroport Internaltional FHB\n 07 BP 290 ABIDJAN 07 / Poste 5228\n Tel: 21 75 79 00\n Cell:07 03 79 05", font2);
			para2.setAlignment(Element.ALIGN_CENTER);
			document.add(para2);
			document.add(Chunk.NEWLINE);
          
			
			
			BaseFont bf = BaseFont.createFont(
                    BaseFont.TIMES_ROMAN,
                    BaseFont.CP1252,
                    BaseFont.EMBEDDED);
            Font fontSize = new Font(bf, 7);
            BaseFont be = BaseFont.createFont(
                    BaseFont.TIMES_ROMAN,
                    BaseFont.CP1252,
                    BaseFont.EMBEDDED
                    );
            Font fontSizeEntete = new Font(be, 8);
            
           
           
			PdfPTable table1 = new PdfPTable(2);
			table1.getDefaultCell().setBorder(0);
			 table1.setTotalWidth(50);
			 
			// Add PDF Table Header ->
				Stream.of(" ", " ").forEach(headerTitle -> {
					PdfPCell header = new PdfPCell();
					//Font headFont = FontFactory.getFont();
					header.setBackgroundColor(BaseColor.WHITE);
					header.setHorizontalAlignment(Element.ALIGN_CENTER);
					header.setVerticalAlignment(Element.ALIGN_MIDDLE);
					header.setBorderWidth(0);
					header.setPhrase(new Phrase(headerTitle, fontSizeEntete));
					table1.addCell(header);
				});
			 
			    PdfPCell idCel = new PdfPCell(new Phrase("CLIENT :",fontSize));
				idCel.setPaddingLeft(0);
				idCel.setBorder(0);
				idCel.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCel.setHorizontalAlignment(Element.ALIGN_CENTER);
				Font fon = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLUE);
				
				table1.addCell(idCel);
				
				PdfPCell NameCell = new PdfPCell(new Phrase(commandeClient.getClient().getNom()+" "+ commandeClient.getClient().getPrenom(),fontSize));
				NameCell.setPaddingLeft(-4);
				NameCell.setBorder(0);
				NameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				NameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(NameCell);

			 
			
			PdfPTable table = new PdfPTable(4);
			table.getDefaultCell().setBorder(0);
			 table.setTotalWidth(50);
			float[] widths = {0.1f,1.20f,2.14f,3.14f};
			//table.setWidths(new int[]{100,100,100,100});// largeur par cellule
			//table.DefaultCell().Phrase = new Phrase() { Font = fontNormal };
		
			// Add PDF Table Header ->
			Stream.of("QTE", "DESIGN", "P.U","MONTANT").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				//Font headFont = FontFactory.getFont();
				header.setBackgroundColor(BaseColor.WHITE);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setVerticalAlignment(Element.ALIGN_MIDDLE);
				header.setBorderWidth(0);
				header.setPhrase(new Phrase(headerTitle, fontSizeEntete));
				table.addCell(header);
			});

			for (LigneCommandeClient ligneCommandeClient: ligneCommandeClients) {
				PdfPCell idCell = new PdfPCell(new Phrase(ligneCommandeClient.getQuantite()+" *",fontSize));
				idCell.setPaddingLeft(0);
				idCell.setBorder(0);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				Font font4 = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLUE);
				
				table.addCell(idCell);

				PdfPCell firstNameCell = new PdfPCell(new Phrase(ligneCommandeClient.getArticle().getDesignation(),fontSize));
				firstNameCell.setPaddingLeft(-4);
				firstNameCell.setBorder(0);
				firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(firstNameCell);

				PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(ligneCommandeClient.getPrix()),fontSize));
				lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				lastNameCell.setPaddingLeft(4);
				lastNameCell.setBorder(0);
				
				table.addCell(lastNameCell);
				
				
				PdfPCell lastNameCell2 = new PdfPCell(new Phrase(String.valueOf(ligneCommandeClient.getPrixTotal()),fontSize));
				lastNameCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				lastNameCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				lastNameCell2.setPaddingLeft(4);
				lastNameCell2.setBorder(0);
				table.addCell(lastNameCell2);
			}
			document.add(table);

			Font vent = FontFactory.getFont(FontFactory.COURIER_BOLD, 8, BaseColor.RED);
			Paragraph vpara= new Paragraph("\nTotal Commande:\t\t\t\t\t\t\t\t"+ commandeClient.getTotalCommande()+" F \nNET A PAYER:\t\t\t\t\t\t\t\t"+ commandeClient.getTotalCommande()+" F", vent);
			vpara.setAlignment(Element.ALIGN_CENTER);
			document.add(vpara);
			 
			Paragraph clientpara= new Paragraph("\n ClIENT : "+ commandeClient.getClient().getNom().toLowerCase()+" "+ commandeClient.getClient().getPrenom().toLowerCase()+" \t\t"+" MAIL: "
					+commandeClient.getClient().getMail().toLowerCase()+" \t\t\t\t\t\t\t\t\t",fontSize);
			clientpara.setAlignment(Element.ALIGN_CENTER);
			document.add(clientpara);
			 
				
			SimpleDateFormat formater = null;
             Date aujourdhui = new Date();
             formater = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss");
             
			Paragraph vipara= new Paragraph("\nFacture de commande éditée "+ formater.format(aujourdhui),fontSize);
			vipara.setAlignment(Element.ALIGN_CENTER);
			document.add(vipara);
			
			
			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}