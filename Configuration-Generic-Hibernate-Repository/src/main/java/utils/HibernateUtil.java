/**
 * BUI_QUANG_HIEU, 2023
 * HibernateUtil.java
 */
package utils;

import domain_models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

/**
 * @author BUI_QUANG_HIEU
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, DatabasePropertiesUtil.getValueByKey("DIALECT"));
        properties.put(Environment.DRIVER, DatabasePropertiesUtil.getValueByKey("DRIVER"));
        properties.put(Environment.URL, DatabasePropertiesUtil.getValueByKey("URL"));
        properties.put(Environment.USER, DatabasePropertiesUtil.getValueByKey("USER"));
        properties.put(Environment.PASS, DatabasePropertiesUtil.getValueByKey("PASS"));
        properties.put(Environment.SHOW_SQL, DatabasePropertiesUtil.getValueByKey("SHOW_SQL"));

        conf.setProperties(properties);
        conf.addAnnotatedClass(User.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

}
