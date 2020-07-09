package com.mojitoming.casclient.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unchecked", "CastCanBeRemovedNarrowingVariableType"})
public class YmlUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(YmlUtils.class);

    private static final String APPLICATION_FILE = "classpath:application.yml";

    private static Map<String, String> result = new HashMap<>();

    /*
     * 根据文件名获取yml的文件内容
     *
     * @param filePath 要读取的配置文件路径
     * @param keys     第一个参数对应第一个key，第二个参数对应第二个key 比如spring.name下的所有 就是两个参数、
     *                 getYmlByFileName(bootstrap_file,"spring", "name");
     * @return Map<String, String>
     */
    public static Map<String, String> getYmlByFileName(String filePath, String... keys) {
        result = new HashMap<>();
        if (filePath == null) filePath = APPLICATION_FILE;
        InputStream in = null;
        try {
            File file = ResourceUtils.getFile(filePath);
            in = new BufferedInputStream(new FileInputStream(file));
            Yaml props = new Yaml();
            Object obj = props.loadAs(in, Map.class);
            Map<String, Object> param = (Map<String, Object>) obj;

            for (Map.Entry<String, Object> entry : param.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                if (keys.length != 0 && !keys[0].equals(key)) {
                    continue;
                }
                if (val instanceof Map) {
                    forEachYaml(key, (Map<String, Object>) val, 1, keys);
                } else {
                    result.put(key, val.toString());
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /*
     * 遍历yml文件，获取map集合
     *
     * @param key_str
     * @param obj
     * @param i
     * @param keys
     */
    public static void forEachYaml(String key_str, Map<String, Object> obj, int i, String... keys) {
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (keys.length > i && !keys[i].equals(key)) {
                continue;
            }
            String str_new;
            if (!StringUtils.isEmpty(key_str)) {
                str_new = key_str + "." + key;
            } else {
                str_new = key;
            }
            if (val instanceof Map) {
                forEachYaml(str_new, (Map<String, Object>) val, ++i, keys);
                i--;
            } else {

                result.put(str_new, val.toString());
            }
        }

    }

    /*
     * 根据 key 获取 value
     *
     * @return String
     */
    public static String getValueByKey(String name) {
        return Objects.requireNonNull(getYmlByFileName(APPLICATION_FILE)).get(name);
    }

    /*
     * Annotation:
     * 根据 keys 获取 value
     *
     * @Author: Adam Ming
     * @Date: Jul 9, 2020 at 5:03:21 PM
     */
    public static String getValueByKeys(String... keys) {
        Map<String, String> ymlByFileName = getYmlByFileName(APPLICATION_FILE, keys);

        assert ymlByFileName != null;

        return String.valueOf(ymlByFileName.values().toArray()[0]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        /*Map<String, String> ymlByFileName = getYmlByFileName(APPLICATION_FILE, "server", "servlet", "context-path");
        assert ymlByFileName != null;
        Set<Map.Entry<String, String>> entries = ymlByFileName.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " === " + entry.getValue());
        }*/

        System.out.println(getValueByKeys("server", "servlet", "context-path"));
    }
}