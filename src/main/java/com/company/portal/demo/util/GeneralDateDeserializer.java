package com.company.portal.demo.util;

import com.company.portal.demo.constant.CustomDateFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GeneralDateDeserializer extends StdDeserializer<Date> {
    private static final Logger logger = LoggerFactory.getLogger(GeneralDateDeserializer.class);
    private SimpleDateFormat formatter = new SimpleDateFormat(CustomDateFormat.GENERAL_DATE_FORMAT, new Locale("tr"));

    public GeneralDateDeserializer() {
        this(null);
    }

    public GeneralDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        String date = jsonparser.getText();
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            logger.error("Parse exception :", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}