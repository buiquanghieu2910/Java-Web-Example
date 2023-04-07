/**
 * BUI_QUANG_HIEU, 2023
 * DatabasePropertiesUtil.java
 */
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author BUI_QUANG_HIEU
 */
public class DatabasePropertiesUtil {
    // lưu các cặp <key, value> trong file properties
    private static Map<String, String> mapDBProperties = new HashMap<>();

    private final static String PROPERTIES_DATABASE_PATH = "properties/database.properties";

    static {
        try {
            // tạo đối tượng kiểu Properties
            Properties properties = new Properties();

            // đọc file properties
            InputStream inputStream = DatabasePropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_DATABASE_PATH);
            properties.load(inputStream);

            // lưu các giá trị key trong file properties
            Enumeration<?> enumeration = properties.propertyNames();

            // true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
            while (enumeration.hasMoreElements()) {
                // key = key tiếp theo
                String key = (String) enumeration.nextElement();
                // lấy value tương ứng với key
                String value = properties.getProperty(key);
                // thêm vào list
                mapDBProperties.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lấy value tương ứng với key trong file properties
     *
     * @param key tên key trong file properties
     * @return trả về value tương ứng với key
     */
    public static String getValueByKey(String key) {
        String value = mapDBProperties.get(key);
        return value;
    }

}
