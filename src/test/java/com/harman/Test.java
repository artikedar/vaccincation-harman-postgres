package com.harman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

//    public static void main(String[] args) throws JSONException, JsonProcessingException {
// JsonNode root = new ObjectMapper().readTree("{\"CDM_STORE_ID:28640\":{\"supplierName\":\"someSuppName\",\"datasourceName\":\"someDSName\",\"CDM_STORE_ID\":\"28640\",\"Total_store_salesunits\":\"1\"},\"CDM_STORE_ID:127072\":{\"supplierName\":\"someSuppName\",\"datasourceName\":\"someDSName\",\"CDM_STORE_ID\":\"127072\",\"Total_store_salesunits\":1988}}");
// // read JSON
// ObjectMapper mapper = new ObjectMapper();
//// JsonNode patient = root.get("ROOT");
// JSONObject obj = new JSONObject("{\"CDM_STORE_ID:28640\":{\"supplierName\":\"someSuppName\",\"datasourceName\":\"someDSName\",\"CDM_STORE_ID\":\"28640\",\"Total_store_salesunits\":\"1\"},\"CDM_STORE_ID:127072\":{\"supplierName\":\"someSuppName\",\"datasourceName\":\"someDSName\",\"CDM_STORE_ID\":\"127072\",\"Total_store_salesunits\":1988}}");
//
// JsonNode firstObject = root.iterator().next();
//
// CsvMapper csvMapper = new CsvMapper();
// CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
// csvSchemaBuilder.addColumn("key", CsvSchema.ColumnType.STRING);
// firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
// CsvSchema csvSchema = csvSchemaBuilder.build().withUseHeader(true);
//
// System.out.println(csvMapper.writer().withSchema(csvSchema).writeValueAsString(obj));
//    }
//}

 public static void main(String[] args) throws IOException {
  String input = "[\"user1\",\"track1\",\"player1\", \"user1\",\"track2\",\"player2\", \"user1\",\"track3\",\"player3\"]";
  input = input.substring(1, input.length() - 1); // get rid of brackets
  String[] split = input.split(" ");

  FileWriter writer = new FileWriter("D:\\temp\\sto1.csv");

  for (String s : split) {
   String[] split2 = s.split(",");
   writer.write(Arrays.asList(split2).stream().collect(Collectors.joining(",")));
   writer.write("\n"); // newline
  }

  writer.close();
 }
}