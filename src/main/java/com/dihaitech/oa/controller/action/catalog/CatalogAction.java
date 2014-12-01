package com.dihaitech.oa.controller.action.catalog;

import java.util.Date;
import java.util.List;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Catalog;
import com.dihaitech.oa.model.Menu;
import com.dihaitech.oa.service.ICatalogService;
import com.dihaitech.oa.service.IMenuService;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;

/**
 * 目录Action
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
 @SuppressWarnings("serial")
public class CatalogAction extends BaseAction {
	private Catalog catalog = new Catalog();
	private ICatalogService catalogService;
	
	private IMenuService menuService;
	
	
	
	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	public ICatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(ICatalogService catalogService) {
		this.catalogService = catalogService;
	}
	/* 
	 * 目录查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			
			pageSize = TypeUtil.stringToInt(pageSizeStr);
			if (pageSize <= 0) {
				pageSize = Property.PAGESIZE;
			}

			pageNo = TypeUtil.stringToInt(pageNoStr);
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			}else{
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = catalogService.selectCatalog(catalog,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Catalog> resultList = this.catalogService.selectCatalog(catalog,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","catalogAction");
			
			//菜单
			Menu menu = new Menu();
			menu.setStatus(1);
			List<Menu> menuList = menuService.selectAllByStatus(menu);
			this.getRequest().setAttribute("menuList", menuList);

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Catalog json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 目录
	 * @return
	 */
	public String add(){
		Menu menu = new Menu();
		menu.setStatus(1);
		List<Menu> menuList = menuService.selectAllByStatus(menu);
		this.getRequest().setAttribute("menuList", menuList);
		return "add";
	}
	
	/**
	 * 保存添加 目录
	 * @return
	 */
	public String addSave(){
		catalog.setCreatetime(new Date());
		catalogService.addSave(catalog);
		return "addSave";
	}
	
	/**
	 * 修改 目录
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			catalog.setId(id);
		}else{
			return null;
		}
		
		Catalog catalogVO = catalogService.selectCatalogById(catalog);
		this.getRequest().setAttribute("catalog", catalogVO);
		
		Menu menu = new Menu();
		menu.setStatus(1);
		List<Menu> menuList = menuService.selectAllByStatus(menu);
		this.getRequest().setAttribute("menuList", menuList);
		return "edit";
	}
	
	/**
	 * 保存修改 目录
	 * @return
	 */
	public String editSave(){
		catalogService.editSave(catalog);
		return "editSave";
	}
	
	/**
	 * 删除 目录
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		catalogService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 目录
	 * @return
	 */
	public String deleteByIds(){
		String[] ids = this.getRequest().getParameterValues("id");
		StringBuffer strbuf = new StringBuffer(" where id in(");
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != 0) {
					strbuf.append("," + ids[i]);
				} else {
					strbuf.append(ids[i]);
				}
			}
			strbuf.append(")");
			catalogService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}