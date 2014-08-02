package app.main.service.jms;

import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;

import javax.naming.NamingException;

import net.sf.ehcache.distribution.jms.JMSUtil;

import org.apache.activemq.jndi.ActiveMQInitialContextFactory;

/**
 * Created with IntelliJ IDEA.
 * User: josgraha
 * Date: 8/1/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class AppActiveMQInitialContextFactory extends ActiveMQInitialContextFactory {

    /**
     * Creates an initial context with
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Context getInitialContext(Hashtable environment) throws NamingException {

        Map<String, Object> data = new ConcurrentHashMap<String, Object>();

        String replicationTopicConnectionFactoryBindingName = (String) environment.get(JMSUtil.TOPIC_CONNECTION_FACTORY_BINDING_NAME);
        if (replicationTopicConnectionFactoryBindingName != null) {
            try {
                data.put(replicationTopicConnectionFactoryBindingName, createConnectionFactory(environment));
            } catch (URISyntaxException e) {
                throw new NamingException("Error initializing TopicConnectionFactory with message " + e.getMessage());
            }
        }
        String getQueueConnectionfactoryBindingName = (String) environment.get(JMSUtil.GET_QUEUE_CONNECTION_FACTORY_BINDING_NAME);

        try {
            data.put(getQueueConnectionfactoryBindingName, createConnectionFactory(environment));
        } catch (URISyntaxException e) {
            throw new NamingException("Error initializing TopicConnectionFactory with message " + e.getMessage());
        }

        String replicationTopicBindingName = (String) environment.get(JMSUtil.REPLICATION_TOPIC_BINDING_NAME);
        String getQueueBindingName = (String) environment.get(JMSUtil.GET_QUEUE_BINDING_NAME);
        if (replicationTopicBindingName != null) {
            data.put(replicationTopicBindingName, createTopic(replicationTopicBindingName));
        }
        data.put(getQueueBindingName, createQueue(getQueueBindingName));
        return createContext(environment, data);
    }
}