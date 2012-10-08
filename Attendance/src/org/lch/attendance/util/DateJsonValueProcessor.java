package org.lch.attendance.util;

import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
  
public class DateJsonValueProcessor implements JsonValueProcessor {  
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";  
    private DateFormat dateFormat;  
  
    /** 
     * 构造方法. 
     * 
     * @param datePattern 日期格式 
     */  
    public DateJsonValueProcessor(String datePattern) {  
        try {  
            dateFormat = new SimpleDateFormat(datePattern);  
        } catch (Exception ex) {  
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);  
        }  
    }  
  
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
        return process(value);  
    }  
  
    public Object processObjectValue(String key, Object value,  
        JsonConfig jsonConfig) {  
        return process(value);  
    }  
  
    private Object process(Object value) {  
        return dateFormat.format((Date) value);  
    }  
    
    /** 
     * write. 
     * 
     * @param bean obj 
     * @param writer 输出流 
     * @param excludes 不转换的属性数组 
     * @param datePattern date到string转换的模式 
     * @throws Exception 写入数据可能出现异常 
     */  
    public static void write(Object bean, Writer writer,  
        String[] excludes, String datePattern) throws Exception {  
        JsonConfig jsonConfig = configJson(excludes, datePattern);  
      
        JSON json = JSONSerializer.toJSON(bean, jsonConfig);  
      
        json.write(writer);  
    }  
      
    /** 
     * 配置json-lib需要的excludes和datePattern. 
     * 
     * @param excludes 不需要转换的属性数组 
     * @param datePattern 日期转换模式 
     * @return JsonConfig 根据excludes和dataPattern生成的jsonConfig，用于write 
     */  
    public static JsonConfig configJson(String[] excludes,  
        String datePattern) {  
        JsonConfig jsonConfig = new JsonConfig();  
        jsonConfig.setExcludes(excludes);  
        jsonConfig.setIgnoreDefaultExcludes(false);  
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
        jsonConfig.registerJsonValueProcessor(Date.class,  
            new DateJsonValueProcessor(datePattern));  
      
        return jsonConfig;  
    }  
}  