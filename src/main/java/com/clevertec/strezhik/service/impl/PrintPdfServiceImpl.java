package com.clevertec.strezhik.service.impl;

import com.clevertec.strezhik.entity.Card;
import com.clevertec.strezhik.entity.Product;
import com.clevertec.strezhik.service.CheckService;
import com.clevertec.strezhik.service.PrintPdfService;
import com.clevertec.strezhik.service.exception.IncorrectInput;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RequiredArgsConstructor
public class PrintPdfServiceImpl implements PrintPdfService {

    private final CheckService simpleCheck;

    public void printPdf(Card card) {
        Document document = new Document();
        Path file = getPathForReceipt();
        PdfWriter writer = getPdfWriter(document, file);
        PdfReader reader = getPdfReader();
        document.open();
        addPageWithImage(document, writer, reader);
        addDiv(document);
        addHeader(document);
        addTableWithProduct(document);
        summaryTable(document, card);
        document.close();
    }

    private PdfReader getPdfReader() {
        PdfReader reader = null;
        try {
            URL resource = PrintPdfServiceImpl.class.getResource("/pdf/Clevertec_Template.pdf");
            String s = Paths.get(resource.toURI()).toString();
            reader = new PdfReader(s);
        } catch (IOException e) {
            throw new IncorrectInput();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return reader;
    }

    private PdfWriter getPdfWriter(Document document, Path file) {
        PdfWriter writer;
        try {
            writer = PdfWriter.getInstance(document, Files.newOutputStream(file));
        } catch (DocumentException | IOException e) {
            throw new IncorrectInput();
        }
        return writer;
    }

    private void summaryTable(Document document, Card card) {
        BigDecimal generalCost = simpleCheck.generalСost().setScale(2, RoundingMode.CEILING);
        PdfPTable pdfPTable = new PdfPTable(2);
        pdfPTable.addCell(new PdfPCell(Phrase.getInstance("Total"))).setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPTable.addCell(new PdfPCell(Phrase.getInstance(String.valueOf(generalCost)))).setHorizontalAlignment(Element.ALIGN_RIGHT);
        if (card != null) {
            pdfPTable.addCell(new PdfPCell(Phrase.getInstance("Discount " + card.getDiscount().toString() + "%"))).setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPTable.addCell(new PdfPCell(Phrase.getInstance(String.valueOf(simpleCheck.discountAmount(card, generalCost)
                    .setScale(2, RoundingMode.CEILING))))).setHorizontalAlignment(Element.ALIGN_RIGHT);
            pdfPTable.addCell(new PdfPCell(Phrase.getInstance("Total without discount"))).setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPTable.addCell(new PdfPCell(Phrase.getInstance(String.valueOf(simpleCheck.costWithDiscount(card, generalCost)
                    .setScale(2, RoundingMode.CEILING))))).setHorizontalAlignment(Element.ALIGN_RIGHT);
        }
        try {
            document.add(pdfPTable);
        } catch (DocumentException e) {
            throw new RuntimeException();
        }
    }

    private void addTableWithProduct(Document document) {
        Map<Product, Integer> map = simpleCheck.getShoppingСart();
        PdfPTable pdfPTable = new PdfPTable(4);
        pdfPTable.addCell(new PdfPCell(Phrase.getInstance("quantity"))).setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPTable.addCell(new PdfPCell(Phrase.getInstance("description"))).setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPTable.addCell(new PdfPCell(Phrase.getInstance("price"))).setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPTable.addCell(new PdfPCell(Phrase.getInstance("total price"))).setHorizontalAlignment(Element.ALIGN_CENTER);
        map.entrySet().stream()
                .peek(set -> pdfPTable.addCell(new PdfPCell(Phrase.getInstance(String.valueOf(set.getValue())))).setBorder(Rectangle.BOTTOM))
                .peek(set -> pdfPTable.addCell(new PdfPCell(Phrase.getInstance(set.getKey().getDescription()))).setBorder(Rectangle.BOTTOM))
                .peek(set -> pdfPTable.addCell(new PdfPCell(Phrase.getInstance(String.valueOf(set.getKey().getPrice())))).setBorder(Rectangle.BOTTOM))
                .peek(set -> pdfPTable.addCell(new PdfPCell(Phrase.getInstance(String
                        .valueOf(simpleCheck.nominationCost(set.getKey(), set.getValue()))))).setBorder(Rectangle.BOTTOM))
                .forEach(Map.Entry::getKey);
        try {
            document.add(pdfPTable);
        } catch (DocumentException e) {
            throw new RuntimeException();
        }
    }

    private void addDiv(Document document) {
        PdfDiv pdfDiv = new PdfDiv();
        pdfDiv.setHeight(250f);
        try {
            document.add(pdfDiv);
        } catch (DocumentException e) {
            throw new IncorrectInput();
        }
    }

    private void addHeader(Document document) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        Paragraph cashReceipt = new Paragraph(
                "CASH RECEIPT" +
                        "\n" + "Prostore" +
                        "\n" + "Kosmonavtov 34" +
                        "\n" + "Phone: +375 25 478-89-40" +
                        "\n" + "Date: " + date +
                        "\n" + "Time: " + time);
        cashReceipt.setAlignment(Element.ALIGN_CENTER);
        try {
            document.add(cashReceipt);
        } catch (DocumentException e) {
            throw new IncorrectInput();
        }
    }

    private void addPageWithImage(Document document, PdfWriter writer, PdfReader reader) {
        PdfImportedPage importedPage = writer.getImportedPage(reader, 1);
        PdfContentByte pdfContentByte = writer.getDirectContent();
        document.newPage();
        pdfContentByte.addTemplate(importedPage, 0, 0);
    }

    private Path getPathForReceipt() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Path tempFile = null;
        try {
            URL resource = PrintPdfServiceImpl.class.getResource("/temp/r.pdf");
            String s = Paths.get(resource.toURI()).toString();
            tempFile = Paths.get(s);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return tempFile;
    }
}
