/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.es.web.showcase.status.show.web.controller;

import com.sishuok.es.common.Constants;
import com.sishuok.es.common.entity.search.Searchable;
import com.sishuok.es.common.plugin.entity.Stateable;
import com.sishuok.es.common.web.controller.BaseCRUDController;
import com.sishuok.es.web.showcase.status.show.entity.Show;
import com.sishuok.es.web.showcase.status.show.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-1-28 下午4:29
 * <p>Version: 1.0
 */
@Controller
@RequestMapping(value = "/showcase/status/show")
public class ShowController extends BaseCRUDController<Show, Long> {

    private ShowService auditService;

    @Autowired
    public ShowController(ShowService auditService) {
        super(auditService);
        this.auditService = auditService;
    }

    @Override
    protected void setCommonData(Model model) {
        model.addAttribute("statusList", Stateable.ShowStatus.values());
    }

    @Override
    public String list(Searchable searchable, Model model) {
        setCommonData(model);
        return super.list(searchable, model);
    }


    @RequestMapping(value = "{id}/{status}", method = RequestMethod.GET)
    public String audit(
            HttpServletRequest request,
            @PathVariable("id") Show audit,
            @PathVariable("status") Stateable.ShowStatus status,
            RedirectAttributes redirectAttributes
        ) {


        audit.setStatus(status);
        auditService.update(audit);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "操作成功！");

        return "redirect:" + request.getAttribute(Constants.BACK_URL);
    }

    /**
     * 验证失败返回true
     * @param m
     * @param result
     * @return
     */
    @Override
    protected boolean hasError(Show m, BindingResult result) {
        Assert.notNull(m);

        return result.hasErrors();
    }

}
