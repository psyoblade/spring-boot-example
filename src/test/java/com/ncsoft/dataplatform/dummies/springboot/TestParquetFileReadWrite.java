package com.ncsoft.dataplatform.dummies.springboot;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.util.Utf8;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.avro.AvroReadSupport;
import org.apache.parquet.hadoop.ParquetWriter;
import org.junit.Test;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestParquetFileReadWrite {

    private static Logger logger = LoggerFactory.getLogger(TestParquetFileReadWrite.class);
    private static String className = TestParquetFileReadWrite.class.getSimpleName();

    private final boolean compat;
    private final Configuration testConf = new Configuration();

    public TestParquetFileReadWrite(boolean compat) {
        this.compat = compat;
        this.testConf.setBoolean(AvroReadSupport.AVRO_COMPATIBILITY, compat);
        testConf.setBoolean("parquet.avro.add-list-element-records", false);
        testConf.setBoolean("parquet.avro.write-old-list-structure", false);
    }

    @Test
    public void testMapWithUtf8Key() throws Exception {
        Schema schema = new Schema.Parser().parse(
                Resources.getResource("map.avsc").openStream());

        String filePath = createTempFile().getPath();
        Path file = new Path(filePath);

        ParquetWriter<GenericRecord> writer = AvroParquetWriter
                .<GenericRecord>builder(file)
                .withSchema(schema)
                .withConf(testConf)
                .build();

        // Write a record with a map with Utf8 keys.
        GenericData.Record record = new GenericRecordBuilder(schema)
                .set("mymap", ImmutableMap.of(new Utf8("a"), 1, new Utf8("b"), 2))
                .build();
        writer.write(record);
        writer.close();

        AvroParquetReader<GenericRecord> reader = new AvroParquetReader<>(testConf, file);
        GenericRecord nextRecord = reader.read();

        assertNotNull(nextRecord);
        assertEquals(ImmutableMap.of(str("a"), 1, str("b"), 2), nextRecord.get("mymap"));
    }

    private File createTempFile() throws IOException {
        File tmp = File.createTempFile(getClass().getSimpleName(), ".tmp");
        tmp.deleteOnExit();
        tmp.delete();
        return tmp;
    }

    /**
     * Return a String or Utf8 depending on whether compatibility is on
     */
    public CharSequence str(String value) {
        return compat ? value : new Utf8(value);
    }
}
