package oracle.iam.webservice.provider;

import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

import oracle.iam.platform.OIMClient;
import oracle.iam.platform.context.ContextAwareNumber;
import oracle.iam.platform.context.ContextAwareString;
import oracle.iam.platform.context.ContextManager;
import oracle.iam.platform.utils.ConfigurationClient;
import oracle.iam.reconciliation.api.ReconOperationsService;
import oracle.iam.webservice.domain.ReconEvent;

public class ReconProvider {
	private static ReconProvider reconProvider;
	private OIMClient oimClient;
	private ReconOperationsService reconSvc;
	private Random r;

	private ReconProvider() {

		Properties jndiProperties = ConfigurationClient.getComplexSettingByPath("Discovery.CoreServer").getAllSettings(); 
			
		r = new SecureRandom();
		try {
			oimClient = new OIMClient(jndiProperties);
			oimClient.login("xelsysadm", "Welcome1".toCharArray());
			reconSvc = oimClient.getService(ReconOperationsService.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static ReconProvider getProvider() {
		if (reconProvider == null) {
			reconProvider = new ReconProvider();
		}

		return reconProvider;
	}

	
	public String createReconEvent(ReconEvent re) {
		String id = "";
		
		int jobID = Math.abs(r.nextInt());
		String jobName = getRandomString(6);
		ContextManager.pushContext(jobID+"", ContextManager.ContextTypes.RECON, "Setup");
		ContextManager.setValue("JOBHISTORYID", new ContextAwareNumber(jobID), true);
		ContextManager.setValue("JOBNAME", new ContextAwareString(jobName));
		
		try {
			long rceKey = reconSvc.createReconciliationEvent(re.getObjName(), re.getData(), true);
			id = rceKey + "";
			reconSvc.callingEndOfJobAPI();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return id;
	}
	
	public String createDeleteReconEvent(ReconEvent re) {
		String id = "";

		int jobID = r.nextInt();
		String jobName = getRandomString(6);
		ContextManager.pushContext(jobID+"", ContextManager.ContextTypes.RECON, "Setup");
		ContextManager.setValue("JOBHISTORYID", new ContextAwareNumber(jobID), true);
		ContextManager.setValue("JOBNAME", new ContextAwareString(jobName));
		
		try {
			long rceKey = reconSvc.createDeleteReconciliationEvent(re.getObjName(), re.getData());
			id = rceKey + "";
			reconSvc.callingEndOfJobAPI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return id;
		
	}
	
	private String getRandomString(int size) {
		String alphabets = "abcdefghijklmnopqrstuvwxyz";
		String s = "";
		
		for (int i=0 ; i<size ; i++) {
			s += alphabets.charAt(Math.abs(r.nextInt() % alphabets.length()));
		}
		
		return s;
	}
}
