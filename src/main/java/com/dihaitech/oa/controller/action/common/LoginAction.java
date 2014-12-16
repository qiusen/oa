package com.dihaitech.oa.controller.action.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Catalog;
import com.dihaitech.oa.model.ManagerRole;
import com.dihaitech.oa.model.Menu;
import com.dihaitech.oa.model.Module;
import com.dihaitech.oa.model.Role;
import com.dihaitech.oa.service.ICatalogService;
import com.dihaitech.oa.service.IManagerRoleService;
import com.dihaitech.oa.service.IMenuService;
import com.dihaitech.oa.service.IModuleService;
import com.dihaitech.oa.service.IRoleService;
import com.dihaitech.tserver.managercenter.Manager;
import com.dihaitech.tserver.managercenter.ManagerCenterService;

/**
 * 登录Action
 * 
 * @author qiusen
 *
 * 2012-2-8 下午04:26:17
 */
public class LoginAction extends BaseAction {
	
	private static final Log logger = LogFactory.getLog(LoginAction.class);

	private static final long serialVersionUID = 3243281533706763908L;
	
	
	private IRoleService roleService;
	
	private IModuleService moduleService;
	
	private ICatalogService catalogService;
	
	private IMenuService menuService;
	
	private IManagerRoleService managerRoleService;
	

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public ICatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(ICatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IManagerRoleService getManagerRoleService() {
		return managerRoleService;
	}

	public void setManagerRoleService(IManagerRoleService managerRoleService) {
		this.managerRoleService = managerRoleService;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		String email = this.getRequest().getParameter("email");
		String password = this.getRequest().getParameter("password");
//		String checkCode = this.getRequest().getParameter("checkCode");
		
//		String sessionCheckCode = (String)this.getSession().getAttribute("checkCode");
		System.out.println("email:" + email +"===========password:" + password);
//		System.out.println("checkCode:" + checkCode +"===========sessionCheckCode:" + sessionCheckCode);
//		if(checkCode!=null && checkCode.equalsIgnoreCase(sessionCheckCode)){
//			Manager manager = new Manager();
//			manager.setUsername(username);
//			manager.setPassword(MD5Util.stringToMD5(password));
//			Manager managerVO = managerService.login(manager);
			Manager managerVO = this.tserverLogin(email, password);
			
			if(managerVO!=null){
				//若帐号被冻结  登录失败
				if(managerVO.getStatus()==0){
					this.getRequest().setAttribute("errorStr", "您的帐号存在问题（已失效），请联系管理员！！！");
					return "login";
				}
//				//记录最后登录的时间和IP
//				managerVO.setLogintime(new Date());
//				managerVO.setLoginip(getRealIP());
//				managerService.editSaveManager(managerVO);
				
				ManagerRole managerRole = new ManagerRole();
				managerRole.setEmail(managerVO.getEmail());
				
				ManagerRole managerRoleVO = managerRoleService.selectManagerRoleByEmail(managerRole);
				
				if(managerRoleVO==null){		//没有权限记录
					managerRole.setRoleId(0);
					managerRole.setCreatetime(new Date());
					managerRole.setUpdatetime(new Date());
					managerRoleService.addSave(managerRole);
					
					this.getSession().setAttribute("role", null);
					this.getSession().setAttribute("rightsMap", null);
					this.getSession().setAttribute("menuList", null);
					this.getSession().setAttribute("catalogList", null);
					this.getSession().setAttribute("moduleList", null);
					
				}else if(managerRoleVO.getRoleId()==0){	//没有赋权
					
					this.getSession().setAttribute("role", null);
					this.getSession().setAttribute("rightsMap", null);
					this.getSession().setAttribute("menuList", null);
					this.getSession().setAttribute("catalogList", null);
					this.getSession().setAttribute("moduleList", null);
					
				}else{
					//设置权限
					Role role = new Role();
					role.setId(managerRoleVO.getRoleId());
					Role roleVO = roleService.selectRoleById(role);
					
					this.getSession().setAttribute("role", roleVO);	//角色信息存入SESSION
					
					String rightsStr = roleVO.getRights();
					if(rightsStr!=null && rightsStr.trim().length()>0){
						Module module = new Module();
						module.setIdStr("0" + rightsStr + "0");
						module.setStatus(1);
						List<Module> moduleList = moduleService.selectModuleByIdStr(module);
						if(moduleList!=null && moduleList.size()>0){
							Map<String, Module> rightsMap = new HashMap<String, Module>();
							Module moduleTemp = null;
							for(int i=0;i<moduleList.size();i++){
								moduleTemp = moduleList.get(i);
								rightsMap.put(moduleTemp.getModuleurl(), moduleTemp);
							}

							this.getSession().setAttribute("rightsMap", rightsMap);
							
							//拼凑树
							StringBuffer strbuf = new StringBuffer("where id in (");
							for(int i=0;i<moduleList.size();i++){
								moduleTemp = moduleList.get(i);
								if(i!=0){
									strbuf.append("," + moduleTemp.getCatalogId());
								}else{
									strbuf.append(moduleTemp.getCatalogId());
								}
							}
							strbuf.append(")");
							Catalog catalog = new Catalog();
							catalog.setStatus(1);	//有效
							catalog.setIdStr(strbuf.toString());
							List<Catalog> catalogList = catalogService.selectCatalogByIdsStatus(catalog);
							this.getSession().setAttribute("catalogList", catalogList);
							
							StringBuffer catalogstrbuf = new StringBuffer("where id in (");
							if(catalogList!=null && catalogList.size()>0){
								Catalog catalogTemp = null;
								for(int i=0;i<catalogList.size();i++){
									catalogTemp = catalogList.get(i);
									if(i!=0){
										catalogstrbuf.append("," + catalogTemp.getMenuId());
									}else{
										catalogstrbuf.append(catalogTemp.getMenuId());
									}
								}
							}
							catalogstrbuf.append(")");
							Menu menu = new Menu();
							menu.setStatus(1);
							menu.setIdStr(catalogstrbuf.toString());
							List<Menu> menuList = menuService.selectMenuByIdsStatus(menu);
							this.getSession().setAttribute("menuList", menuList);
						}
						this.getSession().setAttribute("moduleList", moduleList);
						
					}
				}
				
				
				//存session
				this.getSession().setAttribute("manager", managerVO);
				
				//记录日志
				this.recordLogs(logger, "login", managerVO.getNickname() + " 登录");
			}else{
				this.getRequest().setAttribute("errorStr", "用户名或密码错误");
				return "login";
			}
			// 暂时用status做身份处理
//			if (managerVO.getStatus()==3){
//				return "login2";
//			}

//		}else{
//			this.getRequest().setAttribute("errorStr", "验证码错误");
//			return "login";
//		}
		
		return SUCCESS;
	}
	
	private Manager tserverLogin(String email, String password){
		Manager manager = null;
		try {
			   TTransport transport = new TFramedTransport(new TSocket("localhost", 19090));
			   
			   TBinaryProtocol protocol = new TBinaryProtocol(transport);
			   //TCompactProtocol protocol = new TCompactProtocol(transport);
			   
			   ManagerCenterService.Client client = new ManagerCenterService.Client(protocol);
			   transport.open();
			   
			   Map<String, String> param = new HashMap<String, String>();

			   param.put("tName", "manager");
			   param.put("sName", "selectManagerByEmailPassword");
			   param.put("pValue", email + "," + password);
			   
			   List<Manager> managerList = client.funCallManager(System.currentTimeMillis(), "login", param);
			   if(managerList!=null && managerList.size()>0){
				   manager = managerList.get(0);
			   }
			  
			   transport.close();
		} catch (TException x) {
			   x.printStackTrace();
		}
		return manager;
	}
	
	/**
	 * 登出
	 * @return
	 */
	public String logOff(){
		Manager manager = null;
		Object o = this.getSession().getAttribute("manager");
		if(o!=null){
			manager = (Manager)this.getSession().getAttribute("manager");
			//记录日志
			this.recordLogs(logger, "logoff", manager.getNickname() + " 登出");
			this.getSession().removeAttribute("manager");
			this.getSession().removeAttribute("role");
			this.getSession().removeAttribute("rightsMap");
			this.getSession().removeAttribute("menuList");
			this.getSession().removeAttribute("catalogList");
			this.getSession().removeAttribute("moduleList");
		}
		
		return "logOff";
	}
	
//	/**
//	 * SESSION超时后AJAX登录使用
//	 * @return
//	 */
//	public String loginForAjax(){
//		
//		String username = this.getRequest().getParameter("username");
//		String password = this.getRequest().getParameter("password");
//		
//		System.out.println("username:" + username +"===========password:" + password);
//		
//		Manager manager = new Manager();
//		manager.setUsername(username);
//		manager.setPassword(password);
//		Manager managerVO = managerService.login(manager);
//		
//		try {
//			PrintWriter pw = this.getResponse().getWriter();
//			
//			if(managerVO!=null){
//				//若帐号被冻结  登录失败
//				if(managerVO.getStatus()==0){
//					this.getRequest().setAttribute("errorStr", "您的帐号存在问题，请联系管理员");
//					return "login";
//				}
//				//记录最后登录的时间和IP
//				managerVO.setLogintime(new Date());
//				managerVO.setLoginip(getRealIP());
//				managerService.editSaveManager(managerVO);
//				//存session
//				this.getSession().setAttribute("manager", managerVO);
//				
//				pw.write("{\"statusCode\":\"200\",\"message\":\"欢迎回来~\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}");
//			}else{
//				pw.write("{\"statusCode\":\"300\",\"message\":\"用户名或密码错误，请重试~\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
}
