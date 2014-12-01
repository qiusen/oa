package com.dihaitech.oa.controller.action.area;

import java.util.List;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Area;
import com.dihaitech.oa.model.City;
import com.dihaitech.oa.model.Province;
import com.dihaitech.oa.service.IAreaService;
import com.dihaitech.oa.service.ICityService;
import com.dihaitech.oa.service.IProvinceService;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;

/**
 * 区Action
 * 
 * @author cg
 *
 * @date 2014-03-01
 */
 @SuppressWarnings("serial")
public class AreaAction extends BaseAction {
	private Area area = new Area();
	private IAreaService areaService;
	
	private ICityService cityService;
	
	private IProvinceService provinceService;
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	public IAreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}
	
	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	

	public IProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(IProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	/* 
	 * 区查询
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

			Page pageInfo = areaService.selectArea(area,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Area> resultList = this.areaService.selectArea(area,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","areaAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Area json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
			if(resultList!=null && resultList.size()>0){
				Area area = null;
				StringBuffer strbuf = new StringBuffer();
				for(int i=0;i<resultList.size();i++){
					area = resultList.get(i);
					if(i==0){
						strbuf.append("'" + area.getCityCode() + "'");
					}else{
						strbuf.append(", '" + area.getCityCode() + "'");
					}
				}
				
				City city = new City();
				city.setIdStr(strbuf.toString());
				List<City> cityList = cityService.selectCityByCodes(city);
				this.getRequest().setAttribute("cityList", cityList);
			}
			
			List<Province> provinceList = provinceService.selectAll();
			this.getRequest().setAttribute("provinceList", provinceList);
			
			City city = new City();
			city.setCode(area.getCityCode());
			City cityVO = cityService.selectCityByCode(city);
			this.getRequest().setAttribute("city", cityVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 区
	 * @return
	 */
	public String add(){
		List<Province> provinceList = provinceService.selectAll();
		this.getRequest().setAttribute("provinceList", provinceList);
		
		return "add";
	}
	
	/**
	 * 保存添加 区
	 * @return
	 */
	public String addSave(){
		areaService.addSave(area);
		return "addSave";
	}
	
	/**
	 * 修改 区
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			area.setId(id);
		}else{
			return null;
		}
		
		Area areaVO = areaService.selectAreaById(area);
		this.getRequest().setAttribute("area", areaVO);
		
		City city = new City();
		city.setCode(areaVO.getCityCode());
		City cityVO = cityService.selectCityByCode(city);
		this.getRequest().setAttribute("city", cityVO);
		
		List<Province> provinceList = provinceService.selectAll();
		this.getRequest().setAttribute("provinceList", provinceList);
		
		
		return "edit";
	}
	
	/**
	 * 保存修改 区
	 * @return
	 */
	public String editSave(){
		areaService.editSave(area);
		return "editSave";
	}
	
	/**
	 * 删除 区
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		areaService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 区
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
			areaService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}