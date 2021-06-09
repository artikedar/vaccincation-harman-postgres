package com.harman.ebook.vaccination.covid.csvparser;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.common.record.RecordMetaData;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Bhagwat Rokade
 * @project Harman Vaccination
 * @created 6/9/2021
 */
@Slf4j
@Component
@NoArgsConstructor
public class CSVParser {

  /**
   *
   * @param file
   */
  public void parseFile(MultipartFile file) {
    try {
      InputStream inputStream  = file.getInputStream();
      CsvParser csvParser = getParser();
      csvParser.beginParsing(inputStream, StandardCharsets.UTF_8);
      Record record;

//      List<String[]> parsedRows = csvParser.parseAll();
//      log.info("parsedRows : ",parsedRows.size());

      while ((record = csvParser.parseNextRecord()) != null) {

         log.info("CSV DATA : ",Arrays.toString(record.getValues()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   *
   * @return
   * @throws Exception
   */
  public CsvParser getParser() throws Exception {
    CsvFormat csvFormat = new CsvFormat();
    csvFormat.setDelimiter(',');
   // csvFormat.setQuote(quoteChar);
    csvFormat.setQuoteEscape('\\');
//        csvFormat.setLineSeparator("\n");
    CsvParserSettings csvParserSettings = new CsvParserSettings();
    csvParserSettings.setFormat(csvFormat);
    csvParserSettings.setMaxCharsPerColumn(500);
    csvParserSettings.setMaxColumns(50);
    csvParserSettings.setLineSeparatorDetectionEnabled(true);
    // csvParserSettings.setNumberOfRowsToSkip(skipLines);
    CsvParser csvParser = new CsvParser(csvParserSettings);
    return csvParser;
  }

  /**
   *
   * @param csvParser
   * @param file
   * @param encoding
   * @return
   * @throws Exception
   */
  public long parse(CsvParser csvParser, File file,
      Charset encoding) throws Exception {
    String[] nextLine;
    long noOfLines = 0;

    while ((nextLine = csvParser.parseNext()) != null) {
      RecordMetaData recordMetaData = csvParser.getRecordMetadata();
      String headerNames[] = recordMetaData.headers();
      noOfLines++;
      log.trace(Arrays.toString(nextLine));
    }
    if (noOfLines == 0) {
      throw new Exception("No lines available to read after skip lines");
    }
    csvParser.stopParsing();
    return noOfLines;
  }

}
