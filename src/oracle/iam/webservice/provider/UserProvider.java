package oracle.iam.webservice.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import oracle.iam.identity.usermgmt.api.UserManager;
import oracle.iam.identity.usermgmt.vo.User;
import oracle.iam.identity.usermgmt.vo.UserManagerResult;
import oracle.iam.platform.OIMClient;
import oracle.iam.platform.entitymgr.vo.SearchCriteria;
import oracle.iam.platform.utils.ConfigurationClient;
import oracle.iam.webservice.domain.UserVO;

public class UserProvider {

	private static UserProvider userProvider;
	private OIMClient oimClient;
	private UserManager usrMgr;

	private UserProvider() {

		Properties jndiProperties = ConfigurationClient.getComplexSettingByPath("Discovery.CoreServer").getAllSettings();

		try {
			oimClient = new OIMClient(jndiProperties);
			oimClient.login("xelsysadm", "Welcome1".toCharArray());
			usrMgr = oimClient.getService(UserManager.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static UserProvider getProvider() {
		if (userProvider == null) {
			userProvider = new UserProvider();
		}

		return userProvider;
	}

	public UserVO findUser(String id) {
		UserVO u = new UserVO();

		try {
			
	        SearchCriteria criteria = new SearchCriteria("usr_key",
	        		id + "", SearchCriteria.Operator.EQUAL);
	        Set retSet = new HashSet();
	        retSet.add("usr_key");
	        retSet.add("User Login");
	        retSet.add("First Name");
	        retSet.add("Last Name");
	        retSet.add("Role");
	        retSet.add("Xellerate Type");
	        retSet.add("act_key");
			
	        List<User> users = usrMgr.search(criteria, retSet, null);
	        User user = users.get(0);

			
			u.setId(user.getId());
			u.setUserLogin(user.getLogin());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setRole(user.getEmployeeType());
			u.setType(user.getUserType());
			u.setOrg(user.getOrganizationKey());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return u;
	}
	
	public List<UserVO> findUsers() {
		List<UserVO> retUsers = new ArrayList<UserVO>();

		try {
	        SearchCriteria criteria = new SearchCriteria("User Login",
	        		"*", SearchCriteria.Operator.BEGINS_WITH);
	        Set retSet = new HashSet();
	        retSet.add("usr_key");
	        retSet.add("User Login");
	        retSet.add("First Name");
	        retSet.add("Last Name");
	        retSet.add("Role");
	        retSet.add("Xellerate Type");
	        retSet.add("act_key");
			
	        List<User> users = usrMgr.search(criteria, retSet, null);

			for (User user : users) {
				UserVO u = new UserVO();
			
				u.setId(user.getId());
				u.setUserLogin(user.getLogin());
				u.setFirstName(user.getFirstName());
				u.setLastName(user.getLastName());
				u.setRole(user.getEmployeeType());
				u.setType(user.getUserType());
				u.setOrg(user.getOrganizationKey());
				
				retUsers.add(u);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return retUsers;
	}
	
	public String createUser(String login, String first, String last, String org) {
		String userKey = "";
		try {
			HashMap<String, Object> usrMap = new HashMap<String, Object>();
			usrMap.put("act_key", new Long(org));
			usrMap.put("User Login", login);
			usrMap.put("First Name", first);
			usrMap.put("Last Name",  last);
			usrMap.put("usr_password", "Welcome1");
			usrMap.put("Role", "Full-Time");
			usrMap.put("Xellerate Type", "End-User");
			usrMap.put("Common Name", login);
			usrMap.put("Email", login + "@oracle.com");
			
			User u = new User(login, usrMap);
			UserManagerResult res = usrMgr.create(u);
			userKey = res.getEntityId();
			System.out.println("Created user " + login + " " + userKey);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return userKey;
	}		
}
