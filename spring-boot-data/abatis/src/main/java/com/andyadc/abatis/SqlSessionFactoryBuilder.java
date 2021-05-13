package com.andyadc.abatis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlSessionFactoryBuilder {

    // {name}
    private static final Pattern pattern = Pattern.compile("(#\\{(.*?)})");

    public DefaultSqlSessionFactory build(Reader reader) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            Element rootElement = document.getRootElement();

            Configuration configuration = parseConfiguration(rootElement);
            return new DefaultSqlSessionFactory(configuration);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Configuration parseConfiguration(Element root) {
        Configuration configuration = new Configuration();
        configuration.setDataSource(buildDataSourceMap(root.selectNodes("//dataSource")));
        configuration.setConnection(connection(configuration.dataSource));
        configuration.setMapperElement(mapperElement(root.selectNodes("//mappers")));
        return configuration;
    }

    private Connection connection(Map<String, String> dataSourceMap) {
        try {
            Class.forName(dataSourceMap.get("driver"));
            return DriverManager.getConnection(dataSourceMap.get("url"), dataSourceMap.get("username"), dataSourceMap.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, XNode> mapperElement(List<Node> nodes) {
        Map<String, XNode> sqlMap = new HashMap<>();
        Element node = (Element) nodes.get(0);

        List<Node> contents = node.content();
        try {
            for (Node content : contents) {
                if (content instanceof Element) {
                    Element e = (Element) content;

                    String resource = e.attributeValue("resource");
                    Reader reader = Resources.getResourceAsReader(resource);

                    SAXReader saxReader = new SAXReader();
                    Document document = saxReader.read(new InputSource(reader));

                    Element rootElement = document.getRootElement();
                    String namespace = rootElement.attributeValue("namespace");

                    // select
                    List<Node> selectNodes = rootElement.selectNodes("select");
                    for (Node sn : selectNodes) {
                        Element element = (Element) sn;
                        String id = element.attributeValue("id");
                        String parameterType = element.attributeValue("parameterType");
                        String resultType = element.attributeValue("resultType");
                        String sql = element.getText();

                        Map<Integer, String> parameter = new HashMap<>();

                        Matcher matcher = pattern.matcher(sql);
                        for (int i = 1; matcher.find(); i++) {
                            String g1 = matcher.group(1);
                            String g2 = matcher.group(2);
                            parameter.put(i, g2);
                            sql = sql.replace(g1, "?");
                        }

                        XNode xNode = new XNode();
                        xNode.setId(id);
                        xNode.setNamespace(namespace);
                        xNode.setParameterType(parameterType);
                        xNode.setResultType(resultType);
                        xNode.setParameter(parameter);
                        xNode.setSql(sql);

                        sqlMap.put(namespace + "." + id, xNode);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlMap;
    }

    private Map<String, String> buildDataSourceMap(List<Node> nodes) {
        Element node = (Element) nodes.get(0);
        List<Node> contents = node.content();
        Map<String, String> dataSourceMap = new HashMap<>(4, 1);
        for (Node content : contents) {
            if (content instanceof Element) {
                Element e = (Element) content;
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                dataSourceMap.put(name, value);
            }
        }
        return dataSourceMap;
    }

}
