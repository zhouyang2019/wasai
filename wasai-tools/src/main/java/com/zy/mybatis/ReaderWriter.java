package com.zy.mybatis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

final class ReaderWriter {

    private static final ObjectReader READER;

    private static final ObjectWriter WRITER;

    static {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        WRITER = mapper.writer();
        READER = mapper.reader();
    }

    static String write(TreeNode tree) throws JsonProcessingException {
        return WRITER.writeValueAsString(tree);
    }

    static String write(Object o) throws JsonProcessingException {
        return WRITER.writeValueAsString(o);
    }

    static <T> T read(String json, Class<T> t) throws IOException {
        return READER.forType(t).readValue(json);
    }

}