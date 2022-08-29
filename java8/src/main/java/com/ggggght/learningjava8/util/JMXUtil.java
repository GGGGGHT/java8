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
     * @return url 
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
