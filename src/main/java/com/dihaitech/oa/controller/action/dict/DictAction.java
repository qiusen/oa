package com.dihaitech.oa.controller.action.dict;

import java.util.Date;
import java.util.List;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Dict;
import com.dihaitech.oa.model.Manager;
import com.dihaitech.oa.service.IDictService;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;

/**
 * 字典Action
 * 
 * @author cg
 *
 * @date 2013-10-09
 */
 @SuppressWarnings("serial")
public class DictAction extends BaseAction {
	private Dict dict = new Dict();
	private IDictService dictService;
	
	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}
	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}
	/* 
	 * 字典查询
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

			Page pageInfo = dictService.selectDict(dict,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Dict> resultList = this.dictService.selectDict(dict,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","dictAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Dict json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 字典
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 字典
	 * @return
	 */
	public String addSave(){
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		dict.setCreateuser(managerVO.getUsername());
		dict.setCreatetime(new Date());
		dict.setUpdateuser(managerVO.getUsername());
		dict.setUpdatetime(new Date());
		
		dictService.addSave(dict);
		return "addSave";
	}
	
	/**
	 * 修改 字典
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			dict.setId(id);
		}else{
			return null;
		}
		
		Dict dictVO = dictService.selectDictById(dict);
		this.getRequest().setAttribute("dict", dictVO);
		return "edit";
	}
	
	/**
	 * 保存修改 字典
	 * @return
	 */
	public String editSave(){
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		dict.setUpdateuser(managerVO.getUsername());
		dict.setUpdatetime(new Date());
		
		dictService.editSave(dict);
		return "editSave";
	}
	
	/**
	 * 删除 字典
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		dictService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 字典
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
			dictService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}