package com.example.builder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.org.apache.commons.io.input.BOMInputStream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApachePOIController {

    private static final CSVFormat DEFAULT_CSV_FORMAT = CSVFormat.newFormat(';')
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withIgnoreEmptyLines();

    @GetMapping("/apache-csv")
    public byte[] apacheCsv() throws IOException {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"1", "2"});
        rows.add(new String[]{null, "4"});
        File tempFile = File.createTempFile("temp", ".csv");
        CSVPrinter printer = CSVFormat.RFC4180.withDelimiter(';')
                .withHeader("id", "first_name", "last_name")
                .print(tempFile, Charset.defaultCharset());
        printer.printRecords(rows);
        printer.close(true);

        return FileUtils.readFileToByteArray(tempFile);
    }


    @PostMapping("/csv")
    public List<CSVRecord> parseCsv(
            @RequestPart(value = "file", required = false)
                    MultipartFile multipartFile
    ) throws IOException {
        return parseCSV(IOUtils.toString(multipartFile.getInputStream(), "UTF-8"));
    }

    private List<CSVRecord> parseCSV(String fileContent) throws IOException {
        BOMInputStream bomInputStream = new BOMInputStream(new ByteArrayInputStream(fileContent.getBytes()));
        InputStreamReader inputStreamReader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8);
        BufferedReader fileReader = new BufferedReader(inputStreamReader);
        CSVParser csvParser = new CSVParser(fileReader, DEFAULT_CSV_FORMAT);
        return csvParser.getRecords();
    }
}
