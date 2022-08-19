package com.gtf.cloud.provider.service;

import com.gtf.cloud.common.vo.DeptUpdateQo;
import com.gtf.cloud.provider.po.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface DeptService extends IService<Dept> {

    Dept getDeptById(Long id);

    void updateDept(DeptUpdateQo deptUpdateQo);

}
