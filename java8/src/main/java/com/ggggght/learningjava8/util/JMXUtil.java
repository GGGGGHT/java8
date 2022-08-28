import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import java.io.File;
import java.io.IOException;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXUtil {
    private static final String CONNECTOR_ADDRESS =
        "com.sun.management.jmxremote.localConnectorAddress";

    /**
     * get JMX connect URL
     * @param id target vm pid
     * @return url eg: service:jmx:rmi://127.0.0.1/stub/rO0ABXNyAC5qYXZheC5tYW5hZ2VtZW50LnJlbW90ZS5ybWkuUk1JU2VydmVySW1wbF9TdHViAAAAAAAAAAICAAB4cgAaamF2YS5ybWkuc2VydmVyLlJlbW90ZVN0dWLp/tzJi+FlGgIAAHhyABxqYXZhLnJtaS5zZXJ2ZXIuUmVtb3RlT2JqZWN002G0kQxhMx4DAAB4cHc0AAtVbmljYXN0UmVmMgAACTEyNy4wLjAuMQAAMb2ZGQ1IqvpWrdJNZeoAAAGC4md/z4ABAHg=
     * @throws IOException 
     * @throws AgentLoadException
     * @throws AgentInitializationException
     * @throws AttachNotSupportedException
     */
    public String getJMXConnectURL(String id)
        throws IOException, AgentLoadException, AgentInitializationException,
        AttachNotSupportedException {

        // attach to the target application
        VirtualMachine vm = VirtualMachine.attach(id);

        // get the connector address
        String connectorAddress =
            vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);

        // no connector address, so we start the JMX agent
        if (connectorAddress == null) {
            String agent = vm.getSystemProperties().getProperty("java.home") +
                File.separator + "lib" + File.separator + "management-agent.jar";
            vm.loadAgent(agent);

            // agent is started, get the connector address
            connectorAddress =
                vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);
        }

        // establish connection to connector server
        // JMXServiceURL url = new JMXServiceURL(connectorAddress);
        // JMXConnector connect = JMXConnectorFactory.connect(url);
        // MBeanServerConnection mBeanServerConnection = connect.getMBeanServerConnection();

        return connectorAddress;
    }
}
