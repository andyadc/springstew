package com.andyadc.abatis.spring.boot;

import com.andyadc.abatis.Configuration;
import com.andyadc.abatis.DefaultSqlSessionFactory;
import com.andyadc.abatis.SqlSessionFactoryBuilder;
import com.andyadc.abatis.XNode;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.xml.sax.InputSource;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class SqlSessionFactorySpringBootBuilder extends SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(Connection connection, String packageSearchPath) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setConnection(connection);

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

        List<Element> elements = new ArrayList<>(resources.length);
        for (Resource resource : resources) {
            Document document = new SAXReader().read(new InputSource(new InputStreamReader(resource.getInputStream())));
            elements.add(document.getRootElement());
        }

        configuration.setMapperElement(mapperElement(elements));
        return new DefaultSqlSessionFactory(configuration);
    }

    private Map<String, XNode> mapperElement(List<Element> elements) {
        Map<String, XNode> sqlMap = new HashMap<>();
        for (Element element : elements) {
            String namespace = element.attributeValue("namespace");

            // select
            List<Node> selectNodes = element.selectNodes("select");
            for (Node selectNode : selectNodes) {
                Element e = (Element) selectNode;

                String id = e.attributeValue("id");
                String parameterType = e.attributeValue("parameterType");
                String resultType = e.attributeValue("resultType");
                String sql = e.getText();

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
                xNode.setParameter(parameter);
                xNode.setParameterType(parameterType);
                xNode.setResultType(resultType);
                xNode.setSql(sql);

                sqlMap.put(namespace + "." + id, xNode);
            }
        }
        return sqlMap;
    }
}
