package com.company.portal.demo.util;

import com.company.portal.demo.constant.CustomDateFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GeneralDateSerializer extends StdSerializer<Date> {
    private SimpleDateFormat formatter = new SimpleDateFormat(CustomDateFormat.GENERAL_DATE_FORMAT, new Locale("tr"));

    public GeneralDateSerializer() {
        this(null);
    }

    public GeneralDateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeString(formatter.format(value));
    }
}

//dbden gelirken dışarıya verdiğimiz