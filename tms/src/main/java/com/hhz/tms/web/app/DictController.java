/**
 * 
 */
package com.hhz.tms.web.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hhz.tms.admin.service.app.DictService;
import com.hhz.tms.entity.app.DictData;
import com.hhz.tms.entity.app.DictType;
import com.hhz.tms.util.JsonUtil;
import com.hhz.tms.util.RenderUtil;
import com.hhz.tms.util.service.BaseService;
import com.hhz.tms.web.PageController;

/**
 * @author huangjian
 * 
 */
@Controller
@RequestMapping(value = "/admin/dict")
public class DictController extends PageController<DictType> {
	@Autowired
	private DictService dictService;

	@RequestMapping(value = "")
	public String index(Model model) {
		return "admin/app/dict";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		DictType dictType;
		if (id > 0)
			dictType = dictService.getEntity(id);
		else {
			dictType = new DictType();
			dictType.setId(0l);
		}
		model.addAttribute("dictType", dictType);
		return "admin/app/dict-detail";
	}

	@RequestMapping(value = "isTypeExists")
	public void isTypeExists(HttpServletRequest req, HttpServletResponse resp) {
		String newDictTypeCd = req.getParameter("dictTypeCd").trim();
		String oldDictTypeCd = req.getParameter("oldDictTypeCd").trim();

		if (dictService.existDictTypeCd(newDictTypeCd, oldDictTypeCd)) {
			RenderUtil.renderText("false", resp);
		} else {
			RenderUtil.renderText("true", resp);
		}
	}

	@RequestMapping(value = "listData/{id}")
	public void listData(@PathVariable("id") Long id, HttpServletResponse response) {
		if (id != null && id > 0) {
			DictType dictType = dictService.getEntity(id);
			JsonUtil.renderListJson(response, dictType.getDictDatas());
		} else {
			JsonUtil.renderListJson(response, new ArrayList(0));
		}
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("preload") DictType dictType, HttpServletRequest request,
			HttpServletResponse response) {
		String result = "success";
		dictType = dictService.save(dictType);
		saveDatas(request, dictType.getId());
		result = result + ":" + String.valueOf(dictType.getId());
		RenderUtil.renderText(result, response);
		return null;
	}

	@RequestMapping(value = "deleteData/{id}")
	public String deleteData(@PathVariable("id") Long id, HttpServletResponse response) {
		dictService.deleteData(id);
		JsonUtil.renderSuccess("保存成功", response);
		return null;
	}

	private void saveDatas(HttpServletRequest request, Long dictTypeId) {
		try {
			List<DictData> lstDeleted = JsonUtil.getDeletedRecords(DictData.class, request);
			List<DictData> lstInsert = JsonUtil.getInsertRecords(DictData.class, request);
			List<DictData> lstUpdated = JsonUtil.getUpdatedRecords(DictData.class, request);
			dictService.saveDatas(lstInsert, lstUpdated, lstDeleted, dictTypeId);
		} catch (Exception e) {
			logger.error("dictData save error!", e);
		}
	}

	@Override
	public BaseService<DictType> getService() {
		// TODO Auto-generated method stub
		return dictService;
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preload")
	public DictType getEntity(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			if (id > 0)
				return dictService.getEntity(id);
			else {
				return new DictType();
			}
		}
		return null;
	}
}
